package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    /**
     * Custom method definition for retrieving messages based on account id
     * @param id
     * @return a list of message objects
     */
    public List<Message> findAllByAccountId(int accountId);


}
