var g_costumeCategory = null;

$(function(){
	initAd();
	$('.carousel').carousel();
	$("#carousel-small-ad").slide({ mainCell:"ul",autoPlay:true,effect:"left", vis:5, scroll:2, autoPage:true, pnLoop:false });
	$("#bottom_carousel .picScroll").slide({ mainCell:"ul",autoPlay:true,effect:"left", vis:8, scroll:2, autoPage:true, pnLoop:false });
	mallCategory();
	initCostumeObj();
	initAffiche();
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
	
	//左侧大图
	var leftAdIndex = 0;
	//文字广告
	var textAdIndex = 0;
	//表格小广告
	var tableSmAdIndex = 0;
	for(var i=0;i<adPositions.length;i++){
		var ad = adPositions[i];
		if(ad.code=='home_top_ad'){
			var $a = $('#topAd');
			setAdhrefAndImg($a,ad);
		}
		if(ad.code=='home_left_big_ad'){
			var $a = $($('a.left_big_ad')[leftAdIndex]);
			leftAdIndex++;
			setAdhrefAndImg($a,ad);
		}
		if(ad.code=='home_text_ad'){
			var $a = $($('a.text_ad')[textAdIndex]);
			textAdIndex++;
			setAdhrefAndImg($a,ad);
		}
		//首页表格小广告
		if(ad.code=='home_table_sm_ad'){
			console.log(ad.code);
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
		}else if(ad.code='home_bottom_caro'){
			var $tempLi = $picScrollLi.clone();
			var $a = $bottomCaroLi.children('a');
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
		if(data.length > 4){
			var nt_example1 = $ul.newsTicker({
				row_height: 25,
				max_rows: 4,
				duration: 4000
			});
		}
	});
}

$('.mallCategory a,.cat-subcategory a,.costumeList table table a').click(function(e){
	e.preventDefault();
	var categoryName = $(this).html();
	for(var key in g_costumeCategory){
		console.log(g_costumeCategory.key);
		if(g_costumeCategory[key] == categoryName)
			location.href="enterprise/showList?costumeCode="+key+"&enterpriseKeyword="+g_costumeCategory[key]; 
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