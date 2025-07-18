package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

class Test04MultipleThenReturnCalls {

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
	void should_CountAvailablePlaces_When_CalledMultipleTimes() {
		// given
		when(this.roomServiceMock.getAvailableRooms())
		          .thenReturn(Collections.singletonList(new Room("Room 1", 5)))
		          .thenReturn(Collections.emptyList());
		
		int expectedFirstCall = 5;
		int expectedSecondCall = 0;

		// when
		int actualFirst = bookingService.getAvailablePlaceCount();
		int actualSecond = bookingService.getAvailablePlaceCount();

		// then
		assertAll(
				()->assertEquals(expectedFirstCall, actualFirst),
				()->assertEquals(expectedSecondCall, actualSecond)
				);
		
		

	}
	//So the first time that we called BookingService.getAvailablePlaceCount , behind the scenes,
	//the BookingService called roomServiceMock.getAvailableRooms and got a list with one room of capacity 5.
	//But the second time that BookingService.getAvailablePlaceCount was called , an empty list was returned instead.

	

}
