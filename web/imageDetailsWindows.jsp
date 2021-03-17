<%@page import="java.util.ArrayList"%>
<%@page import="dto.ImageDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ImageDTO image = (ImageDTO)request.getAttribute("image");
		String kernelPath = (String)request.getAttribute("kernelPath");
		String fileSystemPath = (String)request.getAttribute("fileSystemPath");
		ArrayList<String> initrdPathList = (ArrayList<String>)request.getAttribute("initrdPathList");
		if (image != null && kernelPath != null && fileSystemPath != null && initrdPathList != null) {
			out.println("<p>");
			out.println("<label>" + "Image name: " + image.getName() + "</label><br>");
			out.println("<label>" + "Image description: " + image.getDescription() + "</label><br>");
			out.println("<label>" + "wimboot path: " + kernelPath + "</label><br>");
			out.println("<label>" + "boot.wim path: " + fileSystemPath + "</label><br>");
 			out.println("<label>" + "bcd path: " + initrdPathList.get(0) + "</label><br>");
 			out.println("<label>" + "boot.sdi path: " + initrdPathList.get(1) + "</label><br>");
			out.println("</p>");
		} else {
			out.println("ERROR");
		}
	%>
</body>
</html>