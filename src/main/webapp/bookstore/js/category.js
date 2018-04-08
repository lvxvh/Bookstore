/**
 * Created by lxh on 2017/7/15.
 */
$("#save").click(function(e) {
    var name = $("input[name='name']").val();

    var dataset = e.currentTarget.dataset;
    jQuery.ajax({
        url : 'addCategory',
        processData : true,
        dataType : "text",
        data : {
            name : name
        },
        success : function(data) {
            var json = JSON.parse(data);
            if(json.result === "success") {
                bootbox.alert({
                    message: 'Add Successfully! ',
                    callback: function () {
                        location.reload();
                    }
                });
            } else {
                bootbox.alert({
                    message: 'Already Existed',
                    callback: function () {
                        location.reload();
                    }
                });
            }
        }
    })

    $('#modal').modal('hide');
});

$(".delete").click(function(e) {
    bootbox.confirm({
        buttons : {
            confirm : {
                label : 'Delete'
            },
            cancel : {
                label : 'Cancel'
            }
        },
        message : 'Sure to delete?',
        callback : function(result) {
            if (result) {
                var dataset = e.currentTarget.dataset;
                var id = dataset.id;
                jQuery.ajax({
                    url : 'deleteCategory',
                    processData : true,
                    dataType : "text",
                    data : {
                        categoryId : id
                    },
                    success : function(data) {
                        console.log(id);
                        bootbox.alert({
                            message : 'Delete Successfully! ',
                            callback : function() {
                                location.reload();
                            }
                        });
                    }
                });

            }
        }
    });
});
$("#add").click(function(e) {
    $('#modalTitle').html("Add");

    $("input[name='name']").val("");

    $('#modal').modal('show');
});
