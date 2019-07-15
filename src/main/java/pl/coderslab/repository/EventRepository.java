package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
