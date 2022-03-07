package com.mayab.desarrollo.entities;

import java.util.Objects;

public class Room {
	
	private int id; 
	private String roomType; 
	private int price; 
	private boolean booked;
	
	
	public Room(int id, String roomType, int price, boolean booked) {
		this.id = id; 
		this.roomType = roomType;
		this.price = price; 
		this.booked = booked; 
	}
	
	// SETTERS & GETTERS
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the booked
	 */
	public boolean isBooked() {
		return booked;
	}
	/**
	 * @param booked the booked to set
	 */
	public void setBooked(boolean booked) {
		this.booked = booked;
	}
	
	// EQUALS & HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(booked, id, price, roomType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return booked == other.booked && id == other.id && price == other.price
				&& Objects.equals(roomType, other.roomType);
	}
	
	// To String Methods
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomType=" + roomType + ", price=" + price + ", booked=" + booked + "]";
	}
	
	
	
	
	
	

}
