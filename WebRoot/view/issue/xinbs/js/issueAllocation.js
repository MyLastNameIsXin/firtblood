/**
 * 	页面加载事件
 * @returns
 */
$(function(){
	allotIsAdministrator();//查询当前用户作为管理员的未分配的缺陷信息
	queryItemName();//加载项目名下拉
})

/**
 * 查询当前管理员可见缺陷信息
 * @returns
 */
function allotIsAdministrator(){
	var obj=$("#form_search").serialize();
	var url="../../../allotIsAdministrator.json?"+obj+"";
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
		url:'../../../itemAllotPull.json',
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
	var str='<a style="color:blue" class="pao" href="javascript:void(0);" onclick="state(\''+row.issueId+'\',\''+row.projectId+'\')">分配';
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
function state(issueId,projectId){
	designate(projectId);
	$.post("../../../distribute.json",{'issueUseId':issueId},function(data){
		$("#dlg_particulars").dialog({
			title:"缺陷分配"
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
		$("#issueDesc").text(data.list.issueDesc);
		$("#doneCondition").text(data.list.doneCondition);
		if(data.list.parentIssueName == null){
			//父级项目一栏为空时将其替换为“---”
			$("#parentIssueId").text("----");
		}
		if(data.list.doneRatio == null){
			//完成率一栏为空时将其替换为“0%”
			$("#doneRatio").text("0%");
		}
	},"json");
}

/**
 * 	分配面板指派下拉
 * @returns
 */
function designate(projectId){
	$("#appoint").combobox({
		 loadFilter:function(data){
		    	var data=data.list || data;
		    	return data;
		    },
		url:'../../../designate.json?projectId='+projectId+'',
	    valueField: 'userId',
	    textField: 'userName'
	})
}
/**
 * 	分配面板确认按钮
 * @returns
 */
function confirmPanel(){
	var issueId= $("#issueId").val();//获取哪条缺陷的指派
	var assignee=$("#appoint").combobox('getValue');//获取指派给下来的值
	if(assignee == null || assignee==""){
		$.messager.alert("提示","请选择被指派人","info");
		return;
	}
	$.post("../../../assignedTo.json",{"issueId":issueId,"assignee":assignee},function(data){
		//data= eval('(' + data + ')');  // 把JSON字符串解析为javascript对象
		if(data.row == "1"){
			$.messager.alert("提示","缺陷已被成功分配","info");
		}else{
			$.messager.alert("提示","缺陷分配失败，请联系管理员查看错误","info");
		}
		$("#dlg_particulars").panel('close');
		allotIsAdministrator();//查询当前用户作为管理员的未分配的缺陷信息
	},"json")
}

/**
 * 	清空重置按钮 清空条件搜索表单
 * @returns
 */
function reload(){
	$("#form_search").form('clear');
	queryItemName();
	allotIsAdministrator();//查询所有可见缺陷信息
}
/**
 * 	上传修改验证
 * @returns
 */
function verify(){
        $('#appoint').combobox({
        	required:true,
        	missingMessage:"请选择指派给谁"
        });
}