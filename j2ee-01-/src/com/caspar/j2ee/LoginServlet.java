package com.caspar.j2ee;

import net.sf.json.util.JSONStringer;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
/**
 * Created by root on 15-9-17.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //response.setContentType("application/json");
        //esponse.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        JSONStringer jsonMsg = new JSONStringer();
        jsonMsg.array();

        if (username.equals("") || password.equals("")) {

            jsonMsg.object()
                    .key("result").value("error")
                    .key("msg").value("帐号密码不完整")
                    .endObject();
        } else if (username.equals("admin") && password.equals("admin")){

            jsonMsg.object()
                    .key("result").value("success")
                    .key("msg").value("登录成功，测试状态不进行页面跳转")
                    .endObject();
        } else {

            jsonMsg.object()
                    .key("result").value("error")
                    .key("msg").value("帐号或密码错误，默认用户名和密码均为：admin")
                    .endObject();
        }


        jsonMsg.endArray();

        response.getOutputStream().write(jsonMsg.toString().getBytes("UTF-8"));
        response.setContentType("application/json;charset=UTF-8");





    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
