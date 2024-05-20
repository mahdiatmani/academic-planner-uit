package academic.planner.repositories;

import academic.planner.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE " +
            "(:username IS NULL OR s.username LIKE %:username%) AND " +
            "(:firstName IS NULL OR s.firstName LIKE %:firstName%) AND " +
            "(:lastName IS NULL OR s.lastName LIKE %:lastName%) AND " +
            "(:legalIdNumber IS NULL OR s.legalIdNumber LIKE %:legalIdNumber%) AND " +
            "(:apogeeCode IS NULL OR s.apogeeCode LIKE %:apogeeCode%) AND " +
            "(:studentNationalCode IS NULL OR s.studentNationalCode LIKE %:studentNationalCode%)")
    Page<Student> findByFilter(
            @Param("username") String username,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("legalIdNumber") String legalIdNumber,
            @Param("apogeeCode") String apogeeCode,
            @Param("studentNationalCode") String studentNationalCode,
            Pageable pageable
    );

    @Query("SELECT COUNT(s) FROM Student s WHERE " +
            "(:username IS NULL OR s.username LIKE %:username%) AND " +
            "(:firstName IS NULL OR s.firstName LIKE %:firstName%) AND " +
            "(:lastName IS NULL OR s.lastName LIKE %:lastName%) AND " +
            "(:legalIdNumber IS NULL OR s.legalIdNumber LIKE %:legalIdNumber%) AND " +
            "(:apogeeCode IS NULL OR s.apogeeCode LIKE %:apogeeCode%) AND " +
            "(:studentNationalCode IS NULL OR s.studentNationalCode LIKE %:studentNationalCode%)")
    long countByFilter(
            @Param("username") String username,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("legalIdNumber") String legalIdNumber,
            @Param("apogeeCode") String apogeeCode,
            @Param("studentNationalCode") String studentNationalCode
    );
}
