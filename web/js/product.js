function doRemove() {  //下架商品
    var cfm = window.confirm("确定要下架该商品? 此操作不可逆")
    if (!cfm)
        return;
    $.ajax({
        async: true,
        type: "post",
        url: "/removeProduct",
        data: {productID: location.search.split("=")[1]},
        success: function (data) {
            if(data.trim() === "true") {
                $("#remove").attr("disabled", "true");
                alert("下架成功");
            }
            else if (data.trim() === "pin") {
                alert("此商品为固定在售商品，不可下架！");
            }
            else {
                alert("下架失败");
            }
        },
    });
}

function doChangeAmount() {  //修改商品库存数
    var amount;
    amount = prompt("库存修改为：");
    if (!(/^\d+$/.test(amount))) {  //使用正则判断输入的数据是否为非负整数
        alert("请输入非负整数!");
        return;
    }
    $.ajax({
        async: true,
        type: "post",
        url: "/editProductAmount",
        data: {productID: location.search.split("=")[1],
               amount: amount},
        success: function (data) {
            if (data.trim() === "true") {
                alert("修改成功");
                window.location.reload();
            }
            else
                alert("修改失败");
        },
    });
}

function addCart() {  //加入购物车
    $.ajax({
        async: true,
        type: "post",
        url: "/addCart",
        data: {productID: location.search.split("=")[1]},
        success: function (data) {
            if (data.trim() === "true")
                window.location.reload();
            else if (data.trim() === "nologin")
                window.location.href = "/login.jsp";
        }
    })
}

function buyNow() {  //立即购买
    $.ajax({
        async: true,
        type: "post",
        url: "/addCart",
        data: {productID: location.search.split("=")[1]},
        success: function (data) {
            if (data.trim() === "true")
                window.location.href = "/cart.jsp";
            else if (data.trim() === "nologin")
                window.location.href = "/login.jsp";
        }
    })
}