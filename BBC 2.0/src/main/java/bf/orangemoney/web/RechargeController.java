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
import org.springframework.web.bind.annotation.SessionAttributes;

import bf.orangemoney.dao.ProduitRepository;
import bf.orangemoney.dao.TransactionRepository;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Transaction;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/recharge")
@SessionAttributes({"connectedUser", "userGroup"})
public class RechargeController {
	
	@Autowired
	private TransactionRepository transRepo;
	
	@Autowired
	private ProduitRepository prodRepo;
	
	@Autowired
	private Metier metier;
	
	@RequestMapping("/addRecharge")
	public String addRecharge(Model model, String alertRecharge) {
		model.addAttribute("client", new Client());
		model.addAttribute("alertRecharge", alertRecharge);
		model.addAttribute("produits", prodRepo.produitsRecharge());
		return "recharge/addRecharge";
	}
	
	@PostMapping("/saveRecharge")
	public String saveRecharge(Client client, Integer idProduit, 
			long montantT, long montantP, String date) throws ParseException {
		metier.saveRecharge(client, idProduit, montantT, montantP, formatDate(date));
		String alertRecharge=prodRepo.getOne(idProduit).getLibelleProduit();
		return "redirect:addRecharge?alertRecharge="+alertRecharge;
	}
	
	//Liste des recharges
	@GetMapping("/bilanRecharge")
	public String bilanRecharge(Model model) {
		List<Transaction> recharges=transRepo.transactionByOperation("RECHARGE");
		if(recharges.isEmpty()) {
			recharges=null;
		}
		else {
			int size = recharges.size();
			for(int i=0; i<size; i++) {
				recharges.get(i).setMontTransF(Formatage.method(recharges.get(i).getMontantTrans()));
				recharges.get(i).setMontPayeF(Formatage.method(recharges.get(i).getMontantPaye()));
				recharges.get(i).setMontRestF(Formatage.method(recharges.get(i).getMontantRest()));
			}
		}
		model.addAttribute("recharges", recharges);
		return "recharge/bilanRecharge";
	}
	
	//Formatage de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
}
