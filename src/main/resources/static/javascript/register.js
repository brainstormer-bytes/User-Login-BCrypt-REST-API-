// ── SESSION CHECK ON PAGE LOAD ─────────────────────────────────────────────
// If user already has an active session, no need to register again.
// Redirect them straight to the home page.

window.onload = function () {
    checkSession();
};

async function checkSession() {
    try {
        const response = await fetch("/api/session-check", {
            credentials: "include"   // sends JSESSIONID cookie if it exists
        });

        const data = await response.json();

        if (data.loggedIn) {
            // Active session found — user is already logged in, skip register page
            window.location.href = "index.html";
        }
        // else: no session → stay on register page normally

    } catch (error) {
        // Server not reachable or endpoint error — stay on page, do nothing
        console.error("Session check failed:", error);
    }
}


// ── REGISTER ───────────────────────────────────────────────────────────────
// Reads the three input fields, sends to /api/user/register as JSON,
// reads the response, navigates or shows error accordingly.

async function register() {
    const name     = document.getElementById("userName").value.trim();
    const email    = document.getElementById("email1").value.trim();
    const password = document.getElementById("password1").value;
    const message  = document.getElementById("message");

    // Clear any previous message
    message.textContent = "";

    try {
        const response = await fetch("/api/user/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify({ name: name, email: email, password: password })
        });

        const data = await response.json();

        if (data.success) {
            // Registration successful → go to login page
            window.location.href = "login.html";
        } else {
            // Registration failed (e.g. email already exists) → show error on same page
            message.style.color = "red";
            message.textContent = data.message;
        }

    } catch (error) {
        message.style.color = "red";
        message.textContent = "Something went wrong. Please try again.";
        console.error("Register error:", error);
    }
}
