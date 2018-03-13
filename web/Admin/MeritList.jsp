<%@page import="datalayer.DALTest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALCandidate"%>
<%@include file="Header.jsp" %>
        Merit List
</div>

    <div class="panel-body">

          <% 
                    beans.Department update = null;
                    int TestId=0 ;
                    if(request.getParameter("TID")!=null)
                    {
                        TestId = Integer.parseInt(request.getParameter("TID")) ;                
                    }

                    datalayer.DALTest objDAL = new DALTest();
                    beans.Test t = objDAL.getTest(TestId);
                    ArrayList<beans.Candidate> AC = objDAL.getMeritList(TestId);
            %>
              
            <table class="table table-striped" >
                <tr>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Marks</th>
<!--                    <th>Father Name</th>
                    <th>Contact No</th>
                    <th>Email Id</th>-->
                    <!--<th></th>-->
                </tr>
                <% 
                    if(AC.size()==0)
                    {
                %>

                <tr><td colspan="7"></td></tr>
                        <tr>
                            <th colspan="7">There is no appeared candidate in this test</th>
                        </tr>                
                <% 
                    }
                    else
                    {
                        for(beans.Candidate can : AC)
                        {
                    %>
                
                            <tr <%=t.getPassingMarks()<=can.getMarks()?"class='success'":" "%>> 
                                <td><%= can.getName() %> </td>
                                <td><%= can.getGender()%> </td>
                                <td><%= can.getDateofBirth() %> </td>
                                <td><%= can.getMarks() %> </td>
<!--                                <td><%= can.getFatherName() %> </td>
                                <td><%= can.getContactNo() %> </td>
                                <td><%= can.getEmail() %> </td>-->
                            </tr>

                <% }
                    } %>
                <tr>
                    <td><a onclick="history.back();" class="btn btn-default">Back</a></td>                    
                </tr>
            </table>
<%@include file="Footer.html" %>