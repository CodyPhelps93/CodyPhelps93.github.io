let slideIndex = 1;
let autoScroll = true;
let slideTimeout;
showSlides(slideIndex);

function toggleAutoScroll() {
    const scrollOn = document.getElementById("scrollOn");
    autoScroll = scrollOn.checked;

    if (autoScroll) {
        slideTimeout = setTimeout(() => plusSlides(1), 3000);
    } else {
        clearTimeout(slideTimeout);

    }
}
document.getElementById("scrollOn").addEventListener("change", toggleAutoScroll);
document.getElementById("scrollOff").addEventListener("change", toggleAutoScroll);

// Next/previous controls
function plusSlides(n, isManual = false) {
    clearTimeout(slideTimeout);
    if (isManual) {
        autoScroll = false;
        document.getElementById("scrollOff").checked = true;
        document.getElementById("scrollOn").checked = false;
    }
    showSlides(slideIndex += n);
}

// Dot controls
function currentSlide(n) {
    clearTimeout(slideTimeout)
    autoScroll = false;
    document.getElementById("scrollOff").checked = true;
    document.getElementById("scrollOn").checked = false;
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");

    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }

    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";

    // load project details only if autoscroll is off
    if (!autoScroll) {
        clearTimeout(slideTimeout);
        loadProjectDetails();
    } else {
        clearProjectDetails();
    }

    // turn auto scroll on/off
    if (autoScroll) {
        slideTimeout = setTimeout(() => plusSlides(1), 3000);
    } else {
        clearTimeout(slideTimeout);
        console.log("autoscroll set to: " + autoScroll);
    }

}

let projects = [
    {
        title: "Weight tracker Mobile Application",
        description: "A mobile app built using Android Studio and java that allows a user to track" +
            " their weight and calorie intake over time. This app features an prediction algortihm that" +
            " uses linear regression to predict future weight based on past data.",
        link: "https://github.com/CodyPhelps93/CS-360-Enhanced",
        technologies: "Java, Android Studio, XML, Git, sqLite",
    },
    {
        title: "Pet adobtion redesign using Blazor and ASP.NET",
        description: "A web application built using Blazor and ASP.NET that allows users to browse" +
            " and adopt pets from a local animal shelter. The application features a modern and responsive" +
            " design, as well as a user-friendly interface for searching and filtering pets based on various criteria.",
        link: "https://github.com/CodyPhelps93/IT-145-Enhanced",
        technologies: "C#, Blazor, ASP.NET, HTML, CSS, Git, Entity Framework",
    },

    {
        title: "JSON game Engine",
        description: "A simple CLI game engine built using C# and LUA scripting language. The engine allows" +
            " users to create and run text-based RPG games using JSON files to define game elements such as characters," +
            " items, and quests. The engine features a modular design that allows for easy customization and extension." + 
            " This project also allows the user to script new features into the game within the JSON File using NLUA.",
        link: "https://github.com/CodyPhelps93/JSON_RPG_Maker",
        technologies: "C#, JSON, Git, LUA",
    }
]

function loadProjectDetails() {
    const feed = document.getElementById('projectFeed');
    const project = projects[slideIndex - 1];

    // Ensure technologies field exists, use fallback if not
    const technologies = project.technologies || "Not specified";

    // Generate project details HTML
    const projectHTML = `
        <div class="feed">
            <h2>${project.title}</h2>
                <div class="project-card">
                    <p>${project.description}</p>
                    <p><strong>Technologies Used:</strong> ${technologies}</p>
                    <a href="${project.link}" target="_blank" class="btn btn-custom">View Project on GitHub</a>
                </div>
        </div>
    `;
    feed.innerHTML = projectHTML;
}

function clearProjectDetails() {
    const feed = document.getElementById('projectFeed');
    feed.innerHTML = '';
}