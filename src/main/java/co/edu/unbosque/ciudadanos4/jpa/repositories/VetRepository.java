package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Vet;

import java.util.List;
import java.util.Optional;

public interface VetRepository {

    String save(Vet vet);

    Optional<Vet> findByUserName(String username);
    Optional<Vet> findById(Integer username);
    List<Vet> findAll();

    String deleteByUserName(String username);

    String editVet ( String username,String password, String email,String role,Integer vetId, String name, String address, String neighborhood);

}