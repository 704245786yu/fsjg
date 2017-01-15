var g_costumeCategory = null;

$(function(){
	initAd();
	$('.carousel').carousel();
	$(".slideBox").slide({mainCell:".bd ul",autoPlay:true});
	$("#carousel-small-ad").slide({ mainCell:"ul",autoPlay:true,effect:"leftMarquee", vis:5, interTime:50, pnLoop:false });
	$("#bottom_carousel .picScroll").slide({ mainCell:"ul",autoPlay:true,effect:"leftMarquee", vis:8, interTime:50, pnLoop:false });
	mallCategory();
	initCostumeObj();
	initAffiche();
	initTradeNews();
});

function initCostumeObj(){
	var str = $('#tradeAndCostumeMap').html();
	var g_tradeAndCostumeMap = $.parseJSON(str);
	g_costumeCategory = {};
	for(var i=0; i<g_tradeAndCostumeMap.length; i++){
		$.extend(g_costumeCategory,g_tradeAndCostumeMap[i].children);
	}
}

//设置链接地址和图片，供initAdd()方法调用
function setAdhrefAndImg($a,ad){
	if(ad.linkType==0)//外部链接
		$a.attr('href','http://'+ad.link);
	else if(ad.linkType==1)//工厂详情页
		$a.attr('href','enterprise/showDetail/'+ad.link);
	
	$a.children('img').attr('src','uploadFile/ad/'+ad.img);
}

function setAdHref($a,ad){
	$a.html(ad.title);
	if(ad.linkType==0)//外部链接
		$a.attr('href','http://'+ad.link);
	else if(ad.linkType==1)//工厂详情页
		$a.attr('href','enterprise/showDetail/'+ad.link);
}

function initAd(){
	var adPositions = $.parseJSON($('#adPositions').html());
	
	//顶部幻灯片
	var $topAdUl = $('#topAd .bd ul');
	var $topLi = $('#topAd .bd div li');
	
	//大轮播图
	var $carouselBigAd = $('#carousel-big-ad');
	var $carouselSampleLi = $('#carousel-big-ad div[name="sample"] li');
	var $carouselSampleItem = $('#carousel-big-ad div[name="sample"] div.item');
	
	//顶部小轮播图
	var $picScrollUl = $('#carousel-small-ad ul');
	var $picScrollLi = $('#carousel-small-ad div[name="sample"] li');
	
	//底部轮播图
	var $bottomCaroUl = $('#bottom_carousel ul');
	var $bottomCaroLi = $('#bottom_carousel div[name="sample"] li');
	
	//底部幻灯片1
	var $bottomAdUl1 = $('#bottomSlide1 .bd ul');
	var $bottomLi1 = $('#bottomSlide1 .bd div li');
	
	//底部幻灯片2
	var $bottomAdUl2 = $('#bottomSlide2 .bd ul');
	var $bottomLi2 = $('#bottomSlide2 .bd div li');
	
	//左侧大图
	var leftAdIndex = 0;
	//文字广告
	var textAdIndex = 0;
	//表格小广告
	var tableSmAdIndex = 0;
	for(var i=0;i<adPositions.length;i++){
		var ad = adPositions[i];
		//顶部轮切广告
		if(ad.code=='home_top_ad'){
			var $tempLi = $topLi.clone();
			var $a = $tempLi.children('a');
			setAdhrefAndImg($a,ad);
			$topAdUl.append($tempLi);
		}
		if(ad.code=='home_left_big_ad'){
			var $a = $($('a.left_big_ad')[leftAdIndex]);
			leftAdIndex++;
			setAdhrefAndImg($a,ad);
		}
		if(ad.code=='home_text_ad'){
			var $a = $($('a.text_ad')[textAdIndex]);
			textAdIndex++;
			setAdHref($a,ad);
		}
		//底部幻灯片1
		if(ad.code=='home_bottom_slide1'){
			var $tempLi = $bottomLi1.clone();
			var $a = $tempLi.children('a');
			setAdhrefAndImg($a,ad);
			$bottomAdUl1.append($tempLi);
		}
		//底部幻灯片2
		if(ad.code=='home_bottom_slide2'){
			var $tempLi = $bottomLi2.clone();
			var $a = $tempLi.children('a');
			setAdhrefAndImg($a,ad);
			$bottomAdUl2.append($tempLi);
		}
		//首页表格小广告
		if(ad.code=='home_table_sm_ad'){
			var $a = $($('a.table_sm_ad')[tableSmAdIndex]);
			tableSmAdIndex++;
			setAdhrefAndImg($a,ad);
		}
		//大轮播图
		if(ad.code=='home_big_caro'){
			var $tempLi = $carouselSampleLi.clone();
			var $tempItem = $carouselSampleItem.clone();
			var $a = $tempItem.children('a');
			setAdhrefAndImg($a,ad);
			$carouselBigAd.find('ol').append($tempLi);
			$carouselBigAd.find('.carousel-inner').append($tempItem);
		}else if(ad.code=='home_top_sm_caro'){
			var $tempLi = $picScrollLi.clone();
			var $a = $tempLi.children('a');
			setAdhrefAndImg($a,ad);
			$picScrollUl.append($tempLi);
		}else if(ad.code=='home_right_top_1'){
			var $a = $($('a[name="right_top_ad"]')[0]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_right_top_2'){
			var $a = $($('a[name="right_top_ad"]')[1]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_mid_1'){
			var $a = $($('a.mid_ad')[0]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_mid_2'){
			var $a = $($('a.mid_ad')[1]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_mid_3'){
			var $a = $($('a.mid_ad')[2]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_mid_4'){
			var $a = $($('a.mid_ad')[3]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_mid_5'){
			var $a = $($('a.mid_ad')[4]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_mid_6'){
			var $a = $($('a.mid_ad')[5]);
			setAdhrefAndImg($a,ad);
		}else if(ad.code=='home_bottom_caro'){
			var $tempLi = $bottomCaroLi.clone();
			var $a = $tempLi.children('a');
			setAdhrefAndImg($a,ad);
			$bottomCaroUl.append($tempLi);
		}
	}
	var $bigAdLis = $carouselBigAd.find('ol li');
	var $bigAdDivs = $carouselBigAd.find('div.carousel-inner .item');
	$($bigAdLis[0]).attr({'data-slide-to':0,'class':'active'});
	$($bigAdDivs[0]).addClass('active');
	for(var i=1;i<adPositions.length;i++){
		$($bigAdLis[i]).attr('data-slide-to',i);
	}
	
	
}

//公告
function initAffiche(){
	$.get('affiche/getAllTitle',function(data){
		var $ul = $('ul.affiche');
		var $li = $('#afficheDemo li');
		for(var i=0;i<data.length;i++){
			var $temp = $li.clone();
			var $a = $temp.children('a');
			$a.attr('href','affiche/showDetail/'+data[i][0]).html(data[i][1]);
			$ul.append($temp);
		}
		if(data.length > 3){
			var nt_example1 = $ul.newsTicker({
				row_height: 25,
				max_rows: 3,
				duration: 4000
			});
		}
	});
}

//行业咨询
function initTradeNews(){
	var $imgs = $('#tradeNews .media-left > img');
	var $spans = $('#tradeNews .media-body > span');
	for(var i=0; i<$spans.length; i++){
		var $span = $spans.eq(i);
		var str = $span.html();
		var re = /<img[^>]+>/g;  
		var imgs = str.match(re);//截取图片
		if(imgs!=null){
			var src = $(imgs[0]).attr('src');
			$imgs.eq(i).attr('src',src);
		}
		str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
		str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
		//str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
		str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
		str=str.replace(/\s/g,''); //将空格去掉
		$span.html(str.substr(0,100));
	}
}

$('.mallCategory a,.cat-subcategory a,.costumeList table table a').click(function(e){
	e.preventDefault();
	var categoryName = $(this).html();
	for(var key in g_costumeCategory){
		if(g_costumeCategory[key] == categoryName)
			window.open("enterprise/showList?costumeCode="+key+"&enterpriseKeyword="+g_costumeCategory[key]); 
	}
});

function mallCategory(){
	$( ".category-search li" ).each( function( index ){
		$( this ).mouseover( function(){
			var top = $('.category-search').offset().top;
			var borderTop = $( this ).offset().top - 3;
			
			$( ".cat-subcategory" ).show();
			$( ".cat-subcategory div" ).hide().eq( index ).show();
			$( ".cat-subcategory" ).css( "top", borderTop - top );
		});

		$( ".mallCategory" ).mouseleave( function(){
			$( ".cat-subcategory" ).hide();
		});

	});
}