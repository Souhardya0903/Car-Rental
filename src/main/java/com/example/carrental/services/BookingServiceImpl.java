package com.example.carrental.services;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.dto.BookingResponse;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Car;
import com.example.carrental.entities.Users;
import com.example.carrental.exceptions.BookingNotFoundException;
import com.example.carrental.exceptions.CarNotFoundException;
import com.example.carrental.exceptions.UsersNotFoundException;
import com.example.carrental.repositories.BookingRepository;
import com.example.carrental.repositories.CarRepository;
import com.example.carrental.repositories.UserRepository;
import com.example.carrental.util.BookingsConverter;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Users user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new UsersNotFoundException("Wrong user id entered"));
        Car car = carRepository.findById(bookingRequest.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Could not find requested car"));

        long numberOfDays = ChronoUnit.DAYS.between(bookingRequest.getPickupDate(), bookingRequest.getDropoffDate());
        if (numberOfDays == 0) {
            numberOfDays = 1;
        }
        double totalPrice = car.getPrice() * numberOfDays;

        Booking booking = BookingsConverter.ConvertBookingRequestToBooking(bookingRequest, car, user);
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus("Processing");

        Booking savedBooking = bookingRepository.save(booking);

        return convertToResponse(savedBooking);
    }

    @Override
    public BookingResponse getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Could not find the booking with id:" + bookingId));
        return convertToResponse(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Could not find the booking with id:" + bookingId));
        booking.setBookingStatus("Cancelled");
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingResponse> getBookingsByUsersId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUsersId(userId);
        return bookings.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    private BookingResponse convertToResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setPickupDate(booking.getPickupDate());
        response.setDropoffDate(booking.getDropoffDate());
        response.setTotalPrice(booking.getTotalPrice());
        response.setBookingStatus(booking.getBookingStatus());

        response.setCarId(booking.getCar().getId());
        response.setCarMake(booking.getCar().getMake());
        response.setCarModel(booking.getCar().getModel());

        response.setUserId(booking.getUsers().getId());
        response.setUserName(booking.getUsers().getFirstName()); // Or getFullName() if you have it

        return response;
    }
}
