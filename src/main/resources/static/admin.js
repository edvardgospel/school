var createPopup = document.getElementById("createPopup");
var updatePopup = document.getElementById("updatePopup");
var deletePopup = document.getElementById("deletePopup");
var subjects = getSubjects();

function getSubjects() {
    $.get("/user/subjects",function (subjects) {
        $("#allSubjects").html(subjects.join('   '));
    })
}

function openCreatePopup() {
    $("#confirmField").prop("checked", false);
    $("#nameField").val("");
    $("#commentField").val("");
    $("#errorField").val("");
    createPopup.classList.toggle("show");
}

function openUpdatePopup(teacher) {
    updatePopup.classList.toggle("show");
}

function openDeletePopup(teacher) {
    $("#deleteLabel").html("Are you sure you want to delete <b>" + teacher + "</b>?");
    deletePopup.classList.toggle("show");
}

function cancelPopup(popup) {
    popup.classList.toggle("show");
}

$("#teacher").change(function(){
    $.post("/user/change",
    {"name": this.value},
    function (subjects) {
        $("#subjectList").replaceWith(subjects);
    });
});

function createUser(name,isAdmin,teaching) {
    $.post("/user/save",
        {"name": name, "isAdmin": isAdmin, "subjects": teaching},
        function (ok) {
            if (ok) {
                $("#teacher").append("<option>" + name + "</option>");
                createPopup.classList.toggle("show");
            } else {
                $("#errorField").append("Invalid user or subject list.");
            }
        });


}

function deleteUser(name) {
    $.ajax({
        url: "/user/delete/"+name,
        type: "delete"
    });
    $("#teacher option").filter(function(){
        return $.trim($(this).text()) ==  name
    }).remove();
    deletePopup.classList.toggle("show");
}