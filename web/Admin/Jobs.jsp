<%@page import="datalayer.DALJob"%>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("ActiveMenu", "2");
%>

<%@include file="Header.jsp" %>
        Jobs
</div>

<div class="panel-body">
    <%
        datalayer.DALJob objDAL = new DALJob() ;
        ArrayList<beans.Job> AJ = objDAL.getJobs() ;
    %>
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th></th>
            <th></th>
        </tr>
        <% 
            for(beans.Job job : AJ)
            {
        %>
        <tr>
            <td> <%= job.getName() %> </td>
            <td> <%= job.getDescription() %> </td>
            <td><a href="../JobServlet?DJID=<%= job.getJobId() %>" onclick="return Confirmation('Do you want to delete the job?');" class="btn btn-danger">Delete</a></td>
            <td><a href="Job.jsp?UJID=<%= job.getJobId() %>" class="btn btn-primary">Edit</a></td>
        </tr>
        
        <% } %>
    </table>

<%@include file="Footer.html" %>
