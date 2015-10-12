<%--
  Created by IntelliJ IDEA.
  User: Caspar(周恩旭)   2013960837
  Date: 15-9-17
  Time: 上午12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>j2ee-02</title>

    <link href="/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/css/roboto.min.css" type="text/css" rel="stylesheet"/>
    <link href="/css/material.min.css" type="text/css" rel="stylesheet"/>
    <link href="/css/ripples.min.css" type="text/css" rel="stylesheet"/>

    <style>
        .blank {
            height: 100px;
        }
    </style>
</head>
<body>
<div class="blank"></div>
<div class="col-md-4 col-md-offset-4">
    <div class="bs-component">
        <div class="panel panel-default">
            <div class="panel-heading">
                <p>
                    <b>J2EE-02 登录框</b>
                    <span style="float: right;"><a href="template/profile.html" target="_blank">个人信息</a> </span>
                </p>
                <p>测试用户名和密码均为：admin</p>
                <p>默认非法用户为：null和123</p>
            </div>
            <div class="panel-body form-group-info">
                <input class="form-control" type="text" name="username" id="username" placeholder="请输入用户名，测试用户名：admin"/>
                <input class="form-control" type="password" name="password" id="password"
                       placeholder="请输入密码，测试密码：admin"/>
                <button class="btn btn-info withripple center-block" style="margin: 10px auto 0px auto;" type="button"
                        value="submit" id="btn-submit">submit
                </button>
            </div>
            <p id="msg" class="" style="text-align: center; padding: 10px 0px;">

            </p>
        </div>
    </div>
</div>
<script src="/js/jquery-2.1.3.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/material.min.js" type="text/javascript"></script>
<script src="/js/ripples.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $('#btn-submit').click(function () {
                $.ajax({
                    url: "/servlet/index",
                    type: "post",
                    data: {"username": $('#username').val(), "password": $('#password').val()},
                    dataType: "json",
                    success: function (data) {
                        console.log(data[0]);
                        if (data[0].result == "error") {
                            $('#msg').removeClass();
                            $('#msg').addClass("bg-warning");
                            $('#msg').html(data[0].msg);
                        } else {
                            $('#msg').removeClass();
                            $('#msg').addClass("bg-success");
                            $('#msg').html("登录成功，3秒后自动跳转");
                            setTimeout(function () {
                                location.href = "template/welcome.html"
                            }, 3000);
                        }
                    },
                    error: function (a, b, c) {
                        console.log(a);
                        console.log(b);
                        console.log(c);
                    }
                });
            }
    );

</script>
</body>
</html>
