window.onload = seeMore;

function seeMore() {

    let buttons = Array.from(document.getElementsByTagName("button"));

    for (let buttonElement of buttons) {
        buttonElement.addEventListener('click',showMore);
    }

    function showMore(e) {

        let showMore = document.querySelector(".showMore");

        e.target.previousElementSibling.style.display === 'inline-block'
                ? e.target.previousElementSibling.style.display = 'none'
                : e.target.previousElementSibling.style.display = 'inline-block';

        e.target.previousElementSibling.style.display === 'inline-block'
                ? e.target.textContent = 'Show less'
                : e.target.textContent = 'Show more';

    }
}