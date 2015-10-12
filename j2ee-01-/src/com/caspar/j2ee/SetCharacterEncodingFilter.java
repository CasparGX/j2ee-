package com.caspar.j2ee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caspar on 15-9-20.
 */
@WebFilter(filterName = "SetCharacterEncodingFilter")
public class SetCharacterEncodingFilter implements Filter {
    private String encoding;
    private Map<String, String> params = new HashMap<String, String>();
    public void destroy() {
        params=null;
        encoding=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        for (Enumeration e = config.getInitParameterNames(); e
                .hasMoreElements();) {
            String name = (String) e.nextElement();
            String value = config.getInitParameter(name);
            params.put(name, value);
        }
    }

}
