



//查询
$(function(){  
	
   $('#storeS_search').bind('click', function(){  
    	var params={};
    	if($("#ss_pro").val()!=null&&$("#ss_pro").val()!='undefined'){
			params['t.product']=$("#ss_pro").val();
		}
    	if($("#ss_sto").val()!=null&&$("#ss_sto").val()!='undefined'){
			params['t.storehouse']=$("#ss_sto").val();
		}
    	
		
		//条件查询
    	InitSearchStoreTable('search_kucuninfo.action',params);
 });  
   

   
    
    
});  








//列出所有客户
var dataObj;
var editRow=undefined;

InitSearchStoreTable('search_kucuninfo.action');

//加载datagrid
function InitSearchStoreTable(url,params){
	dataObj=$('#searchStore_list_table').datagrid({
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
	         {field:'product',title:'产品',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'storehouse',title:'仓库',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'storeplace',title:'货位',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'numbers',title:'件数',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'remark',title:'备注',width:100,align:'center',editor:{type:'text',options:{required:true}}}
				 
	        
	         ]]
	         
		});
}







	


