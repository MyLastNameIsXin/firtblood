/**
 * 	页面加载事件
 */
$(function(){
	itemPull();//加载项目下拉树
	designeeItem();//查询按钮点击事件
})

/**
 * 	加载项目名下拉树
 */
function itemPull(){
	$("#itema").combotree({
		url:"../lili/findProtectStartInfo.json",
		loadFilter:function(data){
			var arr=[];
			for(var val of data.rows){
				var obj={"id":val.projectId,"text":val.projectName,"state":val.state}
				arr.push(obj);
			}
			return arr;
		}
	})
}
/**
 * 	 查询按钮单击事件
 */
function designeeItem(){
	var $obj=$("#form_search").serializeObject();
	$.post("../../../seriousness.json",$obj,function(data){
		var arr=[]
		for(var val of data.list){ 
			var obj={"name":val.severityDesc,"y":parseFloat(val.branches)}
			arr.push(obj);
		};
		var chart = Highcharts.chart('container', {
			chart: {
				spacing : [40, 0 , 40, 0]
			},
			title: {
				text: '缺陷严重程度'
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
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
					showInLegend: true
				}
			},
			series: 
				[{
				type: 'pie',
				innerSize: '70%',
				name: '市场份额',
				data: arr
			}]
		});
	},"json")
}
/**
 * 	将导出按钮 序列成中文
 */
Highcharts.setOptions({
	lang:{
		contextButtonTitle: "图表导出菜单",
		downloadJPEG:"下载 JPEG 图片",
		downloadPDF:"下载 PDF 文件",
		downloadPNG:"下载 PNG 文件",
		downloadSVG:"下载 SVG 文件",
		printChart:"打印图表"
	}
})


/**
 * 	清空重置按钮 清空条件搜索表单
 * @returns
 */
function reload(){
	$("#form_search").form('clear');
	designeeItem();//查询所有可见缺陷信息
}