var g_entNum = null;
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
	
	//支持类型
	var support = $(':hidden[name="support"]').val();
	var supportAry = support.split(',');
	var $tds = $('#supportTable td');
	for(var i=0;i<supportAry.length;i++){
		$tds.eq(supportAry[i]).css('display','');
	}
	$('#supportTable td')
	
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
	var $img = $('<img style="width:750px;margin-bottom:20px;">');
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
	g_entNum = $(':hidden[name="entNum"]').val();
	initCostumeCate();
});

//产品类别树
function initCostumeCate(){
	var costumeCateMap = $.parseJSON($('#costumeCateMap').text());
	//产品类别列表
	var $costumeCateUl = $('.list-group');
	var $costumeCateLi = $('#costumeCateLi .list-group-item'); 
	//产生二级类目
	$.each(costumeCateMap,function(key,value){
		if(key<10000){
			var $tempList = $costumeCateLi.clone();
			//设置列表的id，方便下面生成三级类目
			$tempList.attr('id','code'+key);
			$tempList.children('a[name="secCate"]').attr({'href':'costumeSample/showEntSample/'+g_entNum+'/'+key,'target':'_blank'}).text(value);
			$costumeCateUl.append($tempList);
		}
	});
	//产生三级类目
	$.each(costumeCateMap,function(key,value){
		if(key>10000){
			var pCode = (key / 100).toFixed();
			var $costumeCateLi = $('#code'+pCode);
			var $div = $costumeCateLi.children('div')
			$div.append('<div><a target="_blank" href="costumeSample/showEntSample/'+g_entNum+'/'+key+'">'+value+'</a></div>');
		}
	});
	
	//产品类别+/-号点击样式切换，以及二级类目的显示与隐藏
	$('.faSquare').click(function(){
		$(this).children('i').toggleClass('fa-minus-square-o');
		$(this).children('i').toggleClass('fa-plus-square-o');
		$(this).nextAll('div').toggle();
		return false;
	});
}