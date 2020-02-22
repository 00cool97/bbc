/**
 * 
 */
$(document).ready(function(){
	
	var numCNIB = $('#numCNIB');
	var phone = $('#phone');
	var nom = $('#nom');
	var prenom = $('#prenom');
	var montant = $('#montant');
	
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
	
	$('.table .btnEdit').click(function(e){
		e.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href, function(pret,status){
			$('.myModalForm #cnibM').val(pret.idClient.numCNIB);
			$('.myModalForm #nomM').val(pret.idClient.nom);
			$('.myModalForm #prenomM').val(pret.idClient.prenom);
			$('.myModalForm #montantM').val(pret.montantPret);
			$('.myModalForm #dateM').val(pret.datePret);
			$('.myModalForm #idPret').val(pret.idPret);
		});
		
	});
	
	$('.table .btnDelete').click(function(e){
		e.preventDefault();
		//var hrefDelete = 'https://localhost:2020/pret/getPret?idPret='+$('#idPret').val();
		var href = $(this).attr('href');
		
		$.get(href, function(pret,status){
			$('.myModalDelete #deletePara').text(pret.idClient.nom + ' '+ pret.idClient.prenom +
					' N°CNIB='+pret.idClient.numCNIB);
			var hrefDelete = 'https://localhost:2020/pret/deletePret?idPret='+pret.idPret;
			$('#myModalDelete #delRef').attr('href',hrefDelete);
		});
		
		
	});
});