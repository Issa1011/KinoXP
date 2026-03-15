document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("loginForm");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const name = document.getElementById("name").value;
        const password = document.getElementById("password").value;
        const errorDiv = document.getElementById("errorMessage");

        try {
            const response = await fetch('/kino/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, password })
            });

            if (response.ok) {
                const userData = await response.json();

                localStorage.setItem('user', JSON.stringify(userData));

                window.location.href = 'index.html';
            } else {
                errorDiv.textContent = 'Forkert navn eller adgangskode';
            }

        } catch (error) {
            errorDiv.textContent = 'Der opstod en fejl. Prøv igen senere.';
            console.error("Login fejl:", error);
        }
    });

});