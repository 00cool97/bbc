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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import bf.orangemoney.dao.ClientRepository;
import bf.orangemoney.dao.PlacementRepository;
import bf.orangemoney.entities.Client;
import bf.orangemoney.entities.Placement;
import bf.orangemoney.entities.RetraitPlacement;
import bf.orangemoney.metier.Metier;

@Controller
@RequestMapping("/placement")
@SessionAttributes({"connectedUser", "userGroup"})
public class PlacementControlleur {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private PlacementRepository placeRepo;
	
	@Autowired
	private Metier metier;
	
	@GetMapping("/addPlacement")
	public String addPlacement(Model model, String alertP) {
		model.addAttribute("client", new Client());
		model.addAttribute("alertP", alertP);
		return "placement/addPlacement";
	}
	
	@PostMapping("/savePlacement")
	public String savePlacement(Model model, Client client, 
			long montantPlacement, String datePlacement) throws ParseException {
		metier.savePlacement(client, montantPlacement, formatDate(datePlacement));
		return "redirect:addPlacement?alertP="+"";
	}
	
	@GetMapping("/totalPlacements")
	public String totalPlacements(Model model) {
		List<Client> placements = clientRepo.listClientPlacement();
		if(placements.isEmpty()) {
			placements = null;
		}
		else {
			int size = placements.size();
			for(int i=0; i<size; i++) {
				placements.get(i).setMontPlaceF(Formatage.method(placements.get(i).getMontantPlace()));
				placements.get(i).setMontRetireF(Formatage.method(placements.get(i).getMontantRetire()));
				placements.get(i).setMontPlaceRestF(Formatage.method(placements.get(i).getMontantPlaceRest()));
			}
		}
		
		model.addAttribute("placements", placements);
		return "placement/totalPlacements";
	}
	
	@GetMapping("/bilanPlacements")
	public String bilanPlacements(Model model) {
		List<Placement> placements=placeRepo.findAll();
		if(placements.isEmpty()) {
			placements = null;
		}
		else {
			int siz = placements.size();
			for(int i=0; i<siz; i++) {
				placements.get(i).setMontPlacementF(Formatage.method(placements.get(i).getMontantPlacement()));
			}
		}
		model.addAttribute("placements", placements);
		return "placement/bilanPlacements";
	}
	
	@GetMapping("/retraitPlacement")
	public String retraitPlacement(Model model) {
		List<Client> placements = clientRepo.listRetraitPlacement();
		if(placements.isEmpty()) {
			placements = null;
		}
		else {
			int size = placements.size();
			for(int i=0; i<size; i++) {
				placements.get(i).setMontPlaceF(Formatage.method(placements.get(i).getMontantPlace()));
				placements.get(i).setMontRetireF(Formatage.method(placements.get(i).getMontantRetire()));
				placements.get(i).setMontPlaceRestF(Formatage.method(placements.get(i).getMontantPlaceRest()));
			}
		}
		model.addAttribute("placements", placements);
		return "placement/retraitPlacement";
	}
	
	@GetMapping("/showRetraitPlacement")
	public String showRetraitPlacement(Model model, Integer idClient) {
		Client client = clientRepo.getOne(idClient);
		client.setMontPlaceF(Formatage.method(client.getMontantPlace()));
		client.setMontRetireF(Formatage.method(client.getMontantRetire()));
		client.setMontPlaceRestF(Formatage.method(client.getMontantPlaceRest()));
		client.setMontPlaceF(Formatage.method(client.getMontantPlace()));
		client.setMontRetireF(Formatage.method(client.getMontantRetire()));
		
		List<RetraitPlacement> retraits = client.getRetraitPlacementList();
		
		List<Placement> placements = client.getPlacementList();
		int siz = placements.size();
		for(int i=0; i<siz; i++) {
			placements.get(i).setMontPlacementF(Formatage.method(placements.get(i).getMontantPlacement()));
		}
		if(retraits.isEmpty()) {
			retraits = null;
		}
		else {
			int size = retraits.size();
			for(int i=0; i<size; i++) {
				retraits.get(i).setMontRetraitF(Formatage.method(retraits.get(i).getMontantRetrait()));
			}
		}
		model.addAttribute("client", client);
		model.addAttribute("placements", placements);
		model.addAttribute("retraits", retraits);
		return "placement/showRetraitPlacement";
	}
	
	@GetMapping("/saveRetraitPlacement")
	public String saveRetraitPlacement(Model model, 
			Integer idClient, String date, long montantRetr) throws ParseException {
		metier.saveRetraitPlacement(idClient, formatDate(date), montantRetr);
		return "redirect:showRetraitPlacement?idClient="+idClient;
	}
	
	@GetMapping("/showPlacement")
	public String showPlacement(Model model, Integer idClient) {
		Client client = clientRepo.getOne(idClient);
		client.setMontPlaceF(Formatage.method(client.getMontantPlace()));
		client.setMontRetireF(Formatage.method(client.getMontantRetire()));
		client.setMontPlaceRestF(Formatage.method(client.getMontantPlaceRest()));
		List<Placement> placements = client.getPlacementList();
		int siz = placements.size();
		for(int i=0; i<siz; i++) {
			placements.get(i).setMontPlacementF(Formatage.method(placements.get(i).getMontantPlacement()));
		}
		List<RetraitPlacement> retraits = client.getRetraitPlacementList();
		if(retraits.isEmpty()) {
			retraits = null;
		}
		else {
			int size = retraits.size();
			for(int i=0; i<size; i++) {
				retraits.get(i).setMontRetraitF(Formatage.method(retraits.get(i).getMontantRetrait()));
			}
		}
		model.addAttribute("client", client);
		model.addAttribute("placements", placements);
		model.addAttribute("retraits", retraits);
		return "placement/showPlacement";
	}
	
	//Recuperation du placement
	@GetMapping("/getPlacement")
	@ResponseBody
	public Placement getPlacement(@RequestParam(value="idPlacement", required = true)
				Integer idPlacement) {
		return placeRepo.getOne(idPlacement);
	}
	
	@PostMapping("/saveUpdatePlacement")
	public String saveUpdatePlacement(Integer idPlacement, String nomM, String prenomM,
				long montantM, String dateM) throws ParseException {
		metier.saveUpdatePlacement(idPlacement, nomM, prenomM, montantM, formatDate(dateM));
		return "redirect:bilanPlacements";
	}
	
	@GetMapping("/deletePlacement")
	public String deletePlacement(Integer idPlacement) {
		metier.deletePlacement(idPlacement);
		return "redirect:bilanPlacements";
	}
	
	//Formatage de date
	public Date formatDate(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
}
