

	$(function() {
		$("#save").click(function(e) {
			var title = $("input[name='title']").val();
			var author = $("input[name='author']").val();
			var price = $("input[name='price']").val();
			var publisher = $("input[name='publisher']").val();
			var isbn = $("input[name='isbn']").val();
			var category = $("#category").val();
			console.log(title, author, price, publisher, isbn);

			var dataset = e.currentTarget.dataset;
			var id = dataset.id;

			if (id != "") { // Edit
				jQuery.ajax({
					url : 'updateBookPro',
					processData : true,
					dataType : "text",
					data : {
						bookId : id,
						title : title,
						author : author,
						price : price,
						publisher : publisher,
						isbn : isbn,
						category : category
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
			} else { // Add
				jQuery.ajax({
					url : 'addBookPro',
					processData : true,
					dataType : "text",
					data : {
						title : title,
						author : author,
						price : price,
						publisher : publisher,
						isbn : isbn,
						category : category
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
							url : 'deleteBookPro',
							processData : true,
							dataType : "text",
							data : {
								bookId : id
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

			$("input[name='title']").val("");
			$("input[name='author']").val("");
			$("input[name='price']").val("");
			$("input[name='publisher']").val("");
			$("input[name='isbn']").val("");
            $("#category").val("");

			$("#save").attr("data-id", "");
			$('#modal').modal('show');
		});

        $("#image").click(function() {
            $("input[name='url']").val("");
            $('#modal-image').modal('show');
        });

        $("#uploadImage").click(function(e) {
            var imageUrl = $("input[name='url']").val();
            console.log(imageUrl);
            var id = $("#modal-detail").attr("bid");
			$.ajax({
				url : 'uploadImage',
				processData : true,
				dataType : "text",
				data : {
					bookId : id,
					imageUrl : imageUrl
				},
				success : function(data) {
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


        $("#description").click(function() {
            $("textarea[name='description']").val($("#descriptionContent").text());
            $('#modal-description').modal('show');
            console.log($("#modal-detail").attr("bid"));
        });

        $("#saveDescription").click(function(e) {
            var description = $("textarea[name='description']").val();
            console.log(description);
            var id = $("#modal-detail").attr("bid");
            console.log(id);
            $.ajax({
                url : 'changeDescription',
                processData : true,
                dataType : "text",
                data : {
                    bookId : id,
                    description : description
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

		$(".edit").click(function(e) {
			$('#modalTitle').html("Edit");
			var dataset = e.currentTarget.dataset;
			var id = dataset.id;
			console.log(id);

			$("input[name='title']").val(dataset.title);
			$("input[name='author']").val(dataset.author);
			$("input[name='price']").val(dataset.price);
			$("input[name='publisher']").val(dataset.publisher);
			$("input[name='isbn']").val(dataset.isbn);

			$("#save").attr("data-id", dataset.id);
			$('#modal').modal('show');
		});



	});

