<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>Book Details</h2>
<hr>
<div class="row">
	<div class="col-sm-2 col-sm-offset-2">
		<img height="250" width="200" class="book" src="data:image/jpg;base64,<s:property value="bookDetail.image"/>"/>
	</div>
	<div class="col-md-7">
		<dl class="dl-horizontal">
			<dt>Name:</dt>
			<dd><s:property value="book.title"/></dd>
			<dt>Auther:</dt>
			<dd><s:property value="book.author"/></dd>
			<dt>Publisher:</dt>
			<dd><s:property value="book.publisher"/></dd>
			<dt>ISBN:</dt>
			<dd><s:property value="book.isbn"/></dd>
			<dt>Price:</dt>
			<dd>$<s:property value="book.price/100"/>.<s:if test="book.price%100<10">0</s:if><s:if test="book.price%100=0">0</s:if><s:property value="book.price%100"/></dd>
			<dt>Category:</dt>
			<dd><s:property value="book.category"/></dd>
			<dt>Description:</dt>
			<dd><s:property value="bookDetail.description"/></dd>
		</dl>
		<a class="col-md-offset-10 btn btn-primary" href="addToCart.action?bookId=<s:property value="book.bookId"/>&amount=1">
			Add to Cart
		</a>
	</div>
</div>
