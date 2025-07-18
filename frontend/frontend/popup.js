// popup.js

document
  .getElementById('generate-main-btn')
  .addEventListener('click', async () => {
    // 1. Find the active tab
    const [tab] = await chrome.tabs.query({
      active: true,
      currentWindow: true
    });
    if (!tab) return;

    // 2. Send scrape request
    chrome.tabs.sendMessage(
      tab.id,
      { action: 'scrape_dsa_problem' },
      (response) => {
        const out = document.getElementById('output');
        if (chrome.runtime.lastError) {
          out.textContent = 'Error: ' + chrome.runtime.lastError.message;
        } else if (!response || !response.description) {
          out.textContent = 'No description found on this page.';
        } else {
          out.textContent = response.description;
        }
      }
    );
  });
