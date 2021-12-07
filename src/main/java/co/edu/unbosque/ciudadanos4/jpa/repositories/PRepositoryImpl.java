package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Propietario;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/*
    This class, save, fynd by id, find all, delete by id and edit an Propietario from the Database
 */
public class PRepositoryImpl implements PRepository {

    private EntityManager entityManager;

    public PRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String save(Propietario propietario) {//Save an propietario
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(propietario);
            entityManager.getTransaction().commit();
            return "It was successfully saved by the propietario";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "The propietario could not be saved";
    }

    @Override
    public Optional<Propietario> findByUsername(String Username) { //Find by id an Propietario
        Propietario propietario = entityManager.find(Propietario.class, Username);
        return propietario != null ? Optional.of(propietario) : Optional.empty();
    }

    @Override
    public List<Propietario> findAll() { //Find all the Owners
        return entityManager.createQuery("from Propietario").getResultList();
    }

    @Override
    public String deleteByUsername(String  Username) { //Delete by id an propietario
        Propietario propietario = entityManager.find(Propietario.class, Username);
        if (propietario != null) {
            try {

                entityManager.getTransaction().begin();

                propietario.getPets().forEach(pet -> {
                    entityManager.remove(pet);
                });

                entityManager.remove(propietario);
                entityManager.getTransaction().commit();
                return "propietario was successfully removed";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "the user could not be removed";
    }

    @Override
    //Edit an Propietario of the DataBase
    public String editOwner(String username, String password, String email, Integer personId, String name, String adress, String neighborhood) {
        Propietario propietario = entityManager.find(Propietario.class, username);
        if (propietario != null) {
            try {
                entityManager.getTransaction().begin();
                propietario.setName(name);
                propietario.setAddress(adress);
                propietario.setNeighborhood(neighborhood);
                entityManager.getTransaction().commit();
                return  "successful propietario edit";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "the edition is not completed";
    }
}