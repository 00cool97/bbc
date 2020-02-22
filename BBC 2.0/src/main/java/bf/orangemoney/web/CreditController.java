package bf.orangemoney.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import bf.orangemoney.dao.ClientRepository;
import bf.orangemoney.entities.Canal;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Paiement;
import bf.orangemoney.entities.Pret;
import bf.orangemoney.entities.Transaction;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/credit")
@SessionAttributes({"connectedUser", "userGroup"})
public class CreditController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private Metier metier;
	
	@GetMapping("/listeCredits")
	public String listeCredits(Model model) {
		List<Client> credits=clientRepo.listClientCredit();
		if(credits.isEmpty()) {
			credits=null;
		}
		else {
			int size = credits.size();
			for(int i=0; i<size; i++) {
				credits.get(i).setMontCreditF(Formatage.method(credits.get(i).getMontantCredit()));
				credits.get(i).setMontPayeF(Formatage.method(credits.get(i).getMontantPaye()));
				credits.get(i).setMontRestF(Formatage.method(credits.get(i).getMontantRest()));
			}
		}
		model.addAttribute("credits", credits);
		return "credit/listeCredits";
	}
	
	//Detai d'un credit
	@GetMapping("/showCredit")
	public String showCredit(Model model, Integer idClient) {
		Client client = clientRepo.getOne(idClient);
		client.setMontCreditF(Formatage.method(client.getMontantCredit()));
		client.setMontPayeF(Formatage.method(client.getMontantPaye()));
		client.setMontRestF(Formatage.method(client.getMontantRest()));
		
		List<Transaction> transactions = client.getTransactionList();
		List<Canal> canals = client.getCanalList();
		List<Paiement> paiements = client.getPaiementList();
		List<Pret> prets = client.getPretList();
		
		if(paiements.isEmpty()) {
			paiements = null;
		}
		else {
			int size = paiements.size();
			for(int i=0; i<size; i++) {
				paiements.get(i).setMontPaieF(Formatage.method(paiements.get(i).getMontantPaie()));
			}
		}
		
		if(canals.isEmpty()) {
			canals = null;
		}
		else {
			int size = canals.size();
			for(int i=0; i<size; i++) {
				canals.get(i).setMontAbonneeF(Formatage.method(canals.get(i).getMontantAbonnee()));
				canals.get(i).setMontPayeF(Formatage.method(canals.get(i).getMontantPaye()));
				canals.get(i).setMontRestF(Formatage.method(canals.get(i).getMontantRest()));
			}
		}
		
		if(transactions.isEmpty()) {
			transactions = null;
		}
		else {
			int size = transactions.size();
			for(int i=0; i<size; i++) {
				transactions.get(i).setMontTransF(Formatage.method(transactions.get(i).getMontantTrans()));
				transactions.get(i).setMontPayeF(Formatage.method(transactions.get(i).getMontantPaye()));
				transactions.get(i).setMontRestF(Formatage.method(transactions.get(i).getMontantRest()));
			}
		}
		
		if(prets.isEmpty()) {
			prets = null;
		}
		else {
			int size = prets.size();
			for(int i=0; i<size; i++) {
				prets.get(i).setMontPretF(Formatage.method(prets.get(i).getMontantPret()));
			}
		}
		
		model.addAttribute("client", client);
		model.addAttribute("paiements", paiements);
		model.addAttribute("transactions", transactions);
		model.addAttribute("canals", canals);
		model.addAttribute("prets", prets);
		return "credit/showCredit";
	}
	
	//Enregistrer un remboursement
	@GetMapping("/saveRemboursement")
	public String saveRemboursement(Model model, Integer idClient,
			String date, long montant) throws ParseException {
		metier.saveRemboursement(idClient, formatDate(date), montant);
		return "redirect:showCredit?idClient="+idClient;
	}
	
	@GetMapping("/bilanCredits")
	public String bilanCredits(Model model) {
		List<Client> credits = clientRepo.bilanCredits();
		if(credits.isEmpty()) {
			credits = null;
		}
		else {
			int size = credits.size();
			for(int i=0; i<size; i++) {
				credits.get(i).setMontCreditF(Formatage.method(credits.get(i).getMontantCredit()));
				credits.get(i).setMontPayeF(Formatage.method(credits.get(i).getMontantPaye()));
				credits.get(i).setMontRestF(Formatage.method(credits.get(i).getMontantRest()));
			}
		}
		model.addAttribute("credits", credits);
		return "credit/bilanCredits";
	}
	
	@GetMapping("/showDetailCredit")
	public String showDetailCredit(Model model, Integer idClient) {
		Client client = clientRepo.getOne(idClient);
		client.setMontCreditF(Formatage.method(client.getMontantCredit()));
		client.setMontPayeF(Formatage.method(client.getMontantPaye()));
		client.setMontRestF(Formatage.method(client.getMontantRest()));
		
		List<Transaction> transactions = client.getTransactionList();
		List<Canal> canals = client.getCanalList();
		List<Paiement> paiements = client.getPaiementList();
		List<Pret> prets = client.getPretList();

		if(paiements.isEmpty()) {
			paiements = null;
		}
		else {
			int size = paiements.size();
			for(int i=0; i<size; i++) {
				paiements.get(i).setMontPaieF(Formatage.method(paiements.get(i).getMontantPaie()));
			}
		}
		
		if(canals.isEmpty()) {
			canals = null;
		}
		else {
			int size = canals.size();
			for(int i=0; i<size; i++) {
				canals.get(i).setMontAbonneeF(Formatage.method(canals.get(i).getMontantAbonnee()));
				canals.get(i).setMontPayeF(Formatage.method(canals.get(i).getMontantPaye()));
				canals.get(i).setMontRestF(Formatage.method(canals.get(i).getMontantRest()));
			}
		}
		
		if(transactions.isEmpty()) {
			transactions = null;
		}
		else {
			int size = transactions.size();
			for(int i=0; i<size; i++) {
				transactions.get(i).setMontTransF(Formatage.method(transactions.get(i).getMontantTrans()));
				transactions.get(i).setMontPayeF(Formatage.method(transactions.get(i).getMontantPaye()));
				transactions.get(i).setMontRestF(Formatage.method(transactions.get(i).getMontantRest()));
			}
		}
		
		if(prets.isEmpty()) {
			prets = null;
		}
		else {
			int size = prets.size();
			for(int i=0; i<size; i++) {
				prets.get(i).setMontPretF(Formatage.method(prets.get(i).getMontantPret()));
			}
		}
		
		model.addAttribute("client", client);
		model.addAttribute("paiements", paiements);
		model.addAttribute("transactions", transactions);
		model.addAttribute("canals", canals);
		model.addAttribute("prets", prets);
		return "credit/showDetailCredit";
	}

	//Formatage de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
	
}
