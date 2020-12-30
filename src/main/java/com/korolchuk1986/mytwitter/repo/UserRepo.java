package com.korolchuk1986.mytwitter.repo;

import com.korolchuk1986.mytwitter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
