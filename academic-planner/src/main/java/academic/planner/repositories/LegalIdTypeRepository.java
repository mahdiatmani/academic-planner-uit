package academic.planner.repositories;

import academic.planner.entities.Establishment;
import academic.planner.entities.LegalIdType;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalIdTypeRepository extends JpaRepository<LegalIdType, Long> {
    Optional<LegalIdType> findByCode(String code);

}
