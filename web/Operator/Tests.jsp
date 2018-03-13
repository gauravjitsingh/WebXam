<%@page import="datalayer.DALTest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALDepartment"%>
<%@include file="Header.jsp" %>
Tests
</div>

<div class="panel-body">
    
    <form method="post" action="Tests.jsp">
                <table class="table table-striped table-hover">
                    <tr>
                        <th>Department Name</th>
                        <td>
                            <select name="ddlDepartments" id="ddlDepartments" class="form-control" onchange="ddlDepartmentvalidation();" autofocus>
                                <option>Select Department</option>
                                <%

                                    int DepartmentId = 0;

                                    if (request.getParameter("ddlDepartments") != null) 
                                    {
                                        DepartmentId = Integer.parseInt(request.getParameter("ddlDepartments"));
                                    }

                                    datalayer.DALDepartment objDAL = new DALDepartment();
                                    ArrayList<beans.Department> AD = objDAL.getDepartments();

                                    for (beans.Department d : AD) 
                                    {
                                %>
                                <option value="<%= d.getDepartmentId()%>" <%= d.getDepartmentId() == DepartmentId ? "selected" : ""%>><%= d.getName()%></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
                    
    <%
        if (DepartmentId > 0) 
        {
    %>
    
            <table class="table table-striped table-hover">

                <tr>
                    <th>Test Date</th>
                    <th>Job</th>
                    <th>Vacancies</th>
                    <th>Passing Marks</th>
                    <th>Duration</th>
                    <th></th>
                    <th></th>
                </tr>

                <%
                    datalayer.DALTest objDALTest = new DALTest();
                    ArrayList<beans.Test> AT = objDALTest.getDepartmentTest(DepartmentId);

                    if (AT.size() == 0) 
                    {
                %>

                        <tr>
                            <th>There is no test scheduled for selected Department</th>
                        </tr>
                <%                
                    } 
                    else 
                    {
                        for (beans.Test test : AT) 
                        {
                %>

                <tr>
                    <td><%= test.getTestDate()%></td>
                    <td><%= test.JobInfo.getName()%></td>
                    <td><%= test.getVacancies()%></td>
                    <td><%= test.getPassingMarks()%></td>
                    <td><%= test.getDuration()%></td>
                    <td><a href="Candidates.jsp?TID=<%= test.getTestId()%>" class="btn btn-primary">Candidates</a></td>
                    <td><a href="AddCandidate.jsp?TID=<%= test.getTestId()%>" class="btn btn-primary">Add Candidate</a></td>
                </tr>

                <%
                        }
                    }
        }
                %>
            </table>        
    
<%@include file="Footer.html" %>
