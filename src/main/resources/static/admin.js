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


function saveTimetable() {
    var timetableRequest = timetableRequestBuilder();
    $.ajax({
        url: "/user/timetable",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: timetableRequest,
        success: function (ok) {
                             if (ok) {
                                 $("#saveFeedback").html("<b>Timetable</b> saved.");
                             } else {
                                 $("#saveFeedback").html("Invalid subjects, please double-check.");
                             }
                             setTimeout(function(){ $("#saveFeedback").html("&nbsp;"); },3000)
                         }
    });
}

function timetableRequestBuilder() {

    var json = {};

    json.name = $("#teacher").val();

    json.monday = [];
    json.tuesday = [];
    json.wednesday = [];
    json.thursday = [];
    json.friday = [];

    json.monday[0] = $("#monday8").val();
    json.monday[1] = $("#monday9").val();
    json.monday[2] = $("#monday10").val();
    json.monday[3] = $("#monday11").val();
    json.monday[4] = $("#monday12").val();
    json.monday[5] = $("#monday13").val();

    json.tuesday[0] = $("#tuesday8").val();
    json.tuesday[1] = $("#tuesday9").val();
    json.tuesday[2] = $("#tuesday10").val();
    json.tuesday[3] = $("#tuesday11").val();
    json.tuesday[4] = $("#tuesday12").val();
    json.tuesday[5] = $("#tuesday13").val();

    json.wednesday[0] = $("#wednesday8").val();
    json.wednesday[1] = $("#wednesday9").val();
    json.wednesday[2] = $("#wednesday10").val();
    json.wednesday[3] = $("#wednesday11").val();
    json.wednesday[4] = $("#wednesday12").val();
    json.wednesday[5] = $("#wednesday13").val();

    json.thursday[0] = $("#thursday8").val();
    json.thursday[1] = $("#thursday9").val();
    json.thursday[2] = $("#thursday10").val();
    json.thursday[3] = $("#thursday11").val();
    json.thursday[4] = $("#thursday12").val();
    json.thursday[5] = $("#thursday13").val();

    json.friday[0] = $("#friday8").val();
    json.friday[1] = $("#friday9").val();
    json.friday[2] = $("#friday10").val();
    json.friday[3] = $("#friday11").val();
    json.friday[4] = $("#friday12").val();
    json.friday[5] = $("#friday13").val();

    return JSON.stringify(json);
}