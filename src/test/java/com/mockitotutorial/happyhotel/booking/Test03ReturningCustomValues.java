package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

class Test03ReturningCustomValues {

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
	void should_CountAvailablePlaces_When_OneRoomAvailable() {
		// given
		// return single element list
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)));
		int expected = 5;

		// when
		int actual = bookingService.getAvailablePlaceCount();

		// then
		assertEquals(expected, actual);

	}

	@Test
	void should_CountAvailablePlaces_When_MultipleRoomAvailable() {
		// given
		List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 5));
		when(this.roomServiceMock.getAvailableRooms())
		            .thenReturn(rooms);// return
		 																											// single
		int expected = 7;																										// element
																													// listint expected = 5;

		// when
		int actual = bookingService.getAvailablePlaceCount();

		// then
		assertEquals(expected, actual);
	}

}
