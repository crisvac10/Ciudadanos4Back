package co.edu.unbosque.ciudadanos4.services;

import co.edu.unbosque.ciudadanos4.jpa.entities.UserApp;
import co.edu.unbosque.ciudadanos4.jpa.repositories.UserAppRepository;
import co.edu.unbosque.ciudadanos4.jpa.repositories.UserAppRepositoryImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class UserAppService {

    UserAppRepository userAppRepository;

    public Optional<String> validateUser( String username, String password ) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Getting credentials from the database
        userAppRepository = new UserAppRepositoryImpl(entityManager);
        System.out.println("Bien entity ");
        Optional<UserApp> user = userAppRepository.findByUsername(username);
        System.out.println("Bien userx  ");
        entityManager.close();
        entityManagerFactory.close();

        // Validating if credentials provided by the user are valid
        // If success, return the user role
        if (user.isPresent()) {
            if (user.get().getUsername().equals(username) && user.get().getPassword().equals(password)) {
                return Optional.of(user.get().getRole());
            }
        }

        return Optional.empty();

    }

}
