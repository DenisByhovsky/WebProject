function addInput() {
   var id = document.getElementById("default-id").value;
  id++;
   $("#testform").append('<div id="div-' + id + '"><input name="input-' + id + '" id="input-' + id + '"   placeholder="extra@your.mail"  required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"><a href="javascript:{}" onclick="removeInput(\'' + id + '\')">Удалить</a></div>');
 if(id<1){
  document.getElementById("default-id").value = id;
}
}

function removeInput(id) {
$("#div-" + id).remove();
}


function isRavno(form,password,confirm,pass22,submit)
{
    PASSWORD=document.forms[form].password.value;
    PASSWORDcount=document.forms[form].password.value.length;
    CONFIRM=document.forms[form].confirm.value;
    PASS22=document.getElementById(pass22);
    SUBMIT=document.forms[form].submit;
    if(PASSWORD==CONFIRM)
    {
        PASS22.style.border="1px solid #446B01";
        PASS22.style.background="#E0FFB3";
        PASS22.style.color="#558701";
        PASS22.innerHTML="Пароли совпадают";
        if(PASSWORDcount>=min_length)
            SUBMIT.disabled=0;
    }
    else
    {
        PASS22.style.border="1px solid #A40004";
        PASS22.style.background="#FFD7E9";
        PASS22.style.color="#D5172B";
        PASS22.innerHTML="Hе совпадают";
        SUBMIT.disabled=1;
    }
}

