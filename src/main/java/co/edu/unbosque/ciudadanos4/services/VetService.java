package co.edu.unbosque.ciudadanos4.services;

import co.edu.unbosque.ciudadanos4.jpa.entities.Vet;
import co.edu.unbosque.ciudadanos4.jpa.repositories.UserAppRepository;
import co.edu.unbosque.ciudadanos4.jpa.repositories.VetRepository;
import co.edu.unbosque.ciudadanos4.jpa.repositories.VetRepositoryImpl;
import co.edu.unbosque.ciudadanos4.resources.pojos.VetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * The service of Vet, use Repository of vet
 */
public class VetService {

    VetRepository vetRepository;
    UserAppRepository userAppRepository;

    /**
     * @return List of Vet
     */
    public List<VetPOJO> listVet() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        List<Vet> vets = vetRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VetPOJO> vetPOJOS = new ArrayList<>();
        for (Vet vet : vets) {
            vetPOJOS.add(new VetPOJO(vet.getUsername(), vet.getPassword(), vet.getRole(),
                    vet.getEmail(), vet.getName(),
                    vet.getAddress(),
                    vet.getNeighborhood(),vet.getVet_id()));
        }
        return vetPOJOS;
    }

    /**
     * Save in DB a Vet
     *
     * @param vetPOJO: VetPOJO
     * @return an Optional of POJO
     */
    public String saveVet(VetPOJO vetPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);

        //Optional<UserApp> user = userAppRepository.findByUsername(vetPOJO.getUsername());
        //if (!user.isPresent()) return "The user does not exist";

        Vet vet = new Vet(vetPOJO.getVetId(),vetPOJO.getUsername(), vetPOJO.getPassword(), vetPOJO.getRole(),
                vetPOJO.getEmail(), vetPOJO.getName(),
                vetPOJO.getAddress(),
                vetPOJO.getNeighborhood());
        String reply = vetRepository.save(vet);
        entityManager.close();
        entityManagerFactory.close();
        return reply;
    }

    /**
     * Delete a Vet of the DB
     *
     * @param username: String -> ID to delete a Vet
     */
    public String deleteVet(String username) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        String reply = vetRepository.deleteByUserName(username);

        entityManager.close();
        entityManagerFactory.close();

        return reply;
    }

    /**
     * Edit a Vet of the DB
     *
     * @param username:     String -> ID to delete a Vet
     * @param name:         String
     * @param address:      String
     * @param neighborhood: String
     */
    public String editVet(String username, String password, String email,String role, Integer vetId, String name, String address, String neighborhood) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        String reply = vetRepository.editVet(username, password, email, role,vetId,name,address,neighborhood);

        entityManager.close();
        entityManagerFactory.close();

        return reply;
    }

}