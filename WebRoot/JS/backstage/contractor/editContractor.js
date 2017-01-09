var g_jqConfirm = new JqConfirmExtend();

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	'person.basicUser.userName': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 20,
    				message: '最多20个字符'
    			},
    			remote : {
					trigger: 'keyup',
					delay:2000,
					message: '用户名已存在',
					url:'basicUser/isNameExist2',
					name:'userName',
					data:function(validator){
						return {id:$(':hidden[name="person.basicUser.id"]').val()};
					}
				}
    		}
    	},
    	'person.realName': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 5,
    				message: '最多5个字符'
    			}
    		}
    	},
    	'person.basicUser.telephone': {
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
    	'person.province':{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	'person.city':{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	'person.county':{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	'person.detailAddr':{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 50,
    				message: '最多50个字符'
    			}
    		}
    	},
    	'contractor.processType':{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	'person.qq':{
    		validators: {
    			digits:{
    				message:'必须为数字'
    			},
    			stringLength: {
    				min:5,
    				max: 10,
    				message: '5~10个字符'
    			}
    		}
    	},
    	'contractor.processYear':{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			},
    			digits:{
    				message:'必须为数字'
    			},
    			stringLength: {
    				max: 3,
    				message: '最多3个数字'
    			}
    		}
    	},
    	'contractor.workerAmount':{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			},
    			digits:{
    				message:'必须为数字'
    			},
    			stringLength: {
    				max: 5,
    				message: '最多5个数字'
    			}
    		}
    	},
    	'contractor.quote':{
    		validators: {
    			stringLength: {
    				max: 30,
    				message: '最多30个字符'
    			}
    		}
    	},
    	'contractor.skill':{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 20,
    				message: '最多20个字符'
    			}
    		}
    	},
    	'contractor.equipment':{
    		validators: {
    			stringLength: {
    				max: 100,
    				message: '最多100个字符'
    			}
    		}
    	},
    	'contractor.processDesc':{
    		validators: {
    			stringLength: {
    				max: 100,
    				message: '最多100个字符'
    			}
    		}
    	}
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

function modify(id){
	$.get('contractor/getById/'+id,function(data){
		var person = data.person;
		var contractor = data.contractor;
		//填充加工类型
		var processType = contractor.processType;
		processType = processType.split(',');
		contractor.processType = processType;
		$("#ff").fill(person);
		$("#ff").fill(contractor);
		//服饰类型
		var costumeCode = contractor.costumeCode;
		var codes = costumeCode.split(',');
		checkCostumeByCodes(codes);//设置“选择产品类别”button的显示文字
		fillDistrict(person.province, person.city, person.county, person.town);
		//显示图片
		var idFrontPhoto = person.idFrontPhoto;
		var idBackPhoto = person.idBackPhoto;
		if(idFrontPhoto!=null && idFrontPhoto!=''){
			var $div = $('input[name="person.idFrontPhoto"] ~ div').css('display','');
			$div.children('img').attr('src','uploadFile/person/'+idFrontPhoto);
		}
		if(idBackPhoto!=null && idBackPhoto!=''){
			var $div = $('input[name="person.idBackPhoto"] ~ div').css('display','');
			$div.children('img').attr('src','uploadFile/person/'+idBackPhoto);
		}
		
		$('#ff').attr('action','contractor/updateData');
		showForm();
	});	
}

//上传文件验证,不兼容IE9及以下浏览器
function imgChange(file,maxSize){
	//image/jpeg image/png
	var files = file.files;
	//IE9以下无此属性
	if(files==null)
		return;
	var f = files[0];
	if(f.type!='image/jpeg' && f.type!='image/png'){
		g_jqConfirm.autoClose("请上传jpg或png图片");
		return;
	}else if(f.size > (maxSize*1000)){
		g_jqConfirm.autoClose("上传的图片大于"+maxSize);
		return;
	}
}


/**取消编辑表单，同时重置表单
 * */
function cancel(){
	var $form = $('#ff');
	$form.find('input[type="file"] ~ div').css('display','none');//隐藏工厂logo
	$form.find('input[type="hidden"]').val('');
	$form.find('input[type="radio"]').attr('checked',false);
	$form.bootstrapValidator('resetForm', true);
	resetModal();//重置costumeCategoryModal
	$form[0].reset();
	$('#listPanel').show();
	$('#editPanel').hide();
}