<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>Order List</h2>
<s:if test="ordersInfo.size>0">
    <s:iterator value="ordersInfo">
        <s:set var="totalprice" value="0"/>
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table id="orders" class="table table-striped table-bordered table-hover">
                        <caption>
                            <label>Order ID：</label><strong>#<s:property value="orderId"/></strong>
                            <label>Date：</label><em class="date"><s:date format="yyyy-MM-dd HH:mm:ss" name="date"/></em>
                        </caption>
                        <tr><th>Book</th><th>Price</th><th>Amount</th></tr>
                        <s:iterator value="orderitems">
                            <tr>
                                <td>
                                    <strong><s:property value="title"/></strong>
                                </td>
                                <td><em id="price">$<s:property value="unitPrice/100"/>.<s:if test="unitPrice%100<10">0</s:if><s:if test="unitPrice%100=0">0</s:if><s:property value="unitPrice%100"/></em></td>
                                <td><em id="amount"><s:property value="amount"/></em></td>
                                <s:set var="totalprice" value="#totalprice+amount*unitPrice"/>
                            </tr>
                        </s:iterator>
                    </table>
                    <label>Total：</label><em id="totalPrice">$<s:property value="#totalprice/100"/>.<s:if test="#totalprice%100<10">0</s:if><s:if test="#totalprice%100=0">0</s:if><s:property value="#totalprice%100"/></em>
                </div>
            </div>
        </div>
    </s:iterator>
</s:if>
<s:else>
    <p class="message">No Orders!</p>
    <p class="action-bar"><a class="button" href="index.jsp">Home Page</a></p>
</s:else>