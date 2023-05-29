/*
 * Student
 * Represents Student object
 * Author: Daniel Hubmann
 * Last Change: 29.05.2023
 */

public class Student extends User {

	// Constructors
	public Student() {
		super();
		Model.getStudents().add(this);
	}

	public Student(String username, String password) {
		super(username, password);
		Model.getStudents().add(this);
	}

	public Student(String username, String password, String email) {
		super(username, password, email);
		Model.getStudents().add(this);
	}

}
