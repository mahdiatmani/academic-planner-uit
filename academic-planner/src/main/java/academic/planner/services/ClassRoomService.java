package academic.planner.services;

import academic.planner.entities.ClassRoom;
import academic.planner.repositories.ClassRoomRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {

    protected final ClassRoomRepository classRoomRepository;

    @Autowired
    public ClassRoomService(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    public List<ClassRoom> getAll() {
        return classRoomRepository.findAll();
    }

    public List<ClassRoom> getByEstablishmentCode(String establishmentCode) {
        return classRoomRepository.findByEstablishmentCode(establishmentCode);
    }
    public ClassRoom getById(Long id) {
        Optional<ClassRoom> optionalClassRoom = classRoomRepository.findById(id);
        if(! optionalClassRoom.isPresent()) throw new AcademicPlannerException(ErrorCode.classRoom_not_found, "ClassRoom not found with id => " + id);
        return optionalClassRoom.get();
    }

    public ClassRoom getByCode(String code) {
        Optional<ClassRoom> optionalClassRoom = classRoomRepository.findByCode(code);
        if(! optionalClassRoom.isPresent()) throw new AcademicPlannerException(ErrorCode.classRoom_not_found, "ClassRoom not found with code => " + code);
        return optionalClassRoom.get();
    }

    public ClassRoom save(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    public List<ClassRoom> save(List<ClassRoom> classRooms) {
        List<ClassRoom> classRoomsList = new ArrayList<ClassRoom>();
        for (ClassRoom classRoom: classRooms) {
            classRoomsList.add(classRoomRepository.save(classRoom));
        }
        return classRoomsList;
    }

    public void delete(Long id) {
        classRoomRepository.delete(getById(id));
    }
}
