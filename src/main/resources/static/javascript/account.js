window.onload = async function () {
    try {
        const res = await fetch("/api/session-check", { credentials: "include" });
        const data = await res.json();

        if (!data.loggedIn) {
            // No session → must login first
            window.location.href = "login.html";
            return;
        }

        // Session exists → show account info
        document.getElementById("display-name").textContent  = data.name;
        document.getElementById("display-email").textContent = data.email;
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