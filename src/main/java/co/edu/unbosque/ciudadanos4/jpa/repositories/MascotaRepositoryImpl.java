package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Mascota;
import co.edu.unbosque.ciudadanos4.resources.pojos.PetPOJO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MascotaRepositoryImpl implements MascotaRepository {

    private EntityManager entityManager;

    public MascotaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String save(Mascota mascota) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(mascota);
            entityManager.getTransaction().commit();
            return "se ha registrado correctamente";
        } catch (Exception e) {
            return "Ha ocurrido un error al registrar la mascota!";
        }
    }
    @Override
    public List<Mascota> findbyOwner(String username) {
        return entityManager.createQuery("SELECT p FROM Mascota p WHERE p.owner.username LIKE :userParam")
                .setParameter("userParam", username).getResultList();
    }
    @Override
    public Optional<Mascota> findById(Integer id) {
        Mascota mascota = entityManager.find(Mascota.class, id);
        return mascota != null ? Optional.of(mascota) : Optional.empty();
    }

    @Override
    public List findAll(Integer petId) {
        return entityManager.createQuery("from Mascota").getResultList();
    }

    @Override
    public List<Mascota> findAll() {
        return entityManager.createQuery("from Mascota").getResultList();
    }

    @Override
    public String editPet(PetPOJO petPOJO) {
        entityManager.getTransaction().begin();

        Optional<Mascota> pet = this.findById(petPOJO.getPet_id());
        if (!pet.isPresent()) return "The pet with the entered id does not exist!";
        pet.get().setMicroship(petPOJO.getMicrochip());
        pet.get().setName(petPOJO.getName());
        pet.get().setSpecies(petPOJO.getSpecies());
        pet.get().setRace(petPOJO.getRace());
        pet.get().setSize(petPOJO.getSize());
        pet.get().setSex(petPOJO.getSex());
        pet.get().setPicture(petPOJO.getPicture());

        entityManager.getTransaction().commit();

        return "It has been successfully modified!";
    }


    @Override
    public void deleteById(Integer id) {
        Mascota mascota = entityManager.find(Mascota.class, id);
        if (mascota != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(mascota);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}