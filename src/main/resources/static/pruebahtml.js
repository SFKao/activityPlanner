document.addEventListener('DOMContentLoaded', () => {
    const colorContainer = document.getElementById('color');
    const changeColorBtn = document.getElementById('boton');

    function changeBackgroundColor() {
        colorContainer.style.backgroundColor = 'red';
    }

    changeColorBtn.addEventListener('click', () => {
        changeBackgroundColor();
    });
});