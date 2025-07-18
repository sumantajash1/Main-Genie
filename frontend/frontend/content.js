// content.js

// Helper to extract the titleSlug from the URL
function getTitleSlug() {
  const match = window.location.pathname.match(/problems\/([^\/]+)/);
  const slug = match ? match[1] : null;
  console.log('[LeetGenie] Extracted titleSlug:', slug);
  return slug;
}

chrome.runtime.onMessage.addListener((request, sender, sendResponse) => {
  if (request.action === 'scrape_dsa_problem') {
    console.log('[LeetGenie] Received scrape_dsa_problem message');
    const titleSlug = getTitleSlug();
    if (!titleSlug) {
      console.warn('[LeetGenie] Could not extract titleSlug from URL:', window.location.pathname);
      sendResponse({ description: '' });
      return;
    }

    // Get CSRF token and session from cookies
    const getCookie = (name) => {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(';').shift();
      return '';
    };
    const csrftoken = getCookie('csrftoken');
    const session = getCookie('LEETCODE_SESSION');
    console.log('[LeetGenie] CSRF token:', csrftoken);
    console.log('[LeetGenie] LEETCODE_SESSION:', session ? '[present]' : '[missing]');

    const fetchBody = {
      operationName: 'questionData',
      variables: { titleSlug },
      query: 'query questionData($titleSlug: String!) { question(titleSlug: $titleSlug) { content } }'
    };
    console.log('[LeetGenie] Fetching problem data from LeetCode API:', fetchBody);

    fetch('https://leetcode.com/graphql', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'x-csrftoken': csrftoken,
        'Cookie': `LEETCODE_SESSION=${session}; csrftoken=${csrftoken}`,
        'Referer': window.location.href
      },
      credentials: 'include',
      body: JSON.stringify(fetchBody)
    })
      .then(res => {
        console.log('[LeetGenie] Received response from LeetCode API:', res);
        return res.json();
      })
      .then(data => {
        const html = data?.data?.question?.content || '';
        console.log('[LeetGenie] LeetCode API raw HTML:', html);
        sendResponse({ description: html });
      })
      .catch(err => {
        console.error('[LeetGenie] LeetCode API error:', err);
        sendResponse({ description: '' });
      });
    // Keep the message channel open for async sendResponse
    return true;
  }
});
  