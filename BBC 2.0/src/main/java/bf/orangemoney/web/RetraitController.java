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

import bf.orangemoney.dao.PiecejustificativeRepository;
import bf.orangemoney.dao.ProduitRepository;
import bf.orangemoney.dao.TransactionRepository;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Transaction;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/retrait")
@SessionAttributes({"connectedUser", "userGroup"})
public class RetraitController {
	
	@Autowired
	private TransactionRepository transRepo;
	
	@Autowired
	private ProduitRepository prodRepo;
	
	@Autowired
	private PiecejustificativeRepository pieceRepo;
	
	@Autowired
	private Metier metier;
	
	@RequestMapping("/addRetrait")
	public String addRetrait(Model model, String alertRetrait) {
		model.addAttribute("client", new Client());
		model.addAttribute("pieces", pieceRepo.findAll());
		model.addAttribute("produits", prodRepo.produitsDepotRetraits());
		model.addAttribute("alertRetrait", alertRetrait);
		return "retrait/addRetrait";
	}
	
	@PostMapping("/saveRetrait")
	public String saveRetrait(Client client, Integer idProduit, Integer idPiece,
			String reference, long montant, String date) throws ParseException {
		metier.saveRetrait(client, idProduit, idPiece, reference, montant, formatDate(date));
		String alertRetrait = prodRepo.getOne(idProduit).getLibelleProduit();
		return "redirect:addRetrait?alertRetrait="+alertRetrait;
	}
	
	//Bilan des retraits
	@GetMapping("/bilanRetraits")
	public String bilanRetraits(Model model) {
		List<Transaction> retraits=transRepo.transactionByOperation("RETRAIT");
		if(retraits.isEmpty()) {
			retraits=null;
		}
		else {
			int size = retraits.size();
			for(int i=0; i<size; i++) {
				retraits.get(i).setMontTransF(Formatage.method(retraits.get(i).getMontantTrans()));
				retraits.get(i).setMontPayeF(Formatage.method(retraits.get(i).getMontantPaye()));
				retraits.get(i).setMontRestF(Formatage.method(retraits.get(i).getMontantRest()));
			}
		}
		model.addAttribute("retraits", retraits);
		return "retrait/bilanRetraits";
	}
	
	//Formated de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
}
