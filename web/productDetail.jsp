<%@ page import="entity.ProductBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${productBean.name}</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/product.js"></script>
</head>
<body>
    <%@include file="top_navbar.jsp"%>
    <%
        if (session.getAttribute("productBean") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
    %>
    <div class="main">
        <table class="productDetail">
            <tr>
                <td id="images">
                    <div>
                        <p>
                            <img src="${pageContext.request.contextPath}${productBean.imgURL}" width="100%" height="100%" />
                        </p>
                    </div>
                </td>
                <td id="detailInfo">
                    <b>商品名：</b>${productBean.name}
                    <hr />
                    <b>售价：</b>￥<fmt:formatNumber type="number" value="${productBean.price}" pattern="#.00"/>
                    <% ProductBean p = (ProductBean) session.getAttribute("productBean"); %>
                    <hr />
                    <b>类别：</b><%=ProductBean.groupMap(p.getGroup())%> | <%=ProductBean.categoryMap(p.getCategory())%>
                    <hr />
                    <b>简介：</b>
                    <p>${productBean.description}</p>
                    <c:if test="${user != null && user.type == 'admin'}">
                        <hr />
                        <b>当前库存：</b>${productBean.amount}
                    </c:if>
                </td>
            </tr>
        </table>
        <!--判断用户类型（普通用户/管理员）-->
        <%
            if (userBean != null && userBean.getType().equals("admin")) {  //已登录且是管理员
        %>
        <div id="optDiv">
            <button id="remove" onclick="doRemove()">
                <font color="red">下架该商品</font>
            </button>
            <button id="changeAmount" onclick="doChangeAmount()">
                <font color="green">修改库存</font>
            </button>
        </div>
        <%
            } else {  //未登录或是已登录普通用户
        %>
        <div id="buyAdd">
            <button id="buy" onclick="buyNow()">
                立即购买
            </button>
            <button id="addCart" onclick="addCart()">
                加入购物车
            </button>
        </div>
        <%
            }
        %>
    </div>
</body>
</html>
