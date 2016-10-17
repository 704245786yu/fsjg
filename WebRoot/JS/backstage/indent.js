var g_state = {"0":"未收到报价","1":"已收到报价","2":"已接单","3":"已失效"};//订单状态
var g_indentType = {"1":"来样加工","2":"看款下单"};//订单类型
var g_saleMarket = {"1":"内销","2":"外销"};
var g_processType = {"1":"清加工","2":"经销","3":"其他"};
var g_jqConfirm = new JqConfirmExtend();
var g_total = null;

$(function(){
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-cn'
    });
	$('#listPanel input[name="indentNum"').mask('#');
});

$('#dg').bootstrapTable({
	onPageChange:function(number,size){
		g_total = $('#dg').bootstrapTable('getOptions').totalRows;
	}
});

function queryParams(params){
	var indentNum = $('input[name="indentNum"]').val();
	var state = $('select[name="state"]').val();
	var beginDate = $('input[name="beginDate"]').val();
	var endDate = $('input[name="endDate"]').val();
	params.indentNum = indentNum;
	params.state = state;
	params.beginDate = beginDate;
	params.endDate = endDate;
	
	params.total = g_total;//g_total可能为null
	
	delete params.order;
	return params;
}

//查询
function search(){
	g_total = null;//设置为null，使后台重新计算total值
	$('#dg').bootstrapTable('selectPage',1);
}

//显示个人详细信息，隐藏其他面板
function view(id){
	var tempRow = $('#dg').bootstrapTable('getRowByUniqueId',id);
	var row = new Object();
	$.extend(row,tempRow);
	var $viewPanel = $('#viewPanel');
	$viewPanel.find(':hidden[name="id"]').val(row.id);
	var districtCodes = [row.condProvince, row.condCity, row.condCounty, row.condTown];
	$.get('district/getNameByCode',{"codes":districtCodes},function(data){
		var str = '';
		for(var i=0; i<data.length; i++){
			str += data[i];
		}
		$viewPanel.find('p[name="condDistrict"]').html(str);
	});
	//获取发单人信息
	var userType = row.createUserType;
	var userId = row.createBy;
	$.get('basicUser/getUserInfo',{'userId':userId,'userType':userType},function(data){
		$viewPanel.find('p[name="userName"]').html(data.userName);
		$viewPanel.find('p[name="district"]').html(data.district);
	});
	row.indentType = g_indentType[row.indentType];
	row.saleMarket = g_saleMarket[row.saleMarket];
	row.processType = g_processType[row.processType];
	if(row.expectPrice == -1)
		row.expectPrice = '面议';
	if(row.isUrgency == 1)
		row.isUrgency = '是';
	row.state = g_state[row.state];
	
	var pAry = $viewPanel.find('p');
	for(var i=0; i<pAry.length; i++){
		var $p = $(pAry[i]);
		var name = $p.attr("name");
		$p.html(row[name]);
	}
	var $aDoc = $viewPanel.find('a[name="document"]').css('display','none');
	if(row.document !=null && row.document.length>0){
		$aDoc.css('display','');
		$aDoc.attr('href','uploadFile/indent/'+row.document).html('点击下载订单附件');
	}
	var $photoP = $viewPanel.find('p[name="photo"]');
	$photoP.empty();
	if(row.photo != null && row.photo.length>0){
		var $img = $photoP.next().children('img');
		var photos = row.photo.split(',');
		for(var i=0;i<photos.length; i++){
			var $tempImg = $img.clone();
			$tempImg.attr('src',"uploadFile/indent/"+photos[i]);
			$photoP.append($tempImg);
		}
	}
	$('#listPanel').hide();
	$('#viewPanel').show();
}

function hideView(){
	$('#listPanel').show();
	$('#viewPanel').hide();
}

//创建人类型
function createUserTypeFmt(value,row,index){
	if(value==1)
		return '个人';
	else if(value==2)
		return '工厂';
}

//状态
function stateFmt(value,row,index){
	return g_state[value];
}
//订单类型
function indentTypeFmt(value,row,index){
	return g_indentType[value];
}

function operFormatter(value,row,index){
	var viewBtn = "<button type='button' class='btn btn-default btn-xs' title='查看' onclick='view("+row.id+")'><span class='text-primary glyphicon glyphicon-eye-open'></span></button>";
	var delBtn = " <button type='button' class='btn btn-default btn-xs' title='垃圾处理' onclick='del("+row.id+","+row.indentNum+")'><span class='text-primary glyphicon glyphicon-trash'></span></button>";
	return viewBtn + delBtn;
}

//删除
function del(id,indentNum){
	if(indentNum == undefined){
		id = $('#viewPanel :hidden[name="id"]').val();
		indentNum = $('#viewPanel p[name="indentNum"]').html();
	}
    //显示confirm弹出框
	$.confirm({
		title: false,
		content: "确定处理该条记录么？",
		confirmButton: '确定',
		cancelButton: '取消',
		confirmButtonClass: 'btn-danger',
		cancelButtonClass: 'btn-primary',
		confirm: function(){
			$.get('indent/delByIndentNum',{'indentNum':indentNum},function(result){
				if(result == 200){
					$('#dg').bootstrapTable("removeByUniqueId", id);//删除数据行
					g_jqConfirm.autoClose('操作成功');
				}
				$('#listPanel').show();
				$('#viewPanel').hide();
			});
		}
	});
}