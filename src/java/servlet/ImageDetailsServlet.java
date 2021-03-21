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

@WebServlet("/ImageDetailsServlet")
public class ImageDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "imageDetails.jsp";

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
					initrdPathList.add(String.format("%s/%s/bcd", imagePath, image.getName()));
					initrdPathList.add(String.format("%s/%s/boot.sdi", imagePath, image.getName()));
					request.setAttribute("image", image);
					request.setAttribute("kernelPath", kernelPath);
					request.setAttribute("fileSystemPath", fileSystemPath);
					request.setAttribute("initrdPathList", initrdPathList);
					request.getRequestDispatcher(PAGE).forward(request, response);
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
