package sk.paz1c.Elibrary.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reader {

	private Long id;
	private String name;
	private String surname;
	private String username;
	private String password = "";
	private boolean isAdmin;
	private String gender;
	private LocalDateTime birthDate;
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
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
		isAdmin = (permission == 1 )? true :false; 
	}

	public boolean getAdmin() {
		return isAdmin;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + ", isAdmin=" + isAdmin + "]";
	}
	
	

}
