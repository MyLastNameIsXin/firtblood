var isNew=true;//判断是新增还是修改
/**
 * 	页面加载事件
 * @returns
 */
$(function(){
	itemName();
	queryAll();//查询所有可见缺陷信息
	queryItemName();//加载项目名下拉列表
})
/**
 * 查询所有可见缺陷信息
 * @returns
 */
function queryAll(){
	var obj=$("#form_search").serialize();
	var url="../../../queryAllData.json?"+obj+"";
	$("#dg").datagrid({'url':url});
	queryState();//加载可操作状态下拉列表
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
		url:'../../../itempull.json',
	    valueField: 'projectId',
	    textField: 'projectName'
	})
	$("#itema").combobox('setValue', '全部');
}
/**
 * 	加载状态的下拉列表的下拉
 * @returns
 */
function queryState(){
	$("#state").combobox({
		 loadFilter:function(data){
		    	var data=data.list || data;
		    	data.unshift({
		    		sateCode:"", 
		    		stateDesc:'全部'
		    	})
		    	return data;
		    },
		url:'../../../statePull.json',
	    valueField: 'sateCode',
	    textField: 'stateDesc'
	})
	$("#state").combobox('setValue', '全部');
}
/**
 * 	新增按钮单机事件
 * @returns
 */
function doAdd(){
	isNew=true;
	$("#dlg_form").form('clear');
	$("#dlg_save").dialog({
		title:"新增任务"
	});
	$.post("../../../queryItemData.json",function(data){
		if(data.list.length<1){
			$("#dlg_save").panel('close');
			$.messager.alert("提示","只有测试人员才可新增，您还不是任何项目的测试人员，如有疑问请与管理员联系","info");
			return;
		}
		$("#dlg_save").dialog('open');
		itemName();//新增面板加载项目可新建下拉
		itemDefect();//新增修改面板加载缺陷状态下拉
		itemDegree();//新增修改面板加载严重程度下拉
		itemPriority();//新增修改面板加载优先级下拉
		itemaParentFlaw();//新增修改面板父级缺陷下拉列表
	},"json");
	removeNode();//加载文件上传按钮
    verify();//验证函数
}
/**
 * 新增面板加载项目可新建下拉
 * @returns
 */
function itemName(){
	$("#itemName").combobox({
		loadFilter:function(data){
	    	var data=data.list || data;
	    	return data;
	    	},
	    url:'../../../queryItemData.json',
	    valueField: 'projectId',
	    textField: 'projectName'
	});
}

/**
 * 	新增修改面板加载缺陷状态下来
 * @returns
 */
function itemDefect(){
	$("#itemDefect").combobox({
		loadFilter:function(data){
	    	var data=data.list || data;
	    	data[0].selected=true;
	    	return data;
	    	},
	    url:'../../../itemDefect.json',
	    valueField: 'typeCode',
	    textField: 'typeDesc',
	});
}

/**
 * 	新增修改面板加载严重程度下拉
 * @returns
 */
function itemDegree(){
	$("#itemDegree").combobox({
		loadFilter:function(data){
	    	var data=data.list || data;
	    	data[0].selected=true;
	    	return data;
	    	},
	    url:'../../../itemDegree.json',
	    valueField: 'severityCode',
	    textField: 'severityDesc'
	});
}
/**
 * 	新增修改面板加载优先级下拉
 * @returns
 */
function itemPriority(){
	$("#itemPriority").combobox({
		loadFilter:function(data){
	    	var data=data.list || data;
	    	data[0].selected=true;
	    	return data;
	    	},
	    url:'../../../itemPriority.json',
	    valueField: 'priorityCode',
	    textField: 'priorityDesc'
	});
}
/**
 * 	新增面板中父级缺陷下拉
 * @returns
 */
function itemaParentFlaw(){
	var projectId= $("#itemName").combo('getValue');
		$("#itemaParentFlaw").combobox({
			loadFilter:function(data){
		    	var data=data.list || data;
		    	return data;
		    	},
		    url:'../../../itemaParentFlaw.json?'+'projectId='+projectId+'',
		    valueField: 'issueId',
			textField: 'issueName'
		});
}
/**
 * 	新增修改面板按钮
 * @returns
 */
function doSave(){
	url = isNew?"../../../addItemDefect.json":"../../../modification.json";
	if($("#dlg_form").form("validate")){
		$('#dlg_form').form('submit', {
		    url:url,
		    success:function(data){//提交成功后的回调函数
		    	data= eval('(' + data + ')');  // 把JSON字符串解析为javascript对象
		    	if(isNew){
		    		if(data.row == "1"){
						$.messager.alert("提示","增加成功","info");
					}else if(data.row == "-1"){
						$.messager.alert("提示","附件上传失败，请联系管理员查看错误","error");
					}else{
						$.messager.alert("提示","增加失败，请联系管理员查看错误","error");
					}
		    	}else{
		    		if(data.row == "1"){
						$.messager.alert("提示","修改成功","info");
					}else if(data.row == "-1"){
						$.messager.alert("提示","附件修改上传失败，请联系管理员查看错误","error");
					}else{
						$.messager.alert("提示","修改失败，请联系管理员查看错误","error");
					}
		    	}
		    	queryAll();
		    	$("#dlg_save").panel('close');
		    }
		});
	}
}

/**
 * 	在每一列后面增加操作列
 * @returns
 */
function formatOper(value,row,index){
	var str='<a style="color:blue" class="pao" href="javascript:void(0);" onclick="state(\''+row.issueId+'\')">详情';
	return str;
}
/**
 * 	详情面板填充数据
 * @param data
 * @returns
 */
function state(issueId){
	$.post("../../../forDetails.json",{'issueUseId':issueId},function(data){
		$("#dlg_particulars").dialog({
			title:"缺陷详细信息"
		});
		$("#dlg_particulars").dialog('open');
		$("#projectId").text(data.list.projectId);
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
		$("#issueDesc").html(lineFeed(data.list.issueDesc));
		$("#assignee").text(ifNull(data.list.assignee))
		$("#doneCondition").html(lineFeed(data.list.doneCondition));
		$("#actWorkeHours").text(ifNull(data.list.actWorkeHours));
		$("#actStartTime").text(ifNull(data.list.actStartTime));
		$("#actEndTime").text(ifNull(data.list.actEndTime));
		if(data.list.assigendTime != null){
			$("#assigendTime").text(genderFormatter(data.list.assigendTime));
		}else{
			$("#assigendTime").text("----")
		}
		if(data.list.parentIssueName == null){
			//父级项目一栏为空时将其替换为“---”
			$("#parentIssueId").text("----");
		}
		if(data.list.doneRatio == null){
			//完成率一栏为空时将其替换为“0%”
			$("#doneRatio").text("0%");
		}
		if(data.list.issueAttach == null){
			//当前缺陷没有附件时将其一栏替换为“----”
			$("#attachName").text("----");
			return;
		}
		if(data.list.issueAttach.isPic == 0){
			$("#attachName").html("<a href='http://localhost:8080/training/showImg.json?attachId="+data.list.issueAttach.attachId+"' style=\"color:blue;\">下载</a>");
		}
		if(data.list.issueAttach.isPic == 1){
			var str=data.list.issueAttach.attachFile.split("_")[1]+"&nbsp;&nbsp;"+"<a href='http://localhost:8080/training/showImg.json?attachId="+data.list.issueAttach.attachId+"' style=\"color:blue;\">下载</a>"
				+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:preview("+data.list.issueAttach.attachId+")' style=\"color:blue;\">预览</a>";
			$("#attachName").html(str);
		}
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
 * 	删除一条缺陷信息
 * @returns
 */
function remove(){
	var row=$("#dg").datagrid('getSelections');
	if(row.length< 1){
		$.messager.alert("错误","请至少选择一条需要删除的数据","error");
		return;
	};
	$.messager.confirm('警告','请问是否确定进行删除操作?',function(r){
		if(r){
			$.post("../../../removeIssueById.json",{"issueUseId":row[0].issueId},function(data){
				//data= eval('(' + data + ')');  // 把JSON字符串解析为javascript对象
				if(data.row == "-1"){
					$.messager.alert("提示","您没有权限删除该缺陷，如有疑问请与管理员联系","info");
				}else if(data.row == "0"){
					$.messager.alert("提示","删除失败，该缺陷已被分配无法删除","info");
				}else{
					$.messager.alert("提示","删除成功","info");
				}
				queryAll();
			},"json")
		}
	})
}
/**
 * 	显示详情页面的关闭按钮
 * @returns
 */
function colsePanel(){
	$("#dlg_particulars").panel('close');
}
/**
 * 	详情页图片预览超链接
 * @returns
 */
function preview(attachId){
	$("#dlg_preview2 img").attr("src","http://localhost:8080/training/showImg.json?attachId="+attachId+"");
	$("#dlg_preview2").dialog({
		title:"缺陷信息图片预览",
		width:550,
		height:550
	});
	$("#dlg_preview2").dialog('open');
}

/**
 * 	预览窗口的关闭按钮
 * @returns
 */
function colsePanel2(){
	$("#dlg_preview2").panel('close');
}

/**
 * 	修改缺陷信息
 * @returns
 */
function modif(){
	isNew=false;
	$("#btn_dlg a").linkbutton({text:'确认'}); 
	var row=$("#dg").datagrid('getSelections');
	if(row.length< 1){
		$.messager.alert("错误","请至少选择一条需要修改的数据","error");
		return;
	};
	$("#dlg_particulars").dialog({
		title:"修改缺陷信息"
	});
	$.ajax({
		type:"post",
		url:"../../../forDetails.json",
		dataType:"json",
		data:{'issueUseId':row[0].issueId},
		async:false,
		success:function(data){
			var userID=data.userId;
			if(data.list.createBy != userID){
				//输出当前用户是否能够修改
				$.messager.alert("提示","您没有权限修改该缺陷，如有疑问请与管理员联系","info");
				return;
			}
			if(data.list.issueStateNum != "01"){
				//输出当前缺陷是否能够修改
				$.messager.alert("提示","该缺陷不是待分配无法修改","info");
				return;
			}
			$("#dlg_save").dialog({
				title:"修改缺陷信息"
			});
			$("#dlg_save").dialog('open');
			itemDefect();//新增修改面板加载缺陷状态下拉
			itemDegree();//新增修改面板加载严重程度下拉
			itemPriority();//新增修改面板加载优先级下拉
			itemaParentFlaw();//新增修改面板父级缺陷下拉列表
			$('#dlg_save').form('load',data.list);
			if(data.list.issueAttach == null){
				//	判断当前缺陷有无附件
				var str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件上传&nbsp;&nbsp;<input type='text' id='fildDomain' name='file' style=\"width:310px;\"/>"
				$("#fileTd").html(str)
			 $('#fildDomain').filebox({
			    	buttonText: '选择文件',
			        buttonAlign: 'right'
			   })
			   return;
			}
			if(data.list.issueAttach != null){
				//当前缺钱有附件
				var str1 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件&nbsp;&nbsp;<a href='http://localhost:8080/training/showImg.json?attachId="+data.list.issueAttach.attachId+"' style=\"color:blue;\">下载</a>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:removeNode()' style=\"color:blue;\">删除<a/>"
				$("#fileTd").html(str1)
			}
			consolo.log(data.list.issueAttach.isPic);
			if(data.list.issueAttach.isPic == 1){
				//判断当前附件是否图片
				var str2="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件&nbsp;&nbsp;<a href='http://localhost:8080/training/showImg.json?attachId="+data.list.issueAttach.attachId+"' style=\"color:blue;\">下载</a>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:preview("+data.list.issueAttach.attachId+")' style=\"color:blue;\">预览</a>"+
					"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:removeNode()' style=\"color:blue;\">删除<a/>";
				$("#fileTd").html(str2);
			}
		}
	});
	$("#itemName").textbox("disable");
	$("#itemaParentFlaw").textbox("disable");
	verify();//验证函数
}
/**
 * 	修改面板删除当前附件
 * @returns
 */
function removeNode(){
	var str="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件上传&nbsp;&nbsp;<input type='text' id='fildDomain' name='file' style=\"width:310px;\"/>"
	$("#fileTd").html(str)
	 $('#fildDomain').filebox({
	    buttonText: '选择文件',
	    buttonAlign: 'right'
	})
}
/**
 * 	上传修改验证
 * @returns
 */
function verify(){
	if(isNew){
        $('#itemName').combobox({
        	required:true,
        	missingMessage:"项目为必选项，请选择"
        });
        $('#itemaTime').numberbox({
        	required:true,
			missingMessage:"预计工时不能为空"
        });
       $("#itemaaaa").textbox({
    	   required:true,
			missingMessage:"缺陷名称不能为空"
       })
	}else{
		$('#itemaTime').numberbox({
        	required:true,
			missingMessage:"预计工时不能为空"
        });
        
	}
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
 * 	清空重置按钮 清空条件搜索表单
 * @returns
 */
function reload(){
	$("#form_search").form('clear');
	queryAll();//查询所有可见缺陷信息
	queryItemName();//加载项目名下拉列表
}
/**
 * 	将多行文本以多行文本显示
 * @param val 传入的文本值
 * @returns
 */
function lineFeed(val){
	return val=val.replace("\n","<br/>")
}