layui.use([],function(){
	
	 // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById('main'));

  // 指定图表的配置项和数据
  var option = {
    title: {
        text: '折线图堆叠表'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['收入']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'收入',
            type:'line',
            stack: '总量',
            data:[7800, 6832, 7101, 6134, 5090, 4230, 8210, 4132, 9101, 4134, 6690, 5230, 7210]
        },
    ]
  };


  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
	
});