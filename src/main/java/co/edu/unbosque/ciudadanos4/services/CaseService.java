 package co.edu.unbosque.ciudadanos4.services;

import co.edu.unbosque.ciudadanos4.jpa.entities.Case;
import co.edu.unbosque.ciudadanos4.jpa.entities.Mascota;
import co.edu.unbosque.ciudadanos4.jpa.repositories.CaseRepository;
import co.edu.unbosque.ciudadanos4.jpa.repositories.CaseRepositoryImpl;
import co.edu.unbosque.ciudadanos4.jpa.repositories.MascotaRepository;
import co.edu.unbosque.ciudadanos4.jpa.repositories.MascotaRepositoryImpl;
import co.edu.unbosque.ciudadanos4.resources.pojos.CasePOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @Stateless
    public class CaseService {
        MascotaRepository mascotaRepository;
        CaseRepository caseRepository;


        public String createCase(CasePOJO casePOJO){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            mascotaRepository = new MascotaRepositoryImpl(entityManager);

            Optional<Mascota> pet = mascotaRepository.findById(casePOJO.getPet_id());
            if (!pet.isPresent()) return "The pet does not exist";
            Case aCase = new Case(
                    casePOJO.getCreated_at(),
                    casePOJO.getType(),
                    casePOJO.getDescription());
            pet.get().addCases(aCase);
            mascotaRepository.save(pet.get());
            entityManager.close();
            entityManagerFactory.close();

            return "The caase was successfully created";

        }

        public Optional<CasePOJO> findCase(Integer id){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            caseRepository = new CaseRepositoryImpl(entityManager);
            Optional<Case> aCase = caseRepository.findById(id);
            System.out.println("case find id -- > " + aCase);
            entityManager.close();
            entityManagerFactory.close();

            if(aCase.isPresent()){
                return Optional.of(new CasePOJO(aCase.get().getCase_id(),aCase.get().getCreated_at(),aCase.get().getType(),aCase.get().getDescription(),aCase.get().getPet_id().getPet_id()));
            }
            return Optional.empty();
        }

        public List<CasePOJO> ListCases(){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            caseRepository = new CaseRepositoryImpl(entityManager);
            List<Case> cases = caseRepository.findAll();
            entityManager.close();
            entityManagerFactory.close();

            List<CasePOJO> casePOJOS = new ArrayList<>();
            for(Case cas : cases){
                casePOJOS.add(new CasePOJO(cas.getCase_id(),cas.getCreated_at(),cas.getType(),cas.getDescription(),cas.getPet_id().getPet_id()));
            }
            return casePOJOS;
        }

        public String editCase(Integer id, String created_at, String type, String description){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            caseRepository = new CaseRepositoryImpl(entityManager);

            String reply = caseRepository.editCase(id,created_at,type,description);

            entityManager.close();
            entityManagerFactory.close();

            return reply;
        }

        public String deleteCase(Integer id){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            caseRepository = new CaseRepositoryImpl(entityManager);

            String reply = caseRepository.deleteById(id);

            entityManager.close();
            entityManagerFactory.close();

            return  reply;
        }

    }

