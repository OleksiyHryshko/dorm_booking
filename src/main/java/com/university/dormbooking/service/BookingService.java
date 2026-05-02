package com.university.dormbooking.service;


import com.university.dormbooking.Entities.Booking;
import com.university.dormbooking.Entities.Room;
import com.university.dormbooking.Entities.User;
import com.university.dormbooking.repository.BookingRepository;
import com.university.dormbooking.repository.RoomRepository;
import com.university.dormbooking.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public Booking bookRoom(Long userId, Long roomId, LocalDate checkIn, LocalDate checkOut) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        BigDecimal price = room.getPricePerMonth();

        if(user.getBalance().compareTo(price) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance().subtract(price));
        userRepository.save(user);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setStatus("ACTIVE");
        return bookingRepository.save(booking);

    }
}
