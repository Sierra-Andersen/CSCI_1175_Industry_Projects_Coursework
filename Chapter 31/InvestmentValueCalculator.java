/*
 * Author: Sierra Andersen
 * Date: 15 Mar 2023
 * 
 * This program calculates the future value of an investment after a given number of years.
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;





public class InvestmentValueCalculator extends Application {


	@Override
	public void start(Stage primaryStage) {

		//Create TextFields
		TextField tfAmount = new TextField();
		TextField tfYears = new TextField();
		TextField tfRate = new TextField();
		TextField tfFutureValue = new TextField();

		//Make the text right-aligned in the TextFields
		tfAmount.setAlignment(Pos.CENTER_RIGHT);
		tfYears.setAlignment(Pos.CENTER_RIGHT);
		tfRate.setAlignment(Pos.CENTER_RIGHT);
		tfFutureValue.setAlignment(Pos.CENTER_RIGHT);

		tfFutureValue.setEditable(false);

		//Create Labels
		Label lblAmount = new Label("Investment Amount: ", tfAmount);
		Label lblYears = new Label("Number of Years: ", tfYears);
		Label lblRate = new Label("Annual Interest Rate (%): ", tfRate);
		Label lblFutureValue = new Label("Future Value: ", tfFutureValue);

		//Display the TextField on the right
		lblAmount.setContentDisplay(ContentDisplay.RIGHT);
		lblYears.setContentDisplay(ContentDisplay.RIGHT);
		lblRate.setContentDisplay(ContentDisplay.RIGHT);
		lblFutureValue.setContentDisplay(ContentDisplay.RIGHT);

		//Create a Button
		Button btCalculate = new Button("Calculate");
		

		
		
		//Calculate the future value when the Calculate button is clicked
		btCalculate.setOnAction(e->{
			//Information from TextFields
			double amount = Double.parseDouble(tfAmount.getText());
			int years = Integer.parseInt(tfYears.getText());
			double rate = Double.parseDouble(tfRate.getText())/100.0;
			
			//Calculate Future Value and Display
			tfFutureValue.setText(calculateFutureValue(amount, years, rate));
		});

		
		//Create a MenuBar with a menu and items
		MenuBar menuBar = new MenuBar();
		Menu menuOperation = new Menu("Operation");
		menuBar.getMenus().add(menuOperation);
		
		MenuItem itemCalculate = new MenuItem("Calculate");
		MenuItem itemExit = new MenuItem("Exit");
		
		menuOperation.getItems().addAll(itemCalculate, itemExit);
		
		
		itemCalculate.setOnAction(e->{
			//Information from TextFields
			double amount = Double.parseDouble(tfAmount.getText());
			int years = Integer.parseInt(tfYears.getText());
			double rate = Double.parseDouble(tfRate.getText())/100.0;
			
			//Calculate Future Value and Display
			tfFutureValue.setText(calculateFutureValue(amount, years, rate));
		});
		
		itemExit.setOnAction(e->{
			System.exit(0);
		});
		//Put the TextFields in a VBox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(menuBar, lblAmount, lblYears, lblRate, lblFutureValue, btCalculate);
		vBox.setAlignment(Pos.TOP_RIGHT);


		Scene scene = new Scene(vBox, 350, 200);

		primaryStage.setTitle("Investment Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public String calculateFutureValue(double amount, int years, double rate) {
		double futureValue = amount * Math.pow((1+rate/12),(years*12));
		String formattedValue = "$" + Math.round(futureValue*100)/100.0;
		
		return formattedValue;
	}



	public static void main(String[] args) {
		Application.launch(args);
	}

}
