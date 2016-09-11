//查询
$(function(){  
   $('#disS_search').bind('click', function(){  
    	var params={};
    	if($("#dis_cname").val()!=null&&$("#dis_cname").val()!='undefined'){
			params['t.customer.cname']=$("#dis_cname").val();
		}
    	if($("#dis_sum").val()!=null&&$("#dis_sum").val()!='undefined'){
			params['t.summary']=$("#dis_sum").val();
		}
    	if($("#dis_stype").val()!=null&&$("#dis_stype").val()!='undefined'){
			params['t.servicetype.id']=$("#dis_stype").val();
		}
		if($("#dis_starttime").val()!=null&&$("#dis_starttime").val()!='undefined'){
			params['']=$("#dis_starttime").val();
		}
		if($("#dis_endtime").val()!=null&&$("dis_endtime").val()!='undefined'){
			params['']=$("#dis_endtime").val();
		}
		if($("#dis_statu").val()!=null&&$("dis_statu").val()!='undefined'){
			params['t.servicestatus.id']=$("#dis_statu").val();
		}
		//条件查询
		InitDisServiceTable('.action',params);
 });  
   

//加载出所有的下拉框
   $.ajax({
	   	type:'POST',
	   	url:'getServiceType.action',
	   	dataType:'JSON',
	   	success:function(data){
	   		if(data.code==1){
	   			$("#dis_stype").html("");
	   			var html="<option value=''>请选择...</option>";
	   			for (var i = 0; i < data.rows.length; i++) {
	   				
						var type = data.rows[i];
						html+="<option value='"+type.id+"'>";
						html+=type.tiaomu+"</option>";
					}
	   			
	   			$("#dis_stype").html(html);
	   		}
	   	}
	   });
 
    
});  

var saleManageList1="";
var saleManageList2="";
$.ajax({
	type:'POST',
	url:'findXiaoShouUserInfo.action',
	dataType:'JSON',
	success:function(data){
		if(data.code==1){
			
			saleManageList1="<select id='saleManager"
			saleManageList2="'><option value='' >请选择...</option>";
			for (var i = 0; i < data.obj.length; i++) {
				
				var cusManager = data.obj[i];
				saleManageList2+="<option value='"+cusManager.id+"'>";
				saleManageList2+=cusManager.name+"</option>";
			}
			
			saleManageList2+="</select>"
		}
	}
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
para['t.servicestatus.id']=36;
InitDisServiceTable('getNewCreateService.action',para);

//加载datagrid
function InitDisServiceTable(url,params){
	dataObj=$('#disService_list_table').datagrid({
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
			 {field:'createdataString',title:'创建日期',width:100,align:'center',editor:{type:'text',options:{required:true}}},
				  {field:'dis',title:'分配给',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
			        	
			        	
			        		 return saleManageList1+index+saleManageList2+"<button class='common_button' onclick='dispatchService("+row.id+","+index+",\""+row.createdataString+"\");'>分配</button>"
			         
			   	 }},
				 {field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	        	
	        	
	        		 return "<img onclick='delService("+row.id+",\""+row.summary+"\")' title='删除' src='../images/bt_del.gif'/>";
	        	
	         
	   	 }}
				 
	        
	         ]]
	         
		});
}

function dispatchService(id,index,createdate){
	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	var timeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	var cusId = $("#saleManager"+index).val();
	alert(cusId);
	$.ajax({
		type:'POST',
		url:'allotService.action',
		
		data:{
			't.id':id,
			't.allotperson.id':cusId,
			't.servicestatus.id':37,
			't.allotdate':timeString
		},
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'服务分配成功',timeout:3000,showType:'slide'});
				dataObj.datagrid('reload');
			}else{
				$.messager.alert('失败提示','服务分配失败,原因：'+data.msg,'error');
			}
		}
	
	});
}

function delService(id,name){


	if(window.confirm("确认删除概要为:\t"+name+"客户的服务？")){
		$.ajax({
			type:'POST',
			url:'deleteSerice.action',
			data:{'t.id':id},
			dataType:'JSON',
			success:function(data){
				if(data.code==1){
					$.messager.show({title:"成功提示",msg:'服务删除成功',timeout:3000,showType:'slide'});
					dataObj.datagrid('reload');
				}else{
					$.messager.alert('失败提示','服务删除失败,原因：'+data.msg,'error');
				}
			}
		});
	}
	


}

function parseTime(timeStr){
	var array = timeStr.split("-");
	return array;
}

	
function closeDispatchOption(){
	$("#dispatchSaleOption").dialog({
		 closed: true
	});
	}

function closeInfoOption(){
	$("#editInfoOption").dialog({
		 closed: true
	});
	
}

function closelinkmanList(){
	$("#linkmanList").dialog({
		closed:true
	});
}

function closeAddLinkMan(){
	$("#addLinkMan").dialog({
		closed:true
	});
}

function closeAddActivity(){
	$("#addActivity").dialog({
		closed:true
	});
}


