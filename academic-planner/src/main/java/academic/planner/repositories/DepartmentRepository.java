package academic.planner.repositories;

import academic.planner.entities.Department;
import academic.planner.entities.Semester;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByCode(String code);
    List<Department> findByEstablishmentCode(String establishmentCode);
}
