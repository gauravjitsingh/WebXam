<%@page import="datalayer.DALDepartment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("ActiveMenu", "1");
%>

<%@include file="Header.jsp" %>
        Department
</div>
    <div class="panel-body" ng-app="">

        <%
            String ButtonName="btnSave", ButtonText="Save";

            beans.Department update = null;
            
            
            if(request.getParameter("UDID")!=null)
            {
                
                datalayer.DALDepartment objDAL = new DALDepartment();
                int DepartmentId = Integer.parseInt(request.getParameter("UDID"));
                
                ButtonName="btnUpdate";
                ButtonText="Update";
                update =  objDAL.getDepartment(DepartmentId) ;
                
            }
            
        %>
        
<form action="../DepartmentServlet" method="Post" name="Department" novalidate>
            <table>
        
                <tr ng-class="{ 'has-error' : Department.txtName.$touched && Department.txtName.$invalid }">
                    <td><label>Department Name</label></td>
                    <td> <input type="text" name="txtName" id="txtName" ng-model="txtName" placeholder="Enter Department Name" 
                                ng-init="txtName='<%=update!=null?update.getName():""%>'" class="form-control" autofocus required> </td>
                    <td><span ng-show="Department.txtName.$touched && Department.txtName.$invalid" class="help-block">Name not filled </span></td>
                </tr>
                
                <tr ng-class="{ 'has-error' : Department.txtAddress.$touched && Department.txtAddress.$invalid }">
                    <td><label>Address</label></td>
                    <td> <input type="text" name="txtAddress" id="txtAddress" ng-model="txtAddress" placeholder="Permanent Address" 
                                ng-init="txtAddress='<%=update!=null?update.getAddress():""%>'" class="form-control" required> </td>
                    <td><span ng-show="Department.txtAddress.$touched && Department.txtAddress.$invalid" class="help-block">Address not filled </span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Department.txtCity.$touched && Department.txtCity.$invalid }">
                    <td><label>City</label></td>
                    <td> <input type="text" name="txtCity" id="txtCity" ng-model="txtCity" placeholder="City" 
                                ng-init="txtCity='<%=update!=null?update.getCity():""%>'" class="form-control" required> </td>
                    <td><span ng-show="Department.txtCity.$touched && Department.txtCity.$invalid" class="help-block">City not filled </span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Department.txtContactNo.$touched && Department.txtContactNo.$invalid }">
                    <td><label>Contact No</label></td>
                    <td> <input type="text" name="txtContactNo" id="txtContactNo" ng-model="txtContactNo" placeholder="Enter Mobile No" 
                                ng-init="txtContactNo='<%=update!=null?update.getContactNo():""%>'" class="form-control" required> </td>
                    <td><span ng-show="Department.txtContactNo.$touched && Department.txtContactNo.$invalid" class="help-block">Mobile No not filled </span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Department.txtEmail.$touched && Department.txtEmail.$invalid }">
                    <td><label>Email Id</label></td>
                    <td> <input type="email" name="txtEmail" id="txtEmail" ng-model="txtEmail" placeholder="Email Id" 
                                ng-init="txtEmail='<%=update!=null?update.getEmailId():""%>'" class="form-control" required> </td>
                    <td><span ng-show="Department.txtEmail.$touched && Department.txtEmail.$invalid" class="help-block">Email Id not filled </span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Department.txtContactPerson.$touched && Department.txtContactPerson.$invalid }">
                    <td><label>Contact Person</label></td>
                    <td> <input type="text" name="txtContactPerson" id="txtContactPerson" ng-model="txtContactPerson" placeholder="Enter Contact Person Name" 
                                ng-init="txtContactPerson='<%=update!=null?update.getContactPerson():""%>'" class="form-control" required> </td>
                    <td><span ng-show="Department.txtContactPerson.$touched && Department.txtContactPerson.$invalid" class="help-block">Contact Person not filled </span></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="txtDepartmentId" value="<%=update!=null?update.getDepartmentId():0%>">
                        <input type="submit" value="<%=ButtonText%>" name="<%=ButtonName%>" class="btn btn-success" ng-disabled="Department.$invalid"> 
                    </td>
                </tr>
                
                  <% 
                      String flag = request.getParameter("flag") ;
                    if("Suceeded".equals(flag))
                    {
                %>
                
                <tr>
                    <td></td>
                    <td>
                        Record Saved...
                    </td>
                </tr>
                
                <% }
                    else if("Duplicate".equals(flag)){
                %>
                <tr>
                    <td></td>
                    <td>
                        Record already exsists...
                    </td>
                </tr>

                <% }
                    else if("Fail".equals(flag)){
                %>

                <tr>
                    <td></td>
                    <td>
                        Failed...
                    </td>
                </tr>
                
                <% } %>
            </table>
        
        </form>

<%@include file="Footer.html" %>