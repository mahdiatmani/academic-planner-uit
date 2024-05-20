package academic.planner.services;

import academic.planner.entities.LegalIdType;
import academic.planner.repositories.LegalIdTypeRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LegalIdTypeService {

    protected final LegalIdTypeRepository legalIdTypeRepository;

    @Autowired
    public LegalIdTypeService(LegalIdTypeRepository legalIdTypeRepository) {
        this.legalIdTypeRepository = legalIdTypeRepository;
    }

    public List<LegalIdType> getAll() {
        return legalIdTypeRepository.findAll();
    }

    public LegalIdType getById(Long id) {
        Optional<LegalIdType> optionalLegalIdType = legalIdTypeRepository.findById(id);
        if(! optionalLegalIdType.isPresent()) throw new AcademicPlannerException(ErrorCode.legalIdType_not_found, "LegalIdType not found with id => " + id);
        return optionalLegalIdType.get();
    }

    public LegalIdType getByCode(String code) {
        Optional<LegalIdType> optionalLegalIdType = legalIdTypeRepository.findByCode(code);
        if(! optionalLegalIdType.isPresent()) throw new AcademicPlannerException(ErrorCode.legalIdType_not_found, "LegalIdType not found with code => " + code);
        return optionalLegalIdType.get();
    }

    public LegalIdType save(LegalIdType legalIdType) {
        return legalIdTypeRepository.save(legalIdType);
    }

    public List<LegalIdType> save(List<LegalIdType> legalIdTypes) {
        List<LegalIdType> legalIdTypesList = new ArrayList<LegalIdType>();
        for (LegalIdType legalIdType: legalIdTypes) {
            legalIdTypesList.add(legalIdTypeRepository.save(legalIdType));
        }
        return legalIdTypesList;
    }

    public void delete(Long id) {
        legalIdTypeRepository.delete(getById(id));
    }
}
