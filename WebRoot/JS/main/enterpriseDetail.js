$(function(){
	$('#loginModal').modal('hide');
	//行业分类
	var trade = $(':hidden[name="trade"]').val();
	var tradeStr = comm_getTradeName(trade);
	$('#tradeText').html(tradeStr);
	//加工类型
	var processType = $(':hidden[name="processType"]').val();
	var processTypeStr = comm_getProcessTypeName(processType);
	$('#processTypeText').html(processTypeStr);
	
	var enterpriseImg = $(':hidden[name="enterpriseImg"]').val();
	var $imgUl = $('#imgUl');
	var $li = $('<li><img alt="工厂图片" bimg="uploadFile/enterprise/default_big.png" src="uploadFile/enterprise/default_big.png" onmousemove="preview(this);"></li>');
	if(enterpriseImg!=null && enterpriseImg!=''){
		var enterpriseImgAry = enterpriseImg.split(',');
		for(var i=0;i<enterpriseImgAry.length;i++){
			var $liTemp = $li.clone();
			var imgUrl = "uploadFile/enterprise/"+enterpriseImgAry[i];
			$liTemp.children('img').attr({"bimg":imgUrl, "src":imgUrl});
			$imgUl.append($liTemp);
		}
	}else{
		$imgUl.append($li);
	}
	
	//设置感兴趣的工厂的加工类型
	var $processTypes = $('#enterpriseList :hidden[name="processType"]');
	for(var i=0; i<$processTypes.length; i++){
		var $processType = $($processTypes[i]);
		var processTypeStr = comm_getProcessTypeName($processType.val());
		$processType.after(processTypeStr);
	}
});