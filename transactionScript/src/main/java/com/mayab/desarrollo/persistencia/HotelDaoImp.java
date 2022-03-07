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
	private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Martin Garcia Nava\\OneDrive - AG-ALL TI S DE RL DE CV\\Leo\\Anahuac 6to Semestre\\Desarrollo Software\\hotel.db";
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
			stmt = con.prepareStatement("INSERT INTO libros(roomType, price, booked) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Room room) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
