$(function () {
    renderComoboShowIssueState();
    renderComoShowProjectName();

    $("#addnewissue").click(function () {
        $("#formModifyIssueName").textbox({
            value: ""
        });
        //渲染缺陷描述
        $("#formModifyIssueDesc").textbox({
            value: ""
        });
        $("#formModifyDoneCondition").textbox({
            value: ""
        });
        $("#formModifyPlanWorkHours").textbox({
            value: ""
        });
        $("#formModifyPlanStartTime").datebox('setValue', "");
        $("#formModifyPlanEndTime").datebox('setValue', "");
        //渲染项目名称
        var data = renderComboShowProjectNameByTest(true);
        if(data == null){
            $.messager.alert('提示框', '不能添加缺陷');
            return;
        }
        //渲染缺陷名称下拉框
        // console.log(data);
        renderComboShowIssueNameByProjectId(data[0].project_ID, true);
        //渲染缺陷分类下拉框
        renderComboShowIssueType(true);
        //渲染严重程度下拉框
        renderComboShowIssueSeverity(true);
        //渲染优先级下拉框
        renderComboShowIssuePriority(true);
        $("#postInformation")[0].onclick = postAddNewInfo;
        $("#datainfoform").dialog("open");
    });

    //给项目名称改变缺陷名称事件
    $("#showProjectNameByTest").combobox({
        onSelect: function (record) {
            renderComboShowIssueNameByProjectId(record.project_ID, true);
        }
    });

    //给查询绑定查询事件
    $("#queryissue").click(querydata);

    //修改一条纪录
    $("#modifynewissue").click(modifyAData);

    //删除纪律
    $("#deletenewissue").click(deleteIssueInfoByIds);

    $("#showdata").datagrid({
        onSelect: function (index, row) {
            if (row.issueState != "002") {
                $("#showdata").datagrid("unselectRow", index);
            }
            $.ajax({
                url: "http://localhost:8080/training/iissulist/queryissuebyissueid.json",
                data: { issueId: row.issueId },
                async: false,
                dataType: 'json',
                success: function (res) {
                    if (res.result == null) {
                        $("#showdata").datagrid("unselectRow", index);
                    }
                }
            });
        }
    });
});

//加载项目下拉框
function renderComoShowProjectName() {
    $('#showProjectName').combobox({
        textField: "project_NAME",
        valueField: "project_ID",
        url: "http://localhost:8080/training/iissulist/queryownerproject.json?project_STATE=1",
        loadFilter: function (data) {
            data = data.rows || data;
            data.unshift({
                project_NAME: "全部",
                project_ID: '',
                selected: true
            });
            return data;
        }
    });
}

//加载状态下拉框
function renderComoboShowIssueState() {
    $('#showIssueState').combobox({
        textField: "stateDesc",
        valueField: "stateCode",
        url: "http://localhost:8080/training/state/query.json?page=1&rows=99999999&moduleState=1",
        loadFilter: function (data) {
            data = data.rows || data;
            data.unshift({
                stateDesc: "全部",
                stateCode: '',
                selected: true
            });
            return data;
        }
    });
}

//增加和修改窗口加载严重程度下拉框
function renderComboShowIssueSeverity(selectone) {
    var data = null;
    $.ajax({
        url: "http://localhost:8080/training/serverity/query.json?page=1&rows=99999999&moduleState=1",
        async: false,
        dataType: 'json',
        success: function (resData) {
            data = resData.rows || resData;
        }
    });
    if (selectone == true) {
        data[0].selected = true;
    }
    $('#showIssueSeverity').combobox({
        textField: "severityDesc",
        valueField: "severityCode",
        data: data
    });
    return data;
}

//增加和修改窗口加载类型下拉框
function renderComboShowIssueType(selectone) {
    var data = null;
    $.ajax({
        url: "http://localhost:8080/training/type/query.json?page=1&rows=99999999&moduleState=1",
        async: false,
        dataType: 'json',
        success: function (resData) {
            data = resData.rows || resData;
        }
    });
    if (selectone == true) {
        data[0].selected = true;
    }
    $('#showIssueType').combobox({
        textField: "typeDesc",
        valueField: "typeCode",
        data: data
    });
    return data;
}


//增加和修改窗口加载优先级下拉框
function renderComboShowIssuePriority(selectone) {
    var data = null;
    $.ajax({
        url: "http://localhost:8080/training/priority/query.json?page=1&rows=99999999&moduleState=1",
        async: false,
        dataType: 'json',
        success: function (resData) {
            data = resData.rows || resData;
        }
    });
    if (selectone == true) {
        data[0].selected = true;
    }
    $('#showIssuePriority').combobox({
        textField: "priorityDesc",
        valueField: "priorityCode",
        data: data
    });
    return data;
}

//增加或修改加载项目名称
function renderComboShowProjectNameByTest(selectone) {
    var data = null;
    $.ajax({
        url: "http://localhost:8080/training/iissulist/queryownerprojectandistest.json",
        async: false,
        dataType: 'json',
        success: function (resData) {
            data = resData.rows || resData;
        }
    });
    if(!data instanceof Array || data.length == 0){
        return null
    }
    if (selectone == true) {
        data[0].selected = true;
    }
    $('#showProjectNameByTest').combobox({
        textField: "project_NAME",
        valueField: "project_ID",
        data: data
    });
    return data;
}

//加载缺陷名称下拉框
function renderComboShowIssueNameByProjectId(projectId, selectone) {
    var data = null;
    //console.log(projectId);
    $.ajax({
        url: "http://localhost:8080/training/iissulist/queryissueidbyprojectid.json",
        async: false,
        dataType: 'json',
        data: { projectId: projectId },
        success: function (resData) {
            data = resData.rows || resData;
        }
    });
    data.unshift({
        issueId: "",
        issueName: "无"
    });
    if (selectone == true) {
        data[0].selected = true;
    }
    $('#queryIssueIdByProjectId').combobox({
        textField: "issueName",
        valueField: "issueId",
        data: data
    });
    return data;
}



//获取表单的数据
//$("#formForAddAndModify")[0]
function getAFormDataToObj(formelement) {
    var formdata = new FormData(formelement);
    var data = Object.create(null);
    //将表单的值封装到一个对象中
    formdata.forEach(function (a, b) {
        data[b] = a;
    });
    return data;
}



//添加一条的纪录
function postAddNewInfo() {
    var data = getAFormDataToObj($("#formForAddAndModify")[0]);
    //数据校验是否有字段为空
    for (var i in data) {
        if (i == "parentIssueId") {
            continue;
        }
        if (data[i].trim() == '') {
            return;
        }
    }
    //数据planWorkHours是否为数字
    if (!Number(data.planWorkHours)) {
        return;
    }
    //console.log(data);
    $.post("http://localhost:8080/training/iissulist/addoneissuebaseinfo.json", data,
        function (data) {
            if (data.result > 0) {
                $.messager.alert('提示框', '添加成功');
            } else {
                $.messager.alert('提示框', '添加失败');
            }
            $("#datainfoform").dialog("close");
        });
}



//查询纪录
function querydata() {
    var value1 = $("#showProjectName").combobox("getValues");
    var value2 = $("#showIssueState").combobox("getValues");
    var value3 = $("#createDateBefore").datetimebox("getValues");
    var value4 = $("#createDateAfter").datetimebox("getValues");
    var obj = {
        projectId: value1[0],
        issueState: value2[0],
        createTimeBegin: value3[0],
        createTimeEnd: value4[0]
    }
    $("#showdata").datagrid({
        url: "http://localhost:8080/training/iissulist/queryiissulist.json",
        queryParams: obj
    });
}


function formaterData(value, row, index) {
    return row.operation = "<a href='javascript:void(0)' style='color:blue;text-decoration: none;' onclick='showrowdata(event," + index + ")'>详细</a>";
}

function showrowdata(event, index) {
    window.event ? window.event.cancelBubble = true : event.stopPropagation();
    $("#moreInfoShow").dialog("open");
}


//修改一条纪律
function modifyAData() {
    var select = $("#showdata").datagrid('getSelections');
    if (select.length != 1) {
        $.messager.alert('提示框', '请确认选中了一项');
        return;
    }

    $.ajax({
        url: "http://localhost:8080/training/iissulist/queryissuebyissueid.json",
        data: { issueId: select[0].issueId },
        async: false,
        dataType: 'json',
        success: function (res) {
            if (res.result == null) {
                $.messager.alert('提示框', '这条数据不能修改');
                return;
            }
            //渲染项目名称
            var data = renderComboShowProjectNameByTest(false);
            for (var i = 0; i < data.length; i++) {
                if (data[i].project_ID == res.result.projectId) {
                    data[i].selected = true;
                    $('#showProjectNameByTest').combobox({
                        textField: "project_NAME",
                        valueField: "project_ID",
                        data: data
                    });
                    break;
                }
            }
            //渲染缺陷名称下拉框
            data = renderComboShowIssueNameByProjectId(res.result.projectId, false);
            for (var i = 0; i < data.length; i++) {
                if (data[i].issueId == res.result.issueId) {
                    data[i].selected = true;
                    $('#queryIssueIdByProjectId').combobox({
                        textField: "issueName",
                        valueField: "issueId",
                        data: data
                    });
                    break;
                }
            }
            //渲染缺陷名称
            $("#formModifyIssueName").textbox({
                value: res.result.issueName
            });
            //渲染缺陷描述
            $("#formModifyIssueDesc").textbox({
                value: res.result.issueDesc
            });
            $("#formModifyDoneCondition").textbox({
                value: res.result.doneCondition
            });
            //渲染缺陷分类下拉框
            data = renderComboShowIssueType(false);
            for (var i = 0; i < data.length; i++) {
                if (data[i].typeCode == res.result.issueType) {
                    data[i].selected = true;
                    $('#showIssueType').combobox({
                        textField: "typeDesc",
                        valueField: "typeCode",
                        data: data
                    });
                    break;
                }
            }
            //渲染严重程度下拉框
            data = renderComboShowIssueSeverity(false);
            for (var i = 0; i < data.length; i++) {
                if (data[i].severityCode == res.result.issueSeverity) {
                    data[i].selected = true;
                    $('#showIssueSeverity').combobox({
                        textField: "severityDesc",
                        valueField: "severityCode",
                        data: data
                    });
                    break;
                }
            }
            //渲染优先级下拉框
            data = renderComboShowIssuePriority(false);
            for (var i = 0; i < data.length; i++) {
                if (data[i].priorityCode == res.result.issuePriority) {
                    data[i].selected = true;
                    $('#showIssuePriority').combobox({
                        textField: "priorityDesc",
                        valueField: "priorityCode",
                        data: data
                    });
                    break;
                }
            }
            $("#formModifyPlanWorkHours").textbox({
                value: res.result.planWorkHours
            });

            $("#formModifyPlanStartTime").datebox('setValue', res.result.planStartTime);
            $("#formModifyPlanEndTime").datebox('setValue', res.result.planEndTime);
            $("#postInformation")[0].onclick = function () {
                postModifyInfoByIssueId(res.result.issueId);
            };
            $("#datainfoform").dialog("open");
        }
    });
}


//提交一条修改数据
function postModifyInfoByIssueId(issueId) {
    var data = getAFormDataToObj($("#formForAddAndModify")[0]);

    //数据校验是否有字段为空
    for (var i in data) {
        if (i == "parentIssueId") {
            continue;
        }
        if (data[i].trim() == '') {
            return;
        }
    }
    data.issueId = issueId;
    //数据planWorkHours是否为数字
    if (!Number(data.planWorkHours)) {
        return;
    }
    //console.log(data);
    $.post("http://localhost:8080/training/iissulist/postmodifyinfobyissueid.json", data,
        function (data) {
            if (data.result > 0) {
                $.messager.alert('提示框', '修改成功');
            } else {
                $.messager.alert('提示框', '修改失败');
            }
            $("#datainfoform").dialog("close");
        });
}


//删除数据
function deleteIssueInfoByIds() {
    var select = $("#showdata").datagrid('getSelections');
    if (select.length < 1) {
        $.messager.alert('提示框', '请至少选中一项');
        return;
    }

    var issueIds = [];
    for (var i = 0; i < select.length; i++) {
        if (select[i].issueState == "002") {
            issueIds.push(select[i].issueId);
        }
    }
    $.post("http://localhost:8080/training/iissulist/deleteissueinfobyids.json", { issueIds: issueIds },
        function (data) {
            if (data.result > 0 && data.result == issueIds.length) {
                $.messager.alert('提示框', '全部删除成功');
            } else if (data.result > 0 && data.result < issueIds.length) {
                $.messager.alert('提示框', '部分删除成功');
            } else {
                $.messager.alert('提示框', '删除失败');
            }
        });
}