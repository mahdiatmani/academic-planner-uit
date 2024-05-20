package academic.planner.services;

import academic.planner.entities.City;
import academic.planner.repositories.CityRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    protected final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City getById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if(! optionalCity.isPresent()) throw new AcademicPlannerException(ErrorCode.city_not_found, "City not found with id => " + id);
        return optionalCity.get();
    }

    public City getByCode(String code) {
        Optional<City> optionalCity = cityRepository.findByCode(code);
        if(! optionalCity.isPresent()) throw new AcademicPlannerException(ErrorCode.city_not_found, "City not found with code => " + code);
        return optionalCity.get();
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public List<City> save(List<City> cities) {
        List<City> CitiesList = new ArrayList<City>();
        for (City city: cities) {
            CitiesList.add(cityRepository.save(city));
        }
        return CitiesList;
    }

    public void delete(Long id) {
        cityRepository.delete(getById(id));
    }
}
