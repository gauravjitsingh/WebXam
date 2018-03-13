<%@page import="datalayer.DALConductedTest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datalayer.DALCandidate"%>
<%@include file="Header.jsp" %>
Verification
</div>
<script>
    function CandidateIdvalidation(){
            var flag = true;
    
        var  cid = document.getElementById("txtCandidateId"); 

        var sp1 = document.getElementById("msg1");
    
        sp1.innerHTML = "";

            if (cid.value.length === 0)
            {
                sp1.innerHTML = "Candidate Id empty!";
                flag = false;
            }
            if (window.isNaN(cid.value) === true)
            {
                sp1.innerHTML = "Not a valid number";
                flag = false;
            }
            return flag;
        }
</script>
<div class="panel-body">

    <form method="post" action="Verification.jsp">
        <table class="table table-striped table-hover">
            <%
                String msg = "";
                beans.Candidate candidate = null;
                boolean ret = false ;
                
                if (request.getParameter("btnVerify") != null) 
                {
                    datalayer.DALCandidate objDAL = new DALCandidate();
                    candidate = objDAL.verifyCandidate(Integer.parseInt(request.getParameter("txtCandidateId")));

                    datalayer.DALConductedTest objDALconduct = new DALConductedTest();
                    ret = objDALconduct.isSubmitted(candidate.getCandidateId()) ;
                    
                    if (candidate.getCandidateId() == 0)
                    {
                        msg = "No such candidate Id exists!";
                    }
                    else if(ret == true){
                        msg = "You have submitted the Test";
                    }
                    else{
                        session.setAttribute("CandidateId", candidate.getCandidateId());
//                        session.setAttribute("TestId", candidate.TestInfo.getTestId());
                    }
                }
            %>

            <tr>
                <th style="width:180px">Candidate Id</th>
                <td style="width:180px">
                    <input type="text" name="txtCandidateId" id="txtCandidateId" class="form-control" autofocus>
                </td>
                <td> <input type="submit" name="btnVerify" value="Verify" id="btnVerify" class="btn btn-success" onclick="return CandidateIdvalidation()"> </td>
                <td><span id="msg1"> </span></td>
                <td><%=msg%></td>
            </tr>

        </table>
    </form>

    <%
        if (candidate != null) {
            if (candidate.getCandidateId() > 0 && ret ==false) {
    %>

    <table class="table table-striped table-hover">
        <tr>
            <th>Candidate Name</th>
            <td><%= candidate.getName()%></td>
        </tr>

        <tr>
            <th>Gender</th>        
            <td><%= candidate.getGender()%></td>
        </tr>

        <tr>
            <th>Date of Birth</th>        
            <td><%= candidate.getDateofBirth()%></td>
        </tr>

        <tr>
            <th>Father Name</th>        
            <td><%= candidate.getFatherName()%></td>
        </tr>

        <tr>
            <th>City</th>        
            <td><%= candidate.getCity()%></td>
        </tr>

        <tr>
            <th>Contact No</th>        
            <td><%= candidate.getContactNo()%></td>
        </tr>

        <tr>
            <th>Education</th>        
            <td><%= candidate.getEducation()%></td>
        </tr>
        <tr>
            <td><a href="Candidate/Instructions.jsp" class="btn btn-info" autofocus>Proceed</a></td>
            <td><a onclick="history.back();" class="btn btn-default">Back</a></td>
        </tr>

    </table>        

    <%
    }
}
    %>

    <%@include file="Footer.html" %>