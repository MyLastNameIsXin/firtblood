
var isNew = true;//是否为新增，true:新增，false：修改
var url=""//新增修改的提交地址
var logicCode="";//获取修改角色的编号
/**
 * 功能:初始化
 * 参数：无
 */
$(function(){
	
});
/**
 * 	新增修改面板的复选框
 * @returns
 */
function operability(){
	$.get("../../../operability.json",function(data){
		var str="";
		var num=1;
		$.each(data.list,function(index,eml){
			if(num%2 !=0){
				str+="<input type='checkbox' name='sateCode' value='"+eml.sateCode+"'/><span>"+eml.stateDesc+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			}else{
				str+="<input type='checkbox' name='sateCode' value='"+eml.sateCode+"'/><span>"+eml.stateDesc+"</span><br/><br/>"
			}
			num++;
		});
		$("#aaa").html(str);
	},"json");
} 

/**
 * 	在每行末尾添加操作列
 * @param index
 * @returns
 */
function formatOper(value,row,index){
	var str='<a style="color:red" class="pao" href="javascript:void(0);" onclick="state(\''+row.roleCode+'\',\''+row.moduleState+'\')">禁用';
	var str2='<a style="color:blue" class="pao" href="javascript:void(0);" onclick="state(\''+row.roleCode+'\',\''+row.moduleState+'\')">启用';
	if(row.moduleState=="0"){
		return str2;
	}else{
		return str;
	}
	
}

/**
 * 	修改函数
 * @returns
 */
function modif(){
	isNew=false;
	logicCode="";
	$("#dlg_save").dialog({
		title:"修改角色信息"
	});
	$("#btn_dlg a").linkbutton({text:'确认'}); 
	var row=$("#dg").datagrid('getSelections');
	if(row.length< 1){
		$.messager.alert("错误","请至少选择一条需要修改的数据","error");
		return;
	}else if(row.length > 1){
		$.messager.alert("警告","一次只能修改一条数据信息","warning");
		$("#dg").datagrid('clearSelections');
		return;
	};
	var roleCode=row[0].roleCode;
	$.ajax({  
        type : "post",  
        url : "../../../queryRole.json",
        dataType:"json",
        data : {'roleCode':roleCode},  
        async : false,
        success : function(data){  
        	$("#roleNum").textbox('setValue',data.list[0].roleCode);
				$("#roleName").textbox({
					value:data.list[0].roleName
					});
				var canDo=data.list[0].canDo.split(',');
				setTimeout(function(){
					$("input[name=sateCode]").each(function(index,element){
						for(var i=0;i<canDo.length;i++){
							 if($(this).next().text() == canDo[i]){
								 $(this).prop("checked",true);
							 }
						}
					});
				}, 300);
        	}  
        }); 
	$("#dlg_save").dialog('open');
	$("#roleNum").textbox({'disabled':true});
	verify();
	
}
/**
 * 	删除角色信息
 * @param index
 * @returns
 */
function remove(){
	var row=$("#dg").datagrid('getSelections');
	if(row.length< 1){
		$.messager.alert("错误","请至少选择一条需要删除的数据","error");
		return;
	};
	var roleCodes=[];
	$.messager.confirm('警告','请问是否确定进行删除操作?',function(r){
		if(r){
			row.forEach(function(role,index,row){
				roleCodes[index]=role.roleCode
			});
			$.post("../../../removeRole.json",{'roleCode':roleCodes},function(data){
				if(data.str=="1"){
					$.messager.alert("提示","删除成功","info");
				}else{
					$.messager.alert("提示","删除失败","error");
				}
				$("#dg").datagrid('reload');
			},'json')
		}
	})
}
/**
 * 	启用/禁用角色信息
 * @returns
 */
function state(roleCode,moduleState){
	if(moduleState == "1"){
		 moduleState="0";
	}else{
		 moduleState="1";
	}
	$.post("../../../starFor.json",{'roleCode':roleCode,"moduleState":moduleState},function(data){
		if(data.str=="1"){
			$.messager.alert("提示","操作成功","info");
		}else{
			$.messager.alert("提示","操作失败","error");
		}
		$("#dg").datagrid('reload');
	},'json')
}
/**
 * 	新增按钮
 * @returns
 */
function doAdd(){
	$("#dlg_form").form('clear');
	$("#dlg_save").dialog({
		title:"新增角色信息"
	});
	$("#roleNum").textbox({'disabled':false});
	$("#dlg_save").dialog('open');
	isNew=true;
	verify();
}
/**
 * 	新增面板的增加按钮
 * @returns
 */
function doSave(){
	if($("#dlg_form").form("validate")){
		isNew?url="../../../addRole.json":url="../../../modifierRole.json";
		var obj=$("#dlg_form").serializeObject();
		if(obj.sateCode == undefined){
			$.messager.alert("提示","请选择角色的可操作状态","info");
			return;
		}
		if(obj.sateCode.constructor != Array){
			var arr = new Array()
			arr[0] = obj.sateCode;
			obj.sateCode = arr;
		}
		var modRoleCode=$("#roleNum").val();
		$.ajax({
			url:url,
			type:"post",
			datatype:"json",
			data:{'obj':obj,'modRoleCode':modRoleCode},
			success:function(data){
				$("#dlg_form").form('clear');
				$("#dlg_save").dialog('close');
				if(data=="0"){
					$.messager.alert("错误","添加数据错误","error");
					return;
				}
				$("#dg").datagrid('reload');
			},
			error:function(){
				$.messager.alert("错误","服务器内部错误","error");
			}
		});
	}
	
}
/**
 * 	序列化状态列0表示无效1表示有效
 * @param value
 * @param row
 * @param index
 * @returns
 */
function genderFormatter(value, row, index){
	if(value=="0"){
		return  "无效";
	}else if(value=="1"){
		return  "有效";
	}
}
/**
 * 	验证用户编号以及角色名是否存在
 * @returns
 */
function verify(){
	if(isNew){// 新增验证
		$("#roleNum").textbox({
			required:true,
			missingMessage:"用户编号不能为空",
			invalidMessage:"用户编号已经存在",
			validType:'remoteValid["../../../erifyRoleCode.json","roleCode"]'
		});
		$("#roleName").textbox({
			required:true,
			missingMessage:"角色不能为空",
			invalidMessage:"角色已经存在",	
			validType:'remoteValid["../../../erifyRoleName.json","roleName"]'	
		});
	}else{// 修改验证
		$("#roleName").textbox({
			required:true,
			missingMessage:"角色不能为空",
			invalidMessage:"角色已经存在",	
			validType:"remoteValid['../../../erifyRoleName.json','roleName',{roleCode:'"+$("#roleNum").val()+"'}]"
		});
	}
}
/**
 * 	自定义验证日期框
 */
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '预计结束日期不能小于开始日期'
    }
});


