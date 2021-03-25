<%-- 
    Document   : windows10
    Created on : Mar 6, 2021, 2:16:06 PM
    Author     : ThinhPH
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Image</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- Material Kit CSS -->
        <link href="assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper ">
            <div class="sidebar" data-color="purple" data-background-color="white">
                <!--
                Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
          
                Tip 2: you can also add an image using data-image tag
                -->
                <div class="logo">
                    <a class="simple-text logo-mini">
                        GPS21IA01 
                    </a>
                    <a class="simple-text logo-normal">
                        SP21IA05
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link" href="MainServlet?action=Dashboard">
                                <i class="material-icons">dashboard</i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <!-- your sidebar here -->
                        <li class="nav-item active">
                            <a class="nav-link" href="MainServlet?action=ImageList">
                                <i class="material-icons">image</i>
                                <p>Image List</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainServlet?action=Deploy">
                                <i class="material-icons">upgrade</i>
                                <p>Deploy</p>
                            </a>
                        </li>
                </div>
            </div>
            <div class="main-panel">
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
                    <div class="container-fluid">
                        <div class="navbar-wrapper">
                            <a class="navbar-brand" href="javascript:;">Dashboard</a>
                        </div>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-end">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="javascript:;">
                                        <i class="material-icons">notifications</i> Notifications
                                    </a>
                                </li>
                                <!-- your navbar here -->
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- End Navbar -->
                <div class="content">
                    <div class="container-fluid">
                        <div class="card">
                            <c:set var="selectedOS" value="${param.selectedOS}"></c:set>
                                <div class="card-header card-header-primary">
                                    <form>
                                        <h4 class="card-title ">Choose an OS: </h4>
                                        <select name="selectedOS" id="selectOS">
                                            <c:if test="${not empty selectedOS}">
                                                <option value="">   --- Choose an OS ----   </option>
                                                <option value="windows" ${selectedOS eq 'windows' ? 'selected' : ''}>Windows</option>
                                                <option value="linux" ${selectedOS eq 'linux' ? 'selected' : ''}>Linux</option>
                                            </c:if>
                                            <c:if test="${empty selectedOS}">
                                                <option value="">   --- Choose an OS ----   </option>
                                                <option value="windows">Windows</option>
                                                <option value="linux">Linux</option>
                                            </c:if>
                                        </select>
                                        <button type="submit" class="btn btn-sm btn-outline-light" value="submit">OK</button>
                                    </form>
                            </div>
                            <div class="card-body">
                                <c:set var="selectedOS" value="${param.selectedOS}"/>
                                <c:set var="addImageStatus" value="${param.addImageStatus}"/>
                                <c:if test="${not empty selectedOS}">
                                    <c:if test="${selectedOS eq 'windows'}">
                                        <div class="col-md-3">
                                            <div>
                                                <label for="name" style="color: black">OS Name:</label>
                                                <input type="text" id="name" class="form-control">
                                            </div>
                                            <div>
                                                <label for="kernelFile" style="color: black" class="form-label">Kernel File:</label>
                                                <input type="file" id="kernelFile" style="padding-left: 40px">
                                            </div>
                                            <div>
                                                <label for="bcdFile" style="color: black">BCD File:</label>
                                                <input type="file" id="bcdFile" class="input" style="padding-left: 40px">
                                            </div>
                                            <div>
                                                <label for="bootSdiFile" style="color: black">Boot.sdi File:</label>
                                                <input type="file" id="bootSdiFile" class="input" style="padding-left: 40px">
                                            </div>
                                            <div>
                                                <label for="bootWimFile" style="color: black">Boot.wim File:</label>
                                                <input type="file" id="bootWimFile" class="input" style="padding-left: 40px">
                                            </div>
                                        </div>
                                        <div class="col-md-auto">
                                            <a href="MainServlet?action=Image"><button type="submit" class="btn btn-primary pull-right">Add Image</button></a>
                                        </div>
                                    </c:if>
                                    <c:if test="${selectedOS eq 'linux'}">
                                        <div class="col-md-3">
                                            <div>
                                                <label for="name" style="color: black">OS Name:</label>
                                                <input type="text" id="name" class="form-control">
                                            </div>
                                            <div>
                                                <label for="kernelFile" style="color: black" class="form-label">VMDK File:</label>
                                                <input type="file" id="kernelFile" style="padding-left: 40px">
                                            </div>
                                        </div>
                                        <div class="col-md-auto">
                                            <a href="MainServlet?action=Image"><button type="submit" class="btn btn-primary pull-right">Add Image</button></a>
                                        </div>
                                    </c:if>
                                </c:if>
                                <c:if  test="${not empty addImageStatus && addImageStatus == false}">
                                    <p class="text-danger">Failed to add new image</p>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="footer">
                <div class="container-fluid">
                    <nav class="float-left">
                        <ul>
                            <li>
                                <a href="https://www.creative-tim.com">
                                    ThinhPH
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="copyright float-right">
                        &copy;
                        <script>
                            document.write(new Date().getFullYear())
                        </script>, made with <i class="material-icons">favorite</i> by GPS21IA01.
                    </div>
                    <!-- your footer here -->
                </div>
            </footer>
        </div>
    </div>
</body>
</html>
