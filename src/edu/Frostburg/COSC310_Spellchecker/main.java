package edu.Frostburg.COSC310_Spellchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		
		//ChainHashMap<String, String> hashMap = new ChainHashMap<>();
		//hashMap.createTable();
		//ReadText.readIn(hashMap);
		
		Map<String, String> map = new HashMap<>(50000);
		ReadText.readIn(map);
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a word to be spell checked:");
		String input = scan.nextLine();
		scan.close();
		
		ArrayList<String> answers = new ArrayList<>();
		answers.addAll(SpellChecker.removeLetter(input));
		answers.addAll(SpellChecker.addLetter(input));
		SpellChecker.Combinations(input, answers);
		
		if(map.containsValue(input)){
			System.out.println("This word is spelled correctly.");
		}
		else{
			System.out.println("This word is not spelled correctly.");
			System.out.println("Did you mean...");
			
			for(int i = 0; i < answers.size(); i++){
				if(map.containsValue(answers.get(i))){
					System.out.print(answers.get(i) + " ");
				}
			}
		}
		
		//Check to see if ArrayList items satisfy the 3 conditions
		/*for(int i = 0; i < answers.size(); i++){
			System.out.println(answers.get(i));
		}*/
		
		//Checking the capacity and actual size of Map
		//System.out.println(hashMap.n + "\n" + hashMap.capacity);
		//for(int i = 0; i < hashMap.n; i++){
		
		//boolean found = false;
		
		/*Iterable<Entry<String, String>> it = hashMap.entrySet();
		for(Entry<String, String> s: it){
			if(s.equals(input)){
				System.out.println("This word is spelled correctly.");
				found = true;
				//break;
			}
		}
		
		if(found = false){
			System.out.println("This word is spelled incorrectly");
		}*/
	}
}


