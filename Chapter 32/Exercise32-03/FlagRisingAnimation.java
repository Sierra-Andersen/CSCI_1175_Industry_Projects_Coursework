/*
 * Modifier: Sierra Andersen
 * Date: 06 Mar 2023
 * 
 * This program has been modified to use a Thread to raise a flag.
 */
import javafx.animation.PathTransition; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();
	
		// Add an image view and add it to pane
		ImageView imageView = new ImageView("image/us.gif");
		pane.getChildren().add(imageView);

		// Create a path transition
		PathTransition pt = new PathTransition(Duration.millis(10000),
							new Line(100, 200, 100, 0), imageView); pt.setCycleCount(5);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {	
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								pt.play();
							}
						});
						
						Thread.sleep(200);
					}
				}
				catch (InterruptedException ex) {
					
				}
			}
		}).start();
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 200); 
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}