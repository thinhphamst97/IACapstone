package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/AddImageServlet")
public class AddImageServlet extends HttpServlet {

    private final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "upload";

    public AddImageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String imageName = request.getParameter("imageName");
        String imagePath = getServletContext().getInitParameter("imagePath");
        String finalImagePath = imagePath + File.separator + imageName;
        String tempImagePath = imagePath + File.separator + "tmp";
        File finalImageDir = new File(finalImagePath);
        if (!finalImageDir.exists()) {
            finalImageDir.mkdir();
        } else {
            out.println("Image name is not available");
            return;
        }
        String type = request.getParameter("type");
        if ("windows".equalsIgnoreCase(type)) {
            String wimbootPath = request.getParameter("wimbootPath");
            String bcdPath = request.getParameter("bcdPath");
            String bootSdiPath = request.getParameter("bootSdiPath");
            String bootWimPath = request.getParameter("bootWimPath");
            if (wimbootPath.equals("") || !Files.exists(Paths.get(wimbootPath))) {
                out.println("wimboot path is invalid");
                finalImageDir.delete();
                return;
            }
            if (bcdPath.equals("") || !Files.exists(Paths.get(bcdPath))) {
                out.println("bcd path is invalid");
                finalImageDir.delete();
                return;
            }
            if (bootSdiPath.equals("") || !Files.exists(Paths.get(bootSdiPath))) {
                out.println("boot.sdi path is invalid");
                finalImageDir.delete();
                return;
            }
            if (bootWimPath.equals("") || !Files.exists(Paths.get(bootWimPath))) {
                out.println("boot.wim path is invalid");
                finalImageDir.delete();
                return;
            }
            Files.copy(Paths.get(wimbootPath), Paths.get(finalImagePath + File.separator + "wimboot"));
            Files.copy(Paths.get(bcdPath), Paths.get(finalImagePath + File.separator + "bcd"));
            Files.copy(Paths.get(bootSdiPath), Paths.get(finalImagePath + File.separator + "boot.sdi"));
            Files.copy(Paths.get(bootWimPath), Paths.get(finalImagePath + File.separator + "boot.wim"));
        } else if ("linux".equalsIgnoreCase(type)) {
            out.println("Not implemented yet");
            finalImageDir.delete();

            String vmdkPath = request.getParameter("vmdkPath");
            if (vmdkPath.equals("") || !Files.exists(Paths.get(vmdkPath))) {
                out.println("vmdk path is invalid");
                finalImageDir.delete();
                return;
            }
            Files.createSymbolicLink(Paths.get(tempImagePath + File.separator + imageName + ".img"), Paths.get(vmdkPath));

            //Remove temp file
            return;
        } else {
            out.println("Unknown image type");
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
