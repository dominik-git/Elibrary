package sk.paz1c.Elibrary.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sk.paz1c.Elibrary.modelFx.ReaderFx;

public class Reader {

	private Long id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private boolean isAdmin;
	private String gender;
	private Date birthDate;
	private String email;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(int permission) {
		isAdmin = (permission == 1) ? true : false;
	}

	public boolean getAdmin() {
		return isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFullName() {
		return this.name+" "+this.surname;
	}

	// put values from Readerfx model to reader
	public void mapFxReaderToReader(ReaderFx readerFxModel) {
		name = readerFxModel.getName();
		username = readerFxModel.getUsername();
		surname = readerFxModel.getSurname();
		password = readerFxModel.getPassword();
		// convert localDate to Date because ReaderFx model use localDate, but we need
		// to save as date bcs we use date in database
		Date date = java.sql.Date.valueOf(readerFxModel.getDate());
		birthDate = date;
		gender = readerFxModel.getGender();
		email = readerFxModel.getEmail();
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + ", isAdmin=" + isAdmin + "]";
	}

}
