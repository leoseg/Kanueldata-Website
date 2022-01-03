package com.example.kanuledatawebsite.dataaccesslayer;

import com.example.kanuledatawebsite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Extends the jpa repository to access the user entity in database
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * Query the user entry corresponding to the username
     * @param username name of user
     * @return User object
     */
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByuserName(String username);
}
