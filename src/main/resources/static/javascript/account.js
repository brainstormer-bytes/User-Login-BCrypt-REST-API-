window.onload = async function () {
    try {
        const res = await fetch("/api/session-check", { credentials: "include" });
        const data = await res.json();

        if (!data.loggedIn) {
            window.location.href = "login.html";
            return;
        }

        document.getElementById("display-name").textContent = data.name;
        document.getElementById("display-email").textContent = data.email;
        document.getElementById("account-btn").innerHTML = `
            <svg width="18" height="18" fill="none" stroke="currentColor" stroke-width="1.5" viewBox="0 0 24 24" aria-hidden="true">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
            </svg>
            <span>${data.name}</span>
        `;
        document.getElementById("account-content").style.display = "block";
    } catch (e) {
        console.error("Session check failed:", e);
        window.location.href = "login.html";
    }
};

async function logout() {
    await fetch("/api/logout", { method: "POST", credentials: "include" });
    window.location.href = "login.html";
}
