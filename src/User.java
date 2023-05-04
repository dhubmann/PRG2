/*
 * User
 * Represents abstract User object
 * Author: Daniel Hubmann
 * Last Change: 26.04.2023
 */

import java.util.UUID;

public abstract class User {

	private UUID userID;
	private String username;
	private String password;
	private String email;
	private boolean isAdmin;
	private boolean isAssistent;

	// Getters & Setters
	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAssistent() {
		return isAssistent;
	}

	public void setAssistent(boolean isAssistent) {
		this.isAssistent = isAssistent;
	}

	// Constructors
	public User() {

	}

	public User(String username, String password, boolean isAdmin, boolean isAssistent) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isAssistent = isAssistent;
	}

	public User(String username, String password, String email, boolean isAdmin, boolean isAssistent) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isAssistent = isAssistent;
	}

}
