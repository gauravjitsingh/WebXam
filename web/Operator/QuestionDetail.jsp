
<%@page import="datalayer.DALQuestion"%>
<%@include file="Header.jsp" %>
Question Details
</div>

<div class="panel-body">
<%
    beans.Question q = null ;
    
    if(request.getParameter("QID")!=null)
    {
        int QuestionId = Integer.parseInt(request.getParameter("QID"));
        
        datalayer.DALQuestion objDAL = new DALQuestion() ;
        q = objDAL.getQuestion(QuestionId) ;
    }
%>

<table class="table table-striped table-hover">
    <tr>
        <th>Question Text</th>
        <td><%= q.getQuestionText() %></td>
    </tr>
    
    <tr>
        <th>Option1</th>        
        <td <%=q.getAnswer()==1?"class='success'":" "%>> <%= q.getOption1() %></td>
    </tr>
    
    <tr>
        <th>Option2</th>        
        <td <%=q.getAnswer()==2?"class='success'":" "%>> <%= q.getOption2() %></td>
    </tr>

    <tr>
        <th>Option3</th>        
        <td <%=q.getAnswer()==3?"class='success'":" "%>> <%= q.getOption3() %></td>
    </tr>
    
    <tr>
        <th>Option4</th>        
        <td <%=q.getAnswer()==4?"class='success'":" "%>> <%= q.getOption4() %></td>
    </tr>
    
<!--    <tr>
        <th>Answer</th>        
        <td><%= q.getAnswer() %></td>
    </tr>
    
    <tr>
        <th>Level</th>
        <td><%= q.getLevel()==0?"Easy":"Hard" %></td>
    </tr>
    
    <tr>
        <th>Category</th>        
        <td><%= q.CategoryInfo.getName() %></td>
    </tr>-->
    <tr>
        <td><a onclick="history.back();" class="btn btn-default">Back</a></td>
    </tr>
</table>
<%@include file="Footer.html" %>