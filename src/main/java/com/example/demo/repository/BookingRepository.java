package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByGuessIdOrderByCheckInDateDesc(Long guessId);

    List<Booking> findByCheckInDateBeforeAndCheckOutDateIsNull(LocalDateTime time);
}
