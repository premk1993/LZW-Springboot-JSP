package com.lzw.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

@Component
public class Encode {

	Map<String, Integer> encTABLE;
	static final double bitLength=12;
	 
	//static final String inputFilePath="/Users/premkumar/eclipse-workspace/LZW Algorithm/Encoded Files/";
	static final String inputFilePath=Paths.get("").toAbsolutePath().toString().concat("/output_files");
	
	/*
	 * @Authors: Premkumar Sreedhar Vemula
	 * 
	 * @Description
	 * In the constructor the initial table is created, which here is the ASCII table
	 * */
	
	public Encode() {
		encTABLE = new HashMap<String, Integer>();
		for (int i = 0; i < 255 ; i++)
			encTABLE.put("" + (char) i, i);
		
	}
	

	/*
	 * @Authors: Premkumar Sreedhar Vemula
	 * 
	 * @Parameters:
	 * inputString - String of characters to be encoded
	 * fileName - input file name
	 * 
	 * @Description:
	 * This method follows the logic of Lempel–Ziv–Welch (LZW) algorithm 
	 * and encodes the input string into integer codes which is stored in an ArrayList
	 * */
	
	public ArrayList<Integer> encoder(String inputString) {
		
		ArrayList<Integer> encodedCodes=new ArrayList<Integer>();
		char[] ipchar=inputString.toCharArray();
		String iniString="";
	    int tblsize=encTABLE.size();
	    double maxTblSize=Math.pow(2, bitLength);
		for(int i=0;i<ipchar.length;i++) {
			
			try {
				if (encTABLE.containsKey(iniString.concat(String.valueOf(ipchar[i])))) {
					iniString += ipchar[i];
				} else {
					encodedCodes.add(encTABLE.get(iniString));
					if(tblsize<maxTblSize) {
						tblsize+=1;
					encTABLE.put(iniString.concat(String.valueOf(ipchar[i])), tblsize);
					}
					iniString = String.valueOf(ipchar[i]);
				} 
			} catch (Exception e) {
				System.out.println("Error in the for block while executing the encode algo. Error as follows  ------->"+e);
			}	
		}	
		encodedCodes.add(encTABLE.get(iniString));
		return encodedCodes;
		
	}
	
	

	/*
	 * @Authors: Premkumar Sreedhar Vemula
	 * 
	 * @Parameters:
	 * encodedCode - arraylist which consists of the encoded codes for the input text
	 * inFileName - name of the input file.
	 * 
	 * @Description:
	 * This method is used to generate a compressed output 16-bit file (.lzw) 
	 * in the same path where the input file was with .lzw extension 
	 * it reads the encoded codes in for each and writes it to the file.
	 * */

	public String generateEncodedOutputFile(ArrayList<Integer> encodedCode) 
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		OutputStream outputStream = null; 
		// String outputFilename=inputFilePath.concat(inFileName.substring(0, inFileName.indexOf(".")).concat(".lzw"));  
		String outputFilename=inputFilePath.concat("/"+Long.toString(timestamp.getTime()).concat("_Encoded.lzw"));
		try { outputStream = new
			  FileOutputStream(outputFilename);
		      Writer outputStreamWriter = new OutputStreamWriter(outputStream);		//The charset is used to generate a 16-bit file   
              encodedCode.forEach(code -> {
			try {
				if(code==null)
					outputStreamWriter.write(0); // this block is for cases if the initial table has no values in it or if the first char wont find a code from the table which wouldnt happen because ASCII table has mostly all the char.
				else
					outputStreamWriter.write(code);
			} catch (IOException e) {
				System.out.println("Error while creating encoded file. "+e);
			}
		});
        outputStreamWriter.close(); 
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found error, kindly check if the path exists. "+e);
		}  
		catch (IOException e) {
			System.out.println("Error while closing the file after done writing. "+e);
			e.printStackTrace();		
		}  
		  finally {
			  System.out.println("Encoded file generated at the path: " +outputFilename);
			  return outputFilename;
		} 
	}
	
}
