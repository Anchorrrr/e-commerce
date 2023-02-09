<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>订单</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <c:if test="${orders != null}">
            <table id="cart">
                <tr id="cartField">
                    <th class="column">序号</th>
                    <th class="column">订单号</th>
                    <th class="column">订单创建时间</th>
                    <th class="column">总价</th>
                    <th class="column">实付</th>
                    <th class="column">支付状态</th>
                    <th class="column">操作</th>
                </tr>
                <c:forEach items="${orders}" var="item" varStatus="vs">
                    <tr class="cartItem">
                        <td class="cell">
                            ${vs.count}
                        </td>
                        <td>
                            ${item.orderID}
                        </td>
                        <td class="cell">
                            ${item.createTime}
                        </td>
                        <td class="cell">
                            ￥<fmt:formatNumber type="number" value="${item.totalPrice}" pattern="#0.00"/>
                        </td>
                        <td class="cell">
                            ￥<fmt:formatNumber type="number" value="${item.actualPayment}" pattern="#0.00"/>
                        </td>
                        <td class="cell">
                            <c:if test="${item.paid == true}">已支付</c:if>
                            <c:if test="${item.paid == false}">等待支付</c:if>
                        </td>
                        <td class="cell">
                            <a href="${pageContext.request.contextPath}/orderDetail?id=${item.orderID}"><button>查看详情</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${orders == null}">
            <div class="empty">
                空 空 如 也
            </div>
        </c:if>
    </div>
</body>
</html>
