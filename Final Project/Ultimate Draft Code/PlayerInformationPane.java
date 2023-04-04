/*
 * Author: Sierra Andersen
 * Date: 22 Mar 2023
 * This is a class that extends a FlowPane to create a space for player Information
 */


import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PlayerInformationPane extends FlowPane {

	public TextField tfFirstName = new TextField();
	public TextField tfLastName = new TextField();
	public TextField tfAge = new TextField();
	public TextField tfHeight = new TextField();
	public TextField tfPhoneNumber = new TextField();

	public ComboBox<Player.ExperienceLevel> cmbExperience = new ComboBox<Player.ExperienceLevel>();
	public ComboBox<Player.Ability> cmbFitness = new ComboBox<Player.Ability> ();
	public ComboBox<Player.Ability>  cmbSpeed = new ComboBox<Player.Ability> ();
	public ComboBox<Player.Ability>  cmbFlick = new ComboBox<Player.Ability> ();
	public ComboBox<Player.Ability>  cmbBackhand = new ComboBox<Player.Ability> ();
	public ComboBox<Player.Ability>  cmbCatching = new ComboBox<Player.Ability> ();
	public ComboBox<Player.Ability>  cmbOffense = new ComboBox<Player.Ability> ();
	public ComboBox<Player.Ability>  cmbDefense = new ComboBox<Player.Ability> ();


	public PlayerInformationPane() {
		paintPlayerPane();
	}


	public void paintPlayerPane(){

		Label lblFirstName = new Label("First Name:",tfFirstName);
		lblFirstName.setContentDisplay(ContentDisplay.RIGHT);


		Label lblLastName =  new Label("Last Name:", tfLastName);
		lblLastName.setContentDisplay(ContentDisplay.RIGHT);


		Label lblAge = new Label("Age:", tfAge);
		lblAge.setContentDisplay(ContentDisplay.RIGHT);


		Label lblHeight = new Label("Height:",tfHeight);
		lblHeight.setContentDisplay(ContentDisplay.RIGHT);



		Label lblPhoneNumber = new Label("Phone Number:", tfPhoneNumber);
		lblPhoneNumber.setContentDisplay(ContentDisplay.RIGHT);

		Label lblExperience = new Label("Experience", cmbExperience);
		lblExperience.setContentDisplay(ContentDisplay.RIGHT);

		//Fill the ComboBox with values from the enum ExperienceLevel
		for (int i = 0; i< Player.ExperienceLevel.values().length; i++) {
			cmbExperience.getItems().add(Player.ExperienceLevel.values()[i]);
		}



		Label lblFitness = new Label ("Fitness", cmbFitness);
		lblFitness.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbFitness);


		Label lblSpeed = new Label("Speed", cmbSpeed);
		lblSpeed.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbSpeed);


		Label lblFlick = new Label("Flick:", cmbFlick);
		lblFlick.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbFlick);


		Label lblBackhand = new Label("Backhand:", cmbBackhand);
		lblBackhand.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbBackhand);


		Label lblCatching = new Label("Catching:", cmbCatching);
		lblCatching.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbCatching);


		Label lblOffense = new Label("Offense:", cmbOffense);
		lblOffense.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbOffense);


		Label lblDefense = new Label("Defense:", cmbDefense);
		lblDefense.setContentDisplay(ContentDisplay.RIGHT);
		generateComboBox(cmbDefense);


		VBox vbBasicInfo = new VBox(20);
		vbBasicInfo.setAlignment(Pos.TOP_RIGHT);
		Text titleBI = new Text("Player Information:");
		titleBI.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
		vbBasicInfo.getChildren().addAll(titleBI, lblFirstName, lblLastName, lblAge, lblHeight, lblPhoneNumber);

		VBox vbAbilityInfo = new VBox(20);
		vbAbilityInfo.setAlignment(Pos.TOP_RIGHT);
		Text titleAI = new Text("Player Ability:");
		titleAI.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
		vbAbilityInfo.getChildren().addAll(titleAI, lblExperience, lblFitness, lblSpeed, lblFlick, lblBackhand, lblCatching, lblOffense, lblDefense);

		this.getChildren().addAll(vbBasicInfo, vbAbilityInfo);
	}

	public void generateComboBox(ComboBox<Player.Ability> cbo) {
		for (int i = 0; i< Player.Ability.values().length; i++) {
			cbo.getItems().add(Player.Ability.values()[i]);
		}
	}
}
