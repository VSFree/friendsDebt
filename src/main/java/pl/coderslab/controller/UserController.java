package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //registration
    @GetMapping(value = "/register")
    public String registerGet(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerPost(@Valid User user, @RequestParam String confirmPassword, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.addError(new FieldError("user", "password",
                    "Passwords do not match"));
            return "register";
        }

        if (userRepository.getByEmail(user.getEmail()) != null) {
            bindingResult.addError(new FieldError("user", "email",
                    "User already exists"));
            return "register";
        }

        if (userRepository.getByNick(user.getNick()) != null) {
            bindingResult.addError(new FieldError("user", "nick",
                    "User already exists"));
            return "register";
        }

        userRepository.save(user);
        return "redirect:/";
    }

    //login
    @GetMapping("/login")
    public String loginGet(){
        return "login";
    }

}
