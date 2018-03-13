<%@page import="datalayer.DALDepartment"%>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("ActiveMenu", "1");
%>
<%@include file="Header.jsp" %>

        Departments
</div>

    <div class="panel-body">

          <% 
                    datalayer.DALDepartment objDAL = new DALDepartment() ;
                    ArrayList<beans.Department> AD = objDAL.getDepartments() ;
            %>
              
            <table class="table table-striped" >
                <tr>
                    <th>Name</th>
                    <th>City</th>
                    <th>Contact No</th>
                    <th>Contact Person</th>                    
                    <th></th>
                    <th></th>
                </tr>
                
                <% 
                    for(beans.Department dep : AD)
                    {
                %>
                
                <tr> 
                    <td><%= dep.getName() %> </td>
                    <td><%= dep.getCity() %> </td>
                    <td><%= dep.getContactNo() %> </td>
                    <td><%= dep.getContactPerson() %> </td>
                    <td><a href="../DepartmentServlet?DDID=<%=dep.getDepartmentId()%>" 
                           onclick="return Confirmation('Do you want to delete the department?');" class="btn btn-danger" > Delete </a> </td>
                    <td><a href="Department.jsp?UDID=<%=dep.getDepartmentId()%>" class="btn btn-success" > Edit </a> </td>
                </tr>
                
                <% } %>
            </table>
                <%@include file="Footer.html" %>