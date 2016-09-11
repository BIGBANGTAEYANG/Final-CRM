var nameList;
//查询
$(function(){
	$.ajax({
		type:'POST',
		url:'list_datatype.action',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				list=data.obj;
			}
		}
	
	});
	
   $('#dictS_search').bind('click', function(){  
    	var params={};
    	if($("#dict_type").val()!=null&&$("#dict_type").val()!='undefined'){
			params['t.dname']=$("#dict_type").val();
		}
    	if($("#dict_tiaomu").val()!=null&&$("#dict_tiaomu").val()!='undefined'){
			params['t.tiaomu']=$("#dict_tiaomu").val();
		}
    	if($("#dict_value").val()!=null&&$("#dict_value").val()!='undefined'){
			params['t.dvalue']=$("#dict_value").val();
		}
		
		//条件查询
		InitDictTable('search_datadirectory.action',params);
 });  
   
   $("#dict_ty").bind('input ',function(){
	   var highlightindex = -1; 
	   var autoNode = $("#dict_auto");
	   if ($('#dict_ty').val()!='' && ischinese($('#dict_ty').val())) {
		   $.ajax({
			   type:'POST',
			   url:'list_dtypedynamic',
			   dataType:'JSON',
			   data:{'t.dname':$("#dict_ty").val()},
			   success:function(data){
				   
				   autoNode.html("");
				   if(data.code==1){
					   for (var i = 0; i < data.obj.length; i++) {
						obj = data.obj[i];
						
						newDivNode = $("<div>").attr({"id":i,"dtype":obj.dtype});  
						newDivNode.html(obj.dname).appendTo(autoNode);
						newDivNode.mouseover(function() {
							if(highlightindex != -1) {
								$("#dict_auto").children("div").eq(highlightindex) .css("background-color","white");     
								}          
							highlightindex = $(this).attr("id");
							$(this).css("background-color","#6EC2FD");    
							}); 
						
						newDivNode.mouseout(function() {                 $(this).css("background-color","white");       });
						newDivNode.click(function(){
							autoNode.hide();
							$("#dict_ty").val($(this).text());
							$("#dict_tyid").val($(this).attr("dtype"));
						});
					}
					   autoNode.show(); 
				   }else{
					   autoNode.hide();
				   }
			   }
		   
		   });
	}else{
		autoNode.hide;
	}
	   
   });
   
 
   

   
    
    
});  








//列出所有客户
var dataObj;
var editRow=undefined;

InitDictTable('search_datadirectory.action');

//加载datagrid
function InitDictTable(url,params){
	dataObj=$('#dict_list_table').datagrid({
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
	         {field:'dname',title:'类别',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'tiaomu',title:'条目',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'dvalue',title:'值',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'iseditable',title:'是否可编辑',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	        	 	return row.isedit=='0'?'是':'否';
	         }},
			
			{field:'g',title:'操作',width:100,align:'center',editor:{type:'text',options:{required:true}}, formatter:function(value,row,index){
	        	
	        		
	        		 return row.isedit=='0'?"<img onclick='editDict("+row.id+",\""+row.dtype+"\",\""+row.dname+"\",\""+row.tiaomu+"\",\""+row.dvalue+"\",\""+true+"\");' title='编辑' src='../images/bt_edit.gif'/><img onclick='delDict("+row.id+");' title='删除' src='../images/bt_del.gif'/>":'';
	        	
	         
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

function editDict(id,type,name,tiaomu,value,isedit){
	$("#dictDialog").dialog({
		title:"数据字典管理 > 编辑数据字典条目",
		onOpen:function(){
			$("#dict_id").val(id),
			$("#dict_tyid").val(type),
			$("#dict_ty").val(name),
			$("#dict_ti").val(tiaomu),
			$("#dict_va").val(value),
			
			$("#dict_check").attr("checked",isedit)
		}
	});
	$("#dictDialog").dialog("open");
}

function ischinese(s){
    var ret=true;
    for(var i=0;i<s.length;i++)
    ret=ret && (s.charCodeAt(i)>=10000);
    return ret;
}

function newDict(){
	$("#dictDialog").dialog({
		title:"数据字典管理 > 新建数据字典条目",
		onOpen:function(){
			$("#dict_id").val(""),
			$("#dict_tyid").val(""),
			$("#dict_ty").val(""),
			$("#dict_ti").val(""),
			$("#dict_va").val(""),
			$("#dict_check").attr("checked",false)
		}
	});
	$("#dictDialog").dialog("open");
}

function saveDict(){

	   var v=$("#dict_ty").val();
	   var flag = true;
	  for (var i = 0; i < list.length; i++) {
		if(list[i].dname==v){
			flag=false;
		}
		
		
	}
	  if(flag){
			alert("类别输入无效，请重新输入");
			$("#dict_ty").val("");
		}else{
			if($("#dictDialog").panel('options').title=='数据字典管理 > 新建数据字典条目'){
				
				$.ajax({
					type:'POST',
					url:'insert_datadirectory.action',
					data:{
						't.dtype':$("#dict_tyid").val(),
						't.dname':$("#dict_ty").val(),
						't.tiaomu':$("#dict_ti").val(),
						't.dvalue':$("#dict_va").val(),
						
						't.isedit':$("#dict_check").is(":checked")?0:1
					},
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'数据字典条目新建成功',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','数据字典条目新建失败,原因：'+data.msg,'error');
						}
					}
					
				})
			}else{
				
				$.ajax({
					
					type:'POST',
					url:'update_datadirectory.action',
					data:{
						't.id':$("#dict_id").val(),
						't.dtype':$("#dict_tyid").val(),
						't.dname':$("#dict_ty").val(),
						't.tiaomu':$("#dict_ti").val(),
						't.dvalue':$("#dict_va").val(),
						't.isedit':$("#dict_check").is(":checked")==true?0:1
					},
					dataType:'JSON',
					success:function(data){
						if(data.code==1){
							$.messager.show({title:"成功提示",msg:'数据字典条目编辑成功',timeout:3000,showType:'slide'});
							dataObj.datagrid('reload');
						}else{
							$.messager.alert('失败提示','数据字典条目编辑失败,原因：'+data.msg,'error');
						}
					}
					
				})
			
			}
		}


}

	


