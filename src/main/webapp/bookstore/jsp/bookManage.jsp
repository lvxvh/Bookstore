<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>BookStore</title>

<%
	String path = request.getContextPath();
%>
<link href="<%=path%>/bookstore/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.responsive.css"
	rel="stylesheet">
<link href="<%=path%>/bookstore/css/bookstore.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		<s:include value="header.jsp"/>
		<s:include value="left.jsp"/>
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Books</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">add book
							<button class="btn btn-default" type="button" id="add">
								<i class="fa fa-plus"></i>
							</button>
						</div>
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
										    <th>ID</th>
											<th>Title</th>
											<th>Author</th>
											<th>Price</th>
											<th>Publisher</th>
											<th>Date</th>
											<th>isbn</th>
											<th>category</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="books">
										<tr>
										    <td><s:property value="bookId"/></td>
											<td><s:property value="title"/></td>
											<td><s:property value="author"/></td>
											<td><s:property value="price"/></td>
											<td><s:property value="publisher"/></td>
											<td><s:property value="date"/></td>
											<td><s:property value="isbn"/></td>
											<td><s:property value="category"/></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<s:property value="bookId"/>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<s:property value="bookId"/>"
													data-title="<s:property value="title"/>"
													data-author="<s:property value="author"/>"
													data-price="<s:property value="price"/>"
													data-publisher="<s:property value="publisher"/>"
													data-date="<s:property value="date"/>"
													data-isbn="<s:property value="isbn"/>"
														data-isbn="<s:property value="category"/>">
													<i class="fa fa-edit"></i>
												</button>
												<button class="btn btn-default info" type="button"
														data-id="<s:property value="bookId"/>">
													<i class="fa fa-info-circle"></i>
												</button>
											</td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Title</label> <input class="form-control" name="title">
								</div>
								<div class="form-group">
									<label>Author</label> <input class="form-control" name="author">
								</div>
								<div class="form-group">
									<label>Price</label> <input class="form-control" type="number"
										step="1" min="0" name="price" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
								</div>
								<div class="form-group">
									<label>Publisher</label> <input class="form-control"
										name="publisher">
								</div>
								<div class="form-group">
									<label>Isbn</label> <input class="form-control" name="isbn">
								</div>
								<div class="form-group">
									<label>Category</label>
									<select class="form-control" id="category">
										<s:iterator value="categories">
											<option value="<s:property value="name"/>"><s:property value="name"/></option>
										</s:iterator>
									</select>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">Save</button>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="modal-detail" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="detail-title">Book detail</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-3">
							<img id="bookImage" class="img-thumbnail" height="200" width="200" src="#"/>
							</div>
						<div class="col-sm-9">
							<p id="descriptionContent">this is what</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="image">Change cover</button>
					<button type="button" class="btn btn-default" id="description">Change description</button>
					<button type="button" class="btn btn-default" id="record">View sale record</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-image" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="imageUrl-title">Input the url of image</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>URL</label> <input class="form-control" name="url">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="uploadImage">Save</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-description" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="description-title">Input the Description</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Deccription</label> <textarea class="form-control" name="description"></textarea>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="saveDescription">Save</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-record" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle-detail">Sale record</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<table class="table table-striped table-bordered table-hover">
										<thead>
										<tr>
											<th>RecordId</th>
											<th>UserId</th>
											<th>Amount</th>
											<th>Date</th>
										</tr>
										</thead>
										<tbody id="record-body">
										</tbody>
									</table>
								</div>
								<label>TotalAmount :</label> <em id="totalAmount"></em>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/book.js"></script>

	<script>
        $(document).ready(function() {
            var table = $('#dataTables').DataTable({
                responsive: true
            });
            var bookRecords;
            $(".info").click(function (e) {
                var dataset = e.currentTarget.dataset;
                var id = dataset.id;
                $("#modal-detail").attr("bid",id);
                $.ajax({
                    url: 'getBookDetail',
                    processData: true,
                    context: this,
                    dataType: "text",
                    data: {
                        bookId: id
                    },
                    success: function (data) {
                        var bookDetail = JSON.parse(data);
                        bookRecords = bookDetail.records;
                        $("#bookImage").attr("src","data:image/jpg;base64," + bookDetail.image);
                        $("#descriptionContent").text(bookDetail.description);
                        $('#modal-detail').modal('show');
                    }
                });
            });

            $("#record").click(function (e) {
					var totalAmount = 0;
					document.getElementById('record-body').innerHTML="";
					$.each(bookRecords, function (n, str) {
						var record = JSON.parse(str);
						var recordId = record.recordId;
						var userId = record.userId;
						var amount = record.amount;
						var date = record.date;
						totalAmount += amount;
						document.getElementById('record-body').innerHTML+='' +
							'<tr>' +
							'<td>' + recordId + '</td>'+
							'<td>'+ userId +'</td>' +
							'<td>'+ amount +'</td>' +
							'<td>' + date + '</td>' +
							'</tr>';
					});
					$('#totalAmount').text(totalAmount);
					$('#modal-record').modal('show');

			});



        });

	</script>

</body>

</html>

