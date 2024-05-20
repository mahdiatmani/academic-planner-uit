package academic.planner.controllers;

import academic.planner.entities.*;
import academic.planner.msg.Filter;
import academic.planner.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
    public AdminController(CountryService countryService, CityService cityService, UniversityService universityService,
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
            value = "/universitySave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<University> universitySave(@RequestBody University university) {
        return new ResponseEntity<>(universityService.save(university), HttpStatus.OK);
    }

    @PostMapping(
            value = "/universitiesSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<University>> universitiesSave(@RequestBody List<University> universities) {
        return new ResponseEntity<>(universityService.save(universities), HttpStatus.OK);
    }

    @DeleteMapping(value = "/universityDelete/{id}")
    public ResponseEntity universityDelete(@PathVariable Long id) {
        universityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/countrySave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> countrySave(@RequestBody Country country) {
        return new ResponseEntity<>(countryService.save(country), HttpStatus.OK);
    }

    @PostMapping(
            value = "/countriesSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> countriesSave(@RequestBody List<Country> countries) {
        return new ResponseEntity<>(countryService.save(countries), HttpStatus.OK);
    }

    @DeleteMapping(value = "/countryDelete/{id}")
    public ResponseEntity countryDelete(@PathVariable Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/citySave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> citySave(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @PostMapping(
            value = "/citiesSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> citiesSave(@RequestBody List<City> cities) {
        return new ResponseEntity<>(cityService.save(cities), HttpStatus.OK);
    }

    @DeleteMapping(value = "/cityDelete/{id}")
    public ResponseEntity cityDelete(@PathVariable Long id) {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/establishmentSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Establishment> establishmentSave(@RequestBody Establishment establishment) {
        return new ResponseEntity<>(establishmentService.save(establishment), HttpStatus.OK);
    }

    @PostMapping(
            value = "/establishmentsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Establishment>> establishmentsSave(@RequestBody List<Establishment> establishments) {
        return new ResponseEntity<>(establishmentService.save(establishments), HttpStatus.OK);
    }

    @DeleteMapping(value = "/establishmentDelete/{id}")
    public ResponseEntity establishmentDelete(@PathVariable Long id) {
        establishmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/departmentSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> departmentSave(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.OK);
    }

    @PostMapping(
            value = "/departmentsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> departmentsSave(@RequestBody List<Department> departments) {
        return new ResponseEntity<>(departmentService.save(departments), HttpStatus.OK);
    }

    @DeleteMapping(value = "/departmentDelete/{id}")
    public ResponseEntity departmentDelete(@PathVariable Long id) {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/classRoomSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClassRoom> classRoomSave(@RequestBody ClassRoom classRoom) {
        return new ResponseEntity<>(classRoomService.save(classRoom), HttpStatus.OK);
    }

    @PostMapping(
            value = "/classRoomsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassRoom>> classRoomsSave(@RequestBody List<ClassRoom> classRooms) {
        return new ResponseEntity<>(classRoomService.save(classRooms), HttpStatus.OK);
    }

    @DeleteMapping(value = "/classRoomDelete/{id}")
    public ResponseEntity classRoomDelete(@PathVariable Long id) {
        classRoomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/academicProgramSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcademicProgram> academicProgramSave(@RequestBody AcademicProgram academicProgram) {
        return new ResponseEntity<>(academicProgramService.save(academicProgram), HttpStatus.OK);
    }

    @PostMapping(
            value = "/academicProgramsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AcademicProgram>> academicProgramsSave(@RequestBody List<AcademicProgram> academicPrograms) {
        return new ResponseEntity<>(academicProgramService.save(academicPrograms), HttpStatus.OK);
    }

    @DeleteMapping(value = "/academicProgramDelete/{id}")
    public ResponseEntity academicProgramDelete(@PathVariable Long id) {
        academicProgramService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/degreeSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Degree> degreeSave(@RequestBody Degree degree) {
        return new ResponseEntity<>(degreeService.save(degree), HttpStatus.OK);
    }

    @PostMapping(
            value = "/degreesSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Degree>> degreesSave(@RequestBody List<Degree> degrees) {
        return new ResponseEntity<>(degreeService.save(degrees), HttpStatus.OK);
    }

    @DeleteMapping(value = "/degreeDelete/{id}")
    public ResponseEntity degreeDelete(@PathVariable Long id) {
        degreeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/semesterSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Semester> semesterSave(@RequestBody Semester semester) {
        return new ResponseEntity<>(semesterService.save(semester), HttpStatus.OK);
    }

    @PostMapping(
            value = "/semestersSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Semester>> semestersSave(@RequestBody List<Semester> semesters) {
        return new ResponseEntity<>(semesterService.save(semesters), HttpStatus.OK);
    }

    @DeleteMapping(value = "/semesterDelete/{id}")
    public ResponseEntity semesterDelete(@PathVariable Long id) {
        semesterService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/courseSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> courseSave(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.OK);
    }

    @PostMapping(
            value = "/coursesSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> coursesSave(@RequestBody List<Course> courses) {
        return new ResponseEntity<>(courseService.save(courses), HttpStatus.OK);
    }

    @DeleteMapping(value = "/courseDelete/{id}")
    public ResponseEntity courseDelete(@PathVariable Long id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/promotionSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Promotion> promotionSave(@RequestBody Promotion promotion) {
        return new ResponseEntity<>(promotionService.save(promotion), HttpStatus.OK);
    }

    @PostMapping(
            value = "/promotionsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Promotion>> promotionsSave(@RequestBody List<Promotion> promotions) {
        return new ResponseEntity<>(promotionService.save(promotions), HttpStatus.OK);
    }

    @DeleteMapping(value = "/promotionDelete/{id}")
    public ResponseEntity promotionDelete(@PathVariable Long id) {
        promotionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/legalIdTypeSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LegalIdType> legalIdTypeSave(@RequestBody LegalIdType legalIdType) {
        return new ResponseEntity<>(legalIdTypeService.save(legalIdType), HttpStatus.OK);
    }

    @PostMapping(
            value = "/legalIdTypesSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LegalIdType>> legalIdTypesSave(@RequestBody List<LegalIdType> legalIdTypes) {
        return new ResponseEntity<>(legalIdTypeService.save(legalIdTypes), HttpStatus.OK);
    }

    @DeleteMapping(value = "/legalIdTypeDelete/{id}")
    public ResponseEntity legalIdTypeDelete(@PathVariable Long id) {
        legalIdTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/studentSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> studentSave(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
    }

    @PostMapping(
            value = "/studentsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> studentsSave(@RequestBody List<Student> students) {
        return new ResponseEntity<>(studentService.save(students), HttpStatus.OK);
    }

    @PostMapping(
            value = "/studentsGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Student>> studentsGet(@RequestBody Filter filter) {
        return new ResponseEntity<>(studentService.getStudents(filter), HttpStatus.OK);
    }

    @DeleteMapping(value = "/studentDelete/{id}")
    public ResponseEntity studentDelete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/teacherSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> teacherSave(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.save(teacher), HttpStatus.OK);
    }

    @PostMapping(
            value = "/teachersSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Teacher>> teachersSave(@RequestBody List<Teacher> teachers) {
        return new ResponseEntity<>(teacherService.save(teachers), HttpStatus.OK);
    }

    @PostMapping(
            value = "/teachersGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Teacher>> teachersGet(@RequestBody Filter filter) {
        return new ResponseEntity<>(teacherService.getTeachers(filter), HttpStatus.OK);
    }

    @PostMapping(
            value = "/teachersDepartmentGet/{departmentCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Teacher>> teachersDepartmentGet(@PathVariable String departmentCode) {
        return new ResponseEntity<>(teacherService.getTeachersByDepartmentCode(departmentCode), HttpStatus.OK);
    }

    @DeleteMapping(value = "/teacherDelete/{id}")
    public ResponseEntity teacherDelete(@PathVariable Long id) {
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = "/personsGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Person>> personsGet(@RequestBody Filter filter) {
        return new ResponseEntity<>(personService.getPersons(filter), HttpStatus.OK);
    }

    @PostMapping(
            value = "/personSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personSave(@RequestBody Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.OK);
    }

    @PostMapping(
            value = "/personsSave",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> personsSave(@RequestBody List<Person> persons) {
        return new ResponseEntity<>(personService.save(persons), HttpStatus.OK);
    }


    @DeleteMapping(value = "/personDelete/{id}")
    public ResponseEntity personDelete(@PathVariable Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
        this.promotionStudentService = promotionStudentService;
        this.gradeService = gradeService;
        this.scheduleService = scheduleService;
     */
}
