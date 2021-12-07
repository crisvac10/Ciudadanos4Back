package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Case;

import java.util.List;
import java.util.Optional;

public interface CaseRepository {
    String save(Case aCase);

    Optional<Case> findById(Integer id);

    List<Case> findAll();

    String deleteById(Integer id);

    String editCase(Integer id, String created_at, String type, String description);
}
