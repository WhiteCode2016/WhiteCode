// 基于准备好的dom，初始化echarts实例
var myFollowerChart = echarts.init(document.getElementById('follower'));
// 显示标题，图例和空的坐标轴
myFollowerChart.setOption({
    color: ['#3398DB'],
    title: {
        text: '知乎关注者数前10排行榜',
        subtext: '数据来源于爬虫'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['关注者数']
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
        name: '关注者数',
        type: 'bar',
        barWidth: '60%',
        data:[]
    }]
});

// 异步加载数据
$.post('/common/zhihu/getZhiHuInfoByFollowerCountTop10').done(function (result) {
    var urlTokens = [];
    var followerCounts = [];

    result.forEach(function (ele,index) {
        urlTokens.push(ele['urlToken']);
        followerCounts.push(ele['followerCount'])
    });

    // 填入数据
    myFollowerChart.setOption({
        xAxis: [{
            type : 'category',
            axisTick: {
                alignWithLabel: true
            },
            data: urlTokens
        }],
        series: [{
            // 根据名字对应到相应的系列
            name: '关注者数',
            type:'bar',
            barWidth: '60%',
            data: followerCounts
        }]
    });
});
