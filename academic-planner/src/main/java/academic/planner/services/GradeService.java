package academic.planner.services;

import academic.planner.entities.Grade;
import academic.planner.repositories.GradeRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    protected final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if(! optionalGrade.isPresent()) throw new AcademicPlannerException(ErrorCode.grade_not_found, "Grade not found with id => " + id);
        return optionalGrade.get();
    }

    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> save(List<Grade> grades) {
        List<Grade> gradesList = new ArrayList<Grade>();
        for (Grade grade: grades) {
            gradesList.add(gradeRepository.save(grade));
        }
        return gradesList;
    }

    public void delete(Grade grade) {
        gradeRepository.delete(grade);
    }
}
