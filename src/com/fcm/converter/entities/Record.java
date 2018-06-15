package com.fcm.converter.entities;

/**
 * Class responsible for representing a record in the database.
 * @author felipe
 *
 */
public class Record {
	private String data;
	private int id;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
