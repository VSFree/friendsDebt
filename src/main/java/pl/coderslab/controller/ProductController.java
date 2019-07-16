package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Product;
import pl.coderslab.entity.User;
import pl.coderslab.repository.EventRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    private EventRepository eventRepository;

    @Autowired
    public ProductController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/addProduct")
    public String addProductGet(@RequestParam String eventId, Model model) {
        Event event = eventRepository.getEventById(Long.parseLong(eventId));
        model.addAttribute("event", event);
        model.addAttribute("product", new Product());
        return "addProduct";
    }

//    @PostMapping("/addProduct")
//    public String addProductPost(@Valid Product product, BindingResult bindingResult, HttpSession session){
//        User buyer = (User) session.getAttribute("loggedUser");
//        List<User> creditors =
//        return "redirect:/eventManager";
//    }
}
