/**
 *@author 支煜
 *2016-07
 *省市级联下拉框
 */
(function(){
	var districtAry = ['#province','#city','#county','#town'];
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
			var cleanIndex = null;//要级联清除下拉框内容的开始下标
			switch(districtLevel){
			case 'province':
				selectId = '#city';
				cleanIndex = 1;
				break;
			case 'city':
				selectId = '#county';
				cleanIndex = 2;
				break;
			case 'county':
				selectId = '#town';
				cleanIndex = 3;
				break;
			}
			//清空所有之后的选择框
			for(var i=cleanIndex; i<4; i++){
				$('#districtContainer '+districtAry[i]).empty();
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
	$district.empty();
	$('<option>').text('--请选择--').val("").appendTo($district);
	for(var i=0; i<data.length; i++){
		$('<option>').text(data[i].districtName).val(data[i].districtCode).appendTo($district);
	}
}

function fillDistrict(province, city, county, town){
	if(province==null)
		return;
	$.get('district/getCascade',{'province':province, 'city':city, 'county':county},function(data){
		assembleSelect('#city', data.cityList);
		assembleSelect('#county', data.countyList);
		assembleSelect('#town', data.townList);
		$('#province').val(province);
		$('#city').val(city);
		$('#county').val(county);
		$('#town').val(town);
	});
}