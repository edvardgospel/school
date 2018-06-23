var createPopup = document.getElementById("createPopup");
var updatePopup = document.getElementById("updatePopup");
var deletePopup = document.getElementById("deletePopup");
var currentSubjects;
getSubjects();
sessionStorage.setItem("userName",$("#userName").val());

function getSubjects() {
    $.get("/user/subjects",function (subjects) {
        $(".allSubjects").html(subjects.join("   "));
    })
}

function openCreatePopup() {
    $("#createConfirmField").prop("checked", false);
    $("#createNameField").val("");
    $("#createCommentField").val("");
    $("#createErrorField").val("");
    createPopup.classList.toggle("show");
}

function openUpdatePopup(teacher) {
    $("#updateConfirmField").prop("checked", false);
    $("#updateCommentField").val("");
    $("#updateErrorField").val("");
    $("#updateLabel").html("Update <b>" + teacher + "</b>");
    $(".currentSubjects").html(currentSubjects);
    updatePopup.classList.toggle("show");
}

function openDeletePopup(teacher) {
    console.log(teacher);
    var val = document.getElementById("#userName");//TODO
    console.log(val);
    if (sessionStorage.getItem("userName") == teacher) {
        $("#deleteLabel").html("Can't delete <b>" + teacher + "</b>.");
        $("#deletebutton").prop("disabled",true);
    } else {
        $("#deleteLabel").html("Are you sure you want to delete <b>" + teacher + "</b>?");
        deletePopup.classList.toggle("show");
    }
}

function cancelPopup(popup) {
    popup.classList.toggle("show");
}

function teacherChanged(teacher) {
    $.post("/user/change",
    {"name": teacher},
    function (subjects) {
        $("#subjectList").replaceWith(subjects);
        currentSubjects = $(".column").text();
        currentSubjects = currentSubjects.replace(/([\d])/g,"$1   ");
    });
}

function createUser(name,isAdmin,teaching) {
    $.post("/user/create",
    {"name": name, "isAdmin": isAdmin, "subjects": teaching},
    function (ok) {
        if (ok) {
            $("#teacher").append("<option>" + name + "</option>");
            createPopup.classList.toggle("show");
        } else {
            $("#createErrorField").append("Invalid user or subject list.");
        }
    });
}

function updateUser(name,isAdmin,teaching) {
    $.post("/user/update",
    {"name": name, "isAdmin": isAdmin, "subjects": teaching},
    function (ok) {
        if (ok) {
            teacherChanged(name);
            updatePopup.classList.toggle("show");
        } else {
            $("#updateErrorField").append("Invalid subject list.");
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