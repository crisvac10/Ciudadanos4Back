package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Mascota;
import co.edu.unbosque.ciudadanos4.resources.pojos.PetPOJO;

import java.util.List;
import java.util.Optional;

public interface MascotaRepository {

    String save(Mascota mascota);

    Optional<Mascota> findById(Integer id);

    List findAll(Integer petId);

    List<Mascota> findAll();
    List<Mascota> findbyOwner(String username);
    String editPet(PetPOJO petPOJO);
    void deleteById(Integer id);

}