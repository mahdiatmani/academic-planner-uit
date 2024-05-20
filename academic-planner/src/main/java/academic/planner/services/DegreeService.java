package academic.planner.services;

import academic.planner.entities.Degree;
import academic.planner.repositories.DegreeRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DegreeService {

    protected final DegreeRepository degreeRepository;

    @Autowired
    public DegreeService(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    public List<Degree> getAll() {
        return degreeRepository.findAll();
    }

    public Degree getById(Long id) {
        Optional<Degree> optionalDegree = degreeRepository.findById(id);
        if(! optionalDegree.isPresent()) throw new AcademicPlannerException(ErrorCode.degree_not_found, "Degree not found with id => " + id);
        return optionalDegree.get();
    }

    public Degree getByCode(String code) {
        Optional<Degree> optionalDegree = degreeRepository.findByCode(code);
        if(! optionalDegree.isPresent()) throw new AcademicPlannerException(ErrorCode.degree_not_found, "Degree not found with code => " + code);
        return optionalDegree.get();
    }

    public Degree save(Degree degree) {
        return degreeRepository.save(degree);
    }

    public List<Degree> save(List<Degree> degrees) {
        List<Degree> degreesList = new ArrayList<Degree>();
        for (Degree degree: degrees) {
            degreesList.add(degreeRepository.save(degree));
        }
        return degreesList;
    }

    public void delete(Long id) {
        degreeRepository.delete(getById(id));
    }
}
