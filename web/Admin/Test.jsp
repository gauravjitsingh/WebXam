<%@page import="datalayer.DALTest"%>
<%@page import="datalayer.DALJob"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALDepartment"%>
<%@include file="Header.jsp" %>
        Test
</div>

    <div class="panel-body" ng-app="">

        <form action="../TestServlet" method="post" name="Test" novalidate>

    <table>

        <% 
            datalayer.DALDepartment objDAL = new DALDepartment() ;
            ArrayList<beans.Department> AD = objDAL.getDepartments() ;

            datalayer.DALJob objDALJ = new DALJob();
            ArrayList<beans.Job> AJ = objDALJ.getJobs() ;
            
//            datalayer.DALTest objDAL = new DALTest() ;
//            ArrayList<beans.Department> AD = objDAL.getDepartments() ;

            String ButtonText="Save" , ButtonName="btnSave" ;
            
            beans.Test update = null ;
            
            if(request.getParameter("UTID")!=null)
            {
                datalayer.DALTest objDALT = new DALTest() ;
                ButtonText="Update" ;
                ButtonName="btnUpdate" ;
                update = objDALT.getTest(Integer.parseInt(request.getParameter("UTID"))) ;
            }
        %>
        <tr>
            <td><label>Department Name</label></td>
            <td>
                <select name="ddlDepartment" id="ddlDepartment" class="form-control" autofocus >
                    <option>Select Department</option>
                    <% 
                        int DepartmentId = 0;

                        if (request.getParameter("ddlDepartments") != null) 
                        {
                            DepartmentId = Integer.parseInt(request.getParameter("ddlDepartments"));
                        }
                        
                        for(beans.Department dep : AD)
                        {
                    %>
                    <option value="<%= dep.getDepartmentId() %>" <%=update!=null&&update.DepartmentInfo.getDepartmentId()==dep.getDepartmentId()?"selected":""%>> <%= dep.getName() %> </option>
                    <% } %>
                                    
                </select>
            </td>
            <td></td>
        </tr>

        <tr>
            <td><label>Job Name</label></td>
            <td>
                <select name="ddlJob" id="ddlJob" class="form-control" >
                    <option>Select Job</option>
                    <% 
                        for(beans.Job job : AJ)
                        {
                    %>
                    <option value="<%= job.getJobId() %>" <%=update!=null&&update.JobInfo.getJobId()==job.getJobId()?"selected":""%>> <%= job.getName() %> </option>
                    <% } %>
                                    
                </select>
            </td>
            <td></td>
        </tr>
                
        <tr ng-class="{ 'has-error' : Test.txtTestDate.$touched && Test.txtTestDate.$invalid }">
            <td><label>Test Date</label></td>
            <td><input type="date" name="txtTestDate" id="txtTestDate" class="form-control" ng-model="txtTestDate" ng-init="txtTestDate='<%=update!=null?update.getTestDate():""%>'" required></td>
            <td><span ng-show="Test.txtTestDate.$touched && Test.txtTestDate.$invalid" class="help-block">Test Date not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Test.txtVacancies.$touched && Test.txtVacancies.$invalid }">
            <td><label>Vacancies</label></td>
            <td><input type="text" name="txtVacancies" id="txtVacancies" class="form-control" ng-model="txtVacancies" ng-init="txtVacancies='<%=update!=null?update.getVacancies():""%>'" required></td>
            <td><span ng-show="Test.txtVacancies.$touched && Test.txtVacancies.$invalid" class="help-block">Vacancies not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Test.txtPassingMarks.$touched && Test.txtPassingMarks.$invalid }">
            <td><label>Passing Marks</label></td>
            <td><input type="text" name="txtPassingMarks" id="txtPassingMarks" class="form-control" ng-model="txtPassingMarks" ng-init="txtPassingMarks='<%=update!=null?update.getPassingMarks():""%>'" required></td>
            <td><span ng-show="Test.txtPassingMarks.$touched && Test.txtPassingMarks.$invalid" class="help-block">Passing Marks not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Test.txtDuration.$touched && Test.txtDuration.$invalid }">
            <td><label>Duration</label></td>
            <td><input type="text" name="txtDuration" id="txtDuration" class="form-control" ng-model="txtDuration" ng-init="txtDuration='<%=update!=null?update.getDuration():""%>'" required></td>
            <td><span ng-show="Test.txtDuration.$touched && Test.txtDuration.$invalid" class="help-block">Duration not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Test.txtDescription.$touched && Test.txtDescription.$invalid }">
            <td><label>Description</label></td>
            <td><input type="text" name="txtDescription" id="txtDescription" class="form-control" ng-model="txtDescription" ng-init="txtDescription='<%=update!=null?update.getDescription():""%>'" required></td>
            <td><span ng-show="Test.txtDescription.$touched && Test.txtDescription.$invalid" class="help-block">Description not filled.</span></td>
        </tr>
        
        <tr>
            <td><label>Level</label></td>
            <td>
                    <input type="radio" name="radLevel" id="radLevel" class="radio-inline" value="1" >Hard
                    <input type="radio" name="radLevel" id="radLevel" class="radio-inline" value="0" checked>Easy
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="hidden" name="txtTestId" value="<%=update!=null?update.getTestId():0%>">
                <input type="submit" value="<%=ButtonText%>" name="<%=ButtonName%>" id="btnSave" class="btn btn-success" ng-disabled="Test.$invalid">
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