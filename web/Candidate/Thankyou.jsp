<%@page import="beans.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALConductedTest"%>
<%@include file="Header.jsp" %>
Exam
</div>
<div class="panel-body">

<div class="jumbotron text-xs-center">
  <h3 class="display-3">Thank You! Test Submitted</h3>
  <p class="lead"><strong>Please check your email</strong> for further instructions</p>
  <hr>
<!--  <p>
    Having trouble? <a href="">Contact us</a>
  </p>-->
<!--  <p class="lead">
    <a class="btn btn-primary btn-sm" href="https://bootstrapcreative.com/" role="button">Continue to homepage</a>
  </p>-->
</div>
    
<%
    int ctid = 0 ;
    
    if(request.getParameter("CTID")!=null)
    {
        ctid = Integer.parseInt(request.getParameter("CTID"));
    }
    
    datalayer.DALConductedTest objDAL = new DALConductedTest();
    ArrayList<beans.Question> aq = objDAL.getSavedQuestions(ctid);
%>    

            <table class="table table-striped table-hover">

                <tr>
                    <th>Question</th>
                    <th>Option Selected</th>
<!--                    <th>Option2</th>
                    <th>Option3</th>
                    <th>Option4</th>
                    <th>Answer</th>-->
                </tr>

                <%
                    if (aq.size() == 0) 
                    {
                %>
                
                <tr><td colspan="2"></td></tr>
                        <tr>
                            <th colspan="2">You have not attempted any question.</th>
                        </tr>
                <%                
                    } 
                    else 
                    {
                        for (beans.Question question : aq) 
                        {
                %>

                    <tr>
                        <td><%= question.getQuestionText()%></td>
                        <td><%= question.getOptionSelected() == 1? question.getOption1() : (question.getOptionSelected() == 2? question.getOption2() : (question.getOptionSelected() == 3? question.getOption3() :  question.getOption4() ) ) %></td>
                    </tr>

                    <%
                            
                        }
                    }
                %>
            </table>        

<%@include file="Footer.html" %>
