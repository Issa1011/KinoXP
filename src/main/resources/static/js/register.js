document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("registerForm");
    const errorDiv = document.getElementById("errorMessage");
    const dobInput = document.getElementById("dateOfBirth");

    // Sæt maks dato (mindst 13 år)
    const today = new Date();
    const maxDob = new Date(
        today.getFullYear() - 13,
        today.getMonth(),
        today.getDate()
    );
    dobInput.max = maxDob.toISOString().slice(0, 10);

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const name = document.getElementById("name").value;
        const dateOfBirth = dobInput.value;
        const role = document.getElementById("role").value;
        const password = document.getElementById("password").value;

        errorDiv.textContent = "";

        // Valider dato
        const dob = new Date(dateOfBirth);

        if (Number.isNaN(dob.getTime())) {
            errorDiv.textContent = "Ugyldig fødselsdato.";
            return;
        }

        if (dob > maxDob) {
            errorDiv.textContent =
                "Du skal være mindst 13 år for at oprette en bruger.";
            return;
        }

        try {
            const response = await fetch("/kino/users", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    name,
                    dateOfBirth,
                    role,
                    password
                })
            });

            if (response.ok) {
                const userData = await response.json();

                localStorage.setItem(
                    "user",
                    JSON.stringify(userData)
                );

                window.location.href = "index.html";
            } else {
                const message = await response.text();
                errorDiv.textContent =
                    message || "Fejl ved oprettelse.";
            }

        } catch (error) {
            console.error("Registrer fejl:", error);
            errorDiv.textContent =
                "Der opstod en fejl. Prøv igen senere.";
        }
    });

});