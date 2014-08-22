var a="";
var b="";
var c=",";
        
function sharing()
    {
        var countid = document.all("share").length;
        
        for(var i=0;i<countid;i++)
            {
        a=document.all("share",i).value;
           if(i>0)
                {
                    b=a+c+b;
                }  
            else
                {
                     b=a+b;
                }                
            }                 
               document.share_form.hvalue.value=b;
               a="";
               b=""; 
               document.share_form.submit();
    }