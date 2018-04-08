<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>Books</h2>
<hr>
<div>
	<s:if test="bookInfos.size>0">
		<div id="books">
			<s:iterator value="bookInfos">
				<div class="col-sm-3">
					<div style="border:1px; width:250px; height:450px">
						<a href="bookDetails?bookId=<s:property value="bookId"/>">
							<img height="200" width="200" class="img-thumbnail" src="data:image/jpg;base64,<s:property value="image"/>"/>
						</a>
						<p><strong><s:property value="title"/></strong></p>
						<p>$<strong><s:property value="price/100"/>.<s:if test="price%100<10">0</s:if><s:if test="price%100=0">0</s:if><s:property value="price%100"/></strong></p>
						<a class="btn btn-primary" href="addToCart.action?bookId=<s:property value="bookId"/>" >
							Add to Cart
						</a>
						<a class="btn btn-default" id="detail" href="bookDetails?bookId=<s:property value="bookId"/>">
							View details »
						</a>
					</div>
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<p><strong>No such books!</strong></p>
		<a class="button" href="index.jsp">Home Page</a>
	</s:else>
</div>
