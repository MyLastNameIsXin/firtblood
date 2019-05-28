
let roleID = location.href.split("?")[1].split("=")[1];

loadStartData()
function loadStartData(){
	$("#infoTable").datagrid({
		url:"getDate.json?protect_ID="+roleID
	})
}

/**
 * 显示增加框
 * @returns
 */
function addBtn(){
	$.get("findUserName.json",addDataUserName,"json");
	$.get("findRoleName.json",addDataRoleName,"json");
	
	//document.getElementById("addRoleMuenForm").reset();
	$("#addRoleMuen").dialog({
		closed:false
	});
}

function addDataUserName(data){
	let value = `<option value=""></option>`;
	for(ro of data.rows){
		value += `<option value="${ro.userId}">${ro.userName}</option>`
	}
	$("#userId").html(value);
}

function addDataRoleName(data){
	let value = `<option value=""></option>`;
	for(ro of data.rows){
		value += `<option value="${ro.roleCode}">${ro.roleName}</option>`
	}
	$("#roleCode").html(value);
}


/**
 * 关闭增加显示框
 * @returns
 */
function closeBtn(){
	
	
	
	$("#addRoleMuen").dialog({
		closed:true
	});
	//document.getElementById("addRoleMuenForm").reset();
} 

/**
 * 添加确认按钮
 * @returns
 */
function savaBtn(){
	console.log($("#userId").val())
	console.log($("#roleCode").val())
	if($("#userId").val()=="" || $("#roleCode").val()==''){
		$.messager.alert('提示','姓名与角色不能为空'); 
		return;
	}
	let formData = $("#addRoleMuenForm").serializeObject();
	formData.protectId = roleID;
	$.post("addRoleInfo.json",formData,resultData,"json");
}

function resultData(data){
	if(data.row==1){
		$.messager.alert('提示','添加成功'); 
		loadStartData();
		closeBtn()
	}else{
		$.messager.alert('提示','用户角色已存在'); 
	}
}

/**
 * 
 */
function removeBtn(){
	let rowInfo = $("#infoTable").datagrid('getSelected');
	rowInfo.protectId = roleID;
	$.get("removeRoleInfo.json",rowInfo,resultDataInfo,"json");
}

function resultDataInfo(data){
	if(data.row>0){
		loadStartData();
		$.messager.alert('提示','删除成功'); 
	}else{
		$.messager.alert('提示','删除失败'); 
	}
}
/**
 * 表单数据格式化
 */
$.fn.serializeObject = function() {  
    var o = {};  
    var a = this.serializeArray();  
    $.each(a, function() {  
        if (o[this.name]) {  
            if (!o[this.name].push) {  
                o[this.name] = [ o[this.name] ];  
            }  
            o[this.name].push(this.value || '');  
        } else {  
            o[this.name] = this.value || '';  
        }  
    });  
    return o;  
};

//function resultData(data){
//	console.log(data.rows)
//	$("#infoTable").datagrid({
//		data:data.rows
//	})
//}