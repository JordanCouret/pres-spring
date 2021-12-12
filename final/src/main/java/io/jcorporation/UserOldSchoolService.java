package io.jcorporation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class UserOldSchoolService implements IUserService {

    @Autowired
    private UserDAO userDAO;

    public void create() {
        Optional<UserEntity> jco = this.userDAO.findById("jco");
        if (jco.isPresent()) {
            throw new RuntimeException("Un utilisateur existe déjà avec l'identifiant " + jco.get().getLogin());
        }

        this.userDAO.save(new UserEntity().setAdmin(false).setFirstName("Jordan").setLastName("CO").setLogin("jco"));
    }
}
