package com.sk.servicemarketplace.controller;

import com.sk.servicemarketplace.entity.Booking;
import com.sk.servicemarketplace.entity.BookingStatus;
import com.sk.servicemarketplace.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Map<String, Object> request, Authentication authentication) {
        try {
            String customerEmail = authentication.getName();
            Long workerId = Long.valueOf(request.get("workerId").toString());
            String serviceType = request.get("serviceType").toString();
            String address = request.get("address").toString();

            Booking booking = bookingService.createBooking(customerEmail, workerId, serviceType, address);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            BookingStatus status = BookingStatus.valueOf(request.get("status").toUpperCase());
            Booking booking = bookingService.updateStatus(id, status);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<?> getMyBookingsAsCustomer(Authentication authentication) {
        List<Booking> bookings = bookingService.getCustomerBookings(authentication.getName());
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/assigned-to-me")
    public ResponseEntity<?> getMyBookingsAsWorker(Authentication authentication) {
        List<Booking> bookings = bookingService.getWorkerBookings(authentication.getName());
        return ResponseEntity.ok(bookings);
    }
}
