var g_auditState = {"1":"待审核","2":"未通过","3":"已通过"};//审核状态
var g_userState = {"0":"正常","1":"冻结"};	//用户状态
var g_jqConfirm = new JqConfirmExtend();
var g_total = null;

$(function(){
	$('input[name="beginDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
	$('input[name="endDate"]').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
	$('input[name="telephone"').mask('#');
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

$('#dg').bootstrapTable({
	onPageChange:function(number,size){
		g_total = $('#dg').bootstrapTable('getOptions').totalRows;
	}
});

function queryParams(params){
	var userName = $('input[name="userName"]').val();
	var telephone = $('input[name="telephone"]').val();
	var auditState = $('select[name="auditState"]').val();
	var beginDate = $('input[name="beginDate"]').val();
	var endDate = $('input[name="endDate"]').val();
	params.userName = userName;
	params.telephone = telephone;
	params.auditState = auditState;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	params.total = g_total;//g_total可能为null
	
	delete params.order;
	return params;
}

//查询
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}

//显示个人详细信息，隐藏其他面板
function view(id){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	var $viewPanel = $('#viewPanel');
	$viewPanel.find(':hidden[name="id"]').val(row.id);
	var districtCodes = [row.province, row.city, row.county, row.town];
	var pAry = $viewPanel.find('p');
	for(var i=0; i<pAry.length; i++){
		var $p = $(pAry[i]);
		var name = $p.attr("name");
		$p.html(row[name]);
	}
	$.get('district/getNameByCode',{"codes":districtCodes},function(data){
		var str = '';
		for(var i=0; i<data.length; i++){
			str += data[i];
		}
		str += row.detailAddr;
		$viewPanel.find('p[name="detailAddr"]').html(str);
	});
	$viewPanel.find('p[name="userName"]').html(row.basicUser.userName);
	$viewPanel.find('p[name="telephone"]').html(row.basicUser.telephone);
	var auditState = g_auditState[row.auditState];
	if(auditState == undefined)
		auditState = '无';
	$viewPanel.find('p[name="auditState"]').html(auditState);
	$viewPanel.find('p[name="state"]').html(g_userState[row.basicUser.state]);
	if(row.idFrontPhoto != null){
		$viewPanel.find('img[name="idFrontPhoto"]').attr('src',"uploadFile/person/"+row.idFrontPhoto);
		$viewPanel.find('img[name="idBackPhoto"]').attr('src',"uploadFile/person/"+row.idBackPhoto);
	}
	$('#listPanel').hide();
	$('#viewPanel').show();
}

function hideView(){
	$('#listPanel').show();
	$('#viewPanel').hide();
}

//审核状态
function auditFormatter(value,row,index){
	var auditState = g_auditState[row.auditState];
	if(auditState == undefined)
		auditState = '无';
	return auditState;
}

//用户状态
function stateFormatter(value,row,index){
	return g_userState[value];
}

function operFormatter(value,row,index){
	var viewBtn = "<button type='button' class='btn btn-default btn-xs' title='查看' onclick='view("+row.id+")'><span class='text-primary glyphicon glyphicon-eye-open'></span></button>";
	var stateBtn = '';
	if(row.basicUser.state == 1){
		stateBtn = " <button type='button' class='btn btn-default btn-xs' title='解冻' onclick='modifyState("+row.id+","+0+")'><span class='text-primary glyphicon glyphicon-ok-circle'></span></button>";
	}else{
		stateBtn = " <button type='button' class='btn btn-default btn-xs' title='冻结' onclick='modifyState("+row.id+","+1+")'><span class='text-primary glyphicon glyphicon-ban-circle'></span></button>";
	}
	var delBtn = " <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.id+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
	return viewBtn + stateBtn + delBtn;
}

//根据常量名称搜索
function search(){
	$('#dg').bootstrapTable('selectPage',1);
}

//审核处理
function audit(auditState){
	var id = $('#viewPanel :hidden[name="id"]').val();
	$.post('person/audit',{'id':id,'auditState':auditState},function(data){
		if(data.status==200){
			var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
			row.auditState = auditState;
			$('#dg').bootstrapTable('updateByUniqueId',{'id':row.id,'row':row});
			g_jqConfirm.autoClose('操作成功');
		}
	});
}

//用户状态更改：冻结 解冻
function modifyState(id, state){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	$.post('basicUser/modifyState',{'id':row.basicUser.id,'state':state},function(data){
		if(data.status==200){
			row.basicUser.state = state;
			$('#dg').bootstrapTable('updateByUniqueId',{'id':row.id,'row':row});
			g_jqConfirm.autoClose('操作成功');
		}
	});
}

//表单验证
/*$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	constantName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 10,
    				message: '最多10个字符'
    			}
    		}
    	},
    	constantTypeCode: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 20,
                    message: '请输入1到20个字符'
                },
                regexp: {
                    regexp: /^[a-zA-Z_]+$/,
                    message: '只能由字母和下划线组成'
                }
            }
        },
        constantValue: {
            validators: {
                notEmpty: {
                    message: '不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 8,
                    message: '请输入1到8个字符'
                },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '只能输入数字'
                }
            }
        },
        description: {
            validators: {
                stringLength: {
                    min: 1,
                    max: 30,
                    message: '请输入1到30个字符'
                }
            }
        }
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});*/

//新增
/*function add(){
	$('#ff').attr('action','person/save');
}*/

//修改
/*function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'person/update');
}*/

//删除
/*function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'person/delete/');
}*/