$(function(){
	$('#loginModal').modal('hide');
	//加工类型
	var processType = $(':hidden[name="processType"]').val();
	var processTypeStr = comm_getProcessTypeName(processType);
	$('#processTypeText').html(processTypeStr);
	
	//销售市场
	var $saleMarket = $('#saleMarket');
	if($saleMarket.text()==0)
		$saleMarket.text('内销');
	else if($saleMarket.text()==1)
		$saleMarket.text('外销');
	
	var imgs = $(':hidden[name="imgs"]').val();
	var $imgUl = $('#imgUl');
	var $li = $('<li><img alt="样品图片" bimg="" src="" onmousemove="preview(this);"></li>');
	if(imgs!=null && imgs!=''){
		var imgAry = imgs.split(',');
		for(var i=0;i<imgAry.length;i++){
			var $liTemp = $li.clone();
			var imgUrl = "uploadFile/costumeSample/"+imgAry[i];
			$liTemp.children('img').attr({"bimg":imgUrl, "src":imgUrl});
			$imgUl.append($liTemp);
			//设置默认显示第一幅图
			if(i==0){
				$('.jqzoom img').attr({"bimg":imgUrl, "src":imgUrl});
			}
		}
	}else{
		//没有图片则显示默认图片
		$imgUl.append($li);
	}
	
	imgs = $(':hidden[name="detailImg"]').val();
	var $img = $('<img style="width:790px;height:530px;margin-bottom:20px;">');
	var $detailImgDiv = $('#detailImgDiv');
	if(imgs!=null && imgs!=''){
		var imgAry = imgs.split(',');
		for(var i=0;i<imgAry.length;i++){
			var $imgTemp = $img.clone();
			var imgUrl = "uploadFile/costumeSample/"+imgAry[i];
			$imgTemp.attr("src",imgUrl);
			$detailImgDiv.append($imgTemp);
		}
	}
});