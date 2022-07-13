
window.onload = getSports;

async function getSports(){
    let url = "http://localhost:8080/sports/all";
    let response = await fetch(url);
    let data = await response.json();

    let main = document.getElementById("main");
    let h1 = document.createElement("h1");
    h1.textContent = "All Sports";
    main.appendChild(h1);

    data.forEach(sport => {
        let section = document.createElement("section");
        section.setAttribute("id", "sportPage");
        section.innerHTML = `
            <div class="card-box">
              <img src="/images/${sport.name}.jpg" alt="*">
                  <div>
                        <div class="text-center">
                            <p class="name">${sport.name}</p>
                            <p class="description">${sport.description}</p>
                        </div>
                        <div class="btn-group">
                            <a href= /trainings/details/${sport.id} id="details">Book an training</a>
                        </div>
                    </div>
                </div>`;

        main.appendChild(section);
    })
}


