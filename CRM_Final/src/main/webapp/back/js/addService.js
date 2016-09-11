$(function(){
	$.ajax({
    	type:'POST',
    	url:'checkLogin.action',
    	dataType:'JSON',
    	success:function(data){
    		$("#service_curr").val(data.obj.uname);
    	}
    });
	
	
	$.ajax({
    	type:'POST',
    	url:'getServiceCustomer.action',
    	dataType:'JSON',
    	success:function(data){
    		if(data.code==1){
    			$("#service_cus").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.rows.length; i++) {
    				
					var type = data.rows[i];
					html+="<option value='"+type.id+"'>";
					html+=type.cname+"</option>";
				}
    			
    			$("#service_cus").html(html);
    		}
    	}
    });
	
	$.ajax({
    	type:'POST',
    	url:'getServiceType.action',
    	dataType:'JSON',
    	success:function(data){
    		if(data.code==1){
    			$("#service_type").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.rows.length; i++) {
    				
					var type = data.rows[i];
					html+="<option value='"+type.id+"'>";
					html+=type.tiaomu+"</option>";
				}
    			
    			$("#service_type").html(html);
    		}
    	}
    });
	
	setCurTime("service_createtime");
	
});

function saveNewService(){
	$.ajax({
		type:'POST',
		url:'createNewService.action',
		data:{
			't.servicetype.id':$("#service_type").val(),
			't.summary':$("#service_sum").val(),
			't.customer.id':$("#service_cus").val(),
			't.servicestatus.id':$("#service_status").val(),
			't.servicerequest':$("#service_req").val(),
			't.createperson':$("#service_curr").val(),
			't.createdate':$("#service_createtime").val(),
			't.servicerequest':$("#service_req").val(),
		},
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'服务创建成功',timeout:3000,showType:'slide'});
			}else{
				$.messager.alert('失败提示','服务创建添加失败,原因：'+data.msg,'error');
			}
		}
	});
	
}
