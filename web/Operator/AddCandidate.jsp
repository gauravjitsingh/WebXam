
<%@include file="Header.jsp" %>
<%@page import="datalayer.DALCandidate"%>
Add Candidate
</div>

<div class="panel-body" ng-app="">
<!--    <script>
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();
            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }

            today = (yyyy-16)+'-'+mm+'-'+dd;
            document.getElementById("txtDateofBirth").setAttribute("max", today);
    </script>-->
    
    <%
            if(request.getParameter("TID")==null&&request.getParameter("UCID")==null)
            {   
                response.sendRedirect("Tests.jsp");
            }
            
            int TestId=0 ;
            if(request.getParameter("TID")!=null)
            {
                TestId = Integer.parseInt(request.getParameter("TID")) ;                
            }
            
            String ButtonName="btnSave", ButtonText="Save";

            beans.Candidate update = null;
            
            if(request.getParameter("UCID")!=null)
            {
                
                datalayer.DALCandidate objDAL = new DALCandidate() ;
                ButtonText="Update";
                ButtonName="btnUpdate";
                update =  objDAL.getCandidate(Integer.parseInt(request.getParameter("UCID"))) ;
                
            }
            
        %>
        
        <form action="../CandidateServlet" method="Post" name="Candidate" novalidate>
            <table>
        
                <tr ng-class="{ 'has-error' : Candidate.txtName.$touched && Candidate.txtName.$invalid }">
                    <td><label>Candidate Name</label></td>
                    <td> <input type="text" name="txtName" id="txtName" ng-model="txtName" ng-init="txtName='<%=update!=null?update.getName():""%>'" placeholder="Enter Candidate Name" 
                                class="form-control" autofocus required> </td>
                    <td><span ng-show="Candidate.txtName.$touched && Candidate.txtName.$invalid" class="help-block">Name not filled.</span></td>
                </tr>
                
                <tr>
                    <td><label>Gender</label></td>
                    <td> 
                            <input type="radio" name="radGender" id="radGender" value="Male" class="radio-inline" checked> Male
                            <input type="radio" name="radGender" id="radGender" value="Female" class="radio-inline"> Female
                    </td>
                </tr>

                <tr ng-class="{ 'has-error' : Candidate.txtDateofBirth.$touched && Candidate.txtDateofBirth.$invalid }">
                    <td><label>Date of Birth</label></td>
                    <td> <input type="date" name="txtDateofBirth" id="txtDateofBirth" ng-model="txtDateofBirth" ng-init="txtDateofBirth='<%=update!=null?update.getDateofBirth():""%>'" class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtDateofBirth.$touched && Candidate.txtDateofBirth.$invalid" class="help-block">Date of Birth not filled.</span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Candidate.txtFatherName.$touched && Candidate.txtFatherName.$invalid }">
                    <td><label>Father Name</label></td>
                    <td> <input type="text" name="txtFatherName" id="txtFatherName" ng-model="txtFatherName" ng-init="txtFatherName='<%=update!=null?update.getFatherName():""%>'" placeholder="Enter Father Name" 
                                class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtFatherName.$touched && Candidate.txtFatherName.$invalid" class="help-block">Father name not filled.</span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Candidate.txtAddress.$touched && Candidate.txtAddress.$invalid }">
                    <td><label>Address</label></td>
                    <td> <input type="text" name="txtAddress" id="txtAddress" ng-model="txtAddress" ng-init="txtAddress='<%=update!=null?update.getAddress():""%>'" placeholder="Address" 
                                class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtAddress.$touched && Candidate.txtAddress.$invalid" class="help-block">Address not filled.</span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Candidate.txtCity.$touched && Candidate.txtCity.$invalid }">
                    <td><label>City</label></td>
                    <td> <input type="text" name="txtCity" id="txtCity" ng-model="txtCity" ng-init="txtCity='<%=update!=null?update.getCity():""%>'" placeholder="Enter City" 
                                class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtCity.$touched && Candidate.txtCity.$invalid" class="help-block">City not filled.</span></td>
                </tr>

                <tr ng-class="{ 'has-error' : Candidate.txtContactNo.$touched && Candidate.txtContactNo.$invalid }">
                    <td><label>Contact No</label></td>
                    <td> <input type="text" name="txtContactNo" id="txtContactNo" ng-model="txtContactNo" ng-init="txtContactNo='<%=update!=null?update.getContactNo():""%>'" placeholder="Enter Contact No" 
                                class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtContactNo.$touched && Candidate.txtContactNo.$invalid" class="help-block">Contact no not filled.</span></td>
                </tr>
                
                <tr ng-class="{ 'has-error' : Candidate.txtEmailId.$touched && Candidate.txtEmailId.$invalid }">
                    <td><label>Email Id</label></td>
                    <td> <input type="text" name="txtEmailId" id="txtEmailId" ng-model="txtEmailId" ng-init="txtEmailId='<%=update!=null?update.getEmail():""%>'" placeholder="Enter Email Id" 
                                class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtEmailId.$touched && Candidate.txtEmailId.$invalid" class="help-block">Email Id not filled.</span></td>
                </tr>
                
                <tr ng-class="{ 'has-error' : Candidate.txtEducation.$touched && Candidate.txtEducation.$invalid }">
                    <td><label>Education</label></td>
                    <td> <input type="text" name="txtEducation" id="txtEducation" ng-model="txtEducation" ng-init="txtEducation='<%=update!=null?update.getEducation():""%>'" placeholder="Education" 
                                class="form-control" required> </td>
                    <td><span ng-show="Candidate.txtEducation.$touched && Candidate.txtEducation.$invalid" class="help-block">Education not filled.</span></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="txtCandidateId" value="<%=update!=null?update.getCandidateId():0%>"> 
                        <input type="submit" value="<%=ButtonText%>" name="<%=ButtonName%>" class="btn btn-success" ng-disabled="Candidate.$invalid"> 
                        <a onclick="history.back();" class="btn btn-default">Back</a>
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
                        Record already exists...
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