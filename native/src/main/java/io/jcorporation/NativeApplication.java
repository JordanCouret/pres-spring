package io.jcorporation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class NativeApplication {

    private static String dbUrl = "jdbc:postgresql://localhost:6789/postgres";
    private static String dbUsername = "postgres";
    private static String dbPassword = "postgres";

    public static Connection getConnection() throws SQLException {
        try {
            // Check si le driver est dans le class path
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    public static void main(String[] args) {
        String login = Arrays.stream(args).findFirst().orElseThrow(() -> new RuntimeException("Pas de login en argument de la fonction"));

        try (Connection co = getConnection()) {
            // Mode transaction
            co.setAutoCommit(false);

            // Vérifie si l'utilisateur existe
            PreparedStatement findById = co.prepareStatement("SELECT login FROM user_entity WHERE login = ?");
            findById.setString(1, login);
            ResultSet resultSet = findById.executeQuery();

            // Si il a un resultat
            if (!resultSet.isBeforeFirst() ) {
                PreparedStatement create = co.prepareStatement("INSERT INTO user_entity(login, admin, created_date, first_name, last_name) VALUES (?, ?, ?, ?, ?)");
                create.setString(1, login);
                create.setBoolean(2, false);
                create.setDate(3, new Date(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
                create.setString(4, "Jordan");
                create.setString(5, "CO");
                create.executeUpdate();

                // Commit de la transaction
                co.commit();
            } else {
                co.rollback();
                throw new RuntimeException("Un utilisateur existe déjà pour le login " + login);
            }
        } catch (SQLException throwables) {
            System.out.println("Exception durant l'ouverture de la connexion à la BDD = " + throwables.getMessage());
        }
    }

}
