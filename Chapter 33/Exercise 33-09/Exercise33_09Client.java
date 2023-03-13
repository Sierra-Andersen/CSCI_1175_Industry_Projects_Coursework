/*
 * Modifier: Sierra Andersen
 * Date: 13 Mar 2023
 * 
 * This program allows two users to chat.
 */

import javafx.application.Application;
import javafx.application.Platform;

import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class Exercise33_09Client extends Application {
	private TextArea taServer = new TextArea();
	private TextArea taClient = new TextArea();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		taServer.setWrapText(true);
		taClient.setWrapText(true);
		taServer.setDisable(true);

		BorderPane pane1 = new BorderPane();
		pane1.setTop(new Label("History"));
		pane1.setCenter(new ScrollPane(taServer));
		BorderPane pane2 = new BorderPane();
		pane2.setTop(new Label("New Message"));
		pane2.setCenter(new ScrollPane(taClient));

		VBox vBox = new VBox(5);
		vBox.getChildren().addAll(pane1, pane2);

		// Create a scene and place it in the stage
		Scene scene = new Scene(vBox, 200, 200);
		primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage


		new Thread(()->{
			try {
				Socket socket = new Socket("localhost", 8000);
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());

				
				while(true) {
					//Send information
					taClient.setOnKeyPressed(e->{
						if(e.getCode()==KeyCode.ENTER) {
							String messageSend = taClient.getText().trim() + "\n";
							taClient.clear();
							try {
								toServer.writeChars(messageSend);
								toServer.flush();
								
								Platform.runLater(()->{
									taServer.appendText("Client: " + messageSend + "\n");	
								});
							}
							catch(IOException ex){
								ex.printStackTrace();
							}

						}
					});

					//Receive Information and print
					String messageReceived = fromServer.readLine();
					
					Platform.runLater(()->{
						taServer.appendText("Server: " + messageReceived+"\n");
					});
				}

			}
			catch(IOException ex) {
				ex.printStackTrace();
			}			
		}).start();

	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
