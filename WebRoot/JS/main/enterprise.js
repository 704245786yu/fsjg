var g_processType = null;	//加工类型
var g_costumeCategory = null;	//服饰类型

$(function(){
	init();
	initCostumeCategory();
	getExcellent();
	getNewest();
	getNewAuth();
});

function init(){
	var str = $('#hiddenProcessType').val();
	str = str.replace(/,}/,'}');
	g_processType = $.parseJSON(str);
	str = $('#hiddenCostumeCategory').val();
	str = str.replace(/,}/,'}');
	g_costumeCategory = $.parseJSON(str);
}

//初始化服饰类别选择弹框
function initCostumeCategory(){
	var $tableTemplate = $('#template').clone();
	$tableTemplate.css('display','table');
	var $trTemplate = $tableTemplate.find('tr').clone();
	$trTemplate.css('display','table-row');
	var $tds = $trTemplate.find('td');
	//三级类目的checkbox
	var $td2 = $($tds[1]);
	var $checkbox2 = $td2.find('label').clone();
	$td2.find('label').remove();	//去除隐藏的checkbox，防止表单提交的时候此类checkbox产生空值
	$checkbox2.css('display','block');
	
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
					$checkBox.find(':checkbox').val(twoLevelNode.categoryCode);
					$checkBox.find('span').html(twoLevelNode.categoryName);
					$td2.append($checkBox);
				}else{
					//二级类目节点
					var $tr = $trTemplate.clone();
					var $tds = $tr.find('td');
					var $td1 = $($tds[0]).css('display','table-cell');
					$td1.find(':checkbox').val(twoLevelNode.categoryCode);
					$td1.find('span').html(twoLevelNode.categoryName);
					//三级类目节点
					$td2 = $($tds[1]);
					var threeLevel = twoLevelNode.children;
					for(var k=0; k<threeLevel.length; k++){	//遍历三级类目
						var $checkBox = $checkbox2.clone();
						$checkBox.find(':checkbox').val(threeLevel[k].categoryCode);
						$checkBox.find('span').html(threeLevel[k].categoryName);
						$td2.append($checkBox);
					}
					$table.append($tr);
				}
			}
			//消除“服装”标签下的多出的一个空tr，该tr由32行产生
			var $trs = $table.find('tr');
			if($trs.length >2)
				$($trs[1]).remove();
		}
	});
}

//二级服饰类目单击事件
function checkAllSubBox(checkbox){
	//选中超过3个checkbox则不允许再选，直接返回
	if(isOverChecked(checkbox)==false)
		return;
	var $checkbox = $(checkbox);
	var $td1 = $checkbox.parents('td');
	var $td2 = $td1.next();
	if($checkbox.prop('checked')==true)
		$td2.find(':checkbox').prop({'checked':'checked','disabled':'disabled'});
	else
		$td2.find(':checkbox').prop({'checked':false,'disabled':false});
}

//三级类目单击事件
function threeLevelCheck(checkbox){
	isOverChecked(checkbox)
}

//判断checkbox是否选择超过三个
function isOverChecked(checkbox){
	var $checkbox = $(checkbox);
	if($checkbox.prop('checked')==true){
		var length = $('#costumeCategoryModal :checked').not(':disabled').length;
		if(length > 3){
			alert('超过三个');
			$checkbox.prop('checked',false);
			return false;
		}
	}
	return true;
}

//确定选择产品类别
function checkCostume(){
	var str = '';
	var $checks = $('#costumeCategoryModal :checked').not(':disabled');
	for(var i=0; i<$checks.length; i++){
		var $check = $($checks[i]);
		str += $check.next().html()+',';
	}
	if(str=='')
		str = '选择产品类别';
	$('#costumeBtn').html(str);
}

//确定选择地区
function checkDistrict(){
	var $select = $('#districtContainer :selected');
	var str = '';
	for(var i=0; i<$select.length; i++){
		if($($select[i]).val() != '')
			str += $($select[i]).text()+' ';
	}
	if(str == '')
		str = '选择发单地区';
	$('#districtBtn').html(str);
}

$('form').submit(function(){
	$('form :checked').prop('disabled',false);
});

//获取优秀工厂(接单工厂)
function getExcellent(){
	var $enterpriseList = $('#enterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getExcellent',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading > a').text(enterprise.enterpriseName).attr('href','enterprise/showDetail/'+enterprise.id);
			var $list1 = $head.next().text('员工人数：'+enterprise.staffNumber+'人');
			//加工类型
			var processTypeAry = enterprise.processType.split(',');
			var processType = '';
			for(var j=0; j<processTypeAry.length; j++){
				processType += g_processType[processTypeAry[j]]+' ';
			}
			var $list2 = $list1.next().text('加工类型：'+processType);
			//主营产品
			var costumeCodes = enterprise.costumeCode;
			var costumeStr = '';
			for(j=0; j<costumeCodes.length; j++){
				costumeStr += g_costumeCategory[costumeCodes[j]]+' ';
			}
			$list2.next().text('主营产品：'+costumeStr);
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
			var costumeCodes = enterprise.costumeCode;
			var costumeStr = '';
			for(j=0; j<costumeCodes.length; j++){
				costumeStr += g_costumeCategory[costumeCodes[j]]+' ';
			}
			$head.next().text('主营产品：'+costumeStr);
			$enterpriseList.append($new);
		}
	});
}

//最新认证加工厂
function getNewAuth(){
	var $enterpriseList = $('#newAuthEnterpriseList');
	var $enterprise = $enterpriseList.children('.enterprise');
	$.get('enterprise/getNewAuth',function(data){
		for(var i=0; i<data.length; i++){
			var enterprise = data[i];
			var $new = $enterprise.clone().css('display','block');
			var $head = $new.find('.media-heading').text(enterprise.enterpriseName);
			var costumeCodes = enterprise.costumeCode;
			var costumeStr = '';
			for(j=0; j<costumeCodes.length; j++){
				costumeStr += g_costumeCategory[costumeCodes[j]]+' ';
			}
			$head.next().text('主营产品：'+costumeStr);
			$enterpriseList.append($new);
		}
	});
}