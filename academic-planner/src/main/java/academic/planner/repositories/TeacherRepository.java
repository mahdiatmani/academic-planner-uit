package academic.planner.repositories;

import academic.planner.entities.Student;
import academic.planner.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t WHERE " +
            "(:username IS NULL OR t.username LIKE %:username%) AND " +
            "(:firstName IS NULL OR t.firstName LIKE %:firstName%) AND " +
            "(:lastName IS NULL OR t.lastName LIKE %:lastName%) AND " +
            "(:legalIdNumber IS NULL OR t.legalIdNumber LIKE %:legalIdNumber%)")
    Page<Teacher> findByFilter(
            @Param("username") String username,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("legalIdNumber") String legalIdNumber,
            Pageable pageable
    );

    List<Teacher> findByDepartmentCode(String departmentCode);
}
