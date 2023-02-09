function checkName() {  //检查收货人姓名格式
    var res = false;
    var len = $("#name").val().length;
    if (len <= 0)
        $("#nameMsg").text("收货人不可为空");
    else if (len > 20)
        $("#nameMsg").text("收货人姓名过长");
    else {
        $("#nameMsg").text("");
        res = true;
    }
    return res;
}
function checkPhone() {  //检查收货人电话格式
    var res = false;
    var len = $("#phone").val().length;
    if (len !== 11)
        $("#phoneMsg").text("联系电话格式非法");
    else {
        $("#phoneMsg").text("");
        res = true;
    }
    return res;
}
function checkAddress() {  //检查收货地址格式
    var res = false;
    var len = $("#address").val().length;
    if (len < 10)
        $("#addressMsg").text("收货地址过短");
    else if (len > 200)
        $("#addressMsg").text("收货地址过长");
    else {
        $("#addressMsg").text("");
        res = true;
    }
    return res;
}
function checkForm() {  //提交时再次检查以上三个内容
    var res = checkName() && checkPhone() &&checkAddress();
    if (!res)
        alert("请完善收货信息");
    else
        doSubmit();
}
function doSubmit() {  //提交订单
    $.ajax({
        async: true,
        type: "post",
        dataType: 'json',
        url: "/createOrder",
        data: {totalPrice: $("#totalPrice").val(),
               actualPayment: $("#actualPayment").val(),
               name: $("#name").val(),
               phone: $("#phone").val(),
               address: $("#address").val()},
        success: function (data) {
            if (data.flag === "true") {
                window.location.replace("/orderDetail?id=" + data.orderID);
                window.scrollTo(0, document.documentElement.scrollHeight);
            }
            else {
                $("#createOrderMsg").text("订单创建失败");
                $("#createOrderMsg").css("color", "red");
            }
        },
    });
}