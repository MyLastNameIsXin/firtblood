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
			$("#addInputSeverityCode").textbox({value:null});
			$("#addInputSeverityDesc").textbox({value:null});
			$("#ad").dialog("open");
		});
		//给增加表单提交按钮绑定数据提交事件
		$("#adformsure").click(submitAddInfo);
		//给增加表单重置按钮清空增加窗口
		$("#adformrest").click(function(){
			$("#addInputSeverityCode").textbox({value:null});
			$("#addInputSeverityDesc").textbox({value:null});
		});
		//给删除按钮绑定删除事件
		$("#remove").click(function(){
			$.messager.confirm('确认框', '确认删除吗', function(r){
				if (r){
					var array = $("#dataShow").datagrid("getSelections");
					var arrays = [];
					if(array.length == 0){
						return;
					}
					for(var i = 0;i<array.length;i++){
						arrays.push(array[i].severityCode);
					}
					deleteInfoes(arrays);
			}});
		});
		//给修改窗口提交按钮绑定修改事件
		$("#formsure").click(submitModifiedInfo);
		//给修改重置按钮清空修改窗口
	$("#formrest").click(function(){
		$("#inputSeverityCode").textbox({
			value:preCode
		});
		$("#inputSeverityDesc").textbox({
		 value:resetValue
	 });
	});
});



//渲染table数据
function renderTable(){
    $("#dataShow").datagrid({
		url: 'http://localhost:8080/training/serverity/query.json',
		//给操作列添加相关操作选项
		loadFilter: function (data) {
			for (var i = 0; i < data.rows.length; i++) {
				if (data.rows[i].moduleState == "1") {
					data.rows[i].moduleState = "有效";
					data.rows[i].operation = 	'<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:red;"' +'>禁用</a>'
				} else {
					data.rows[i].moduleState = "无效";
					data.rows[i].operation = '<a href="javascript:void(0)" onclick="modifyState(this,event)" style="color:green;">启用</a>'
				}
			}
			return data;
		}
	});
}

//修改按钮绑定弹出修改窗口
function showModifyWindow(){
	var array = $("#dataShow").datagrid("getSelections");
	if(array.length!=1){
		$.messager.alert('提示框','请确认选择了一项','info');
		return;
	}
		 preCode = array[0].severityCode;
		 resetValue = array[0].severityDesc;
		 $("#inputSeverityCode").textbox({
			 value:array[0].severityCode
		 });
		 $("#inputSeverityDesc").textbox({
			value:array[0].severityDesc
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
	$.post('http://localhost:8080/training/serverity/modifymodulestate.json',{'severityCode':row.severityCode,'moduleState':moduleState},
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


//提交增加数据
function submitAddInfo(){
		var severityCode = 	$("#addInputSeverityCode").val();
		var severityDesc = $("#addInputSeverityDesc").val();
		if(severityCode.trim() == "" || severityDesc.trim() == ""){
			return;
		}
		if(checkedData==false){
			return;
		}
		checkedData=false;
		$.post('http://localhost:8080/training/serverity/addOneServerity.json',{severityCode:severityCode,severityDesc:severityDesc},
			function(data){
				if(data.result>0){
					$.messager.alert('提示框','添加成功','info');
					$('#dataShow').datagrid('appendRow',{
						severityCode: severityCode,
						severityDesc: severityDesc,
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
		$("#addInputSeverityCode").textbox({value:null});
		$("#addInputSeverityDesc").textbox({value:null});
}


//删除数据事件，array为一个数组对象的实例
function deleteInfoes(array){
		$.post('http://localhost:8080/training/serverity/delete.json',{'severityCodes':array},function(data){
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
	var severityCode=$("#inputSeverityCode").val();
	var severityDesc=$("#inputSeverityDesc").val();
	if(severityCode == null || severityDesc == null || severityCode.trim()=="" || severityDesc.trim() == ""){
		return;
	}
	if(checkedData==false){
		return;
	}
	var data = { severityCode:severityCode, severityDesc:severityDesc,preCode:preCode };
	$.post('http://localhost:8080/training/serverity/modify.json', data,
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

var checkedData = false;
//声明校验
$.extend($.fn.validatebox.defaults.rules, {
    existence:{
		validator: function(value,fn){
			$.ajax({
				url:"http://localhost:8080/training/serverity/checkedInfo.json",
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
			var data = null;
			if(value==preCode){
				checkedData = true;
			}else{
				$.ajax({
					url:"http://localhost:8080/training/serverity/checkedInfo.json",
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





