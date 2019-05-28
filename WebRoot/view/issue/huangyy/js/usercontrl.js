var url="";

/**
 * 功能:初始化
 * 参数：无
 */
$(function(){
	//加载用户列表
	$("#dg").datagrid(Common.createDatagridOptionsParams(basePath, "/searchAllUser.json", 135, null));
	
});

/**
 * 功能：格式化genderId列的显示效果
 * 参数：
 * 	value：某行该列的值
 * 	row：该行的数据
 * 	index：该行的下标genderFormatter
 */
function genderFormatter(value, row, index){
	if(value=="O"){
		return  "保密";
	}else if(value=="M"){
		return  "男";
	}else if(value=="F"){
		return  "女";
	}
}


/**
 * 功能：格式化isUsed列的显示效果
 * 参数：
 * 	value：某行该列的值
 * 	row：该行的数据
 * 	index：该行的下标
 */
function isUsedFormatter(value, row, index){
	if(value==1){
		return  "<img src='../../css/themes/icons/ok.png'/>";
	} else if(value==0){
		return  "—";
	}
}

/**
 * 功能：打开新增对话框
 * 参数：无
 */
function doAdd(){
	isNew = true;//新增
	url="/addUserContrl.json";
	userValid("add");//初始化校验
	$("[textboxname='userId']").textbox("readonly",false);
	$("#form_save").form("reset");
	$("#userId").removeAttr("readonly");
	$("#yc").show();
	$("#dlg_save").show().dialog({
		iconCls:"icon-save",
		title:"新增用户信息",
		width:320,
		height:"auto",
		modal:true,
		buttons:"#btn_dlg",
		onClose:function(){
			$("#form_save").form("reset");
		}
	});
	
	setTimeout(function(){
		$("[textboxname='userId']").focus();
	}, 300);
}

/**
 * 功能：保存修改或添加
 * 参数：无
 */
function doSave(){	
	var formData = $("#form_save").serializeObject();
	var roleIds = formData.roleIds;
	if(roleIds!=null){
		if(typeof roleIds=="string"){
			
		}else if(roleIds){
			formData.roleIds = roleIds.join(",");
		}
	}
	
	
	if($("#form_save").form("validate")){

		$.ajax({
			url:basePath + url,
			type:"post",
			dataType:"json",
			data:formData,
			success:function(data){
					$("#dg").datagrid("reload");
					doCancel();
					$.messager.alert("提示信息","恭喜，操作成功","info");
			},
			error:function(){
				$.messager.alert("错误","服务器内部错误","error");
			}
		});
	}
	
}

/**
 * 打开修改对话框
 * 参数：无
 */
function doModify(){
	isNew = false;//修改
	url="/modifUserContrl.json";
	var rows = $("#dg").datagrid("getSelections");	
	if(rows==null || rows.length==0){
		$.messager.alert("提示信息","请输入要修改的用户","info");
		return;
	}else if(rows.length>1){
		$.messager.alert("提示信息","一次只能修改一个用户","info");
		return;
	}
	var row=rows[0];
	userValid("modify");
	$("#yc").hide();
	//显示对话框
	$("#dlg_save").show().dialog({
		iconCls:"icon-save",
		title:"修改用户信息",
		width:320,
		height:"auto",
		modal:true,
		buttons:"#btn_dlg",
		onClose:function(){
			$("#form_save").form("reset");
			
		}
	});
	$("#form_save").form("load",row);
	$("#userId").textbox("readonly","readonly");
	setTimeout(function(){
		$("[textboxname='userName']").next('span').find('input').focus();
	}, 300);
	
}



/**
 * 功能:取消按钮点击事件，重置输入表单，关闭对话框
 * 参数：无
 */
function doCancel(){
	$("#form_save").form("reset");
	$("#dlg_save").dialog("close");
}



/**
 * 功能：删除选中的用户
 * 参数：无
 */
function doDelete(){
	var ids = $("#dg").datagrid("getSelections");
	if(ids.length < 1){
		$.messager.alert("提示","请选择要删除的学生信息");
		return;
	}
	var userIds = [];

	$.messager.confirm('警告','请问，是否进行删除操作?',function(r){
	    if (r){
	    	ids.forEach(function(user,index,ids){
				userIds[index] = user.userId;
			});
			$.post(basePath +"/deleteUserContrl.json",{"userIds":userIds},function(data){			
				if(data.result != 0){
					$("#dg").datagrid("reload");
				}else{
					alert("删除失败");
				}
			},"json");
	    }
	});
}

/**
 * 功能：条件查询
 * 参数：无
 */
function doUserSearch(){
	var params = $("#form_search").serializeObject();
	$("#dg").datagrid("load", params);
}


/**
 * 功能：输入校验，新增时输入的用户编号是否存在
 * 参数：无
 */
function userValid(r){
	if(r=="add"){
		$("#userId").textbox({
			required:true,		
			missingMessage:"用户名不能为空",
			invalidMessage:"用户编号已经存在",
			validType:'remoteValid[basePath +"/verifyUserId.json","userId"]'	
		});
		$("[textboxname='password']").textbox({
			required:true,
			missingMessage:"登录密码不能为空",
			invalidMessage:"密码不能少于6位多余12位",
			validType:['number','length[6,12]']
		});
	}else{
		$("#userId").textbox({
			invalidMessage:null,
			validType:null	
		});
		$("[textboxname='password']").textbox({
			required:null,
			missingMessage:null,
			invalidMessage:null,
			validType:null
		});
	}
	
	$("[textboxname='userName']").textbox({
		required:true,		
		missingMessage:"用户名不能为空"
	});


	
	$("[textboxname='email']").textbox({
		invalidMessage:"电子邮件格式错误",
		delay:1000,
		validType:"email"
	});
	
	$("[textboxname='mobile']").textbox({
		invalidMessage:"移动电话格式错误",
		delay:1000,
		validType:['number','length[11,11]']
	});
	
}



