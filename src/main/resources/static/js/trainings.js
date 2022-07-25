
window.onload = getTrainingDetails;

async function getTrainingDetails() {

    let id = window.location.pathname.split("/")[3];

    let url = `http://localhost:8080/sports/api/details/${id}`;
    let response = await fetch(url);
    let trainings = await response.json();

    let main = document.querySelector(".main");
    let h1 = document.createElement("h1");
    h1.setAttribute("id", "title");

    if (trainings.length === 0) {
        let div = document.createElement('div');
        div.setAttribute('id', 'box-title')
        h1.textContent = "We do not have Trainings for this Sport yet!";
        div.appendChild(h1);
        main.appendChild(div);
        return;
    }

    h1.textContent = "All Trainings for " + trainings[0].sportName + "!";
    let img = document.createElement('img');
    img.setAttribute('class', 'img-sport');
    img.src = '/images/' + trainings[0].sportName + '-vector.jpg'
    main.appendChild(img);
    main.appendChild(h1);

    trainings.forEach(training => {
        let section = document.createElement("section");
        section.setAttribute("id", "detailsPage");
        section.innerHTML = `
     <div class="wrapper">
        <div class="trainingCover">
          <img src="/images/${training.name.split(" ").join("")}.jpg" alt="No image">
        </div>
        <div class="trainingInfo">
            <div class="trainingText">
        
                <h1>Training: ${training.name}</h1>
                <h3>Duration: ${training.duration} minutes</h3>
                <h3>Intesity: ${training.intensity}</h3>
                <h3>Sport: ${training.sportName}</h3>
                <h3>Coach: ${training.coachFullName}</h3>
                <h3>Hall: ${training.hallName}</h3>
                <h3>Started on: ${training.startedOn}</h3>
              <div class="actionBtn buttons"> 
                <a href="/trainings/reserve/${training.id}">Reserve</a>
                <a href="/trainings/details/${training.id}">Details</a>
              </div>
            </div>  
        </div>
    </div>
    `
        main.appendChild(section);

        if (main.querySelector('#hasRole') !== null){
            let a = document.createElement('a');
            a.textContent = 'Delete';
            a.setAttribute("class", "deleteBtn")
            a.setAttribute('href', "/trainings/delete/" + training.id);
            let divBtn = document.querySelector(".buttons");
            divBtn.appendChild(a);
            divBtn.classList.remove("buttons");
        }
        function sportsName(){
            return training.sport;
        }
    })

    Array.from(document.querySelectorAll(".deleteBtn"))
        .forEach(e => e.addEventListener('click', onDelete));

    function onDelete(e){

        e.preventDefault();
        let confirmed =  confirm("Are you sure you want to delete this item?");

        if (confirmed){
            window.location.href = e.target.getAttribute("href");
        }

    }
}


