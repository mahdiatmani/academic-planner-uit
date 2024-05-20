package academic.planner.msg;

import academic.planner.entities.Profile;
import academic.planner.entities.Person;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityDTO {

    private String firstname;
    private String lastName;
    private String username;
    private String password;
    private Profile profile;
    private Boolean gender;
    private String email;
    private String thumbnail;
    private Date birthDate;
    private String otp;
    private String token;

    public void init(Person person, String token){
        this.firstname      = person.getFirstName();
        this.lastName       = person.getLastName();
        this.username       = person.getUsername();
        this.profile        = person.getProfile();
        this.gender         = person.getGender();
        this.email          = person.getEmail();
        this.thumbnail      = person.getThumbnail();
        this.birthDate      = person.getBirthDate();
        this.token          = token;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
