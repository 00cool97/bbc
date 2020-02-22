/**
 * 
 */
$(document).ready(function(){
	
	var numCNIB = $('#numCNIB');
	var phone = $('#phone');
	var nom = $('#nom');
	var prenom = $('#prenom');
	var montant = $('#montantPlacement');
	
	//Désactivation des champs
	$(nom).prop('disabled', true);
	$(prenom).prop('disabled', true);
	$(montant).prop('disabled', true);
	
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
					$(montant).prop('disabled', false);
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
	
	//Retrait de placement
	var saveRetraitPlacement = $('#saveRetraitPlacement');
	var montantRest = $('#montantRest');
	var montantRetr = $('#montantRetr');
	$(saveRetraitPlacement).submit(function(){
		$('small').remove();
		var montR = parseFloat($(montantRest).val());
		var mont = parseFloat($(montantRetr).val());
		if(mont > montR){
			$('#saveRetraitPlacement').after('<small  class="text-danger font-italic font-weight-bold">'+
			'Désolé! Ce montant est superieur au montant restant! <br> Veuillez saisir un montant inferieur!</small>');
			return false;
		}
		else{
			return true;
		}
	});
	
	//Modifier un placement
	$('.table .btnEdit').click(function(e){
		e.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href, function(placement,status){
			$('.myModalForm #cnibM').val(placement.idClient.numCNIB);
			$('.myModalForm #nomM').val(placement.idClient.nom);
			$('.myModalForm #prenomM').val(placement.idClient.prenom);
			$('.myModalForm #montantM').val(placement.montantPlacement);
			$('.myModalForm #dateM').val(placement.datePlacement);
			$('.myModalForm #idPlacement').val(placement.idPlacement);
		});
		
	});
	
	//Suppression d'un placement
	$('.table .btnDelete').click(function(e){
		e.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href, function(placement,status){
			$('.myModalDelete #deletePara').text(placement.idClient.nom + ' '+ placement.idClient.prenom +
					' n°CNIB='+placement.idClient.numCNIB);
			var hrefDelete = 'https://localhost:2020/placement/deletePlacement?idPlacement='+placement.idPlacement;
			$('#myModalDelete #delRef').attr('href',hrefDelete);
		});
		
		
	});
});