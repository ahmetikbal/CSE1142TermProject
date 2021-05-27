package application;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.scene.media.AudioClip;

public class Main extends Application {

	// Create and initialize cell
	private Cell[][] cell = new Cell[10][10];

	// Create and initialize a status label
	private Label levelLabel = new Label("Level #1");
	private Label scoreLabel = new Label("0");
	private Label highScoreLabel = new Label("High Score: 3");
	private Label statusLabel = new Label("---Text---");

	Button nextLevelButton = new Button("Next Level");

	public int level = 1;

	public int score = 0;
	public int finalScore = 0;
	public int highScore = 0;

	Image woodImage = new Image(new File("image/mcwood.png").toURI().toString(), 50, 50, false, false);
	Image stoneImage = new Image(new File("image/mcstone.png").toURI().toString(), 50, 50, false, false);
	Image mirrorImage = new Image(new File("image/mcmirror.png").toURI().toString(), 50, 50, false, false);
	Image spaceImage = new Image(new File("image/space.png").toURI().toString(), 50, 50, false, false);

	BackgroundImage wood = new BackgroundImage(woodImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	BackgroundImage stone = new BackgroundImage(stoneImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	BackgroundImage mirror = new BackgroundImage(mirrorImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	BackgroundImage space = new BackgroundImage(spaceImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

	AudioClip failSound = new AudioClip(Paths.get("music/ses1.mp3").toUri().toString());
	AudioClip winSound = new AudioClip(Paths.get("music/ses2.mp3").toUri().toString());

	@Override
	public void start(Stage primaryStage) {

		highScoreLabel.setText(String.valueOf(HighScores.getHigh(level)));

		try {

			// Pane to hold cell
			GridPane gridPane = new GridPane();
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++) {

					Cell cell = new Cell();
					cell.i = i;
					cell.j = j;

					gridPane.add(this.cell[i][j] = cell, j, i);
				}

			gridPane.setHgap(3);
			gridPane.setVgap(3);
			gridPane.setPadding(new Insets(5, 5, 5, 5));

			BorderPane borderPane = new BorderPane();
			borderPane.setCenter(gridPane);
			borderPane.setStyle("-fx-background-color: lavender");
			// borderPane.setPadding(new Insets(0,3,0,3));

			// BORDERPANE UP
			BorderPane borderPaneUp = new BorderPane();
			borderPaneUp.setPadding(new Insets(5, 5, 5, 5));
			borderPaneUp.setStyle("-fx-background-color: gold");
			borderPaneUp.setLeft(levelLabel);
			borderPaneUp.setCenter(scoreLabel);
			borderPaneUp.setRight(highScoreLabel);

			// BORDERPANE DOWN
			BorderPane borderPaneDown = new BorderPane();
			borderPaneDown.setPadding(new Insets(5, 5, 5, 5));
			borderPaneDown.setStyle("-fx-background-color: gold");
			borderPaneDown.setLeft(statusLabel);
			borderPaneDown.setRight(nextLevelButton);

			nextLevelButton.setStyle("-fx-background-color: tomato");
			nextLevelButton.setDisable(true);
			nextLevelButton.setOnMouseClicked(e -> {
				try {
					nextLevelMouseClickButton();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			// SET BORDERS
			borderPane.setTop(borderPaneUp);
			borderPane.setBottom(borderPaneDown);

			// Create a scene and place it in the stage
			Scene scene = new Scene(borderPane, 550, 575);
			primaryStage.setTitle("Game"); // Set the stage title
			primaryStage.setScene(scene); // Place the scene in the stage
			primaryStage.setResizable(false);
			primaryStage.show(); // Display the stage

			generateLevel();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

	public void generateLevel() throws FileNotFoundException {

		String lvl = "levels/level" + level + ".txt";

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				cell[i][j].setBackground(new Background(stone));
				cell[i][j].hp = -1;
			}
		}

		// Take input from level txt files
		FileInputStream fileInput = new FileInputStream(lvl);
		Scanner scanner = new Scanner(fileInput);

		while (scanner.hasNext()) {
			String input = scanner.next();
			String[] parts = input.split(",");

			String cellType = parts[0].trim();

			int xloc = Integer.parseInt(parts[1]);
			int yloc = Integer.parseInt(parts[2]);

			if (cellType.equals("Empty")) {

				cell[xloc][yloc].setBackground(new Background(this.space));
				cell[xloc][yloc].hp = 0;

			} else if (cellType.equals("Mirror")) {

				cell[xloc][yloc].setBackground(new Background(this.mirror));
				cell[xloc][yloc].hp = 1;

			} else if (cellType.equals("Wood")) {

				cell[xloc][yloc].setBackground(new Background(this.wood));
				cell[xloc][yloc].hp = 2;

			}

			System.out.println(cellType + xloc + yloc);
		}

	}

	private Object nextLevelMouseClickButton() throws FileNotFoundException {

		nextLevelButton.setDisable(true);

		finalScore = 0;
		scoreLabel.setText("0");

		level++;
		levelLabel.setText("Level #" + level);

		generateLevel();

		return null;

	}

	// An inner class for a cell
	public class Cell extends Pane {

		public int i; // first dimension
		public int j; // second dimension
		public int hp;

		public Cell() {

			setStyle("-fx-border-color: black");
			this.setPrefSize(80, 80);
			this.setOnMouseClicked(e -> cellMouseClick());

		}

		/* Handle a mouse click event */
		private void cellMouseClick() {

			int cellCount = 0;
			String statusText = "";

			if (this.hp > 0) {

				this.hp -= 1;
				cellCount++;
				statusText += "Box: " + this.i + "," + this.j;
				if (cell[(this.i) + 1][this.j].hp > 0) {
					cell[(this.i) + 1][this.j].hp -= 1;
					statusText += " Hit: " + (this.i + 1) + "," + this.j;
					cellCount++;
				}
				if (cell[(this.i) - 1][this.j].hp > 0) {
					cell[(this.i) - 1][this.j].hp -= 1;
					statusText += " Hit: " + (this.i - 1) + "," + this.j;
					cellCount++;
				}
				if (cell[this.i][(this.j) + 1].hp > 0) {
					cell[this.i][(this.j) + 1].hp -= 1;
					statusText += " Hit: " + this.i + "," + (this.j + 1);
					cellCount++;
				}
				if (cell[this.i][(this.j) - 1].hp > 0) {
					cell[this.i][(this.j) - 1].hp -= 1;
					statusText += " Hit: " + this.i + "," + (this.j - 1);
					cellCount++;
				}

				updateCells();
				generateScore(cellCount);
				hasFinished();

				statusLabel.setText(statusText + " (" + score + " points)");
				score = 0;
			}

		}

		public void updateCells() {

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {

					if (cell[i][j].hp == -1)
						cell[i][j].setBackground(new Background(stone));

					else if (cell[i][j].hp == 0)
						cell[i][j].setBackground(new Background(space));

					else if (cell[i][j].hp == 1)
						cell[i][j].setBackground(new Background(mirror));

					else if (cell[i][j].hp == 2)
						cell[i][j].setBackground(new Background(wood));

				}
			}

		}

		public void generateScore(int cellCount) {

			switch (cellCount) {
			case 1:
				score -= 3;
				break;
			case 2:
				score -= 1;
				break;
			case 3:
				score += 1;
				break;
			case 4:
				score += 2;
				break;
			case 5:
				score += 4;
				break;
			}

			finalScore += score;

			scoreLabel.setText(String.valueOf(finalScore));

		}

		public void hasFinished() {

			boolean clickable = false;

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					if (cell[i][j].hp > 0)
						clickable = true;

			if (!clickable) {

				try {
					HighScores.high(level, finalScore);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				nextLevelButton.setDisable(false);
				if(finalScore > 0) winSound.play(); else failSound.play();

			}

		}

	}

}