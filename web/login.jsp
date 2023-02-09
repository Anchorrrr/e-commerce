<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script>
         function clickFunc() {
            $("#emailMsg").text("");
        }
    </script>
</head>
<body>
<!--top_navbar-->
<%@include file="top_navbar.jsp" %>
<div class="main">
    <div>
        <form class="infoForm"
              action="<%=request.getContextPath()%>/login" method="post"
              onsubmit="return">
            <table>
                <tr><th class="tagTd">邮 箱</th>
                    <td><input type="text" id="email" name="email" required="required" onclick="clickFunc()"/></td></tr>
                <tr><td></td>
                    <td class="msg" id="emailMsg"></td></tr>
                <tr><th class="tagTd">密码</th>
                    <td><input type="password" id="password" name="password" required="required" onclick="clickFunc()"/></td>
                </tr>
                <script>
                    $("#emailMsg").text("${requestScope.loginMsg}");
                    $("#emailMsg").css("color", "red");
                    document.getElementById("email").value = "${requestScope.email}";
                    document.getElementById("password").value = "${requestScope.password}";
                </script>
                <% request.removeAttribute("loginMsg");
                    request.removeAttribute("email");
                    request.removeAttribute("password"); %>
                <tr>
                    <td></td>
                    <td class="msg"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="submit" value="登录"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="aDiv">
        <a href="<%=request.getContextPath()%>/register.jsp">还没帐号？去注册</a>
        <a href="<%=request.getContextPath()%>/sendEmail.jsp">没收到激活邮件?</a>
    </div>
</div>
</body>
</html>
