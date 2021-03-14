<%-- 
    Document   : imagelist
    Created on : Mar 12, 2021, 1:09:19 AM
    Author     : ThinhPH
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Image List</title>
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
                            <a class="nav-link" href="MainServlet?action=Image">
                                <i class="material-icons">image</i>
                                <p>Image List</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainServlet?action=Deploy">
                                <i class="material-icons">download_for_offline</i>
                                <p>Deploy</p>
                            </a>
                        </li>
                    </ul>
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
                        <div class="col-md 12">
                            <div class="card">
                                <div class="card-header card-header-primary">
                                    <h4 class="card-title ">Image List</h4>
                                    <p class="card-category">List all the image existing on server hard drive</p>
                                </div>
                                <div class="card-body">
                                    <c:set var="addImageStatus" value="${param.addImageStatus}"/>
                                    <div class="table-responsive">
                                        <c:set var="listImage" value="${requestScope.resultList}"/>
                                        <table class="table">
                                            <thead class=" text-primary">
                                            <th>
                                                ID
                                            </th>
                                            <th>
                                                Name
                                            </th>
                                            <th>
                                                Kernel
                                            </th>
                                            <th>
                                                Date
                                            </th>
                                            <th>
                                                Status
                                            </th>
                                            <th>
                                                Action
                                            </th>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty listImage}">
                                                    <c:forEach items="${listImage}" var="x" varStatus="status">
                                                        <tr>
                                                            <td style="width: 10%">
                                                                ${status.index+1}
                                                            </td>
                                                            <td id="id_${status.index+1}" style="width: 15%">
                                                                ${x.get(0)}
                                                            </td>
                                                            <td style="width: 15%">
                                                                ${x.get(1)}
                                                            </td>
                                                            <td style="width: 15%">
                                                                ${x.get(2)}
                                                            </td>
                                                            <td style="width: 15%">
                                                                <c:if test="${x.get(3) eq 'Active'}" >
                                                                    <h4  id="isActive_${status.index+1}" style="color: green; font-weight: bold" >Active</h4>
                                                                </c:if>
                                                                <c:if test="${x.get(3) eq 'Inactive'}" >
                                                                    <h4 id="isActive_${status.index+1}" style="color: red; font-weight: bold">Inactive</h4>
                                                                </c:if>
                                                            </td>
                                                            <td style="width: 15%">
                                                                <button type="submit" class="btn btn-sm">View</button>
                                                                <c:if test="${x.get(3) eq 'Active'}" >
                                                                    <button id="btnChangeStatus${status.index+1}" type="submit" class="btn btn-sm btn-danger"  onclick="changeStatus(${status.index+1})">Deactivate</button>
                                                                </c:if>
                                                                <c:if test="${x.get(3) eq 'Inactive'}" >
                                                                    <button id="btnChangeStatus${status.index+1}" type="submit" class="btn btn-sm btn-success"  onclick="changeStatus(${status.index+1})">     Active     </button>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>  
                                                </c:if>
                                            </tbody>
                                        </table>
                                        <a href="addimage.jsp"><button type="submit" class="btn btn-primary">Add new Image</button></a>
                                        <a href=""><button type="submit" class="btn btn-primary">Update table</button></a>
                                    </div>
                                    <c:if  test="${not empty addImageStatus && addImageStatus == true}">
                                        <p class="text-success">Add new Image successful</p>
                                    </c:if>
                                </div>
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
<script>
    function changeStatus(cellIndex) { //change status Image to Active status == Deactive
        var tdCellIndex = 'isActive_' + cellIndex;
        var btnCellIndex = 'btnChangeStatus' + cellIndex;
        var cell = document.getElementById(tdCellIndex);
        var btn = document.getElementById(btnCellIndex);
        if (cell.style.color == 'red') {
            cell.style.color = 'green'; //change <p> to Inactive with color red
            cell.innerText = "Active";
            btn.className = 'btn btn-sm btn-danger'; //change <button> to Active with color green
            btn.innerText = "Deactivate";
        } else {
            cell.style.color = 'red'; //change <p> to Inactive with color red
            cell.innerText = "Inactive";
            btn.className = 'btn btn-sm btn-success'; //change <button> to Active with color green
            btn.innerText = "     Active     ";
        }
    }
</script>
</html>
