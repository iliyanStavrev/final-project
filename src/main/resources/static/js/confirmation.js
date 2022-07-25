
if (window.location.href.split('?')[1] === 'isAlreadyTaken=true'){
    alert('You already have this training');
    window.location.href =  window.location.href.split('?')[0]
}

let elements = Array.from(document.querySelectorAll('.add'));
elements.forEach(e => e.addEventListener('click',confirmTraining));
function confirmTraining(e) {
    e.preventDefault();
    let confirmed = confirm("Are you sure you want to add this training?");
    if (confirmed) {
        // window.location.href = "http://localhost:8080/trainings/user";

        window.location.href = e.target.getAttribute("href");
    }
}