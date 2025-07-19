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
        const outBox = document.getElementById('output-box');
        if (chrome.runtime.lastError) {
          outBox.textContent = 'Error: ' + chrome.runtime.lastError.message;
        } else if (!response || !response.description) {
          outBox.textContent = 'No output found.';
        } else {
          outBox.textContent = response.description;
        }
      }
    );
  });

// Copy button logic
const copyBtn = document.getElementById('copy-btn');
const outBox = document.getElementById('output-box');
copyBtn.addEventListener('click', () => {
  if (!outBox.textContent) return;
  navigator.clipboard.writeText(outBox.textContent).then(() => {
    const original = copyBtn.textContent;
    copyBtn.textContent = 'Copied!';
    setTimeout(() => {
      copyBtn.textContent = original;
    }, 1000);
  });
});
