package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<br/>\n");
      out.write("<div class=\"container-center\" data-ng-controller=\"loginController\">\n");
      out.write("\t<form class=\"well\" name='f' action='/academy/j_spring_security_check'\n");
      out.write("\t\tmethod='POST'>\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t<div class=\"col-sm-6\">\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"form-control input-group-lg\"\n");
      out.write("\t\t\t\t\t\tname=\"j_username\" placeholder=\"User Name\" data-ng-required=\"true\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t<br /> <br />\n");
      out.write("\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t<div class=\"col-sm-6\">\n");
      out.write("\t\t\t\t\t<input type=\"password\" class=\"form-control input-group-lg\"\n");
      out.write("\t\t\t\t\t\tname=\"j_password\" placeholder=\"Password*\" data-ng-required=\"true\">\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<br /> <br />\n");
      out.write("\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t<div class=\"col-sm-6\">\n");
      out.write("\t\t\t\t\t<button name=\"submit\" type=\"submit\" class=\"btn btn-info\"\n");
      out.write("\t\t\t\t\t\tdata-ng-disabled=\"f.$invalid\">Submit</button>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</form>\n");
      out.write("</div>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
