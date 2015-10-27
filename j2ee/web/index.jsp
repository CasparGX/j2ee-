<%@ page import="java.io.File" %>
<%@ page import="org.dom4j.Document" %>
<%@ page import="org.dom4j.Element" %>
<%@ page import="java.util.List" %>
<%@ page import="org.dom4j.DocumentException" %>
<%@ page import="org.dom4j.io.SAXReader" %>
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
    <title>j2ee-周恩旭</title>

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
                    <b>J2EE-登录框</b>
                    <span style="float: right;"><a href="template/profile.html" target="_blank">个人信息</a> </span>
                </p>

                <p>测试用户名和密码均为：admin</p>

                <p>默认非法用户为：null和123</p>
            </div>
            <div class="panel-body form-group-info">
                <form action="/index.jsp" method="post">
                    <input class="form-control" type="text" name="username" id="username"
                           placeholder="请输入用户名，测试用户名：admin"/>
                    <input class="form-control" type="password" name="password" id="password"
                           placeholder="请输入密码，测试密码：admin"/>
                    <input class="btn btn-info withripple center-block" style="margin: 10px auto 0px auto;"
                           type="submit"
                           value="submit" id="btn-submit">
                    </input>
                </form>
            </div>

            <%!
                String username;
                String password;
                String illegality;
                String msg = "";
                String msgClass = "";
            %>
            <%
                username = request.getParameter("username");
                password = request.getParameter("password");
                illegality = request.getParameter("illegality");

                if (illegality != null && illegality.equals("1")) {
                    msgClass = "bg-danger";
                    msg = "非法用户";
                } else if (username == null && password == null) {

                } else if (username.equals("") || password.equals("")) {
                    msgClass = "bg-danger";
                    msg = "帐号密码不完整";
                } else if (!username.equals("") && !password.equals("")) {
                    String path = request.getSession().getServletContext().getRealPath("/user.xml");
                    File file = new File(path);
                    try {
                        SAXReader reader = new SAXReader();
                        Document doc = reader.read(file);
                        Element root = doc.getRootElement();
                        List<Element> list = root.elements();
                        boolean flag1 = false;
                        boolean flag2 = false;
                        for (Element e : list) {
                            if (e.getName().equals("allow")) {
                                List<Element> list2 = e.elements();
                                for (Element l : list2) {
                                    if (l.getName().equals("username")) {
                                        if (username.equals(l.getText())) {
                                            flag1 = true;
                                        } else {
                                            flag1 = false;
                                        }
                                    }
                                    if (l.getName().equals("password")) {
                                        if (password.equals(l.getText())) {
                                            flag2 = true;
                                        } else {
                                            flag2 = false;
                                        }
                                    }
                                    if (flag1&&flag2) {
                                        break;
                                    }
                                }

                            }
                        }
                        if (flag1&&flag2) {
                            msgClass = "bg-success";
                            msg = "登录成功,3秒后跳转至详细信息";
                            request.getSession().setAttribute("username", "admin");
                            //response.sendRedirect("./template/profile.html");
                            response.setHeader("refresh", "3;url=./template/welcome.html");
                        } else {
                            msgClass = "bg-danger";
                            msg = "帐号或密码错误，默认用户名和密码均为：admin";
                        }
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
            %>
            <p id="msg" class="<%=msgClass%>" style="text-align: center; padding: 10px 0px;">
                <%=msg%>
            </p>
        </div>
    </div>
</div>
<script src="/js/jquery-2.1.3.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/material.min.js" type="text/javascript"></script>
<script src="/js/ripples.min.js" type="text/javascript"></script>
<script type="text/javascript">
    /*$('#btn-submit').click(function () {
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
     );*/

</script>
</body>
</html>
