package application;

import java.io.File;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighScores {

	public static void high(int level, int newScore) throws IOException {

		String file = "highscores/high" + level + ".txt";
		File lvlfile = new File(file);

		if (lvlfile.createNewFile())
			System.out.println("File created: " + lvlfile.getName());
		else
			System.out.println("File already exists.");

		Scanner sc = new Scanner(file);
		PrintWriter writer = new PrintWriter(file);

		if (!sc.hasNext()) {

			writer.print(String.valueOf(newScore));

		} else {

			String old = sc.next();
			int oldHigh = Integer.valueOf(old);

			if (oldHigh < newScore) {

				old.replaceAll(old, String.valueOf(newScore));
				writer.print(old);

			} else {

			}

		}

	}

	public static int getHigh(int level) {

		File file = new File("highscores/high" + level + ".txt");
		Scanner sc = new Scanner("highscores/high" + level + ".txt");

		if (file.exists()) {

			if (sc.hasNext())
				return sc.nextInt();
			else
				return 0;

		} else {

			return 0;

		}

	}
}
