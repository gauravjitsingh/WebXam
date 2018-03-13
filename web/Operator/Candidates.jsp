<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALCandidate"%>
<%@include file="Header.jsp" %>
        Candidates
</div>

    <div class="panel-body">

          <% 
                    beans.Department update = null;
                    int TestId=0 ;
                    if(request.getParameter("TID")!=null)
                    {
                        TestId = Integer.parseInt(request.getParameter("TID")) ;                
                    }

                    datalayer.DALCandidate objDAL = new DALCandidate();
                    ArrayList<beans.Candidate> AC = objDAL.getCandidates(TestId);
            %>
              
            <table class="table table-striped" >
                <tr>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Father Name</th>
                    <th>City</th>
                    <th>Contact No</th>
                    <th>Education</th>
                    <th></th>
                    <th></th>
                </tr>
                <% 
                    if(AC.size()==0)
                    {
                %>

                <tr><td colspan="9"></td></tr>
                        <tr>
                            <th colspan="9">There is no candidate in this test</th>
                        </tr>                
                <% 
                    }
                    else
                    {
                        for(beans.Candidate can : AC)
                        {
                    %>
                
                            <tr> 
                                <td><%= can.getName() %> </td>
                                <td><%= can.getGender()%> </td>
                                <td><%= can.getDateofBirth() %> </td>
                                <td><%= can.getFatherName() %> </td>
                                <td><%= can.getCity() %> </td>
                                <td><%= can.getContactNo() %> </td>
                                <td><%= can.getEducation() %> </td>
<!--                                <td><input type="hidden" name="txtTestId" value="<%= TestId %>"></td>-->
                                <td><a href="../CandidateServlet?DCID=<%=can.getCandidateId() %>" 
                                       onclick="return Confirmation('Do you want to delete the candidate?');" class="btn btn-danger" > Delete </a> </td>
                                <td><a href="AddCandidate.jsp?UCID=<%=can.getCandidateId() %>" class="btn btn-success" > Edit </a> </td>
                            </tr>

                <% }
                    } %>
                <tr>
                    <td><a onclick="history.back();" class="btn btn-default">Back</a></td>                    
                </tr>
            </table>
<%@include file="Footer.html" %>