package com.mayab.desarrollo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.mayab.desarrollo.entities.Room;

public class HotelDaoImp implements IHotelDao{
	
	private ArrayList<Room> rooms;
	private static final String DRIVER_NAME = "com.sqlite.jdbc.Driver";
	private static final String DB_URL = "jdbc:sqlite:D:\\Eclipse\\Desarrollo\\Transaction-Scripts\\transactionScript\\database\\Hotel.db";
	private static final String ID = "";
	private static final String PASS = "";
	
	// CONSTRUCTOR
	public HotelDaoImp() {
		
	}
	
	// DRIVER METHODS
	private Connection getConnection(){
		try {
			System.out.println("Connecting to database...");
			return DriverManager.getConnection(DB_URL, ID, PASS); 
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
		
	private static void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	// IMPLEMENTATIONS
	@Override
	public ArrayList<Room> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Room room) {
		// 
		Connection con = null; 
		PreparedStatement stmt = null;
		boolean resBool = false; 
		
		try {
			con = getConnection();
			stmt = con.prepareStatement("INSERT INTO ROOMS(roomType, price, booked) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, room.getRoomType());
			stmt.setInt(2, room.getPrice());
			stmt.setBoolean(3, room.isBooked());
			
			int result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				room.setId(rs.getInt(1));
				resBool = true;
				System.out.println("Room added with success....");
				
			}
		}catch(SQLException e) {
				throw new RuntimeException(e);
				
		}finally {
			close(con);
		}
		return resBool;
	}

	@Override
	public boolean update(Room room) {
		// 
		Connection con = null; 
		PreparedStatement stmt = null;
		boolean resBool = false; 
		
		try {
			con = getConnection();
			stmt = con.prepareStatement("UPDATE ROOMS SET ROOM_TYPE = ?, PRICE = ?, BOOKED = ?  WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, room.getRoomType());
			stmt.setInt(2, room.getPrice());
			stmt.setBoolean(3, room.isBooked());
			stmt.setInt(4, room.getId());
			
			int result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				resBool = true;
				System.out.println("Room updated with success....");
				
			}
		}catch(SQLException e) {
				throw new RuntimeException(e);
				
		}finally {
			close(con);
		}
		return resBool;
	}

	@Override
	public boolean delete(Room room) {
		// 
		Connection con = null; 
		PreparedStatement stmt = null;
		boolean resBool = false; 
		
		try {
			con = getConnection();
			stmt = con.prepareStatement("DELETE FROM ROOMS WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, room.getId());
			
			int result = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				resBool = true;
				System.out.println("Room deleted with success....");
				
			}
		}catch(SQLException e) {
				throw new RuntimeException(e);
				
		}finally {
			close(con);
		}
		return resBool;
	}

	@Override
	public Room searchById(int id) {
		Connection con = null; 
		PreparedStatement stmt = null;
		ResultSet rs;
		boolean result = false;

		Room retrieved = null;

		try {
			// Start con
			con = getConnection();
			// Declare statement query to run
			stmt = con.prepareStatement("SELECT * FROM ROOMS WHERE id = ?");
			// Set the values to match in the ? on query
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			// Obtain the pointer to the data in generated table
			rs.next();

			int retrivedId = rs.getInt(1);
			String retrivedRoomType = rs.getString(2);
			int retrivedPrice = rs.getInt(3);
			boolean retrivedBookedStatus = rs.getBoolean(4);

			retrieved = new Room(retrivedId, retrivedRoomType, retrivedPrice, retrivedBookedStatus);

			// Return the values of the search
			System.out.println("\n");
			System.out.println("---Room---");
			System.out.println("ID: " + retrieved.getId());
			System.out.println("Type: " + retrieved.getRoomType());
			System.out.println("Price: " + retrieved.getPrice());
			System.out.println("Booked: " + retrieved.isBooked() + "\n");
			// Close connection with the database
			con.close();
			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return retrieved;
	}
	
}
	
	
	
	


