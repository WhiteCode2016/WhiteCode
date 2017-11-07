// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
// 显示标题，图例和空的坐标轴
myChart.setOption({
    title: {
        text: '异步数据加载示例'
    },
    tooltip: {},
    legend: {
        data:['回答数']
    },
    xAxis: {
        data: []
    },
    yAxis: {},
    series: [{
        name: '回答数',
        type: 'bar',
        data: []
    }]
});

// 异步加载数据
$.post('/common/zhihu/getZhiHuData').done(function (result) {
    var ids = [];
    var answers = [];

    result.forEach(function (ele,index) {
        ids.push(ele['zhId']);
        answers.push(ele['zhAnswers'])
    });

    // 填入数据
    myChart.setOption({
        xAxis: {
            data: ids
        },
        series: [{
            // 根据名字对应到相应的系列
            name: '回答数',
            data: answers
        }]
    });
});