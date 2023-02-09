<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册帐号</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/register.js"></script>
</head>
<body>
    <!--top_navbar-->
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <div>
            <form class="infoForm"
                  action="<%=request.getContextPath()%>/register" method="post"
                  onsubmit="return checkForm()">
                <table>
                    <tr><th class="tagTd">邮箱</th>
                        <td><input type="text" id="email" name="email" onblur="validateEmail()"/></td></tr>
                    <tr><td></td>
                        <td class="msg" id="emailMsg"></td></tr>
                    <tr><th class="tagTd">用户名</th>
                        <td><input type="text" id="name" name="name" onblur="validateName()"/></td></tr>
                    <tr><td></td>
                        <td class="msg" id="nameMsg"></td></tr>
                    <tr><th class="tagTd">密码</th>
                        <td><input type="password" id="password" name="password" onblur="validatePassword()"/></td></tr>
                    <tr><td></td>
                        <td class="msg" id="passwordMsg"></td></tr>
                    <tr><th class="tagTd">确认密码</th>
                        <td><input type="password" id="password_again" name="password_again" onblur="validatePassword()"/></td></tr>
                    <tr><td></td>
                        <td class="msg" id="registerMsg"></td></tr>
                    <script>
                        $("#registerMsg").text("${requestScope.registerMsg}");
                        $("#registerMsg").css("color", "green");
                        document.getElementById("email").value = "${requestScope.email}";
                        document.getElementById("name").value = "${requestScope.name}";
                    </script>
                    <%  request.removeAttribute("registerMsg");
                        request.removeAttribute("email");
                        request.removeAttribute("name"); %>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="submit" value="注册"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="aDiv">
            <a href="login.jsp">已有帐号? 去登录</a>
            <a href="sendEmail.jsp">没收到激活邮件?</a>
        </div>
    </div>
</body>
</html>