<%--
  Created by IntelliJ IDEA.
  User: lxh
  Date: 2017/7/14
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>Personal Details</h2>
<hr>
<div class="row">
    <div class="col-sm-2 col-sm-offset-2">
        <div class="row">
        <img class="img-thumbnail" height="150" width="150" src="data:image/jpg;base64,<s:property value="imageUrl"/>"/>
        </div>
        <div class="row" style="margin-top: 20px;">
        <button class="btn btn-default" id="changeImg">Change Avatar</button>
        </div>
    </div>
    <div class="col-sm-7">
        <form class="form-horizontal" role="form" action="modifyUserInfo" method="post">
            <div class="form-group">
                <label  class="col-sm-2 control-label">User ID</label>
                <div class="col-sm-6">
                    <input type="text" readonly="readonly"  name="userId" id="userId" class="form-control" value="<s:property value="userId"/>">
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">Username</label>
                <div class="col-sm-6">
                    <input type="text" name="username" class="form-control" value="<s:property value="username"/>" required autofocus>
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">Phone</label>
                <div class="col-sm-6">
                    <input type="text" name="phone" class="form-control" value="<s:property value="phone"/>" required >
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">Address</label>
                <div class="col-sm-6">
                    <input type="text" name="address" class="form-control" value="<s:property value="address"/>" required>
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">Profile</label>
                <div class="col-sm-6">
                    <textarea name="profile" class="form-control" required ><s:property value="profile"/></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-default">Modify</button>
                    <button type="button" class="btn btn-default" id="changePwd">Change password</button>
                </div>
            </div>
        </form>

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

<div class="modal fade" id="modal-password" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="password-title">Change password</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <form role="form">
                            <div class="form-group">
                                <label>Original password</label> <input type="password" class="form-control" name="originalPassword">
                            </div>
                            <div class="form-group">
                                <label>New password</label> <input type="password" class="form-control" name="newPassword">
                            </div>
                            <div class="form-group">
                                <label>Confirm password</label> <input type="password" class="form-control" name="confirmPassword">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="savePwd">Save</button>
            </div>
        </div>
    </div>
</div>

