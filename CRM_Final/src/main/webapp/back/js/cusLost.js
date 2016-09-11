//查询
$(function(){  
   $('#cusLost_search').bind('click', function(){  
    	var params={};
    	if($("#lost_name").val()!=null&&$("#lost_name").val()!='undefined'){
			params['t.orderInfo.customer.cname']=$("#lost_name").val();
		}
    	if($("#lost_manager").val()!=null&&$("#lost_manager").val()!='undefined'){
			params['t.orderInfo.customer.customermanager.name']=$("#lost_manager").val();
		}
    	if($("#lost_status").val()!=null&&$("#lost_status").val()!='undefined'){
			params['t.orderInfo.customer.customerstatus.id']=$("#lost_status").val();
		}
		
		//条件查询
		InitCusLostTable('search_lost.action',params);
 });  
   

//加载出所有的下拉框
	$.ajax({
    	type:'POST',
    	url:'list_selectstatus.action',
    	dataType:'JSON',
    	success:function(data){
    		if(data.code==1){
    			$("#lost_status").html("");
    			var html="<option value=''>全部...</option>";
    			for (var i =2; i < data.obj.length-1; i++) {
    				
					var level = data.obj[i];
					html+="<option value='"+level.id+"'>";
					html+=level.tiaomu+"</option>";
				}
    			
    			
    			$("#lost_status").html(html);
    			
    			
    			
    			
    			
    			
    		}
    	}
    });
 
});  








//列出所有客户
var dataObj;
var editRow=undefined;
InitCusLostTable('list_lost.action');

//加载datagrid
function InitCusLostTable(url,params){
	dataObj=$('#cusLost_list_table').datagrid({
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
	         {field:'order',title:'客户',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.orderInfo){
						return row.orderInfo.customer.cname;
					} else {
						return value;
					}
				}},
	         {field:'lost_man',title:'客户经理',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.orderInfo){
						return row.orderInfo.customer.customermanager.name;
					} else {
						return value;
					}
				}},
	         {field:'lastOrdertime',title:'上次下单时间',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
					if (row.orderInfo){
						return row.orderInfo.ordertimestr;
					} else {
						return value;
					}
				}},
			 {field:'lostdatestr',title:'确认流失时间',width:100,align:'center',editor:{type:'text',options:{required:true}}},
			{field:'loststatu',title:'状态',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
				if (row.orderInfo){
					return row.orderInfo.customer.customerstatus.tiaomu;
				} else {
					return value;
				}
			}},
				 {field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	        	
	        	
					 //return "<img onclick='confirmLost("+row.id+","+row.orderInfo.customer.id+",\""+row.orderInfo.customer.cname+"\",\""+row.orderInfo.customer.customermanager.name+"\",\""+row.orderInfo.ordertimestr+"\",\""+row.measure+"\");' title='确认流失' src='../images/bt_confirm.gif'/>&nbsp;<img onclick='replayLost("+row.id+","+row.orderInfo.customer.id+",\""+row.orderInfo.customer.cname+"\",\""+row.orderInfo.customer.customermanager.name+"\",\""+row.orderInfo.ordertimestr+"\",\""+row.measure+"\");' title='暂缓流失' src='../images/bt_relay.gif'/>";
					return "<img onclick='confirmLost("+index+","+row.id+");' title='确认流失' src='../images/bt_confirm.gif'/>&nbsp;<img onclick='replayLost("+index+","+row.id+");' title='暂缓流失' src='../images/bt_relay.gif'/>";
				        	
	         
	   	 }}
				 
	        
	         ]]
	         
		});
}
//return "<img onclick='confirmLost("+row.id+","+row.orderInfo.customer.id+",\""+row.orderInfo.customer.cname+"\",\""+row.orderInfo.customer.customermanager.name+"\",\""+row.orderInfo.ordertimestr+"\",\""+row.measure+"\");' title='确认流失' src='../images/bt_confirm.gif'/>&nbsp;<img onclick='replayLost("+row.id+","+row.orderInfo.customer.id+",\""+row.orderInfo.customer.cname+"\",\""+row.orderInfo.customer.customermanager.name+"\",\""+row.orderInfo.ordertimestr+"\",\""+row.measure+"\");' title='暂缓流失' src='../images/bt_relay.gif'/>";
//id,cid,cname,cusManager,time,mea
function confirmLost(index,id){
	var changb = "<span class='l-btn-left l-btn-icon-left'><span class='l-btn-text'>暂缓流失</span><span class='l-btn-icon icon-search'> </span></span>"
	$("#delayLost").html(changb);
	dataObj.datagrid('selectRow',index);
	var row = dataObj.datagrid('getSelected');
	$("#saveDelayLost").attr("onclick","");
	$("#saveDelayLost").attr("onclick","saveConfirm("+id+","+row.orderInfo.customer.id+");return false;");
	$("#cusLostDialog").dialog({
		title:"客户流失管理 > 确认流失",
	})
	var html="";
	html+="<tr><th>编号</th><td>"+id+"</td><th>客户</th><td>"+row.orderInfo.customer.cname+"</td></tr><tr><th>客户经理</th><td>"+row.orderInfo.customer.customermanager.name+"</td>";
	html+="<th>上次下单时间</th><td>"+row.orderInfo.ordertimestr+"</td></tr><tr><th>暂缓措施</th><td colspan='3'>"+row.measure+"</td></tr>";	
	html+="<tr><th>流失原因</th><td colspan='3'><textarea rows='6' cols='50' id='lostReason'></textarea><span class='red_star'>*</span></td></tr>";
	$("#cusLostTable1").html(html);
	$("#cusLostDialog").dialog("open");
}

function replayLost(index,id){
	var changb = "<span class='l-btn-left l-btn-icon-left'><span class='l-btn-text'>确认流失</span><span class='l-btn-icon icon-search'> </span></span>"
	$("#delayLost").html(changb);
	dataObj.datagrid('selectRow',index);
	var row = dataObj.datagrid('getSelected');
	$("#saveDelayLost").attr("onclick","");
	$("#saveDelayLost").attr("onclick","saveDelay("+id+","+row.orderInfo.customer.id+",\""+row.measure+"\");return false;");
	$("#cusLostDialog").dialog({
		title:"客户流失管理 > 暂缓流失",
	})
	var html="";
	html+="<tr><th>编号</th><td>"+id+"</td><th>客户</th><td>"+row.orderInfo.customer.cname+"</td></tr><tr><th>客户经理</th><td>"+row.orderInfo.customer.customermanager.name+"</td>";
	html+="<th>上次下单时间</th><td>"+row.orderInfo.ordertimestr+"</td></tr><tr><th>暂缓措施</th><td colspan='3'>"+row.measure+"</td></tr>";	
	html+="<tr><th>追加暂缓措施</th><td colspan='3'><textarea rows='6' cols='50' id='lostReason'></textarea><span class='red_star'>*</span></td></tr>";
	$("#cusLostTable1").html(html);
	$("#cusLostDialog").dialog("open");
}

function saveConfirm(id,cid){
	$.ajax({
		type:'POST',
		data:{
			't.id':id,
			't.lostreason':$("#lostReason").val(),
			't.orderInfo.customer.id':cid
		},
		url:'update_lost.action',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'确认流失成功',timeout:3000,showType:'slide'});
				$("#cusLostDialog").dialog({
					closed:true
				})
				dataObj.datagrid('reload');
			}else{
				$.messager.alert('失败提示','确认流失失败,原因：'+data.msg,'error');
			}
		}
	});
}


function saveDelay(id,cid,mea){
	$.ajax({
		type:'POST',
		data:{
			't.id':id,
			't.measure':mea+"\t"+$("#lostReason").val(),
			't.addmeasure':$("#lostReason").val(),
			't.orderInfo.customer.id':cid
		},
		url:'update_lost.action',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'确认流失成功',timeout:3000,showType:'slide'});
				$("#cusLostDialog").dialog({
					closed:true
				})
				dataObj.datagrid('reload');
			}else{
				$.messager.alert('失败提示','确认流失失败,原因：'+data.msg,'error');
			}
		}
	});
}







function closeAddLinkMan(){
	$("#addLinkMan").dialog({
		closed:true
	});
}




