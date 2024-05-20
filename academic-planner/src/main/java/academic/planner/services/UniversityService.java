package academic.planner.services;

import academic.planner.entities.University;
import academic.planner.repositories.UniversityRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    protected final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public University getById(Long id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if(! optionalUniversity.isPresent()) throw new AcademicPlannerException(ErrorCode.university_not_found, "University not found with id => " + id);
        return optionalUniversity.get();
    }

    public University getByCode(String code) {
        Optional<University> optionalUniversity = universityRepository.findByCode(code);
        if(! optionalUniversity.isPresent()) throw new AcademicPlannerException(ErrorCode.university_not_found, "University not found with code => " + code);
        return optionalUniversity.get();
    }

    public University save(University university) {
        return universityRepository.save(university);
    }

    public List<University> save(List<University> universities) {
        List<University> universitiesList = new ArrayList<University>();
        for (University university: universities) {
            universitiesList.add(universityRepository.save(university));
        }
        return universitiesList;
    }

    public void delete(Long id) {
        universityRepository.delete(getById(id));
    }
}
