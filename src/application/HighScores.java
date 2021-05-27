package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighScores {

	public static void createNewHighScore(int level, int newScore) throws IOException {

		String file = "highscores/level" + level + ".txt";
		File lvlfile = new File(file);

		if (lvlfile.createNewFile())
			System.out.println("File created: " + lvlfile.getName());
		else
			System.out.println("File already exists.");
		
		
		FileWriter writeFile = null;
		BufferedWriter writer = null;
		
		try {
			
			writeFile = new FileWriter (lvlfile);
			writer = new BufferedWriter(writeFile);
			writer.write(String.valueOf(newScore));
			
		} 
		finally {
		
			try {
				if(writer != null)
					writer.close();
			}
			catch(Exception e){}
			
		}

	}

	public static int getHighScore(int level) throws FileNotFoundException {

		File file = new File("highscores/level" + level + ".txt");

		if (file.exists()) {

			FileInputStream fileInput = new FileInputStream("highscores/level" + level + ".txt");
			Scanner sc = new Scanner(fileInput);
			
			if (sc.hasNext())
				return sc.nextInt();
			else
				return 0;

		} 
		else 
			return 0;

	}
}