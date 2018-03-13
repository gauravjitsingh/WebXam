<%@page import="datalayer.DALQuestion"%>
<%@page import="datalayer.DALCategory"%>
<%@page import="java.util.ArrayList"%>
<%@include file="Header.jsp" %>
        Question
</div>

    <div class="panel-body" ng-app="">

        <form action="../QuestionServlet" method="post" name="Question" novalidate>

    <table>

        <% 
            datalayer.DALCategory objDAL = new DALCategory() ;
            ArrayList<beans.Category> AC = objDAL.getCategories() ;

            String ButtonText="Save" , ButtonName="btnSave" ;
            
            beans.Question update = null ;
            
            if(request.getParameter("UQID")!=null)
            {
                datalayer.DALQuestion objDALQ = new DALQuestion();
                ButtonText="Update" ;
                ButtonName="btnUpdate" ;
                update = objDALQ.getQuestion(Integer.parseInt(request.getParameter("UQID"))) ;
            }
        %>
        <tr>
            <td><label>Category Name</label></td>
            <td>
                <select name="ddlCategory" id="ddlCategory" class="form-control" autofocus >
                    <option>Select Category</option>
                    <% 
                        for(beans.Category cat : AC)
                        {
                    %>
                    <option value="<%= cat.getCategoryId() %>" <%=update!=null&&update.CategoryInfo.getCategoryId()==cat.getCategoryId()?"selected":""%>> <%= cat.getName() %> </option>
                    <% } %>
                                    
                </select>
            </td>
            <td></td>
        </tr>
                
        <tr ng-class="{ 'has-error' : Question.txtQuestion.$touched && Question.txtQuestion.$invalid }">
            <td><label>Question</label></td>
            <td><input type="text" name="txtQuestion" id="txtQuestion" class="form-control" ng-model="txtQuestion" ng-init="txtQuestion='<%=update!=null?update.getQuestionText():""%>'" required></td>
            <td><span ng-show="Question.txtQuestion.$touched && Question.txtQuestion.$invalid" class="help-block">Question not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Question.txtOption1.$touched && Question.txtOption1.$invalid }">
            <td><label>Option 1</label></td>
            <td><input type="text" name="txtOption1" id="txtOption1" class="form-control" ng-model="txtOption1" ng-init="txtOption1='<%=update!=null?update.getOption1():""%>'" required></td>
            <td><span ng-show="Question.txtOption1.$touched && Question.txtOption1.$invalid" class="help-block">Option1 not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Question.txtOption2.$touched && Question.txtOption2.$invalid }">
            <td><label>Option 2</label></td>
            <td><input type="text" name="txtOption2" id="txtOption2" class="form-control" ng-model="txtOption2" ng-init="txtOption2='<%=update!=null?update.getOption2():""%>'" required></td>
            <td><span ng-show="Question.txtOption2.$touched && Question.txtOption2.$invalid" class="help-block">Option2 not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Question.txtOption3.$touched && Question.txtOption3.$invalid }">
            <td><label>Option 3</label></td>
            <td><input type="text" name="txtOption3" id="txtOption3" class="form-control" ng-model="txtOption3" ng-init="txtOption3='<%=update!=null?update.getOption3():""%>'" required></td>
            <td><span ng-show="Question.txtOption3.$touched && Question.txtOption3.$invalid" class="help-block">Option3 not filled.</span></td>
        </tr>
        
        <tr ng-class="{ 'has-error' : Question.txtOption4.$touched && Question.txtOption4.$invalid }">
            <td><label>Option 4</label></td>
            <td><input type="text" name="txtOption4" id="txtOption4" class="form-control" ng-model="txtOption4" ng-init="txtOption4='<%=update!=null?update.getOption4():""%>'" required></td>
            <td><span ng-show="Question.txtOption4.$touched && Question.txtOption4.$invalid" class="help-block">Option4 not filled.</span></td>
        </tr>
        
        <tr>
            <td><label>Answer</label></td>
            <td>
                <select name="ddlAnswer" id="ddlAnswer" class="form-control" autofocus >
                    <option>Select Answer</option>
                    <option value="1" <%=update!=null&&update.getAnswer()==1?"selected":""%>>Option1</option>                                    
                    <option value="2" <%=update!=null&&update.getAnswer()==2?"selected":""%>>Option2</option>                                    
                    <option value="3" <%=update!=null&&update.getAnswer()==3?"selected":""%>>Option3</option>                                    
                    <option value="4" <%=update!=null&&update.getAnswer()==4?"selected":""%>>Option4</option>                                    
                </select>
            </td>
            <td></td>
        </tr>        
        <tr>
            <td><label>Level</label></td>
            <td>
                    <input type="radio" name="radLevel" id="radLevel" class="radio-inline" value="1">Hard
                    <input type="radio" name="radLevel" id="radLevel" class="radio-inline" value="0" checked>Easy
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="hidden" name="txtTestId" value="<%=update!=null?update.getQuestionId():0%>">
                <input type="submit" value="<%=ButtonText%>" name="<%=ButtonName%>" id="btnSave" class="btn btn-success" ng-disabled="Question.$invalid">
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