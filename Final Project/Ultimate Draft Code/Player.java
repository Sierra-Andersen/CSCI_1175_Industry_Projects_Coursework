/*
 * Author: Sierra Andersen
 * Date: 22 Mar 2023
 * 
 * This is a Player object that represents a player in a sport that can be put on a team.
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;


public class Player implements Serializable {

	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleIntegerProperty age;
	private SimpleDoubleProperty height;
	public enum ExperienceLevel {None, HighSchool, League, College, Club};
	private SimpleStringProperty experience;
	public enum Ability {Poor, BelowAverage, Average, AboveAverage, Excellent};
	private SimpleStringProperty fitness;
	private SimpleStringProperty speed;
	private SimpleStringProperty flick;
	private SimpleStringProperty backhand;
	private SimpleStringProperty catching;
	private SimpleStringProperty offense;
	private SimpleStringProperty defense;
	private SimpleStringProperty phoneNumber;

	Player(String firstName, String lastName, int age, double height, ExperienceLevel experience, 
			Ability fitness, Ability speed, Ability flick, Ability backhand, Ability catching,
			Ability offense, Ability defense, String phoneNumber){
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.age = new SimpleIntegerProperty(age);
		this.height = new SimpleDoubleProperty(height);
		this.experience = new SimpleStringProperty(experience.name());
		this.fitness = new SimpleStringProperty(fitness.name());
		this.speed = new SimpleStringProperty(speed.name());
		this.flick = new SimpleStringProperty(flick.name());
		this.backhand = new SimpleStringProperty(backhand.name());
		this.catching = new SimpleStringProperty(catching.name());
		this.offense = new SimpleStringProperty(offense.name());
		this.defense = new SimpleStringProperty(defense.name());
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public double getHeight() {
		return height.get();
	}

	public void setHeight(double height) {
		this.height.set(height);
	}

	public ExperienceLevel getExperience(){
		return ExperienceLevel.valueOf(experience.get());
	}

	public void setExperience(ExperienceLevel experience) {
		this.experience.set(experience.name());
	}

	public Ability getFitness(){
		return Ability.valueOf(fitness.get());
	}

	public void setFitness(Ability fitness) {
		this.fitness.set(fitness.name());
	}

	public Ability getSpeed(){
		return Ability.valueOf(speed.get());
	}

	public void setSpeed(Ability speed) {
		this.speed.set(speed.name());
	}

	public Ability getFlick(){
		return Ability.valueOf(flick.get());
	}

	public void setFlick(Ability flick) {
		this.flick.set(flick.name());
	}

	public Ability getBackhand(){
		return Ability.valueOf(backhand.get());
	}

	public void setBackhand(Ability backhand) {
		this.backhand.set(backhand.name());
	}

	public Ability getCatching(){
		return Ability.valueOf(catching.get());
	}

	public void setCatching(Ability catching) {
		this.catching.set(catching.name());
	}

	public Ability getOffense(){
		return Ability.valueOf(offense.get());
	}

	public void setOffense(Ability offense) {
		this.offense.set(offense.name());
	}

	public Ability getDefense(){
		return Ability.valueOf(defense.get());
	}

	public void setDefense(Ability defense) {
		this.defense.set(defense.name());
	}

}
