
var darkMode = document.getElementById("darkmode");
   
if (localStorage.getItem("style-theme") == null) {
    localStorage.setItem("style-theme", "sun");
}
darkMode.onclick = function() {
    document.body.classList.toggle("moon");
    if (document.body.classList.contains("moon")) {
        localStorage.setItem("style-theme", "night-theme");
    } else {
        localStorage.setItem("style-theme", "sun");
    }
}
let localData = localStorage.getItem("style-theme");
if (localData == "sun") {
    document.body.classList.remove("moon");
} else if (localData == "night-theme") {
    document.body.classList.add("moon");
}

const dropButton = document.getElementsByClassName('dropdown')[0]
const navigationLinks = document.getElementsByClassName('navigation-links')[0]

dropButton.addEventListener('click', () => {
    navigationLinks.classList.toggle('active')
})

