jQuery(document).ready(function() {
	//Fullscreen background
    $.backstretch("plugin/bootstrap-login-form/img/login_bg.jpg");
    
    if(window != top)
		top.location.href = location.href;
    
    $("input").focus(function(){
    	$(this).removeClass('input-error');
		$("#errorMsg").fadeOut();
	});
    $('form').submit(function(e){
    	e.preventDefault();
    	var $userName = $('input[name="userName"]');
    	var $password = $('input[name="password"]');
    	//检查非空
		if( $userName.val().trim() == "" ) {
			$userName.addClass('input-error');
		}else {
			$userName.removeClass('input-error');
		}
		//密码可包含空格
		if( $password.val() == "" ) {
			$password.addClass('input-error');
		}else {
			$password.removeClass('input-error');
		}
		var userName = $userName.val().trim();
		var password = $password.val();
    	if(userName != "" && password !=""){
    		$.post(this.action,$(this).serialize(),function(data){
        		if(data == 0){
        			$('#errorMsg').fadeIn();
        		}else{
        			window.location.href="backstage/index";
        		}
        	});
    	}
    });
});