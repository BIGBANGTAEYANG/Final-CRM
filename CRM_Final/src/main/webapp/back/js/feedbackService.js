//查询
$(function(){  
   $('#feedbackS_search').bind('click', function(){  
    	var params={};
    	if($("#feedback_cname").val()!=null&&$("#feedback_cname").val()!='undefined'){
			params['t.customer.cname']=$("#feedback_cname").val();
		}
    	if($("#feedback_sum").val()!=null&&$("#feedback_sum").val()!='undefined'){
			params['t.summary']=$("#feedback_sum").val();
		}
    	if($("#feedback_stype").val()!=null&&$("#feedback_stype").val()!='undefined'){
			params['t.servicetype.id']=$("#dis_stype").val();
		}
		if($("#feedback_starttime").val()!=null&&$("#feedback_starttime").val()!='undefined'){
			params['']=$("#dis_starttime").val();
		}
		if($("#feedback_endtime").val()!=null&&$("feedback_endtime").val()!='undefined'){
			params['']=$("#dis_endtime").val();
		}
		if($("#feedback_statu").val()!=null&&$("feedback_statu").val()!='undefined'){
			params['t.servicestatus.id']=$("#feedback_statu").val();
		}
		//条件查询
		InitDealServiceTable('.action',params);
 });  
   

//加载出所有的下拉框
   $.ajax({
   	type:'POST',
   	url:'getServiceType.action',
   	dataType:'JSON',
   	success:function(data){
   		if(data.code==1){
   			$("#feedback_stype").html("");
   			var html="<option value=''>请选择...</option>";
   			for (var i = 0; i < data.rows.length; i++) {
   				
					var type = data.rows[i];
					html+="<option value='"+type.id+"'>";
					html+=type.tiaomu+"</option>";
				}
   			
   			$("#feedback_stype").html(html);
   		}
   	}
   });
   
   $.ajax({
   	type:'POST',
   	url:'list_select.action',
   	dataType:'JSON',
   	success:function(data){
   		if(data.code==1){
   			
   			$("#feedback_sat").html("");
   			var html="<option value=''>请选择...</option>";
   			for (var i = 0; i < data.obj.satisfaction.length; i++) {
   				
					var level = data.obj.satisfaction[i];
					html+="<option value='"+level.id+"'>";
					for (var j = 0; j < Number(data.obj.satisfaction[i].dvalue); j++) {
						html+="☆";
					}
					html+="</option>";
				}
   		
   			$("#feedback_sat").html(html);
   			
   			
   		}
   	}
   });
	

	 $.ajax({
	    	type:'POST',
	    	url:'checkLogin.action',
	    	dataType:'JSON',
	    	success:function(data){
	    		$("#feedback_curr").val(data.obj.uname);
	    	}
	    });
    
    
    
    
});  


$('#dd').datebox({  
	formatter:$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+"年"+m+"月"+d+"日";
	}


}); 

$('#dd').datebox({
	onSelect: function(date){
		$('#dd').datebox('setValue','');
		$('#dd').datebox('setValue',date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
	}
});



//列出所有客户
var dataObj;
var editRow=undefined;
var para={};
para['t.servicestatus.id']=38;
InitDealServiceTable('getNewCreateService.action',para);

//加载datagrid
function InitDealServiceTable(url,params){
	dataObj=$('#feedbackService_list_table').datagrid({
		url:url,
		fitColumns:true,
		loadMsg:'数据加载中',
		pagination:true,
		pageNumber:1,
		sortName:"id",
		remoteSort:false,
		queryParams:params,
		singleSelect:true,
		columns:[[
	         {field:'id',title:'编号',width:100,align:'center',sortable:true,},
	         {field:'name',title:'客户',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.customer){
						return row.customer.cname;
					} else {
						return value;
					}
				}},
	         {field:'summary',title:'概要',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'type',title:'服务类型',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.servicetype){
						return row.servicetype.tiaomu;
					} else {
						return value;
					}
				}},
	         {field:'createperson',title:'创建人',width:100,align:'center',editor:{type:'text',options:{required:true}}},
			
				 {field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	        	
	        	
	        		 return "<img onclick='feedbackService("+row.id+");' title='处理' src='../images/bt_deal.gif'/>";
	        	
	         
	   	 }}
				 
	        
	         ]]
	         
		});
}


function feedbackService(id){
	$.ajax({
		type:'POST',
		url:'getServiceDetail.action',
		data:{'t.id':id},
		dataType:'JSON',
		success:function(data){
			var html="";
			html+="<tr><th>编号</th><td>"+data.rows.id+"</td><th>服务类型</th><td>"+data.rows.servicetype.tiaomu+"</td></tr><tr><th>概要</th><td colspan='3'>"+data.rows.summary+"</td></tr>	";
			html+="<tr><th>客户</th><td>"+data.rows.customer.cname+"</td><th>状态</th><td>"+data.rows.servicestatus.tiaomu+"</td></tr>	<tr><th>服务请求</th><td colspan='3'>"+data.rows.servicerequest+"<br></td></tr>";
			html+="<tr><th>创建人</th><td>"+data.rows.createperson+"</td><th>创建时间</th><td>"+data.rows.createdataString+"</td></tr>";
			$("#feedbackTable1").html(html);
			html="";
			html+="<tr><th>分配给</th><td>"+data.rows.allotperson.name+"</td><th>分配时间</th><td>"+data.rows.allotdateString+"</td></tr>";
			$("#feedbackTable2").html(html);
			html="";
			html+="<tr><th>服务处理</th><td colspan='3'>"+data.rows.servicedeal+"</td></tr><tr><th>处理人</th><td>"+data.rows.dealperson.name+"</td><th>处理时间</th><td> "+data.rows.dealtimeString+"</td></tr>";
			$("#feedbackTable3").html(html);
			$("#saveFeedbackService").bind('click',function(){
				$.ajax({
					type:"POST",
					data:{
						't.id':id,
						't.dealresult':$("#feedback_result").val(),
						't.satisify.id':$("#feedback_sat").val()
					},
					url:'feedbackSerice.action',
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'服务反馈添加成功',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','服务反馈添加失败,原因：'+data.msg,'error');
						}
					}
				})
			});
			
			$("#feedbackServiceDialog").dialog("open");
		}
	
		
	});
}


function parseTime(timeStr){
	var array = timeStr.split("-");
	return array;
}

	


