<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>订单详情</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/order.js"></script>
</head>
<body>
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <c:if test="${cart != null && cart.size() > 0}">
            <table id="cart">
                <tr id="cartField">
                    <th class="column">序号</th>
                    <th class="column">图片</th>
                    <th class="column">商品名称</th>
                    <th class="column">单价</th>
                    <th class="column">数量</th>
                    <th class="column">小计</th>
                </tr>
                <c:set var="totalPrice" value="0" />
                <c:set var="totalAmount" value="0" />
                <c:forEach items="${cart}" var="item" varStatus="vs">
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
                    <c:set var="totalPrice" value="${totalPrice + item.key.price * item.value}"/>
                    <c:set var="totalAmount" value="${totalAmount + item.value}"/>
                </c:forEach>
            </table>
            <div id="order">
                <form id="orderInfoForm">
                    <table class="t">
                        <tr>
                            <td><input type="text" id="totalPrice" name="totalPrice" value="${totalPrice}" style="display: none"/></td>
                            <td><input type="text" id="actualPayment" name="actualPayment" value="${0.00}" style="display: none"/></td>
                        </tr>
                        <tr>
                            <th>收货人</th><td><input type="text" id="name" name="name" onblur="checkName()"/></td>
                        </tr>
                        <tr>
                            <td></td><td class="msg" id="nameMsg"></td>
                        </tr>
                        <tr>
                            <th>联系电话</th><td><input type="tel" id="phone" name="phone" onblur="checkPhone()"/></td>
                        </tr>
                        <tr>
                            <td></td><td class="msg" id="phoneMsg"></td>
                        </tr>
                        <tr>
                            <th>收货地址</th><td><input type="text" id="address" name="address" onblur="checkAddress()"/></td>
                        </tr>
                        <tr>
                            <td></td><td class="msg" id="addressMsg"></td>
                        </tr>
                    </table>
                </form>
                <table class="t">
                    <tr>
                        <th>总件数</th><td>${totalAmount}件</td>
                    </tr>
                    <tr>
                        <th>总价</th><td>￥<fmt:formatNumber type="number" value="${totalPrice}" pattern="#.00"/></td>
                    </tr>
                    <tr>
                        <th>优惠</th>
                        <td><font style="color: #bb3f3f">
                            -￥<fmt:formatNumber type="number" value="${totalPrice}" pattern="#.00"/></font></td>
                    </tr>
                    <tr>
                        <th>实付</th>
                        <td><font style="color: #bb3f3f">￥<fmt:formatNumber type="number" value="${0.00}" pattern="#0.00"/></font></td>
                    </tr>
                </table>
                <div class="msg" id="createOrderMsg"></div>
                <div id="orderBtn">
                    <input id="orderSubmit" type="submit" onclick="checkForm()" value="提交订单"/>
                </div>
            </div>
        </c:if>
        <c:if test="${cart == null || cart.size() <= 0}">
            <div class="empty">
                空 空 如 也
            </div>
        </c:if>
    </div>
</body>
</html>
