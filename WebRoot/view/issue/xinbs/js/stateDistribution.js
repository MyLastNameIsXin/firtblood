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
	$.post("../../../distribution.json",$obj,function(data){
		var arr=[];
		for(var val of data.list){
			var obj={"name":val.stateDesc,"data":[parseFloat(val.branches)]};
			arr.push(obj);
		}
		var chart = Highcharts.chart('container',{
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: '缺陷状态分布'
		    },
		    xAxis: {
		        categories: [
		            '待分配','进行中','已解决','已关闭'
		        ],
		        crosshair: true
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: '数量（条）'
		        }
		    },
		    tooltip: {
		        // head + 每个 point + footer 拼接成完整的 table
		        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		        '<td style="padding:0"><b>{point.y:.1f}条</b></td></tr>',
		        footerFormat: '</table>',
		        shared: true,
		        useHTML: true
		    },
		    plotOptions: {
		        column: {
		            borderWidth: 0
		        }
		    },
		    series:arr
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