$(function(){
	$('.carousel').carousel();
	getCategory();
	mallCategory();
});

function getCategory(){
	var pCodes = [101,102,103,104,105,2,3,4,5,6];
//	var regExp = new RegExp("^"+pCode[1]+"..$");
//	console.log(regExp.test(10200));
	$.get('costumeCategory/getAll',function(data){
		for(var i=1; i<data.length; i++){
			var temp = data[i];
			var pCode = Math.floor(temp.categoryCode/100);
			var categoryDivId = null;
			switch (pCode) {
			case 101:
				categoryDivId = 101;break;
			case 102:
				categoryDivId = 102;break;
			case 103:
				categoryDivId = 103;break;
			case 104:
				categoryDivId = 104;break;
			case 105:
				categoryDivId = 105;break;
			case 2:
				categoryDivId = 2;break;
			case 3:
				categoryDivId = 3;break;
			case 4:
				categoryDivId = 4;break;
			case 5:
				categoryDivId = 5;break;
			case 6:
				categoryDivId = 6;break;
			}
			
			if(categoryDivId !=null){
				var $a = $('<a>').attr('href','enterprise/showList/'+temp.categoryCode).html(temp.categoryName);
				$('#category_'+categoryDivId).append($a);
			}
		}
	});
}

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