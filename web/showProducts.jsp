<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="entity.PageBean" %>
<link href="/css/main.css" type="text/css" rel="stylesheet">

<div>
    <%
        if (session.getAttribute("pageBean") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
    %>
    <!--展示商品简要信息-->
    <table id="productsTable">
        <% int countCol = 0; int countTot = 0;
            PageBean temp = (PageBean)session.getAttribute("pageBean"); int size = temp.getProducts().size();%>
        <!--遍历pageBean内的商品-->
        <c:forEach items="${pageBean.products}" var="prd" varStatus="vs">
            <!--每行显示4个商品，countCol表示当前商品位于第几列(0,1,2,3)-->
            <!--若当前商品位于第0列，另起一行-->
            <% if(countCol == 0) { %>
                <tr class="productsRow">
            <% } %>
                    <td class="productUnit">
                        <div>
                            <!--插入商品图片-->
                            <a href="<%=request.getContextPath()%>/productDetail?id=${prd.productID}">
                                <img src="<%=request.getContextPath()%>${prd.imgURL}" width="216" height="216"/>
                            </a>
                        </div>
                        <div>
                            <!--插入商品名和价格-->
                            <a href="<%=request.getContextPath()%>/productDetail?id=${prd.productID}">
                                    ${prd.name}<br/>
                                    ￥<fmt:formatNumber type="number" value="${prd.price}" pattern="#.00"/>
                            </a>
                        </div>
                    </td>
            <!--更新countCol和CountTot，countTot为商品计数器，size为该pageBean含有的商品数-->
            <!--若更新后的countCol为0，或countTot等于size（即商品全部遍历完），使用</tr>结束这一行-->
            <% countCol = (countCol + 1) % 4; countTot += 1;
               if(countCol == 0 || countTot == size) { %>
                </tr>
            <% } %>
        </c:forEach>
    </table>
    <!--页数选择器-->
    <div id="pageDiv">
        <ul>
        <!--上一页的点击链接-->
            <!--本页为第一页，故“上一页”按钮不可用-->
            <c:if test="${pageBean.pageNum == 1}">
                <li class="nearPage useless">上一页</li>
            </c:if>
            <!--本页不是第一页，“上一页”按钮可用-->
            <c:if test="${pageBean.pageNum != 1}">
                <!--根据用户不同的搜索条件，获取相应链接-->
                <c:choose>
                    <c:when test="${param.group != null and param.category != null and param.name != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&category=${param.category}&name=${param.name}&page=${pageBean.pageNum - 1}">上一页</a></li>
                    </c:when><c:when test="${param.group != null and param.category != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&category=${param.category}&page=${pageBean.pageNum - 1}">上一页</a></li>
                    </c:when><c:when test="${param.group != null and param.name != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&name=${param.name}&page=${pageBean.pageNum - 1}">上一页</a></li>
                    </c:when><c:when test="${param.group != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&page=${pageBean.pageNum - 1}">上一页</a></li>
                    </c:when><c:when test="${param.name != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?&name=${param.name}&page=${pageBean.pageNum - 1}">上一页</a></li>
                    </c:when><c:otherwise>
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?page=${pageBean.pageNum - 1}">上一页</a></li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        <!--每一页的点击链接-->
            <c:forEach begin="1" end="${pageBean.totalPageCount}" var="pageNum">
                <!--当前遍历到的页码即为当前页面页码，故按钮不可点，无需加超链接-->
                <c:if test="${pageBean.pageNum == pageNum}">
                    <li id="curPage">${pageNum}</li>
                </c:if>
                <!--当前遍历到的页码不是当前页面页码-->
                <c:if test="${pageBean.pageNum != pageNum}">
                    <!--根据用户不同的搜索条件，获取相应链接-->
                    <c:choose>
                        <c:when test="${param.group != null and param.category != null and param.name != null}">
                            <li><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&category=${param.category}&name=${param.name}&page=${pageNum}">${pageNum}</a></li>
                        </c:when><c:when test="${param.group != null and param.category != null}">
                            <li><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&category=${param.category}&page=${pageNum}">${pageNum}</a></li>
                        </c:when><c:when test="${param.group != null and param.name != null}">
                            <li><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&name=${param.name}&page=${pageNum}">${pageNum}</a></li>
                        </c:when><c:when test="${param.group != null}">
                            <li><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&page=${pageNum}">${pageNum}</a></li>
                        </c:when><c:when test="${param.name != null}">
                            <li><a href="<%=request.getContextPath()%>/showProducts?&name=${param.name}&page=${pageNum}">${pageNum}</a></li>
                        </c:when><c:otherwise>
                            <li><a href="<%=request.getContextPath()%>/showProducts?page=${pageNum}">${pageNum}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
        <!--下一页的点击链接-->
            <!--本页为最后一页，故“下一页”按钮不可用-->
            <c:if test="${pageBean.pageNum == pageBean.totalPageCount}">
                <li class="nearPage useless">下一页</li>
            </c:if>
            <!--本页不是最后一页，“下一页”按钮可用-->
            <c:if test="${pageBean.pageNum != pageBean.totalPageCount}">
                <!--根据用户不同的搜索条件，获取相应链接-->
                <c:choose>
                    <c:when test="${param.group != null and param.category != null and param.name != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&category=${param.category}&name=${param.name}&page=${pageBean.pageNum + 1}">下一页</a></li>
                    </c:when><c:when test="${param.group != null and param.category != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&category=${param.category}&page=${pageBean.pageNum + 1}">下一页</a></li>
                    </c:when><c:when test="${param.group != null and param.name != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&name=${param.name}&page=${pageBean.pageNum + 1}">下一页</a></li>
                    </c:when><c:when test="${param.group != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?group=${param.group}&page=${pageBean.pageNum + 1}">下一页</a></li>
                    </c:when><c:when test="${param.name != null}">
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?&name=${param.name}&page=${pageBean.pageNum + 1}">下一页</a></li>
                    </c:when><c:otherwise>
                        <li class="nearPage"><a href="<%=request.getContextPath()%>/showProducts?page=${pageBean.pageNum + 1}">下一页</a></li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </ul>
    </div>
</div>
