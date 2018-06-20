var createPopup = document.getElementById("createPopup");
var updatePopup = document.getElementById("updatePopup");
var deletePopup = document.getElementById("deletePopup");
var subjects = getSubjects();
var teachers = getTeachers()


function getSubjects() {
    $.ajax({
        url: "/user/subjects",
        type: "get",
        success: function (subjects) {
            $("#allSubjects").html(subjects.join(',  '));
        }
    });
}

function getTeachers() {
    $("option").each(function() {
        sessionStorage.setItem($(this).val(),$(this).val());
    });
}

function openCreatePopup() {
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

function teacherChanged(newTeacher) {
    $.ajax({
        url: "/user/change",
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
        url: "/user/save",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: newTeacherStringify,
        success: function (subjects) {
            $("#subjectList").replaceWith(subjects);
        }
    });
    sessionStorage.setItem(name,name);
    createPopup.classList.toggle("show");

}

function deleteUser(name) {
    $.ajax({
        url: "/user/delete/"+name,
        type: "delete"
    });
    deletePopup.classList.toggle("show");
    sessionStorage.removeItem(name);
    var teachers = getTeachersFromSessionStorage();
    renderTeachers(teachers)
}

function getTeachersFromSessionStorage() {
    var values = [],
        keys = Object.keys(sessionStorage),
        i = keys.length;
    while ( i-- ) {
        values.push(sessionStorage.getItem(keys[i]) );
    }
    return values;
}

function renderTeachers(teachers) {
    $("#teacher").replaceWith("<select id=\"teacher\" onchange=\"teacherChanged(teacher.value)\"><option th:each=\"user : ${users}\" th:text=\"${user.name}\"></option></select>");
}