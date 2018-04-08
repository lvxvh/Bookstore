<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 

<div class="col-md-3 col-md-push-4">
      <form class="form-signin" action="login.action" method="post">
      	<s:actionerror/>
        <h2 class="form-signin-heading">Please sign in</h2>        
        <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <br>
        <input type="submit"  class="btn btn-lg btn-default btn-block" value="Sign in">
      </form>
</div> 
