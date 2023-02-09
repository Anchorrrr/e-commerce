<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮箱激活</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script>
        function validateEmail_() {
            var regex = /[a-zA-Z0-9]+([-_.][A-Za-zd]+)*@([a-zA-Z0-9]+[-.])+[A-Za-zd]{2,5}$/;
            if (!regex.test($("#email_").val())){
                $("#emailMsg_").text("邮箱格式不合法");
                $("#emailMsg_").css("color", "red");
                return false;
            }
            else {
                $("#emailMsg_").text("");
                return true;
            }
        }
    </script>
</head>
<body>
    <%@include file="top_navbar.jsp"%>
    <div class="main">
        <form id="sendEmailForm" class="infoForm" method="post"
              onsubmit="return validateEmail_()" action="<%=request.getContextPath()%>/sendEmail">
            <table>
                <tr>
                    <th>邮箱</th>
                    <td><input type="text" name="email" id="email_" onblur="validateEmail_()" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td class="msg" id="emailMsg_"></td>
                    <script>
                        $("#emailMsg_").text("${requestScope.sendEmailMsg}");
                        $("#emailMsg_").css("color", "red");
                        document.getElementById("email_").value = "${requestScope.email}";
                    </script>
                    <% request.removeAttribute("sendEmailMsg");
                       request.removeAttribute("email"); %>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="获取激活邮件"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
