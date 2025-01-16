package com.sweetme.back.auth.repository;

import com.sweetme.back.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);
}
