package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class imageDetailsWindows_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=windows-1252");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Add Image</title>\r\n");
      out.write("        <!-- Required meta tags -->\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n");
      out.write("        <!--     Fonts and icons     -->\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons\" />\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css\">\r\n");
      out.write("        <!-- Material Kit CSS -->\r\n");
      out.write("        <link href=\"assets/css/material-dashboard.css?v=2.1.2\" rel=\"stylesheet\" />\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"wrapper \">\r\n");
      out.write("            <div class=\"sidebar\" data-color=\"purple\" data-background-color=\"white\">\r\n");
      out.write("                <!--\r\n");
      out.write("                Tip 1: You can change the color of the sidebar using: data-color=\"purple | azure | green | orange | danger\"\r\n");
      out.write("          \r\n");
      out.write("                Tip 2: you can also add an image using data-image tag\r\n");
      out.write("                -->\r\n");
      out.write("                <div class=\"logo\">\r\n");
      out.write("                    <a class=\"simple-text logo-mini\">\r\n");
      out.write("                        GPS21IA01 \r\n");
      out.write("                    </a>\r\n");
      out.write("                    <a class=\"simple-text logo-normal\">\r\n");
      out.write("                        SP21IA05\r\n");
      out.write("                    </a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"sidebar-wrapper\">\r\n");
      out.write("                    <ul class=\"nav\">\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <a class=\"nav-link\" href=\"MainServlet?action=Dashboard\">\r\n");
      out.write("                                <i class=\"material-icons\">dashboard</i>\r\n");
      out.write("                                <p>Dashboard</p>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <!-- your sidebar here -->\r\n");
      out.write("                        <li class=\"nav-item active\">\r\n");
      out.write("                            <a class=\"nav-link\" href=\"MainServlet?action=ImageList\">\r\n");
      out.write("                                <i class=\"material-icons\">image</i>\r\n");
      out.write("                                <p>Image List</p>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item\">\r\n");
      out.write("                            <a class=\"nav-link\" href=\"MainServlet?action=Deploy\">\r\n");
      out.write("                                <i class=\"material-icons\">download_for_offline</i>\r\n");
      out.write("                                <p>Deploy</p>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"main-panel\">\r\n");
      out.write("                <!-- Navbar -->\r\n");
      out.write("                <nav class=\"navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top \">\r\n");
      out.write("                    <div class=\"container-fluid\">\r\n");
      out.write("                        <div class=\"navbar-wrapper\">\r\n");
      out.write("                            <a class=\"navbar-brand\" href=\"javascript:;\">Dashboard</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" aria-controls=\"navigation-index\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("                            <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("                            <span class=\"navbar-toggler-icon icon-bar\"></span>\r\n");
      out.write("                            <span class=\"navbar-toggler-icon icon-bar\"></span>\r\n");
      out.write("                            <span class=\"navbar-toggler-icon icon-bar\"></span>\r\n");
      out.write("                        </button>\r\n");
      out.write("                        <div class=\"collapse navbar-collapse justify-content-end\">\r\n");
      out.write("                            <ul class=\"navbar-nav\">\r\n");
      out.write("                                <li class=\"nav-item\">\r\n");
      out.write("                                    <a class=\"nav-link\" href=\"javascript:;\">\r\n");
      out.write("                                        <i class=\"material-icons\">notifications</i> Notifications\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                </li>\r\n");
      out.write("                                <!-- your navbar here -->\r\n");
      out.write("                            </ul>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </nav>\r\n");
      out.write("                <!-- End Navbar -->\r\n");
      out.write("                <div class=\"content\">\r\n");
      out.write("                    <div class=\"container-fluid\">\r\n");
      out.write("                        <div class=\"card\">\r\n");
      out.write("                                <div class=\"card-header card-header-primary\">\r\n");
      out.write("                                    <form>\r\n");
      out.write("                                        <h4 class=\"card-title \">Choose an OS: </h4>\r\n");
      out.write("                                        <select name=\"selectedOS\" id=\"selectOS\">\r\n");
      out.write("                                    </select>\r\n");
      out.write("                                    <button type=\"submit\" class=\"btn btn-sm btn-outline-light\" value=\"submit\">OK</button>\r\n");
      out.write("                                </form>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"card-body\">\r\n");
      out.write("                                        <div class=\"col-md-3\">\r\n");
      out.write("                                            <div>\r\n");
      out.write("                                                <label for=\"name\" style=\"color: black\">OS Name:</label>\r\n");
      out.write("                                                <input type=\"text\" id=\"name\" class=\"form-control\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div>\r\n");
      out.write("                                                <label for=\"kernelFile\" style=\"color: black\" class=\"form-label\">Kernel File:</label>\r\n");
      out.write("                                                <input type=\"file\" id=\"kernelFile\" style=\"padding-left: 40px\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div>\r\n");
      out.write("                                                <label for=\"bcdFile\" style=\"color: black\">BCD File:</label>\r\n");
      out.write("                                                <input type=\"file\" id=\"bcdFile\" class=\"input\" style=\"padding-left: 40px\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div>\r\n");
      out.write("                                                <label for=\"bootSdiFile\" style=\"color: black\">Boot.sdi File:</label>\r\n");
      out.write("                                                <input type=\"file\" id=\"bootSdiFile\" class=\"input\" style=\"padding-left: 40px\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                            <div>\r\n");
      out.write("                                                <label for=\"bootWimFile\" style=\"color: black\">Boot.wim File:</label>\r\n");
      out.write("                                                <input type=\"file\" id=\"bootWimFile\" class=\"input\" style=\"padding-left: 40px\">\r\n");
      out.write("                                            </div>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                        <div class=\"col-md-auto\">\r\n");
      out.write("                                            <a href=\"MainServlet?action=Image\"><button type=\"submit\" class=\"btn btn-primary pull-right\">Add Image</button></a>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <footer class=\"footer\">\r\n");
      out.write("                <div class=\"container-fluid\">\r\n");
      out.write("                    <nav class=\"float-left\">\r\n");
      out.write("                        <ul>\r\n");
      out.write("                            <li>\r\n");
      out.write("                                <a href=\"https://www.creative-tim.com\">\r\n");
      out.write("                                    ThinhPH\r\n");
      out.write("                                </a>\r\n");
      out.write("                            </li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </nav>\r\n");
      out.write("                    <div class=\"copyright float-right\">\r\n");
      out.write("                        &copy;\r\n");
      out.write("                        <script>\r\n");
      out.write("                            document.write(new Date().getFullYear())\r\n");
      out.write("                        </script>, made with <i class=\"material-icons\">favorite</i> by GPS21IA01.\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!-- your footer here -->\r\n");
      out.write("                </div>\r\n");
      out.write("            </footer>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
