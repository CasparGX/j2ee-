package com.caspar.j2ee.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caspar on 15-9-28.
 */
@WebFilter(filterName = "AuthenticateFilter2")
public class AuthenticateFilter2 implements Filter {
    String username;

    public void destroy() {
        username = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        //request.getSession().setAttribute("username","admin");
        username = (String) request.getSession().getAttribute("username");
        if (username == null || username.equals("")) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("/error.html");   //跳转页面
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
