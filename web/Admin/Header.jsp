<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="" var="mainJsUrl" />

<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../css/bootstrap-theme.css" rel="stylesheet" type="text/css" />
        <link href="../Accordion.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="ScriptFiles/Script.js"></script>
        <script type="text/javascript" src="../angular.js"></script>
        <script type="text/javascript" src="../LatestJQuery.js"></script>
        <style type="text/css">

            *
            {
                margin:0px;
                padding:0px;
            }

            #TopStrip
            {
                height:30px;
                background-color:skyblue;
                margin-bottom:20px;
            }

            table tr td
            {
                padding: 3px;
                overflow: auto;
            }

        </style>

        <%

            if (session.getAttribute("ActiveMenu") != null) {

        %>
        <script type="text/javascript">

            $(document).ready(
                    function ()
                    {
                        $("#menu<%=session.getAttribute("ActiveMenu").toString()%> > ul").show();
                    }

            );


        </script>

        <%}%>
        <script type="text/javascript" src="../LatestJQuery.js">
        </script>

        <script type="text/javascript">

            $(document).ready
                    (
                            function ()
                            {

                                $("#accordion > li > div").click(function () {

                                    if (false == $(this).next().is(':visible')) {
                                        $('#accordion ul').slideUp(300);
                                    }

                                    $(this).next().slideToggle(300);
                                });

                            }

                    );

        </script>

    </head>

    <body>

        <div id="TopStrip">

        </div>

        <div class="container">

            <div class="panel panel-info" >
                <div class="panel-heading" style="height:80px;">

                    <h1 style="position:absolute">
                        Web XAM
                    </h1>

                    <ul class="nav nav-pills pull-right" style="margin-top:10px">
                        <li > <a href="../Home.jsp">Home</a> </li>
                        <li> <a href="../Login.jsp"> <span class="glyphicon glyphicon-user"></span> Login</a> </li>
                        <li> <a href="#">Contact Us</a> </li>
                        <li> <a href="../AboutUs.jsp">About Us</a> </li>
                    </ul>

                </div>	
            </div>

            <div class="row">

                <div class="col-sm-3">

                    <ul id="accordion">
                        <li id="menu1"><div>Department</div>
                            <ul>
                                <li><a href="Department.jsp">Add Department</a></li>
                                <li><a href="Departments.jsp">List Departments</a></li>
                            </ul>
                        </li>

                        <li id="menu2"><div>Job</div>
                            <ul>
                                <li><a href="Job.jsp">Add Job</a></li>
                                <li><a href="Jobs.jsp">List Job</a></li>
                            </ul>
                        </li>

                        <li id="menu3"><div>Category</div>
                            <ul>
                                <li><a href="Category.jsp">Add Category</a></li>
                                <li><a href="Categories.jsp">List Categories</a></li>
                            </ul>
                        </li>

                        <li><div>Test</div>
                            <ul>
                                <li><a href="Test.jsp">Add Test</a></li>
                                <li><a href="TestList.jsp">List Test</a></li>
                            </ul>
                        </li>

                    </ul>

                </div>

                <div class="col-sm-9">
                    <div class="panel panel-danger">
                        <div class="panel-body" style="background-color:#FF9927;color:white">