package bf.orangemoney.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import bf.orangemoney.dao.ProduitRepository;
import bf.orangemoney.dao.users.UserRepository;
import bf.orangemoney.security.UserPrincipal;
import bf.orangemoney.users.User;

@Controller
@SessionAttributes(value = "connectedUser", types = {String.class})
public class PageController {
	
	@Autowired
	private ProduitRepository prodRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private User user = new User();
	private UserPrincipal connectedUser = new UserPrincipal(user);
	

	@ModelAttribute("connectedUser")
	public String getUserConnected() {
		String connectedUsername = ""+ SecurityContextHolder.getContext().
				getAuthentication().getPrincipal() ;
		if(!connectedUsername.equals("anonymousUser")) {
			connectedUser = (UserPrincipal)
					SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			user = userRepo.findByUsername(connectedUser.getUsername());
			return user.getPrenom()+" "+user.getNom();
		}
		return "";
	}

	@ModelAttribute("userGroup")
	public String userGroup() {
		String connectedUsername = ""+ SecurityContextHolder.getContext().
				getAuthentication().getPrincipal() ;
		if(!connectedUsername.equals("anonymousUser")) {
			connectedUser = (UserPrincipal)
					SecurityContextHolder.getContext().
					getAuthentication().getPrincipal();
			user = userRepo.findByUsername(connectedUser.getUsername());
			return user.getRoleList().get(0).getLibelle();
		}
		return "";
	}
	
	@RequestMapping("/")
	public String home() {
		BCryptPasswordEncoder pwdEncod = new BCryptPasswordEncoder();
		System.out.println("\n \n Montant == "+Formatage.method(-14500000)+"\n \n");
		user = userRepo.findByUsername(((UserPrincipal)
				SecurityContextHolder.getContext().
				getAuthentication().getPrincipal()).getUsername());
		System.out.println("\n \n Test = "+pwdEncod.matches("Admin", user.getPassword())+"\n \n");
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model, SessionStatus status ) {
		status.setComplete();
		return "user/login";
	}
	
	@GetMapping("/bilanProduits")
	public String bilanProduits(Model model) {
		model.addAttribute("produits", prodRepo.findAll());
		return "bilanProduits";
	}
	
	
	
}
