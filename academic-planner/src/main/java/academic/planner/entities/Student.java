package academic.planner.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "student")
public class Student extends Person {

    @Column(name = "student_national_code", unique = true)
    private String studentNationalCode;

    @Column(name = "apogee_code", unique = true)
    private String apogeeCode;

    //--------------------------------------------------------------------------------
    // GETTER AND SETTERS
    //--------------------------------------------------------------------------------

    public String getStudentNationalCode() {
        return studentNationalCode;
    }

    public void setStudentNationalCode(String studentNationalCode) {
        this.studentNationalCode = studentNationalCode;
    }

    public String getApogeeCode() {
        return apogeeCode;
    }

    public void setApogeeCode(String apogeeCode) {
        this.apogeeCode = apogeeCode;
    }

}
