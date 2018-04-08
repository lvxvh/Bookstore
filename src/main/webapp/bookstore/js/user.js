$(function() {

	$("#save").click(function(e) {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		var role = $("input[name='role']").val();
        var phone = $("input[name='phone']").val();
        var address = $("input[name='address']").val();
		console.log(username, password, role);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateUserPro',
				processData : true,
				dataType : "text",
				data : {
					userId : id,
					username : username,
					password : password,
					role : role,
					phone : phone,
					address : address
				},
				success : function(data) {
					console.log(id);
					bootbox.alert({
						message : 'Modify Successfully! '
							+ 'PS: No change if foreign key does not exist!',
						callback : function() {
							location.reload();
						}
					});
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addUserPro',
				processData : true,
				dataType : "text",
				data : {
					username : username,
					password : password,
					role : role,
					phone : phone,
					address : address
				},
				success : function(data) {
					bootbox.alert({
						message : 'Add Successfully! '
							+ 'PS: No change if foreign key does not exist!',
						callback : function() {
							location.reload();
						}
					});
				}
			})
		}

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
						url : 'deleteUserPro',
						processData : true,
						dataType : "text",
						data : {
							userId : id
						},
						success : function(data) {
							console.log(id);
							bootbox.alert({
								message : 'Delete Successfully! '
									+ 'PS: No change if foreign key does not exist!',
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

    $("#image").click(function() {
        $("input[name='url']").val("");
        $('#modal-avator').modal('show');
    });

    $("#uploadAvator").click(function(e) {
        var imageUrl = $("input[name='url']").val();
        console.log(imageUrl);
        var id = $("#modal-detail").attr("uid");
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
                var result = JSON.parse(data);
                if (result.result === "success") {
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


    $("#profile").click(function() {
        $("textarea[name='profile']").val($("#userProfile").text());
        $('#modal-profile').modal('show');
        console.log($("#modal-detail").attr("uid"));
    });

    $("#saveProfile").click(function(e) {
        var profile = $("textarea[name='profile']").val();
        var id = $("#modal-detail").attr("uid");
        console.log(id);
        $.ajax({
            url : 'changeProfile',
            processData : true,
            dataType : "text",
            data : {
                userId : id,
                profile : profile
            },
            success : function(data) {
                console.log(id);
                bootbox.alert({
                    message : 'Modify Successfully! ',
                    callback : function() {
                        location.reload();
                    }
                });
            }
        });

    });

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("input[name='username']").val("");
		$("input[name='password']").val("");
		$("input[name='role']").val("");
        $("input[name='phone']").val("");
        $("input[name='address']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='username']").val(dataset.username);
		$("input[name='password']").val(dataset.password);
		$("input[name='role']").val(dataset.role);
        $("input[name='phone']").val(dataset.phone);
        $("input[name='address']").val(dataset.address);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

});
