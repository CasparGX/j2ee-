package com.caspar.j2ee.Filter;

import net.sf.json.util.JSONStringer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
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
            for (Element e : list) {
                if (e.getName().equals("item")) {
                    if (req.getParameter("username").equals(e.getText())) {
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {
                JSONStringer jsonMsg = new JSONStringer();
                jsonMsg.array();
                jsonMsg.object()
                        .key("result").value("error")
                        .key("msg").value("非法用户")
                        .endObject();

                jsonMsg.endArray();
                resp.getOutputStream().write(jsonMsg.toString().getBytes("UTF-8"));
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
