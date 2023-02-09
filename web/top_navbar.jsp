<%@ page import="entity.UserBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<script src="${pageContext.request.contextPath}/js/top_navbar.js"></script>
<link href="${pageContext.request.contextPath}/css/nav.css" type="text/css" rel="stylesheet">

<div id="top_navbar">
    <div id="nav">
        <ul id="groupTabs">
            <!--Home按钮-->
            <li>
                <a class="logo" href="<%=request.getContextPath()%>/showProducts">
                    <img src="<%=request.getContextPath()%>/img/logo.png" height="30px"/>
                </a>
            </li>
            <!--选择女装-->
            <li>
                <a class="tab" href="<%=request.getContextPath()%>/showProducts?group=women"
                   onmouseenter="womenTabMouseEnter()">女装</a>
            </li>
            <!--选择男装-->
            <li>
                <a class="tab" href="<%=request.getContextPath()%>/showProducts?group=men"
                   onmouseenter="menTabMouseEnter()">男装</a>
            </li>
            <!--选择童装-->
            <li>
                <a class="tab" href="<%=request.getContextPath()%>/showProducts?group=kids"
                   onmouseenter="kidsTabMouseEnter()">童装</a>
            </li>
        </ul>
        <!--关键词搜索-->
        <form action="<%=request.getContextPath()%>/showProducts" id="search_form" method="get">
            <table>
                <tr>
                    <td>
                        <c:if test="${param.group != null}">
                            <input type="hidden" name="group" value="${param.group}">
                        </c:if>
                        <c:if test="${param.category != null}">
                            <input type="hidden" name="category" value="${param.category}">
                        </c:if>
                        <input type="text" name="name" placeholder="关键词搜索"/>
                    </td>
                    <td>
                        <button><img src="/img/search.png"/></button>
                    </td>
                </tr>
            </table>
        </form>
        <ul id="toolTabs">
            <!--管理员操作-->
            <%
                UserBean userBean = (UserBean) session.getAttribute("user");
                if(userBean != null && userBean.getType().equals("admin"))
                {
            %>
                <li id="manager" class="tab" onmouseenter="managerTabMouseEnter()">管理员</li>
            <!--普通用户可以查看自己的订单记录、购物车-->
            <%
                } else {
            %>
            <!--历史订单-->
            <li><a class="logo" href="/showOrders"><img src="/img/order.png" height="30px" width="30px"/></a></li>
            <!--购物车-->
            <li><a class="logo" href="/cart.jsp"><img src="/img/cart.png" height="30px" width="30px"/></a></li>
            <%
                }
            %>
            <!--登录or用户信息-->
            <%
                if (session.getAttribute("user") == null) {
                    String URI = request.getRequestURI();
                    if (!URI.equals("/login.jsp") && !URI.equals("/register.jsp")) {
                        if (request.getQueryString() != null) {
                            session.setAttribute("preURL", request.getRequestURL().toString() + "?" +
                                    request.getQueryString());
                        }
                        else {
                            session.setAttribute("preURL", request.getRequestURL().toString());
                        }
                    }
            %>
                <li><a class="tab" href="/login.jsp">登录</a></li>
            <%
                }
                else {
            %>
                <li><a class="logo" href="/userInfo.jsp"><img src="/img/user.png" height="30px" width="30px"/></a></li>
            <%
                }
            %>
        </ul>
    </div>
    <!--种类标签-->
    <div id="category_tabs">
        <!--women-->
        <div id="womenCates" class="cates" style="display: none">
            <dl>
                <div>
                    <dt>T恤</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=shortT">短袖T恤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=longT">长袖T恤</a></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>外套</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=fleece">卫衣</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=downjacket">羽绒服</a></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>裤装</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=jeans">牛仔裤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=casualpants">休闲裤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=sweatpants">运动裤</a></dd>
                    <dd></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>裙装</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=dress">连衣裙</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=women&category=skirt">半身裙</a></dd>
                </div>
            </dl>
        </div>
        <!--men-->
        <div id="menCates" class="cates" style="display: none">
            <dl>
                <div>
                    <dt>T恤</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=shortT">短袖T恤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=longT">长袖T恤</a></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>外套</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=fleece">卫衣</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=downjacket">羽绒服</a></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>裤装</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=jeans">牛仔裤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=casualpants">休闲裤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=men&category=sweatpants">运动裤</a></dd>
                    <dd></dd>
                </div>
            </dl>
        </div>
        <!--kids-->
        <div id="kidsCates" class="cates" style="display: none">
            <dl>
                <div>
                    <dt>T恤</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=shortT">短袖T恤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=longT">长袖T恤</a></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>外套</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=fleece">卫衣</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=downjacket">羽绒服</a></dd>
                </div>
            </dl>
            <dl>
                <div>
                    <dt>裤装</dt>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=jeans">牛仔裤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=casualpants">休闲裤</a></dd>
                    <dd><a href="<%=request.getContextPath()%>/showProducts?group=kids&category=sweatpants">运动裤</a></dd>
                    <dd></dd>
                </div>
            </dl>
        </div>
        <!--管理员操作-->
        <div id="managerTabs" class="cates" style="display: none">
            <dl>
                <div><dd><a class="tab" href="<%=request.getContextPath()%>/addProduct.jsp">添加商品</a></dd></div>
            </dl>
            <dl>
                <div><dd><a class="tab" href="<%=request.getContextPath()%>/sales">销售统计</a></dd></div>
            </dl>
            <dl>
                <div><dd><a class="tab" href="<%=request.getContextPath()%>/recentOrders">用户订单</a></dd></div>
            </dl>
        </div>
    </div>
    <!--半透明灰色蒙层-->
    <div id="mask" onmouseenter="closeCates()"></div>
</div>
