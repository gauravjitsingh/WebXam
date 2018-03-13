<%@page import="beans.Test"%>
<%@page import="datalayer.DALTest"%>
<%@include file="Header.jsp" %>
Test Details
</div>

<div class="panel-body">
<%
    beans.Test t = null ;
    if(request.getParameter("TID")!=null)
    {
        int TestId = Integer.parseInt(request.getParameter("TID"));
        
        datalayer.DALTest objDAL = new DALTest() ;
        t = objDAL.getTest(TestId) ;
    }
%>

<table class="table table-striped table-hover">
    <tr>
        <th>Department Name</th>
        <td><%= t.DepartmentInfo.getName() %></td>
    </tr>
    
    <tr>
        <th>Job Name</th>        
        <td><%= t.JobInfo.getName() %></td>
    </tr>
    
    <tr>
        <th>Test Date</th>        
        <td><%= t.getTestDate() %></td>
    </tr>

    <tr>
        <th>Vacancies</th>        
        <td><%= t.getVacancies() %></td>
    </tr>
    
    <tr>
        <th>Passing Marks</th>        
        <td><%= t.getPassingMarks() %></td>
    </tr>
    
    <tr>
        <th>Duration</th>        
        <td><%= t.getDuration() %></td>
    </tr>
    
    <tr>
        <th>Level</th>
        <td><%= t.getLevel()==0?"Easy":"Hard" %></td>
    </tr>
    
    <tr>
        <th>Description</th>        
        <td><%= t.getDescription()%></td>
    </tr>
    <tr>
        <td><a href="TestCategory.jsp?TID=<%=t.getTestId()%>" class="btn btn-info">Categories</a></td>
        <td><a href="AppearedCandidates.jsp?TID=<%=t.getTestId()%>" class="btn btn-primary">Appeared Candidates</a></td>
        <td><a href="MeritList.jsp?TID=<%=t.getTestId()%>" class="btn btn-primary">Merit List</a></td>
        <td><a onclick="history.back();" class="btn btn-default">Back</a></td>
    </tr>
</table>
<%@include file="Footer.html" %>