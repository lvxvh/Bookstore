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
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<s:include value="header.jsp"/>
			<s:include value="left.jsp"/>
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Users</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							add user
							<button class="btn btn-default" type="button" id="add">
								<i class="fa fa-plus"></i>
							</button>
						</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
										    <th>ID</th>
											<th>Username</th>
											<th>Password</th>
											<th>Role</th>
											<th>Phone</th>
											<th>Address</th>
											<th></th>
										</tr>
									</thead>
										<s:iterator value="users">
										<tr>
										    <td><s:property value="userId"/></td>
											<td><s:property value="username"/></td>
											<td><s:property value="password"/></td>
											<td><s:property value="role"/></td>
											<td><s:property value="phone"/></td>
											<td><s:property value="address"/></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<s:property value="userId"/>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
														data-id="<s:property value="userId"/>"
														data-username="<s:property value="username"/>"
														data-password="<s:property value="password"/>"
														data-role="<s:property value="role"/>"
														data-phone="<s:property value="phone"/>"
														data-address="<s:property value="address"/>">
														<i class="fa fa-edit"></i>
												</button>
												<button class="btn btn-default info" type="button"
														data-id="<s:property value="userId"/>">
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
					<form role="form">
						<div class="form-group">
							<label>Username</label> <input class="form-control" name="username">
						</div>
						<div class="form-group">
							<label>Password</label> <input class="form-control"
								name="password">
						</div>
						<div class="form-group">
							<label>Role</label> <input class="form-control" name="role">
						</div>
						<div class="form-group">
							<label>Phone</label> <input class="form-control" name="phone">
						</div>
						<div class="form-group">
							<label>Address</label> <input class="form-control" name="address">
						</div>
					</form>
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
					<h4 class="modal-title" id="modalTitle-detail">User detail</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-3">
							<img id="userAvator" class="img-thumbnail" height="200" width="200" src="#"/>
						</div>
						<div class="col-sm-4">
							<p id="userProfile"></p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="image">Change avator</button>
					<button type="button" class="btn btn-default" id="profile">Change profile</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="modal-avator" tabindex="-1" role="dialog"
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
					<button type="button" class="btn btn-primary" id="uploadAvator">Save</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modal-profile" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="description-title">Input the Profile</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Profile</label> <textarea class="form-control" name="profile"></textarea>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="saveProfile">Save</button>
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

	<script src="<%=path%>/bookstore/js/user.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
            $(".info").click(function (e) {
                var dataset = e.currentTarget.dataset;
                var id = dataset.id;
                console.log(id);
                $("#modal-detail").attr("uid",id);
                $.ajax({
                    url: 'getUserDetail',			//修改url, 添加action
                    processData: true,
                    context: this,
                    dataType: "text",
                    data: {
                        userId: id
                    },
                    success: function (data) {
                        console.log(data);
                        var userDetail = JSON.parse(data);

                        $("#userAvator").attr("src","data:image/jpg;base64," + userDetail.avator);
                        $("#userProfile").text(userDetail.profile);
                        $('#modal-detail').modal('show');
                    }
                });
            });
		});
	</script>

</body>

</html>

