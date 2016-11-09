$(function(){
	$('#loginModal').modal('hide');
	//目标价
	var expectPrice = Number($('#expectPrice').html());
	if(expectPrice==-1)
		$('#expectPrice').html('面谈');
	var $createTime = $('#createTime');
	$createTime.html($createTime.html().split(' ')[0]);
	var $preDeliveryDate = $('#preDeliveryDate');
	$preDeliveryDate.html($preDeliveryDate.html().split(' ')[0]);
	var $effectiveDate = $('#effectiveDate');
	var effectiveDate = $effectiveDate.html().split(' ')[0];
	var date = moment(effectiveDate);
	var days = date.diff(moment(),'days');
	days = days<0 ? 0 : days; 
	
	$effectiveDate.html(effectiveDate+'&nbsp;&nbsp;&nbsp;&nbsp;<strong style="color:red;font-size:16px;">仅剩'+days+'天</strong>');
	var $indentType = $('#indentType');
	var indentType = $indentType.html();
	if(indentType==1){
		$indentType.html('来图/来样加工');
	}else if(indentType==2){
		$indentType.html('看款下单');
	}
	
	var $photo = $('.photo');
	var photoAry = $(':hidden[name="photo"]').val().split(',');
	for(var i=0; i<photoAry.length; i++){
		if(photoAry[i]=='')
			break;
		var $temp = $photo.clone();
		$temp.css('display','inline');
		$temp.attr('src', $temp.attr('src')+photoAry[i]);
		$photo.after($temp);
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