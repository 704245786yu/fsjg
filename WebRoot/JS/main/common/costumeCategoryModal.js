$(function(){
	initCostumeCategory();
});

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
	var data = $.parseJSON($('#costumeCategoryModal span[name="data"]').html());
	var trades = new TreeUtil().adjTransToNest(data)[0].children;//获取根目录下的行业分类
	//获取tab模板
	var $nav = $('#costumeCategoryModal .nav-tabs');
	var $tabTemplate = $nav.children('li').clone().css('display','');
	var $tabContent = $('#costumeCategoryModal .tab-content');
	var $paneTemplate = $tabContent.children('.tab-pane').clone().css('display','');
	for(var h=0; h<trades.length; h++){	//遍历行业分类
		var trade = trades[h];
		var costumeCategorys = trade.children;
		if(costumeCategorys == null)
			break;	//没有一级类目
		for(var i=0; i<costumeCategorys.length; i++){	//遍历一级类目
			var oneLevelNode = costumeCategorys[i];
			
			//添加tab标签及对应内容
			var $tab = $tabTemplate.clone();
			var $a = $tab.children();
			$a.attr('href','#costume_'+oneLevelNode.categoryCode).html(oneLevelNode.categoryName);
			$nav.append($tab);//添加tab标签
			var $pane = $paneTemplate.clone();
			$pane.attr('id','costume_'+oneLevelNode.categoryCode);
			$tabContent.append($pane);
			
			var $costumeDiv = $('#costume_'+oneLevelNode.categoryCode);
			var $table = $tableTemplate.clone();
			$costumeDiv.append($table);
			var twoLevel = oneLevelNode.children;
			if(twoLevel==null)//没有二级类目
				break;
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
	}
	//设置第一个为active显示
	$($nav.children('li')[1]).addClass('active');
	$($tabContent.children('.tab-pane')[1]).addClass('active');
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

//判断checkbox是否选择超过5个
function isOverChecked(checkbox){
	var $checkbox = $(checkbox);
	if($checkbox.prop('checked')==true){
		var length = $('#costumeCategoryModal :checked').not(':disabled').length;
		var limitChkNum = $('#costumeCategoryModal input[name="limitChkNum"]').val();
		if(limitChkNum != ''){
			if(length > limitChkNum){
				alert('不能超过'+limitChkNum+'个');
				$checkbox.prop('checked',false);
				return false;
			}
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

/**根据服饰类型编码设置checkbox选中，同时设置btn内容*/
function checkCostumeByCodes(codes){
	var str = '';
	var $c = $('#costumeCategoryModal');
	for(var i=0; i<codes.length; i++){
		var $check = $c.find(':checkbox[value="'+codes[i]+'"]');
		$check.prop('checked','checked');
		str += $check.next().html()+',';
	}
	if(str=='')
		str = '选择产品类别';
	$('#costumeBtn').html(str);
}

function getNameByCodes(codesStr){
	var str = '';
	var codes = codesStr.split(',');
	var $c = $('#costumeCategoryModal');
	for(var i=0; i<codes.length; i++){
		var $check = $c.find(':checkbox[value="'+codes[i]+'"]');
		str += $check.next().html()+' ';
	}
	return str;
}

/**重置模态框*/
function resetModal(){
	$('#costumeCategoryModal #costumeBtn').html('选择产品类别');
}

function isCostumeCheck(){
	return $('#costumeCategoryModal :checked').length == 0 ? false : true;
}