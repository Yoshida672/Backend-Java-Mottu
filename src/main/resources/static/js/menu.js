document.addEventListener("DOMContentLoaded", () => {
    const toggleButton = document.getElementById("toggleMenu");
    const menuLateral = document.getElementById("menuLateral");

    toggleButton.addEventListener("click", () => {
        menuLateral.classList.toggle("aberto");
    });
});
