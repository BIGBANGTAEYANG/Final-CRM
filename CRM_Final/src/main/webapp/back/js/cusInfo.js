//查询
$(function(){  
   $('#cusInfo_search').unbind('click').bind('click', function(){  
    	var params={};
    	if($("#id").val()!=null&&$("#id").val()!='undefined'){
			params['t.id']=$("#id").val();
		}
    	if($("#cname").val()!=null&&$("#cname").val()!='undefined'){
			params['t.cname']=$("#cname").val();
		}
    	if($("#location").val()!=null&&$("#location").val()!='undefined'){
			params['t.district.id']=$("#location").val();
		}
		if($("#manager").val()!=null&&$("#manager").val()!='undefined'){
			params['t.customermanager.name']=$("#manager").val();
		}
		if($("#level").val()!=null&&$("level").val()!='undefined'){
			params['t.grade.id']=$("#level").val();
		}
		//条件查询
		InitTable('customer_search.action',params);
		/*$.ajax({
			type:'POST',
			data:params,
			url:'http://192.168.1.120:8080/CRM/customer_search',
			dataType:'JSON',
			success:function(data){
				InitTable();
			}
		});*/
 });  
   

//加载出所有的下拉框
	$.ajax({
    	type:'POST',
    	url:'list_select.action',
    	dataType:'JSON',
    	success:function(data){
    		if(data.code==1){
    			$("#level").html("");
    			var html="<option value=''>全部...</option>";
    			for (var i = 0; i < data.obj.grade.length; i++) {
    				
					var level = data.obj.grade[i];
					html+="<option value='"+level.id+"'>";
					html+=level.tiaomu+"</option>";
				}
    			/*var map = data.obj;
    			for (var key in map) {  
    				html+="<option value='"+key+"'>";
    				html+=map[key]+"</option>";
    	        } */
    			
    			$("#level").html(html);
    			
    			
    			$("#location").html("");
    			var html="<option value=''>全部...</option>";
    			for (var i = 0; i < data.obj.district.length; i++) {
    				
					var location = data.obj.district[i];
					html+="<option value='"+location.id+"'>";
					html+=location.tiaomu+"</option>";
				}
    			/*var map = data.obj;
    			for (var key in map) {  
    				html+="<option value='"+key+"'>";
    				html+=map[key]+"</option>";
    	        }  */
    			
    			$("#location").html(html);
    			
    			
    			$("#cus_district").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.district.length; i++) {
    				
					var location = data.obj.district[i];
					html+="<option value='"+location.id+"'>";
					html+=location.tiaomu+"</option>";
				}
    			$("#cus_district").html(html);
    			
    			$("#cus_customermanager").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.customerman.length; i++) {
    				
					var location = data.obj.customerman[i];
					html+="<option value='"+location.id+"'>";
					html+=location.name+"</option>";
				}
    			$("#cus_customermanager").html(html);
    			
    			$("#cus_grade").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.grade.length; i++) {
    				
					var level = data.obj.grade[i];
					html+="<option value='"+level.id+"'>";
					html+=level.tiaomu+"</option>";
				}
    		
    			$("#cus_grade").html(html);
    			
    			$("#post").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.post.length; i++) {
    				
					var level = data.obj.post[i];
					html+="<option value='"+level.id+"'>";
					html+=level.tiaomu+"</option>";
				}
    		
    			$("#post").html(html);
    			
    			
    			$("#satisfaction").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.satisfaction.length; i++) {
    				
					var level = data.obj.satisfaction[i];
					html+="<option value='"+level.id+"'>";
					for (var j = 0; j < Number(data.obj.satisfaction[i].dvalue); j++) {
						html+="☆";
					}
					html+="</option>";
				}
    		
    			$("#satisfaction").html(html);
    			
    			$("#credit").html("");
    			var html="<option value=''>请选择...</option>";
    			for (var i = 0; i < data.obj.credit.length; i++) {
					var l = data.obj.credit[i];
					var v=Number(l.dvalue);
					html+="<option value='"+l.id+"'>";
					//html+=level.tiaomu+"</option>";
					
					for (var j = 0; j < v; j++) {
						html+="☆";
					}
					html+="</option>";
				}
    		
    			$("#credit").html(html);
    		}
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
InitTable('list_customer.action');

//加载datagrid
function InitTable(url,params){
	dataObj=$('#cus_list_table').datagrid({
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
	         
		    
	         {field:'cid',title:'序号',width:100,align:'center',formatter: function(value,row,index){
				return index+1;
				}},
	         {field:'id',title:'客户编号',width:100,align:'center',sortable:true,},
	         {field:'cname',title:'名称',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'location',title:'地区',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'name',title:'客户经理',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.customermanager){
						return row.customermanager.name;
					} else {
						return value;
					}
				}},
	         {field:'level',title:'客户等级',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.grade){
						return row.grade.tiaomu;
					} else {
						return value;
					}
				}},
	        {field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	        	if(row.customermanager!=null){
	        	if(row.customermanager.id==$("#currId").val()){
	        		 return "<img onclick='editCusInfo("+row.id+");' title='编辑' src='../images/bt_edit.gif'/>&nbsp;<img onclick='getContacterList("+index+","+row.id+");' title='联系人' src='../images/bt_linkman.gif'/>&nbsp;<img onclick='getActivities("+index+","+row.id+");' title='交往记录' src='../images/bt_acti.gif'/>&nbsp;<img onclick='getHistory("+index+","+row.id+");' title='历史订单' src='../images/bt_orders.gif'/>&nbsp;<img onclick='delCusInfo("+row.id+",\""+row.cname+"\");' title='删除' src='../images/bt_del.gif'/>";
	        	}else{
	        		 return "<img onclick='editCusInfo("+row.id+");' title='编辑' src='../images/bt_edit.gif'/>&nbsp;<img onclick='getContacterList("+index+","+row.id+");' title='联系人' src='../images/bt_linkman.gif'/>&nbsp;<img onclick='getActivities("+index+","+row.id+");' title='交往记录' src='../images/bt_acti.gif'/>&nbsp;<img onclick='getHistory("+index+","+row.id+");' title='历史订单' src='../images/bt_orders.gif'/>";
	        	}
	         
	   	 }else{
	   		 return "<img onclick='editCusInfo("+row.id+");' title='编辑' src='../images/bt_edit.gif'/>&nbsp;<img onclick='getContacterList("+index+","+row.id+");' title='联系人' src='../images/bt_linkman.gif'/>&nbsp;<img onclick='getActivities("+index+","+row.id+");' title='交往记录' src='../images/bt_acti.gif'/>&nbsp;<img onclick='getHistory("+index+","+row.id+");' title='历史订单' src='../images/bt_orders.gif'/>";
	        	
	   	 }
	        	}}
	        
	         ]]
	         
		});
}
//编辑
function editCusInfo(id){
	$.ajax({
		type:'POST',
		url:'list_customerbyid.action',
		data:{'t.id':id},
		dataType:'JSON',
		success:function(data){
			
			$("#editInfoOption").dialog({
				
				onOpen:function(){
					$("#cus_id").val(data.obj.id);
					$("#cus_cname").val(data.obj.cname);
					if(data.obj.district!=null){
					var count = $("#cus_district option").length;
					for (var i = 0; i < count; i++) {
						if($("#cus_district").get(0).options[i].text==data.obj.district.tiaomu){
							$("#cus_district").get(0).options[i].selected=true;
							break;
						}
					}
					}
					
		
					
					count=$("#cus_grade option").length;
					if(data.obj.grade!=null){
					for (var i = 0; i < count; i++) {
						if($("#cus_grade").get(0).options[i].text==data.obj.grade.tiaomu){
							$("#cus_grade").get(0).options[i].selected=true;
						}
					}
					}
					count=$("#satisfaction option").length;
					if(data.obj.satisfaction!=null){
					for (var i = 0; i < count; i++) {
						if($("#satisfaction").get(0).options[i].value==data.obj.satisfaction.id){
							$("#satisfaction").get(0).options[i].selected=true;
						}
					}
					}
					//$("#credit").val(data.obj.credit.tiaomu);
					if(data.obj.credit!=null){
					count=$("#credit option").length;
					for (var i = 0; i < count; i++) {
						if($("#credit").get(0).options[i].value==data.obj.credit.id){
							$("#credit").get(0).options[i].selected=true;
						}
					}
					}
					$("#cus_location").val(data.obj.location);
					$("#postcode").val(data.obj.postcode);
					$("#cus_telphone").val(data.obj.telphone);
					$("#fax").val(data.obj.fax);
					$("#website").val(data.obj.website);
					$("#license").val(data.obj.license);
					$("#legal").val(data.obj.legal);
					$("#registermoney").val(data.obj.registermoney);
					$("#yearincome").val(data.obj.yearincome);
					$("#bank").val(data.obj.bank);
					$("#bankaccount").val(data.obj.bankaccount);
					$("#rentnumber").val(data.obj.rentnumber);
					$("#taxnumber").val(data.obj.taxnumber);
					//$("#fax").attr("disabled",true),
					//$("#assigntime").attr("disabled",true),
					//$("#save").attr("onclick","editSaleOption();return false;")
					
				}
				
			});
			
			
			$("#editInfoOption").dialog("open");
			
		}
	});
}

//历史订单
function getHistory(cid,id){
	dataObj.datagrid('selectRow',cid);
	var row = dataObj.datagrid('getSelected');
	$('#linkSaveButton').linkbutton('disable');
	$("#linkmanList").dialog({
		title:"客户信息管理 > 客户信息 > 历史订单",
		onOpen:function(){
			$("#linkman_id").val(row.id),
			$("#linkman_cname").val(row.cname)
		}
	})
	
	$.ajax({
		type:'POST',
		data:{'customer.id':id},
		url:'list_orderinfo.action',
		dataType:'JSON',
		success:function(data){
			html="<tr><th>订单编号</th><th>日期</th><th>送货地址</th><th>状态</th><th>操作</th></tr>";
			for (var i = 0; i < data.obj.length; i++) {
				var obj = data.obj[i];
				html+="<tr><td class='list_data_text'>"+obj.id+"</td><td class='list_data_ltext'>"+obj.ordertimestr+"</td><td class='list_data_text'>"+obj.sendlocation+"</td>";
				html+="<td class='list_data_text'>"+obj.orderstatus.tiaomu+"</td>";
				html+="<td class='list_data_op'><img onclick='historyDetail("+id+","+obj.id+",\""+obj.ordertimestr+"\",\""+obj.sendlocation+"\",\""+obj.orderstatus.tiaomu+"\");' title='查看明细' src='../images/bt_detail.gif' class='op_button' /></td></tr>";
			}
			$("#linkman_table").html(html);
		}
	});
	
	$("#linkmanList").dialog("open");

}


//联系人
function getContacterList(cid,id){
	dataObj.datagrid('selectRow',cid);
	var row = dataObj.datagrid('getSelected');
	$("#linkSaveButton").unbind('click');
	$("#linkSaveButton").attr("onclick","");
	$("#linkSaveButton").attr("onclick","addLinkman("+id+");return false;");
	$("#linkmanList").dialog({
		title:"客户信息管理 > 客户信息 > 联系人",
		onOpen:function(){
			$("#linkman_id").val(row.id),
			$("#linkman_cname").val(row.cname)
		}
	})
	
	$.ajax({
		type:'POST',
		data:{'customer.id':id},
		url:'list_contacterbycustomerid.action',
		dataType:'JSON',
		success:function(data){
			html="<tr><th>姓名</th><th>性别</th><th>职位</th><th>办公电话</th><th>手机</th><th>备注</th><th>操作</th></tr>";
			for (var i = 0; i < data.obj.length; i++) {
				var obj = data.obj[i];
				html+="<tr><td class='list_data_text'>"+obj.cname+"</td><td class='list_data_ltext'>"+obj.sex.tiaomu+"</td><td class='list_data_text'>"+obj.post.tiaomu+"</td>";
				html+="<td class='list_data_text'>"+obj.telphone+"</td><td class='list_data_text'>"+obj.cellphone+"</td><td class='list_data_op'>"+obj.remark+"</td>";
				html+="<td class='list_data_op'><img onclick='editListman("+id+","+obj.id+",\""+obj.cname+"\","+obj.sex.id+",\""+obj.post.id+"\",\""+obj.telphone+"\",\""+obj.cellphone+"\",\""+obj.remark+"\");' title='编辑' src='../images/bt_edit.gif' class='op_button' /><img onclick='deleteLinkman("+id+","+obj.id+",\""+obj.cname+"\")' title='删除' src='../images/bt_del.gif' class='op_button' /></td></tr>";
			}
			$("#linkman_table").html(html);
		}
	});
	
	$("#linkmanList").dialog("open");
}
function deleteActivity(cid,id,summary){

	if(window.confirm("确认删除概要为:\t"+summary+"的历史纪录？")){
		$.ajax({
			type:'POST',
			url:'delete_contacthistory.action',
			data:{'id':id},
			dataType:'JSON',
			success:function(data){
				if(data.code==1){
					$.messager.show({title:"成功提示",msg:'联系人删除成功',timeout:3000,showType:'slide'});
					reloadActivity(cid);
				}else{
					$.messager.alert('失败提示','联系人删除失败,原因：'+data.msg,'error');
				}
			}
		});
	}
	

} 

function deleteLinkman(cid,id,cname){
	if(window.confirm("确认删除联系人:\t"+cname+"？")){
		$.ajax({
			type:'POST',
			url:'delete_contacterdetail.action',
			data:{'id':id},
			dataType:'JSON',
			success:function(data){
				if(data.code==1){
					$.messager.show({title:"成功提示",msg:'联系人删除成功',timeout:3000,showType:'slide'});
					reloadLinkman(cid);
				}else{
					$.messager.alert('失败提示','联系人删除失败,原因：'+data.msg,'error');
				}
			}
		});
	}
	
}


function getActivities(cid,id){
	dataObj.datagrid('selectRow',cid);
	var row = dataObj.datagrid('getSelected');
	$("#linkSaveButton").unbind('click');
	$("#linkSaveButton").attr("onclick","");
	$("#linkSaveButton").attr("onclick","addActivity("+id+");return false;");
	$("#linkmanList").dialog({
		title:"客户信息管理 > 客户信息 > 交往记录",
		onOpen:function(){
			$("#linkman_id").val(row.id),
			$("#linkman_cname").val(row.cname)
		}
	})
	
	$.ajax({
		type:'POST',
		data:{'customer.id':id},
		url:'list_contacthistory',
		dataType:'JSON',
		success:function(data){
			html="<tr><th width='232'>时间</th><th>地点</th><th>概要</th><th>详细信息</th><th>备注</th><th>操作</th></tr>";
			for (var i = 0; i < data.obj.length; i++) {
				var obj=data.obj[i];
				var array = parseTime(obj.contacttimestr);
				html+="<tr><td class='list_data_text'>"+array[0]+"年"+array[1]+"月"+array[2]+"日"+"</td><td class='list_data_ltext'>"+obj.place+"</td><td class='list_data_text'>"+obj.summary+"</td>";
				html+="<td class='list_data_text'>"+obj.item+"</td><td class='list_data_text'>"+obj.remark+"</td>";
				html+="<td class='list_data_op'><img onclick='editActivity("+id+","+obj.id+",\""+obj.contacttimestr+"\",\""+obj.place+"\",\""+obj.summary+"\",\""+obj.item+"\",\""+obj.remark+"\");' title='编辑' src='../images/bt_edit.gif' class='op_button' /><img onclick='deleteActivity("+id+","+obj.id+",\""+obj.summary+"\")' title='删除' src='../images/bt_del.gif' class='op_button' /></td></tr>";
				}
			$("#linkman_table").html(html);
		}
	});
	
	$("#linkmanList").dialog("open");
}

//保存编辑客户信息
function saveCusInfo(){

	
	$.ajax({
		type:'POST',
		data:{
			't.id':$("#cus_id").val(),
			't.cname':$("#cus_cname").val(),
			't.district.id':$("#cus_district").val(),
			't.customermanager.id':$("#cus_customermanager").val(),
			't.grade.id':$("#cus_grade").val(),
			't.satisfaction.id':$("#satisfaction").val(),
			't.credit.id':$("#credit").val(),
			't.location':$("#cus_location").val(),
			't.postcode':$("#postcode").val(),
			't.telphone':$("#cus_telphone").val(),
			't.fax':$("#fax").val(),
			't.website':$("#website").val(),
			't.license':$("#license").val(),
			't.legal':$("#legal").val(),
			't.registermoney':$("#registermoney").val(),
			't.yearincome':$("#yearincome").val(),
			't.bank':$("#bank").val(),
			't.bankaccount':$("#bankaccount").val(),
			't.rentnumber':$("#rentnumber").val(),
			't.taxnumber':$("#taxnumber").val()
							
		},
	url:'save_customerbyid.action',
	dataType:'JSON',
	success:function(data){
		if(data.code==1){
			$.messager.show({title:"成功提示",msg:'用户信息修改成功',timeout:3000,showType:'slide'});
			$("#editInfoOption").dialog({
				closed:true
			})
			dataObj.datagrid('reload');
		}else{
			$.messager.alert('失败提示','用户信息添加失败,原因：'+data.msg,'error');
		}
	}
	});

}

//新建交往记录
function addActivity(id){
	 closelinkmanList();
	 $("#saveNewActivity").unbind('click');
	 $("#saveNewActivity").attr("onclick","");
	 $("#saveNewActivity").unbind('click').bind('click',function(){
		 $.ajax({
			 type:'POST',
			 data:{
				 'customer.id':id,
				 'contacttime':$("#dd").datebox('getValue'),
				 'place':$("#act_loc").val(),
				 'summary':$("#act_sum").val(),
				 'remark':$("#act_rem").val(),
				 'item':$("#act_detail").val()
			 },
			 url:'insert_contacthistory.action',
			 dataType:'JSON',
			 success:function(data){
					if(data.code==1){
						$.messager.show({title:"成功提示",msg:'交往记录添加成功',timeout:3000,showType:'slide'});
						reloadActivity(id);
						$("#act_loc").val(""),
						 $("#act_sum").val(""),
						$("#act_rem").val(""),
						$("#act_detail").val("");
						
					}else{
						$.messager.alert('失败提示','交往记录添加失败,原因：'+data.msg,'error');
					}
				}
		 });
	 });
	 $("#addActivity").dialog({
			title:"客户信息管理 > 客户信息 > 交往记录 > 新建交往记录",
			onOpen:function(){
				
				 $("#act_loc").val(""),
				 $("#act_sum").val(""),
				 $("#act_rem").val(""),
				 $("#act_detail").val("")
				
			}
		});
	$("#addActivity").dialog("open");
}

//新建联系人
function addLinkman(id){
	 closelinkmanList();
	
	 $("#saveNewLinkman").unbind('click').bind('click',function(){
		 $.ajax({
			 type:'POST',
			 data:{
				 'customer.id':id,
				 'cname':$("#link_cname").val(),
				 'sex.id':$("input[name='gander']:checked").val(),
				 'post.id':$("#post").val(),
				 'telphone':$("#link_tel").val(),
				 'cellphone':$("#link_cell").val(),
				 
				 'remark':$("#remark").val()
			 },
			 url:'save_contacterdetail',
			 dataType:'JSON',
			 success:function(data){
					if(data.code==1){
						$.messager.show({title:"成功提示",msg:'联系人添加成功',timeout:3000,showType:'slide'});
						reloadLinkman(id);
						$("#link_cname").val("");
						$("#post").val("");
						$("#link_tel").val("");
						$("#link_cell").val("");
						
					}else{
						$.messager.alert('失败提示','联系人添加失败,原因：'+data.msg,'error');
					}
				}
		 });
	 });
	 $("#addLinkMan").dialog({
			title:"客户信息管理 > 客户信息 > 联系人 > 新建联系人",
			onOpen:function(){
				 $("#link_cname").val(""),
				 $("input[name='gander'][value="+26+"]").attr("checked",true),
				 $("#post").val(""),
				 $("#link_tel").val(""),
				 $("#link_cell").val(""),
				 $("#remark").val("")
			}
		});
	$("#addLinkMan").dialog("open");
}

function historyDetail(cid,id,ordertime,sendlocation,orderstatus){
	var total=0;
	$.ajax({
		type:'POST',
		url:'list_orderitem.action',
		data:{'order.id':id},
		dataType:'JSON',
		success:function(data){
			var str = "<tr><th>商品</th><th>数量</th><th>单位</th><th>单价（元）</th><th>金额（元）</th></tr>";
			for (var i = 0; i < data.obj.length; i++) {
				var obj = data.obj[i];
				total+=obj.countprice;
				str+="<tr><td class='list_data_text'>"+obj.pname+"</td>";
				str+="<td class='list_data_ltext'>"+obj.ordernumber+"</td><td class='list_data_text'>"+obj.unit+"</td><td class='list_data_text'>"+obj.price+"</td><td class='list_data_text'>"+obj.countprice+"</td></tr>"	
			}
			$("#detailTable2").html(str);
			var html="";
			 html+="<tr><th>订单编号</th><td>"+id+"</td><th>日期</th><td>"+ordertime+"</td></tr><tr><th height='28'>送货地址</th><td>"+sendlocation+"　</td><th height='28'>总金额（元）</th><td>"+total+"</td></tr><tr><th height='28'>状态</th><td>"+orderstatus+"</td><th height='28'>&nbsp;</th><td>&nbsp;</td></tr>";
			 $("#detailTable1").html(html);
			 $("#historyDetail").dialog({
					title:"客户信息管理 > 客户信息 > 历史订单 > 订单明细 ",
					
				})
			 $("#historyDetail").dialog("open");
		}
	});
	
	
}

//编辑历史纪录id+","+obj.id+",\""+obj.contacttimestr+"\","+obj.place+",\""+obj.summary+"\",\""+obj.item+"\",\""+obj.remark+"
function editActivity(cid,id,time,place,sum,item,remark){
	
	 closelinkmanList();
	 $("#addActivity").dialog({
			title:"客户信息管理 > 客户信息 > 交往记录 > 编辑交往记录",
			onOpen:function(){
				 $("#dd").datebox('setValue',time),
				 $("#act_loc").val(place),
				 $("#act_sum").val(sum),
				 $("#act_rem").val(remark),
				 $("#act_detail").val(item)
				
			}
		});
	 $("#saveNewActivity").unbind('click');
		$("#saveNewActivity").attr("onclick","");
		$("#saveNewActivity").attr("onclick","saveEditActivity("+cid+","+id+");return false;");
		$("#addActivity").dialog("open");
	
	
}

//编辑联系人
function editListman(cid,id,name,sex,option,tel,cel,remark){
	 closelinkmanList();
	 $("#addLinkMan").dialog({
			title:"客户信息管理 > 客户信息 > 联系人 > 编辑联系人",
			onOpen:function(){
				 $("#link_cname").val(name),
				 $("input[name='gander'][value="+sex+"]").attr("checked",true),
				 $("#post").val(option),
				 $("#link_tel").val(tel),
				 $("#link_cell").val(cel),
				 $("#remark").val(remark)
			}
		});
	 $("#saveNewLinkman").unbind('click');
		$("#saveNewLinkman").attr("onclick","");
		$("#saveNewLinkman").attr("onclick","saveEditLinkman("+cid+","+id+");return false;");
		$("#addLinkMan").dialog("open");
	
	
}
//保存编辑联系人信息
function saveEditLinkman(cid,id){
	
	$.ajax({
		type:'POST',
		data:{
			'customer.id':cid,
			'id':id,
			 'cname':$("#link_cname").val(),
			 'sex.id':$("input[name='gander']:checked").val(),
			 'post.id':$("#post").val(),
			 'telphone':$("#link_tel").val(),
			 'cellphone':$("#link_cell").val(),
			 'remark':$("#remark").val()
			
		},
		url:'update_contacterdetail.action',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'联系人编辑成功',timeout:3000,showType:'slide'});
				reloadLinkman(cid);
			/*	 $("#link_cname").val("");
				 $("#post").val("");
				$("#link_tel").val("");
				 $("#link_cell").val("");
				$("#remark").val("");*/
			}else{
				$.messager.alert('失败提示','联系人编辑失败,原因：'+data.msg,'error');
			}
		}
	});
}


//保存编辑历史纪录

function saveEditActivity(cid,id){
	
	$.ajax({
		type:'POST',
		data:{
			'customer.id':cid,
			'id':id,
			 'contacttime':$("#dd").datebox('getValue'),
			 'place':$("#act_loc").val(),
			 'summary':$("#act_sum").val(),
			 'remark':$("#act_rem").val(),
			 'item':$("#act_detail").val()
			
		},
		url:'update_contacthistory.action',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'历史纪录编辑成功',timeout:3000,showType:'slide'});
				reloadActivity(cid);
				 
				$("#act_loc").val("");
				$("#act_sum").val("");
				$("#act_rem").val("");
				 $("#act_detail").val("");
			}else{
				$.messager.alert('失败提示','历史纪录编辑失败,原因：'+data.msg,'error');
			}
		}
	});
}

//重新加载联系人列表
function reloadLinkman(cid){
	$.ajax({
		type:'POST',
		data:{'customer.id':cid},
		url:'list_contacterbycustomerid.action',
		dataType:'JSON',
		success:function(data){
			
			html="<tr><th>姓名</th><th>性别</th><th>职位</th><th>办公电话</th><th>手机</th><th>备注</th><th>操作</th></tr>";
			for (var i = 0; i < data.obj.length; i++) {
				var obj = data.obj[i];
				html+="<tr><td class='list_data_text'>"+obj.cname+"</td><td class='list_data_ltext'>"+obj.sex.tiaomu+"</td><td class='list_data_text'>"+obj.post.tiaomu+"</td>";
				html+="<td class='list_data_text'>"+obj.telphone+"</td><td class='list_data_text'>"+obj.cellphone+"</td><td class='list_data_op'></td>";
				html+="<td class='list_data_op'><img onclick='editListman("+cid+","+obj.id+",\""+obj.cname+"\","+obj.sex.id+",\""+obj.post.id+"\",\""+obj.telphone+"\",\""+obj.cellphone+"\",\""+obj.remark+"\");' title='编辑' src='../images/bt_edit.gif' class='op_button' /><img onclick='deleteLinkman("+cid+","+obj.id+",\""+obj.cname+"\")' title='删除' src='../images/bt_del.gif' class='op_button' /></td></tr>";
				}
			$("#linkman_table").html("");
			$("#linkman_table").html(html);
			$("addLinkMan").dialog({
				closed:true
			});
			//$("#linkmanList").dialog("open");
		}
	});
}


//重新加载交往记录列表
function reloadActivity(cid){
	$.ajax({
		type:'POST',
		data:{'customer.id':cid},
		url:'list_contacthistory.action',
		dataType:'JSON',
		success:function(data){
			
			html="<tr><th width='232'>时间</th><th>地点</th><th>概要</th><th>详细信息</th><th>备注</th><th>操作</th></tr>";
			for (var i = 0; i < data.obj.length; i++) {
				var obj=data.obj[i];
				var array = parseTime(obj.contacttimestr);
				html+="<tr><td class='list_data_text'>"+array[0]+"年"+array[1]+"月"+array[2]+"日"+"</td><td class='list_data_ltext'>"+obj.place+"</td><td class='list_data_text'>"+obj.summary+"</td>";
				html+="<td class='list_data_text'>"+obj.item+"</td><td class='list_data_text'>"+obj.remark+"</td>";
				html+="<td class='list_data_op'><img onclick='editActivity("+id+","+obj.id+",\""+obj.contacttimestr+"\","+obj.place+",\""+obj.summary+"\",\""+obj.item+"\",\""+obj.remark+"\");' title='编辑' src='../images/bt_edit.gif' class='op_button' /><img onclick='deleteActivity("+id+","+obj.id+",\""+obj.summary+"\")' title='删除' src='../images/bt_del.gif' class='op_button' /></td></tr>";
				}
			$("#linkman_table").html(html);
			$("addActivity").dialog({
				closed:true
			});
			$("#linkmanList").dialog("open");
		}
	});
}

function delCusInfo(id,name){


	if(window.confirm("确认删除客户:\t"+name+"？")){
		$.ajax({
			type:'POST',
			url:'delete_customerbyid.action',
			data:{'t.id':id},
			dataType:'JSON',
			success:function(data){
				if(data.code==1){
					$.messager.show({title:"成功提示",msg:'客户删除成功',timeout:3000,showType:'slide'});
					dataObj.datagrid('reload');
				}else{
					$.messager.alert('失败提示','客户删除失败,原因：'+data.msg,'error');
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


