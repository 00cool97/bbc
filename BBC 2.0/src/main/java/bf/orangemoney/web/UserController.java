package bf.orangemoney.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import bf.orangemoney.dao.users.RoleRepository;
import bf.orangemoney.dao.users.UserRepository;
import bf.orangemoney.metier.Metier;
import bf.orangemoney.security.UserPrincipal;
import bf.orangemoney.users.User;

@Controller
@RequestMapping("/user")
@SessionAttributes({"connectedUser", "userGroup"})
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private Metier metier;
	
	@GetMapping("/addUser")
	public String addUser(Model model, String alert) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleRepo.findAll());
		model.addAttribute("alert", alert);
		return "user/addUser";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(Model model, User user, Long idRole) {
		metier.saveUser(user, idRole);
		return "redirect:addUser?alert="+"U";
	}
	
	@GetMapping("/showUser")
	public String showUser(Model model) {
		UserPrincipal connectedUser = (UserPrincipal)
				SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		model.addAttribute("user", userRepo.findByUsername(connectedUser.getUsername()));
		return "user/showUser";
	}
	
	@RequestMapping(value = "/verifyPassword", method = RequestMethod.GET)
	@ResponseBody 
	public boolean verifyPassword(
			@RequestParam(value="oldMdp", required = true)String oldMdp) {
		BCryptPasswordEncoder pwdEncod = new BCryptPasswordEncoder();
		User user = userRepo.findByUsername(((UserPrincipal)
				SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		return pwdEncod.matches(oldMdp, user.getPassword());
	}
	
	@PostMapping("/updateUser")
	public String updateUser(String newMdp, String username) {
		User user = userRepo.findByUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(newMdp));
		userRepo.save(user);
		return "redirect:/login";
	}
	
	@RequestMapping("/listeUsers")
	public String listeUsers(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "user/listeUsers";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(Long idUser) {
		userRepo.deleteById(idUser);
		return "redirect:listeUsers";
	}
}
