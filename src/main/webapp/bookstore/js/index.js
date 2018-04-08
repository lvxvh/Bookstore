/**
 * Created by lxh on 2017/7/15.
 */
$("#changeImg").click(function(){
    $("input[name='url']").val("");
    $('#modal-avator').modal('show');
});

$("#changePwd").click(function(){
    $("input[name='originalPassword']").val("");
    $("input[name='newPassword']").val("");
    $("input[name='confirmPassword']").val("");
    $('#modal-password').modal('show');
});

$("#uploadAvator").click(function(e) {
    var imageUrl = $("input[name='url']").val();
    console.log(imageUrl);
    var id = $("#userId").val();
    $.ajax({
        url : 'uploadAvator',
        processData : true,
        dataType : "text",
        data : {
            userId : id,
            imageUrl : imageUrl
        },
        success : function(data) {
            console.log(data);
            var json = JSON.parse(data);
            if (json.result === "success") {
                bootbox.alert({
                    message: 'Modify Successfully! ',
                    callback: function () {
                        location.reload();
                    }
                });
            } else {
                bootbox.alert({
                    message: 'Url Wrong!',
                    callback: function () {
                        location.reload();
                    }
                });
            }
        }
    });
});

$("#savePwd").click(function(e) {
    var originPwd = $("input[name='originalPassword']").val();
    var newPwd = $("input[name='newPassword']").val();
    var confirmPwd = $("input[name='confirmPassword']").val();
    var id = $("#userId").val();
    if(confirmPwd !== newPwd) {
        bootbox.alert({
            message: 'Two inputs differ!',
            callback: function () {
                location.reload();
            }
        });
        return;
    }
    $.ajax({
        url : 'updatePwd',
        processData : true,
        dataType : "text",
        data : {
            userId : id,
            password : originPwd,
            password2 : newPwd
        },
        success : function(data) {
            console.log(data);
            var json = JSON.parse(data);
            if(json.result === "success"){
                bootbox.alert({
                    message: 'Modify Successfully! ',
                    callback: function () {
                        location.reload();
                    }
                });
            } else {
                bootbox.alert({
                    message: 'Password Wrong!',
                    callback: function () {
                        location.reload();
                    }
                });
            }
        }
    });
});

$(".categorySelected").click(function() {
    var name = $(this).text();
    name = name.replace(/&/g, '%26');
    location.href='getBooksByCategory?name=' + name;
});