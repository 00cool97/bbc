/**
 * 
 */
$(document).ready(function(){
	var password = $('#password');
	var password2 = $('#password2');
	
	$('#saveUser').submit(function(){
		$('small').remove();
		if($(password).val().length != $(password2).val().length){
			$(password2).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
			'Le mot de passe est incorrect!</small>');
			return false;
		}
		else if($(password).val() != $(password2).val()){
			$(password2).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
			'Le mot de passe est incorrect!</small>');
			return false;
		}
		else{
			return true;
		}
	});
	
	//Modification du mot de passe
	var oldMdp = $('#oldMdp');
	var newMdp = $('#newMdp');
	var editPassword = $('#editPassword');
	var btnEditPassword = $('#btnEditPassword');
	
	$(oldMdp).focusout(function(){
		$('small').remove();
		if($(oldMdp).val().length == 0){
			$(oldMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
			'SVP! Veuillez saisir l\'ancien mot de passe!</small>');
		}
		else{
			$.getJSON('https://localhost:2020/user/verifyPassword', {
				oldMdp : $(this).val(),
				ajax : 'true'
				}, function(data) {
					if(data == false ){
						$(oldMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
						'Désolé! L\'ancien mot de passe est incorrecte!</small>');
					}
			});
		}
	});
	
	$(newMdp).focusout(function(){
		$('small').remove();
		if($(newMdp).val().length == 0){
			$(newMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
			'SVP! Veuillez saisir le nouveau mot de passe!</small>');
		}
		else if($(newMdp).val().length < 4){
			$(newMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
			'SVP! Le mot de passe doit avoir au moins 4 caractères!</small>');
		}
	});
	
	$(btnEditPassword).click(function(e){
		e.preventDefault();
		//Suppression des messages d'erreurs
		$('small').remove();

		if($(oldMdp).val().length == 0){
			$(oldMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
			'SVP! Veuillez saisir l\'ancien mot de passe!</small>');
		}
		else{
			$.getJSON('https://localhost:2020/user/verifyPassword', {
				oldMdp : $(oldMdp).val(),
				ajax : 'true'
				}, function(data) {
					if(data == false ){
						$(oldMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
						'Désolé! L\'ancien mot de passe est incorrecte!</small>');
					}
					else{
						if($(newMdp).val().length == 0){
							$(newMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
							'SVP! Veuillez saisir le nouveau mot de passe!</small>');
						}
						else if($(newMdp).val().length < 4){
							$(newMdp).after('<small class="offset-4 text-danger font-italic font-weight-bold">'+
							'SVP! Le mot de passe doit avoir au moins 4 caractères!</small>');
						}
						else{
							editPassword.submit();
						}
					}
			});
		}
	});
});