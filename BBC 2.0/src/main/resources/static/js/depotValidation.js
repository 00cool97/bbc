/**
 * 
 */
$(document).ready(function(){
	
	//Calcul du montant restant à payer
	var nom = $('#nom');
	var prenom = $('#prenom');
	var montantP = $('#montantP');
	var montantR = $('#montantR');
	var montantT = $('#montantT');
	var numCNIB = $('#numCNIB');
	var saveDepot = $('#saveDepot');
	
	//Désactivation des champs
	$(nom).prop('disabled', true);
	$(prenom).prop('disabled', true);
	$(montantP).prop('disabled', true);
	$(montantT).prop('disabled', true);
	
	$(numCNIB).focusout(
			function(e){
				$('small').remove();
				if($(numCNIB).val() == ''){
					$(numCNIB).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'SVP! Veuillez saisir un numéro de CNIB!</small>');
				}
				else if($(numCNIB).val().length < 8){
					$(numCNIB).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'Désolé! Ce numéro de CNIB est invalide! Veuillez saisir un numéro de CNIB valide!</small>');
				}
				else{
					$(nom).prop('disabled', false);
					$(prenom).prop('disabled', false);
					$(montantP).prop('disabled', false);
					$(montantT).prop('disabled', false);
					$(nom).val('');
					$(prenom).val('');
					$.getJSON('https://localhost:2020/depot/findclient', {
						numCNIB : $(this).val(),
						ajax : 'true'
						}, function(data) {
							if(data != null){
								$(nom).val(data.nom);
								$(prenom).val(data.prenom);
								$(nom).prop('disabled', true);
								$(prenom).prop('disabled', true);
							}
					});
				}
			}
	);
	
	/*
	$(phone).focusout(function(){
		$(nom).prop('disabled', false);
		$(prenom).prop('disabled', false);
		$(montantP).prop('disabled', false);
		$(montantT).prop('disabled', false);
	});
	*/
	
	$("#montantT, #montantP").focusout(
			function(){
				var montT = parseFloat($(montantT).val());
				var montP = parseFloat($(montantP).val());
				$('small').remove();
				if(montP > montT){
					$(montantP).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'Désolé! Ce montant est superieur au montant du dépôt <br> Veuillez saisir un montant inferieur!</small>');
				}
				else{
					var montR = parseFloat(montT - montP);
					$(montantR).val(parseFloat(montR));
				}
			}
	);
	
	$('#saveDepot').submit(
			function(e){
				var retour = 1;
				var montT = parseFloat($(montantT).val());
				var montP = parseFloat($(montantP).val());
				$('small').remove();
				
				if($(numCNIB).val() == ''){
					$(numCNIB).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'SVP! Veuillez saisir un numéro de CNIB!</small>');
				}
				else if($(numCNIB).val().length < 8){
					$(numCNIB).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'Désolé! Ce numéro de CNIB est invalide! Veuillez saisir un numéro de CNIB valide!</small>');
				}
				
				if($(phone).val() == ''){
					$(phone).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'SVP! Veuillez saisir un contact!</small>');
					retour = 0;
				}
				else if($(phone).val().length <8){
					$(phone).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'Désolé! Ce contact est invalide! Veuillez saisir un contact valide!</small>');
					retour = 0;
				}
				
				if(montP > montT){
					$(montantP).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'Désolé! Ce montant est superieur au montant du dépôt <br> Veuillez saisir un montant inferieur!</small>');
					retour = 0;
				}
				
				if(retour == 1){
					return true;
				}
				else{
					return false;
				}
				
			}
	);
	
	
});