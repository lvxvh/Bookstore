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
					<h1 class="page-header">Orders</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							add order
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
											<th>Userid</th>
											<th>Date</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="orders">
										<tr>
											<td><s:property value="orderId"/></td>
											<td><s:property value="userId"/></td>
											<td><s:property value="date"/></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<s:property value="orderId"/>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<s:property value="orderId"/>"
													data-userid="<s:property value="userId"/>"
													data-date="<s:property value="date"/>">
													<i class="fa fa-edit"></i>
												</button>
												<button class="btn btn-default info" type="button"
														data-id="<s:property value="orderId"/>">
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
									<label>Userid</label>
									<select class="form-control" id="userId">
										<s:iterator value="users">
										<option value="<s:property value="userId"/>"><s:property value="userId"/></option>
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

	<div class="modal fade" id="modal-orderDetail" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle-detail">Order detail</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									add orderitem
									<button class="btn btn-default" type="button" id="add-item">
										<i class="fa fa-plus"></i>
									</button>
								</div>
								<div class="panel-body">
									<table class="table table-striped table-bordered table-hover">
										<thead>
										<tr>
											<th>recordId</th>
											<th>bookId</th>
											<th>title</th>
											<th>Amount</th>
											<th>Unit Price</th>
											<th></th>
										</tr>
										</thead>
										<tbody id="detail-body">
										</tbody>
									</table>
								</div>
								<label>TotalPrice :</label> <em id="totalPrice"></em>
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

	<div class="modal fade" id="modal-edit" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle-edit">Order item</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>BookId</label>
									<select class="form-control" id="bookId">
										<s:iterator value="books">
										<option value="<s:property value="bookId"/>"><s:property value="bookId"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="form-group">
									<label>Amount</label> <input class="form-control" name="amount" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="save-item">Save</button>
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

	<script src="<%=path%>/bookstore/js/order.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});


            $(".info").click(function (e) {
                var dataset = e.currentTarget.dataset;
                var id = dataset.id;
                console.log(id);
                $("#modal-orderDetail").attr("oid",id);
                $.ajax({
                    url: 'getOrderDetail',
                    processData: true,
                    context: this,
                    dataType: "text",
                    data: {
                        orderId: id
                    },
                    success: function (data) {
                        console.log(data);
                        var totalPrice = 0;
                        var orderDetail = JSON.parse(data);
						var orderitems = orderDetail.orderitems;
                        document.getElementById('detail-body').innerHTML="";
                        $.each(orderitems, function (n, str) {
                            var item = JSON.parse(str);
                            var recordId = item.recordId;
                            var bookId = item.bookId;
                            var title = item.title;
                            var amount = item.amount;
                            var unitPrice = item.unitPrice;
                            totalPrice += amount*unitPrice;
                            document.getElementById('detail-body').innerHTML+='' +
								'<tr>' +
                                '<td>'+ recordId +'</td>' +
								'<td>'+ bookId +'</td>' +
                                '<td>'+ title +'</td>' +
								'<td>'+ amount +'</td>' +
								'<td>' + unitPrice + '</td>' +
								'<td><button class="btn btn-default showEdit" type="button">' +
                                '<i class="fa fa-edit"></i>' +
                                '</button>' +
                                '<button class="btn btn-default del" type="button">' +
                                '<i class="fa fa-trash"></i>' +
                                '</button>' +
                                '</td>' +
								'</tr>';
                        });
                        var priceStr = "$" + parseInt(totalPrice/100) + ".";
                        if(totalPrice%100 < 10) priceStr += "0";
                        if(totalPrice%100 == 0) priceStr += "0";
                        if(totalPrice%100 != 0) priceStr += totalPrice%100;
						$('#totalPrice').text(priceStr);
                        $('#modal-orderDetail').modal('show');
                    }
                });

                $(document.body).on('click','.showEdit',function(){
                    $('#modalTitle-edit').html("Edit");
                    var recordId = $(this).parent().prev().prev().prev().prev().prev().text();
					var bookId = $(this).parent().prev().prev().prev().prev().text();
					var amount = $(this).parent().prev().prev().text();
                    $("#bookId").val(bookId);
                    $("input[name='amount']").val(amount);

                    $("#save-item").attr("edit", "1");
                    $("#save-item").attr("recordId", recordId);
                    $('#modal-edit').modal('show');
				});

                $(document.body).on('click','.del',function(){
                    var orderId = $("#modal-orderDetail").attr("oid");
                    var recordId = $(this).parent().prev().prev().prev().prev().prev().text();
                    var bookId = $(this).parent().prev().prev().prev().prev().text();
                    var amount = $(this).parent().prev().prev().text();
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
                                $.ajax({
                                    url : 'deleteOrderitem',
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
            });
		});
	</script>

</body>

</html>

