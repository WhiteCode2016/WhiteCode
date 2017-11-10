// 基于准备好的dom，初始化echarts实例
var myAnswerChart = echarts.init(document.getElementById('answer'));
// 显示标题，图例和空的坐标轴
myAnswerChart.setOption({
    color: ['#3398DB'],
    title: {
        text: '知乎回答数前10排行榜',
        subtext: '数据来源于爬虫'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['回答数']
    },
    xAxis: [{
        type : 'category',
        axisTick: {
            alignWithLabel: true
        },
        data : []
    }],
    yAxis: [{
        type: 'value'
    }],
    series: [{
        name: '回答数',
        type: 'bar',
        barWidth: '60%',
        data:[]
    }]
});

// 异步加载数据
$.post('/common/zhihu/getZhiHuInfoByAnswerCountTop10').done(function (result) {
    var urlTokens = [];
    var answerCounts = [];

    result.forEach(function (ele,index) {
        urlTokens.push(ele['urlToken']);
        answerCounts.push(ele['answerCount'])
    });

    // 填入数据
    myAnswerChart.setOption({
        xAxis: [{
            type : 'category',
            axisTick: {
                alignWithLabel: true
            },
            data: urlTokens
        }],
        series: [{
            // 根据名字对应到相应的系列
            name: '回答数',
            type:'bar',
            barWidth: '60%',
            data: answerCounts
        }]
    });
});
