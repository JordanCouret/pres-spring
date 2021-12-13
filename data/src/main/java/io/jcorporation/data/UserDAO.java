package io.jcorporation.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface UserDAO extends JpaRepository<UserEntity, String> {

}
