$(function(){
	$('#loginModal').modal('hide');
	var $createTime = $('#createTime');
	$createTime.html($createTime.html().split(' ')[0]);
	var $preDeliveryDate = $('#preDeliveryDate');
	$preDeliveryDate.html($preDeliveryDate.html().split(' ')[0]);
	var $effectiveDate = $('#effectiveDate');
	$effectiveDate.html($effectiveDate.html().split(' ')[0]);
	var $indentType = $('#indentType');
	var indentType = $indentType.html();
	if(indentType==1){
		$indentType.html('来图/来样加工');
	}else if(indentType==2){
		$indentType.html('看款下单');
	}
})

function quote(){
	var quote = Number($('#quote').val());
	if(isNaN(quote) || quote<=0)
		new JqConfirmExtend().autoClose('请输入正确的报价');
	var quoteUrl = $('#quoteUrl').val();
	$.post(quoteUrl+quote,function(data){
		var status = data.status;
		if(status == 500){
			var $confirm = new JqConfirmExtend();
			var value = data.value;
			if(value == 'nologin')
				$confirm.autoClose('请先登录');
			else if(value == 'noEnterprise')
				$confirm.autoClose('您不是企业用户无法报价');
			else if(value == 'quoted')
				$confirm.autoClose('您已报价，无需重复报价');
		}else if(status == 200){
			$('#quoteP').remove();
			$('#quoteSuc').css('display','block');
		}
	});
}