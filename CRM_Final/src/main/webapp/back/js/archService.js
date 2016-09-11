



//查询
$(function(){  
   $('#archS_search').bind('click', function(){  
    	var params={};
    	if($("#arch_cname").val()!=null&&$("#arch_cname").val()!='undefined'){
			params['t.customer.cname']=$("#arch_cname").val();
		}
    	if($("#arch_sum").val()!=null&&$("#arch_sum").val()!='undefined'){
			params['t.summary']=$("#arch_sum").val();
		}
    	if($("#arch_stype").val()!=null&&$("#arch_stype").val()!='undefined'){
			params['t.servicetype.id']=$("#arch_stype").val();
		}
		if($("#arch_starttime").val()!=null&&$("#arch_starttime").val()!='undefined'){
			params['t.servicemindate']=$("#arch_starttime").datebox('getValue');
		}
		if($("#arch_endtime").val()!=null&&$("arch_endtime").val()!='undefined'){
			params['t.servicemaxdate']=$("#arch_endtime").datebox('getValue');
		}
		if($("#arch_statu").val()!=null&&$("arch_statu").val()!='undefined'){
			params['t.servicestatus.id']=$("#arch_statu").val();
		}
		//条件查询
		InitArchServiceTable('getServiceByCondition.action',params);
 });  
   

//加载出所有的下拉框
   $.ajax({
   	type:'POST',
   	url:'getServiceType.action',
   	dataType:'JSON',
   	success:function(data){
   		if(data.code==1){
   			$("#arch_stype").html("");
   			var html="<option value=''>请选择...</option>";
   			for (var i = 0; i < data.rows.length; i++) {
   				
					var type = data.rows[i];
					html+="<option value='"+type.id+"'>";
					html+=type.tiaomu+"</option>";
				}
   			
   			$("#arch_stype").html(html);
   		}
   	}
   });
   
  
	


    
    
    
    
});  


$('#arch_starttime').datebox({  
	formatter:$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+"-"+m+"-"+d;
	}


}); 

$('#arch_starttime').datebox({
	onSelect: function(date){
		$('#arch_starttime').datebox('setValue','');
		$('#arch_starttime').datebox('setValue',date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
	}
});

$('#arch_endtime').datebox({
	onSelect: function(date){
		$('#arch_endtime').datebox('setValue','');
		$('#arch_endtime').datebox('setValue',date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
	}
});

$('#arch_endtime').datebox({  
	formatter:$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+"-"+m+"-"+d;
	}


}); 





//列出所有客户
var dataObj;
var editRow=undefined;
var para={};
para['t.servicestatus.id']=40;
InitArchServiceTable('getNewCreateService.action',para);

//加载datagrid
function InitArchServiceTable(url,params){
	dataObj=$('#archService_list_table').datagrid({
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
	        	
	        	
	        		 return "<img onclick='archDetailService("+row.id+");' title='查看' src='../images/bt_detail.gif'/>";
	        	
	         
	   	 }}
				 
	        
	         ]]
	         
		});
}


function archDetailService(id){
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
			$("#archTable1").html(html);
			html="";
			html+="<tr><th>分配给</th><td>"+data.rows.allotperson.name+"</td><th>分配时间</th><td>"+data.rows.allotdateString+"</td></tr>";
			$("#archTable2").html(html);
			html="";
			html+="<tr><th>服务处理</th><td colspan='3'>"+data.rows.servicerequest+"</td></tr><tr><th>处理人</th><td>"+data.rows.dealperson.name+"</td><th>处理时间</th><td> "+data.rows.dealtimeString+"</td></tr>";
			$("#archTable3").html(html);
			html="";
			html+="<tr><th>处理结果</th><td>"+data.rows.dealresult+"</td><th>满意度</th><td>";
			for (var i = 0; i < data.rows.satisify.dvalue; i++) {
				html+="<img src='../images/star.jpg' class='star_pic' />";
				
			}
			
			html+="</tr>";
			$("#archTable4").html(html);
			
			$("#archServiceDialog").dialog("open");
		}
	
		
	});
}




	


