package com.fcm.converter.run;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.fcm.converter.entities.Record;
import com.fcm.converter.entities.WDB5File;

/**
 * Class responsible for implementing the rules of the file reading and
 * converting.
 * 
 * @author felipe
 *
 */
public class Converter {
	
	public static String CSV_FILE = "blob.csv";
	
	public static void main(String[] args) {
		if (args.length != 1) {
			log("Invalid argument.");
			System.exit(0);
		}
		log("Reading file: " + args[0]);
		File file = new File(args[0]);
		byte[] buffer = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				input = new BufferedInputStream(new FileInputStream(file));
				// Reads the file, loading the buffer.
				input.read(buffer, 0, buffer.length);

				// Converting the byte code into something more manageable.
				WDB5File wdbFile = new WDB5File(buffer);
				BufferedWriter br = new BufferedWriter(new FileWriter(CSV_FILE));
				
				// Building the CSV file.
				for (Record r : wdbFile.getRecords()) {
					StringBuilder sb = new StringBuilder();
					sb.append(r.getId());
					sb.append(",");
					sb.append(r.getData());
					br.write(sb.toString());
					br.newLine();
				}
				log("CSV File: " + CSV_FILE + " created.");
				br.close();
			} finally {
				// Closing the input stream.
				input.close();
			}
		} catch (FileNotFoundException e) {
			log("File does not exist.");
		} catch (IOException e) {
			log(e);
		}
	}

	private static void log(Object o) {
		System.out.println(o.toString());
	}

}
