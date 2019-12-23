import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CipherCracking {

	public static void main(String[] args) throws Exception {
		
		 Scanner fileInput = new Scanner(new File("CipherCrackingIN.txt"));
		 	
		 	int i = 0;
	        while(fileInput.hasNext()) {
	 
	            String inputStr = fileInput.nextLine().trim();
	             
	            System.out.println(closestVowel(inputStr));
	             
	            i++;
	        }
	        fileInput.close();
	}
	
	public static String closestVowel(String str) {
		
		//STUBIFY
		int distance_from_head = 0;
		int distance_from_tail = 0;
		String closest_vowel_from_head = "";
		String closest_vowel_from_tail = "";
		
		int distance = 0;
		boolean closest_vowel_found = false;
		
		while(!closest_vowel_found && distance < str.length()) {
			char frontLetter = str.charAt(distance);
			if(isVowel(frontLetter)) {
				return "" + frontLetter;
			}
			
			char backLetter = str.charAt(str.length() - distance - 1);
			if(isVowel(backLetter)) {
				return "" + backLetter;
			}
			distance++;
		}
		
		return "";
		//ENDSTUBIFY
	}
	
	//STUBIFY
	public static boolean isVowel(char letter) {
		return "AEIOUaeiou".indexOf(letter) != -1;
	}
	//ENDSTUBIFY
}
