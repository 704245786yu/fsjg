var g_role = new Object();//全局变量,角色
$(function() {
	//获取字典常量类型
	$("select[name='roleId'] > option").each(function () {
        var txt = $(this).text(); //获取单个text
        var val = $(this).val(); //获取单个value
        g_role[val] = txt;
    });
	new BsFormTableExtend().closeFormModal();// form模态框关闭事件，触发该事件时重置form
});

function search() {
	$('#dg').bootstrapTable('selectPage', 1);
}

// 查询框回车执行查询操作
$('#searchText').keydown(function(event) {
	if (event.keyCode == 13) {
		search();
	}
});

//用户角色
function roleFormatter(value,row,index){
	return g_role[value];
}

// 表单验证
$('#ff').bootstrapValidator({
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		userName : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					max : 10,
					message : '最多10个字符'
				}
			}
		},
		password : {
			message : '密码不合法',
			validators : {
				notEmpty : {
					message : '密码不能为空'
				},
				stringLength : {
					max : 10,
					message : '最多10个字符'
				}
			}
		},
		rePassword : {
			message : '密码不一致',
			validators : {
				notEmpty : {
					message : '确认密码不能为空'
				},
				stringLength : {
					max : 10,
					message : '最多10个字符'
				},
				identical: {
                    field: 'password',
                    message: '确认密码与原密码不一致'
                }
			}
		}
	}
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

// 新增
function add() {
	$('#ff').attr('action', 'user/saveUser');
}

// 修改
function modify(id) {
	new BsFormTableExtend().showModifyForm(id, 'user/updateUser');
}

// 删除
function del(index, id) {
	new BsFormTableExtend().delRecord(index, id, 'user/delete/');
}