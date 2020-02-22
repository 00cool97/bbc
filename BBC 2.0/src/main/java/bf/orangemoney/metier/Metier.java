package bf.orangemoney.metier;

import java.util.Date;

import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Pret;
import bf.orangemoney.users.User;

public interface Metier {
	public void saveDepot(Client client, 
			Integer idProduit, long montantT,long montantP, Date date);
	
	public void saveRetrait(Client client, Integer idProduit, Integer idPiece,
			String reference, long montant, Date date);
	
	public void saveRecharge(Client client, Integer idProduit, 
			long montantT, long montantP, Date date);
	
	public void saveRemboursement(Integer idClient, Date date, long montant);
	
	public void saveAbonnement(Client client, String numCarte, String numAbonne,
			Integer dureeAbonnee, long montantA, long montantP, Date dateAbonnee);
	
	public void savePret(Client client, long montant, Date datePret);
	
	public void savePlacement(Client client,long montantPlacement, Date datePlacement);
	public void saveRetraitPlacement(Integer idClient, Date date, long montant);
	
	public void saveUser(User user, Long idRole);
	
	public void deletePret(Pret pret);
	
	public void saveUpdatePret(Integer idPret, String nomM, 
			String prenomM, long montantM, Date dateM);
	
	public void saveUpdatePlacement(Integer idPlacement, String nomM, String prenomM,
				long montantM, Date dateM);
	
	public void deletePlacement(Integer idPlacement);
}
