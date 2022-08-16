package net.codejava;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.codejava.user.User2Repository;
 
@Controller
public class AppController {
 
   @Autowired
    private UserRepository userRepo;
  
     
    @GetMapping("/asa")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/register")
    public String showsignupForm(Model model) {
    	model.addAttribute("user", new User());
    	
    	return "signup_form";
    }
    	
    	@PostMapping("/process_register")
    	public String processRegistration(User user) {
    		
    		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
    		String encodedPassword =encoder.encode(user.getPassword());
    		user.setPassword(encodedPassword);
    		userRepo.save(user);
    	return"register_success";
    }
    	@GetMapping("/list_users")
    	public String viewUsersList(Model model) {
    		
    		List<User> listUsers = userRepo.findAll();
    		model.addAttribute("listUsers", listUsers);
    		
    		return "users";
    	}
    	@GetMapping("/Admin")
    	public String Change_pass() {
    		return "aaa";
    	}
    	
    	
    	@PostMapping("/ADchange")
    	public String Pass(
    			@RequestParam("EMAIL") String useremail,
    			@RequestParam("PASSWORD") String userpass,
    			@RequestParam("ID") int id) {
    
    		Optional<User> optional = userRepo.findById(id);
    		
    		User user = optional.get();
    		
    		String b= user.getEmail();
    		System.out.println(user.getEmail());
    		System.out.println(b);
    		
    		if(b.equals(useremail)) {
    			
    			BCryptPasswordEncoder encoder1= new BCryptPasswordEncoder();
        		String encodedPassword1 =encoder1.encode(userpass);
        		//user.setPassword(encodedPassword);
        	//	userRepo.save(user);
    		user.setPassword(encodedPassword1);
    		userRepo.save(user);
    		System.out.println();
    		
    	return"bbb";
    		}
    		return "ccc";
    	
}
    	//....................................
    	@GetMapping("/ccc2")
    	public String ccc() {
    		return "ccc2";
    	}
    	@GetMapping("/welcome")
    	public String Wecome() {
    		return "Shop";
    	}
}
