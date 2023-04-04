/*
 * Author: Sierra Andersen
 * Date: 29 Mar 2023
 * 
 * This is a Client that allows a captain to select players from the server and organize a team.
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import java.io.*;
import java.net.*;



public class CaptainClient extends Application {

	@Override
	public void start(Stage primaryStage) {

		MenuBar menuBar = new MenuBar();

		//Set up File menu
		Menu menuFile = new Menu("File");
		MenuItem miSelectPlayer = new MenuItem("Select Player");
		MenuItem miExit = new MenuItem("Exit");
		menuFile.getItems().addAll(miSelectPlayer, miExit);

		//Set up View menu
		Menu menuView = new Menu("View");
		MenuItem miPlayerList = new MenuItem("All Players");
		MenuItem miTeamList = new MenuItem("Team List");
		MenuItem miPlayerInfo = new MenuItem("Player Info");
				
		menuView.getItems().addAll(miPlayerList, miTeamList, miPlayerInfo);
		menuBar.getMenus().addAll(menuFile, menuView);

		//Exit program when Exit is selected
		miExit.setOnAction(e-> System.exit(0));


		TableView<Player> teamView = new TableView<Player>();

		ObservableList<Player> olAllPlayers = FXCollections.observableArrayList();
		ObservableList<Player> olTeamPlayers = FXCollections.observableArrayList();
		
		teamView.setItems(olTeamPlayers);

		//Set up the columns in the table
		TableColumn<Player,String> firstNameCol = new TableColumn<Player,String>("First");
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));

		TableColumn<Player, String> lastNameCol = new TableColumn<Player, String>("Last");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));

		teamView.getColumns().addAll(firstNameCol, lastNameCol);

		TabPane tabPane = new TabPane();
		Tab tabTeamList = new Tab("Team List");
		tabTeamList.setContent(teamView);

		Tab tabAllPlayers = new Tab("All Players");
		PlayerTable availablePlayersTable = new PlayerTable(olAllPlayers);
		
		Button btSelect = new Button("Select Player");
		VBox vbAllPlayers = new VBox();
		vbAllPlayers.getChildren().addAll(availablePlayersTable, btSelect);
		tabAllPlayers.setContent(vbAllPlayers);

		tabPane.getTabs().addAll(tabTeamList, tabAllPlayers);

		VBox vbTeamDisplay = new VBox();
		vbTeamDisplay.getChildren().addAll(menuBar, tabPane);

		miPlayerList.setOnAction(e->{
			tabPane.getTabs().remove(tabAllPlayers);
			tabPane.getTabs().add(tabAllPlayers);
		});
		
		miTeamList.setOnAction(e->{
			tabPane.getTabs().remove(tabTeamList);
			tabPane.getTabs().add(tabTeamList);
		});
		
		
		btSelect.setOnAction(e->{
			olTeamPlayers.add((Player)availablePlayersTable.getSelectionModel().getSelectedItem());
			availablePlayersTable.getItems().remove(availablePlayersTable.getSelectionModel().getSelectedIndex());
		});
		
		miSelectPlayer.setOnAction(e->btSelect.fire());
		
		miPlayerInfo.setOnAction(e->{
			Tab tabPlayerStats = new Tab("Player Stats");
			
			PlayerInformationPane playerInfoPane = new PlayerInformationPane();
			Player currentPlayer = (Player)teamView.getSelectionModel().getSelectedItem();
			
			playerInfoPane.tfFirstName.setEditable(false);
			playerInfoPane.tfLastName.setEditable(false);
			playerInfoPane.tfAge.setEditable(false);
			playerInfoPane.tfHeight.setEditable(false);
			playerInfoPane.cmbExperience.getItems().clear();
			playerInfoPane.cmbFitness.getItems().clear();
			playerInfoPane.cmbSpeed.getItems().clear();
			playerInfoPane.cmbFlick.getItems().clear();
			playerInfoPane.cmbBackhand.getItems().clear();
			playerInfoPane.cmbCatching.getItems().clear();
			playerInfoPane.cmbOffense.getItems().clear();
			playerInfoPane.cmbDefense.getItems().clear();
			playerInfoPane.tfPhoneNumber.setEditable(false);
			
			playerInfoPane.tfFirstName.setText(currentPlayer.getFirstName());			
			playerInfoPane.tfLastName.setText(currentPlayer.getLastName());
			playerInfoPane.tfAge.setText(currentPlayer.getAge()+"");
			playerInfoPane.tfHeight.setText(currentPlayer.getHeight()+"");	
			
			playerInfoPane.cmbExperience.getItems().add(currentPlayer.getExperience());
			playerInfoPane.cmbExperience.getSelectionModel().select(currentPlayer.getExperience());
			playerInfoPane.cmbFitness.getItems().add(currentPlayer.getFitness());
			playerInfoPane.cmbFitness.getSelectionModel().select(currentPlayer.getFitness());
			playerInfoPane.cmbSpeed.getItems().add(currentPlayer.getSpeed());
			playerInfoPane.cmbSpeed.getSelectionModel().select(currentPlayer.getSpeed());
			playerInfoPane.cmbFlick.getItems().add(currentPlayer.getFlick());
			playerInfoPane.cmbFlick.getSelectionModel().select(currentPlayer.getFlick());
			playerInfoPane.cmbBackhand.getItems().add(currentPlayer.getBackhand());
			playerInfoPane.cmbBackhand.getSelectionModel().select(currentPlayer.getBackhand());
			playerInfoPane.cmbCatching.getItems().add(currentPlayer.getCatching());
			playerInfoPane.cmbCatching.getSelectionModel().select(currentPlayer.getCatching());
			playerInfoPane.cmbOffense.getItems().add(currentPlayer.getOffense());
			playerInfoPane.cmbOffense.getSelectionModel().select(currentPlayer.getOffense());
			playerInfoPane.cmbDefense.getSelectionModel().select(currentPlayer.getDefense());
			playerInfoPane.cmbDefense.getItems().add(currentPlayer.getDefense());
			playerInfoPane.tfPhoneNumber.setText(currentPlayer.getPhoneNumber()+"");
						
			tabPlayerStats.setContent(playerInfoPane);
			tabPane.getTabs().add(tabPlayerStats);
			
		});


		Scene scene = new Scene(vbTeamDisplay, 1200, 500);
		primaryStage.setTitle("Player List");
		primaryStage.setScene(scene);
		primaryStage.show();

		//Connection with Server and Client
		new Thread(()->{
			try {
				Socket socket = new Socket("localhost", 8000);

				BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true) {
					try {						
					 Player player= new Player(
						fromServer.readLine(),
						fromServer.readLine(),
						Integer.parseInt(removeNull(fromServer.readLine())),
						Double.parseDouble(removeNull(fromServer.readLine())),
						Player.ExperienceLevel.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],  
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						Player.Ability.values()[Integer.parseInt(removeNull(fromServer.readLine()))],
						fromServer.readLine());
						
						olAllPlayers.add(player);
						
						
						
						Platform.runLater(()->{							
							tabPane.getTabs().remove(tabAllPlayers);
							tabPane.getTabs().add(tabAllPlayers);
						});
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

			catch(IOException ex) {
				ex.printStackTrace();
			}

		}).start();

	}


	public static void main(String[] args) {
		Application.launch(args);
	}

	
	public String removeNull(String problem) {
		String newString = "";
		
		for(int i= 0; i<problem.length(); i++) {
			if(problem.charAt(i)!=(0)) {
				newString += problem.charAt(i);
			}
		}
		
		return newString;
	}
}
