package com.mockitotutorial.happyhotel.booking;

import java.util.*;
import java.util.stream.Collectors;

public class RoomService {

	private final Map<Room, Boolean> roomAvailability;
	{
		roomAvailability = new HashMap<>();
		roomAvailability.put(new Room("1.1", 2), true);
		roomAvailability.put(new Room("1.2", 2), true);
		roomAvailability.put(new Room("1.3", 5), true);
		roomAvailability.put(new Room("2.1", 3), true);
		roomAvailability.put(new Room("2.2", 4), true);
	}

	public String findAvailableRoomId(BookingRequest bookingRequest) {
		return roomAvailability.entrySet().stream()
				.filter(entry -> entry.getValue()).map(entry -> entry.getKey())
				.filter(room -> room.getCapacity() == bookingRequest.getGuestCount())
				.findFirst()
				.map(room -> room.getId())
				.orElseThrow(BusinessException::new);
	}
	
	public List<Room> getAvailableRooms() {
		return roomAvailability.entrySet().stream()
				.filter(entry -> entry.getValue())
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
	}
	
	public int getRoomCount() {
		return roomAvailability.size();
	}

	public void bookRoom(String roomId) {
		Room room = roomAvailability.entrySet().stream()
			.filter(entry -> entry.getKey().getId().equals(roomId) && entry.getValue())
			.findFirst()
			.map(entry -> entry.getKey())
			.orElseThrow(BusinessException::new);
		
		roomAvailability.put(room, false);		
	}
	
	public void unbookRoom(String roomId) {
		Room room = roomAvailability.entrySet().stream()
			.filter(entry -> entry.getKey().getId().equals(roomId) && !entry.getValue())
			.findFirst()
			.map(entry -> entry.getKey())
			.orElseThrow(BusinessException::new);
		
		roomAvailability.put(room, true);		
	}
	
}
