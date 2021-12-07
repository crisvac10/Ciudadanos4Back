package co.edu.unbosque.ciudadanos4.jpa.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@PrimaryKeyJoinColumn
@Table(name = "Mascota")
@NamedQueries({
        @NamedQuery(name = "Pet.findAll",
                query = "SELECT b FROM Mascota b")
})
/**
 *  Class for Pets
 */
public class Mascota {
    @Id
    @GeneratedValue
    @Column(name = "pet_id", nullable = false)
    private Integer pet_id;

    @Column(name = "microship",unique = true)
    private String microship;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "race")
    private String race;

    @Column(name = "size")
    private String size;

    @Column(name = "sex")
    private String sex;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Propietario propietario;

    @OneToMany(mappedBy = "mascota", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Case> cases = new HashSet<>();

    @OneToMany(mappedBy = "mascota", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

    /**
     * Constructor Method
     * @param pet_id
     * @param microchip
     * @param name
     * @param species
     * @param race
     * @param size
     * @param sex
     * @param picture
     */
    public Mascota(Integer pet_id, String microchip, String name, String species, String race, String size, String sex, String picture){

    }

    /**
     * This is the second method constructor of Mascota with params
     * @param pet_id: Integer
     * @param microship: String
     * @param name: String
     * @param species: String
     * @param race: String
     * @param size: String
     * @param sex: String
     * @param picture: String
     * @param propietario: Propietario
     * @param cases: List
     * @param visits: List
     */
    public Mascota(Integer pet_id, String microship, String name, String species, String race, String size, String sex, String picture, Propietario propietario, Set<Case> cases, Set<Visit> visits) {
        this.pet_id = pet_id;
        this.microship = microship;
        this.name = name;
        this.species = species;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
        this.propietario = propietario;
        this.cases = cases;
        this.visits = visits;
    }

    public Mascota() {

    }

    public Integer getPet_id() {
        return pet_id;
    }

    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
    }

    public String getMicroship() {
        return microship;
    }

    public void setMicroship(String microship) {
        this.microship = microship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Propietario getOwner() {
        return propietario;
    }

    public void setOwner(Propietario propietario_id) {
        this.propietario = propietario_id;
    }

    public Set<Case> getCases() {
        return cases;
    }

    public void addCases(Case aCase) {
        cases.add(aCase);
        aCase.setPet_id(this);

    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void addVisits(Visit visit) {
        this.visits.add(visit);
    }
}