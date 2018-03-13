<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALCategory"%>
<%
    session.setAttribute("ActiveMenu", "3");
%>

<%@include file="Header.jsp" %>


Categories
</div>

<div class="panel-body">

    <% 
        datalayer.DALCategory objDAL = new DALCategory() ;
        ArrayList<beans.Category> AC = objDAL.getCategories() ;
    %>

    <table class="table table-striped">
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th></th>
        <th></th>
    </tr>

    <%
        for(beans.Category cat : AC)
        {
        %>
    <tr>
        <td><%= cat.getName() %></td>
        <td><%= cat.getDescription() %></td>
        <td><a href="../CategoryServlet?DCID=<%= cat.getCategoryId() %>" 
               onclick="return Confirmation('Do you want to delete the department?');" class="btn btn-danger">Delete</a></td>
        <td><a href="Category.jsp?DCID=<%= cat.getCategoryId() %>" class="btn btn-primary">Edit</a></td>
    </tr>
    <% } %>
</table>
<%@include file="Footer.html" %>