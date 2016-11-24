package edu.Frostburg.COSC310_Spellchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ReadText {
	
	public static void readIn(ChainHashMap<String, String> map) throws FileNotFoundException{
		File file = new File("Dictionary.txt");
		Scanner scan = new Scanner(file);
		int place = 0;
		
		while(scan.hasNextLine()){
			String tempStr = scan.nextLine();
			map.bucketPut(place++, tempStr, tempStr);
		}
		
		scan.close();
	}
	
	public static void readIn(Map<String, String> map) throws FileNotFoundException{
	File file = new File("Dictionary.txt");
	Scanner scan = new Scanner(file);
	
	while(scan.hasNextLine()){
		String tempStr = scan.nextLine();
		map.put(tempStr.toLowerCase().trim(), tempStr.toLowerCase().trim());
	}
	
	scan.close();
	}
}
