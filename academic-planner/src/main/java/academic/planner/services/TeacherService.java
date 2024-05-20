package academic.planner.services;

import academic.planner.entities.Teacher;
import academic.planner.msg.Filter;
import academic.planner.repositories.TeacherRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.EncryptionManager;
import academic.planner.utils.ErrorCode;
import academic.planner.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    protected final TeacherRepository teacherRepository;
    protected final PersonService   personService;
    protected final ObjectUtils objectUtils;
    protected final EncryptionManager encryptionManager;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, PersonService personService, ObjectUtils objectUtils, EncryptionManager encryptionManager) {
        this.teacherRepository = teacherRepository;
        this.personService = personService;
        this.objectUtils = objectUtils;
        this.encryptionManager = encryptionManager;
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Page<Teacher> getTeachers(Filter filter) {
       Pageable pageable   = objectUtils.constructPageable(filter);
        return teacherRepository.findByFilter(filter.getUsername(), filter.getFirstName(), filter.getLastName(), filter.getLegalIdNumber(), pageable);
    }

    public List<Teacher> getTeachersByDepartmentCode(String departmentCode) {
        return teacherRepository.findByDepartmentCode(departmentCode);
    }

    public Teacher getById(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if(! optionalTeacher.isPresent()) throw new AcademicPlannerException(ErrorCode.teacher_not_found, "Teacher not found with id => " + id);
        return optionalTeacher.get();
    }

    public Teacher save(Teacher teacher) {
        String username = personService.generateUsername(teacher.getFirstName(), teacher.getLastName());
        teacher.setUsername(username);
        teacher.setPassword(encryptionManager.digest(teacher.getUsername(), teacher.getUsername()));
        return teacherRepository.save(teacher);
    }

    public List<Teacher> save(List<Teacher> teachers) {
        List<Teacher> teachersList = new ArrayList<Teacher>();
        for (Teacher teacher: teachers) {
            teachersList.add(teacherRepository.save(teacher));
        }
        return teachersList;
    }

    public void delete(Long id) {
        teacherRepository.delete(getById(id));
    }

}
