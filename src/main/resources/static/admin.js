var createPopup = document.getElementById("createPopup");
var updatePopup = document.getElementById("updatePopup");
var deletePopup = document.getElementById("deletePopup");

function openCreatePopup() {
    $.ajax({
        url: "/admin/subjects",
        type: "get",
        success: function (subjects) {
            $("#allSubjects").html(subjects.join(',  '));
        }
    });
    createPopup.classList.toggle("show");
}

function openUpdatePopup(teacher) {
    updatePopup.classList.toggle("show");
}

function openDeletePopup(teacher) {
    $("#deleteLabel").html("Are you sure you want do delete <b>" + teacher + "</b>?");
    deletePopup.classList.toggle("show");
}

function cancelPopup(popup) {
    popup.classList.toggle("show");
}

function teacherChanged(newTeacher) {
    $.ajax({
        url: "/admin",
        type: "post",
        data: {"name": newTeacher},
        success: function (subjects) {
            $("#subjectList").replaceWith(subjects);
        }
    });
}

function saveNewUser(name,isAdmin,teaching) {
    var teachingList = teaching.replace(/\s/g,'').split(",");
    var newTeacher = { "name": name,
                       "isAdmin": isAdmin,
                       "subjects": teachingList }
    var newTeacherStringify = JSON.stringify(newTeacher);
    $.ajax({
        url: "/admin/save",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: newTeacherStringify,
        success: function (subjects) {
            $("#subjectList").replaceWith(subjects);
        }
    });
    var teacherSelect = document.getElementById("teacher");
    var option = document.createElement("option");
    option.text = name;
    teacherSelect.add(option);
    createPopup.classList.toggle("show");

}

function deleteUser(name) {
    console.log(name + " TYPE: " + typeof name)
    $.ajax({
        url: "/admin/delete",
        type: "delete",
        data: {"name": name}
    });
}