package com.example.scheduledevelop.users.repository;

import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findByEmail(String email);

    default User findByEmailOrElse(String email) {
        return findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

}
