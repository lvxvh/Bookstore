<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.jsp" >BookStore</a>
		</div>
		<form class="navbar-form navbar-left" action="search" method="get">
			<div class="form-group">
				<input class="form-control" type="text" name="keyword"/>
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
		<s:action name="categories" executeResult="true"/>
		<div class="navbar-right">
			<s:if test="#session.user==null">
				<ul class="nav navbar-nav" >
					<li>
						<a href="showCart">Cart
							<s:if test="#session.cart!=null">
								<span class="badge"><s:property value="#session.cart.orderitems.size"/></span>
							</s:if>
						</a>
					</li>
				</ul>
				<a class="btn btn-primary navbar-btn" href="index.jsp?page=register">Sign up</a>
				<a class="btn btn-default navbar-btn" href="index.jsp?page=login">Sign in</a>
			</s:if>
			<s:else>
				<p class="navbar-text">Welcome, <a href="getUserInfo?userId=<s:property value="#session.user.userId"/>"><s:property value="#session.user.username"/></a></p>
				<ul class="nav navbar-nav" >
					<li>
						<a href="getUserOrder">Orders</a>
					</li>
					<li>
						<a href="showCart">Cart
							<s:if test="#session.cart!=null">
								<span class="badge"><s:property value="#session.cart.items.size"/></span>
							</s:if>
						</a>
					</li>
				</ul>
				<s:if test="#session.user.role=='admin'">
					<a class="btn btn-danger navbar-btn" href="allBooksPro">Admin</a>
				</s:if>
				<a class="btn btn-default navbar-btn"  href="logout.action">Log out</a>
			</s:else>
		</div>
	</div>
</nav>