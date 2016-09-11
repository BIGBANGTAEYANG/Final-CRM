



//查询
$(function(){  
	
   $('#productS_search').bind('click', function(){  
    	var params={};
    	if($("#sp_name").val()!=null&&$("#sp_name").val()!='undefined'){
			params['t.pname']=$("#sp_name").val();
		}
    	if($("#sp_type").val()!=null&&$("#sp_type").val()!='undefined'){
			params['t.pversion']=$("#sp_type").val();
		}
    	if($("#sp_batch").val()!=null&&$("#sp_batch").val()!='undefined'){
			params['t.batch']=$("#sp_batch").val();
		}
		
		//条件查询
    	InitSearchProductTable('search_productinfo.action',params);
 });  
   

   
    
    
});  








//列出所有客户
var dataObj;
var editRow=undefined;

InitSearchProductTable('search_productinfo.action');

//加载datagrid
function InitSearchProductTable(url,params){
	dataObj=$('#searchProduct_list_table').datagrid({
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
	         {field:'pname',title:'名称',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'pversion',title:'型号',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'batch',title:'等级/批次',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'unit',title:'单位',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'price',title:'单价（元）',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'remark',title:'备注',width:100,align:'center',editor:{type:'text',options:{required:true}}}
				 
	        
	         ]]
	         
		});
}







	


