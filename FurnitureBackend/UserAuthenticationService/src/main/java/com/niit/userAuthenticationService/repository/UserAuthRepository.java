package com.niit.userAuthenticationService.repository;

import com.niit.userAuthenticationService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<User, String> {

    User findByUserEmailAndUserPassword(String userEmail, String userPassword);
}
