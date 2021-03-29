package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ImageDAO;
import dto.ImageDTO;
import utils.Utils;

@WebServlet("/ImageDetailsServlet")
public class ImageDetailsServlet extends HttpServlet {

    private final long serialVersionUID = 1L;
    private final String PAGE = "receivehashexample.jsp";

    public ImageDetailsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        String imagePath = context.getInitParameter("imagePath");
        if (request.getParameter("id") != null) {
            int id;
            try {
                id = Integer.valueOf(request.getParameter("id"));
                ImageDTO image = ImageDAO.getImageDetails(id);
                if (image.getType().equalsIgnoreCase("windows")) {
                    String kernelPath = String.format("%s/wimboot", imagePath);
                    String fileSystemPath = String.format("%s/%s/boot.wim", imagePath, image.getName());
                    ArrayList<String> initrdPathList = new ArrayList<String>();
                    ArrayList<String> initrdMd5List = new ArrayList<String>();
                    String temp;
                    temp = String.format("%s/%s/bcd", imagePath, image.getName());
                    initrdPathList.add(temp);
                    initrdMd5List.add(Utils.getMd5OfFile(temp));
                    temp = String.format("%s/%s/boot.sdi", imagePath, image.getName());
                    initrdPathList.add(temp);
                    initrdMd5List.add(Utils.getMd5OfFile(temp));
                    request.setAttribute("image", image);
                    request.setAttribute("kernelPath", kernelPath);
                    request.setAttribute("kernelMd5", Utils.getMd5OfFile(kernelPath));
                    request.setAttribute("fileSystemPath", fileSystemPath);
                    request.setAttribute("fileSystemMd5", Utils.getMd5OfFile(fileSystemPath));
                    request.setAttribute("initrdPathList", initrdPathList);
                    request.setAttribute("initrdMd5List", initrdMd5List);
                    request.getRequestDispatcher(PAGE).forward(request, response);
                } else if (image.getType().equalsIgnoreCase("linux")) {
                    String kernelPath = String.format("%s/%s/vmlinuz", imagePath, image.getName());
                    String fileSystemPath = String.format("%s/%s/%s.img", imagePath, image.getName(), image.getName());
                    ArrayList<String> initrdPathList = new ArrayList<String>();
                    ArrayList<String> initrdMd5List = new ArrayList<String>();
                    String temp;
                    temp = String.format("%s/%s/initrd.img", imagePath, image.getName());
                    initrdPathList.add(temp);
                    initrdMd5List.add(Utils.getMd5OfFile(temp));
                    temp = String.format("%s/ltsp.img", imagePath);
                    initrdPathList.add(temp);
                    initrdMd5List.add(Utils.getMd5OfFile(temp));
                    request.setAttribute("image", image);
                    request.setAttribute("kernelPath", kernelPath);
                    request.setAttribute("kernelMd5", Utils.getMd5OfFile(kernelPath));
                    request.setAttribute("fileSystemPath", fileSystemPath);
                    request.setAttribute("fileSystemMd5", Utils.getMd5OfFile(fileSystemPath));
                    request.setAttribute("initrdPathList", initrdPathList);
                    request.setAttribute("initrdMd5List", initrdMd5List);
                    request.getRequestDispatcher(PAGE).forward(request, response);
                } else {
                    out.println("Unknown image type");
                    out.close();
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(out);
                out.close();
                return;
            }
            //call ImageDAO
            out.println(imagePath);
            out.println(id);
        } else {
            out.println("Need to specify image id: id=...");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
