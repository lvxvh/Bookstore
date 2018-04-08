<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 

<div class="col-md-5 col-md-push-3">
    <form class="form-horizontal" role="form" action="signup" method="post">
        <s:actionerror/>
        <h2 class="form-signin-heading">Please sign up</h2>
        <div class="form-group">
            <label  class="col-sm-2 control-label">Username</label>
            <div class="col-sm-10">
                <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" placeholder="Password" required>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">Confirm Password</label>
            <div class="col-sm-10">
                <input type="password" name="password2" class="form-control" placeholder="Confirm Password" required>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">Phone</label>
            <div class="col-sm-10">
                <input type="text" name="phone" class="form-control" placeholder="Phone" required >
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">Address</label>
            <div class="col-sm-10">
                <input type="text" name="address" class="form-control" placeholder="Address" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Sign up</button>
            </div>
        </div>
    </form>
</div> 

