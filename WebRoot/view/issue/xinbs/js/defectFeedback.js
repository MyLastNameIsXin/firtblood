/**
 * 	页面加载事件
 * @returns
 */
$(function(){
	designeeItem();//查询当前用户作为管理员的未分配的缺陷信息
	queryItemName();
})

/**
 * 查询当前管理员可见缺陷信息
 * @returns
 */
function designeeItem(){
	var obj=$("#form_search").serialize();
	var url="../../../feedbackItem.json?"+obj;
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
		url:'../../../feedbackPull.json',
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
	var str='<a style="color:blue" class="pao" href="javascript:void(0);" onclick="state(\''+row.issueId+'\')">反馈';
	return str;
}
/**
 * 	序列化时间列只显示到分
 * @param value
 * @param row
 * @param index
 * @returns
 */
function genderFormatter(value, row, index){
	var str=value.substring(0,value.lastIndexOf(":"));
	return str;
}
/**
 * 	详情面板填充数据
 * @param data
 * @returns
 */
function state(issueId){
	$.post("../../../dispose.json",{'issueUseId':issueId},function(data){
		//调用的是缺陷处理的控制器及方法
		$("#dlg_particulars").dialog({
			title:"缺陷反馈"
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
		$("#doneRatio").text(data.list.doneRatio);
		$("#planStartTime").text(data.list.planStartTime);
		$("#planEndTime").text(data.list.planEndTime);
		$("#doneCondition").text(data.list.doneCondition);
		$("#issueDesc").html(lineFeed(data.list.issueDesc));
		$("#doneCondition").html(lineFeed(data.list.doneCondition));
		$("#assignee").text(ifNull(data.list.assignee));
		$("#assigendTime").text(genderFormatter(data.list.assigendTime));
		$("#actWorkeHours").text(ifNull(data.list.actWorkeHours));
		$("#actStartTime").text(ifNull(data.list.actStartTime));
		$("#actEndTime").text("----");
		if(data.list.parentIssueName == null){
			//父级项目一栏为空时将其替换为“---”
			$("#parentIssueId").text("----");
		}
		$("#retroaction").html('<a style="color:blue" href="javascript:void(0);" onclick="feedbackInformation(\''+data.list.issueId+'\')">查看反馈信息(共计'+data.num.retroactionNum+'条)');
		feedbackStatePull();//缺陷处理状态下拉
		verify();//非空验证
	},"json");
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
 * 	查看反馈信息
 * 	此方法调用缺陷处理页面控制器
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
function feedbackStatePull(){
	$("#feedbackStatePull").combobox({
		 loadFilter:function(data){
		    	var data=data.list || data;
		    	return data;
		    },
		url:'../../../feedbackStatePull.json',
	    valueField: 'sateCode',
	    textField: 'stateDesc'
	})
}
/**
 * 	确认提交按钮
 * @returns
 */
function confirmPanel(){
	var obj=$("#from_dispose").serializeObject();
	if($("#from_dispose").form("validate")){
		$.post("../../../retroaction.json",obj,function(data){
			if(data.row == "1"){
				$.messager.alert("提示","操作成功","info");
			}else{
				$.messager.alert("错误","操作失败，请重试或者联系管理员查看错误","error");
			}
			$("#dlg_particulars").dialog('close');
			$("#from_dispose").form('clear');
			designeeItem();//查询当前用户作为管理员的未分配的缺陷信息
		},"json")
	}
}

/**
 * 	上传修改验证
 * @returns
 */
function verify(){
        $('#feedbackStatePull').combobox({
        	required:true,
        	missingMessage:"请选择缺陷状态"
        });
}

/**
 * 	清空重置按钮 清空条件搜索表单
 * @returns
 */
function reload(){
	$("#form_search").form('clear');
	queryItemName();
	designeeItem();//查询所有可见缺陷信息
}

/**
 * 	将多行文本以多行文本显示
 * @param val 传入的文本值
 * @returns
 */
function lineFeed(val){
	return val=val.replace("\n","<br/>")
}