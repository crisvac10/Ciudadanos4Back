package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.Visit;

import java.util.List;

public interface VisitRepository {

    String save(Visit visit);


    List<Visit> findAll();


}