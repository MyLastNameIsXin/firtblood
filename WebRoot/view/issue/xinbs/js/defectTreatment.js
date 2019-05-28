/**
 * 	页面加载事件
 * @returns
 */
$(function(){
	designeeItem();//查询当前用户作为管理员的未分配的缺陷信息
	queryItemName();//加载项目名下拉列表
	var val="sdwfwd\ndawsdwds";
})

/**
 * 查询当前管理员可见缺陷信息
 * @returns
 */
function designeeItem(){
	var obj=$("#form_search").serialize();
	var url="../../../designeeItem.json?"+obj;
	$("#dg").datagrid({'url':url});
}
/**
 * 	加载项目名的下拉
 * @returns
 */
function queryItemName(){
	$("#itema").combobox({
		 loadFilter:function(data){
		    	var data=data.list || data;
		    	data.unshift({
		    		projectId:"",
		    		projectName:'全部'
		    	})
		    	return data;
		    },
		url:'../../../itemDisposePull.json',
	    valueField: 'projectId',
	    textField: 'projectName'
	})
	$("#itema").combobox('setValue', '全部');
}


/**
 * 	在每一列后面增加操作列
 * @returns
 */
function formatOper(value,row,index){     
	var str='<a style="color:blue" class="pao" href="javascript:void(0);" onclick="state(\''+row.issueId+'\',\''+row.projectId+'\')">处理';
	return str;
}
/**
 * 	序列化时间列只显示到分
 * @param value
 * @param row
 * @param index
 * @returns
 */
function genderFormatter(value){
	var str=value.substring(0,value.lastIndexOf(":"));
	return str;
}
/**
 * 	详情面板填充数据
 * @param data
 * @returns
 */
function state(issueId,projectId){
	$.post("../../../dispose.json",{'issueUseId':issueId},function(data){
		$("#dlg_particulars").dialog({
			title:"缺陷处理"
		});
		$("#dlg_particulars").dialog('open');
		$("#issueId").val(data.list.issueId);
		$("#projectId").text(data.list.projectName);
		$("#parentIssueId").text(data.list.parentIssueName);
		$("#issueName").text(data.list.issueName);
		$("#issueType").text(data.list.issueType);
		$("#issueState").text(data.list.issueState);
		$("#issueSeverity").text(data.list.issueSeverity);
		$("#issuePriority").text(data.list.issuePriority);
		$("#planWorkHours").text(data.list.planWorkHours);
		$("#doneRatio").text(data.list.doneRatio+"%");
		$("#planStartTime").text(data.list.planStartTime);
		$("#planEndTime").text(data.list.planEndTime);
		$("#issueDesc").html(lineFeed(data.list.issueDesc));
		$("#doneCondition").html(lineFeed(data.list.doneCondition));
		$("#actWorkeHours").text(ifNull(data.list.actWorkeHours));
		$("#actStartTime").text(ifNull(data.list.actStartTime));
		$("#actEndTime").text(ifNull(data.list.actEndTime));
		$("#assignee").text(ifNull(data.list.assignee));
		$("#assigendTime").text(genderFormatter(data.list.assigendTime));
		if(data.list.parentIssueName == null){
			//父级项目一栏为空时将其替换为“---”
			$("#parentIssueId").text("----");
		}
		if(data.list.doneRatio == null){
			//完成率一栏为空时将其替换为“0%”
			$("#doneRatio").text("0%");
		}
		$("#retroaction").html('<a style="color:blue" href="javascript:void(0);" onclick="feedbackInformation(\''+data.list.issueId+'\')">查看反馈信息(共计'+data.num.retroactionNum+'条)');
		imperStatePull();//缺陷处理状态下拉
		verify();//非空验证
	},"json");
}
/**
 * 	查看反馈信息
 * @param issueId 缺陷编号
 * @returns
 */
function feedbackInformation(issueId){
		var url="../../../seeFeedback.json?issueId="+issueId;
		$("#feedback_dg").datagrid({'url':url});
		$("#dlg_feedback").dialog({
			title:"反馈信息"
		});
		$("#dlg_feedback").dialog('open');
}
/**
 * 	关闭反馈面板
 * @returns
 */
function closePanel(){
	$("#dlg_feedback").dialog('close');
}

/**
 * 	生成缺陷处理下拉
 * @returns
 */
function imperStatePull(){
	$("#imperStatePull").combobox({
		url:'../../../imperStatePull.json',
	    valueField: 'sateCode',
	    textField: 'stateDesc',
	    loadFilter:function(data){
		    	var data=data.list || data;
		    	return data;
		    }
	});
}
/**
 * 
 * 	处理面板确定按钮
 * @returns
 */
function confirmPanel(){
	console.log($("#imperStatePull").combobox("getValue"));
	var obj=$("#from_dispose").serializeObject();
	if($("#from_dispose").form("validate")){
		$.post("../../../dealDefects.json",obj,function(data){
			if(data.row == "1"){
				$.messager.alert("提示","登记成功，感谢您的辛劳付出","info");
			}else{
				$.messager.alert("错误","登记失败，请重试或者联系管理员查看错误","error");
			}
			$("#dlg_particulars").dialog('close');
			$("#from_dispose").form("clear")
			designeeItem();//查询当前用户作为管理员的未分配的缺陷信息
		},"json")
	}
}

/**
 * 	上传修改验证
 * @returns
 */
function verify(){
        $('#imperStatePull').combobox({
        	required:true,
        	missingMessage:"请选择缺陷状态"
        });
        $('#itemaTime').numberbox({
        	required:true,
			missingMessage:"耗工时不能为空"
        });
        $('#roleNum').numberbox({
        	required:true,
			missingMessage:"完成率不能为空"
        });
}

/**
 * 	清空重置按钮 清空条件搜索表单
 * @returns
 */
function reload(){
	$("#form_search").form('clear');
	designeeItem();//查询所有可见缺陷信息
	queryItemName();//加载项目名下拉列表
}
/**
 * 	缺陷状态选中已完成时完成率填入100
 * @returns
 */
function pitchOn(){
	var val=$("#imperStatePull").combobox("getValue");
	if(val == "03"){
		$("#roleNum").numberbox('setValue', 100);
	}else{
		$("#roleNum").numberbox('setValue', "");
	}
}
/**
 * 	当完成率输入100时状态选中已完成
 * @returns
 */
function complete(){
	var num=$("#roleNum").numberbox('getValue');
	if(num == 100){
		$("#imperStatePull").combobox("setValue","03")
	}else{
		$("#imperStatePull").combobox("setValue","02")
	}
}
/**
 * 	判断当前字段是否为空
 * @param value 字段值
 * @returns
 */
function ifNull(value){
	if(value == null){
		return "----";
	}else{
		return value;
	}
}

/**
 * 	将多行文本以多行文本显示
 * @param val 传入的文本值
 * @returns
 */
function lineFeed(val){
	return val=val.replace("\n","<br/>")
}