


	$("#save").click(function(e) {
		var userId = $("#userId").val();
		console.log(userId);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateOrderPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					userId : userId
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
				url : 'addOrderPro',
				processData : true,
				dataType : "text",
				data : {
					userId : userId
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
						url : 'deleteOrderPro',
						processData : true,
						dataType : "text",
						data : {
							id : id
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

    $("#save-item").click(function(e) {
    	var orderId = $("#modal-orderDetail").attr("oid");
        var bookId = $("#bookId").val();
        var amount = $("input[name='amount']").val();


		var edit = $("#save-item").attr("edit");

        if (edit == "1") { // Edit
			var recordId = $("#save-item").attr("recordId");
            $.ajax({
                url : 'updateOrderitem',
                processData : true,
                dataType : "text",
                data : {
                	recordId : recordId,
                    orderId : orderId,
                    bookId : bookId,
					amount : amount
                },
                success : function(data) {
                    bootbox.alert({
                        message : 'Modify Successfully! ',
                        callback : function() {
                            location.reload();
                        }
                    });
                }
            });
        } else { // Add
            $.ajax({
                url : 'addOrderitem',
                processData : true,
                dataType : "text",
                data : {
                	orderId : orderId,
                    bookId : bookId,
					amount : amount
                },
                success : function(data) {
                    bootbox.alert({
                        message : 'Add Successfully! ',
                        callback : function() {
                            location.reload();
                        }
                    });
                }
            })
        }

        $('#modal').modal('hide');
    });

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("#userId").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("#userId").val(dataset.userid);
		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

    $("#add-item").click(function(e) {
        $('#modalTitle').html("Add");

        $("#bookId").val("");
        $("#amount").val("");

        $("#save").attr("edit", "0");
        $('#modal-edit').modal('show');
    });



