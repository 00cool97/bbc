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

import bf.orangemoney.dao.CanalRepository;
import bf.orangemoney.entities.Canal;
import bf.orangemoney.entities.Client;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/canal")
@SessionAttributes({"connectedUser", "userGroup"})
public class CanalControlleur {
	
	@Autowired
	private CanalRepository canalRepo;
	
	@Autowired
	private Metier metier;
	
	@GetMapping("/addAbonnement")
	public String addAbonnement(Model model, String alertAbon) {
		model.addAttribute("client", new Client());
		model.addAttribute("alertAbon", alertAbon);
		return "canal/addAbonnement";
	}
	
	
	@PostMapping("/saveAbonnement")
	public String saveAbonnement(Model model, Client client, String numCarte, String numAbonne,
			Integer dureeAbonnee, long montantA, long montantP, String dateAbonnee) throws ParseException {
		metier.saveAbonnement(client, numCarte, numAbonne, dureeAbonnee, montantA, montantP, formatDate(dateAbonnee));
		return "redirect:addAbonnement?alertAbon="+"";
	}
	
	@GetMapping("/bilanAbonnements")
	public String bilanAbonnements(Model model) {
		List<Canal> canals = canalRepo.findAll();
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
		model.addAttribute("canals", canals);
		return "canal/bilanAbonnements";
	}
	
	//Formated de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
}
