package com.example.scheduledevelop.users.repository;

import com.example.scheduledevelop.entity.User;
import com.example.scheduledevelop.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *  유저 정보를 저장하는 JpaRepository 입니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 유저 식별자를 통해 유저를 조회하고, 존재하지 않으면 예외가 발생합니다.
     * @param userId    조회하고자 하는 유저 식별자
     * @return          조회된 유저의 정보
     */
    default User findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(UserNotFoundException::new);
    }

    /**
     * 이메일을 통한 유저 조회입니다.
     * @param email 조회하고자 하는 유저의 이메일
     * @return      조회된 유저의 정보
     */
    Optional<User> findByEmail(String email);

    /**
     * 이메일을 통한 유저 조회, 존재하지 않으면 예외가 발생합니다.
     * @param email 조회하고자 하는 유저의 이메일
     * @return      조회된 유저의 정보
     */
    default User findByEmailOrElse(String email) {
        return findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

}
