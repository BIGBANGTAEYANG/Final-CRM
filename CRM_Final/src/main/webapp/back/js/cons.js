var cons_chart = new Highcharts.Chart({
    chart: {
        renderTo: "cons_chart",
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
    },
    title: {
        text: '客户构成分析'
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
	var param={};
	param['t.dtype']=$("#cons_type").val();
	InitConsTable('getDataDirectoryAndCustomerCount.action',param);
	
	
   $('#consS_search').bind('click', function(){  
    	var params={};
		params['t.dtype']=$("#cons_type").val();
		//条件查询
		InitConsTable('getDataDirectoryAndCustomerCount.action',params);
 });  
   


    
});  


function InitConsTable(url,para){
	$.ajaxSettings.async = false;
    var data1 = [];
	$.ajax({
		type:'POST',
		url:url,
		data:para,
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				cons_chart.series[0].setData(null);
				var title = $("#cons_type").val()=='grade'?"等级":$("#cons_type").val()=='credit'?"信用度":"满意度";
				var html="<tr><th>编号</th><th>"+title+"</th><th>客户数量</th></tr>";
				for (var i = 0; i < data.rows.length; i++) {
					var obj = data.rows[i];
					html+="<tr><td class='list_data_number'>"+(i+1)+"</td><td class='list_data_text'>"+obj.tiaomu+"</td><td class='list_data_number'>"+obj.customercount+"</td></tr>";
					data1.push([obj.tiaomu, obj.customercount]);
				}
				$("#cons_list_table").html('');
				$("#cons_list_table").html(html);
				cons_chart.series[0].setData(data1);
			}
		
		}
	
	});
}

function OpenconsC(){
	$("#consC").dialog("open");
}







	


