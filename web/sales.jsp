<%@ page import="entity.ProductBean" %>
<%@ page import="entity.SaleBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售报表</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
</head>
<body>
    <%@include file="/top_navbar.jsp"%>
    <div class="main">
        <%  //检查用户是否为管理员，只有管理员可查看销售统计图
            if (userBean != null && userBean.getType().equals("admin"))
            {
        %>
            <div id="allDiv" style="margin-top: 170px; width: 100%; height: 500px;"></div>
            <div>
                <div id="womenDiv" style="margin-top: 40px; width: 100%; height: 500px"></div>
                <div id="menDiv" style="margin-top: 40px; width: 100%; height: 500px;"></div>
                <div id="kidsDiv" style="margin-top: 40px; width: 100%; height: 500px;"></div>
            </div>
            <script>
                var emphasis_ = {
                    itemStyle: {
                        shadowBlur: 20,
                    }
                };
                var toolTip_ = {
                    trigger: "item",
                    backgroundColor: "rgba(255, 255, 255, 0.85)",
                    textStyle: {
                        fontSize: 16,
                    },
                };
                var label_ = {
                    fontSize: 16,
                };
                var allDiv=echarts.init(document.getElementById("allDiv"));
                allDiv.setOption(
                    {
                        title:{
                            text: "群体销售额统计(单位：元)",
                            left: "center",
                        },
                        tooltip: toolTip_,
                        series: [
                            {
                                type: "pie",
                                radius: "80%",
                                data: [
                                    <%
                                        ArrayList<SaleBean> sales_all =
                                        (ArrayList<SaleBean>) request.getAttribute("sale_all");
                                        for (SaleBean saleBean: sales_all) {
                                    %>
                                            {value: "<%=saleBean.getSales()%>",
                                                name: "<%=ProductBean.groupMap(saleBean.getGroup())%>"},
                                    <%
                                        }
                                    %>
                                ],
                                label: label_,
                                emphasis: emphasis_,
                            }
                        ],
                    }
                );
                var womenDiv=echarts.init(document.getElementById("womenDiv"));
                womenDiv.setOption(
                    option={
                        title:{
                            text: "女装销售额统计(单位：元)",
                            left: "center",
                        },
                        tooltip: toolTip_,
                        series: [
                            {
                                type: "pie",
                                radius: "80%",
                                data: [
                                    <%
                                        ArrayList<SaleBean> sales_women = (ArrayList<SaleBean>) request.getAttribute("sale_women");
                                        for (SaleBean saleBean: sales_women) {
                                    %>
                                            {value: <%=saleBean.getSales()%>, name: "<%=ProductBean.categoryMap(saleBean.getCategory())%>"},
                                    <%
                                        }
                                    %>
                                ],
                                label: label_,
                                emphasis: emphasis_,
                            }
                        ],
                    }
                );
                var menDiv=echarts.init(document.getElementById("menDiv"));
                menDiv.setOption(
                    option={
                        title:{
                            text: "男装销售额统计(单位：元)",
                            left: "center",
                        },
                        tooltip: toolTip_,
                        series: [
                            {
                                type: "pie",
                                radius: "80%",
                                data: [
                                    <%
                                        ArrayList<SaleBean> men_all = (ArrayList<SaleBean>) request.getAttribute("sale_men");
                                        for (SaleBean saleBean: men_all) {
                                    %>
                                            {value: <%=saleBean.getSales()%>, name: "<%=ProductBean.categoryMap(saleBean.getCategory())%>"},
                                    <%
                                        }
                                    %>
                                ],
                                label: label_,
                                emphasis: emphasis_,
                            }
                        ],
                    }
                );
                var kidsDiv=echarts.init(document.getElementById("kidsDiv"));
                kidsDiv.setOption(
                    option={
                        title:{
                            text: "童装销售额统计(单位：元)",
                            left: "center",
                        },
                        tooltip: toolTip_,
                        series: [
                            {
                                type: "pie",
                                radius: "80%",
                                data: [
                                    <%
                                        ArrayList<SaleBean> kids_all = (ArrayList<SaleBean>) request.getAttribute("sale_kids");
                                        for (SaleBean saleBean: kids_all) {
                                    %>
                                            {value: <%=saleBean.getSales()%>, name: "<%=ProductBean.categoryMap(saleBean.getCategory())%>"},
                                    <%
                                        }
                                    %>
                                ],
                                label: label_,
                                emphasis: emphasis_,
                            }
                        ],
                    }
                );
            </script>
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
