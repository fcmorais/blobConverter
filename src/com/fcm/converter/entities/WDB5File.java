package com.fcm.converter.entities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * This class represents the whole structure of the file.
 * @author felipe
 *
 */
public class WDB5File {
	private Header header;
	private FieldStructure[] fieldStructure;
	private int[] offsets;
	private Record[] records;
	
	public WDB5File(byte[] buffer) {
		super();
		header = new Header();
		
		/*
		 *  Setting the offset.
		 */
		int currentOffset = 0;
		
		/**
		 * Building the Header.
		 */
		header.setSignature(new String(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setRecordCount(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setFieldCount(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setRecordSize(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setStringTableSize(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setTableHash(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4));
		header.setLayoutHash(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4));
		header.setMinId(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setMaxId(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setLocale(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setCopyTableSize(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
		header.setFlags(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 2));
		header.setIdIndex(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 2));
		
		fieldStructure = new FieldStructure[header.getFieldCount()];
		
		for (FieldStructure f: fieldStructure){
			f = new FieldStructure();
			f.setSize(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 2));
			f.setPosition(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 2));
		}
		
		/**
		 * Length is one longer than the record count to account for the length of the last record.
		 */
		offsets = new int[header.getRecordCount() + 1];
		
		for (int i = 0; i < header.getRecordCount(); i++) {
			offsets[i] = byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4));
		}
		
		//Setting the last offset at the end of the String Table.
		offsets[header.getRecordCount()] = header.getStringTableSize();
		
		records = new Record[header.getRecordCount()];
		
		// Getting the record data from the String Table.
		for (int i = 0; i < header.getRecordCount(); i++) {
			records[i] = new Record();
			records[i].setData(new String(Arrays.copyOfRange(buffer, currentOffset + offsets[i], currentOffset + offsets[i+1])));
		}

		// If the flag is 4, we have non-inline IDs.
		if (header.getFlags()[0] == 4); {
			// Setting the current reading offset at the end of the String Table. 
			currentOffset+=header.getStringTableSize();
			
			for (int i = 0; i < header.getRecordCount(); i++) {
				records[i].setId(byteArrayToInt(Arrays.copyOfRange(buffer, currentOffset, currentOffset += 4)));
			}
		}
	}

	/**
	 * Responsible for converting a byte array into an int.
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {
	    final ByteBuffer bb = ByteBuffer.wrap(b);
	    bb.order(ByteOrder.LITTLE_ENDIAN);
	    return bb.getInt();
	}
	
	// Only the records should be visible outside.
	public Record[] getRecords() {
		return records;
	}
	
}
