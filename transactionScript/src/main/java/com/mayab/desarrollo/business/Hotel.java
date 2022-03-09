package com.mayab.desarrollo.business;

import com.mayab.desarrollo.persistencia.HotelDaoImp;
import com.mayab.desarrollo.entities.Room;
import java.util.logging.Logger;



public class Hotel {
	
	private final HotelDaoImp hotelDao;
	private Logger LOGGER = Logger.getLogger("Transaction Log");
	
	public Hotel(HotelDaoImp hotelDao) {
	    this.hotelDao = hotelDao;
	  }

	  public void bookRoom(int roomNumber){
		try {
			Room room = hotelDao.searchById(roomNumber);
			
		    if (room.isBooked()) {
		    	System.out.println("Room number: " + roomNumber + " exists");
		    	System.out.println("Room already booked!");
		    } else {
	    	  System.out.println("Room number: " + roomNumber + " exists");
		      Room updateRoomBooking = room;
		      updateRoomBooking.setBooked(true);
		      hotelDao.update(updateRoomBooking);
		      LOGGER.info("Booking CREATED for room number: " + roomNumber);
		      System.out.println("Succesfully booked room");
		      System.out.println(updateRoomBooking.toString());
		    }  
		}catch(Exception e) {
			throw e;
		}  
	  }

	  public void cancelRoomBooking(int roomNumber){

	    Room room = hotelDao.searchById(roomNumber);

		    try {
			      if (room.isBooked()) {
			        System.out.println("Room number: " + roomNumber + " exists");
			        Room updateRoomBooking = room;
			        updateRoomBooking.setBooked(false);
			        int refundAmount = updateRoomBooking.getPrice();
			        hotelDao.update(updateRoomBooking);
		
			        LOGGER.info("Booking cancelled for room number: " + roomNumber);
			        LOGGER.info(refundAmount + " is refunded");
			      } else {
			    	  System.out.println("Room number: " + roomNumber + " exists");
			    	  System.out.println("No booking for the room exists");
			      }
			    }catch(Exception e) {
					throw e;
				} 
	  }

}
