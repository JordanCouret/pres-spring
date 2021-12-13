package io.jcorporation.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneOffset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;

public class UserDAO implements DisposableBean {
    private final Log log = LogFactory.getLog(getClass());

    private static String dbUrl = "jdbc:postgresql://localhost:6789/postgres";
    private static String dbUsername = "postgres";
    private static String dbPassword = "postgres";

    private Connection connection;

    public UserDAO() {

    }

    public boolean exist(String id) throws SQLException {
        // Vérifie si l'utilisateur existe
        PreparedStatement findById = connection.prepareStatement("SELECT login FROM user_entity WHERE login = ?");
        findById.setString(1, id);
        boolean exist = findById.executeQuery().isBeforeFirst();
        log.info(String.format("Vérifie si l'utilisateur %s = existe (%b)", id, exist));
        return exist;
    }

    public int create(UserEntity entity) throws SQLException {
        // Vérifie si l'utilisateur existe
        PreparedStatement create = connection.prepareStatement(
            "INSERT INTO user_entity(login, admin, created_date, first_name, last_name) VALUES (?, ?, ?, ?, ?)");
        create.setString(1, entity.getLogin());
        create.setBoolean(2, entity.isAdmin());
        create.setDate(3, new Date(entity.getCreatedDate().toInstant(ZoneOffset.UTC).toEpochMilli()));
        create.setString(4, entity.getFirstName());
        create.setString(5, entity.getLastName());
        log.info(String.format("Création de l'utilisateur %s", entity));
        return create.executeUpdate();
    }

    public void destroy() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public void initConnection() throws SQLException {
        try {
            // Check si le driver est dans le class path
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        this.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        this.connection.commit();
        log.info("Commit de la transaction");
    }

    public void rolllback() throws SQLException {
        this.connection.rollback();
        log.info("Rollback de la transaction");
    }
}
