package edu.Frostburg.COSC310_Spellchecker;

import java.util.ArrayList;

public class SpellChecker  {

	/**
	 * Method to add an extra letter to an index, i.e. adding a String's length should increase by 1
	 * Doesn't go as far as to return the permutations of these substrings either
	 * @param s
	 * @return ArrayList of suggested answers
	 */
	public static ArrayList<String> addLetter(String s){
		char temp = 'a';
		ArrayList<String> tempArray = new ArrayList<>();
		StringBuilder newStr = new StringBuilder(s);
		
		//char[] charArray = new char[s.length() + 1];
		
		String tempString = s;
		
		for(int i = 0; i < s.length()+1; i++){
			temp = 'a';
			
			for(int j = 0; j < 26; j++){
						
				newStr.insert(i, temp++);
				s = newStr.toString().toLowerCase();
				tempArray.add(s);
				s = tempString;
				newStr.replace(0, s.length()+1, s);
			}
		}
		return tempArray;
	}
	
	/**
	 * Method to remove a char from each index in a string and print the permutations of the string
	 * Doesn't go as far to return the permutations of said substrings
	 * @param s
	 * @return ArrayList of suggested answers
	 */
	public static ArrayList<String> removeLetter(String s){
		ArrayList<String> tempArray = new ArrayList<>();		
		StringBuilder newStr = new StringBuilder(s);
		
		String tempString = s;
		
		for(int i = 0; i < s.length(); i++){
			
			newStr.deleteCharAt(i);
			s = newStr.toString().toLowerCase();
			tempArray.add(s);
			s = tempString;
			newStr.replace(0, s.length(), s);
		}
		
		return tempArray;
	}
	
	
	/**
	 * Adds all permutations of a string to an ArrayList
	 * @param str
	 */
	public static void Combinations(String str, ArrayList<String> arrayList) { 
	   Combinations("", str, arrayList); 
	}

	private static void Combinations(String prefix, String str, ArrayList<String> tempArray) {

		int n = str.length();
	    if (n == 0){
	    	tempArray.add(prefix.toLowerCase());
	    }
	    else {
	        for (int i = 0; i < n; i++)
	          Combinations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), tempArray);
	    }
	}
}
