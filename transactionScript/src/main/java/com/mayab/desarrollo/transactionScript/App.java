package com.mayab.desarrollo.transactionScript;

import com.mayab.desarrollo.business.Hotel;
import com.mayab.desarrollo.persistencia.HotelDaoImp;

public class App {

	public static void main(String[] args){
		HotelDaoImp hotelDAO = new HotelDaoImp();
		Hotel sys = new Hotel(hotelDAO);
		
		sys.bookRoom(101);
		sys.bookRoom(101);
		
		sys.cancelRoomBooking(101);
			
	}

}
