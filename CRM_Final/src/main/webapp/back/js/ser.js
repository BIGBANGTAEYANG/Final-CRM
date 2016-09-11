var chart1 = new Highcharts.Chart({
    chart: {
        renderTo: "ser_container",
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
    },
    title: {
        text: '客户服务分析'
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
	InitSerTable('getServiceTypeAndCount.action');
	
	
   $('#serS_search').bind('click', function(){  
    	var params={};
		params['t.createtime']=$("#ser_year").val();
		//条件查询
    	InitSerTable('getServiceTypeAndCount.action',params);
 });  
   


    
});  


function InitSerTable(url,para){
	$.ajaxSettings.async = false;
    var data1 = [];
	$.ajax({
		type:'POST',
		url:url,
		data:para,
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				var html="<tr><th>编号</th><th>条目</th><th>服务数量</th></tr>";
				for (var i = 0; i < data.rows.length; i++) {
					var obj = data.rows[i];
					html+="<tr><td class='list_data_number'>"+obj.servicetype.id+"</td><td class='list_data_text'>"+obj.servicetype.tiaomu+"</td><td class='list_data_number'>"+obj.count+"</td></tr>";
					data1.push([obj.servicetype.tiaomu, Number(obj.count)]);
				}
				$("#ser_list_table").html('');
				$("#ser_list_table").html(html);
				chart1.series[0].setData(data1);
			}
		
		}
	
	});
}
function OpenserC(){
	$("#serC").dialog("open");
}








	


