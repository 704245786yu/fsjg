var g_role = new Object();//角色

$(function(){
	//获取角色
	$("select[name='roleId'] > option").each(function () {
        var txt = $(this).text(); //获取单个text
        var val = $(this).val(); //获取单个value
        g_role[val] = txt;
    });
	$('#meter').entropizer({
		target:'#password',
		engine: {
	        classes: ['lowercase', 'uppercase', 'numeric','symbols','symbolsCommon']
	    },
	    buckets: [
	          { max: 45, strength: '弱', color: '#e13' },
	          { min: 45, max: 60, strength: '中', color: '#f80' },
	          { min: 60, max: 75, strength: '强', color: '#8c0' },
	          { min: 75, strength: '极强', color: '#0c8' }
	    ],
		update: function(data, ui) {
	        ui.bar.css({
	            'background-color': data.color,
	            'width': data.percent + '%'
	        });
	        ui.text.html(data.strength);
	    }
	});
	new BsFormTableExtend().closeFormModal(undefined,resetForm);// form模态框关闭事件，触发该事件时重置form
	new BsFormTableExtend().closeFormModal('pwdformModal',resetMeter);
});

function resetForm(){
	$('.selectpicker').selectpicker("deselectAll");//对使用了bootstarp-select的表单执行取消选择
}

function resetMeter(){
	$('.entropizer-bar').css('width','0%');
	$('.entropizer-text').html('弱');
}

function getQueryParams(params) {
	var searchText = $('#searchText').val().trim();
	params.userName = searchText;
	delete params.order;
	return params;
}
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

//格式化角色信息
function roleFormatter(value,row,index){
	return g_role[value];
}

function operFormatter(value,row,index){
	var modifyBtn = " <button type='button' class='btn btn-default btn-xs' title='修改' onclick='modify("+row.id+")'><span class='text-primary glyphicon glyphicon-edit'></span></button>";
	var modifyPwdBtn = " <button type='button' class='btn btn-default btn-xs' title='重设密码' onclick='showPwdFormModal("+row.id+")'><span class='text-primary fa fa-key'></span></button>";
	var delBtn = " <button type='button' class='btn btn-default btn-xs' title='删除' onclick='del("+index+","+row.id+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
	return modifyBtn + modifyPwdBtn + delBtn;
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
		}
	}
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

$('#modifyPwdForm').bootstrapValidator({
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		oldPassword : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '字符个数6~20'
				},
				remote : {
					trigger: 'keyup',
					delay:1000,
					message: '您输入的原密码有误',
					url:'user/checkPwd',
					data: function(validator) {
						return {userId: $('#modifyPwdForm > input[name="id"]').val()};
					}
				}
			}
		},
		password : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				stringLength : {
					min : 6,
					max : 20,
					message : '字符个数6~20'
				}
			}
		},
		rePassword : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				identical : {
					field : 'password',
					message : '确认密码与原密码不一致'
				}
			}
		}
	}
}).on('success.form.bv', function(e) {
	e.preventDefault();
	var $form = $(e.target);
    $.post($form.attr('action'), $form.serialize(), function(data) {
    	if(data == 'success')
    		$('#pwdformModal').modal('hide');
    });
});

// 新增
function add() {
	$('#ff').attr('action', 'user/save');
}

// 修改
function modify(id) {
	new BsFormTableExtend().showModifyForm(id, 'user/update');
}

//显示密码修改模态框
function showPwdFormModal(id) {
	$('#modifyPwdForm').autofill( {'id':id},{restrict:true} );
	$('#pwdformModal').modal('show');
}

// 删除
function del(index, id) {
	new BsFormTableExtend().delRecord(index, id, 'user/delete/');
}