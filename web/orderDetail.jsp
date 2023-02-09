<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>订单详情</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script>
        function checkout() {  //结账
            $.ajax({
                async: true,
                type: "post",
                url: "/checkout",
                data: {orderID: "${order.orderID}"},
                success: function (data) {
                    if (data.trim() === "true") {
                        window.location.reload();
                        alert("支付成功，已向您的邮箱发送通知");
                    }
                    else {
                        alert("支付失败，请稍后重试");
                    }
                },
            });
        }
    </script>
</head>
<body>
<%@include file="top_navbar.jsp"%>
<div class="main">
    <c:if test="${orderDetail != null && orderDetail.size() > 0 && (order.userID == user.userID || user.type == 'admin')}">
        <table id="cart">
            <tr id="cartField">
                <th class="column">序号</th>
                <th class="column">图片</th>
                <th class="column">商品名称</th>
                <th class="column">单价</th>
                <th class="column">数量</th>
                <th class="column">小计</th>
            </tr>
            <c:set var="totalAmount" value="0" />
            <c:forEach items="${orderDetail}" var="item" varStatus="vs">
                <tr class="cartItem">
                    <td class="cell">
                            ${vs.count}
                    </td>
                    <td class="cell">
                        <a href="/productDetail?id=${item.key.productID}" title="${item.key.name}">
                            <img src="${item.key.imgURL}" height="80px" width="80px" alt="${item.key.name}">
                        </a>
                    </td>
                    <td class="cell">
                        <a href="./productDetail?id=${item.key.productID}" title="${item.key.name}">${item.key.name}</a>
                    </td>
                    <td class="cell">
                        ￥<fmt:formatNumber type="number" value="${item.key.price}" pattern="#.00"/>
                    </td>
                    <td class="cell">
                            ${item.value}
                    </td>
                    <td class="cell">
                        ￥<fmt:formatNumber type="number" value="${item.key.price * item.value}" pattern="#.00"/>
                    </td>
                </tr>
                <c:set var="totalAmount" value="${totalAmount + item.value}"/>
            </c:forEach>
        </table>
        <div id="order">
            <div id="orderInfoTable">
                <table>
                    <tr>
                        <th>订单号</th>
                        <td id="orderIdMsg">${order.orderID}</td>
                    </tr>
                    <tr>
                        <th>创建时间</th>
                        <td id="createTimeMsg">${order.createTime}</td>
                    </tr>
                    <tr>
                        <th>支付状态</th>
                        <c:if test="${order.paid == true}">
                            <td id="paidMsg">已支付</td>
                        </c:if>
                        <c:if test="${order.paid == false}">
                            <td id="paidMsg">未支付</td>
                        </c:if>
                    </tr>
                    <c:if test="${order.paid == true}">
                        <tr>
                            <th>支付时间</th>
                            <td>${order.payTime}</td>
                        </tr>
                    </c:if>
                </table>
                <div id="remainTime"></div>
                <c:if test="${order.paid == false}">
                    <div>
                    <%
                        if (userBean != null && userBean.getType().equals("user"))
                        {
                    %>
                        <button id="payBtn" onclick="checkout()">去支付</button>
                    <%
                        }
                    %>
                    </div>
                </c:if>
            </div>
            <form id="orderInfoForm">
                <table class="t">
                    <tr>
                        <th>收货人</th>
                        <td>${order.name}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="msg" id="nameMsg"></td>
                    </tr>
                    <tr>
                        <th>联系电话</th>
                        <td>${order.phone}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="msg" id="phoneMsg"></td>
                    </tr>
                    <tr>
                        <th>收货地址</th>
                        <td>${order.address}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="msg" id="addressMsg"></td>
                    </tr>
                </table>
            </form>
            <table class="t">
                <tr>
                    <th>总件数</th>
                    <td>${totalAmount}件</td>
                </tr>
                <tr>
                    <th>总价</th>
                    <td>￥<fmt:formatNumber type="number" value="${order.totalPrice}" pattern="#0.00"/></td>
                </tr>
                <tr>
                    <th>优惠</th>
                    <td><font style="color: #bb3f3f">-￥<fmt:formatNumber type="number" value="${order.totalPrice - order.actualPayment}" pattern="#0.00"/></font></td>
                </tr>
                <tr>
                    <th>实付</th>
                    <td><font style="color: #bb3f3f">￥<fmt:formatNumber type="number" value="${order.actualPayment}" pattern="#0.00"/></font></td>
                </tr>
            </table>
        </div>
    </c:if>
    <c:if test="${orderDetail == null || orderDetail.size() <= 0 || (order.userID != user.userID && user.type != 'admin')}">
        <div class="empty">
            空 空 如 也
        </div>
    </c:if>
</div>
</body>
</html>
