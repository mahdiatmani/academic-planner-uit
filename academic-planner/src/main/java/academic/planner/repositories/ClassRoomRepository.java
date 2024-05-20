package academic.planner.repositories;

import academic.planner.entities.ClassRoom;
import academic.planner.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

    Optional<ClassRoom> findByCode(String code);

    List<ClassRoom> findByEstablishmentCode(String establishmentCode);

}
