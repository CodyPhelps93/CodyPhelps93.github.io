document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('contactForm');
    if (!form) {
        console.warn('contactEmail.js: #contactForm not found');
        return;
    }

    const nameInput = document.getElementById('nameInput');
    const emailInput = document.getElementById('emailInput');
    const messageInput = document.getElementById('messageInput');
    const recipient = 'cody.phelps731@gmail.com';

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        // Basic validation
        if (!form.checkValidity()) {
            form.classList.add('was-validated');
            return;
        }

        const name = (nameInput && nameInput.value.trim()) || '';
        const email = (emailInput && emailInput.value.trim()) || '';
        const message = (messageInput && messageInput.value.trim()) || '';

        const subject = `Portfolio Contact: ${name || 'No Name'}`;
        const body = [
            `Name: ${name}`,
            `Email: ${email}`,
            '',
            'Message:',
            message
        ].join('\r\n');

        const mailto = `mailto:${recipient}?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;

        // open user's mail client
        window.location.href = mailto;
    });
});