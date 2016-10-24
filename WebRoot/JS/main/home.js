var g_costumeCategory = null;

$(function(){
	initAd();
	$('.carousel').carousel();
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

//广告
function initAd(){
	var adPositions = $.parseJSON($('#adPositions').html());
	var $carouselBigAd = $('#carousel-big-ad');//大轮播图
	var $carouselSampleLi = $('#carousel-big-ad sample li');
	var $carouselSampleItem = $('#carousel-big-ad sample div.item');
	for(var i=0;i<adPositions.length;i++){
		var ad = adPositions[i];
		if(ad=='home_big_caro'){
			var $tempLi = $carouselSampleLi.clone();
			var $tempItem = $carouselSampleItem.clone();
			var $a = $tempItem.children('a');
			if(ad.linkType==0)//外部链接
				$a.attr('href',ad.link);
			else if(ad.linkType==1)//工厂详情页
				$a.attr('href','enterprise/showDetail/'+ad.link);
			$a.children('img').attr('src',ad.img);
			$carouselBigAd.find('ol').append($carouselBigAd);
			$carouselBigAd.find('.carousel-inner').append($carouselBigAd);
		}
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
			$a.attr('href','affiche/showDetail/'+data[i][0]).html('[公告]'+data[i][1]);
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

$('.mallCategory a,.cat-subcategory a,.costumeList table a').click(function(e){
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