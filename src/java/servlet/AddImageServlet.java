package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ImageDAO;
import dao.InitrdDAO;
import dao.KernelDAO;
import utils.Utils;

@MultipartConfig
@WebServlet("/AddImageServlet")
public class AddImageServlet extends HttpServlet {

    private final long serialVersionUID = 1L;
    //private final String UPLOAD_DIRECTORY = "upload";
    private final String PAGE = "addimage.jsp";

    public AddImageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        float size = 0;
    	int addResult;
        String imageName = request.getParameter("imageName");
        String imageDescription = request.getParameter("imageDescription");
        
        //FolderName will not conflict anymore
        String imageFolderName = request.getParameter("imageFolderName");
        
        String imagePath = getServletContext().getInitParameter("imagePath");
        String httpPath = getServletContext().getInitParameter("httpPath");
        String finalImagePath = imagePath + File.separator + imageName;
        String tempImagePath = httpPath + File.separator + "tmp";
        
        if (imageName == null || imageName.equals("")) {
        	request.setAttribute("result", "Image name cannot be empty");
            forward(PAGE, request, response);
        }
        String type = request.getParameter("type");
        if ("windows".equalsIgnoreCase(type)) {
            File finalImageDir = new File(finalImagePath);
            if (!finalImageDir.exists()) {
                finalImageDir.mkdir();
            } else {
            	request.setAttribute("result", "Image name is not available");
                forward(PAGE, request, response);
            }
            
        	int wimbootId = Integer.parseInt(getServletContext().getInitParameter("wimbootId"));
        	//Copy files
            String bcdPath = request.getParameter("bcdPath");
            String bootSdiPath = request.getParameter("bootSdiPath");
            String bootWimPath = request.getParameter("bootWimPath");
            if (bcdPath.equals("") || !Files.exists(Paths.get(bcdPath))) {
                request.setAttribute("result", "bcd path is invalid");
                finalImageDir.delete();
                forward(PAGE, request, response);
            }
            if (bootSdiPath.equals("") || !Files.exists(Paths.get(bootSdiPath))) {
                request.setAttribute("result", "boot.sdi path is invalid");
                finalImageDir.delete();
                forward(PAGE, request, response);
            }
            if (bootWimPath.equals("") || !Files.exists(Paths.get(bootWimPath))) {
                request.setAttribute("result", "boot.wim path is invalid");
                finalImageDir.delete();
                forward(PAGE, request, response);
            }
            Files.copy(Paths.get(bcdPath), Paths.get(finalImagePath + File.separator + "bcd"));
            Files.copy(Paths.get(bootSdiPath), Paths.get(finalImagePath + File.separator + "boot.sdi"));
            Files.copy(Paths.get(bootWimPath), Paths.get(finalImagePath + File.separator + "boot.wim"));
            
            //Calculate image size
            String[] paths = new String[4];
            paths[0] = imagePath + File.separator + "wimboot";
            paths[1] = finalImagePath + File.separator + "bcd";
            paths[2] = finalImagePath + File.separator + "boot.sdi";
            paths[3] = finalImagePath + File.separator + "boot.wim";
            size = Utils.getImageSize(paths);
            
            //Add information to database
            addResult = ImageDAO.addImage(imageName, "windows", size, imageDescription, false, Date.valueOf(LocalDate.now()), wimbootId);
            if (addResult != 1) {
                request.setAttribute("result", "Fail to add image");
                forward(PAGE, request, response);
            }
        } else if ("linux".equalsIgnoreCase(type)) {
        	String kernelName = request.getParameter("kernelName");
        	String logFilePath = getServletContext().getInitParameter("logDir") + File.separator + imageName + ".log";
        	String linkFilePath = tempImagePath + File.separator + imageName + ".img";
        	if (kernelName == null || kernelName.equals("")) {
                request.setAttribute("result", "Kernel name cannot be empty");
                forward(PAGE, request, response);
        	}
            
            String vmdkPath = request.getParameter("vmdkPath");
            if (vmdkPath.equals("") || !Files.exists(Paths.get(vmdkPath))) {
                request.setAttribute("result", "VMDK path is invalid");
                forward(PAGE, request, response);
            }
            Files.createSymbolicLink(Paths.get(linkFilePath), Paths.get(vmdkPath));
            
            //Execute ltsp commands
            String[] cmdArray = new String[]{"/bin/sh", "-c", "ltsp image " + tempImagePath + File.separator + imageName + ".img"};//new String[]{"ltsp", "image", };
            String output = Utils.executeCommand(cmdArray);
            Files.writeString(Paths.get(logFilePath), output, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            if (output.contains("To update the iPXE menu, run: ltsp ipxe")) {
            	//Successful
            	String ltspImageDirPath = "/srv/tftp/ltsp/" + imageName;
            	String ltspInitrdPath = ltspImageDirPath + File.pathSeparator + "initrd.img";
            	String ltspVmlinuzPath = ltspImageDirPath + File.pathSeparator + "vmlinuz";
            	if (Files.exists(Paths.get(ltspImageDirPath)) && Files.isDirectory(Paths.get(ltspImageDirPath))) {
            		if (Files.exists(Paths.get(ltspInitrdPath)) && !Files.isDirectory(Paths.get(ltspInitrdPath))) {
                		if (Files.exists(Paths.get(ltspVmlinuzPath)) && !Files.isDirectory(Paths.get(ltspVmlinuzPath))) {
                			//Move full image directory (contains initrd.img and vmlinuz) to /var/www/html/ltsp/image/
                			Files.move(Paths.get(ltspImageDirPath), Paths.get(finalImagePath));

                            //Calculate image size
                            String[] paths = new String[4];
                            paths[0] = imagePath + File.separator + "ltsp.img";
                            paths[1] = finalImagePath + File.separator + "vmlinuz";
                            paths[2] = finalImagePath + File.separator + "initrd.img";
                            paths[3] = "/srv/ltsp/images/" + imageName + ".img";
                            size = Utils.getImageSize(paths);
                            
                            //Add information to database
                            addResult = KernelDAO.addKernel(kernelName);
                            if (addResult != 1) {
                                request.setAttribute("result", "Fail to add kernel");
                                forward(PAGE, request, response);
                            }
                            addResult = ImageDAO.addImage(imageName, "linux", size, imageDescription, false, Date.valueOf(LocalDate.now()), KernelDAO.getHighestId());
                            if (addResult != 1) {
                                request.setAttribute("result", "Fail to add image");
                                forward(PAGE, request, response);
                            }
                		} else {
                        	Files.delete(Paths.get(linkFilePath));
                            request.setAttribute("result", "File " + ltspVmlinuzPath + " does not exist");
                            forward(PAGE, request, response);
                		}
            		} else {
                    	Files.delete(Paths.get(linkFilePath));
                        request.setAttribute("result", "File " + ltspInitrdPath + " does not exist");
                        forward(PAGE, request, response);
            		}
            	} else {
                	Files.delete(Paths.get(linkFilePath));
                    request.setAttribute("result", "Directory " + ltspImageDirPath + " does not exist");
                    forward(PAGE, request, response);
            	}
            } else {
            	//Fail
                //Remove link file
            	//Files.delete(Paths.get(linkFilePath));
                request.setAttribute("result", "Failed to run command \"ltsp image\". Read the log file at " + logFilePath);
                System.out.println(output);
                System.out.println(cmdArray[0]);
                System.out.println(cmdArray[1]);
                System.out.println(cmdArray[2]);
                forward(PAGE, request, response);
            }
        } else {
            request.setAttribute("result", "Unknown image type");
            forward(PAGE, request, response);
        }
        request.setAttribute("result", "true");
        forward(PAGE, request, response);
    }
    
    private void forward(String PAGE, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        request.getRequestDispatcher(PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
