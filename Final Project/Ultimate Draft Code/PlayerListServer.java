/*
 * Author: Sierra Andersen
 * Date: 22 Mar 2023
 * 
 * This is a Server that allows players to be added to a list and selected by captains through a client.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.*;
import java.net.*;


public class PlayerListServer extends Application {
	@Override
	public void start(Stage primaryStage) {

		//Create a PlayerInformationPane with a button to add players
		PlayerInformationPane addPlayerPane = new PlayerInformationPane();
		Button btDemoPlayer = new Button("Demo Player");
		Button btAdd = new Button("Add Player");
		addPlayerPane.getChildren().addAll(btAdd, btDemoPlayer);



		btDemoPlayer.setOnAction(e->{
			addPlayerPane.tfFirstName.setText("Joe");
			addPlayerPane.tfLastName.setText("Blow");
			addPlayerPane.tfAge.setText("24");
			addPlayerPane.tfHeight.setText("73");
			addPlayerPane.tfPhoneNumber.setText("555-5555");
			addPlayerPane.cmbExperience.setValue(Player.ExperienceLevel.None);
			addPlayerPane.cmbFitness.setValue(Player.Ability.Poor);
			addPlayerPane.cmbSpeed.setValue(Player.Ability.BelowAverage);
			addPlayerPane.cmbFlick.setValue(Player.Ability.Average);
			addPlayerPane.cmbBackhand.setValue(Player.Ability.AboveAverage);
			addPlayerPane.cmbCatching.setValue(Player.Ability.Average);
			addPlayerPane.cmbOffense.setValue(Player.Ability.Excellent);
			addPlayerPane.cmbDefense.setValue(Player.Ability.Poor);
		});

		new Thread(()->{
			try {
				ServerSocket serverSocket = new ServerSocket(8000);
				Socket socket = serverSocket.accept();
				//BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

				while(true) {	

					btAdd.setOnAction(e->{
						try {
							//Send player data to the Client.
							toClient.writeChars(addPlayerPane.tfFirstName.getText() +"\n");
							toClient.writeChars(addPlayerPane.tfLastName.getText()+"\n");
							toClient.writeChars(addPlayerPane.tfAge.getText()+"\n");	
							toClient.writeChars(addPlayerPane.tfHeight.getText()+"\n");
							toClient.writeChars(addPlayerPane.cmbExperience.getItems().indexOf(addPlayerPane.cmbExperience.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbFitness.getItems().indexOf(addPlayerPane.cmbFitness.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbSpeed.getItems().indexOf(addPlayerPane.cmbSpeed.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbFlick.getItems().indexOf(addPlayerPane.cmbFlick.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbBackhand.getItems().indexOf(addPlayerPane.cmbBackhand.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbCatching.getItems().indexOf(addPlayerPane.cmbCatching.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbOffense.getItems().indexOf(addPlayerPane.cmbOffense.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.cmbDefense.getItems().indexOf(addPlayerPane.cmbDefense.getValue()) +"\n");
							toClient.writeChars(addPlayerPane.tfPhoneNumber.getText()+"\n");

							toClient.flush();
						}

						catch(IOException ex) {
							ex.printStackTrace();
						}
						Platform.runLater(()->{	
							//Clear Player information to allow for another player to be added.
							addPlayerPane.tfFirstName.clear();
							addPlayerPane.tfLastName.clear();
							addPlayerPane.tfAge.clear();
							addPlayerPane.tfHeight.clear();
							addPlayerPane.tfPhoneNumber.clear();
							addPlayerPane.cmbExperience.setValue(null);
							addPlayerPane.cmbFitness.setValue(null);
							addPlayerPane.cmbSpeed.setValue(null);
							addPlayerPane.cmbFlick.setValue(null);
							addPlayerPane.cmbBackhand.setValue(null);
							addPlayerPane.cmbCatching.setValue(null);
							addPlayerPane.cmbOffense.setValue(null);
							addPlayerPane.cmbDefense.setValue(null);
						});

					});
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}).start();

		Scene scene = new Scene(addPlayerPane, 800, 450);
		primaryStage.setTitle("Player List");
		primaryStage.setScene(scene);
		primaryStage.show();
	}



	public static void main(String[] args) {
		Application.launch(args);
	}
}
