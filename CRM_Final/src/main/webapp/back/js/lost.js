
//查询
$(function(){ 
	
	
   $('#lostS_search').unbind('click').bind('click', function(){  
		var params={};
    	if($("#lost_cname").val()!=null&&$("#lost_cname").val()!='undefined'){
			params['t.customer.cname']=$("#lost_cname").val();
		}
    	if($("#lost_cusM").val()!=null&&$("#lost_cusM").val()!='undefined'){
			params['t.userinfo.name']=$("#lost_cusM").val();
		}
		//条件查询
    	InitLostTable('CustomerLostAnalysis.action',params);
 });  
   


    
});  

var dataObj;
var editRow=undefined;
InitLostTable('CustomerLostAnalysis');
function InitLostTable(url,params){
	dataObj=$('#lost_list_table').datagrid({
		url:url,
		fitColumns:true,
		loadMsg:'数据加载中',
		pagination:true,
		pageNumber:1,
		sortName:"id",
		queryParams:params,
		remoteSort:false,
		columns:[[
	         
	         
	         {field:'id',title:'编号',width:100,align:'center',sortable:true,formatter: function(value,row,index){
					return (index+1)
	         }},
	         {field:'lostdatestr',title:'年份',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	         {field:'cname',title:'客户',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.customer){
						return row.customer.cname;
					
					} else {
						return value;
					}
				}
	},
	         {field:'cm',title:'客户经理',width:100,align:'center',editor:{type:'text',options:{required:true}},formatter: function(value,row,index){
					if (row.customer){
						return row.customer.customermanager.name;
					} else {
						return value;
					}
				}},
	         {field:'lostreason',title:'流失原因',width:100,align:'center',editor:{type:'text',options:{required:true}}},
	       
	         
	         ]]
	         
		});
}










	


