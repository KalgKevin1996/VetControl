document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("formUsuario");
  const passwordInput = document.getElementById("password");
  const confirmPasswordInput = document.getElementById("confirmPassword");
  const errorDiv = document.getElementById("errorContraseña");

  form.addEventListener("submit", function (e) {
    if (passwordInput.value !== confirmPasswordInput.value) {
      e.preventDefault();
      errorDiv.classList.remove("d-none");
    } else {
      errorDiv.classList.add("d-none");
    }
  });
});

// Función para mostrar/ocultar contraseña
function togglePassword(inputId) {
  const input = document.getElementById(inputId);
  const icon = input.nextElementSibling.querySelector("i");

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
