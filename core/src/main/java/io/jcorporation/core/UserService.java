package io.jcorporation.core;


import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserService {

    private final Log log = LogFactory.getLog(getClass());

    private final UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public void create(String login) {
        try {
            if (!this.dao.exist(login)) {
                int nbUpdate = this.dao.create(new UserEntity(login, "Jordan", "CO", LocalDateTime.now(), false));
                if (nbUpdate != 1) {
                    throw new RuntimeException("L'insertion n'a pas réussi");
                }
            } else {
                throw new RuntimeException("Un utilisateur existe déjà pour le login " + login);
            }
        } catch (SQLException e) {
            log.error("Une erreur est survenu pendant le traitement", e);
            throw new RuntimeException("Une erreur est survenu pendant le traitement", e);
        }
    }
}
