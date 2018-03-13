
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp"%>
Login
</div>

<div class="panel-body" ng-app="">
    <div>
        <%
            if(request.getParameter("btnLogin")!=null)
            {
                if(request.getParameter("txtUserName")!=null)
                {
                    if(request.getParameter("txtUserName").equals("Admin") && request.getParameter("txtPassword").equals("rugged"))
                    {
                        response.sendRedirect("Admin/Department.jsp");
                    }                
                    else if(request.getParameter("txtUserName").equals("Operator") && request.getParameter("txtPassword").equals("alphamale"))
                    {
                            response.sendRedirect("Operator/Question.jsp");                    
                    }
                }
            }
        %>
        <form action="" method="post" name="Login" novalidate>
            <table>
                <tr ng-class="{ 'has-error' : Login.txtUserName.$touched && Login.txtUserName.$invalid }">
                    <th><label class="">User Name</label></th>
                    <td><input type="text" name="txtUserName" id="txtUserName" class="form-control" ng-model="txtUserName" required autofocus></td>
                    <td><span ng-show="Login.txtUserName.$touched && Login.txtUserName.$invalid" class="help-block">Enter User Name</span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Login.txtPassword.$touched && Login.txtPassword.$invalid }">
                    <th><label class="">Password</label></th>
                    <td><input type="password" name="txtPassword" id="txtPassword" class="form-control" ng-model="txtPassword" required></td>
                    <td><span ng-show="Login.txtPassword.$touched && Login.txtPassword.$invalid" class="help-block">Enter Password</span></td>
                </tr>            
                <tr>
                    <td></td>
                    <td><input type="submit" class="btn btn-primary" name="btnLogin" value="Login"></td>
                </tr>
            </table>
        </form>
    </div>
    <%@include file="Footer.html"%>