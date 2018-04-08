<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>Cart</h2>
<hr>
<s:if test="#session.cart.items.size>0">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="dataTable_wrapper">
                <s:form theme="simple" action="updateCart" method="post">
                    <table class="table table-striped table-bordered table-hover" id="cart">
                        <tr><th>Name</th><th>Price</th><th>Amount</th><th>Operation</th></tr>
                        <s:set var="totalprice" value="0"/>
                        <s:iterator value="#session.cart.items">
                            <s:set var="totalprice" value="#totalprice+amount*unitPrice"/>
                            <tr>
                                <td>
                                    <input type="hidden" name="bookId" value="<s:property value="bookId"/>"/>
                                    <s:property value="title"/>
                                </td>
                                <td>
                                    $<s:property value="unitPrice/100"/>.<s:if test="unitPrice%100<10">0</s:if><s:if test="unitPrice%100=0">0</s:if><s:property value="unitPrice%100"/>
                                </td>
                                <td>
                                    <s:if test="amount>1">
                                        <a href="updateCart.action?bookId=<s:property value="bookId"/>&amount=<s:property value="amount-1"/>"><i class="fa fa-minus"></i></a>
                                    </s:if>
                                    <s:else>
                                        <span><i class="fa fa-minus"></i></span>
                                    </s:else>
                                    <s:textfield name="amount" cssClass="number"/>
                                    <a href="updateCart.action?bookId=<s:property value="bookId"/>&amount=<s:property value="amount+1"/>"><i class="fa fa-plus"></i></a>
                                </td>
                                <td>
                                    <a href="removeFromCart.action?bookId=<s:property value="bookId"/>"><i class="fa fa-trash"></i></a>
                                </td>
                            </tr>
                        </s:iterator>
                        <tr>
                            <td id="totalPrice" colspan="4">
                                <strong>Total Price：</strong>
                                <em class="price">$<s:property value="#totalprice/100"/>.<s:if test="#totalprice%100<10">0</s:if><s:if test="#totalprice%100=0">0</s:if><s:property value="#totalprice%100"/></em>
                            </td>
                        </tr>
                    </table>
                    <p class="action-bar">
                        <a href="checkout" class="btn btn-primary">Checkout</a>
                    </p>
                </s:form>
            </div>
        </div>
    </div>
</s:if>
<s:else>
    <p class="message">Cart is empty!</p>
    <p class="action-bar"><a class="button" href="index.jsp">Homepage</a></p>
</s:else>
