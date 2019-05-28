//用于修改时纪录主键
var preCode = "";
//用于重置修改表单
var resetValue ="";

$(function(){
		//渲染表单
		renderTable();
		//给增加按钮绑定弹出窗口事件
		$("#add").click(function(){
			//弹出增加窗口
			$("#addInputStateCode").textbox({value:null});
			$("#addInputStateDesc").textbox({value:null});
			$("#ad").dialog("open");
		});
		//给增加表单提交按钮绑定数据提交事件
		$("#adformsure").click(submitAddInfo);
		//给增加表单重置按钮清空增加窗口
		$("#adformrest").click(function(){
			$("#addInputStateCode").textbox({value:null});
			$("#addInputStateDesc").textbox({value:null});
		});
		//给删除按钮绑定删除事件
		$("#remove").click(function(){
			var array = $("#dataShow").datagrid("getSelections");
			var arrays = [];
			if(array.length == 0){
				return;
			}
			for(var i = 0;i<array.length;i++){
				arrays.push(array[i].stateCode);
			}
			deleteInfoes(arrays);
		});
		//给修改窗口提交按钮绑定修改事件
		$("#formsure").click(submitModifiedInfo);
		//给修改重置按钮清空修改窗口
	$("#formrest").click(function(){
		var form = document.querySelector("#modifyform");
		var input = form.getElementsByTagName("input");
	 	input[1].value=preCode;
		input[4].value=resetValue;
	});
});



//渲染table数据
function renderTable(){
    $("#dataShow").datagrid({
		url: 'http://localhost:8080/training/state/query.json',
		//给操作列添加相关操作选项
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
}


function showModifyWindow(){
	var array = $("#dataShow").datagrid("getSelections");
	if(array.length!=1){
		$.messager.alert('提示框','请确认选择了一项','info');
		return;
	}
		 preCode = array[0].stateCode;
		 resetValue = array[0].stateDesc;
		 $("#inputStateCode").textbox({
			 value:preCode
		 });
		 $("#inputStateDesc").textbox({
			value:resetValue
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
	$.post('http://localhost:8080/training/state/modifymodulestate.json',{'stateCode':row.stateCode,'moduleState':moduleState},
	function(data){
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

//操作列的删除按钮绑定的删除事件
function deleteAInfo(e) {
	var td = e.parentElement.parentElement.parentElement;
	var index = td.getAttribute("datagrid-row-index");
	var row = $("#dataShow").datagrid("getRows")[index];
	$.messager.confirm('确认框', '确认删除吗', function(r){
		if (r){
			deleteInfoes(Array.of(row.stateCode));
		}
	});
}

//提交增加数据
function submitAddInfo(){
		var stateCode = $("#addInputStateCode").val();
		var stateDesc = 	$("#addInputStateDesc").val();
		if(stateCode.trim() == "" || stateDesc == ""){
			return;
		}
		if(checkedData==false){
			return;
		}
		checkedData=false;
		$.post('http://localhost:8080/training/state/addOneInfo.json',{stateCode:stateCode,stateDesc:stateDesc},
		function(data){
			if(data.result>0){
				$.messager.alert('提示框','添加成功','info');
				$('#dataShow').datagrid('appendRow',{
					stateCode: stateCode,
					stateDesc: stateDesc,
					moduleState: '有效',
					operation:'<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +
					'>禁用</a>'
				});				
			}else{
				$.messager.alert('提示框','添加失败','info');
			}
		});
		//关闭增加窗口
		$("#ad").dialog("close");
		$("#addInputStateCode").textbox({value:null});
		$("#addInputStateDesc").textbox({value:null});
}


//删除数据事件，array为一个数组对象的实例
function deleteInfoes(array){
		$.post('http://localhost:8080/training/state/delete.json',{'stateCodes':array},function(data){
			if(data.result > 0){
				$.messager.alert('结果','成功删除','info');
				renderTable();
			}else{
				$.messager.alert('结果','删除失败','info');
			}
		},'json');	
}

//提交修改数据并关闭修改窗口和重新渲染表单
function submitModifiedInfo(){
	var stateCode=$("#inputStateCode").val();
	var stateDesc=$("#inputStateDesc").val();
	if(stateCode == null || stateDesc == null || stateCode.trim()=="" || stateDesc.trim() == ""){
		return;
	}
	if(checkedData==false){
		return;
	}
	var data = { stateCode:stateCode, stateDesc:stateDesc,preCode:preCode };
	$.post('http://localhost:8080/training/state/modify.json', data,
		function (data) {
			if(data.result > 0){
				$.messager.alert('结果','成功修改','info');
				renderTable();
			}else{
				$.messager.alert('结果','修改失败','info');
			}
		}, 'json'
	);
	$("#dd").dialog("close");
}


//声明校验
var checkedData = false;
$.extend($.fn.validatebox.defaults.rules, {
    existence:{
		validator: function(value,fn){
			$.ajax({
				url:"http://localhost:8080/training/state/checkedInfo.json",
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
			if(value==preCode){
				checkedData = true;
			}else{
				$.ajax({
					url:"http://localhost:8080/training/state/checkedInfo.json",
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



