function userLogin() {
    var userName = $("#userName").val();
    var userPwd = $("#userPwd").val();
    if(isEmpty(userName)){
        alert("用户名为空");
        return;
    }
    if(isEmpty(userPwd)){
        alert("密码为空");
        return;
    }
    //封装参数
    var params = {};
    params.userName = userName;
    params.userPwd = userPwd;
    $.ajax({
        type:"post",
        url:ctx+"/user/userLogin",
        data:params,
        dataType:"json",
        success:function (data) {
            //这里的data是后台传回来的数据
            if(data.code=200){
                //成功状态码200，页面跳转

            }else {
                alert("登陆失败");
            }
        }
    })
}