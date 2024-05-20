package academic.planner.repositories;

import academic.planner.entities.Country;
import academic.planner.entities.Establishment;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    Optional<Establishment> findByCode(String code);

}
