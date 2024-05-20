package academic.planner.services;

import academic.planner.entities.Profile;
import academic.planner.repositories.ProfileRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    protected final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public Profile getById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if(! optionalProfile.isPresent()) throw new AcademicPlannerException(ErrorCode.profile_not_found, "Profile not found with id => " + id);
        return optionalProfile.get();
    }

    public Profile getByCode(String code) {
        Optional<Profile> optionalProfile = profileRepository.findByCode(code);
        if(! optionalProfile.isPresent()) throw new AcademicPlannerException(ErrorCode.profile_not_found, "Country not found with code => " + code);
        return optionalProfile.get();
    }

    public Profile save(Profile Profile) {
        return profileRepository.save(Profile);
    }

    public List<Profile> save(List<Profile> Profiles) {
        List<Profile> ProfilesList = new ArrayList<Profile>();
        for (Profile Profile: Profiles) {
            ProfilesList.add(profileRepository.save(Profile));
        }
        return ProfilesList;
    }

    public void delete(Long id) {
        profileRepository.delete(getById(id));
    }
}
