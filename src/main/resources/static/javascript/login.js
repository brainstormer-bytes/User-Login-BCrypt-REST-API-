// ── SESSION CHECK ON PAGE LOAD ─────────────────────────────────────────────
// If user already has an active session, skip the login page entirely.
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
            // Active session found → no need to login again
            window.location.href = "index.html";
        }
        // else: no session → stay on login page normally

    } catch (error) {
        // Server not reachable or endpoint error — stay on page, do nothing
        console.error("Session check failed:", error);
    }
}


// ── LOGIN ──────────────────────────────────────────────────────────────────
// Reads email and password fields, sends to /api/login as JSON,
// reads the response, navigates or shows error accordingly.

async function login() {
    const email    = document.getElementById("email2").value.trim();
    const password = document.getElementById("password2").value;
    const message  = document.getElementById("message");

    // Clear any previous message
    message.textContent = "";

    try {
        const response = await fetch("/api/user/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",   // critical: stores the JSESSIONID cookie the server sends back
            body: JSON.stringify({ email: email, password: password })
        });

        const data = await response.json();

        if (data.success) {
            // Login successful → session is now active on the server → go to home page
            window.location.href = "index.html";
        } else {
            // Login failed (wrong credentials) → show error, stay on login page
            message.style.color = "red";
            message.textContent = data.message;
        }

    } catch (error) {
        message.style.color = "red";
        message.textContent = "Something went wrong. Please try again.";
        console.error("Login error:", error);
    }
}
