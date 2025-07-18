package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

class Test06Matchers {

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
	void should_NotCompleteBooking_When_PriceTooHigh() {
		// given
		BookingRequest bookingRequest = new BookingRequest("2", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
				2, true);
		
		when(this.paymentServiceMock.pay(any(), anyDouble()))
		     .thenThrow(BusinessException.class);
		
		//for any parameter value-> throw an exception ...then use matchers
		//Mockito matchers are used to match method arguments when stubbing or verifying.
        //✅ You use them when you don’t want to hardcode exact values, or when values can vary.
		

		// when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);

		// then
		assertThrows(BusinessException.class, executable);
	}
	
	// (any(), eq(400.0)) -> any ke saath direct hardcode value use nhi kr sakte..eq ka use karna padta hain
	
//	| Matcher              | Description              | Example                   |
//	| -------------------- | ------------------------ | ------------------------- |
//	| `any()`              | Any object of any type   | `any()`                   |
//	| `anyInt()`           | Any integer              | `anyInt()`                |
//	| `anyString()`        | Any string               | `anyString()`             |
//	| `eq(value)`          | Exactly equal to value   | `eq("shashank")`          |
//	| `anyList()`          | Any list                 | `anyList()`               |
//	| `any(Class.class)`   | Any object of that class | `any(User.class)`         |
//	| `argThat(predicate)` | Custom condition         | `argThat(id -> id > 100)` |


}
