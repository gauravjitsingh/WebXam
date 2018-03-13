function Confirmation(msg)
{
    var flag = window.confirm(msg);
    return flag ;
}


function validateData()
{
    var flag = true;
    
    var  n = document.getElementById("txtDeptName"); 
    var a = document.getElementById("txtDeptAdd"); 
    var c = document.getElementById("txtDeptCity"); 
    var cn = document.getElementById("txtDeptCno"); 
    var e = document.getElementById("txtDeptEmail"); 
    var cp = document.getElementById("txtDepCp"); 
    
    var sp1 = document.getElementById("msg1");
    var sp2 = document.getElementById("msg2");
    var sp3 = document.getElementById("msg3");
    var sp4 = document.getElementById("msg4");
    var sp5 = document.getElementById("msg5");
    var sp6 = document.getElementById("msg6");

    sp1.innerHTML="" ;
    sp2.innerHTML="" ;
    sp3.innerHTML="" ;
    sp4.innerHTML="" ;
    sp5.innerHTML="" ;
    sp6.innerHTML="" ;
    
    if(n.value.length===0)
        {
                sp1.innerHTML="Name empty!";
                flag = false ;
        }
        
    if(a.value.length===0)
        {
                sp2.innerHTML="Address empty!";
                flag = false ;
        }
        
    if(c.value.length===0)
        {
                sp3.innerHTML="City empty!";
                flag = false ;
        }
        
    if(cn.value.length===0)
        {
                sp4.innerHTML="Contact no empty!";
                flag = false ;
        }
        if( window.isNaN(cn.value)===true)
            {
                    sp4.innerHTML="Not a valid number";
                    flag = false ;
            }

    if(e.value.length===0)
        {
                sp5.innerHTML="Email empty!";
                flag = false ;
        }
    if(cp.value.length===0)
        {
                sp6.innerHTML="Contact Person empty!";
                flag = false ;
        }

        return flag ;
}

function ddlDepartmentvalidation()
{
    var ddl = document.getElementById("ddlDepartments");
    
    if(ddl.selectedIndex >0)
    {
        ddl.form.submit() ;
    }
}

function ddlCategoryvalidation()
{
    var ddl = document.getElementById("ddlCategory");
    
    if(ddl.selectedIndex >0)
    {
        ddl.form.submit() ;
    }
}
function radLevelvalidation()
{
    var rad = document.getElementById("radLevel").checked;
    
    if(rad === true)
    {
        rad.form.submit() ;
    }
}