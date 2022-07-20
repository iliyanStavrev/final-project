
Array.from(document.querySelectorAll(".deleteBtn"))
    .forEach(e => e.addEventListener('click', onDelete));

function onDelete(e){

    e.preventDefault();
   let confirmed =  confirm("Are you sure you want to delete this item?");

   if (confirmed){
       // window.location.href = "http://localhost:8080/trainings/user";
       window.location.href = e.target.getAttribute("href");
   }
}
