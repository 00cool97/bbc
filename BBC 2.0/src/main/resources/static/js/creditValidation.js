/**
 * 
 */
$(document).ready(function(){
	var montantR = $('#montantR');
	var montant = $('#montant');
	
	$('#saveRemboursement').submit(function(){
		$('small').remove();
		var montR = parseFloat($(montantR).val());
		var mont = parseFloat($(montant).val());
		if(mont > montR){
			$('#saveRemboursement').after('<small  class="text-danger font-italic font-weight-bold">'+
			'Désolé! Ce montant est superieur au montant restant! <br> Veuillez saisir un montant inferieur!</small>');
			return false;
		}
		else{
			return true;
		}
	});
});