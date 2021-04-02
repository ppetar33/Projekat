package dispecer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrikazVozaca {
	
	public static void main(String[] args) {
		
		String[] words = readArray("src/fajlovi/korisnici.txt");
		for(int i = 0; i < words.length ; i++) {
			if(words[i].contains("DISPECAR")) {
				System.out.println(words[i]);
			}
		}
		// zbog razmaka ne prikazuje podatke
	
	}
	
	public static String[] readArray(String file) {
		
		int ctr = 0;
		try {
			
			Scanner s1 = new Scanner(new File(file));

			while(s1.hasNextLine()) {
				ctr = ctr + 1;
				s1.next();
			}
			
			String[] words = new String[ctr];

			Scanner s2 = new Scanner(new File(file));
			
			for(int i = 0; i < ctr; i++) {
				words[i] = s2.next();
			}
			
			return words;
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		return null;
	}
}
