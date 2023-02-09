function validateEmail() {  //email格式合法性检验，并检查该email是否已被绑定
    var res = false;
    //采用正则的方式进行格式检验
    var regex = /[a-zA-Z0-9]+([-_.][A-Za-zd]+)*@([a-zA-Z0-9]+[-.])+[A-Za-zd]{2,5}$/;
    if (!regex.test($("#email").val())) {
        $("#emailMsg").text("邮箱格式不合法");
        $("#emailMsg").css("color", "red");
        return false;
    }
    if ($("#email").val().length > 50) {
        $("#emailMsg").text("邮箱太长");
        $("#emailMsg").css("color", "red");
        return false;
    }
    $.ajax({
        async: false,
        type: "post",
        url: "/validateEmail",  //让validateEmailServlet进行判重
        data: {email: $("#email").val()},
        success: function (data) {
            $("#emailMsg").text(data);
            if (data.trim() === "邮箱可用") {  //邮箱可用
                $("#emailMsg").css("color", "green");
                res = true;
            }
            else  //邮箱已被注册
                $("#emailMsg").css("color", "red");
        },
    });
    return res;
}
function validateName() {  //用户名格式合法性检验，并检查该用户名是否已被占用
    var res = false;
    if ($("#name").val().length > 20) {
        $("#nameMsg").text("用户名太长");
        $("#nameMsg").css("color", "red")
        return false;
    }
    $.ajax({
        async: false,
        type: "post",
        url: "/validateName",  //让validateNameServlet判断是否已被占用
        data: {name: $("#name").val()},
        success: function (data) {
            $("#nameMsg").text(data);
            if (data.trim() === "用户名可用") {  //用户名未被占用，可用
                $("#nameMsg").css("color", "green");
                res = true;
            }
            else  //用户名已被占用
                $("#nameMsg").css("color", "red");
        },
    });
    return res;
}
function validatePassword() {
    var res = false;
    var pwd = $("#password").val();
    var pwd_again = $("#password_again").val();
    $("#passwordMsg").css("color", "red");
    if (pwd === "")
        $("#passwordMsg").text("密码不能为空");
    else if (pwd.length < 10)
        $("#passwordMsg").text("密码太短");
    else if (pwd.length > 30)
        $("#passwordMsg").text("密码太长");
    else if (pwd_again === "")
        $("#passwordMsg").text("确认密码不能为空");
    else if (pwd !== pwd_again)
        $("#passwordMsg").text("密码与确认密码不一致");
    else {
        $("#passwordMsg").text("密码一致");
        $("#passwordMsg").css("color", "green");
        res = true;
    }
    return res;
}
function checkForm() {
    var res = validateEmail() && validateName() && validatePassword();
    if(!res)
        alert("请完善信息");
    return res;
}