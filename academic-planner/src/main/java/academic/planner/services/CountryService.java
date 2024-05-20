package academic.planner.services;

import academic.planner.entities.City;
import academic.planner.entities.Country;
import academic.planner.repositories.CountryRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    protected final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAll() {
        List<Country> countries = countryRepository.findAll();
        for (Country country: countries) {
            for(City city : country.getCities()){
                city.setCountry(null);
            }
        }
        return countries;
    }

    public Country getById(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if(! optionalCountry.isPresent()) throw new AcademicPlannerException(ErrorCode.country_not_found, "Country not found with id => " + id);
        return optionalCountry.get();
    }

    public Country getByCode(String code) {
        Optional<Country> optionalCountry = countryRepository.findByCode(code);
        if(! optionalCountry.isPresent()) throw new AcademicPlannerException(ErrorCode.country_not_found, "Country not found with code => " + code);
        return optionalCountry.get();
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> save(List<Country> countries) {
        List<Country> countriesList = new ArrayList<Country>();
        for (Country country: countries) {
            countriesList.add(countryRepository.save(country));
        }
        return countriesList;
    }

    public void delete(Long id) {
        countryRepository.delete(getById(id));
    }
}
