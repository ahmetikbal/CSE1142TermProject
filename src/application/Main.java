package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Main extends Application {
	
	
	  

	  // Create and initialize cell
	  private Cell[][] cell =  new Cell[10][10];

	  // Create and initialize a status label
	  private Label lblStatusUpLeft = new Label("Level #1");
	  private Label lblStatusUpCenter = new Label("0");
	  private Label lblStatusUpRight = new Label("High Score: 3");
	  
	  private Label lblStatusDownLeft = new Label("---Text---");
	  
	  
	@Override
	public void start(Stage primaryStage) {
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
			      for (int j = 0; j < 10; j++)
			    	  gridPane.add(cell[i][j] = new Cell(), j, i);
			    
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
			    Button nextLevel = new Button("Next Level");
			    nextLevel.setOnMouseClicked(e -> handleMouseClickButton());
			    nextLevel.setStyle("-fx-background-color: tomato");
			    borderPaneDown.setRight(nextLevel);
			    
			    //SET BORDERS
			    borderPane.setTop(borderPaneUp);
			    borderPane.setBottom(borderPaneDown);
			    
			    // Create a scene and place it in the stage
			    Scene scene = new Scene(borderPane, 450, 450);
			    primaryStage.setTitle("Game"); // Set the stage title
			    primaryStage.setScene(scene); // Place the scene in the stage
			    primaryStage.show(); // Display the stage  
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private Object handleMouseClickButton() {
		System.out.println("Next level");
		return null;
	}
	
	
	
	
	  
	
	
	// An inner class for a cell
	  public class Cell extends Pane {
	    // Token used for this cell
	    private char token = ' ';
	  

	    public Cell() {
	      
	      setStyle("-fx-border-color: black"); 
	      this.setPrefSize(800, 800);
	      this.setOnMouseClicked(e -> handleMouseClick());
	    }

	    
	

	    
	    

	    /* Handle a mouse click event */
	    private void handleMouseClick() {
			System.out.println("Cell");
  }
 }
}
