package com.example.fms.implementation;


    import com.example.fms.entity.BookingMaster;
    import com.example.fms.repository.IBookingRepository;
    import com.example.fms.service.BookingService;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.junit.jupiter.MockitoExtension;

    import java.util.Collections;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.junit.jupiter.api.Assertions.assertTrue;
    import static org.mockito.ArgumentMatchers.any;

    @ExtendWith(MockitoExtension.class)
    public class BookingImplementationTest {
        @InjectMocks
        private BookingService bookingService;

        @Mock
        private IBookingRepository bookingRepository;

        @Test
        void testBookFlight_Success() {
            BookingMaster booking = new BookingMaster();
            booking.setCustomerId(1L);

            Mockito.when(bookingRepository.save(any(BookingMaster.class))).thenReturn(booking);

            boolean isBooked = bookingService.bookFlight(booking);

            assertTrue(isBooked);
        }

        @Test
        void testBookFlight_Failure() {
            BookingMaster booking = new BookingMaster();
            booking.setCustomerId(1L);

            Mockito.when(bookingRepository.save(any(BookingMaster.class))).thenThrow(RuntimeException.class);

            boolean isBooked = bookingService.bookFlight(booking);

            assertFalse(isBooked);
        }

        @Test
        void testGetCustomerBookings() {
            Long customerId = 1L;
            List<BookingMaster> bookings = Collections.singletonList(new BookingMaster());

            Mockito.when(bookingRepository.findByCustomerId(customerId)).thenReturn(bookings);

            List<BookingMaster> customerBookings = bookingService.getCustomerBookings(customerId);

            assertFalse(customerBookings.isEmpty());
            assertTrue(customerBookings.size() == 1);
        }
    }
