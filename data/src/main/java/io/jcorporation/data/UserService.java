package io.jcorporation.data;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void create(String login) throws IOException {
        Optional<UserEntity> user = this.userDAO.findById(login);
        if (user.isPresent()) {
            user.get().setAdmin(true); // TODO: Explication orale
            throw new IOException("Un utilisateur existe déjà avec l'identifiant " + login);
        }
        this.userDAO.save(new UserEntity().setAdmin(false).setFirstName("Jordan").setLastName("CO").setLogin(login));
    }
}
