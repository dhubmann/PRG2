/*
 * Student
 * Represents Student object
 * Author: Daniel Hubmann
 * Last Change: 27.05.2023
 */

public class Student extends User {

	// Constructors
	public Student() {
		super();
		Model.getStudents().add(this);
	}

	public Student(String username, String password, boolean isAdmin, boolean isAssistant) {
		super(username, password, isAdmin, isAssistant);
		Model.getStudents().add(this);
	}

	public Student(String username, String password, String email, boolean isAdmin, boolean isAssistant) {
		super(username, password, email, isAdmin, isAssistant);
		Model.getStudents().add(this);
	}

}
