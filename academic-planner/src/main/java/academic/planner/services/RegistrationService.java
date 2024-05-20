package academic.planner.services;


import academic.planner.entities.Registration;
import academic.planner.repositories.RegistrationRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    protected final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    public Registration getById(Long id) {
        Optional<Registration> optionalPromotionStudent = registrationRepository.findById(id);
        if (!optionalPromotionStudent.isPresent())
            throw new AcademicPlannerException(ErrorCode.registration_not_found, "PromotionStudent not found with id => " + id);
        return optionalPromotionStudent.get();
    }

    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    public List<Registration> save(List<Registration> registrations) {
        List<Registration> promotionStudentsList = new ArrayList<Registration>();
        for (Registration registration : registrations) {
            promotionStudentsList.add(registrationRepository.save(registration));
        }
        return promotionStudentsList;
    }

    public void delete(Registration registration) {
        registrationRepository.delete(registration);
    }
}
