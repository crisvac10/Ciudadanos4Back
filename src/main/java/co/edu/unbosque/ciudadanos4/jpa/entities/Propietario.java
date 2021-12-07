package co.edu.unbosque.ciudadanos4.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Propietario")
@NamedQueries({
        @NamedQuery(name = "Owner.findByUsername",
                query = "SELECT b FROM Propietario b WHERE b.username = :username"),
        @NamedQuery(name = "Owner.findAll",
                query = "SELECT b FROM Propietario b")
})
@PrimaryKeyJoinColumn
/**
 *  Class for Propietario Users extends UserAppPOJO
 */
public class Propietario extends UserApp {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id", unique = true)
    private Integer personId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @OneToMany(mappedBy = "propietario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mascota> mascotas = new ArrayList<>();

    /**
     * Constructor Method
     */
    public Propietario() {
    }

    /**
     * This is the second method constructor of Case with params
     * @param username: String
     * @param password: String
     * @param email: String
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public Propietario(Integer personId, String username, String password, String email, String role, String name, String address, String neighborhood) {
        super(username, password, email, role);
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<Mascota> getPets() {
        return mascotas;
    }

    public void addPets(Mascota mascota) {
        mascotas.add(mascota);
        mascota.setOwner(this);
    }
}