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
	  private Label lblStatusDown = new Label("---Text---");
	  
	  
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
			    GridPane pane = new GridPane(); 
			    for (int i = 0; i < 10; i++)
			      for (int j = 0; j < 10; j++)
			        pane.add(cell[i][j] = new Cell(), j, i);

			    BorderPane borderPane = new BorderPane();
			    borderPane.setCenter(pane);
			    borderPane.setBottom(lblStatusDown);
			    borderPane.setTop(lblStatusUpLeft);
			    borderPane.setPadding(new Insets(0,10,0,10));
			    
			    pane.setHgap(3);
			    pane.setVgap(3);
			    
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
	     
  }
 }
}
