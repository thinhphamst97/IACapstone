<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Windows -->
	<form method="post" action="AddImageServlet">
		<h4>Windows</h4>
		<label>Image name: </label>
		<input type="text" name="imageName"/> <br>
		<label>Wimboot path: </label>
		<input type="text" name="wimbootPath" /> <br>
		<label>BCD path: </label>
		<input type="text" name="bcdPath" /> <br>
		<label>Boot.sdi path: </label>
		<input type="text" name="bootSdiPath" />  <br>
		<label>Boot.wim path: </label>
		<input type="text" name="bootWimPath" /> <input type="submit" value="Add Image" />
		<input type="hidden" name="type" value="windows"/>
	</form>
	<br>---------------------------------------------------------------------<br>
	<!-- Linux -->
	<form method="post" action="AddImageServlet" enctype="multipart/form-data">
		<h4>Linux</h4>
		<label>Image name: </label>
		<input type="text" name="imageName"/> <br>
		<label>VMDK path: </label>
		<input type="text" name="vmdkPath" /> <input type="submit" value="Add Image" />
		<input type="hidden" name="type" value="linux"/>
	</form>
</body>
</html>