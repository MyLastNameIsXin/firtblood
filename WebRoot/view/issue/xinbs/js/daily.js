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
	var obj=$("#form_search").serialize();
	var url="../../../daily.json?"+obj;
	$("#dg").datagrid({'url':url});
}
/**
 * 	导出日报
 * @returns
 */
function tableToExcel(){
	var obj=$("#form_search").serialize();
	var url="../../../dailyAll.json?"+obj;
	$.post(url,function(data){
		 //要导出的json数据
	      const jsonData = data.rows
	      //列标题，逗号隔开，每一个逗号就是隔开一个单元格
	      let str = `项目名,登记人,登记时间,登记工时\n`;
	      //增加\t为了不让表格显示科学计数法或者其他格式
	      for(let i = 0 ; i < jsonData.length ; i++ ){
	        str += `${jsonData[i].projectName + '\t'},${jsonData[i].userName + '\t'},${jsonData[i].logDate + '\t'},${jsonData[i].workHours + '\t'},`;
	        str += '\n';
	      }
	      //encodeURIComponent解决中文乱码
	      let uri = 'data:text/xls;charset=utf-8,\ufeff' + encodeURIComponent(str);
	      //通过创建a标签实现
	      let link = document.createElement("a");
	      link.href = uri;
	      //对下载的文件命名
	      link.download =  "工时记录日报"+new Date().getTime()+".xls";
	      document.body.appendChild(link);
	      link.click();
	      document.body.removeChild(link);
	},"json")
}

/**
 * 	清空重置按钮 清空条件搜索表单
 * @returns
 */
function reload(){
	$("#form_search").form('clear');
	designeeItem();//查询所有可见缺陷信息
}