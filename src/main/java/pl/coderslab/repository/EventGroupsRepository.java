package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.EventGroups;
import pl.coderslab.entity.User;

import java.util.List;

public interface EventGroupsRepository extends JpaRepository<EventGroups, Long> {
    List<EventGroups> getEventGroupsByUser(User user);
}
