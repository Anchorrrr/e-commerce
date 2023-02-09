<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>最近订单</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@include file="top_navbar.jsp"%>
<div class="main">
    <c:if test="${orders != null && user != null && user.type == 'admin'}">
        <table id="recentOrders">
            <tr id="cartField">
                <th class="column">序号</th>
                <th class="column">用户名</th>
                <th class="column">订单号</th>
                <th class="column">订单创建时间</th>
                <th class="column">总价</th>
                <th class="column">实付</th>
                <th class="column">支付状态</th>
            </tr>
            <c:forEach items="${orders}" var="item" varStatus="vs">
                <tr class="cartItem">
                    <td class="cell">
                        ${vs.count}
                    </td>
                    <script>
                        console.log("${item.value}");
                    </script>
                    <td>
                        <a href="${pageContext.request.contextPath}/showOrders?userID=${item.key.userID}">${item.value}</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/orderDetail?id=${item.key.orderID}">${item.key.orderID}</a>
                    </td>
                    <td class="cell">
                        ${item.key.createTime}
                    </td>
                    <td class="cell">
                        ￥<fmt:formatNumber type="number" value="${item.key.totalPrice}" pattern="#0.00"/>
                    </td>
                    <td class="cell">
                        ￥<fmt:formatNumber type="number" value="${item.key.actualPayment}" pattern="#0.00"/>
                    </td>
                    <td class="cell">
                        <c:if test="${item.key.paid == true}">已支付</c:if>
                        <c:if test="${item.key.paid == false}">等待支付</c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${orders == null || user == null || user.type != 'admin'}">
        <div class="empty">
            您 无 权 操 作
        </div>
    </c:if>
</div>
</body>
</html>
