var contr_chart = new Highcharts.Chart({
    chart: {
        renderTo: "container1",
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
    },
    title: {
        text: '客户贡献分析'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.y}</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                }

            },
            //showInLegend: true
        }
    },
    series: [{
        type: 'pie',
        name: '',
        data: []
    }]
});



//查询
$(function(){ 
	 
    
	
	InitConrTable('getOrderInfoAndSumPrice.action');	
   $('#contrS_search').bind('click', function(){  
    	var params={};
    	if($("#contr_cname").val()!=null&&$("#contr_cname").val()!='undefined'){
			params['customername']=$("#contr_cname").val();
		}
    	if($("#contr_year").val()!=null&&$("#contr_year").val()!='undefined'){
			params['createtime']=$("#contr_year").val();
		}
		//条件查询
    	InitConrTable('getOrderInfoAndSumPrice.action',params);
 });  
   
   
   


    
});  






function InitConrTable(url,para){
	$.ajaxSettings.async = false;
    var data1 = [];
	$.ajax({
		type:'POST',
		url:url,
		data:para,
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				contr_chart.series[0].setData(null);
				var html="<tr><th>编号</th><th>客户名称</th><th>订单金额（元）</th></tr>";
				for (var i = 0; i < data.rows.length; i++) {
					var obj = data.rows[i];
					html+="<tr><td class='list_data_number'>"+obj.customer.id+"</td><td class='list_data_text'>"+obj.customername+"</td><td class='list_data_number'>"+obj.sumprice+"</td></tr>";
					data1.push([obj.customername, obj.sumprice]);
				}
				$("#contr_list_table").html('');
				$("#contr_list_table").html(html);
				
				contr_chart.series[0].setData(data1);
			}
		
		}
	
	});
}

function opencontrC(){
	$("#contrC").dialog("open");
}







	


