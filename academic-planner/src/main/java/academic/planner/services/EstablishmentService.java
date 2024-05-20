package academic.planner.services;

import academic.planner.entities.Establishment;
import academic.planner.repositories.EstablishmentRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentService {

    protected final EstablishmentRepository establishmentRepository;

    @Autowired
    public EstablishmentService(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    public List<Establishment> getAll() {
        return establishmentRepository.findAll();
    }

    public Establishment getById(Long id) {
        Optional<Establishment> optionalEstablishment = establishmentRepository.findById(id);
        if(! optionalEstablishment.isPresent()) throw new AcademicPlannerException(ErrorCode.establishment_not_found, "Establishment not found with id => " + id);
        return optionalEstablishment.get();
    }

    public Establishment getByCode(String code) {
        Optional<Establishment> optionalEstablishment = establishmentRepository.findByCode(code);
        if(! optionalEstablishment.isPresent()) throw new AcademicPlannerException(ErrorCode.establishment_not_found, "Establishment not found with code => " + code);
        return optionalEstablishment.get();
    }

    public Establishment save(Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    public List<Establishment> save(List<Establishment> establishments) {
        List<Establishment> establishmentsList = new ArrayList<Establishment>();
        for (Establishment establishment: establishments) {
            establishmentsList.add(establishmentRepository.save(establishment));
        }
        return establishmentsList;
    }

    public void delete(Long id) {
        establishmentRepository.delete(getById(id));
    }

}
