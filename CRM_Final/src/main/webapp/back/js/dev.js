$(function(){  
    $('#dev_search').unbind('click').bind('click', function(){  
    	var params={};
    	if($("#dev_cname").val()!=null&&$("#dev_cname").val()!='undefined'){
			params['t.contacter.cname']=$("#cname").val();
		}
		if($("#dev_summary").val()!=null&&$("#dev_summary").val()!='undefined'){
			params['t.chancesummary']=$("#dev_summary").val();
		}
		if($("#dev_contactName").val()!=null&&$("dev_contactName").val()!='undefined'){
			params['t.customername']=$("#dev_contactName").val();
		}
		//条件查询
		InitDevData('findChanceByCondition.action',params);
		
		
		
    });  
 
    
    
});  



var dataObj;
var editRow=undefined;
var params = {'t.chancestatus.id':6};
InitDevData('findChanceListPage.action',params);
function InitDevData(url,params){
	dataObj=$('#dev_list_table').datagrid({
		url:url,
		queryParams:params,
		fitColumns:true,
		loadMsg:'数据加载中',
		pagination:true,
		pageNumber:1,
		sortName:"id",
		remoteSort:false,
		columns:[[
	         
	         
	         {field:'id',title:'编号',width:100,align:'center',sortable:true},
	         {field:'customername',title:'客户名称',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'chancesummary',title:'概要',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'cname',title:'联系人',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.contacter){
						return row.contacter.cname;
					} else {
						return value;
					}
				}
	},
	         {field:'cellphone',title:'联系人电话',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.contacter){
						return row.contacter.cellphone;
					} else {
						return value;
					}
				}},
	         {field:'changecreatetime',title:'创建时间',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'dvalue',title:'状态',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.chancestatus){
						return row.chancestatus.tiaomu;
					} else {
						return value;
					}
				}},
	         {field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	   		if($("#currId").val()==2){
	   		 return "<img onclick='makePlan("+row.id+");' title='制定计划' src='../images/bt_plan.gif'/>";
	   		}else{
	        	 if(row.chancestatus.id==6){
	   		 return "<img onclick='makePlan("+row.id+");' title='制定计划' src='../images/bt_plan.gif'/>&nbsp;<img onclick='executePlan("+row.id+");' title='执行计划' src='../images/bt_feedback.gif'/>&nbsp;<img onclick='devSuccess("+row.id+");' title='开发成功' src='../images/bt_yes.gif'/>";
	   		}else{
	        	 return "<img onclick='dev_detail("+row.id+");' title='查看' src='../images/bt_detail.gif'/>";
	   		}
	   		}
	   	 } }
		  
	       
	         
	         ]]
	         
		});
}


//执行计划
function executePlan(id){
	
	$.ajax({
		type:'POST',
		url:'findAllPlanByChanceId.action',
		data:{'chance.id':id},
		dataType:'JSON',
		success:function(data){
			if(data.rows.length>0){
			var html="";
			html+="<tr><th>编号</th><td>"+data.rows[0].chance.id+"</td><th>机会来源</th><td>"+data.rows[0].chance.chancesource+"</td></tr>";
			html+="<tr><th>客户名称</th><td>"+data.rows[0].chance.customername+"</td><th>成功机率（%）</th><td>"+data.rows[0].chance.success+"</td></tr>";
			html+="<tr><th>概要</th><td colspan='3'>"+data.rows[0].chance.chancesummary+"</td></tr>"
			html+="<tr><th>联系人</th><td>"+(data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.cname)+"</td><th>联系人电话</th><td>"+(data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.telphone)+"</td></tr>";
			html+="<tr><th>机会描述</th><td colspan='3'>"+data.rows[0].chance.chancedescribe+"</td></tr>";
			html+="<tr><th>创建人</th><td>"+data.rows[0].chance.changecreatetime+"</td><th>创建时间</th><td>"+data.rows[0].chance.changecreatetime+"</td></tr>"
			html+="<tr><th>指派给</th><td>"+data.rows[0].chance.user.name+"</td><th>指派时间</th><td>"+data.rows[0].chance.changeassigntime+"</td></tr>"
			html+="</table>";
			$("#executePlan1").html(html);
			
			var str="<tr><th>日期</th><th>计划项</th><th>执行效果</th></tr>";
			for (var i = 0; i < data.rows.length; i++) {
				var obj = data.rows[i];
				str+="<tr><td class='list_data_text'>"+obj.plantimeString+"</td><td class='list_data_ltext'>"+obj.planitem+"</td><td class='list_data_ltext'><input value='"+obj.result+"'/>　<button class='common_button' onclick='saveExecutePlan("+i+","+obj.id+","+id+",\""+obj.plantimeString+"\",\""+obj.planitem+"\");'>保存</button></td></tr>";
			}
			$("#executePlan2").html(str);
			
			$("#endExecute").attr("onclick","");
			$("#endExecute").unbind('click').bind("click",function(){
				
				$.ajax({
					type:"POST",
					data:{				
						't.id':data.rows[0].chance.id,
						't.chancesource':data.rows[0].chance.chancesource,
						't.customername':data.rows[0].chance.customername,
						't.success':data.rows[0].chance.success,
						't.chancesummary':data.rows[0].chance.chancesummary,
						't.contacter.cname':data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.cname,
						't.contacter.telphone':data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.telphone,
						't.chancedescribe':data.rows[0].chance.chancedescribe,
						't.creater':data.rows[0].chance.creater,
						't.createtime':data.rows[0].chance.changecreatetime,
						't.user.id':data.rows[0].chance.user.id,
						't.assigntime':data.rows[0].chance.changeassigntime,
						't.chancestatus.id':8
						
						},
					url:'updateChanceUserInfo.action',
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'开发失败，已归档',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','原因：'+data.msg,'error');
						}
					}
				
			
			
		});
});
			
			
		}else{
			$.ajax({
				type:'POST',
				url:'getChanceDetail.action',
				data:{'t.id':id},
				dataType:'JSON',
				success:function(data){

					var html="";
					html+="<tr><th>编号</th><td>"+data.rows.id+"</td><th>机会来源</th><td>"+data.rows.chancesource+"</td></tr>";
					html+="<tr><th>客户名称</th><td>"+data.rows.customername+"</td><th>成功机率（%）</th><td>"+data.rows.success+"</td></tr>";
					html+="<tr><th>概要</th><td colspan='3'>"+data.rows.chancesummary+"</td></tr>"
					html+="<tr><th>联系人</th><td>"+(data.rows.contacter==null?"":data.rows.contacter.cname)+"</td><th>联系人电话</th><td>"+(data.rows.contacter==null?"":data.rows.contacter.telphone)+"</td></tr>";
					html+="<tr><th>机会描述</th><td colspan='3'>"+data.rows.chancedescribe+"</td></tr>";
					html+="<tr><th>创建人</th><td>"+data.rows.changecreatetime+"</td><th>创建时间</th><td>"+data.rows.changecreatetime+"</td></tr>"
					html+="<tr><th>指派给</th><td>"+data.rows.user.name+"</td><th>指派时间</th><td>"+data.rows.changeassigntime+"</td></tr>"
					html+="</table>"
					$("#executePlan1").html(html);
					
					var str="<tr><th>日期</th><th>计划项</th><th>执行效果</th></tr>";
					$("#executePlan2").html(str);
					$("#endExecute").attr("onclick","");
					$("#endExecute").unbind('click').bind("click",function(){
						
						$.ajax({
							type:"POST",
							data:{				
								't.id':data.rows.id,
								't.chancesource':data.rows.chancesource,
								't.customername':data.rows.customername,
								't.success':data.rows.success,
								't.chancesummary':data.rows.chancesummary,
								't.contacter.cname':data.rows.contacter==null?"":data.rows.contacter.cname,
								't.contacter.telphone':data.rows.contacter==null?"":data.rows.contacter.telphone,
								't.chancedescribe':data.rows.chancedescribe,
								't.creater':data.rows.creater,
								't.createtime':data.rows.changecreatetime,
								't.user.id':data.rows.user.id,
								't.assigntime':data.rows.changeassigntime,
								't.chancestatus.id':8
								
								},
							url:'updateChanceUserInfo.action',
							dataType:'JSON',
							success:function(data){
								if(data.code==1){
									$.messager.show({title:"成功提示",msg:'开发失败，已归档',timeout:3000,showType:'slide'});
									dataObj.datagrid('reload');
								}else{
									$.messager.alert('失败提示','原因：'+data.msg,'error');
								}
							}
						
					
					
				});
		});
				
				}
			});
		}
			
			
		}
	});
	
	
	$("#executePlan").dialog("open");
	
}

//查看
function dev_detail(id){
	$.ajax({
		type:'POST',
		url:'findAllPlanByChanceId.action',
		data:{'chance.id':id},
		dataType:'JSON',
		success:function(data){
			if(data.rows.length<=0){
				$.ajax({
					type:'POST',
					url:'getChanceDetail.action',
					data:{'t.id':id},
					dataType:'JSON',
					success:function(data){

						var html="";
						html+="<tr><th>编号</th><td>"+data.rows.id+"</td><th>机会来源</th><td>"+data.rows.chancesource+"</td></tr>";
						html+="<tr><th>客户名称</th><td>"+data.rows.customername+"</td><th>成功机率（%）</th><td>"+data.rows.success+"</td></tr>";
						html+="<tr><th>概要</th><td colspan='3'>"+data.rows.chancesummary+"</td></tr>"
						html+="<tr><th>联系人</th><td>"+(data.rows.contacter==null?"":data.rows.contacter.cname)+"</td><th>联系人电话</th><td>"+(data.rows.contacter==null?"":data.rows.contacter.telphone)+"</td></tr>";
						html+="<tr><th>机会描述</th><td colspan='3'>"+data.rows.chancedescribe+"</td></tr>";
						html+="<tr><th>创建人</th><td>"+data.rows.changecreatetime+"</td><th>创建时间</th><td>"+data.rows.changecreatetime+"</td></tr>"
						html+="<tr><th>指派给</th><td>"+data.rows.user.name+"</td><th>指派时间</th><td>"+data.rows.changeassigntime+"</td></tr>"
						html+="</table>"
						$("#devTable").html(html);
						
						var str="<tr><th>日期</th><th>计划</th><th>执行效果</th></tr>";
					
						$("#devTable2").html(str);
					
					}
				});
			}else{
						var html="";
						html+="<tr><th>编号</th><td>"+data.rows[0].chance.id+"</td><th>机会来源</th><td>"+data.rows[0].chance.chancesource+"</td></tr>";
						html+="<tr><th>客户名称</th><td>"+data.rows[0].chance.customername+"</td><th>成功机率（%）</th><td>"+data.rows[0].chance.success+"</td></tr>";
						html+="<tr><th>概要</th><td colspan='3'>"+data.rows[0].chance.chancesummary+"</td></tr>"
						html+="<tr><th>联系人</th><td>"+(data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.cname)+"</td><th>联系人电话</th><td>"+(data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.telphone)+"</td></tr>";
						html+="<tr><th>机会描述</th><td colspan='3'>"+data.rows[0].chance.chancedescribe+"</td></tr>";
						html+="<tr><th>创建人</th><td>"+data.rows[0].chance.changecreatetime+"</td><th>创建时间</th><td>"+data.rows[0].chance.changecreatetime+"</td></tr>"
						html+="<tr><th>指派给</th><td>"+data.rows[0].chance.user.name+"</td><th>指派时间</th><td>"+data.rows[0].chance.changeassigntime+"</td></tr>"
						html+="</table>";
						$("#devTable").html(html);
						
						var str="<tr><th>日期</th><th>计划</th><th>执行效果</th></tr>";
						for (var i = 0; i < data.rows.length; i++) {
							var obj = data.rows[i];
							str+="<tr><td class='list_data_text'>"+obj.plantimeString+"</td><td class='list_data_text'>"+obj.planitem+"</td><td class='list_data_text'>"+obj.result+"</td></tr>";
						}
						$("#devTable2").html(str);
					}
				
			
		}
		
	});
	
	
	$("#devDetailOption").dialog("open");
	
}



//制定计划
function makePlan(id){
	$.ajax({
		type:'POST',
		url:'findAllPlanByChanceId.action',
		data:{'chance.id':id},
		dataType:'JSON',
		success:function(data){
			if(data.rows.length>0){
				var html="";
				html+="<tr><th>编号</th><td>"+data.rows[0].chance.id+"</td><th>机会来源</th><td>"+data.rows[0].chance.chancesource+"</td></tr>";
				html+="<tr><th>客户名称</th><td>"+data.rows[0].chance.customername+"</td><th>成功机率（%）</th><td>"+data.rows[0].chance.success+"</td></tr>";
				html+="<tr><th>概要</th><td colspan='3'>"+data.rows[0].chance.chancesummary+"</td></tr>"
				html+="<tr><th>联系人</th><td>"+(data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.cname)+"</td><th>联系人电话</th><td>"+(data.rows[0].chance.contacter==null?"":data.rows[0].chance.contacter.telphone)+"</td></tr>";
				html+="<tr><th>机会描述</th><td colspan='3'>"+data.rows[0].chance.chancedescribe+"</td></tr>";
				html+="<tr><th>创建人</th><td>"+data.rows[0].chance.changecreatetime+"</td><th>创建时间</th><td>"+data.rows[0].chance.changecreatetime+"</td></tr>"
				html+="<tr><th>指派给</th><td>"+data.rows[0].chance.user.name+"</td><th>指派时间</th><td>"+data.rows[0].chance.changeassigntime+"</td></tr>"
				html+="</table>";
			$("#planTable1").html(html);
			
			var str="<tr><th>日期</th><th>计划项</th></tr>";
			for (var i = 0; i < data.rows.length; i++) {
				var obj = data.rows[i];
				str+="<tr><td class='list_data_text'>"+obj.plantimeString+"</td><td class='list_data_ltext'><input value='"+obj.planitem+"' />　<button class='common_button' onclick='savePlanTable2Item("+i+","+obj.id+","+id+",\""+obj.plantimeString+"\");'>保存</button><button class='common_button' onclick='deletePlanTable2Item("+id+","+obj.id+")'>删除</td></tr>";
			}
			$("#planTable2").html(str);
			
			
		}else{
			$.ajax({

				type:'POST',
				url:'getChanceDetail.action',
				data:{'t.id':id},
				dataType:'JSON',
				success:function(data){

					var html="";
					html+="<tr><th>编号</th><td>"+data.rows.id+"</td><th>机会来源</th><td>"+data.rows.chancesource+"</td></tr>";
					html+="<tr><th>客户名称</th><td>"+data.rows.customername+"</td><th>成功机率（%）</th><td>"+data.rows.success+"</td></tr>";
					html+="<tr><th>概要</th><td colspan='3'>"+data.rows.chancesummary+"</td></tr>"
					html+="<tr><th>联系人</th><td>"+(data.rows.contacter==null?" ":data.rows.contacter.cname)+"</td><th>联系人电话</th><td>"+(data.rows.contacter==null?" ":data.rows.contacter.telphone)+"</td></tr>";
					html+="<tr><th>机会描述</th><td colspan='3'>"+data.rows.chancedescribe+"</td></tr>";
					html+="<tr><th>创建人</th><td>"+data.rows.changecreatetime+"</td><th>创建时间</th><td>"+data.rows.changecreatetime+"</td></tr>"
					html+="<tr><th>指派给</th><td>"+data.rows.user.name+"</td><th>指派时间</th><td>"+data.rows.changeassigntime+"</td></tr>"
					html+="</table>"
					$("#planTable1").html(html);
					
					var str="<tr><th>日期</th><th>计划项</th></tr>";
					$("#planTable2").html(str);
				}
			
			});
		}
		}
	});
	setCurTime("dev_time");
	$("#makePlanSave").unbind().bind("click",function(){
		
		$.ajax({
			type:'POST',
			url:'insertPlan.action',
			data:{
				
				'chance.id':id,
				'plantime':$("#dev_time").val(),
				'planitem':$("#dev_planitem").val(),
				'result':''
			},
			dataType:'JSON',
			success:function(data){
				if(data.code==1){
					$.messager.show({title:"成功提示",msg:'计划保存成功',timeout:3000,showType:'slide'});
					setCurTime("dev_time");
					$("#dev_planitem").val("");
					reloadPlanTable2(id);
					
				}else{
					$.messager.alert('失败提示','计划保存失败,原因：'+data.msg,'error');
				}
			}
		
			
		});
	});
	
	if($("#currId").val()==2){
		$('#dev_exe').linkbutton('disable');
	}
	
	$("#makePlan").dialog("open");
	
}

function saveExecutePlan(i,id,cid,time,item){
	$.ajax({
		type:'POST',
		url:'updatePlan.action',
		data:{
			'id':id,
			'chance.id':cid,
			'plantime':time,
			'planitem':item,
			'result':$("#executePlan2 :input:eq("+i*2+")").val(),
			
		},
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'执行效果更新成功',timeout:3000,showType:'slide'});
				
			}else{
				$.messager.alert('失败提示','执行效果更新失败,原因：'+data.msg,'error');
			}
		}
	
		
	});
}

function savePlanTable2Item(i,id,cid,time){
	$.ajax({
		type:'POST',
		url:'updatePlan.action',
		data:{
			'id':id,
			'chance.id':cid,
			'plantime':time,
			'planitem':$("#planTable2 :input:eq("+i*2+")").val(),
			'result':'未执行'
		},
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'计划项更新成功',timeout:3000,showType:'slide'});
				dataObj.datagrid('reload');
			}else{
				$.messager.alert('失败提示','计划项更新失败,原因：'+data.msg,'error');
			}
		}
	});
}

function reloadPlanTable2(id){
	$.ajax({
		type:'POST',
		url:'findAllPlanByChanceId.action',
		data:{'chance.id':id},
		dataType:'JSON',
		success:function(data){
			
				
			
			var str="<tr><th>日期</th><th>计划项</th></tr>";
			for (var i = 0; i < data.rows.length; i++) {
				var obj = data.rows[i];
				str+="<tr><td class='list_data_text'>"+obj.plantimeString+"</td><td class='list_data_ltext'><input value='"+obj.planitem+"' />　<button class='common_button' onclick='savePlanTable2Item("+i+","+obj.id+","+id+",\""+obj.plantimeString+"\");'>保存</button><button class='common_button' onclick='deletePlanTable2Item("+id+","+obj.id+")'>删除</td></tr>";
			}
			$("#planTable2").html("");
			$("#planTable2").html(str);
			
			
		
		}
	});
}

function deletePlanTable2Item(cid,id){
	if(window.confirm("确认删除此的计划？")){
	$.ajax({
		type:'POST',
		url:'deletePlan.action',
		data:{
			'id':id,
		},
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'计划项删除成功',timeout:3000,showType:'slide'});
				reloadPlanTable2(cid)
			}else{
				$.messager.alert('失败提示','计划项删除失败,原因：'+data.msg,'error');
			}
		}
	
		
	});
	}
}
function devSuccess(id){
	$.ajax({
		type:'POST',
		url:'getChanceDetail.action',
		data:{'t.id':id},
		dataType:'JSON',
		success:function(data){
			
				$.ajax({
					type:"POST",
					data:{				
						't.id':data.rows.id,
						't.chancesource':data.rows.chancesource,
						't.customername':data.rows.customername,
						't.success':data.rows.success,
						't.chancesummary':data.rows.chancesummary,
						't.contacter.cname':data.rows.contacter==null?"":data.rows.contacter.cname,
						't.contacter.telphone':data.rows.contacter==null?"":data.rows.contacter.telphone,
						't.chancedescribe':data.rows.chancedescribe,
						't.creater':data.rows.creater,
						't.createtime':data.rows.changecreatetime,
						't.user.id':data.rows.user.id,
						't.assigntime':data.rows.changeassigntime,
						't.chancestatus.id':7
						
						},
					url:'updateChanceStatusAndInsertCustomer.action',
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'开发成功，已添加新客户记录',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','添加开发成功失败,原因：'+data.msg,'error');
						}
					}
				})
			
			
		}
	
	})
}

	
function closeDetailOption(){
	$("#devDetailOption").dialog({
		 closed: true
	});
	}
function closeexecutePlan(){
	$("#executePlan").dialog({
		 closed: true
	});
}

function closePlan(){
	$("#makePlan").dialog({
		 closed: true
	});
	
}

