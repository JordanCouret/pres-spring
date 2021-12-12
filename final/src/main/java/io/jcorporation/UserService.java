package io.jcorporation;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Ou @Component, au final c'est la même
public class UserService implements IUserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    @Transactional
    public void create() {
        Optional<UserEntity> jco = this.userDAO.findById("jco");
        if (jco.isPresent()) {
            throw new RuntimeException("Un utilisateur existe déjà avec l'identifiant " + jco.get().getLogin());
        }

        this.userDAO.save(new UserEntity().setAdmin(false).setFirstName("Jordan").setLastName("CO").setLogin("jco"));
    }
}
