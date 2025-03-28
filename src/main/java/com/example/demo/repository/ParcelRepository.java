package com.example.demo.repository;

import com.example.demo.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    List<Parcel> findByGuessIdAndAvailableTrue(Long guessId);
}
