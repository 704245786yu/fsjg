var g_jqConfirm = new JqConfirmExtend();
var g_delImg = new Array();

$(function(){
	var province = $('input[name="province"]').val();
	var city = $('input[name="city"]').val();
	var county = $('input[name="county"]').val();
	var town = $('input[name="town"]').val();
	if(province!='')
		fillDistrict(province, city, county, town);
	
	var $form = $('#ff');
	
	var costumeCodes = $.parseJSON($form.find('#costumeCode').val());
	checkCostumeByCodes(costumeCodes);//设置“选择产品类别”button的显示文字
	
	//填充行业分类
	var trade = $form.find('#trade').val();
	if( trade != null && trade != ''){
		trade = trade.split(',');
		for(var i=0; i<trade.length; i++){
			$(':checkbox[name="trade"][value="'+trade[i]+'"]').prop('checked','checked');
		}
	}
	//填充加工类型
	var processType = $form.find('#processType').val();
	if(processType != null && processType != ''){
		var processType = processType.split(',');
		for(var i=0; i<processType.length; i++){
			$(':checkbox[name="processType"][value="'+processType[i]+'"]').prop('checked','checked');
		}
	}
	//销售市场
	var saleMarket = $form.find('#saleMarket').val();
	if(saleMarket != null){
		$(':radio[name="saleMarket"][value="'+saleMarket+'"]').prop('checked','checked');
	}
	//显示图片
	var logo = $form.find('input[name="logo"]').val();
	if(logo!='' && logo!='default_logo.png'){
		var $div = $('input[name="logoImg"] ~ div').css('display','');
		$div.children('img').attr('src','uploadFile/enterprise/'+logo);
	}
	var licenseImg = $form.find('input[name="licenseImg"]').val();
	if(licenseImg !=null && licenseImg!=''){
		var $div = $('input[name="licensePic"] ~ div').css('display','');
		$div.children('img').attr('src','uploadFile/enterprise/'+licenseImg);
	}
	var enterpriseImg = $form.find('input[name="enterpriseImg"]').val();
	if(enterpriseImg!=null && enterpriseImg!=''){
		var imgs = enterpriseImg.split(',');
		var $div = $('input[name="enterprisePic"] ~ div');
		for(var i=0; i<imgs.length; i++){
			var $divTemp = $div.clone().css('display','');
			$divTemp.children('img').attr('src','uploadFile/enterprise/'+imgs[i]);
			$div.after($divTemp);
		}
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
    	'basicUser.userName': {
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
					delay:1000,
					message: '用户名已存在',
					url:'basicUser/isNameExist'
				}
    		}
    	},
    	enterpriseName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 30,
    				message: '最多30个字符'
    			},
    			remote : {
					trigger: 'keyup',
					delay:1000,
					message: '工厂名称已存在',
					url:'enterprise/isNameExist'
				}
    		}
    	},
    	linkman: {
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
    	'basicUser.telephone': {
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
					delay:1000,
					message: '手机号已存在',
					url:'basicUser/isTeleExist'
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
    	trade:{
    		validators: {
    			notEmpty: {
    				message: '必选'
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
    	saleMarket:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	staffNumber:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			},
    			integer:{
    				message:'必须为数字'
    			}
    		}
    	},
    	qq:{
    		validators: {
    			digits:{
    				message:'必须为数字'
    			}
    		}
    	},
    	highSpeedStaffNumber:{
    		validators: {
    			integer:{
    				message:'必须为数字'
    			}
    		}
    	},
    	otherStaffNumber:{
    		validators: {
    			integer:{
    				message:'必须为数字'
    			}
    		}
    	},
    	enterpriseAge:{
    		validators: {
    			integer:{
    				message:'必须为数字'
    			}
    		}
    	}
    }
}).on('success.form.bv', function(e) {
	if(!isCostumeCheck()){
		e.preventDefault();
		var $form = $(e.target);
		$form.find(':submit').removeAttr('disabled');
		g_jqConfirm.autoClose('请选择主营产品');
	}
});

var options = {  
   beforeSubmit: beforeSubmit,  //提交前的回调函数  
   success: success      //提交后的回调函数  
}

$('#ff').ajaxForm(options);

function beforeSubmit(formData, jqForm, options){
	for(var i=0; i<g_delImg.length; i++){
		formData.push({'name':'delImg','value':g_delImg[i]});
	}
}

function success(data){
	var action = $('#ff').attr('action');
	if(data.status==200){
		g_jqConfirm.autoClose('更新成功');
		$('#ff').bootstrapValidator('resetForm');
		var value = data.value;
		//更新用户基本信息
		updateBasicInfo(data.value);
		//显示图片
		var logo = value.logo;
		if(logo!='' && logo!='default_logo.png'){
			var $div = $('input[name="logoImg"] ~ div').css('display','');
			$div.children('img').attr('src','uploadFile/enterprise/'+logo);
		}
		var licenseImg = value.licenseImg;
		if(licenseImg !=null && licenseImg!=''){
			var $div = $('input[name="licensePic"] ~ div').css('display','');
			$div.children('img').attr('src','uploadFile/enterprise/'+licenseImg);
		}
		var enterpriseImg = value.enterpriseImg;
		if(enterpriseImg!=null && enterpriseImg!=''){
			var imgs = enterpriseImg.split(',');
			var $div = $('input[name="enterprisePic"] ~ div:first');
			$div.nextAll().remove();//移除展示的工厂图片
			for(var i=0; i<imgs.length; i++){
				var $divTemp = $div.clone().css('display','');
				$divTemp.children('img').attr('src','uploadFile/enterprise/'+imgs[i]);
				$div.after($divTemp);
			}
		}
		//清空删除的旧图片
		g_delImg = new Array();
		//清空上传表单域
		$('input[type="file"]').val('');
	}else if(data.status==500){
		g_jqConfirm.showDialog('保存失败',data.value);
	}else if(data.status==501){
		g_jqConfirm.showDialog('保存失败',data.value);
	}
}

function delImg(imgName){
	var $hidden = $(':hidden[name="'+imgName+'"]');
	g_delImg.push($hidden.val());
	$hidden.val('');
	$hidden.nextAll('div').css('display','none');
}

function delEnterpriseImg(btn){
	var $enterpriseImg = $(':hidden[name="enterpriseImg"]');
	var enterpriseImgs = $enterpriseImg.val().split(',');
	var path = "uploadFile/enterprise/";
	var $img = $(btn).parent('div').prev();
	var src = $img.attr('src')
	src = src.slice(path.length,src.length);
	g_delImg.push(src);
	var index = $.inArray(src,enterpriseImgs);
	enterpriseImgs.splice(index,1);
	$enterpriseImg.val(enterpriseImgs.join());
	$img.parent('div').css('display','none');
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

function enterpriseImgChange(file,maxSize){
	//image/jpeg image/png
	var imgStr = $(':hidden[name="enterpriseImg"]').val();
	var imgs = [];
	if(imgStr != '')
		imgs = imgStr.split(',');
	var files = file.files;
	//IE9以下无此属性
	if(files==null)
		return;
	if((files.length+imgs.length) > 6){
		g_jqConfirm.autoClose("最多选择6张图片");
		return;
	}
	for(var i=0; i<files.length; i++){
		var f = files[i];
		if(f.type!='image/jpeg' && f.type!='image/png'){
			g_jqConfirm.autoClose("请上传jpg或png图片");
			return;
		}else if(f.size > (maxSize*1000)){
			g_jqConfirm.autoClose("上传的图片大于"+maxSize);
			return;
		}
	}
}

//更新用户基本信息
function updateBasicInfo(data){
	var $table = $('#basic-info');
	$table.find('span[name="userName"]').html(data.basicUser.userName);
	$table.find('span[name="linkman"]').html(data.linkman);
	$table.find('span[name="email"]').html(data.email);
	$table.find('span[name="telephone"]').html(data.telephone);
	$table.find('span[name="enterpriseName"]').html(data.enterpriseName);
}