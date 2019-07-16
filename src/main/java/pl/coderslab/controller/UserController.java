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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String registerGet(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerPost(@Valid User user, @RequestParam String confirmPassword, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        //check if passwords are same
        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.addError(new FieldError("user", "password",
                    "Passwords do not match"));
            return "register";
        }
        //check if there is that email in database
        if (userRepository.getByEmail(user.getEmail()) != null) {
            bindingResult.addError(new FieldError("user", "email",
                    "User already exists"));
            return "register";
        }
        //check if there is that nick in database
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
    public String loginGet() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String email,
                            @RequestParam String password,
                            Model model, HttpServletRequest request) {
        boolean success = true;
        if (email == null || email.trim().equals("")
                || password == null || password.trim().equals("")) {
            success = false;
        }

        User existingUser = null;
        if (success) {
            existingUser = userRepository.getByEmail(email);
            if (existingUser == null || !existingUser.getPassword().equals(password)) {
                success = false;
            }
        }

        if (!success) {
            model.addAttribute("success", false);
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("loggedUser", existingUser);

        return "redirect:/";
    }

    //logout
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
