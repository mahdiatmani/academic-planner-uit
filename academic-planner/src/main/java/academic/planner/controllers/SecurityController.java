package academic.planner.controllers;

import academic.planner.entities.*;
import academic.planner.msg.SecurityDTO;
import academic.planner.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    private final CountryService            countryService;
    private final CityService               cityService;
    private final UniversityService         universityService;
    private final EstablishmentService      establishmentService;
    private final DepartmentService         departmentService;
    private final ClassRoomService          classRoomService;
    private final DegreeService             degreeService;
    private final AcademicProgramService    academicProgramService;
    private final PromotionService          promotionService;
    private final SemesterService           semesterService;
    private final CourseService             courseService;
    private final LegalIdTypeService        legalIdTypeService;
    private final StudentService            studentService;
    private final TeacherService            teacherService;
    private final RegistrationService       registrationService;
    private final GradeService              gradeService;
    private final ScheduleService           scheduleService;
    private final PersonService             personService;

    @Autowired
    public SecurityController(CountryService countryService, CityService cityService, UniversityService universityService,
                           EstablishmentService establishmentService, DepartmentService departmentService, ClassRoomService classRoomService,
                           DegreeService degreeService, AcademicProgramService academicProgramService, PromotionService promotionService,
                           SemesterService semesterService, CourseService courseService, LegalIdTypeService legalIdTypeService, TeacherService teacherService,
                           StudentService studentService, RegistrationService registrationService, GradeService gradeService, ScheduleService scheduleService,
                           PersonService personService) {

        this.countryService         = countryService;
        this.cityService            = cityService;
        this.universityService      = universityService;
        this.establishmentService   = establishmentService;
        this.departmentService      = departmentService;
        this.classRoomService       = classRoomService;
        this.degreeService          = degreeService;
        this.academicProgramService = academicProgramService;
        this.promotionService       = promotionService;
        this.semesterService        = semesterService;
        this.courseService          = courseService;
        this.legalIdTypeService     = legalIdTypeService;
        this.teacherService         = teacherService;
        this.studentService         = studentService;
        this.registrationService    = registrationService;
        this.gradeService           = gradeService;
        this.scheduleService        = scheduleService;
        this.personService          = personService;
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SecurityDTO> login(@RequestBody Person person) {
        return new ResponseEntity<>(personService.authenticateUser(person.getUsername(), person.getPassword()), HttpStatus.OK);
    }

    @PostMapping(
            value = "/logout",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@RequestBody Person person) {
        personService.burnToken(person.getUsername(), person.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
