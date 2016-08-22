$(function() {
	$('#loginModal').modal();
    $('#loginModal form').submit(function(e){
    	e.preventDefault();
    	var $userName = $('input[name="userName"]');
    	var $password = $('input[name="password"]');
		var userName = $userName.val().trim();
		var password = $password.val();
    	if(userName != "" && password !=""){
    		$.post(this.action,$(this).serialize(),function(data){
        		if(data == true){
        			location.reload();
        		}else{
        			$('#errorMsg').fadeIn();
        		}
        	});
    	}
    });
});