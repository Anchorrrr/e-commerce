<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function msg()
    {
        alert("本功能待开发");
    }
</script>
</script>
<html>
<head>
    <title>Title</title>
    <link href="/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
    <%@include file="top_navbar.jsp"%>
    <%
        if(session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    %>
    <div class="main">
        <table id="userInfo">
            <tr>
                <th>邮箱</th>
                <% if(userBean.getEmail() != null) { %>
                    <td>${sessionScope.user.email}</td>
                    <td><button onclick="msg()">更改邮箱</button></td>
                <% } else { %>
                    <td>暂未绑定</td>
                    <td><button onclick="msg()">去绑定</button></td>
                <% } %>
            </tr>
            <tr>
                <td></td>
                <td><button onclick="msg()">更改密码</button></td>
            </tr>
            <tr>
                <td></td>
                <td><a href="<%=request.getContextPath()%>/logout"><button>注销</button></a></td>
            </tr>
        </table>
    </div>
</body>
</html>
