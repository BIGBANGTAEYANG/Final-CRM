function reload(){
	window.location.reload();
}


function setCusList(name){
	$.ajax({
    	type:'POST',
    	url:'findXiaoShouUserInfo.action',
    	dataType:'JSON',
    	success:function(data){
    		if(data.code==1){
    			$("#"+name).html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.length; i++) {
    				
					var cusManager = data.obj[i];
					html+="<option value='"+cusManager.id+"'>";
					html+=cusManager.name+"</option>";
				}
    			
    			$("#"+name).html(html);
    		}
    	}
    });
}

function setCurTime(oid){
	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	var timeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	var oCtl = document.getElementById(oid);
	oCtl.value = timeString;
}