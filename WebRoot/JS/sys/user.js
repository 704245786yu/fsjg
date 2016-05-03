$(function() {
	new BsFormTableExtend().closeFormModal();// form模态框关闭事件，触发该事件时重置form
});

// 根据常量名称搜索
function search() {
	$('#dg').bootstrapTable('selectPage', 1);
}

// 查询框回车执行查询操作
$('#searchText').keydown(function(event) {
	if (event.keyCode == 13) {
		search();
	}
});

// 表单验证
$('#ff').bootstrapValidator({
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		passWord : {
			message : '密码不合法',
			validators : {
				notEmpty : {
					message : '密码不能为空'
				},
				stringLength : {
					max : 20,
					message : '最多20个字符'
				}
			}
		},
		rePassWord : {
			message : '密码不一致',
			validators : {
				notEmpty : {
					message : '确认密码不能为空'
				},
				stringLength : {
					max : 20,
					message : '最多20个字符'
				},
				identical: {
                    field: 'passWord',
                    message: '确认密码与原密码不一致'
                }
			}
		},
		
		oldPassWord : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					max : 20,
					message : '最多20个字符'
				},
				callback : {
					message : '您输入的原密码有误',
					callback : function(value, validator) {
						var res = false;
						if (value.length <= 20 && value.length >= 1) {
							$.ajax({
								url : "sysUser/checkOldPwd",
								type : 'post',
								dataType : 'json',
								async : false,
								data : {
									oldPassWord : value,
									userName : $("#userName").val()
								},
								success : function(data) {
					
									if (data == true) {
										res = true;	
									}
								}
							});
							
						} 
						else {
							res=false;
						}
						return res;
					}
				}
			}
		},
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
		}

	}
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

// 新增
function add() {
	document.getElementById('oldpwdDiv').style.display = "none";
	$('#ff').attr('action', 'user/save');
}

// 修改
function modify(id) {
	document.getElementById('oldpwdDiv').style.display = "";
	new BsFormTableExtend().showModifyForm(id, 'user/update');
}

// 删除
function del(index, id) {
	new BsFormTableExtend().delRecord(index, id, 'user/delete/');
}