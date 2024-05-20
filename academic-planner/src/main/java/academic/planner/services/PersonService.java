package academic.planner.services;

import academic.planner.entities.Person;
import academic.planner.msg.Filter;
import academic.planner.msg.SecurityDTO;
import academic.planner.repositories.PersonRepository;
import academic.planner.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    protected final PersonRepository personRepository;
    protected final ObjectUtils objectUtils;
    protected final EncryptionManager encryptionManager;
    protected final JwtTokenManager jwtTokenManager;

    @Autowired
    public PersonService(PersonRepository personRepository, ObjectUtils objectUtils, EncryptionManager encryptionManager, JwtTokenManager jwtTokenManager) {
        this.personRepository = personRepository;
        this.objectUtils = objectUtils;
        this.encryptionManager = encryptionManager;
        this.jwtTokenManager = jwtTokenManager;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Page<Person> getPersons(Filter filter) {
        Pageable pageable   = objectUtils.constructPageable(filter);
        return personRepository.findByFilter(filter.getUsername(), filter.getFirstName(), filter.getLastName(), filter.getLegalIdNumber(), filter.getProfileCode(), pageable);
    }

    public Person getById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(! optionalPerson.isPresent()) throw new AcademicPlannerException(ErrorCode.person_not_found, "Person not found with id => " + id);
        return optionalPerson.get();
    }

    public Person save(Person person) {
        String username = generateUsername(person.getFirstName(), person.getLastName());
        person.setUsername(username);
        person.setPassword(encryptionManager.digest(person.getUsername(), person.getUsername()));
        return personRepository.save(person);
    }

    public List<Person> save(List<Person> persons) {
        List<Person> personsList = new ArrayList<Person>();
        for (Person person: persons) {
            personsList.add(personRepository.save(person));
        }
        return personsList;
    }

    public void delete(Long id) {
        personRepository.delete(getById(id));
    }

    public String generateUsername(String firstName, String lastName){
        String[] firstNameParts = firstName.toLowerCase().split(" ");
        String[] lastNameParts = lastName.toLowerCase().split(" ");

        StringBuilder usernameBuilder = new StringBuilder();
        for (String part : firstNameParts) {
            usernameBuilder.append(part).append(".");
        }
        for (String part : lastNameParts) {
            usernameBuilder.append(part).append(".");
        }

        if (usernameBuilder.length() > 0) {
            usernameBuilder.deleteCharAt(usernameBuilder.length() - 1);
        }

        // Check if this combination firstName and lastName already exists
        String username = usernameBuilder.toString();
        List<Person> persons = personRepository.findByUsernameContaining(username);
        if(persons.size() > 0) username += "."+ persons.size();

        return username;
    }

    public SecurityDTO authenticateUser(String login, String password) {

        Optional<Person> optionalPerson = personRepository.findByUsername(login);
        if (optionalPerson.isEmpty()) throw new AcademicPlannerException(ErrorCode.security_login_password, "Login or Password invalid");

        Person person = optionalPerson.get();
        String hashPassword = encryptionManager.digest(password, person.getUsername());
        if(! person.getPassword().equals(hashPassword)) throw new AcademicPlannerException(ErrorCode.security_login_password, "Login or Password invalid");

        String token = jwtTokenManager.createToken(login);

        SecurityDTO securityDTO = new SecurityDTO();
        securityDTO.init(person, token);

        return securityDTO;
    }

    public void burnToken(String login, String token) {

        Optional<Person> person = personRepository.findByUsername(login);
        if (person.isEmpty()) throw new AcademicPlannerException(ErrorCode.security_login_password, "Login or Password invalid");

        jwtTokenManager.validateAndExpireTokenForLogin(login, token);
    }
}
