<%@page import="datalayer.DALJob"%>
<%@page import="datalayer.eStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("ActiveMenu", "2");
%>

<%@include file="Header.jsp" %>
    Job
    
    </div>

    <div class="panel-body" ng-app="">
        
        <% 
                String ButtonName="btnSave", ButtonText="Save" ;
                
                beans.Job update = null ;
                
                if(request.getParameter("UJID")!=null)
                {
                    datalayer.DALJob objDAL = new DALJob() ;
                    ButtonText="Update"  ;
                    ButtonName="btnUpdate" ;
                    update = objDAL.getJob(Integer.parseInt(request.getParameter("UJID"))) ;                

                }
                
                
        %>


        <form action="../JobServlet" method="post" name="Job" novalidate>
            <table>

                <tr ng-class="{ 'has-error' : Job.txtName.$touched && Job.txtName.$invalid }">
                    <td><label>Name</label></td>
                    <td><input type="text" name="txtName" id="txtName" ng-model="txtName" ng-init="txtName='<%= update!=null?update.getName():"" %>'" class="form-control" placeholder="Name" required autofocus></td>
                    <td><span ng-show="Job.txtName.$touched && Job.txtName.$invalid" class="help-block">Name not filled.</span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Job.txtDescription.$touched && Job.txtDescription.$invalid }">
                    <td><label>Description</label></td>
                    <td><input type="text" name="txtDescription" id="txtDescription" ng-model="txtDescription" ng-init="txtDescription='<%= update!=null?update.getDescription():"" %>'" class="form-control" placeholder="Description" required></td>
                    <td><span ng-show="Job.txtDescription.$touched && Job.txtDescription.$invalid" class="help-block">Description not filled.</span></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="txtJobId" value="<%= update!=null?update.getJobId():0 %>">
                        <input type="submit" name="<%=ButtonName%>" value="<%=ButtonText%>" class="btn btn-success" ng-disabled="Job.$invalid">
                    </td>
                </tr>
                
                <%
                    String flag = request.getParameter("flag") ;
                    if("Suceeded".equals(flag))
                    {
                %>
                
                <tr>
                    <td></td>
                    <td>Record Saved..</td>
                </tr>

                <%} 
                    else if("Duplicate".equals(flag))
                    {
                %>

                <tr>
                    <td></td>
                    <td>Record already exsists..</td>
                </tr>
                
                <% }
                    else if("Fail".equals(flag))
                    {
                %>
                
                <tr>
                    <td></td>
                    <td>Record could not be saved..</td>
                </tr>
                
                <% } %>
            </table>
        </form>
        <%@include file="Footer.html" %>