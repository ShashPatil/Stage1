package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDate;

class Test08Spies {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	@BeforeEach
	void setup() {

		// mock() method creates a dummy object of given class
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = spy(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}
	// The difference between mocks and spies is that mocks don't have any logic of
	// the mocked class, and
	// they simply return the default values such as nulls or empty lists, unless we
	// change their behavior.
	// Spice, in turn, have all the logic from the mocked class, so they behave just
	// like a normal object
	// In other words, for spies, we call the actual methods from the actual
	// classes.method's logic that is shown here. we are getting actual bookinId by using spy 
	//see line no.-> 25

	@Test
	void should_MakeBooking_When_InputOK() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, true);

		
        // when
		String bookingId = bookingService.makeBooking(bookingRequest);

		// then
		verify(bookingDAOMock).save(bookingRequest);
		System.out.println("bookingId = " + bookingId);

	}
	@Test
	void should_CancelBooking_When_InputOK() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, true);
		bookingRequest.setRoomId("1.3");
		String bookingId = "1";
		
		doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);

		
        // when
		bookingService.cancelBooking(bookingId);
		

		// then
		

	}

}
