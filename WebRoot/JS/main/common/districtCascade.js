/**
 *@author 支煜
 *2016-07
 *省市级联下拉框
 */
(function(){
	initDistrictSelect('#province', null);
	$('#districtContainer select').each(function(i){
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
			//清空所有之后的选择框
			var $nextAll = $(this).parent().nextAll();
			for(var i=0; i<$nextAll.length; i++){
				$($nextAll[i]).children('select').empty();
			}
			initDistrictSelect(selectId, code);
		});
	});
	
	/**初始化城市下拉框信息*/
	function initDistrictSelect(selectId, pCode){
		$.get("district/getByParent",{'pCode':pCode},function(data){
			assembleSelect(selectId, data)
		});
	}

	/**装配下拉框*/
	function assembleSelect(selectId, data){
		var $district = $(selectId);
		$('<option>').text('--请选择--').val("").appendTo($district);
		for(var i=0; i<data.length; i++){
			$('<option>').text(data[i].districtName).val(data[i].districtCode).appendTo($district);
		}
	}
})();

function assembleSelect(selectId, data){
	var $district = $(selectId);
	$('<option>').text('--请选择--').val("").appendTo($district);
	for(var i=0; i<data.length; i++){
		$('<option>').text(data[i].districtName).val(data[i].districtCode).appendTo($district);
	}
}

function fillDistrict(province, city, county, town){
	$.get('district/getCascade',{'province':province, 'city':city, 'county':county},function(data){
		assembleSelect('#city', data.cityList);
		assembleSelect('#county', data.countyList);
		assembleSelect('#town', data.townList);
		$('#city').val(city);
		$('#county').val(county);
		$('#town').val(town);
	});
}