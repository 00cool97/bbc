$(document).ready(function () {
	$.noConflict();
	
	/*
	 *  Definir le nombre d'éléments à afficher dans une page : "pageLength": 3 
	 */
	$('#tab1').DataTable({
		
		language: {
       	 "sProcessing":     "Traitement en cours...",
       		"sSearch":         "Rechercher&nbsp;:",
       	    "sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
       		"sInfo":           "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
       		"sInfoEmpty":      "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
       		"sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
       		"sInfoPostFix":    "",
       		"sLoadingRecords": "Chargement en cours...",
       	    "sZeroRecords":    "Aucun &eacute;l&eacute;ment &agrave; afficher",
       		"sEmptyTable":     "Aucune donn&eacute;e disponible dans le tableau",
       		"oPaginate": {
       			"sFirst":      "Premier",
       			"sPrevious":   "Pr&eacute;c&eacute;dent",
       			"sNext":       "Suivant",
       			"sLast":       "Dernier"
       		},
       		"oAria": {
       			"sSortAscending":  ": activer pour trier la colonne par ordre croissant",
       			"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
       		}
    }
	});
	
	
    $('#tab').DataTable({
    	 dom: 'Bfrtip',
         buttons: [
        	 {
                 extend: 'copy',
                 text: 'Copier'
             },
             {
                 extend: 'print',
                 text: 'Imprimer'
             }, 
             'csv', 'excel', 'pdf'
         ],
         language: {
        	 "sProcessing":     "Traitement en cours...",
        		"sSearch":         "Rechercher&nbsp;:",
        	    "sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
        		"sInfo":           "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
        		"sInfoEmpty":      "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
        		"sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
        		"sInfoPostFix":    "",
        		"sLoadingRecords": "Chargement en cours...",
        	    "sZeroRecords":    "Aucun &eacute;l&eacute;ment &agrave; afficher",
        		"sEmptyTable":     "Aucune donn&eacute;e disponible dans le tableau",
        		"oPaginate": {
        			"sFirst":      "Premier",
        			"sPrevious":   "Pr&eacute;c&eacute;dent",
        			"sNext":       "Suivant",
        			"sLast":       "Dernier"
        		},
        		"oAria": {
        			"sSortAscending":  ": activer pour trier la colonne par ordre croissant",
        			"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
        		}
     }
    });

   // $('#tab1').DataTable();
});
