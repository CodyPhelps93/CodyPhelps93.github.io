// js/loadHeader.js
// Determine the correct path to header.html based on the current page's location
const currentPath = window.location.pathname;
const isRoot = currentPath === '/' || currentPath === '/index.html' || !currentPath.includes('pages/');
const headerPath = isRoot ? 'pages/header.html' : 'header.html';

fetch(headerPath)
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to load header.html');
        }
        return response.text();
    })
    .then(data => {
        document.getElementById('header').innerHTML = data;
    })
    .catch(error => {
        console.error('Error loading header:', error);
    });