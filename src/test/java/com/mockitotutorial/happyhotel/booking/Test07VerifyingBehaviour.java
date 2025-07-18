package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDate;

class Test07VerifyingBehaviour {

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

	@Test
	void should_InvokePayment_When_Prepaid() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, true);

		// priceTotal = 4*2*50 = 400.0;

		// when
		bookingService.makeBooking(bookingRequest);

		// then
		verify(paymentServiceMock, times(1)).pay(bookingRequest,400.0);
		// On this line, we check whether the method pay() from the paymentServiceMock
		// was called with these specific arguments.
		
		verifyNoMoreInteractions(paymentServiceMock);
		//and this mockito method checks if any other methods from this mock were called.
		//if pay() method is called again for 2nd time, then this will throw an exception

	}

	@Test
	void should_NotInvokePayment_When_NotPrepaid() {
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, false);

		// when
		bookingService.makeBooking(bookingRequest);

		// then
          verify(paymentServiceMock, never()).pay(any(), anyDouble());
          //when payment is not prepaid then paymentServiceMock is never called with any arguments.
          //this line verifies that.
	}

}
