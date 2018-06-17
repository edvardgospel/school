function createUser() {
    console.log("create");
    var popup = document.getElementById("myPopup");
        popup.classList.toggle("show");
}

function updateUser(teacher) {
    console.log("update " + teacher);
}

function deleteUser(teacher) {
    console.log("delete " + teacher);
}

function logOut() {
    console.log("logOut");
}

function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}