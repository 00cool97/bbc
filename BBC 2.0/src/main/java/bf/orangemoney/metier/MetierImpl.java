package bf.orangemoney.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bf.orangemoney.dao.CanalRepository;
import bf.orangemoney.dao.ClientRepository;
import bf.orangemoney.dao.OperationRepository;
import bf.orangemoney.dao.PaiementRepository;
import bf.orangemoney.dao.PiecejustificativeRepository;
import bf.orangemoney.dao.PlacementRepository;
import bf.orangemoney.dao.PretRepository;
import bf.orangemoney.dao.ProduitRepository;
import bf.orangemoney.dao.RetraitPlacementRepository;
import bf.orangemoney.dao.TransactionRepository;
import bf.orangemoney.dao.users.RoleRepository;
import bf.orangemoney.dao.users.UserRepository;
import bf.orangemoney.entities.Canal;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Operation;
import bf.orangemoney.entities.Paiement;
import bf.orangemoney.entities.Piecejustificative;
import bf.orangemoney.entities.Placement;
import bf.orangemoney.entities.Pret;
import bf.orangemoney.entities.Produit;
import bf.orangemoney.entities.RetraitPlacement;
import bf.orangemoney.entities.Transaction;
import bf.orangemoney.users.Role;
import bf.orangemoney.users.User;

@Service
@Transactional
public class MetierImpl implements Metier {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private OperationRepository operaRepo;
	
	@Autowired
	private TransactionRepository transRepo;
	
	@Autowired
	private ProduitRepository prodRepo;
	
	@Autowired
	private PaiementRepository paieRepo;
	
	@Autowired
	private CanalRepository canalRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PiecejustificativeRepository pieceRepo;
	
	@Autowired
	private PretRepository pretRepo;
	
	@Autowired
	private PlacementRepository placeRepo;
	
	@Autowired
	private RetraitPlacementRepository retPlaRepo;

	@Override
	public void saveDepot(Client client, Integer idProduit, long montantT, long montantP,
			Date date) {
		// TODO Auto-generated method stub
		Produit produit=prodRepo.getOne(idProduit);
		Operation op=operaRepo.findByLibelleOperation("DEPOT");
		//Client client2 = clientRepo.findByPhone(client.getPhone());
		Client client2 = clientRepo.findByNumCNIB(client.getNumCNIB());
		ArrayList<Produit> produits = new ArrayList<>();
		Transaction trans= new Transaction();
		long montantR = montantP - montantT;
		
		//Enregistrement du client
		if(client2 == null) {
			client.setMontantCredit(montantR);
			client.setMontantRest(montantR);
			client.setMontantPaye(0);
			client.setMontantPlace(0);
			client.setMontantRetire(0);
			client = clientRepo.save(client);
		}
		else {
			client2.setMontantCredit(client2.getMontantCredit() + montantR);
			client2.setMontantRest(client2.getMontantRest() + montantR);
			client = clientRepo.save(client2);
		}
		
		//Enregistrement de la transaction
		trans.setMontantTrans(montantT);
		trans.setMontantPaye(montantP);
		trans.setMontantRest(montantR);
		trans.setIdClient(client);
		trans.setPhone(client.getPhone());
		trans.setIdOperation(op);
		trans.setDateTrans(date);
		trans.setIdProduit(produit);
		transRepo.save(trans);
		
		//Mise à jour de l'operation
		produits.add(produit);
		op.setProduitList(produits);
		operaRepo.save(op);
		
		//Mise à jour du produit
		produit.setDotationProduit(produit.getDotationProduit() - montantT);
		prodRepo.save(produit);
	}

	@Override
	public void saveRetrait(Client client, Integer idProduit, Integer idPiece, String reference, long montant,
			Date date) {
		Produit produit=prodRepo.getOne(idProduit);
		Client client2 = clientRepo.findByPhone(client.getPhone());
		Operation op=operaRepo.findByLibelleOperation("RETRAIT");
		Piecejustificative piece = pieceRepo.getOne(idPiece);
		ArrayList<Produit> produits=new ArrayList<>();
		Transaction trans= new Transaction();
		
		//Client
		if(client2 == null) {
			client.setNom("Inconnu");
			client.setPrenom("Inconnu");
			client.setMontantPlace(0);
			client.setMontantRetire(0);
			client.setMontantCredit(0);
			client.setMontantPaye(0);
			client.setMontantRest(0);
			client = clientRepo.save(client);
		}
		else {
			client = client2;
		}
		
		//Transaction
		trans.setIdClient(client);
		trans.setIdProduit(produit);
		trans.setIdPiece(piece);
		trans.setReferencePiece(reference);
		trans.setMontantTrans(montant);
		trans.setIdOperation(op);
		trans.setDateTrans(date);
		transRepo.save(trans);
		
		//Mise à jour de l'operation
		produits.add(produit);
		op.setProduitList(produits);
		operaRepo.save(op);
		
		//Mise à jour du produit
		produit.setDotationProduit(produit.getDotationProduit() - montant);
		prodRepo.save(produit);
	}

	@Override
	public void saveRecharge(Client client, Integer idProduit, long montantT, long montantP, Date date) {
		// TODO Auto-generated method stub
		Client client2 = clientRepo.findByNumCNIB(client.getNumCNIB());
		Produit produit=prodRepo.getOne(idProduit);
		Operation op=operaRepo.findByLibelleOperation("RECHARGE");
		ArrayList<Produit> produits=new ArrayList<>();
		Transaction trans= new Transaction();
		long montantR = montantP - montantT;
		
		//Enregistrement du client
		if(client2 == null) {
			client.setMontantCredit(montantR);
			client.setMontantRest(montantR);
			client.setMontantPaye(0);
			client.setMontantPlace(0);
			client.setMontantRetire(0);
			client = clientRepo.save(client);
		}
		else {
			client2.setMontantCredit(client2.getMontantCredit() - montantR);
			client2.setMontantRest(client2.getMontantRest() - montantR);
			client = clientRepo.save(client2);
		}
		
		//Transaction
		trans.setMontantTrans(montantT);
		trans.setMontantPaye(montantP);
		trans.setMontantRest(montantR);
		trans.setIdClient(client);
		trans.setIdOperation(op);
		trans.setPhone(client.getPhone());
		trans.setDateTrans(date);
		trans.setIdProduit(produit);
		transRepo.save(trans);
		
		//MAJ operation
		produits.add(produit);
		op.setProduitList(produits);
		operaRepo.save(op);
		
		//MAJ produit
		produit.setDotationProduit(produit.getDotationProduit() - montantT);
		prodRepo.save(produit);
	}

	@Override
	public void saveRemboursement(Integer idClient, Date date, long montant) {
		// TODO Auto-generated method stub
		Paiement paie = new Paiement();
		Client client = clientRepo.findById(idClient).get();
		client.setMontantPaye(client.getMontantPaye() + montant);
		client.setMontantRest(client.getMontantRest() + montant);
		clientRepo.save(client);
		
		paie.setIdClient(client);
		paie.setMontantPaie(montant);
		paie.setDatePaie(date);
		paieRepo.save(paie);
		
	}

	@Override
	public void saveAbonnement(Client client, String numCarte, String numAbonne, Integer dureeAbonnee,
			long montantA, long montantP, Date dateAbonnee) {
		// TODO Auto-generated method stub
		Client client2 = clientRepo.findByNumCNIB(client.getNumCNIB());
		long montantR = montantP - montantA;
		
		//Enregistrement du client
		if(client2 == null) {
			client.setMontantCredit(montantR);
			client.setMontantRest(montantR);
			client.setMontantPaye(0);
			client.setMontantPlace(0);
			client.setMontantRetire(0);
			client = clientRepo.save(client);
		}
		else {
			client2.setMontantCredit(client2.getMontantCredit() + montantR);
			client2.setMontantRest(client2.getMontantRest() + montantR);
			client = clientRepo.save(client2);
		}
		
		//Enregistrement de l'abonnement
		Canal canal = new Canal();
		canal.setIdClient(client);
		canal.setNumCarte(numCarte);
		canal.setNumAbonne(numAbonne);
		canal.setDuree(dureeAbonnee);
		canal.setMontantAbonnee(montantA);
		canal.setMontantPaye(montantP);
		canal.setMontantRest(montantR);
		canal.setDateAbonnement(dateAbonnee);
		canalRepo.save(canal);
	}

	@Override
	public void savePret(Client client, long montant, Date datePret) {
		// TODO Auto-generated method stub
		Client client2 = clientRepo.findByNumCNIB(client.getNumCNIB());
		long montantPret = -montant;
		//Client
		if(client2 == null) {
			client.setMontantCredit(montantPret);
			client.setMontantRest(montantPret);
			client.setMontantPaye(0);
			client.setMontantPlace(0);
			client.setMontantRetire(0);
			client = clientRepo.save(client);
		}
		else {
			client2.setMontantCredit(client2.getMontantCredit() - montantPret);
			client2.setMontantRest(client2.getMontantRest() - montantPret);
			client = clientRepo.save(client2);
		}
		
		//Pret
		Pret pret = new Pret();
		pret.setIdClient(client);
		pret.setMontantPret(montant);
		pret.setDatePret(datePret);
		pretRepo.save(pret);
	}

	@Override
	public void savePlacement(Client client, long montantPlacement, Date datePlacement) {
		// TODO Auto-generated method stub
		Client client2 = clientRepo.findByNumCNIB(client.getNumCNIB());
		
		//Client
		if(client2 == null) {
			client.setMontantCredit(0);
			client.setMontantRest(0);
			client.setMontantPaye(0);
			client.setMontantPlace(montantPlacement);
			client.setMontantPlaceRest(montantPlacement);
			client.setMontantRetire(0);
			client = clientRepo.save(client);
		}
		else {
			client2.setMontantPlace(client2.getMontantPlace() + montantPlacement);
			client2.setMontantPlaceRest(client2.getMontantPlaceRest() + montantPlacement);
			client = clientRepo.save(client2);
		}
		
		//Placement
		Placement place = new Placement();
		place.setDatePlacement(datePlacement);
		place.setIdClient(client);
		place.setMontantPlacement(montantPlacement);
		placeRepo.save(place);
	}

	@Override
	public void saveRetraitPlacement(Integer idClient, Date date, long montant) {
		// TODO Auto-generated method stub
		Client client = clientRepo.getOne(idClient);
		client.setMontantRetire(client.getMontantRetire() + montant);
		client.setMontantPlaceRest(client.getMontantPlaceRest() - montant);
		clientRepo.save(client);
		
		RetraitPlacement retrait = new RetraitPlacement();
		retrait.setIdClient(client);
		retrait.setDateRetrait(date);
		retrait.setMontantRetrait(montant);
		retPlaRepo.save(retrait);
	}

	@Override
	public void saveUser(User user, Long idRole) {
		// TODO Auto-generated method stub
		Role role = roleRepo.getOne(idRole);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoleList(roles);
		userRepo.save(user);
	}

	@Override
	public void deletePret(Pret pret) {
		// TODO Auto-generated method stub
		Client client = pret.getIdClient();
		if(client.enableDelete()) {
			pretRepo.delete(pret);
			clientRepo.delete(client);
		}
		else {
			pretRepo.delete(pret);
		}
	}

	@Override
	public void saveUpdatePret(Integer idPret, String nomM, String prenomM, long montantM, Date dateM) {
		// TODO Auto-generated method stub
		Pret pret = pretRepo.getOne(idPret);
		Client client = clientRepo.getOne(pret.getIdClient().getIdClient()); 
		client.setNom(nomM);
		client.setPrenom(prenomM);
		pret.setMontantPret(montantM);
		pret.setDatePret(dateM);
		
		clientRepo.save(client);
		pretRepo.save(pret);
	}

	
	@Override
	public void saveUpdatePlacement(Integer idPlacement, String nomM, String prenomM, 
			long montantM, Date dateM) {
		// TODO Auto-generated method stub
		Placement placement = placeRepo.getOne(idPlacement);
		placement.setDatePlacement(dateM);
		placement.setMontantPlacement(montantM);
		
		Client client = placement.getIdClient();
		client.setNom(nomM);
		client.setPrenom(prenomM);
		client.setMontantPlaceRest(client.getMontantPlaceRest()+montantM - client.getMontantPlace());
		client.setMontantPlace(montantM);
		placeRepo.save(placement);
		clientRepo.save(client);
		
	}

	@Override
	public void deletePlacement(Integer idPlacement) {
		// TODO Auto-generated method stub
		Placement placement = placeRepo.getOne(idPlacement);
		Client client = placement.getIdClient();
		retPlaRepo.deleteAll(new ArrayList<RetraitPlacement>());
		
		if(client.enableDelete()){
			placeRepo.delete(placement);
			clientRepo.delete(client);
		}
		else {
			client.setMontantPlace(client.getMontantPlace() - placement.getMontantPlacement());
			client.setMontantPlaceRest(
					client.getMontantPlaceRest() + client.getMontantRetire() - placement.getMontantPlacement());
			placeRepo.delete(placement);
		}
	}

}
