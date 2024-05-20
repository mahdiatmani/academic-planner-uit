package academic.planner.entities;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private Long id;

    @Column(name = "code", nullable=false)
    private String code;

    @Column(name = "title", nullable=false)
    private String title;

    @Lob
    @Column(name = "description", nullable=false)
    private String description;

    @Column(name = "credit_hours", nullable=false)
    private Integer creditHours;

    @Column(name = "coefficient")
    private Integer coefficient = 1;

    @Column(name = "hold", nullable=false)
    private Boolean hold = false;

    @ManyToOne
    @JoinColumn(name="teacher_id", foreignKey=@ForeignKey(name="fk_course_teacher"), nullable=false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="academic_program_id", foreignKey=@ForeignKey(name="fk_course_academic_program"), nullable=false)
    private AcademicProgram academicProgram;

    @ManyToOne
    @JoinColumn(name="semester_id", foreignKey=@ForeignKey(name="fk_course_semester"), nullable=false)
    private Semester semester;

    //--------------------------------------------------------------------------------
    // GETTER AND SETTERS
    //--------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Integer getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(Integer creditHours) {
        this.creditHours = creditHours;
    }

    public Boolean getHold() {
        return hold;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public void setHold(Boolean hold) {
        this.hold = hold;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

}