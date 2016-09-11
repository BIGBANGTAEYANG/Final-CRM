$(function(){
	$("#index_content").tabs('add',{
		title:'首页',
		closable:false,
		href:'firstPage.html'
	}); 
	
	$.ajax({
		url:'checkLogin.action',
		method:'POST',
		dataType:'JSON',
		success:function(data){
			if(data.code==0){
				location.href="../../login.html"
			}else{
				$("#currUser").html('当前用户,'+data.obj.uname+'('+data.obj.role.tiaomu+')'+'<input id="currId" hidden=true value='+data.obj.id+'>'+'&nbsp;&nbsp;<a href="#" onclick="loginout();return false;">退出系统</a>');
			}
		}
	
});

	$("#menu_tree").tree({
		//data:'{\"text\":\"Languages\",\"state\":\"closed\",\"children\":[{\"text\":\"Java\"},{\"text\":\"C#\"}]}'
		//data:'[{"id":1,"text":"某公司","children":[{"id":2,"text":"生产部"}, {"id":4,"text":"质检部","children":[{"id":3,"text":"销售部"}, {"id":8,"text":"IT部"}]}] }12 ]'
		url:'treeServer.jsp',
		//url:'../data/tree_data.json'

			onClick:function(node){
				var text = node.text;
				var obj = $("#index_content");
				if("销售机会管理"==text){
					if(obj.tabs('exists',"销售机会管理")){
						obj.tabs('select',"销售机会管理");
					}else{
						obj.tabs('add',{
							title:"销售机会管理",
							closable:true,
							fit:true,
							href:'sale/list.html'
						})
					}
				}else if("客户开发计划"==text){
					if(obj.tabs('exists',"客户开发计划")){
						obj.tabs('select',"客户开发计划");
					}else{
						obj.tabs('add',{
							title:"客户开发计划",
							closable:true,
							fit:true,
							href:'sale/dev.html'
						})
					}
				}else if("客户信息管理"==text){
					if(obj.tabs('exists',"客户信息管理")){
						obj.tabs('select',"客户信息管理");
					}else{
						obj.tabs('add',{
							title:"客户信息管理",
							closable:true,
							fit:true,
							href:'cust/cusInfo.html'
						})
					}
				}else if("客户流失管理"==text){
					if(obj.tabs('exists',"客户流失管理")){
						obj.tabs('select',"客户流失管理");
					}else{
						obj.tabs('add',{
							title:"客户流失管理",
							closable:true,
							fit:true,
							href:'cust/cusLost.html'
						})
					}
				}else if("服务创建"==text){
					if(obj.tabs('exists',"服务创建")){
						obj.tabs('select',"服务创建");
					}else{
						obj.tabs('add',{
							title:"服务创建",
							closable:true,
							fit:true,
							href:'service/addService.html'
						})
					}
				}else if("服务分配"==text){
					if(obj.tabs('exists',"服务分配")){
						obj.tabs('select',"服务分配");
					}else{
						obj.tabs('add',{
							title:"服务分配",
							closable:true,
							fit:true,
							href:'service/disService.html'
						})
					}
				}else if("服务处理"==text){
					if(obj.tabs('exists',"服务处理")){
						obj.tabs('select',"服务处理");
					}else{
						obj.tabs('add',{
							title:"服务处理",
							closable:true,
							fit:true,
							href:'service/dealService.html'
						})
					}
				}else if("服务反馈"==text){
					if(obj.tabs('exists',"服务反馈")){
						obj.tabs('select',"服务反馈");
					}else{
						obj.tabs('add',{
							title:"服务反馈",
							closable:true,
							fit:true,
							href:'service/feedbackService.html'
						})
					}
				}else if("服务归档"==text){
					if(obj.tabs('exists',"服务归档")){
						obj.tabs('select',"服务归档");
					}else{
						obj.tabs('add',{
							title:"服务归档",
							closable:true,
							fit:true,
							href:'service/archService.html'
						})
					}
				}else if("客户贡献分析"==text){
					if(obj.tabs('exists',"客户贡献分析")){
						obj.tabs('select',"客户贡献分析");
					}else{
						obj.tabs('add',{
							title:"客户贡献分析",
							closable:true,
							fit:true,
							href:'report/contr.html'
						})
					}
				}else if("客户构成分析"==text){
					if(obj.tabs('exists',"客户构成分析")){
						obj.tabs('select',"客户构成分析");
					}else{
						obj.tabs('add',{
							title:"客户构成分析",
							closable:true,
							fit:true,
							href:'report/cons.html'
						})
					}
				}else if("客户服务分析"==text){
					if(obj.tabs('exists',"客户服务分析")){
						obj.tabs('select',"客户服务分析");
					}else{
						obj.tabs('add',{
							title:"客户服务分析",
							closable:true,
							fit:true,
							href:'report/ser.html'
						})
					}
				}else if("客户流失分析"==text){
					if(obj.tabs('exists',"客户流失分析")){
						obj.tabs('select',"客户流失分析");
					}else{
						obj.tabs('add',{
							title:"客户流失分析",
							closable:true,
							fit:true,
							href:'report/lost.html'
						})
					}
				}else if("数据字典管理"==text){
					if(obj.tabs('exists',"数据字典管理")){
						obj.tabs('select',"数据字典管理");
					}else{
						obj.tabs('add',{
							title:"数据字典管理",
							closable:true,
							fit:true,
							href:'base/dict.html'
						})
					}
				}else if("查询产品信息"==text){
					if(obj.tabs('exists',"查询产品信息")){
						obj.tabs('select',"查询产品信息");
					}else{
						obj.tabs('add',{
							title:"查询产品信息",
							closable:true,
							fit:true,
							href:'base/searchProduct.html'
						})
					}
				}else if("查询库存"==text){
					if(obj.tabs('exists',"查询库存")){
						obj.tabs('select',"查询库存");
					}else{
						obj.tabs('add',{
							title:"查询库存",
							closable:true,
							fit:true,
							href:'base/searchStore.html'
						})
					}
				}else if("用户管理"==text){
					if(obj.tabs('exists',"用户管理")){
						obj.tabs('select',"用户管理");
					}else{
						obj.tabs('add',{
							title:"用户管理",
							closable:true,
							fit:true,
							href:'base/role.html'
						})
					}
				}
			}
		
	});
});

function loginout(){
	$.ajax({
		type:'POST',
		url:"logout",
		dataType:'JSON',
		success:function(data){
			location.href="../../login.html";
		}
	});

	
}