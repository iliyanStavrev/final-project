function dropDownAdmin() {

    Array.from(document.getElementsByClassName("dropdown-content"))
        .forEach(e => e.classList.toggle("show"));
   document.getElementById('admin')
        .addEventListener('click',dropDown);

}

function dropDownTrainings(){
    Array.from(document.getElementsByClassName("dropdown-content-trainings"))
        .forEach(e => e.classList.toggle("show"));
    document.getElementById('trainings')
        .addEventListener('click',dropDown);
}

// Close the dropdown menu if the user clicks outside of it
 function dropDown(event) {

    if (!event.target.matches('.dropbtn')) {
        var dropdowns = event.target.nextElementSibling
        //    document.getElementsByClassName("dropdown-content");
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }

}