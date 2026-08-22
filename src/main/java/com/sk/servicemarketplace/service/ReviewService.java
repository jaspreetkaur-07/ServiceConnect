package com.sk.servicemarketplace.service;

import com.sk.servicemarketplace.entity.Booking;
import com.sk.servicemarketplace.entity.BookingStatus;
import com.sk.servicemarketplace.entity.Review;
import com.sk.servicemarketplace.repository.BookingRepository;
import com.sk.servicemarketplace.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Review addReview(Long bookingId, Integer rating, String comment) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.COMPLETED) {
            throw new RuntimeException("Can only review completed bookings");
        }

        Review review = new Review();
        review.setBooking(booking);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }
}
