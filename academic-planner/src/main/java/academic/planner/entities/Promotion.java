package academic.planner.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="academic_program_id", foreignKey=@ForeignKey(name="fk_promotion_program_academic"), nullable=false)
    public AcademicProgram academicProgram;

    @ManyToOne
    @JoinColumn(name="semester_id", foreignKey=@ForeignKey(name="fk_promotion_semester"), nullable=false)
    private Semester actualSemester;

    @Column(name = "start_year", nullable=false)
    private Date startYear;

    @Column(name = "end_year", nullable=false)
    private Date endYear;

    //--------------------------------------------------------------------------------
    // GETTER AND SETTERS
    //--------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public Semester getActualSemester() {
        return actualSemester;
    }

    public void setActualSemester(Semester actualSemester) {
        this.actualSemester = actualSemester;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }
}
