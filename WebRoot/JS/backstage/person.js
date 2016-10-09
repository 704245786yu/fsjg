var g_auditState = {"1":"待审核","2":"未通过","3":"已通过"};//审核状态
var g_userState = {"0":"正常","1":"冻结"};	//用户状态

$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
});

function getQueryParams(params){
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
	delete params.order;
	return params;
}

//显示个人详细信息，隐藏其他面板
function view(id){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	var $viewPanel = $('#viewPanel');
	var pAry = $viewPanel.find('p');
	for(var i=0; i<pAry.length; i++){
		var $p = $(pAry[i]);
		var name = $p.attr("name");
		$p.html(row[name]);
	}
	$viewPanel.find('p[name="userName"]').html(row.basicUser.userName);
	$viewPanel.find('p[name="telephone"]').html(row.basicUser.telephone);
	var auditState = g_auditState[row.auditState];
	if(auditState == undefined)
		auditState = '无';
	$viewPanel.find('p[name="auditState"]').html(auditState);
	$viewPanel.find('p[name="state"]').html(g_userState[row.basicUser.state]);
	$viewPanel.find('img[name="idFrontPhoto"]').attr('src',"uploadFile/person/"+row.idFrontPhoto);
	$viewPanel.find('img[name="idBackPhoto"]').attr('src',"uploadFile/person/"+row.idBackPhoto);
	$('#listPanel').hide();
	$('#viewPanel').show();
}

function hideView(){
	$('#listPanel').show();
	$('#viewPanel').hide();
}

//个人实名状态
var g_authenticationState = ['未认证','已认证'];
function authenFormatter(value,row,index){
	return g_authenticationState[value];
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

//根据常量名称搜索
function search(){
	$('#dg').bootstrapTable('selectPage',1);
}

//审核处理
function audit(auditState){
	
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