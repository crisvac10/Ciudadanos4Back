package co.edu.unbosque.ciudadanos4.jpa.repositories;

import co.edu.unbosque.ciudadanos4.jpa.entities.UserApp;

import java.util.Optional;

public interface UserAppRepository {

    Optional<UserApp> findByUsername(String username);

    Optional<UserApp> save(UserApp user);

}