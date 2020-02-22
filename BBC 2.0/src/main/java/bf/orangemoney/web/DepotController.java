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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import bf.orangemoney.dao.ClientRepository;
import bf.orangemoney.dao.ProduitRepository;
import bf.orangemoney.dao.TransactionRepository;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Produit;
import bf.orangemoney.entities.Transaction;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/depot")
@SessionAttributes({"connectedUser", "userGroup"})
public class DepotController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private TransactionRepository transRepo;
	
	@Autowired
	private ProduitRepository prodRepo;
	
	@Autowired
	private Metier metier;
	
	@RequestMapping("/addDepot")
	public String addDepot(Model model, String alertDepot) {
		model.addAttribute("client", new Client());
		model.addAttribute("alertDepot", alertDepot);
		model.addAttribute("produits", prodRepo.produitsDepotRetraits());
		return "depot/addDepot";
	}
	
	@PostMapping("/saveDepot")
	public String saveDepot(Model model, Client client, 
			Integer idProduit, long montantT,long montantP, String date) throws ParseException {
		metier.saveDepot(client, idProduit, montantT, montantP, formatDate(date));
		String alertDepot=prodRepo.getOne(idProduit).getLibelleProduit();
		return "redirect:addDepot?alertDepot="+alertDepot;
	}
	
	@GetMapping("/bilanDepots")
	public String bilanDepots(Model model) {
		List<Transaction> depots=transRepo.transactionByOperation("DEPOT");
		if(depots.isEmpty()) {
			depots=null;
		}
		else {
			int size = depots.size();
			for(int i=0; i<size; i++) {
				depots.get(i).setMontTransF(Formatage.method(depots.get(i).getMontantTrans()));
				depots.get(i).setMontPayeF(Formatage.method(depots.get(i).getMontantPaye()));
				depots.get(i).setMontRestF(Formatage.method(depots.get(i).getMontantRest()));
			}
		}
		model.addAttribute("depots", depots);
		return "depot/bilanDepots";
	}
	
	//Detail d'un dépôt
	@GetMapping("/showDepot")
	public String showDepot(Model model, Integer idTrans) {
		Transaction trans = transRepo.getOne(idTrans);
		model.addAttribute("transaction", trans);
		return "depot/showDepot";
	}
	
	//Recuperation du client
	@RequestMapping(value = "/findclient", method = RequestMethod.GET)
	@ResponseBody 
	public Client findClient(
			@RequestParam(value="numCNIB", required = true)String numCNIB) {
		return clientRepo.findByNumCNIB(numCNIB);
	}
	
	//Formatage de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
	
	@PostMapping("/saveNewProduit")
	public String saveNewProduit(Model model, Integer typeOp, String libelle) {
		Produit prod = new Produit();
		prod.setLibelleProduit(libelle);
		if(typeOp == 1) {
			prod.setEtatDepotRetrait(true);
			prod.setEtatRecharge(true);
		}
		else {
			prod.setEtatDepotRetrait(false);
			prod.setEtatRecharge(true);
		}
		prodRepo.save(prod);
		return "redirect:/bilanProduits";
	}
}
