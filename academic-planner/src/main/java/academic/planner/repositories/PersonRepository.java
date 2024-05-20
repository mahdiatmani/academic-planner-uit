package academic.planner.repositories;

import academic.planner.entities.Person;
import academic.planner.entities.Teacher;
import jakarta.persistence.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByUsernameContaining(String username);
    Optional<Person> findByUsername(String username);

    @Query("SELECT p FROM Person p " +
            "INNER JOIN p.profile pr " +
            "WHERE (:username IS NULL OR p.username LIKE %:username%) AND " +
            "(:firstName IS NULL OR p.firstName LIKE %:firstName%) AND " +
            "(:lastName IS NULL OR p.lastName LIKE %:lastName%) AND " +
            "(:legalIdNumber IS NULL OR p.legalIdNumber LIKE %:legalIdNumber%) AND " +
            "(:profileCode IS NULL OR pr.code = :profileCode)")
    Page<Person> findByFilter(
            @Param("username") String username,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("legalIdNumber") String legalIdNumber,
            @Param("profileCode") String profileCode,
            Pageable pageable
    );
}
