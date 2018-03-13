<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="" var="mainJsUrl" />

<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="css/bootstrap-theme.css" rel="stylesheet" type="text/css" />
        <link href="Accordion.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="Admin/ScriptFiles/Script.js"></script>
        <script type="text/javascript" src="angular.js"></script>

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


        <script type="text/javascript" src="../LatestJQuery.js">
        </script>

        <script type="text/javascript">

            $(document).ready
                    (
                            function()
                            {

                                $("#accordion > li > div").click(function() {

                                    //if(false == $(this).next().is(':visible')) {
                                    //    $('#accordion ul').slideUp(300);
                                    //}

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
                        <li > <a href="Home.jsp">Home</a> </li>
                        <li> <a href="Login.jsp"> <span class="glyphicon glyphicon-user"></span> Login</a> </li>
                        <li> <a href="Verification.jsp">Start Test</a> </li>
                        <li> <a href="AboutUs.jsp">About Us</a> </li>
                    </ul>

                </div>	
            </div>

            <div class="row">


                <div class="col-sm-12">
                    <div class="panel panel-danger">
                        <div class="panel-body" style="background-color:#FF9927;color:white">