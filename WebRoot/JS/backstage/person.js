var g_constantType = new Object();//全局变量,常量类型

$(function(){
	new BsFormTableExtend().closeFormModal();//form模态框关闭事件，触发该事件时重置form
	//获取字典常量类型
	$("select[name='constantTypeCode'] > option").each(function () {
        var txt = $(this).text(); //获取单个text
        var val = $(this).val(); //获取单个value
        g_constantType[val] = txt;
    });
});

function getQueryParams(params){
	params.pageSize = params.limit;
//	var searchText = $('#searchText').val().trim();
//	params.constantName = searchText;
	delete params.limit;
	delete params.order;
	return params;
}

function view(id){
	var row = $('#dg').bootstrapTable('getRowByUniqueId',id);
	var pAry = $('#viewModal p');
	for(var i=0; i<pAry.length; i++){
		var $p = $(pAry[i]);
		var name = $p.attr("name");
		console.log(name);
		$p.html(row[name]);
	}
	$('#viewModal').modal('show');//显示form模态框
}

//个人实名状态
var g_authenticationState = ['未认证','已认证'];
function authenFormatter(value,row,index){
	return g_authenticationState[value];
}

//审核状态
var g_auditState = ['待审核','未通过','已通过'];
function auditFormatter(value,row,index){
	return g_auditState[value];
}

//用户状态
var g_personState = ['正常','冻结'];
function personFormatter(value,row,index){
	return g_personState[value];
}

//根据常量名称搜索
function search(){
	$('#dg').bootstrapTable('selectPage',1);
}

//查询框回车执行查询操作
$('#searchText').keydown(function(event){
	if(event.keyCode == 13){
		search();
	}
});

//表单验证
$('#ff').bootstrapValidator({
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
});

//新增
function add(){
	$('#ff').attr('action','person/save');
}

//修改
function modify(id){
	new BsFormTableExtend().showModifyForm(id, 'person/update');
}

//删除
function del(index,id){
	new BsFormTableExtend().delRecord(index,id,'person/delete/');
}

$('#fileupload').fileupload({
	done: function (e, data) {	//上传请求成功完成后的回调处理方法
		if(data.result == 1){	//跳转到最后一页，以展示最新数据
			var opt = $('#dg').bootstrapTable('getOptions');
//		  		var pageSize = opt.pageSize;
			var pageNumber = opt.pageNumber;
			console.log(pageNumber);
//		  		var totalRows = opt.totalRows;
//		  		var tzPageNumber = Math.ceil(totalRows/pageSize);
			$('#dg').bootstrapTable('selectPage',pageNumber);
		}else{
			alert('上传文件失败');
		}
	}
});
