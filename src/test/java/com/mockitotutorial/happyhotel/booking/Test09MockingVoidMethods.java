package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

class Test09MockingVoidMethods {

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
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}
     //That's because we cannot use the when(...) methods with methods that return void values
	//for void methods we need to use doThrow().when()
	@Test
	void should_ThrowException_When_MailNotReady() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, false);
		
		doThrow(new BusinessException()).when(mailSenderMock).sendBookingConfirmation(any());

		// when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);

		// then
		assertThrows(BusinessException.class, executable);
	}
	
	@Test
	void should_NotThrowException_When_MailNotReady() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, false);
		
		doNothing().when(mailSenderMock).sendBookingConfirmation(any());

		// when
		bookingService.makeBooking(bookingRequest);

		// then
		//no exception thrown
	}
	
	//if you want to throw an exception from void method-> use doThrow()
    // if not then -> doNothing()
}
