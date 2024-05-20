package academic.planner.repositories;

import academic.planner.entities.Degree;
import academic.planner.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Optional<Degree> findByCode(String code);

}
