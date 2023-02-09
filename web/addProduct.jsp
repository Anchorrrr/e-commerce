<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <link href="/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
    <%@include file="/top_navbar.jsp"%>
    <%
        if (request.getAttribute("addProductMsg") != null) {
    %>
            <script>
                alert("${addProductMsg}");
            </script>
    <%
            request.removeAttribute("addProductMsg");
        }
    %>
    <div class="main">
        <%
            if (userBean != null && userBean.getType().equals("admin"))
            {
        %>
            <div class="catesTag">
                添加商品>
            </div>
            <form class="infoForm" id="addForm" action="<%=request.getContextPath()%>/addProduct"
              method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>商品名称：</td>
                    <td><input type="text" name="name"/></td>
                    <td class="leftMargin">商品价格：</td>
                    <td><input type="text" name="price"/></td>
                </tr>
                <tr>
                    <td>商品数量：</td>
                    <td><input type="text" name="amount"/></td>
                    <td class="leftMargin">商品类别：</td>
                    <td>
                        <select name="group">
                            <option value="" selected="selected">--选择群体--</option>
                            <option value="women">女装</option>
                            <option value="men">男装</option>
                            <option value="kids">童装</option>
                        </select>
                        <select name="category">
                            <option value="" selected="selected">--选择服装种类--</option>

                            <option value="shortT">短袖T恤</option>
                            <option value="longT">长袖T恤</option>

                            <option value="fleece">卫衣</option>
                            <option value="downjacket">羽绒服</option>

                            <option value="jeans">牛仔裤</option>
                            <option value="casualpants">休闲裤</option>
                            <option value="sweatpants">运动裤</option>

                            <option value="dress">连衣裙</option>
                            <option value="skirt">半身裙</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>商品图片：</td>
                    <td><input type="file" name="imgURL"/></td>
                </tr>
                <tr>
                    <td>商品描述：</td>
                    <td><textarea name="description"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input class="btn" type="submit" value="确定"/></td>
                    <td class="leftMargin"><input class="btn" type="reset" value="重置"/></td>
                </tr>
            </table>
        </form>
        <%
            } else {
        %>
            <div class="empty">
                您 无 权 操 作
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
