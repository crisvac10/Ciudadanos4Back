package co.edu.unbosque.ciudadanos4.services;

import co.edu.unbosque.ciudadanos4.jpa.entities.Propietario;
import co.edu.unbosque.ciudadanos4.jpa.repositories.PRepository;
import co.edu.unbosque.ciudadanos4.jpa.repositories.PRepositoryImpl;
import co.edu.unbosque.ciudadanos4.resources.pojos.OwnerPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class PService {

    PRepository pRepository;

    public String createOwner(OwnerPOJO ownerPOJO) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        pRepository = new PRepositoryImpl(entityManager);

        Propietario propietario = new Propietario(ownerPOJO.getPersonId(),
                ownerPOJO.getUsername(),
                ownerPOJO.getPassword(),
                ownerPOJO.getEmail(),
                ownerPOJO.getRole(),
                ownerPOJO.getName(),
                ownerPOJO.getAddress(),
                ownerPOJO.getNeighborhood());
        String reply = pRepository.save(propietario);

        entityManager.close();
        entityManagerFactory.close();

        return reply;

    }

    public List<OwnerPOJO> listOwners() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        pRepository = new PRepositoryImpl(entityManager);
        List<Propietario> propietarios = pRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OwnerPOJO> ownerPOJOS = new ArrayList<>();
        for (Propietario propietario : propietarios) {
            ownerPOJOS.add(new OwnerPOJO(propietario.getPersonId(),
                    propietario.getUsername(),
                    propietario.getPassword(),
                    propietario.getEmail(),
                    propietario.getRole(),
                    propietario.getName(),
                    propietario.getAddress(),
                    propietario.getNeighborhood()));
        }
        return ownerPOJOS;
    }

    public Optional<OwnerPOJO> findByUserName(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        pRepository = new PRepositoryImpl(entityManager);
        Optional<Propietario> owner = pRepository.findByUsername(username);

        entityManager.close();
        entityManagerFactory.close();

        if (owner.isPresent()) {
            return Optional.of(new OwnerPOJO(
                    owner.get().getPersonId(),
                    owner.get().getUsername(),
                    owner.get().getPassword(),
                    owner.get().getEmail(),
                    owner.get().getRole(),
                    owner.get().getName(),
                    owner.get().getAddress(),
                    owner.get().getNeighborhood()));
        } else {
            System.out.println("non-existent owner");
            return Optional.empty();
        }
    }

    public String editOwner(OwnerPOJO ownerPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        pRepository = new PRepositoryImpl(entityManager);
        String reply = pRepository.editOwner(
                ownerPOJO.getUsername(),
                ownerPOJO.getPassword(),
                ownerPOJO.getEmail(),
                ownerPOJO.getPersonId(),
                ownerPOJO.getName(),
                ownerPOJO.getAddress(),
                ownerPOJO.getNeighborhood());
        entityManager.close();
        entityManagerFactory.close();
        return reply;

    }

    public String deleteOwner(String username) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        pRepository = new PRepositoryImpl(entityManager);
        String reply =  pRepository.deleteByUsername(username);
        entityManager.close();
        entityManagerFactory.close();
        return reply;
    }


}