<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
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
<body style="background:#FFFFFF">
	<s:include value="/bookstore/jsp/header.jsp"/>
	<div  class="container" >
    <div  id="content">
      <s:if test="#parameters.action!=null">
      	<s:action name="%{#parameters.action}" executeResult="true"/>      	
      </s:if>
      <s:elseif test="#parameters.page!=null">
        <s:include value="/bookstore/jsp/%{#parameters.page}.jsp"/>
      </s:elseif>
      <s:else>
        <s:action name="books" executeResult="true"/>
      </s:else>
    </div>
  </div>
</body>
	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>
	<script src="<%=path%>/bookstore/js/index.js"></script>
</html>