package com.example.demo.repository;

import com.example.demo.model.Guess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuessRepository extends JpaRepository<Guess, Long> {

    // find by idCard
    Guess findByIdentity(String identity);

}