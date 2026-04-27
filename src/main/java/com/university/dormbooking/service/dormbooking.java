package com.university.dormbooking.service;


import com.university.dormbooking.Entities.Booking;
import com.university.dormbooking.Entities.User;
import com.university.dormbooking.repository.BookingRepository;
import com.university.dormbooking.repository.RoomRepository;
import com.university.dormbooking.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class dormbooking {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public Booking createBooking(Long userId, Long roomId, LocalDate checkIn, LocalDate checkOut) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.save(dormbooking);
    }
}
