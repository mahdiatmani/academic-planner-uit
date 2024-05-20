package academic.planner.repositories;

import academic.planner.entities.AcademicProgram;
import academic.planner.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicProgramRepository extends JpaRepository<AcademicProgram, Long> {

    Optional<AcademicProgram> findByCode(String code);
    List<AcademicProgram> findByDepartmentCode(String departmentCode);

}
