package co.edu.unbosque.ciudadanos4.services;

import co.edu.unbosque.ciudadanos4.jpa.entities.Mascota;
import co.edu.unbosque.ciudadanos4.jpa.repositories.*;
import co.edu.unbosque.ciudadanos4.resources.pojos.PetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MascotaService<fin> {

    private MascotaRepository mascotaRepository;
    private PRepository pRepository;
    private VisitRepository visitRepository;
    private CaseRepository caseRepository;

    /**
     * Method that saves a pet
     *
     * @param petPOJO: pet's pojo
     * @return a message
     */
    public String savePet(PetPOJO petPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        mascotaRepository = new MascotaRepositoryImpl(entityManager);
        pRepository = new PRepositoryImpl(entityManager);
        //Create an owner optional object an save the id that entry
        //  Optional<Propietario> owner = pRepository.findByUsername(petPOJO.get);

        //If the owner with the id doesn't exists return a message
        //  if (!owner.isPresent()) return "The username of the owner entered does not exist!";

        List<Mascota> mascotaList = mascotaRepository.findAll();
        //If the owner with the id exist, scroll through the mascota list to verify that the microchip does not exist
        for (int i = 0; i < mascotaList.size(); i++) {
            if (mascotaList.get(i).getMicroship() != null && mascotaList.get(i).getMicroship().equals(petPOJO.getMicrochip()))
                return "The entered microchip already exists";
        }
        //If the microchip doesn't exist save the mascota in the pojo and save the mascota in the owner
        Mascota mascota = new Mascota(
                petPOJO.getPet_id(),
                petPOJO.getMicrochip(),
                petPOJO.getName(),
                petPOJO.getSpecies(),
                petPOJO.getRace(),
                petPOJO.getSize(),
                petPOJO.getSex(),
                petPOJO.getPicture());
        //owner.get().addPet(mascota);

        //   pRepository.save(owner.get());

        entityManager.close();
        entityManagerFactory.close();
        return "Mascota has been successfully registered!";
    }

    /**
     * Method that modify the data of a petPojo
     *
     * @param petPOJO pet's pojo
     * @return string message
     */
    public String modifyPet(PetPOJO petPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES_256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        mascotaRepository = new MascotaRepositoryImpl(entityManager);

        List<Mascota> mascotaList = mascotaRepository.findAll();
        //If the owner with the id exist, scroll through the pet list to verify that the microchip does not exist
        for (int i = 0; i < mascotaList.size(); i++) {
            if (mascotaList.get(i).getMicroship() != null && mascotaList.get(i).getMicroship().equals(petPOJO.getMicrochip()))
                return "The entered microchip already exists";
        }
        String message = mascotaRepository.editPet(petPOJO);

        entityManager.close();
        entityManagerFactory.close();
        return message;
    }
    public List<PetPOJO> listPetsByOwner(String username) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        mascotaRepository = new MascotaRepositoryImpl(entityManager);

        List<Mascota> mascota = mascotaRepository.findbyOwner(username);

        entityManager.close();
        entityManagerFactory.close();

        List<PetPOJO> petPOJOS = new ArrayList<>();
        for (Mascota mascota1 : mascota) {
            petPOJOS.add(new PetPOJO(mascota1.getPet_id(), mascota1.getMicroship(), mascota1.getName(), mascota1.getSpecies(), mascota1.getRace(), mascota1.getSize(), mascota1.getSex(), mascota1.getPicture(), mascota1.getOwner().getPersonId()));
        }
        return petPOJOS;
    }
}