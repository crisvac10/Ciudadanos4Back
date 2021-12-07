package co.edu.unbosque.ciudadanos4.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "Visit")
@NamedQueries({
        @NamedQuery(name = "Visit.remove",
                query = "DELETE FROM Visit e WHERE e.id = :id")
})
/**
 *  Class for visits to a vet
 */
public class Visit{

    @Id
    @GeneratedValue
    @Column(name = "visit_id")
    private Integer visit_id;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Mascota mascota;


    /**
     * Constructor Method
     * @param createdAt
     * @param type
     * @param description
     * @param vet
     * @param mascota
     */
    public Visit(String createdAt, String type, String description, Vet vet, Mascota mascota) {

    }

    /**
     * Second constructor method of Visit with params
     *
     * @param visit_id:    Integer
     * @param created_id:  String
     * @param type:        String
     * @param description: String
     * @param vet:         Vet
     * @param mascota:         Mascota
     */
    public Visit(Integer visit_id, String created_id, String type, String description, Vet vet, Mascota mascota) {
        this.visit_id = visit_id;
        this.created_at = created_id;
        this.type = type;
        this.description = description;
        this.vet = vet;
        this.mascota = mascota;
    }

    public Integer getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(Integer visit) {
        this.visit_id = visit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_id) {
        this.created_at = created_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Mascota getPet() {
        return mascota;
    }

    public void setPet(Mascota mascota) {
        this.mascota = mascota;
    }
}