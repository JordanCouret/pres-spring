package io.jcorporation.core;

import java.time.LocalDateTime;

/**
 * POJO: Représenation de ma table SQL
 */
public class UserEntity {
    private String login;

    private String firstName;

    private String lastName;

    private LocalDateTime createdDate;

    private boolean admin;

    public UserEntity(String login, String firstName, String lastName, LocalDateTime createdDate, boolean admin) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", createdDate=" + createdDate +
            ", admin=" + admin +
            '}';
    }
}
