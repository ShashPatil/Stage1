package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

class Test01DefaultReturnValues {

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
		System.out.println("List Returned "+ roomServiceMock.getAvailableRooms());
		System.out.println("Object returned "+ roomServiceMock.findAvailableRoomId(null));
		System.out.println("Primitive returned "+ roomServiceMock.getRoomCount());

	}
    @Test
	void should_CountAvailablePlaces() {
    	//given
    	int expected = 0;
    	//when we just define the mocks and don't specify any of the return values,
    	//they return nice default values such as empty lists, null objects, 0/1
    	
    	//when
    	int actual = bookingService.getAvailablePlaceCount(); //-->calls roomService()
    	
    	//then
    	assertEquals(expected, actual);
    	
    	
		
	}

}
