window.onload = async function () {
    try {
        const res = await fetch("/api/session-check", { credentials: "include" });
        const data = await res.json();
        const accountBtn = document.getElementById("account-btn");

        if (data.loggedIn) {
            // Show user's name on the account button
            accountBtn.innerHTML = `
                    <svg width="18" height="18" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24">
                        <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
                    </svg>
                    <span style="font-size:12px; letter-spacing:0.05em;">
                    ${data.name}`;
            accountBtn.onclick = () => window.location.href = "account.html";
        } else {
            // Not logged in → clicking account goes to login
            window.location.href = "login.html";
        }
    } catch (e) {
        console.error("Session check failed:", e);
    }
};