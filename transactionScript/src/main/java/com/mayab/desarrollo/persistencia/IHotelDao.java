package com.mayab.desarrollo.persistencia;

import com.mayab.desarrollo.entities.*;

import java.util.ArrayList;


public interface IHotelDao {
	
	ArrayList<Room> getAll();
	Room searchById(int id); 
	boolean add(Room room); 
	boolean update(Room room); 
	boolean delete(Room room); 

}
