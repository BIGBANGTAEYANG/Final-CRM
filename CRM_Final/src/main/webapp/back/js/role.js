$(function(){  
   
 
   
    $.ajax({
    	type:'POST',
    	url:'getSelectList.action',
    	dataType:'JSON',
    	success:function(data){
    		if(data.code==1){
    			$("#role_name").html("");
    			$("#role_r").html("");
    			var html="<option value=''>全部...</option>";
    			for (var i =0; i < data.rows.length; i++) {
    				
					var role = data.rows[i];
					html+="<option value='"+role.id+"'>";
					html+=role.tiaomu+"</option>";
				}
    			
    			
    			$("#role_name").html(html);
    			$("#role_r").html(html);
    		}
    	}
    });
    
   
    
  
    
    
    
    
});  


var dataObj;
var editRow=undefined;
InitRoleData('findAllUserInfo.action');
function InitRoleData(url,params){
	dataObj=$('#data_list_table').datagrid({
		url:url,
		fitColumns:true,
		loadMsg:'数据加载中',
		pagination:true,
		pageNumber:1,
		sortName:"id",
		queryParams:params,
		remoteSort:false,
		columns:[[
	         
	         
	         {field:'id',title:'编号',width:100,align:'center',sortable:true},
	         {field:'name',title:'用户名称',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'sex',title:'性别',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.sex){
						return row.sex.tiaomu;
					} else {
						return value;
					}
				}
	},
	         {field:'role',title:'角色',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.role){
						return row.role.tiaomu;
					} else {
						return value;
					}
				}},
	         {field:'userage',title:'年龄',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	        {field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	   		
	        	return "<img onclick='EditRoleDetail("+row.id+");' title='编辑' src='../images/bt_edit.gif'/>&nbsp;<img onclick='delRole("+row.id+",\""+row.name+"\");' title='删除' src='../images/bt_del.gif'/>";
	   		
	   			
	   	 } }
	      
	         ]]
	         
		});
}





//编辑
function EditRoleDetail(id){

	$.ajax({
		type:'POST',
		url:'getUserInfoDetail.action',
		data:{'t.id':id},
		dataType:'JSON',
		success:function(data){
			
			
			
			$("#addRoleOption").dialog({
				title:"基础数据  > 编辑用户信息",
				onOpen:function(){
					$("#role_id").val(data.rows.id);
					$("#role_uname").val(data.rows.uname);
					$("#role_pwd").val(data.rows.pwd);
					$("#role_name").val(data.rows.name);
					$("#role_age").val(data.rows.userage);
					 $("input[name='role_sex'][value="+data.rows.sex.id+"]").attr("checked",true);
					var count=$("#role_r option").length;
					for (var i = 0; i < count; i++) {
						if($("#role_r").get(0).options[i].text==data.rows.role.tiaomu){
							$("#role_r").get(0).options[i].selected=true;
						}
					}
					count=$("#role_s option").length;
					for (var i = 0; i < count; i++) {
						if($("#role_s").get(0).options[i].text==data.rows.isassign.tiaomu){
							$("#role_s").get(0).options[i].selected=true;
						}
					}
					
				}
			
				
			});
			$("#role_s").attr("disabled",false);
			$("#saveRoleInfo").attr("onclick","");
			$("#saveRoleInfo").unbind('click').bind('click',function(){

				$.ajax({
					type:"POST",
					data:{				
						't.id':$("#role_id").val(),
						't.uname':$("#role_uname").val(),
						't.pwd':$("#role_pwd").val(),
						't.name':$("#role_name").val(),
						't.userage':$("#role_age").val(),
						't.sex.id':$("input[name='role_sex']:checked").val(),
						't.role.id':$("#role_r").val(),
						't.isassign.id':$("#role_s").val(),
						
						},
					url:'updateUserInfo.action',
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'用户信息编辑成功',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','用户信息编辑失败,原因：'+data.msg,'error');
						}
					}
				})
			
			})
		
			
			$("#addRoleOption").dialog("open");
			
		}
	});
}



function addRoleOption(){
	$("#saveRoleInfo").unbind('click');
	$("#saveRoleInfo").attr("onclick","saveRoleInfo();return false;");
	$("#role_s").get(0).options[0].selected=true;
	$("#role_s").attr("disabled",true);
	$("#addRoleOption").dialog({
		title:"基础数据  > 新建用户",
		
			onOpen:function(){
				$("#role_id").val("");
				$("#role_uname").val("");
				$("#role_pwd").val("");
				$("#role_name").val("");
				$("#role_age").val("");
				 $("input[name='role_sex'][value="+26+"]").attr("checked",true);
			
					
						$("#role_r").get(0).options[0].selected=true;
				
			
						$("#role_s").get(0).options[0].selected=true;
					
				}
				
			
		
	});
	$("#addRoleOption").dialog("open");
	   
	}
	
function closeDispatchOption(){
	$("#dispatchSaleOption").dialog({
		 closed: true
	});
	}

function closeSaleOption(){
	$("#addSaleOption").dialog({
		 closed: true
	});
	
}
function delRole(id,name){
	if(window.confirm("确认删除用户:"+name+"？")){
		$.ajax({
			type:'POST',
			url:'deleteUserInfo.action',
			data:{'t.id':id},
			dataType:'JSON',
			success:function(data){
				if(data.code==1){
					$.messager.show({title:"成功提示",msg:'用户删除成功',timeout:3000,showType:'slide'});
					dataObj.datagrid('reload');
				}else{
					$.messager.alert('失败提示','用户删除失败,原因：'+data.msg,'error');
				}
			}
		});
	}
	
}

//保存新建数据
function saveRoleInfo(){
	$.ajax({
		type:'POST',
		data:{
			't.id':$("#role_id").val(),
			't.uname':$("#role_uname").val(),
			't.pwd':$("#role_pwd").val(),
			't.name':$("#role_name").val(),
			't.userage':$("#role_age").val(),
			't.sex.id':$("input[name='role_sex']:checked").val(),
			't.role.id':$("#role_r").val(),
			't.isassign.id':$("#role_s").val(),
		},
		url:'createUserinfo.action',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				$.messager.show({title:"成功提示",msg:'用户添加成功',timeout:3000,showType:'slide'});
				dataObj.datagrid('reload');
			}else{
				$.messager.alert('失败提示','用户添加失败,原因：'+data.msg,'error');
			}
		}
	});
}




