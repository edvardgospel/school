var createPopup;
var updatePopup;
var deletePopup;

function openCreatePopup() {
    $.ajax({
        url: "/admin/subjects",
        type: "get",
        success: function (subjects) {
            $("#allSubjects").html(subjects.join(',  '));
        }
    });
    createPopup = document.getElementById("createPopup");
    createPopup.classList.toggle("show");
}

function openUpdatePopup(teacher) {
    updatePopup = document.getElementById("updatePopup");
    updatePopup.classList.toggle("show");
}

function openDeletePopup(teacher) {
    $("#deleteLabel").html("Are you sure you want do delete <b>" + teacher + "</b>?");
    deletePopup = document.getElementById("deletePopup");
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