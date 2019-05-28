


/**
 * 数据初始化过滤
 * @param data
 * @returns
 */
function loadProjectInfo(data){
	return data.rows;
}


/**
 * 状态格式修改
 * @param value
 * @param row
 * @param index
 * @returns
 */
function showState(value, row, index){
	document.getElementsByClassName
	return value=="1"?`<span>有效</span>`:`<span style="color:red;">无效</span>`;
}

/*changeUrl();
function changeUrl(){
	if($("#showNoUsedMenu").prop("checked")){
		$('#menuTreeGrid').treegrid({
			 url:'findProtectInfo.json'
		 })
	}else{
		$('#menuTreeGrid').treegrid({
			 url:'findProtectStartInfo.json'
		 }) 
	}
	$('#menuTreeGrid').treegrid('reload');
}*/


/**
 * 刷新页面
 * @returns
 */
function doRefresh(){
	$('#menuTreeGrid').treegrid('reload');
}
/**
 * 添加操作列类容
 * @param value
 * @param row
 * @param index
 * @returns
 */
function domContent(value, row, index){
	let node = JSON.stringify(row);
	let str =`<a href="iIssueRole.html?protectID=${row.projectId}" style='color:blue;text-decoration:none;'>成员管理</a>&nbsp;|
			  <a href="javascript:void(0)" onclick='showInfo(${node})' style='color:blue;text-decoration:none;'>详情</a>&nbsp;|&nbsp;`;
	if(row.projectState=="1"){
		str += `<a href='javascript:void(0)' onclick='changeState(${node})' style='color:red;text-decoration:none;'>关闭</a>`;
	}else{
		str += `<a href='javascript:void(0)' onclick='changeState(${node})' style='color:blue;text-decoration:none;'>打开</a>`;
	}
	return str;
}

/**
 * 更改项目状态信息
 * @param obj
 * @returns
 */
function changeState(obj){
	obj.projectState = obj.projectState == "1" ?"0":"1";
	//判断父级项目是否存在与存在时状态是否为无效
	let dataInfo = $('#menuTreeGrid').treegrid('getParent',obj.projectId);
	if(dataInfo != null){
		console.log('1')
		if(dataInfo.projectState == "0"){
			console.log('2')
			$.messager.alert('提示','父级项目为无效,无法修改，请先修改父级项目状态'); 
			return;
		}
	}
	modifyProjectInfo(JSON.stringify(obj));
}

/**
 * 显示项目详情信息
 * @param obj
 * @returns
 */
function showInfo(obj){
	$("#detailedMuen").form('load',obj);
	$("#detailedMuen").dialog({
		closed:false
	});
}


/**
 * 显示新增框
 * @returns
 */
function addRootMenu(){
	document.getElementById("addRootMenuForm").reset();
	$("#addRootMenu").dialog({
		closed:false
	});
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

/**
 * 提交新增
 * @returns
 */
function sureAddBtn(){
	//判断项目编号是否为空
	if($("#addprojectId").val()=="" ){
		$('#showAddInfo').html("项目编号不能为空,请填写");
		document.getElementById("showAddInfo").className = "isid";
		return;
	}
	//判断项目名称是否为空
	if($("#addprojectName").val()==""){
		$('#showNameInfo').html("项目名称不能为空,请填写");
		document.getElementById("showNameInfo").className = "isid";
		return;
	}
	//判断提示信息是否为空
	if($('#showAddInfo').html()=="" 
		&& $("#showNameInfo").html()=="" 
		){
		formData = $("#addRootMenuForm").serializeObject();
		$.post('addProtectInfo.json',formData,function(data){
			if(data.row>0){
				 $("#menuTreeGrid").treegrid('append',{
						data:{"rows":[formData]}
					});
				$.messager.alert('提示','操作成功');
				closeAddMeun()
			}
		},"json");
	}
	else{
		return;
	}
}
/**
 * 关闭增加根目录框
 * @returns
 */
function closeAddMeun(){
	$("#addRootMenu").dialog({
		closed:true
	});
}


/**
 * 显示新增下级muen
 * @returns
 */
function addNextMenu(){
	var node = $('#menuTreeGrid').treegrid('getSelected');
	//判断父级是否选择,没选择不能添加
	if(node==null){
		$.messager.alert('提示','请选择父级数据'); 
		return;
	}
	//判断父级是否有效(无效的父级不能新增下级项目)
	if(node.projectState==0){
		$.messager.alert('提示','无效项目不能添加子项目,请将父级项目的状态改为有效'); 
		return
	}
	let superID =  node.projectId;
	$("#superId").html(superID+".");
	document.getElementById("addNextMenuForm").reset();
	$("#addNextMenu").dialog({
		closed:false
	});
}

/**
 * 确认新增次级项目
 * @returns
 */
function sureNextBtn(){
	//项目编号值为空验证
	if($("#projectNext_ID").val()=="" ){
		$('#showNextInfo').html("项目编号不能为空,请填写");
		document.getElementById("showNextInfo").className = "isid";
		return;
	}
	//项目名称值为空验证
	if($("#projectNextNAME").val()==""){
		$('#showNameNextInfo').html("项目名称不能为空,请填写");
		document.getElementById("showNameNextInfo").className = "isid";
		return;
	}
	//项目提示为空验证通过继续执行
	if($('#showNextInfo').html()=="" 
		&& $("#showNameNextInfo").html()=="" 
		){
		//获取当前选中的行(父级)
		let node = $('#menuTreeGrid').treegrid('getSelected');
		//表单数据对象化
		formData = $("#addNextMenuForm").serializeObject();
		formData.projectId = $("#superId").html()+$("#projectNext_ID").val();
		formData.pProjectId = node.projectId;
		//新增提交请求
		$.post('addProtectInfo.json',formData,
			function(data){
				if(data.row>0){
					//获取当前选中的行(父级)
					//let node = $('#menuTreeGrid').treegrid('getSelected');
					//在父级行下面加入新增的数据
					$("#menuTreeGrid").treegrid('append',{
						parent:node.projectId,
						data:{"rows":[formData]}
					});
					$.messager.alert('提示','增加成功');
				}else{
					$.messager.alert('提示','增加失败');
				}
				closeNextMuen();
		},"json");
	}else{
		return;
	}
}

/**
 * 关闭次级项目
 * @returns
 */
function closeNextMuen(){
	$("#addNextMenu").dialog({
		closed:true
	});
	document.getElementById("addNextMenuForm").reset();
}


/**
 * 显示修改框
 * @returns
 */
function modifyMenu(){
	var node = $('#menuTreeGrid').treegrid('getSelected');
	//判断父级是否选择,没选择不能修改
	if(node==null){
		$.messager.alert('提示','请选择修改数据'); 
		return;
	}
	//判断父级项目是否存在与存在时状态是否为无效
	let dataInfo = $('#menuTreeGrid').treegrid('getParent',node.projectId);
	if(dataInfo != null){
		if(dataInfo.projectState == "0"){
			$.messager.alert('提示','父级项目为无效,无法修改，请先修改父级项目状态'); 
			return;
		}
	}
	//打开修改框
	$("#modifyMenuForm").form('load',node);
	$("#modifyMenu").dialog({
		closed:false
	});
}

/**
 * 确认修改
 * @returns
 */
function sureModifyBtn(){
	if($("#projectModifyName").val() == ""){
		$.messager.alert('提示','项目名称不能为空'); 
	}
	let formData = $("#modifyMenuForm").serializeObject();
	
	modifyProjectInfo(JSON.stringify(formData));
//	$.post(
//			'modifyProtectInfo.json',
//			formData,
//			function(data){
//				if(data.rows>0){
//					if(formData.projectState == "0"){
//						let node = JSON.stringify(formData);
//						updateDataInfo(node);
//					}else{
//						$("#menuTreeGrid").treegrid('update',{
//							id: formData.projectId,
//							row:formData
//						});
//					}
//					$.messager.alert('提示','修改成功');
//					closeModifyMeun()
//				}
//			},'json');
}

function modifyProjectInfo(node){
	 let formData = JSON.parse(node)
	$.post(
			'modifyProtectInfo.json',
			formData,
			function(data){
				if(data.rows>0){
					if(formData.projectState == "0"){
						let node = JSON.stringify(formData);
						updateDataInfo(node);
					}else{
						$("#menuTreeGrid").treegrid('update',{
							id: formData.projectId,
							row:formData
						});
					}
					$.messager.alert('提示','修改成功');
					closeModifyMeun()
				}
			},'json');
}

/**
 * 递归更新所有修改的项目信息
 * @param node
 * @returns
 */
function updateDataInfo(node){
	let obj = JSON.parse(node);
	let dataInfo = $('#menuTreeGrid').treegrid('getChildren',obj.projectId);
	$("#menuTreeGrid").treegrid('update',{
		id: obj.projectId,
		row:obj
	});
	if(dataInfo.length!=0){
		for(let info of dataInfo){
			info.projectState = '0';
			updateDataInfo(JSON.stringify(info));
		}
	}
}


/**
 * 关闭修改框
 * @returns
 */
function closeModifyMeun(){
	$("#modifyMenu").dialog({
		closed:true
	});
}

/**
 * 根项目ID与Name验证
 * @param btn
 * @returns
 */
function isID(btn){
	let id = btn.value;
	$.get("findProtectId.json",{'id':id},findIdResult,"json");
}
function findIdResult(data){
	if(data.row==0){
		$('#showAddInfo').html("");
		document.getElementById("showAddInfo").className = "";
	}else{
		$('#showAddInfo').html("项目编号已存在请从新填写");
		document.getElementById("showAddInfo").className = "isid";
	}
}

/**
 * 次级项目ID与Name验证
 * @param btn
 * @returns
 */
function isNextID(btn){
	let id = $("#superId").html()+""+btn.value;
	$.get("findProtectId.json",{'id':id},findIdNextResult,"json");
}
  
function findIdNextResult(data){
	if(data.row==0){
		$('#showNextInfo').html("");
		document.getElementById("showNextInfo").className = "";
	}else{
		$('#showNextInfo').html("项目编号已存在请从新填写");
		document.getElementById("showNextInfo").className = "isid";
	}
}

function isName(btn){
	if(btn.value){
		$('#showNameInfo').html("");
		document.getElementById("showNameInfo").className = "";
		$('#showNameNextInfo').html("");
		document.getElementById("showNameNextInfo").className = "";
	}
}
