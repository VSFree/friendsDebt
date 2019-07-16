package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.EventGroups;
import pl.coderslab.entity.User;
import pl.coderslab.repository.EventGroupsRepository;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    private EventRepository eventRepository;
    private EventGroupsRepository eventGroupsRepository;
    private UserRepository userRepository;

    @Autowired
    public EventController(EventRepository eventRepository, EventGroupsRepository eventGroupsRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventGroupsRepository = eventGroupsRepository;
        this.userRepository = userRepository;
    }

    //all events
    @GetMapping(value = "/events")
    public String displayEvents(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        List<EventGroups> eventGroupsList = eventGroupsRepository.getEventGroupsByUser(user);
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < eventGroupsList.size(); i++) {
            Long id = eventGroupsList.get(i).getId();
            if(!eventList.contains(eventRepository.getEventById(id))){
                eventList.add(eventRepository.getEventById(id));
            }
        }
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
        eventRepository.save(event);
        User user = (User) session.getAttribute("loggedUser");
        EventGroups eventGroups = new EventGroups();
        eventGroups.setEvent(event);
        eventGroups.setUser(user);
        eventGroupsRepository.save(eventGroups);
        return "redirect:/events";
    }

    //event manager
    @GetMapping(value = "/eventManager")
    public String eventManagerGet(){
        return "eventManager";
    }

}
