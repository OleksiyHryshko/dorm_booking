package com.university.dormbooking.controller;


import com.university.dormbooking.entity.Booking;
import com.university.dormbooking.dto.BookingRequest;
import com.university.dormbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService dormBooking;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try{
            Booking newBooking = dormBooking.bookRoom(
                    request.getUserId(),
                    request.getRoomId(),
                    request.getCheckInDate(),
                    request.getCheckOutDate()
            );
            return ResponseEntity.ok(newBooking);

        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
