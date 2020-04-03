package org.test;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@WebServlet("/")
public class TestServlet implements Servlet {

    public void init(ServletConfig config) throws ServletException {
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        System.out.println(httpServletRequest.getRequestURI());

        if (httpServletRequest.getRequestURI().contains("testBootstrap.js")) {
            res.setContentType("text/javascript");
            writeResource(req, out, "/WEB-INF/testBootstrap.js");
            return;
        }
        if (httpServletRequest.getRequestURI().contains("styles.css")) {
            res.setContentType("text/css");
            writeResource(req, out, "/WEB-INF/styles.css");
            return;
        }
        if (httpServletRequest.getRequestURI().contains("test.xml")) {
            res.setContentType("text/xml");
            writeResource(req, out, "/WEB-INF/test.xml");
            return;
        }
        res.setContentType("text/html");
        // write application
        out.print("<h3>Hello There...!</h3>");
        out.print("<script type=\"text/javascript\" src=\"testBootstrap.js\"></script>");
        out.print("<button type=\"button\" onclick=\"loadXMLDoc()\">Do XHR</button>");
        out.print("<button type=\"button\" onclick=\"tryRedirect()\">Try redirect</button>");
        out.print("<table id=\"demo\"></table>");
        out.print("<script>");
        out.print("var loadXMLDoc = function () {");
        out.print("        var xmlhttp = new XMLHttpRequest();");
        out.print("        xmlhttp.onreadystatechange = function () {");
        out.print("            if (this.readyState == 4 && this.status == 200) {");
        out.print("                myFunction(this);");
        out.print("            }");
        out.print("        };");
        out.print("        xmlhttp.open(\"GET\", \"test.xml\", true);");
        out.print("        xmlhttp.send();");
        out.print("    };");
        out.print("    var myFunction = function (xml) {");
        out.print("        var i;");
        out.print("        var xmlDoc = xml.responseXML;");
        out.print("        console.log(xmlDoc);");
        out.print("        var table = \"<tr><th>TITLE</th><th>YEAR</th></tr>\";");
        out.print("        var x = xmlDoc.getElementsByTagName(\"CD\");");
        out.print("        for (i = 0; i < x.length; i++) {");
        out.print("            table += \"<tr><td>\" + x[i].getElementsByTagName(\"TITLE\")[0].childNodes[0].nodeValue + \"</td><td>\" + x[i].getElementsByTagName(\"YEAR\")[0].childNodes[0].nodeValue + \"</td></tr>\";");
        out.print("        }");
        out.print("        document.getElementById(\"demo\").innerHTML = table;");
        out.print("    };");
        out.print("    var tryRedirect = function () {window.open(\"/servelt-xhr-test/styles.css\", \"_self\");}");
        out.print("</script>");
    }

    private void writeResource(ServletRequest req, PrintWriter out, String s) throws IOException {
        ServletContext context = req.getServletContext();
        InputStream is = context.getResourceAsStream(s);
        InputStreamReader isReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        out.print(sb.toString());
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
    }
}
