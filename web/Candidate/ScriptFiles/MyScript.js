  
var request;  

  function OptionUpdate( OptionSelected,  AttemptedQuestionId )  
    {  
//    alert("GauravjitSingh");
    var OptionSelected = OptionSelected ;
    var AttemptedQuestionId = AttemptedQuestionId ;
    
    var url="http://localhost:8080/WebXam/ConductedTestServlet?OptionSelected="+OptionSelected+"&AttemptedQuestionId="+AttemptedQuestionId;  

    if(window.XMLHttpRequest){  
    request=new XMLHttpRequest();  
    }  
    else if(window.ActiveXObject){  
    request=new ActiveXObject("Microsoft.XMLHTTP");  
    }  

    try{  
    request.onreadystatechange=getInfo;  
    request.open("post",url,true);  
    request.send();  
    }
    catch(e)
    {
        alert("Unable to connect to server");}  
    }  

    function getInfo(){  
    
        if(request.readyState==4){  
            var val=request.responseText;  
            
            if(val!="0")
            {
                var span = document.getElementById("sp"+val);
                span.style.color="blue";
            }
        }  
    }             
    
    
function ddlDepartmentvalidation()
{
    var ddl = document.getElementById("ddlDepartments");
    
    if(ddl.selectedIndex >0)
    {
        ddl.form.submit() ;
    }
}    