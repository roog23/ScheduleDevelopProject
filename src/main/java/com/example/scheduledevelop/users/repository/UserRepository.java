package com.example.scheduledevelop.users.repository;

import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.UserNotFountException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(UserNotFountException::new);
    }
}
