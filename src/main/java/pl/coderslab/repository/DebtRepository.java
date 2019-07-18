package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Debt;
import pl.coderslab.entity.EventGroups;
import pl.coderslab.entity.User;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {

    List<Debt> getDebtsByUserAndEventGroup(User user, EventGroups eventGroups);

}
