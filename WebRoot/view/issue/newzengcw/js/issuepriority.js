//用于修改时纪录主键
var prePriorityCode = "";
var result = "";

$(function () {
	//初始化页面数据
	$("#dataShow").datagrid({
		url: 'http://localhost:8080/training/priority/query.json',
		loadFilter: function (data) {
			for (var i = 0; i < data.rows.length; i++) {
				if (data.rows[i].moduleState == "1") {
					data.rows[i].moduleState = "有效";
					data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +
						'>禁用</a>';
				} else {
					data.rows[i].moduleState = "无效";
					data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:green;">启用</a>';
				}
			}
			return data;
		}
	});

	//给修改窗口按钮绑定提交数据事件
	$("#formsure").click(formsure);
	
	//给删除按钮绑定删除事件
	$("#remove").click(function(){
		$.messager.confirm('确认框', '确认删除吗',function(r){
			if(r){
				var array = $("#dataShow").datagrid("getSelections");
				var arrays = [];
				if(array.length == 0){
					return;
				}
				for(var i = 0;i<array.length;i++){
					arrays.push(array[i].priorityCode);
				}
			deletepriorities(arrays);
				}
			})
	});

	//给增加按钮绑定弹出窗口事件
	$("#add").click(function(){
		//弹出增加窗口
		emptyAddForm();
		$("#ad").dialog("open");
	});

	//给增加确认按钮绑定数据提交事件并重新渲染表格
	$("#adformsure").click(function(){
		var priorityCode = $("#addInputPriorityCode").val();
		var priorityDesc = $("#addinputPriorityDesc").val();
		if(priorityCode.trim() == "" || priorityDesc.trim() == ""){
			return;
		}
		if(checkedData==false){
			return;
		}
		checkedData=false;
		$.post('http://localhost:8080/training/priority/addOnePriprity.json',{priorityCode:priorityCode,priorityDesc:priorityDesc},
		function(data){
			if(data.result>0){
				$.messager.alert('提示框','添加成功','info');
				$('#dataShow').datagrid('appendRow',{
					priorityCode: priorityCode,
					priorityDesc: priorityDesc,
					moduleState: '有效',
					operation:'<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +
					'>禁用</a>'
				});				
			}else{
				$.messager.alert('提示框','添加失败','info');
			}
		});
		$("#ad").dialog("close");
		emptyAddForm();
	});

	//清空修改窗口
	$("#formrest").click(function(){
		emptyModifyForm();
	});

	//清空增加窗口
	$("#adformrest").click(function(){
		emptyAddForm();
	});
});



//操作列的修改按钮绑定弹出修改窗口
	function showModifyWindow(){
		var array = $("#dataShow").datagrid("getSelections");
		if(array.length!=1){
			$.messager.alert('提示框','请确认选择了一项','info');
			return;
		}
			 prePriorityCode = array[0].priorityCode;
			 result = array[0].priorityDesc;
			 $("#inputPriorityCode").textbox({
				 value:array[0].priorityCode
			 });
			 $("#inputPriorityDesc").textbox({
				value:array[0].priorityDesc
			});
		$("#dd").dialog("open");
	}





//操作列的启用或禁用绑定修改状态事件
function modifyState(e,event) {
	window.event ? window.event.cancelBubble = true : event.stopPropagation();
	var td = e.parentElement.parentElement.parentElement;
	var index = td.getAttribute("datagrid-row-index");
	var row = $("#dataShow").datagrid("getRows")[index];
	var moduleState = (row.moduleState == "无效"? '1' : '0');
	$.post('http://localhost:8080/training/priority/modifymodulestate.json',{'priorityCode':row.priorityCode,'moduleState':moduleState},function(data){
		if(data.result > 0){
			$('#dataShow').datagrid('updateRow',{
				index: index,
				row: {
					moduleState:moduleState == "1"?"有效":"无效",
					operation:moduleState == "1"?'<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +
					'>禁用</a>':'<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:green;">启用</a>'
				}
			});			
		}
	},'json');
}


//提交修改数据
function formsure() {
	var priorityCode =  $("#inputPriorityCode").val();
	var priorityDesc =  $("#inputPriorityDesc").val();
	if(priorityCode == null || priorityDesc == null || priorityCode.trim()=="" || priorityDesc.trim() == ""){
		return;
	}
	if(checkedData==false){
		return;
	}
	var data = { priorityCode:priorityCode, priorityDesc:priorityDesc,prePriorityCode:prePriorityCode };
	$.post('http://localhost:8080/training/priority/modify.json', data,
		function (data) {
			if(data.result > 0){
				$.messager.alert('结果','成功修改','info');
				$("#dataShow").datagrid({
					url: 'http://localhost:8080/training/priority/query.json',
					loadFilter: function (data) {
						for (var i = 0; i < data.rows.length; i++) {
							if (data.rows[i].moduleState == "1") {
								data.rows[i].moduleState = "有效";
								data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +
								'>禁用</a>';
							} else {
								data.rows[i].moduleState = "无效";
								data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:green;">启用</a>';
							}
						}
						return data;
					}
				});
			}else{
				$.messager.alert('结果','修改失败','info');
			}
		}, 'json'
	);
	$("#dd").dialog("close");
}


//删除数据事件，array为一个数组对象的实例
function deletepriorities(array){
	$.post('http://localhost:8080/training/priority/delete.json',{'priorityCodes':array},function(data){
		if(data.result > 0){
			$.messager.alert('结果','成功删除','info');
			$("#dataShow").datagrid({
				url: 'http://localhost:8080/training/priority/query.json',
				loadFilter: function (data) {
					for (var i = 0; i < data.rows.length; i++) {
						if (data.rows[i].moduleState == "1") {
							data.rows[i].moduleState = "有效";
							data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +
						'>禁用</a>';
						} else {
							data.rows[i].moduleState = "无效";
							data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:green;">启用</a>';
						}
					}
					return data;
				}
			});
		}else{
			$.messager.alert('结果','删除失败','info');
		}
	},'json');
}




//清空增加表单
function emptyAddForm(){
	$("#addInputPriorityCode").textbox({value:null});
	$("#addinputPriorityDesc").textbox({value:null});
}

function emptyModifyForm(){
	$("#inputPriorityCode").textbox({value:prePriorityCode});
	$("#inputPriorityDesc").textbox({value:result});
}

/*
 * 声明一个校验
 */
var checkedData = false;
$.extend($.fn.validatebox.defaults.rules, {
    existence:{
		validator: function(value,fn){
			$.ajax({
				url:"http://localhost:8080/training/priority/checkedInfo.json",
				async:false,
				data:{id:value},
				type:"post",
				success:function(newdata){
					checkedData = newdata.data;
				},
				dataType:"json"
			});
			return checkedData;
		},
		message: "此信息以存在"
    }
});

$.extend($.fn.validatebox.defaults.rules, {
    existenceModify:{
		validator: function(value){
			if(value==prePriorityCode){
				checkedData = true;
			}else{
				$.ajax({
					url:"http://localhost:8080/training/priority/checkedInfo.json",
					async:false,
					data:{id:value},
					type:"post",
					success:function(newdata){
						checkedData = newdata.data;
					},
					dataType:"json"
				});
			}
			return checkedData;
		},
		message: "此信息以存在"
    }
});



