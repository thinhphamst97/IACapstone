package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String NOTFOUND = "404.html";
    private final String DASHBOARD = "index.jsp";
    private final String DEPLOY = "DeployServlet";
    private final String LOGIN = "LoginServlet";
    private final String LOGINFORM = "loginForm.html";
    private final String IMAGELIST = "ImageListServlet";
    private final String IMAGEDETAILS = "ImageDetailsServlet";
    private final String UPDATESTATUSIMAGE = "UpdateStatusImageServlet";

    
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        String action = request.getParameter("action");
        if ("".equals(action) || action == null) {
            url = IMAGELIST;
        } else if ("Login".equals(action)) {
            url = LOGIN;
        }else if("Dashboard".equals(action)){
            url = DASHBOARD;
        } else if ("ImageList".equals(action)) {
            url = IMAGELIST;
        } else if ("ImageDetails".equals(action)) {
            url = IMAGEDETAILS;
        } else if ("UpdateStatusImage".equals(action)) {
            url = UPDATESTATUSIMAGE;
        } else if ("Deploy".equals(action)) {
            url = DEPLOY; //redirect to deploy.jsp 
        } else {
            url = NOTFOUND;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
