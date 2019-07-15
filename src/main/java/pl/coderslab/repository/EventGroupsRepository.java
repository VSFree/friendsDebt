package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.EventGroups;

public interface EventGroupsRepository extends JpaRepository<EventGroups, Long> {

}
