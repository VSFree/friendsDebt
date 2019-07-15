package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long> {

}
