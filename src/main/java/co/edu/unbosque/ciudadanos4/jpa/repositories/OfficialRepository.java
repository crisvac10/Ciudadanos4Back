package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Official;

import java.util.List;

public interface OfficialRepository {

    String save(Official oficial);

    List<Official> listAll();

    List<Official> getByUsername(String username);

}