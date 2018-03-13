<%@page import="datalayer.DALConductedTest"%>
<%@page import="beans.Question"%>
<%@page import="datalayer.DALCandidate"%>
<%@page import="datalayer.DALQuestion"%>
<%@page import="java.util.ArrayList"%>
<%@include file="Header.jsp" %>
Exam
</div>
<script type="text/javascript" src="ScriptFiles/MyScript.js"></script>
<script type="text/javascript" src="ScriptFiles/timer.js"></script>
<script>f1();</script>
<div class="panel-body">

    <%
        int CID = Integer.parseInt(session.getAttribute("CandidateId").toString());
        datalayer.DALCandidate objDALCandidate = new DALCandidate();
        beans.Candidate candidate = objDALCandidate.getCandidate(CID);
    %>

    <table class="table table-condensed">
        <tr>
            <th>Candidate Name</th>
            <td><%= candidate.getName()%></td>
            <th>Gender</th>
            <td><%= candidate.getGender()%></td>
        </tr>
        <tr>
            <th>Date of Birth</th>
            <td><%= candidate.getDateofBirth()%></td>
            <th>Father Name</th>
            <td><%= candidate.getFatherName()%></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><%= candidate.getAddress()%></td>
            <th>City</th>
            <td><%= candidate.getCity()%></td>
        </tr>
        <tr>
            <th>Contact No</th>
            <td><%= candidate.getContactNo()%></td>
            <th>Email Id</th>
            <td><%= candidate.getEmail()%></td>
        <tr>
            <th>Education</th>
            <td><%= candidate.getEducation()%></td>
        </tr>
    </table>

    <%
        int ConductedTestId = 0;

        datalayer.DALQuestion objDAL = new DALQuestion();

        datalayer.DALConductedTest objDALCT = new DALConductedTest();
        
        if (objDALCT.isStarted(candidate.getCandidateId()) == false) {

            ArrayList<beans.TestCategory> tc = objDAL.gettestcategory(candidate.TestInfo.getTestId());
            ArrayList<beans.Question> totalQuestions = new ArrayList<Question>();

            for (beans.TestCategory testcat : tc) {
                ArrayList<beans.Question> q = objDAL.getTestQuestions(testcat.getNoofQuestions(), testcat.CategoryInfo.getCategoryId(), testcat.TestInfo.getLevel());

                for (beans.Question que : q) {
                    totalQuestions.add(que);
                }
            }
            ConductedTestId = objDALCT.startTest(candidate.getCandidateId(), candidate.TestInfo.getDuration(), totalQuestions);

        } else {

            ConductedTestId = objDALCT.getConductedTestId(candidate.getCandidateId());

        }
        
        ArrayList<beans.Question> SavedQuestions = objDALCT.getSavedQuestions(ConductedTestId);

        int j = 1;

    %>

    <form method="post" action="../ConductedTestServlet">
        
    <table class="table">
        <tr><td class="pull-right active"><div> <span id="time"></span> </div></td></tr>

        <%        for (beans.Question que : SavedQuestions) {
        %>
        <tr> 
            <td> <span id="sp<%=que.getAttemptedQuestionId()%>"> Q<%=j%>.  <%= que.getQuestionText()%> </span> </td>
        <input type="hidden" name="QuestionId<%=j%>" id="QuestionId<%=j%>" value="<%=que.getQuestionId()%>">
        </tr>
        <tr>
            <td><input type="radio" name="rad<%=j%>" class="radio-inline" value="1$<%=que.getAttemptedQuestionId()%>" onclick="OptionUpdate(1,<%=que.getAttemptedQuestionId() %>)" <%=que.getOptionSelected()==1?"Checked":""%>   style="margin-left:10px;margin-right: 10px "> <%= que.getOption1()%> </td>
        </tr>
        <tr>
            <td><input type="radio" name="rad<%=j%>" class="radio-inline" value="2$<%=que.getAttemptedQuestionId()%>" onclick="OptionUpdate(2,<%=que.getAttemptedQuestionId() %>)" <%=que.getOptionSelected()==2?"Checked":""%> style="margin-left:10px;margin-right: 10px"> <%= que.getOption2()%> </td>
        </tr>
        <tr>
            <td><input type="radio" name="rad<%=j%>" class="radio-inline" value="3$<%=que.getAttemptedQuestionId()%>" onclick="OptionUpdate(3,<%=que.getAttemptedQuestionId()%>)" <%=que.getOptionSelected()==3?"Checked":""%> style="margin-left:10px;margin-right: 10px"> <%= que.getOption3()%> </td>
        </tr>
        <tr>
            <td><input type="radio" name="rad<%=j%>" class="radio-inline" value="4$<%=que.getAttemptedQuestionId()%>" onclick="OptionUpdate(4,<%=que.getAttemptedQuestionId() %>)" <%=que.getOptionSelected()==4?"Checked":""%> style="margin-left:10px;margin-right: 10px"> <%= que.getOption4()%> </td>
        </tr>

        <%
                j++;
            }
        %>

        <tr>
        
        <input type="hidden" name="count" id="count" value="<%=SavedQuestions.size()%>">
        <input type="hidden" name="Time" id="Time" value="<%=objDALCT.getTimeRemaining(ConductedTestId) %>">
        <input type="hidden" name="ConductedTestId" id="ConductedTestId" value="<%=ConductedTestId%>">
        
        <td><input type="submit" name="btnSubmit" id="btnSubmit" value="Submit" class="btn btn-success"> </td>
        </tr>            
    </table>
    </form>

    <%@include file="Footer.html" %>