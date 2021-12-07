package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Propietario;

import java.util.List;
import java.util.Optional;

public interface PRepository {

    String save(Propietario propietario);

    Optional<Propietario> findByUsername(String Username);

    List<Propietario> findAll();

    String deleteByUsername(String username);

    String editOwner (String username, String password, String email, Integer personId, String name, String adress,String neighborhood);
}