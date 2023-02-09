<%
    PageBean pageBean = (PageBean) session.getAttribute("pageBean");
    if (pageBean == null || !pageBean.isIndexPage())
    {
        response.sendRedirect(request.getContextPath() + "/showProducts");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商城首页</title>
</head>
<body>
    <!--navigation bar-->
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <!--carousel-->
        <div>

        </div>
        <div class="catesTag">全部商品></div>
        <%@include file="showProducts.jsp"%>
    </div>
</body>
</html>







