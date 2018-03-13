<%
    session.setAttribute("ActiveMenu", "3");
%>

<%@include file="Header.jsp" %>
Category
</div>

<div class="panel-body">

    <%
        String ButtonName = "btnSave", ButtonText = "Save";
    %>

    <form action="../CategoryServlet" method="post">
        <table>

            <tr>
                <td>Name</td>
                <td><input type="text" name="txtName" id="txtName" value="" class="form-control" placeholder="Name" autofocus></td>
            </tr>

            <tr>
                <td>Description</td>
                <td><input type="text" name="txtDescription" id="txtDescription" value="" class="form-control" placeholder="Description"></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" name="<%=ButtonName%>" value="<%=ButtonText%>" class="btn btn-success"></td>
            </tr>

            <%
                String flag = request.getParameter("flag");
                if ("Suceeded".equals(flag)) {
            %>

            <tr>
                <td></td>
                <td>
                    Record Saved...
                </td>
            </tr>

            <% } else if ("Duplicate".equals(flag)) {
            %>
            <tr>
                <td></td>
                <td>
                    Record already exsists...
                </td>
            </tr>

            <% } else if ("Fail".equals(flag)) {
            %>

            <tr>
                <td></td>
                <td>
                    Failed...
                </td>
            </tr>

            <% }%>
        </table>
    </form>
    <%@include file="Footer.html" %>