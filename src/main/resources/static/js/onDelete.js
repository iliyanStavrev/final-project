
Array.from(document.querySelectorAll(".deleteBtn"))
    .forEach(e => e.addEventListener('click', onDelete));

function onDelete(e){
    e.preventDefault();
   let confirmed =  confirm("Are you sure you want to delete this item?");

   if (confirmed){
       console.log(e.currentTarget);
       window.location.href = e.currentTarget.getAttribute("href");

   }
}
