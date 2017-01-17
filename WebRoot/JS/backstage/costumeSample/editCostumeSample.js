var g_jqConfirm = new JqConfirmExtend();
var g_delImg = new Array();

var g_enterpriseAry = null;
$('input[name="enterpriseName"]').typeahead({
	delay:1000,
	source:function(query, process){
		$(':hidden[name="enterpriseNum"]').val('');
		query = query.trim();
		if(query.length==0)
			return;
		$.get('enterprise/getNames',{'name':query},function(data){
			g_enterpriseAry = data;
			var ary = new Array(data.length);
			for(var i=0; i<data.length; i++){
				ary[i] = data[i].name;
			}
			process(ary);
		});
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
    	name: {
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
    	enterpriseName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			}
    		}
    	},
    	orderAmount:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			},
    			integer:{
    				message:'必须为整数'
    			}
    		}
    	},
    	price:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			},
    			numeric:{
    				message:'必须为数字'
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
    	support:{
    		validators: {
    			notEmpty: {
    				message: '必选'
    			}
    		}
    	},
    	processDesc:{
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
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
	
	//工厂编号
	var $enterpriseNum = $(':hidden[name="enterpriseNum"]');
	var enterpriseNum = $enterpriseNum.val();
	if(enterpriseNum==''){
		var enterpriseName = $('#editPanel input[name="enterpriseName"]').val();
		for(var i=0; i<g_enterpriseAry.length; i++){
			if(g_enterpriseAry[i].name == enterpriseName){
				enterpriseNum = g_enterpriseAry[i].num;
				break;
			}
		}
		if(enterpriseNum != ''){
			$enterpriseNum.val(enterpriseNum);
		}else{
			alert('请选择正确的工厂');
			return;
		}
	}
	$form.ajaxSubmit({
		beforeSubmit:function(formData, jqForm, options){
			//加入被删的图片
			for(var i=0; i<g_delImg.length; i++){
				formData.push({'name':'delImg','value':g_delImg[i]});
			}
			if(formData[7].value=="" && formData[8].value==""){ //smImg, smPic
				alert('未上传样品图片');
				return false;
			}
			if(formData[14].value=="" && formData[15].value==""){ //detailImg, detailPic
				alert('未上传详情图片');
				return false;
			}
		},
		success:function(data) {     
			var action = $form.attr('action');
			var enterpriseName = $('#editPanel input[name="enterpriseName"]').val();
			if(data.status==200){
				data.value.enterpriseName = enterpriseName;
				var opt = action.split('/')[1];	//根据url判断执行的是save还是update方法
				var costumeCate = getNameByCodes(data.value.costumeCode.toString());
				data.value.costumeCate = costumeCate;
				if(opt.indexOf("save")!=-1){
					$('#dg').bootstrapTable('append',data.value);
				}else if(opt.indexOf("update")!=-1){	//update by unique id
					$('#dg').bootstrapTable('updateByUniqueId',{'id':data.value.id,'row':data.value});
				}
				cancel();
			}else if(data.status==500){
				g_jqConfirm.showDialog('保存失败',data.value);
			}else if(data.status==501){
				g_jqConfirm.showDialog('保存失败',data.value);
			}
		}
	});
});

//显示Form表单，隐藏其他面板
function showForm(){
	g_delImg = new Array();
	$('#listPanel').hide();
	$('#editPanel').show();
}

//新增，该方法由主页面的add按钮触发
function add(){
	$('#ff').attr('action','costumeSample/saveData');
	showForm();
}

function modify(id){
	var data = $('#dg').bootstrapTable('getRowByUniqueId',id);
	$("#ff").autofill(data);
	var codes = [data.costumeCode];
	checkCostumeByCodes(codes);//设置“选择产品类别”button的显示文字
	
	//填售后保障
	var support = data.support;
	if( support != null && support != ''){
		support = support.split(',');
		for(var i=0; i<support.length; i++){
			$(':checkbox[name="support"][value="'+support[i]+'"]').prop('checked','checked');
		}
	}
	if(data.smImg!=null && data.smImg!=''){
		var imgs = data.smImg.split(',');
		var $div = $('input[name="smPic"] ~ div');
		for(var i=0; i<imgs.length; i++){
			var $divTemp = $div.clone().css('display','');
			$divTemp.children('img').attr('src','uploadFile/costumeSample/'+imgs[i]);
			$div.after($divTemp);
		}
	}
	if(data.detailImg!=null && data.detailImg!=''){
		var imgs = data.detailImg.split(',');
		var $div = $('input[name="detailPic"] ~ div');
		for(var i=0; i<imgs.length; i++){
			var $divTemp = $div.clone().css('display','');
			$divTemp.children('img').attr('src','uploadFile/costumeSample/'+imgs[i]);
			$div.after($divTemp);
		}
	}
	$('#ff').attr('action','costumeSample/updateData');
	showForm();
}

function delImg(btn,hidField){
	var $hidImgField = $(':hidden[name="'+hidField+'"]');
	var imgs = $hidImgField.val().split(',');
	var path = "uploadFile/costumeSample/";
	var $img = $(btn).parent('div').prev();
	var src = $img.attr('src')
	src = src.slice(path.length,src.length);
	g_delImg.push(src);
	var index = $.inArray(src,imgs);
	imgs.splice(index,1);
	$hidImgField.val(imgs.join());
	$img.parent('div').css('display','none');
}

function smImgChange(file,maxSize){
	//image/jpeg image/png
	var imgStr = $(':hidden[name="smImg"]').val();
	var imgs = [];
	if(imgStr != '')
		imgs = imgStr.split(',');
	var files = file.files;
	//IE9以下无此属性
	if(files==null)
		return;
	if((files.length+imgs.length) > 5){
		g_jqConfirm.autoClose("最多选择5张图片");
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

function detailImgChange(file,maxSize){
	//image/jpeg image/png
	var imgStr = $(':hidden[name="detailImg"]').val();
	var imgs = [];
	if(imgStr != '')
		imgs = imgStr.split(',');
	var files = file.files;
	//IE9以下无此属性
	if(files==null)
		return;
	if((files.length+imgs.length) > 10){
		g_jqConfirm.autoClose("最多选择10张图片");
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

/**取消编辑表单，同时重置表单
 * */
function cancel(){
	g_delImg = new Array();
	var $form = $('#ff');
	$form.find(':hidden[name="smImg"] ~ div:first').nextAll().remove();//移除图片
	$form.find(':hidden[name="detailImg"] ~ div:first').nextAll().remove();//移除图片
	$form.find('input[type="hidden"]').val('');
	$form.bootstrapValidator('resetForm', true);
	resetModal();//重置costumeCategoryModal
	$form[0].reset();
	$('#listPanel').show();
	$('#editPanel').hide();
}