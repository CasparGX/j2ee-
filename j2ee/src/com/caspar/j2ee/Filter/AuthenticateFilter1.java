package com.caspar.j2ee.Filter;

import net.sf.json.util.JSONStringer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by caspar on 15-9-28.
 */
@WebFilter(filterName = "AuthenticateFilter1")
public class AuthenticateFilter1 implements Filter {
    SAXReader reader = new SAXReader();
    String path = null;
    File file = null;
    boolean flag = false;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        path = request.getSession().getServletContext().getRealPath("/user.xml");
        file = new File(path);
        try {
            Document doc = reader.read(file);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            flag = false;
            if (req.getParameter("username") != null) {
                for (Element e : list) {
                    if (e.getName().equals("ban")) {
                        List<Element> list2 = e.elements();
                        for (Element l : list2) {
                            if (l.getName().equals("username2")) {
                                if (req.getParameter("username").equals(l.getText())) {
                                    flag = true;
                                    break;
                                }
                            }
                        }

                    }
                }
            }
            if (flag) {
                HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect("/index.jsp?illegality=1");   //跳转页面
            } else {
                chain.doFilter(req, resp);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
