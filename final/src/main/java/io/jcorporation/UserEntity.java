package io.jcorporation;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    private String login;

    private String firstName;

    private String lastName;

    @CreatedDate
    private LocalDateTime createdDate;

    private boolean admin;

    public String getLogin() {
        return login;
    }

    public UserEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public UserEntity setCreatedDate(LocalDateTime dateCreated) {
        this.createdDate = dateCreated;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public UserEntity setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }
}
