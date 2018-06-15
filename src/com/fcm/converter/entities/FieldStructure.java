package com.fcm.converter.entities;

/**
 * This class represents the field structure of the file.
 * @author felipe
 *
 */
public class FieldStructure {
	private byte[] size;
	private byte[] position;
	
	public FieldStructure() {
		super();
		size = new byte[2];
		position = new byte[2];
	}

	public byte[] getSize() {
		return size;
	}
	public void setSize(byte[] size) {
		this.size = size;
	}
	public byte[] getPosition() {
		return position;
	}
	public void setPosition(byte[] position) {
		this.position = position;
	}
	
}
