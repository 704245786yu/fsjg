var g_processType = {1:'清加工',2:'经销',3:'自营出口',4:'其他'};

$(function(){
	getExcellent();
	getNewest();
	getNewAuth();
});

//获取优秀工厂(接单工厂)
function getExcellent(){
	var $enterpriseList = $('#enterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getExcellent',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading').text(enterprise.enterpriseName);
			var workNumber = null;
			if(enterprise.minimumStaffAmount == enterprise.maximumStaffAmount)
				workNumber = enterprise.minimumStaffAmount;
			else
				workNumber = enterprise.minimumStaffAmount + '~' + enterprise.maximumStaffAmount;
			var $list1 = $head.next().text('员工人数：'+workNumber+'人');
			var $list2 = $list1.next().text('加工类型：'+g_processType[enterprise.processType]);
			$list2.next().text('主营产品：');
			$enterpriseList.append($new);
		}
	});
}

//最新入住的企业
function getNewest(){
	var $enterpriseList = $('#newEnterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getNewest',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading').text(enterprise.enterpriseName);
			$head.next().text('主营产品：');
			$enterpriseList.append($new);
		}
	});
}

function getNewAuth(){
	var $enterpriseList = $('#newAuthEnterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getNewAuth',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading').text(enterprise.enterpriseName);
//			$head.next().text('主营产品：');
			$enterpriseList.append($new);
		}
	});
}