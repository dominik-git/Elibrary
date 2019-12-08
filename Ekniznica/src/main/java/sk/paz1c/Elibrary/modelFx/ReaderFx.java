package sk.paz1c.Elibrary.modelFx;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.beans.property.LongProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.paz1c.Elibrary.model.Reader;

public class ReaderFx {
	
	private LongProperty id =  new SimpleLongProperty();
	private StringProperty name=  new SimpleStringProperty();
	private StringProperty surname = new SimpleStringProperty();
	private StringProperty username = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty passwordRepeat = new SimpleStringProperty();
	private BooleanProperty isAdmin = new SimpleBooleanProperty();
	private StringProperty gender = new SimpleStringProperty();
	 private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
	private StringProperty email = new SimpleStringProperty();
	
	

	public StringProperty nameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	
	
	public LongProperty idProperty() {
		return id;
	}

	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}
	
	

	public StringProperty surnameProperty() {
		return surname;
	}

	public String getSurname() {
		return surname.get();
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}
	
	

	public StringProperty usernameProperty() {
		return username;
	}
	public String getUsername() {
		return username.get();
	}
	public void setUsername(String username) {
		this.username.set(username);
	}
	
	
	public StringProperty passwordProperty() {
		return password;
	}
	public String getPassword() {
		return password.get();
	}
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	

	public BooleanProperty isAdminProperty() {
		return isAdmin;
	}
	public Boolean getIsAdmin() {
		return isAdmin.get();
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin.set(isAdmin);
	}
	
	
	public StringProperty genderProperty() {
		return gender;
	}
	public String getGender() {
		return gender.get();
	}
	public void setGender(String gender) {
		this.gender.set(gender);
	}
	

	
	public ObjectProperty<LocalDate> dateProperty() {
		return date;
	}
	public LocalDate getDate() {
		return date.get();
	}
	public void setDate(LocalDate date) {
		this.date.set(date);
	}
	
	
	public StringProperty emailProperty() {
		return email;
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	
	public StringProperty passwordRepeatProperty() {
		return passwordRepeat;
	}
	public String getPasswordRepeat() {
		return passwordRepeat.get();
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat.set(passwordRepeat);;
	}
	
	
	
	


}
