/*
 *
 */
//动态创建菜单
var data;
var basedata;
$(document).ready(function(){
	$.ajax({
		type : "get",
		async : false,
		url : "menu/getAdjTree",	
		dataType : "json",							
		success : function(returndatas) {
			data=returndatas;
		}
	 });
	var ul=document.getElementById("demo-list");              
	for(var i=0;i<data.length;i++){
		if(data[i].pId==null){
			basedata=data[i];
		}
		if(data[i].pId==basedata.id){
			var obj=document.createElement("li"); 
			obj.id=data[i].id;
			if(data[i].path==null||data[i].path==""){
				obj.innerHTML="<a href='javascript:void(0);' target='right_frame'>"+data[i].menuName+"</a>";           
			}else{
				obj.innerHTML="<a href="+data[i].path+" target='right_frame'>"+data[i].menuName+"</a>";  
			}
			ul.appendChild(obj);  
			}
		}
	for(var i=1;i<data.length;i++){
		for(var j=1;j<data.length;j++){
			if(data[i].pId==data[j].id){
				var li=document.getElementById(data[j].id);
				var obj1=document.createElement("ul");
				var obj2=document.createElement("li");
				obj1.className="submenu";
				obj2.id=data[i].id;
				if(data[i].path==null||data[i].path==""){
					obj2.innerHTML="<a href='javascript:void(0);' target='right_frame'>"+data[i].menuName+"</a>";           
				}else{
					obj2.innerHTML="<a href="+data[i].path+" target='right_frame'>"+data[i].menuName+"</a>";  
				}
				obj1.appendChild(obj2);  
				li.appendChild(obj1);
			}
		}
	} 	
}); 
 jQuery(document).ready(function () {
		jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
		
	});
 $(function(){	
		//顶部导航切换
		$("#demo-list li").click(function(){
			$("#demo-list li.active").removeClass("active")
			$(this).addClass("active");
		})	
	})	
 (function($) {
$.expr[":"].Contains = function(a, i, m) {
	return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};
function filterList(header, list) {
	//@header 头部元素
	//@list 无需列表
	//创建一个搜素表单
	var form = $("<form>").attr({
		"class":"filterform",
		action:"#"
	}), input = $("<input>").attr({
		"class":"filterinput",
		type:"text"
	});
	$(form).append(input).appendTo(header);
	$(input).change(function() {
		var filter = $(this).val();
		if (filter) {
			$matches = $(list).find("a:Contains(" + filter + ")").parent();
			$("li", list).not($matches).slideUp();
			$matches.slideDown();
		} else {
			$(list).find("li").slideDown();
		}
		return false;
	}).keyup(function() {
		$(this).change();
	});
}
$(function() {
	filterList($("#form"), $("#demo-list"));
});
})(jQuery);	