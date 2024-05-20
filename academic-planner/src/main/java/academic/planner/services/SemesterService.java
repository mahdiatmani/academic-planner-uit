package academic.planner.services;

import academic.planner.entities.Semester;
import academic.planner.repositories.SemesterRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {

    protected final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    public Semester getById(Long id) {
        Optional<Semester> optionalSemester = semesterRepository.findById(id);
        if(! optionalSemester.isPresent()) throw new AcademicPlannerException(ErrorCode.semester_not_found, "Semester not found with id => " + id);
        return optionalSemester.get();
    }

    public Semester getByCode(String code) {
        Optional<Semester> optionalSemester = semesterRepository.findByCode(code);
        if(! optionalSemester.isPresent()) throw new AcademicPlannerException(ErrorCode.semester_not_found, "Semester not found with code => " + code);
        return optionalSemester.get();
    }

    public Semester save(Semester semester) {
        return semesterRepository.save(semester);
    }

    public List<Semester> save(List<Semester> semesters) {
        List<Semester> semestersList = new ArrayList<Semester>();
        for (Semester semester: semesters) {
            semestersList.add(semesterRepository.save(semester));
        }
        return semestersList;
    }

    public void delete(Long id) {
        semesterRepository.delete(getById(id));
    }
}
