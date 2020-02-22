package bf.orangemoney.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import bf.orangemoney.dao.ClientRepository;
import bf.orangemoney.dao.PretRepository;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Pret;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/pret")
@SessionAttributes({"connectedUser", "userGroup"})
public class PretControlleur {
	
	@Autowired
	private Metier metier;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private PretRepository pretRepo;
	
	@GetMapping("/addPret")
	public String addPret(Model model, String alertPret) {
		model.addAttribute("client", new Client());
		model.addAttribute("alertPret", alertPret);
		return "pret/addPret";
	}
	
	@PostMapping("/savePret")
	public String savePret(Model model, Client client, long montant, String datePret) throws ParseException {
		metier.savePret(client, montant, formatDate(datePret));
		return "redirect:addPret?alertPret="+"";
	}
	
	@GetMapping("/bilanPrets")
	public String bilanPrets(Model model) {
		List<Pret> prets = pretRepo.findAll(Sort.by(Sort.Direction.DESC, "idPret"));
		if(prets.isEmpty()) {
			prets = null;
		}
		else {
			int size = prets.size();
			for(int i=0; i<size; i++) {
				prets.get(i).setMontPretF(Formatage.method(prets.get(i).getMontantPret()));
			}
		}
		model.addAttribute("prets", prets);
		return "pret/bilanPrets";
	}
	
	@GetMapping("/getPret")
	@ResponseBody
	public Pret getPret(@RequestParam(value="idPret", required = true)Integer idPret) {
		return pretRepo.getOne(idPret);
	}
	
	@PostMapping("/saveUpdatePret")
	public String saveUpdatePret(Integer idPret, String nomM,
			String prenomM, long montantM, String dateM) throws ParseException {
		metier.saveUpdatePret(idPret, nomM, prenomM, montantM, formatDate(dateM));
		return "redirect:bilanPrets";
	}
	
	@GetMapping("/deletePret")
	public String deletePret(Integer idPret) {
		metier.deletePret(pretRepo.getOne(idPret));
		return "redirect:bilanPrets";
	}
	
	//Formatage de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
}
