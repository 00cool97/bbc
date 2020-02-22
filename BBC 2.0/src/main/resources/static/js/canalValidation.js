/**
 * 
 */
$(document).ready(function(){
	
	//Calcul du montant restant à payer
	var nom = $('#nom');
	var prenom = $('#prenom');
	var numCNIB = $('#numCNIB');
	var montantA = $('#montantA');
	var montantR = $('#montantR');
	var montantP = $('#montantP');
	var numCarte = $('#numCarte');
	var numAbonne = $('#numAbonne');
	
	//Désactivation des champs
	$(nom).prop('disabled', true);
	$(prenom).prop('disabled', true);
	$(montantA).prop('disabled', true);
	$(montantP).prop('disabled', true);
	$(numCarte).prop('disabled', true);
	$(numAbonne).prop('disabled', true);
	
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
					$(montantA).prop('disabled', false);
					$(numCarte).prop('disabled', false);
					$(numAbonne).prop('disabled', false);
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
	
	$("#montantA, #montantP").focusout(
			function(){
				$('small').remove();
				var montA = parseFloat($(montantA).val());
				var montP = parseFloat($(montantP).val());
				
				if(montP > montA){
					$(montantP).after('<small  class="offset-3 col-8 text-danger font-italic font-weight-bold">'+
					'Désolé! Ce montant est superieur au montant de l\'abonnement <br> Veuillez saisir un montant inferieur!</small>');
				}
				
				var montR = parseFloat(montA - montP);
				$(montantR).val(parseFloat(montR));
			}
	);
	
	$('#saveAbonnement').submit(
			function(e){
				var retour = 1;
				var montA = parseFloat($(montantA).val());
				var montP = parseFloat($(montantP).val());
				$('small').remove();
				
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