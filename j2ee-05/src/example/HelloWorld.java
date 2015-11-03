package example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.File;
import java.util.List;

/**
 * Created by caspar on 15-11-3.
 */
@WebService()
public class HelloWorld {
  @WebMethod
  public String sayHelloWorldFrom(String username,String password) {
    String result = null;
    String path = "user.xml";
    File file = new File(path);
    try {
      SAXReader reader = new SAXReader();
      Document doc = reader.read(file);
      Element root = doc.getRootElement();
      List<Element> list = root.elements();
      boolean flag1 = false;
      boolean flag2 = false;
      boolean flag3 = false;
      for (Element e : list) {
        List<Element> list2 = e.elements();
        if (e.getName().equals("ban")) {
          list2 = e.elements();
          for (Element l : list2) {
            if (l.getName().equals("username2")) {
              if (username.equals(l.getText())) {
                flag3 = true;
                break;
              } else {
                flag3 = false;
              }
            }
          }
        }

        if (flag3) break;

        if (e.getName().equals("allow")) {
          list2 = e.elements();
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
      if (flag3) {
        result = "ban";
      }else if (flag1&&flag2) {
        result = "true";
      } else {
        result = "false";
      }
    } catch (DocumentException e) {
      e.printStackTrace();
    }

    return result;
  }
  public static void main(String[] argv) {
    Object implementor = new HelloWorld ();
    String address = "http://localhost:9000/HelloWorld";
    Endpoint.publish(address, implementor);
  }
}
