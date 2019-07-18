package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.*;
import pl.coderslab.repository.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class EventController {

    private EventRepository eventRepository;
    private EventGroupsRepository eventGroupsRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private DebtRepository debtRepository;

    @Autowired
    public EventController(EventRepository eventRepository,
                           EventGroupsRepository eventGroupsRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository,
                           DebtRepository debtRepository) {
        this.eventRepository = eventRepository;
        this.eventGroupsRepository = eventGroupsRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.debtRepository = debtRepository;
    }

    //all events
    @GetMapping(value = "/events")
    public String displayEvents(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        List<EventGroups> eventGroupsList = eventGroupsRepository.getEventGroupsByUserId(user.getId());
        List<Event> eventList = new ArrayList<>();
//        for (int i = 0; i < eventGroupsList.size(); i++) {
//            Long id = eventGroupsList.get(i).getId();
//            if (!eventList.contains(eventRepository.getEventById(id))) {
//                eventList.add(eventRepository.getEventById(id));
//            }
//        }
        eventList.addAll(eventRepository.getEventsByParticipantsId(user.getId()));

        model.addAttribute("eventList", eventList);
        return "events";
    }

    //add event
    @GetMapping(value = "/addEvent")
    public String addEventGet(Model model) {
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @PostMapping(value = "/addEvent")
    public String addEventPost(@Valid Event event, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "addEvent";
        }
        User user = (User) session.getAttribute("loggedUser");
        event.getParticipants().add(user);
        eventRepository.save(event);
        EventGroups eventGroups = new EventGroups();
        eventGroups.setEvent(event);
        eventGroups.setUser(user);
        eventGroupsRepository.save(eventGroups);
        return "redirect:/events";
    }

    //event manager
    @GetMapping(value = "/eventManager")
    public String eventManagerGet(@RequestParam String eventId, Model model, HttpSession session) {
        Event event = eventRepository.getEventById(Long.parseLong(eventId));
        model.addAttribute("event", event);

        User buyer = (User) session.getAttribute("loggedUser");
        List<EventGroups> eventGroupsList = eventGroupsRepository.getEventGroupsByEventAndUser(event, buyer);

        model.addAttribute("eventGroups", eventGroupsList);

        HashMap<String, Double> debtorMap = new HashMap<String, Double>();

        for (EventGroups eventGroup : eventGroupsList) {
            for (User user : eventGroup.getUsers()) {
                if (user.getNick().equals(buyer.getNick())) {
                    continue;
                }
                if (debtorMap.keySet().contains(user.getNick())) {
                    Double addPrice = 0.0;
                    for (Product product : eventGroup.getProducts()) {
                        addPrice += (product.getPrice() / eventGroup.getUsers().size());
                    }
                    addPrice += debtorMap.get(user.getNick());
                    debtorMap.replace(user.getNick(), addPrice);

                } else {
                    Double addPrice = 0.0;
                    for (Product product : eventGroup.getProducts()) {
                        addPrice += (product.getPrice() / eventGroup.getUsers().size());
                    }
                    debtorMap.put(user.getNick(), addPrice);
                }
            }
        }
        model.addAttribute("debtorsMap", debtorMap);

        HashMap<String, Double> creditorMap = new HashMap<>();
        List<EventGroups> eventGroupList =
                eventGroupsRepository.getEventGroupsByEventIdAndUsersId(event.getId(), buyer.getId());

        for (EventGroups eventGroup : eventGroupList) {
            if (eventGroup.getUser().getNick().equals(buyer.getNick())) {
                continue;
            }
            Double addPrice = 0.0;
            for (User user : eventGroup.getUsers()) {
                if (user.getNick().equals(buyer.getNick())) {
                    for (Product product : eventGroup.getProducts()) {
                        addPrice += (product.getPrice() / eventGroup.getUsers().size());
                    }
                }
                if (creditorMap.keySet().contains(user.getNick())) {
                    addPrice += creditorMap.get(user.getNick());
                    creditorMap.replace(user.getNick(), addPrice);
                } else {
                    if (addPrice > 0) {
                        creditorMap.put(user.getNick(), addPrice);
                    }
                }
            }
        }

        model.addAttribute("creditors", creditorMap);


        return "eventManager";
    }


    //adding event participants
    @GetMapping(value = "/addParticipants")
    public String addParticipantGet(Model model, @RequestParam String eventId,
                                    @RequestParam(required = false) String success) {
        String asuccess = "" + success;
        if (asuccess.equals("false")) {
            model.addAttribute("success", false);
        }
        model.addAttribute("event", eventRepository.getEventById(Long.parseLong(eventId)));

        return "addParticipants";
    }

    @PostMapping(value = "/addParticipants")
    public String addParticipantsPost(@RequestParam String participantNick, Model model, @RequestParam String eventId) {
        boolean success = true;
        if (participantNick == null || participantNick.trim().equals("")) {
            success = false;
        }

        if (success) {
            User participant = userRepository.getByNick(participantNick);
            if (participant == null) {
                success = false;
            }
        }

        if (!success) {
            model.addAttribute("success", false);
            return "redirect:/addParticipants" + "?eventId=" + eventId;
        }

        Event event = eventRepository.getEventById(Long.parseLong(eventId));
        User check = userRepository.getByNick(participantNick);
        if (event.getParticipants().contains(check)) {
            model.addAttribute("success", false);
            return "redirect:/addParticipants" + "?eventId=" + eventId;
        }

        event.getParticipants().add(userRepository.getByNick(participantNick));
        eventRepository.save(event);

        return "redirect:/addParticipants" + "?eventId=" + eventId;
    }
}