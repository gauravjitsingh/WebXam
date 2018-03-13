//function startTimer(duration, display) {
//    var start = Date.now(),
//        diff,
//        minutes,
//        seconds;
//    function timer() {
//        // get the number of seconds that have elapsed since 
//        // startTimer() was called
//        diff = duration - (((Date.now() - start) / 1000) | 0);
//
//        // does the same job as parseInt truncates the float
//        minutes = (diff / 60) | 0;
//        seconds = (diff % 60) | 0;
//
//        minutes = minutes < 10 ? "0" + minutes : minutes;
//        seconds = seconds < 10 ? "0" + seconds : seconds;
//
//        display.textContent = minutes + ":" + seconds; 
//
//        if (diff <= 0) {
//            // add one second so that the count down starts at the full duration
//            // example 05:00 not 04:59
//            start = Date.now() + 1000;
//        }
//    };
//    // we don't want to wait a full second before the timer starts
//    timer();
//    setInterval(timer, 1000);
//}
//
//window.onload = function examtimer (examduration) {
//    var fiveMinutes = examduration,
//        display = document.querySelector('#time');
//    startTimer(fiveMinutes, display);
//};


var mins, secs;
var ref;

setTimeout('f1()',500);

function f1(){
    var a = document.getElementById("Time");
    mins = parseInt(a.value) - 1;
    secs = 60;
  
    ref = setInterval('Timer()', 1000);
//    reftime = setInterval('TimeUpdate()', 5000);
}

function Timer() {
    secs--;
    if(secs == 0){
        if(mins>0){
            mins--;
            secs=59;
            var ctid = document.getElementById("ConductedTestId");
            var ConductedTestId1 = parseInt(ctid.value);
            TimeUpdate(mins, ConductedTestId1);
            }
    }
    if(secs<10)
    {
        document.getElementById("time").innerHTML = mins + " : 0" + secs;        
    }
    else if(mins<10&&secs<10){
        document.getElementById("time").innerHTML = "0"+ mins + " : 0" + secs;        
    }
    else if(mins<10)
    {
        document.getElementById("time").innerHTML = "0"+ mins + " : " + secs;                
    }
    else
    {
        document.getElementById("time").innerHTML = mins + " : " + secs;        
    }


    if(mins === 0 && secs === 0){
        clearInterval(ref);
        document.getElementById("btnSubmit").click();
    }
}

var request;  

  function TimeUpdate(pMinute,pConductedTestId)  
    {  
//    alert("GauravjitSingh");
    var Min = pMinute;
    var ConductedTestId = pConductedTestId;

    var url="http://localhost:8080/WebXam/ConductedTestServlet?TimeRemaining="+Min+"&ConductedTestId="+ConductedTestId;  

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
        }  
    }             
