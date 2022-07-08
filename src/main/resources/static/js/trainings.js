window.onload = getTrainingDetails;

async function getTrainingDetails() {

    let id = window.location.pathname.split("/")[2];

    let url = `http://localhost:8080/sports/details/${id}`;
    let response = await fetch(url);
    let trainings = await response.json();

    let main = document.getElementById("main");
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

    h1.textContent = "All Trainings!";
    main.appendChild(h1);

    trainings.forEach(training => {
        let section = document.createElement("section");
        section.setAttribute("id", "detailsPage");
        section.innerHTML = `
     <div class="wrapper">
        <div class="trainingCover">
          <img src="/images/${training.name.split(" ").join("")}.jpg" alt="*">
        </div>
        <div class="trainingInfo">
            <div class="trainingText">
        
                <h1>Training: ${training.name}</h1>
                <h3>Duration: ${training.duration} minutes</h3>
                <h3>Intesity: ${training.intensity}</h3>
                <h3>Sport: ${training.sport}</h3>
                <h3>Coach: ${training.coach}</h3>
                <h3>Hall: ${training.hall}</h3>
                <h3>Started on: ${training.startedOn}</h3>
              <div class="actionBtn"> 
                <a href="/">Reserve</a>
              </div>
            </div>  
        </div>
    </div>
    `
        main.appendChild(section);
    })
}


