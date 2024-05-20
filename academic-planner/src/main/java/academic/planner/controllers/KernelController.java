package academic.planner.controllers;

import academic.planner.entities.*;
import academic.planner.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kernel")
public class KernelController {

    private final CountryService              countryService;
    private final CityService                 cityService;
    private final UniversityService           universityService;
    private final EstablishmentService        establishmentService;
    private final DepartmentService           departmentService;
    private final ClassRoomService            classRoomService;
    private final DegreeService               degreeService;
    private final AcademicProgramService      academicProgramService;
    private final PromotionService            promotionService;
    private final SemesterService             semesterService;
    private final CourseService               courseService;
    private final LegalIdTypeService          legalIdTypeService;
    private final ProfileService                profileService;
    private final StudentService              studentService;
    private final RegistrationService registrationService;
    private final GradeService                gradeService;
    private final ScheduleService             scheduleService;

    @Autowired
    public KernelController(CountryService countryService, CityService cityService, UniversityService universityService,
                            EstablishmentService establishmentService, DepartmentService departmentService, ClassRoomService classRoomService,
                            DegreeService degreeService, AcademicProgramService academicProgramService, PromotionService promotionService,
                            SemesterService semesterService, CourseService courseService, LegalIdTypeService legalIdTypeService, ProfileService profileService,
                            StudentService studentService, RegistrationService registrationService, GradeService gradeService, ScheduleService scheduleService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.universityService = universityService;
        this.establishmentService = establishmentService;
        this.departmentService = departmentService;
        this.classRoomService = classRoomService;
        this.degreeService = degreeService;
        this.academicProgramService = academicProgramService;
        this.promotionService = promotionService;
        this.semesterService = semesterService;
        this.courseService = courseService;
        this.legalIdTypeService = legalIdTypeService;
        this.profileService = profileService;
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.gradeService = gradeService;
        this.scheduleService = scheduleService;
    }

    @GetMapping(
            value = "/countriesGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> countryGet() {
        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/countryGet/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> countriesGet(@PathVariable String code) {
        return new ResponseEntity<>(countryService.getByCode(code), HttpStatus.OK);
    }

    @GetMapping(
            value = "/universityGet/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<University> universityGet(@PathVariable String code) {
        return new ResponseEntity<>(universityService.getByCode(code), HttpStatus.OK);
    }

    @GetMapping(
            value = "/legalIdTypesGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LegalIdType>> legalIdTypesGet() {
        return new ResponseEntity<>(legalIdTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/profilesGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> profilesGet() {
        return new ResponseEntity<>(profileService.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/degreesGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Degree>> degreesGet() {
        return new ResponseEntity<>(degreeService.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/semestersGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Semester>> semestersGet() {
        return new ResponseEntity<>(semesterService.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/establishmentsGet",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Establishment>> establishmentSave() {
        return new ResponseEntity<>(establishmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping(
            value = "/establishmentByCodeGet/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Establishment> establishmentByCodeGet(@PathVariable String code) {
        return new ResponseEntity<>(establishmentService.getByCode(code), HttpStatus.OK);
    }

    @GetMapping(
            value = "/departmentByCodeGet/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> departmentByCodeGet(@PathVariable String code) {
        return new ResponseEntity<>(departmentService.getByCode(code), HttpStatus.OK);
    }

    @GetMapping(
            value = "/departmentsGet/{establishmentCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> departmentsGet(@PathVariable String establishmentCode) {
        return new ResponseEntity<>(departmentService.getByEstablishmentCode(establishmentCode), HttpStatus.OK);
    }

    @GetMapping(
            value = "/academicProgramByCodeGet/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcademicProgram> academicProgramByCodeGet(@PathVariable String code) {
        return new ResponseEntity<>(academicProgramService.getByCode(code), HttpStatus.OK);
    }
    @GetMapping(
            value = "/academicProgramsGet/{departmentCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AcademicProgram>> academicProgramsGet(@PathVariable String departmentCode) {
        return new ResponseEntity<>(academicProgramService.getByDepartmentCode(departmentCode), HttpStatus.OK);
    }

    @GetMapping(
            value = "/courseByCodeGet/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> coursesByCodeGet(@PathVariable String code) {
        return new ResponseEntity<>(courseService.getByCode(code), HttpStatus.OK);
    }
    @GetMapping(
            value = "/coursesGet/{academicProgramCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> coursesGet(@PathVariable String academicProgramCode) {
        return new ResponseEntity<>(courseService.getByAcademicProgramCode(academicProgramCode), HttpStatus.OK);
    }

    @GetMapping(
            value = "/classRoomsGet/{establishmentCode}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassRoom>> classRoomsGet(@PathVariable String establishmentCode) {
        return new ResponseEntity<>(classRoomService.getByEstablishmentCode(establishmentCode), HttpStatus.OK);
    }

}
