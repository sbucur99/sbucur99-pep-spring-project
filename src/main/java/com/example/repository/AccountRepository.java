package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    /**
     * Custom method definition for checking whether account username exist
     * @param username
     * @return an Account object wrapped inside an Optional or an empty Optional
     */
    Optional<Account> findByUsername(String username);

    /**
     * Custom method definition for finding user account
     * @param username
     * @param password
     * @return an Account object wrapped inside an Optional or an empty Optional
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
