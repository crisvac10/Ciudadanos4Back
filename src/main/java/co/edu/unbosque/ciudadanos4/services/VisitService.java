package co.edu.unbosque.ciudadanos4.services;

import co.edu.unbosque.ciudadanos4.jpa.entities.Mascota;
import co.edu.unbosque.ciudadanos4.jpa.entities.Vet;
import co.edu.unbosque.ciudadanos4.jpa.entities.Visit;
import co.edu.unbosque.ciudadanos4.jpa.repositories.*;
import co.edu.unbosque.ciudadanos4.resources.pojos.VisitPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class VisitService {
    private MascotaRepository mascotaRepository;
    private VetRepository vetRepository;
    private VisitRepository visitRepository;

    /**
     * @param visitPOJO visit's pojo
     * @return a string message
     */
    public String saveVisit(VisitPOJO visitPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        mascotaRepository = new MascotaRepositoryImpl(entityManager);
        vetRepository = new VetRepositoryImpl(entityManager);

        // Creating an optional mascota object and find the id of the mascota in the visit's pojo
        List petO = mascotaRepository.findAll(visitPOJO.getPet_id());

        //If the id doesn't exist return false

        if (petO.isEmpty()) return "No existe esa identificación de mascota";

        // Creating an optional vet object and find the id of the vet in the visit's pojo
        Optional<Vet> vetO = vetRepository.findById(visitPOJO.getVet_id());

        //If the id doesn't exist return false
        if (!vetO.isPresent()) return "No existe esa veterinaria";

        Mascota mascota = mascotaRepository.findById(visitPOJO.getPet_id()).get();

        Vet vet = vetRepository.findById(visitPOJO.getVet_id()).get();

        //Validating the format of the date, passing date of string to date

        //Creating the visit and save it in the repository
        Visit visit = new Visit(visitPOJO.getCreated_at(), visitPOJO.getType(), visitPOJO.getDescription(), vet, mascota);

        mascota.addVisits(visit);
        mascotaRepository.save(mascota);

        vet.addVisit(visit);
        vetRepository.save(vet);

        entityManager.close();
        entityManagerFactory.close();
        return "Se ha creado exitosamente la visita!";
    }

    /**
     * Get all the visits of DB
     * @return List of Visit
     */
    public List<VisitPOJO> listVisit(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        List<Visit> visits = visitRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VisitPOJO> visitPOJOS = new ArrayList<>();
        for (Visit visit : visits){

            visitPOJOS.add(new VisitPOJO(visit.getVisit_id(),visit.getCreated_at(),visit.getType(),visit.getDescription(),visit.getVet().getVet_id(),visit.getPet().getPet_id()));
        }
        return visitPOJOS;
    }
}