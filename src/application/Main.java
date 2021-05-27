package application;
	
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.scene.media.AudioClip;

public class Main extends Application {
     
	  // Create and initialize cell
	  private Cell[][] cell =  new Cell[10][10];

	  // Create and initialize a status label
	  private Label lblStatusUpLeft = new Label("Level #1");
	  private Label lblStatusUpCenter = new Label("0");
	  private Label lblStatusUpRight = new Label("High Score: 3");
	  private Label lblStatusDownLeft = new Label("---Text---");
	  
	  Image image = new Image(new File("image/mcwood.png").toURI().toString(), 50, 50, false, false);
	  Image image2 = new Image(new File("image/mcstone.png").toURI().toString(), 50, 50, false, false);
	  Image image3 = new Image(new File("image/mcmirror.png").toURI().toString(), 50, 50, false, false);
	  Image image4 = new Image(new File("image/space.png").toURI().toString(), 50, 50, false, false);
	  
	  BackgroundImage wood = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	  BackgroundImage stone = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	  BackgroundImage mirror = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	  BackgroundImage space = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	  	  
	  public String type;
	  
	  public int xloc;
	  public int yloc;
	  
	  
	  public int level = 1;
	  
	  public int score =0;
	  public int finalScore = 0;
	  public int highScore=0;
	  public int boxCount;
	  
	  Button nextLevel = new Button("Next Level");
	  
		 String path = "music/ses1.mp3";  
	     
		 AudioClip audioClip = new AudioClip(Paths.get("music/ses2.mp3").toUri().toString());
	  
	@Override
	public void start(Stage primaryStage) {
		
		lblStatusUpRight.setText(String.valueOf(HighScores.getHigh(level)));
		
		try {
			/*
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1000,1000);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			*/

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
			    gridPane.setPadding(new Insets(5,5,5,5));

			    BorderPane borderPane = new BorderPane();
			    borderPane.setCenter(gridPane);
			    borderPane.setStyle("-fx-background-color: lavender");
			    //borderPane.setPadding(new Insets(0,3,0,3));
			    
			    //BORDERPANE UP
			    BorderPane borderPaneUp = new BorderPane();
			    borderPaneUp.setPadding(new Insets(5,5,5,5));
			    borderPaneUp.setStyle("-fx-background-color: gold");
			    borderPaneUp.setLeft(lblStatusUpLeft);
			    borderPaneUp.setCenter(lblStatusUpCenter);
			    borderPaneUp.setRight(lblStatusUpRight);
			    
			    //BORDERPANE DOWN
			    BorderPane borderPaneDown = new BorderPane();
			    borderPaneDown.setPadding(new Insets(5,5,5,5));
			    borderPaneDown.setStyle("-fx-background-color: gold");
			    borderPaneDown.setLeft(lblStatusDownLeft);
			    
			    nextLevel.setDisable(true);
			    nextLevel.setOnMouseClicked(e -> {
					try {
						handleMouseClickButton();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			    nextLevel.setStyle("-fx-background-color: tomato");
			    borderPaneDown.setRight(nextLevel);
			    
			    //SET BORDERS
			    borderPane.setTop(borderPaneUp);
			    borderPane.setBottom(borderPaneDown);
			    
			    // Create a scene and place it in the stage
			    Scene scene = new Scene(borderPane, 550, 575);
			    primaryStage.setTitle("Game"); // Set the stage title
			    primaryStage.setScene(scene); // Place the scene in the stage
			    primaryStage.setResizable(false);
			    primaryStage.show(); // Display the stage  
			    
			    generateLevel();	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		launch(args);
	}
	
	private Object handleMouseClickButton() throws FileNotFoundException {
		nextLevel.setDisable(true);
		lblStatusUpCenter.setText(String.valueOf(0));
		finalScore=0;
		System.out.println("Next level");
		level++;
		generateLevel();
		lblStatusUpLeft.setText("Level #"+level);
		return null;
	}
	
	public void generateLevel() throws FileNotFoundException {
		
		/*ImageView wood = new ImageView(image);
		ImageView stone = new ImageView(image2);
		ImageView mirror = new ImageView(image3);*/
		
		 String lvl = "levels/level"+level+".txt";
		
		for(int i=0;i<10;i++) {
    		for(int j = 0;j<10;j++) {
    			cell[i][j].setBackground(new Background(stone));
    			cell[i][j].hp = -1;
    		}
    	}
		 //Take input from level txt files
	    FileInputStream fis=new FileInputStream(lvl);
	    Scanner sc = new Scanner(fis);
	   
	    while(sc.hasNext()) {
	    	String input = sc.next();
	    	String[] parts = input.split(",");
	    	type = parts[0]; 
	    	type = type.trim();
	    	String e = "Empty";
	    	String m = "Mirror";
	    	String w = "Wood";
	    	xloc = Integer.parseInt(parts[1]);
	    	yloc = Integer.parseInt(parts[2]);
	    	
	    	
	    	if(type.equals(e)) {
	    		
	    		cell[xloc][yloc].setBackground(new Background(space));
	    		cell[xloc][yloc].hp = 0;
	    		
	    	}
	    	else if(type.equals(m)) {
	    		
	    		cell[xloc][yloc].setBackground(new Background(mirror));
	    		cell[xloc][yloc].hp = 1;
	    	}
	    	else if(type.equals(w)) {
	    		
	    		cell[xloc][yloc].setBackground(new Background(wood));
	    		cell[xloc][yloc].hp = 2;
	    	}
	    	
	    	
	    	
	    	System.out.println(type+xloc+yloc);
	    }
		
		
		
		
	}
	
	
	
	
	  
	
	
	// An inner class for a cell
	  public class Cell extends Pane {
 
		public int i;
		public int j;
		public int hp;
		
		  
	    public Cell() {
	      
	      setStyle("-fx-border-color: black"); 
	      this.setPrefSize(80, 80);
	      this.setOnMouseClicked(e -> handleMouseClick());
	    }

	    /* Handle a mouse click event */
	    private void handleMouseClick() {
	    	
	    	
	    	String box = "";
	    	String box2 = "";
	    	String box3 = "";
	    	String box4 = "";
	    	String box5 = "";
	    	
			System.out.println("Cell");
			//this.setStyle("-fx-background-color: gold");
			//this.getChildren().add(stone);
			
			/*BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
			this.setBackground(new Background(bg));*/
			
			if(this.hp>0) {
				this.hp-=1;
				boxCount++;
				 box = "Box :"+this.i+","+this.j; 
				if(cell[(this.i)+1][this.j].hp>0) {
					cell[(this.i)+1][this.j].hp-=1;
					boxCount++;
					box2 = " Hit :"+(this.i+1)+","+this.j;
				}
				if(cell[(this.i)-1][this.j].hp>0) {
					cell[(this.i)-1][this.j].hp-=1;
					boxCount++;
					box3 = " Hit :"+(this.i-1)+","+this.j;
				}
				if(cell[this.i][(this.j)+1].hp>0) {
					cell[this.i][(this.j)+1].hp-=1;
					boxCount++;
					box4 = " Hit :"+this.i+","+(this.j+1);
				}
				if(cell[this.i][(this.j)-1].hp>0) {
					cell[this.i][(this.j)-1].hp-=1;
					boxCount++;
					box5 = " Hit :"+this.i+","+(this.j-1);
				}
				
				updateCells();
				generateScore(boxCount);
				lblStatusDownLeft.setText(box+box2+box3+box4+box5+" ("+score+" points)");
				hasFinished();
				score = 0;
				boxCount=0;
			}
			
	
  }
	    
	    public void updateCells() {
	    	for(int i =0;i<10;i++) {
	    		for(int j = 0;j<10;j++) {
	    			if(cell[i][j].hp == -1) {
	    				cell[i][j].setBackground(new Background(stone));
	    			}
	    			else if(cell[i][j].hp == 0) {
	    				cell[i][j].setBackground(new Background(space));
	    			}
	    			else if(cell[i][j].hp == 1) {
	    				cell[i][j].setBackground(new Background(mirror));
	    			}
	    			else if(cell[i][j].hp == 2) {
	    				cell[i][j].setBackground(new Background(wood));
	    			}
	    		}
	    	}
	    }
	    
	    public void generateScore(int boxCount) {
	    	
	    	switch(boxCount){
	    	case 1:
	    		score-=3;
	    		break;
	    	case 2:
	    		score-=1;
	    		break;
	    	case 3:
	    		score+=1;
	    		break;
	    	case 4:
	    		score+=2;
	    		break;
	    	case 5:
	    		score+=4;
	    		break;
	    	}
	    	
	    	finalScore+=score;
	    	
	    	lblStatusUpCenter.setText(String.valueOf(finalScore));
	    	
	    }
	    
	    public void hasFinished() {
			boolean clickable = false;
			for(int i =0;i<10;i++) {
				for(int j=0;j<10;j++) {
					if(cell[i][j].hp>0) {
						clickable = true;
					}
				}
			}
			if(!clickable) {
				
				try {
					HighScores.high(level,finalScore);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nextLevel.setDisable(false);
				audioClip.play();
			}
				
					
	    }
	    
	    
	    
 }
}
