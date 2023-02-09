<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>购物车</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script>
        //修改购物车内商品数量，参数upperBound为商品现有库存数
        function editProductCount(productID, count, upperBound) {
            count = parseInt(count);
            upperBound = parseInt(upperBound);
            if(count === 0) {
                var confirm = window.confirm("确定从购物车中移除该商品吗？");
                if(!confirm) {
                    return;
                }
            }
            else if(count > upperBound){
                alert("已达到该商品购买限额，不可再添加。");
                return;
            }
            $.ajax({
                type: "post",
                url: "/editCart",
                data: {id: productID, count: count},
                success: function () {
                    window.location.reload(); //修改成功后刷新页面数据
                }
            })
        }
    </script>
</head>
<body>
    <!--navigation bar-->
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <c:if test="${cart != null && cart.size() > 0 && user.type == 'user'}">
            <table id="cart">
                <tr id="cartField">
                    <th class="column g-select">序号</th>
                    <th class="column g-images">图片</th>
                    <th class="column g-goodsname">商品名称</th>
                    <th class="column g-price">单价</th>
                    <th class="column g-quantity">数量</th>
                    <th class="column g-sum">小计</th>
                    <th class="column g-action">操作</th>
                </tr>
                <c:set var="totalPrice" value="0" />
                <c:set var="totalAmount" value="0" />
                <!--遍历购物车cart内的每个商品-->
                <c:forEach items="${cart}" var="item" varStatus="vs">
                <tr class="cartItem">
                    <!--序号-->
                    <td class="cell c-select">
                        ${vs.count}
                    </td>
                    <!--商品图-->
                    <td class="cell">
                        <a href="${pageContext.request.contextPath}/productDetail?id=${item.key.productID}"
                           title="${item.key.name}">
                            <img src="${item.key.imgURL}" height="150px" width="150px" alt="${item.key.name}">
                        </a>
                    </td>
                    <!--商品名-->
                    <td class="cell">
                        <a href="${pageContext.request.contextPath}/productDetail?id=${item.key.productID}"
                           title="${item.key.name}">${item.key.name}</a>
                    </td>
                    <!--商品单价-->
                    <td class="cell">
                        ￥<fmt:formatNumber type="number" value="${item.key.price}" pattern="#.00"/>
                    </td>
                    <!--商品加购数量-->
                    <td class="cell">
                        <!-- “-”（减）按钮 -->
                        <button class="reduce"
                                onclick="editProductCount('${item.key.productID}', '${item.value - 1}', '${item.key.amount}')">
                            -
                        </button>
                        ${item.value}
                        <!-- “+”（加）按钮 -->
                        <button class="add"
                                onclick="editProductCount('${item.key.productID}', '${item.value + 1}', '${item.key.amount}')">
                            +
                        </button>
                    </td>
                    <!--商品总价小计-->
                    <td class="cell">
                        ￥<fmt:formatNumber type="number" value="${item.key.price * item.value}" pattern="#.00"/>
                    </td>
                    <!--移除操作-->
                    <td class="cell">
                        <button onclick="editProductCount('${item.key.productID}', '0', '${item.key.amount}')">移除商品</button>
                    </td>
                </tr>
                <c:set var="totalPrice" value="${totalPrice + item.key.price * item.value}"/>
                <c:set var="totalAmount" value="${totalAmount + item.value}"/>
                </c:forEach>
            </table>
            <div id="order">
                <table class="t">
                    <tr>
                        <th>总件数</th>
                        <td>${totalAmount}件</td>
                    </tr>
                    <tr>
                        <th>总价</th>
                        <td>￥<fmt:formatNumber type="number" value="${totalPrice}" pattern="#.00"/></td>
                    </tr>
                </table>
                <div id="orderBtn">
                    <a href="<%=request.getContextPath()%>/createOrder.jsp"><button>结算</button></a>
                </div>
            </div>
        </c:if>
        <c:if test="${cart == null || cart.size() <= 0 || user.type != 'user'}">
            <div class="empty">
                空 空 如 也
            </div>
        </c:if>
    </div>
</body>
</html>
