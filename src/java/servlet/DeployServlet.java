/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ImageDAO;
import dto.ImageDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThinhPH
 */
@WebServlet("/DeployServlet")
public class DeployServlet extends HttpServlet {

    private final String DEPLOY_SINGLE_OS = "deploysingleos.jsp";
    private final String DEPLOY_MULTIPLE_OS = "deploymultipleos.jsp";
    private final String DEPLOY_OS_WITHIN_CLIENTMAC = "deploywithinclientmac.jsp";

    public DeployServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("option") != null) {
            int option = Integer.valueOf(request.getParameter("option"));
            //option 0: single OS
            //option 1: multiple OS
            //option 2: OS within Client's MAC
            switch (option) {
                case 0:
                    ArrayList imageList = ImageDAO.getAll();
                    request.setAttribute("imageList", imageList);
                    //Nếu request chứ selectImage --> trả về selectImage
                    if (request.getParameter("selectImage") != null && !request.getParameter("selectImage").equalsIgnoreCase("-1")) {
                        //Nếu selectImage == -1 thì nó là dòng text default --> bỏ qua
                        ServletContext context = getServletContext();
                        String imagePath = context.getInitParameter("imagePath");
                        int id = Integer.valueOf(request.getParameter("selectImage"));
                        ImageDTO image = ImageDAO.getImageDetails(id);
                        if (image.getType().equalsIgnoreCase("windows")) {
                            String kernelPath = String.format("%s/wimboot", imagePath);
                            String fileSystemPath = String.format("%s/%s/boot.wim", imagePath, image.getName());
                            ArrayList<String> initrdPathList = new ArrayList<String>();
                            initrdPathList.add(String.format("%s/%s/bcd", imagePath, image.getName()));
                            initrdPathList.add(String.format("%s/%s/boot.sdi", imagePath, image.getName()));
                            request.setAttribute("image", image);
                            request.setAttribute("kernelPath", kernelPath);
                            request.setAttribute("fileSystemPath", fileSystemPath);
                            request.setAttribute("initrdPathList", initrdPathList);
                        } else if (image.getType().equalsIgnoreCase("linux")) {
                            String kernelPath = String.format("%s/%s/vmlinuz", imagePath, image.getName());
                            String fileSystemPath = String.format("%s/%s/%s.img", imagePath, image.getName(), image.getName());
                            ArrayList<String> initrdPathList = new ArrayList<String>();
                            initrdPathList.add(String.format("%s/%s/initrd.img", imagePath, image.getName()));
                            initrdPathList.add(String.format("%s/ltsp.img", imagePath));
                            request.setAttribute("image", image);
                            request.setAttribute("kernelPath", kernelPath);
                            request.setAttribute("fileSystemPath", fileSystemPath);
                            request.setAttribute("initrdPathList", initrdPathList);
                        }
                    }
                    //Nếu request chứa ID Deploy 
                    if (request.getParameter("idDeploy") != null) {
                        int id = Integer.valueOf(request.getParameter("idDeploy"));
                        System.out.println("DEPLOY CAI NAY NE! ID= " + id);
                    }
                    request.getRequestDispatcher(DEPLOY_SINGLE_OS).forward(request, response);
                    break;
                case 1:
                    request.getRequestDispatcher(DEPLOY_MULTIPLE_OS).forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher(DEPLOY_OS_WITHIN_CLIENTMAC).forward(request, response);
                    break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
