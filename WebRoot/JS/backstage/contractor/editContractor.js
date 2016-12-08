var g_jqConfirm = new JqConfirmExtend();

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	'basicUser.userName': {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 20,
    				message: '最多20个字符'
    			},remote : {
					trigger: 'keyup',
					delay:2000,
					message: '用户名已存在',
					url:'basicUser/isNameExist2',
					data:function(validator){
						return {id:$(':hidden[name="basicUser.id"]').val()};
					}
				}
    		}
    	},
    	realName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 30,
    				message: '最多30个字符'
    			}
    		}
    	},
    	'basicUser.telephone': {
    		threshold: 11,
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			regexp: {
                    regexp: /^1[3|4|5|7|8]\d{9}$/,
                    message: '手机号码格式不正确'
                },remote : {
					trigger: 'keyup',
					delay:2000,
					message: '手机号已存在',
					url:'basicUser/isTeleExist2',
					data:function(validator){
						return {id:$(':hidden[name="basicUser.id"]').val()};
					}
				}
    		}
    	},
    	province:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	city:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	county:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	detailAddr:{
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
    	processType:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	qq:{
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
		$("#ff").fill(data);
		var person = data.person;
		var contractor = data.contractor;
		var costumeCode = contractor.costumeCode;
		var codes = costumeCode.split(',');
		checkCostumeByCodes(codes);//设置“选择产品类别”button的显示文字
		fillDistrict(person.province, person.city, person.county, person.town);
		
		$('#ff').attr('action','contractor/updateData');
		showForm();
	});
	
//	$('input[name="basicUser.id"]').val(data.basicUser.id);
//	$('input[name="basicUser.userName"]').val(data.basicUser.userName);
//	$('input[name="basicUser.telephone"]').val(data.basicUser.telephone);
	//填充加工类型
//	var processType = data.processType;
//	if(processType != null && processType != ''){
//		var processType = processType.split(',');
//		for(var i=0; i<processType.length; i++){
//			$(':checkbox[name="processType"][value="'+processType[i]+'"]').prop('checked','checked');
//		}
//	}
//	//显示图片
//	if(data.logo!='' && data.logo!='default_logo.png'){
//		var $div = $('input[name="logoImg"] ~ div').css('display','');
//		$div.children('img').attr('src','uploadFile/enterprise/'+data.logo);
//	}
//	if(data.licenseImg !=null && data.licenseImg!=''){
//		var $div = $('input[name="licensePic"] ~ div').css('display','');
//		$div.children('img').attr('src','uploadFile/enterprise/'+data.licenseImg);
//	}
//	if(data.enterpriseImg!=null && data.enterpriseImg!=''){
//		var imgs = data.enterpriseImg.split(',');
//		var $div = $('input[name="enterprisePic"] ~ div');
//		for(var i=0; i<imgs.length; i++){
//			var $divTemp = $div.clone().css('display','');
//			$divTemp.children('img').attr('src','uploadFile/enterprise/'+imgs[i]);
//			$div.after($divTemp);
//		}
//	}
	//认证审核
//	if(data.auditState==1)
//		$('input[name="isAudit"]').eq(0).attr('checked',true);
//	else if(data.auditState==2)
//		$('input[name="isAudit"]').eq(1).attr('checked',true);
		
	
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
	$form.find('input[name="logoImg"] ~ div').css('display','none');//隐藏工厂logo
	$form.find('input[type="hidden"]').val('');
	$form.find('input[type="radio"]').attr('checked',false);
	$form.bootstrapValidator('resetForm', true);
	resetModal();//重置costumeCategoryModal
	$form[0].reset();
	$('#listPanel').show();
	$('#editPanel').hide();
}