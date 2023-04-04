/*
 * Author: Sierra Andersen
 * Date: 30 Mar 2023
 * 
 * This is a class that creates a table of players
 */


import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerTable extends TableView {

	PlayerTable(ObservableList<Player> data){
		this.setItems(data);
		paintTable();
	}

	//Set up the columns in the table
	public void paintTable() {
		TableColumn<Player,String> firstNameCol = new TableColumn<Player,String>("First");
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));

		TableColumn<Player, String> lastNameCol = new TableColumn<Player, String>("Last");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));

		TableColumn nameCol = new TableColumn("Name");
		nameCol.getColumns().addAll(firstNameCol, lastNameCol);


		TableColumn<Player, Integer> ageCol = new TableColumn<Player, Integer>("Age");
		ageCol.setMinWidth(100);
		ageCol.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));


		TableColumn<Player, Double> heightCol = new TableColumn<Player, Double>("Height (in)");
		heightCol.setMinWidth(100);
		heightCol.setCellValueFactory(new PropertyValueFactory<Player, Double>("height"));



		TableColumn<Player,Player.ExperienceLevel> experienceCol = new TableColumn<Player, Player.ExperienceLevel>("Experience");
		experienceCol.setMinWidth(100);
		experienceCol.setCellValueFactory(new PropertyValueFactory<Player, Player.ExperienceLevel>("experience"));

		TableColumn<Player, Player.Ability> fitnessCol = new TableColumn<Player, Player.Ability>("Fitness");
		fitnessCol.setMinWidth(100);
		fitnessCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("fitness"));

		TableColumn<Player, Player.Ability> speedCol = new TableColumn<Player, Player.Ability>("Speed");
		speedCol.setMinWidth(100);
		speedCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("speed"));

		TableColumn<Player, Player.Ability> flickCol = new TableColumn<Player, Player.Ability>("Flick");
		flickCol.setMinWidth(100);
		flickCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("flick"));

		TableColumn<Player, Player.Ability> backhandCol = new TableColumn<Player, Player.Ability>("Backhand");
		backhandCol.setMinWidth(100);
		backhandCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("backhand"));

		TableColumn<Player, Player.Ability> overAllCol = new TableColumn<Player, Player.Ability>("Overall");
		overAllCol.setMinWidth(100);
		overAllCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("offense"));

		TableColumn<Player, Player.Ability> catchCol = new TableColumn<Player, Player.Ability>("Catch");
		catchCol.setMinWidth(100);
		catchCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("catching"));

		TableColumn<Player, Player.Ability> offenseCol = new TableColumn<Player, Player.Ability>("Offense");
		offenseCol.getColumns().addAll(flickCol, backhandCol, overAllCol);

		TableColumn<Player, Player.Ability> defenseCol = new TableColumn<Player, Player.Ability>("Defense");
		defenseCol.setMinWidth(100);
		defenseCol.setCellValueFactory(new PropertyValueFactory<Player, Player.Ability>("defense"));

		this.getColumns().addAll(nameCol, ageCol, heightCol, 
				experienceCol, fitnessCol, speedCol, catchCol, offenseCol, defenseCol);
	}
}
