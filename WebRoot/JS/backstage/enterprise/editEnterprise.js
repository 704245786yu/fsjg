$(function(){
	initDistrictSelect('#province', null);
	$('#districtDiv select').each(function(i){
		//镇/乡/街道无需出发该事件
		if(i==3)
			return false;

		$(this).change(function(){
			//未选择则返回
			var code = $(this).val();
			if(code == "")
				return;
			
			var districtLevel = $(this).attr('id');
			var selectId = null;
			switch(districtLevel){
			case 'province':
				selectId = '#city';
				break;
			case 'city':
				selectId = '#county';
				break;
			case 'county':
				selectId = '#town';
				break;
			}
			initDistrictSelect(selectId, code);
		});
	});
});

/**初始化城市下拉框信息*/
function initDistrictSelect(selectId, pCode){
	$.get("district/getByParent",{'pCode':pCode},function(data){
		var $district = $(selectId).empty();
		$('<option>').text('--请选择--').val("").appendTo($district);
		for(var i=0; i<data.length; i++){
			$('<option>').text(data[i].districtName).val(data[i].districtCode).appendTo($district);
		}
	});
}

//表单验证
$('#ff').bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	enterpriseName: {
    		validators: {
    			notEmpty: {
    				message: '不能为空'
    			},
    			stringLength: {
    				max: 30,
    				message: '最多30个字符'
    			}
    		}
    	}
    }
}).on('success.form.bv', function(e) {
	new BsFormTableExtend().submitFunc(e);
});

//显示Form表单，隐藏其他面板
function showForm(){
	$('#listPanel').hide();
	$('#editPanel').show();
}

//新增，该方法由主页面的add按钮触发
function add(){
	$('#ff').attr('action','enterprise/save');
	showForm();
}

//新增，该方法由主页面的add按钮触发
function modify(id){
	var data = $('#dg').bootstrapTable('getRowByUniqueId',id);
	$("#ff").autofill(data);
	$('#ff').attr('action','enterprise/update');
	showForm();
}

/**取消编辑表单，同时重置表单
 * */
function cancel(){
	$('#listPanel').show();
	$('#editPanel').hide();
	var $form = $('#ff');
	$form.bootstrapValidator('resetForm', true);
	$form[0].reset();
}