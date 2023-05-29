import java.util.ArrayList;

/*
 * User
 * Represents abstract User object
 * Author: Daniel Hubmann
 * Last Change: 29.05.2023
 */

public abstract class User {

	private String username;
	private String password;
	private String email;
	private ArrayList<Course> courseList = new ArrayList<Course>();

	// Getters & Setters
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

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	// Constructors
	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
