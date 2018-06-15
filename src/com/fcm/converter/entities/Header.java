package com.fcm.converter.entities;

/**
 * This class represents the header of the file.
 * 
 * @author felipe
 *
 */
public class Header {
	private String signature;
	private int recordCount;
	private int fieldCount;
	private int recordSize;
	private int stringTableSize;
	private byte[] tableHash;
	private byte[] layoutHash;
	private int minId;
	private int maxId;
	private int locale;
	private int copyTableSize;
	private byte[] flags;
	private byte[] idIndex;

	public Header() {
		super();
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getStringTableSize() {
		return stringTableSize;
	}

	public void setStringTableSize(int stringTableSize) {
		this.stringTableSize = stringTableSize;
	}

	public byte[] getTableHash() {
		return tableHash;
	}

	public void setTableHash(byte[] tableHash) {
		this.tableHash = tableHash;
	}

	public byte[] getLayoutHash() {
		return layoutHash;
	}

	public void setLayoutHash(byte[] layoutHash) {
		this.layoutHash = layoutHash;
	}

	public int getMinId() {
		return minId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public int getLocale() {
		return locale;
	}

	public void setLocale(int locale) {
		this.locale = locale;
	}

	public int getCopyTableSize() {
		return copyTableSize;
	}

	public void setCopyTableSize(int copyTableSize) {
		this.copyTableSize = copyTableSize;
	}

	public byte[] getFlags() {
		return flags;
	}

	public void setFlags(byte[] flags) {
		this.flags = flags;
	}

	public byte[] getIdIndex() {
		return idIndex;
	}

	public void setIdIndex(byte[] idIndex) {
		this.idIndex = idIndex;
	}

}
