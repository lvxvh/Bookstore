<%--
  Created by IntelliJ IDEA.
  User: lxh
  Date: 2017/7/15
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
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
                <h1 class="page-header">Categories</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        add category
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
                                    <th>Category</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="categories">
                                    <tr>
                                        <td><s:property value="categoryId"/></td>
                                        <td><s:property value="name"/></td>
                                        <td>
                                            <button class="btn btn-default delete" type="button"
                                                    data-id="<s:property value="categoryId"/>">
                                                <i class="fa fa-trash"></i>
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
                        <label>Category</label> <input class="form-control" name="name">
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

<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
<script src="<%=path%>/bookstore/js/bookstore.js"></script>
<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

<script src="<%=path%>/bookstore/js/category.js"></script>

<script>
    $(document).ready(function() {
        $('#dataTables').DataTable({
            responsive : true
        });
    });
</script>

</body>

</html>
