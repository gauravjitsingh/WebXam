<%@page import="beans.TestCategory"%>
<%@page import="datalayer.DALTestCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALCategory"%>
<%@include file="Header.jsp" %>
Test Category
</div>

<div class="panel-body">

    <% 
        datalayer.DALCategory objDAL = new DALCategory() ;
        ArrayList<beans.Category> AC = objDAL.getCategories() ;
    %>
    <form action="../TestCategoryServlet" method="post">
    <table class="table table-striped table-hover">
        <tr>
            <th>Category</th>
            <td>
                <select name="ddlCategories" id="ddlCategories" onchange="" autofocus >
                    <option>Select Category</option>
                    <% 
                        int TestId = 0 ;
                        int CategoryId = 0 ;
                        if(request.getParameter("TID")!=null)
                        {
                            TestId = Integer.parseInt(request.getParameter("TID"));
                        }

                        if(request.getParameter("ddlCategories")!=null)
                        {
                            CategoryId = Integer.parseInt(request.getParameter("ddlCategories")) ;
                        }
                        
                        for(beans.Category c : AC)
                        {
                    %>
                    <option value="<%= c.getCategoryId() %>"><%= c.getName() %></option>
                    <% } %>
                </select>
            </td>
        </tr>
        
        <tr>
            <th>No of Questions</th>
            <td><input type="text" name="txtNoOfQuestions" class="form-control"</td>
        </tr>
        
        <tr>
            <td><input type="hidden" name="TID" value="<%= TestId %>"</td>
            <td><input type="submit" name="btnSave" value="Add" class="btn btn-success"></td>
            <td><a onclick="history.back();" class="btn btn-default">Back</a></td>
        </tr>
    </table>
    </form>            
                            
                <%if(TestId>0 )
                    {
                %>
                <table class="table table-striped table-hover">

                    <tr>
                        <td>Categories</td>
                        <td>No of Questions</td>
                    </tr>
                    <%
                            datalayer.DALTestCategory objDaltc = new DALTestCategory() ;
                            ArrayList<beans.TestCategory> ATC = objDaltc.getTestCategories(TestId) ;
                            
                            if(ATC.size()==0)
                            {
                    %>
                    
                    <tr><td colspan="3"></td></tr>
                        <tr>
                            <th colspan="3">There is yet no category for particular test</th>
                        </tr>
                
                        <%                
                            } 
                            else 
                            {
                                for (beans.TestCategory testcategory : ATC) 
                                {
                        %>

                        <tr>
                            <td><%= testcategory.CategoryInfo.getName() %></td>
                            <td><%= testcategory.getNoofQuestions() %></td>
                            <td><a href="../TestCategoryServlet?DTCID=<%=testcategory.getTestCategoryId() %>&TID=<%=TestId%>" class="btn btn-danger">Delete</a></td>
                        </tr>
                        
                        <%
                                }   
                            }        
                    }
                        %>

                    </table>
<%@include file="Footer.html" %>