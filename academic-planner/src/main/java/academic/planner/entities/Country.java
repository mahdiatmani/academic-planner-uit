package academic.planner.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable=false, unique = true)
    private String code;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "rank", nullable=false)
    private String rank;

    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private List<City> cities;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> citys) {
        this.cities = citys;
    }
}
