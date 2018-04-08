<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lxh
  Date: 2017/7/15
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="nav navbar-nav navbar-left">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            Categories
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <s:iterator value="categories">
                <li><a class="categorySelected"><s:property value="name"/></a></li>
            </s:iterator>
        </ul>
    </li>
</ul>