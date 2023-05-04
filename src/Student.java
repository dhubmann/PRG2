/*
 * Student
 * Represents Student object
 * Author: Daniel Hubmann
 * Last Change: 28.04.2023
 */

import java.util.ArrayList;

public class Student extends User {

	private ArrayList<Course> courseList;

	// Getters & Setters
	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	// Constructors
	public Student() {
		super();
		Model.getStudents().add(this);
	}

	public Student(String username, String password, boolean isAdmin, boolean isAssistent) {
		super(username, password, isAdmin, isAssistent);
		Model.getStudents().add(this);
	}
	
	public Student(String username, String password, String email, boolean isAdmin, boolean isAssistent) {
		super(username, password, email, isAdmin, isAssistent);
		Model.getStudents().add(this);
	}

}
