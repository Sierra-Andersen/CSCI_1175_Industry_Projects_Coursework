// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Exercise33_01Server extends Application {
	// Text area for displaying contents
	private TextArea ta = new TextArea();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		ta.setWrapText(true);

		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 400, 200);
		primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(8000);

				Platform.runLater(()->
				ta.appendText("Server started at " + new Date() + "\n"));

				Socket socket = serverSocket.accept();

				DataInputStream fromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

				while(true) {
					double rate = fromClient.readDouble();
					int years = fromClient.readInt();
					double amount = fromClient.readDouble();

					Loan loan = new Loan(rate, years, amount);
					
					double monthlyPay = Math.round(loan.getMonthlyPayment()*100)/100.0;
					double totalPay = Math.round(loan.getTotalPayment()*100)/100.0;

					toClient.writeDouble(monthlyPay);
					toClient.writeDouble(totalPay);

					Platform.runLater(()->{
						ta.appendText("Rate received from client: "+ rate + "\n");
						ta.appendText("Loan length received from client: "+ years + " years\n");
						ta.appendText("Loan Amount received from client: $"+ amount + "\n");
						ta.appendText("Monthly Payment: $" + monthlyPay+ "\n");
						ta.appendText("Total Payment: $" + totalPay + "\n");
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
