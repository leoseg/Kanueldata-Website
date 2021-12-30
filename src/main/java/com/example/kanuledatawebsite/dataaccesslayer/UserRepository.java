package com.example.kanuledatawebsite.dataaccesslayer;

import com.example.kanuledatawebsite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByuserName(String username);
}
