//查询
$(function(){  
   $('#dealS_search').bind('click', function(){  
    	var params={};
    	if($("#deal_cname").val()!=null&&$("#deal_cname").val()!='undefined'){
			params['t.customer.cname']=$("#deal_cname").val();
		}
    	if($("#deal_sum").val()!=null&&$("#deal_sum").val()!='undefined'){
			params['t.summary']=$("#deal_sum").val();
		}
    	if($("#deal_stype").val()!=null&&$("#deal_stype").val()!='undefined'){
			params['t.servicetype.id']=$("#dis_stype").val();
		}
		if($("#deal_starttime").val()!=null&&$("#deal_starttime").val()!='undefined'){
			params['']=$("#dis_starttime").val();
		}
		if($("#deal_endtime").val()!=null&&$("deal_endtime").val()!='undefined'){
			params['']=$("#dis_endtime").val();
		}
		if($("#deal_statu").val()!=null&&$("deal_statu").val()!='undefined'){
			params['t.servicestatus.id']=$("#deal_statu").val();
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
   			$("#deal_stype").html("");
   			var html="<option value=''>请选择...</option>";
   			for (var i = 0; i < data.rows.length; i++) {
   				
					var type = data.rows[i];
					html+="<option value='"+type.id+"'>";
					html+=type.tiaomu+"</option>";
				}
   			
   			$("#deal_stype").html(html);
   		}
   	}
   });
	

	 $.ajax({
	    	type:'POST',
	    	url:'checkLogin.action',
	    	dataType:'JSON',
	    	success:function(data){
	    		$("#deal_curr").val(data.obj.uname);
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
para['t.servicestatus.id']=37;
InitDealServiceTable('getNewCreateService.action',para);

//加载datagrid
function InitDealServiceTable(url,params){
	dataObj=$('#dealService_list_table').datagrid({
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
	        	
	        	
	        		 return "<img onclick='dealService("+row.id+");' title='处理' src='../images/bt_deal.gif'/>";
	        	
	         
	   	 }}
				 
	        
	         ]]
	         
		});
}


function dealService(id){
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
			$("#dealTable1").html(html);
			var html="";
			html+="<tr><th>分配给</th><td>"+data.rows.allotperson.name+"</td><th>分配时间</th><td>"+data.rows.allotdateString+"</td></tr>";
			$("#dealTable2").html(html);
			$("#saveDealService").bind('click',function(){
				$.ajax({
					type:"POST",
					data:{
						't.servicedeal':$("#deal_result").val(),
						't.dealperson.id':$("#currId").val(),
						't.dealtime':$("#deal_time").val(),
						't.servicestatus.id':38,
						't.id':id
					},
					url:'DelService.action',
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'服务处理添加成功',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','服务处理添加失败,原因：'+data.msg,'error');
						}
					}
				})
			});
			setCurTime("deal_time");
			$("#dealServiceDialog").dialog("open");
		}
	
		
	});
}


function parseTime(timeStr){
	var array = timeStr.split("-");
	return array;
}

	


