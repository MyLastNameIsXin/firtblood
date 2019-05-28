 /**
 * 	页面加载事件
 */
$(function(){
	monthTime("startTime");
	monthTime("endTime")
	itemPull();//加载项目下拉树
	designeeItem();//查询按钮点击事件
})
/**
 * 	只显示月份
 * @returns
 */
function monthTime(startTime){
	 $("#"+startTime).datebox({
		   //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
		   onShowPanel: function () {
		      //触发click事件弹出月份层
		      span.trigger('click'); 
		      if (!tds)
		        //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
		        setTimeout(function() { 
		            tds = p.find('div.calendar-menu-month-inner td');
		            tds.click(function(e) {
		               //禁止冒泡执行easyui给月份绑定的事件
		               e.stopPropagation(); 
		               //得到年份
		               var year = /\d{4}/.exec(span.html())[0] ,
		               //月份
		               //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
		               month = parseInt($(this).attr('abbr'), 10);  

		     //隐藏日期对象                     
		     $("#"+startTime).datebox('hidePanel') 
		       //设置日期的值
		       .datebox('setValue', year + '-' + month); 
		                    });
		                }, 0);
		        },
		        //配置parser，返回选择的日期
		        parser: function (s) {
		            if (!s) return new Date();
		            var arr = s.split('-');
		            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		        },
		        //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
		        formatter: function (d) { 
		            var currentMonth = (d.getMonth()+1);
		            var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
		            return d.getFullYear() + '-' + currentMonthStr; 
		        }
		    });

		    //日期选择对象
		    var p = $("#"+startTime).datebox('panel'), 
		    //日期选择对象中月份
		    tds = false, 
		    //显示月份层的触发控件
		    span = p.find('span.calendar-text'); 
		    var curr_time = new Date();
}
/**
 * 	格式化只显示到月
 * @param date
 * @returns
 */
function myformatter(date) {
    //获取年份
    var y = date.getFullYear();
    //获取月份
    var m = date.getMonth() + 1;
    return y + '-' + m;
}
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
	var url="../../../montyly.json?"+obj;
	$("#dg").datagrid({'url':url});
}
/**
 * 	导出月报
 * @returns
 */
function tableToExcel(){
	var obj=$("#form_search").serialize();
	var url="../../../montylyAll.json?"+obj;
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
	      link.download =  "工时记录月报"+new Date().getTime()+".xls";
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