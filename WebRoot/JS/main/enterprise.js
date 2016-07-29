var g_processType = {1:'清加工',2:'经销',3:'自营出口',4:'其他'};

$(function(){
	initCostumeCategory();
	getExcellent();
	getNewest();
	getNewAuth();
});

function initCostumeCategory(){
	var $tableTemplate = $('#template').clone();
	$tableTemplate.css('display','block');
	var $trTemplate = $tableTemplate.find('tr').clone();
	$trTemplate.css('display','block');
	var $tds = $trTemplate.find('td');
	//三级类目checkbox
	var $td2 = $($tds[1]);
	var $checkbox2 = $td2.find('label').clone();
	$checkbox2.css('display','inline');
	
	$.get('costumeCategory/getAllHierarchy',function(data){
		var ary = new TreeUtil().adjTransToNest(data);
		var costumeCategorys = ary[0].children;
		for(var i=0; i<costumeCategorys.length; i++){	//遍历一级类目
			var oneLevelNode = costumeCategorys[i];
			var $costumeDiv = $('#costume_'+oneLevelNode.categoryCode);
			var $table = $tableTemplate.clone();
			$costumeDiv.append($table);
			var twoLevel = oneLevelNode.children;
			var $tr = $trTemplate.clone();
			var $td2 = $($tr.find('td')[1]);
			$table.append($tr);
			for(var j=0; j<twoLevel.length; j++){	//遍历二级类目
				var twoLevelNode = twoLevel[j];
				if(twoLevelNode.children == null){	//无子节点（即三级类目）则直接添加到div中
					var $checkBox = $checkbox2.clone();
					console.log($checkBox.html());
					$checkBox.find(':checkbox').val(twoLevelNode.categoryCode);
					$checkBox.find('span').html(twoLevelNode.categoryName);
					$td2.append($checkBox);
				}else{
					//遍历三级类目
					var $p = $('<p>');
					//二级类目节点
//					var $checkBox = $template.clone();
//					$checkBox.find(':checkbox').val(twoLevelNode.categoryCode);
//					$checkBox.find('span').html(twoLevelNode.categoryName);
//					$p.append($checkBox);
////					var $checkbox = $('<input type="checkbox">').val(twoLevelNode.categoryCode)
////					var $label = $('<label>').html(twoLevelNode.categoryName);
////					$p.append($checkbox).append($label);
//					var threeLevel = twoLevelNode.children;
//					for(var k=0; k<threeLevel.length; k++){	//遍历三级类目
//						var $checkBox = $template.clone();
//						$checkBox.find(':checkbox').val(threeLevel[k].categoryCode);
//						$checkBox.find('span').html(threeLevel[k].categoryName);
//						$p.append($checkBox);
////						$checkbox = $('<input type="checkbox">').val(threeLevel[k].categoryCode);
////						$label = $('<label>').html(threeLevel[k].categoryName);
////						$p.append($checkbox).append($label);
//					}
					$costumeDiv.append($p);
				}
			}
		}
	});
}

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