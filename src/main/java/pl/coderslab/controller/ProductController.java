package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.EventGroups;
import pl.coderslab.entity.Product;
import pl.coderslab.entity.User;
import pl.coderslab.repository.EventGroupsRepository;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.repository.ProductRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private EventRepository eventRepository;
    private EventGroupsRepository eventGroupsRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductController(EventRepository eventRepository, EventGroupsRepository eventGroupsRepository,
                             UserRepository userRepository, ProductRepository productRepository) {
        this.eventRepository = eventRepository;
        this.eventGroupsRepository = eventGroupsRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/addProduct")
    public String addProductGet(@RequestParam String eventId, Model model) {
        Event event = eventRepository.getEventById(Long.parseLong(eventId));
        model.addAttribute("event", event);
        model.addAttribute("product", new Product());
        model.addAttribute("participants", event.getParticipants());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPost(@Valid Product product, BindingResult bindingResult,
                                 HttpSession session, @RequestParam String eventId) {
        User buyer = (User) session.getAttribute("loggedUser");

        List<Long> listIds = product.getParticipantIds();
        List<User> listUsers = new ArrayList<>();

        for (int i = 0; i < listIds.size(); i++) {
            User user = userRepository.getById(listIds.get(i));
            listUsers.add(user);
        }

        EventGroups eventGroup = new EventGroups();
        eventGroup.setUser(buyer);
        eventGroup.setEvent(eventRepository.getEventById(Long.parseLong(eventId)));
        eventGroup.setUsers(listUsers);

        List<EventGroups> buyerEventGrouops = new ArrayList<>();
        EventGroups found = null;
        if (eventGroupsRepository.getEventGroupsByUser(buyer).isEmpty()) {
            eventGroupsRepository.save(eventGroup);
        } else {
            buyerEventGrouops = eventGroupsRepository.getEventGroupsByUser(buyer);
            for (EventGroups eventGroups : buyerEventGrouops) {
                if (eventGroups.getEvent().getId() != Long.parseLong(eventId)) {
                    buyerEventGrouops.remove(eventGroups);
                }
            }
            for (EventGroups eventGroups : buyerEventGrouops) {
                List<User> users = eventGroups.getUsers();

                if (eventGroup.getUsers().containsAll(users) &&
                        users.containsAll(eventGroup.getUsers()) &&
                        users.size() == eventGroup.getUsers().size()) {
                    found = eventGroups;
                    break;
                }
            }
        }
        if (found != null) {
            product.setEventGroupId(found);
            productRepository.save(product);
        } else {
            product.setEventGroupId(eventGroup);
            eventGroupsRepository.save(eventGroup);
            productRepository.save(product);
        }

        return "redirect:/addProduct?eventId=" + eventId;
    }
}
