//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	telephone: {
    		threshold: 11,
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },
                remote : {
					trigger: 'keyup',
					delay:2000,
					message: '手机号已存在',
					url:'basicUser/isTeleExist2',
					name:'telephone',
					data:function(validator){
						return {id:$(':hidden[name="person.basicUser.id"]').val()};
					}
				}
    		}
    	},
    }
}).on('success.form.bv', function(e) {
	e.preventDefault();
	var $form = $(e.target);
	//检查是否选择了主营产品
	if(!isCostumeCheck()){
		$form.find(':submit').removeAttr('disabled');
		alert('请选择主营产品');
		return;
	}
	$form.ajaxSubmit(function(data) {     
		var action = $form.attr('action');
		if(data.status==200){
			var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
			var value = data.value;
			var contractor = new Object();
			contractor.id = value.person.id;
			contractor.userName = value.person.basicUser.userName;
			contractor.realName = value.person.realName;
			contractor.telephone = value.person.basicUser.telephone;
			contractor.state = value.person.basicUser.state;
			contractor.processType = value.contractor.processType;
			contractor.auditState = value.person.auditState;
			contractor.createTime = value.person.createTime;
			if(opt.indexOf("save")!=-1){
				$('#dg').bootstrapTable('append',contractor);
			}else if(opt.indexOf("update")!=-1){	//update by unique id
				$('#dg').bootstrapTable('updateByUniqueId',{'id':contractor.id,'row':contractor});
			}
			cancel();
		}else if(data.status==500){
			g_jqConfirm.showDialog('保存失败',data.value);
		}else if(data.status==501){
			g_jqConfirm.showDialog('保存失败',data.value);
		}
	});
});

//显示Form表单，隐藏其他面板
function showForm(){
	$('#listPanel').hide();
	$('#editPanel').show();
}

//新增，该方法由主页面的add按钮触发
function add(){
	$('#ff').attr('action','contractor/saveData');
	showForm();
}