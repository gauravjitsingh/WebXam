
<%@page import="datalayer.DALQuestion"%>
<%@page import="datalayer.DALCategory"%>
<%@page import="java.util.ArrayList"%>

<%@include file="Header.jsp" %>
Questions
</div>

<div class="panel-body">

    <form method="post" action="Questions.jsp">
                <table class="table table-striped table-hover">
                    <tr>
                        <th>Category</th>
                        <td>
                            <select name="ddlCategory" id="ddlCategory" class="form-control" autofocus>
                                <option>Select </option>
                                <%

                                    int CategoryId = 0;
                                    Byte Level = 0 ;
                                    
                                    if (request.getParameter("ddlCategory") != null) 
                                    {
                                        CategoryId = Integer.parseInt(request.getParameter("ddlCategory"));
                                    }
                                    if(request.getParameter("radLevel")!=null)
                                    {
                                        Level = Byte.parseByte(request.getParameter("radLevel")) ;
                                    }

                                    datalayer.DALCategory objDAL = new DALCategory();
                                    ArrayList<beans.Category> AC = objDAL.getCategories();

                                    for (beans.Category c : AC) 
                                    {
                                %>
                                <option value="<%= c.getCategoryId() %>" <%= c.getCategoryId() == CategoryId ? "selected" : ""%>><%= c.getName() %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
        
                    <tr>
                        <td><label>Level</label></td>
                        <td>
                            <input type="radio" name="radLevel" id="radLevel" class="radio-inline" value="1" <%= Level==1 ? "checked": ""%>>Hard
                            <input type="radio" name="radLevel" id="radLevel" class="radio-inline" value="0" <%= Level==0 ? "checked": ""%>>Easy
                        </td>
                    </tr>
                    
                    <tr>
                        <td> <input type="submit" name="btnSave" value="Show" id="btnSave" class="btn btn-success"> </td>
                    </tr>
                </table>
            </form>
                    
    <%
        if (CategoryId > 0) 
        {
    %>
    
            <table class="table table-striped table-hover">

                <tr>
                    <th>Question</th>
<!--                    <th>Option1</th>
                    <th>Option2</th>
                    <th>Option3</th>
                    <th>Option4</th>
                    <th>Answer</th>-->
                    <th></th>
                    <th></th>
                </tr>

                <%
                    datalayer.DALQuestion objDALQuestion = new DALQuestion();
                    ArrayList<beans.Question> AQ = objDALQuestion.getQuestion(Level, CategoryId);

                    if (AQ.size() == 0) 
                    {
                %>
                
                <tr><td colspan="8"></td></tr>
                        <tr>
                            <th colspan="8">There are no questions in this category of this level.</th>
                        </tr>
                <%                
                    } 
                    else 
                    {
                        for (beans.Question question : AQ) 
                        {
                %>

                <tr>
                    <td><%= question.getQuestionText() %></td>
                    <td><a href="../QuestionServlet?DQID=<%= question.getQuestionId() %>" 
                           onclick="return Confirmation('Do you want to delete the Question?');" class="btn btn-danger">Delete</a></td>
                    <td><a href="Question.jsp?UQID=<%= question.getQuestionId()%>" class="btn btn-primary">Edit</a></td>
                    <td><a href="QuestionDetail.jsp?QID=<%= question.getQuestionId() %>" class="btn btn-primary">Details</a></td>
                </tr>

                <%
                        }
                    }
        }
                %>
            </table>        

    <%@include file="Footer.html" %>