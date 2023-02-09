<%@ page import="entity.ProductBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品</title>
    <link href="/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
    <!--navigation bar-->
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <div class="catesTag">
            <!-- group tab -->
            <%
                if (request.getParameter("group") != null) {
                    String g = ProductBean.groupMap(request.getParameter("group"));
                    if (!g.equals("")) {
            %>
            <%=
                        g
            %>
            >
            <%
                    }
            %>
            <%
                }
            %>
            <!-- category tab -->
            <%
                if (request.getParameter("category") != null) {
                    String c = ProductBean.categoryMap(request.getParameter("category"));
                    if (!c.equals("")) {
            %>
            <%=
                        c
            %>
            >
            <%
                    }
            %>
            <%
                }
            %>
            <!-- name tab-->
            <%
                if (request.getParameter("name") != null) {
            %>
            "
            <%=
                    request.getParameter("name")
            %>
            ">
            <%
                }
            %>
        </div>
        <%@include file="showProducts.jsp"%>
    </div>
</body>
</html>
