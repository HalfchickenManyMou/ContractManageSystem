$(function () {
    // ------------------------------
    //- Start totalContractChart -
    var donutChartCanvas = $('#totalContractChart').get(0).getContext('2d')
    var donutData        = {
        labels: [
            '계약진행',
            '계약완료',
            '계약만료'
        ],
        datasets: [
            {
                data: [1100, 1450, 850],
                backgroundColor : ['#26c411', '#00C0EF', '#F56954'],
            }
        ]
    }
    var donutOptions     = {
        maintainAspectRatio : false,
        responsive : true,
    }
    var donutChart = new Chart(donutChartCanvas, {
        type: 'doughnut',
        data: donutData,
        options: donutOptions
    })
    //- END totalContractChart -
    // ------------------------------


    // ------------------------------
    // - Start monthlyContractChart -
    var stackedBarChartCanvas1 = $('#monthlyContractChart').get(0).getContext('2d')

    var areaChartData1 = {
        labels  : ['1월', '2월', '3월', '4월', '5월', '6월', '7월'],
        datasets: [
            {
                label               : '계약진행',
                backgroundColor     : 'rgba(38, 196, 17, 1)',
                borderColor         : 'rgba(38, 196, 17, 1)',
                pointRadius          : false,
                pointColor          : '#3b8bba',
                pointStrokeColor    : 'rgba(38, 196, 17, 1)',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(38, 192, 15, 1)',
                data                : [28, 48, 40, 19, 86, 27, 90]
            },
            {
                label               : '계약완료',
                backgroundColor     : 'rgba(0, 192, 239, 1)',
                borderColor         : 'rgba(0, 192, 239, 1)',
                pointRadius         : false,
                pointColor          : 'rgba(0, 192, 239, 1)',
                pointStrokeColor    : '#c1c7d1',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(0, 186, 235, 1)',
                data                : [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label               : '계약만료',
                backgroundColor     : 'rgba(245, 105, 84, 1)',
                borderColor         : 'rgba(245, 105, 84, 1)',
                pointRadius         : false,
                pointColor          : 'rgba(245, 105, 84, 1)',
                pointStrokeColor    : '#c1c7d1',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(245, 99, 80, 1)',
                data                : [65, 59, 80, 81, 56, 55, 40]
            },
        ]
    }

    var stackedBarChartData1 = jQuery.extend(true, {}, areaChartData1)

    var stackedBarChartOptions1 = {
        responsive              : true,
        maintainAspectRatio     : false,
        scales: {
            xAxes: [{
                stacked: true,
            }],
            yAxes: [{
                stacked: true
            }]
        }
    }

    var stackedBarChart = new Chart(stackedBarChartCanvas1, {
        type: 'bar',
        data: stackedBarChartData1,
        options: stackedBarChartOptions1
    })
    // - End monthlyContractChart -
    // ------------------------------


    // ------------------------------
    // - Start departmentContractChart -
    var stackedBarChartCanvas2 = $('#departmentContractChart').get(0).getContext('2d')

    var areaChartData2 = {
        labels  : ['마케팅부','영업부','개발부','법무부'],
        datasets: [
            {
                label               : '계약진행',
                backgroundColor     : 'rgba(38, 196, 17, 1)',
                borderColor         : 'rgba(38, 196, 17, 1)',
                pointRadius          : false,
                pointColor          : '#3b8bba',
                pointStrokeColor    : 'rgba(38, 196, 17, 1)',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(38, 192, 15, 1)',
                data                : [28, 48, 40, 19]
            },
            {
                label               : '계약완료',
                backgroundColor     : 'rgba(0, 192, 239, 1)',
                borderColor         : 'rgba(0, 192, 239, 1)',
                pointRadius         : false,
                pointColor          : 'rgba(0, 192, 239, 1)',
                pointStrokeColor    : '#c1c7d1',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(0, 186, 235, 1)',
                data                : [65, 59, 80, 81]
            },
            {
                label               : '계약만료',
                backgroundColor     : 'rgba(245, 105, 84, 1)',
                borderColor         : 'rgba(245, 105, 84, 1)',
                pointRadius         : false,
                pointColor          : 'rgba(245, 105, 84, 1)',
                pointStrokeColor    : '#c1c7d1',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(245, 99, 80, 1)',
                data                : [65, 59, 80, 81]
            },
        ]
    }

    var stackedBarChartData2 = jQuery.extend(true, {}, areaChartData2)

    var stackedBarChartOptions2 = {
        responsive              : true,
        maintainAspectRatio     : false,
        scales: {
            xAxes: [{
                stacked: true,
            }],
            yAxes: [{
                stacked: true
            }]
        }
    }

    var stackedBarChart2 = new Chart(stackedBarChartCanvas2, {
        type: 'bar',
        data: stackedBarChartData2,
        options: stackedBarChartOptions2
    })
    // - End departmentContractChart -
    // ------------------------------
})