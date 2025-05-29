function togglePassword(id, btn) {
    const input = document.getElementById(id);
    const icon = btn.querySelector('i');
    if (input.type === "password") {
        input.type = "text";
        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");
    } else {
        input.type = "password";
        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const warning = document.getElementById("passwordMismatch");

    if (form) {
        form.addEventListener("submit", function (e) {
            const pass1 = document.getElementById("password").value;
            const pass2 = document.getElementById("confirmPassword").value;

            if (pass1 !== pass2) {
                e.preventDefault();
                warning.classList.remove("d-none");
            } else {
                warning.classList.add("d-none");
            }
        });
    }
});
