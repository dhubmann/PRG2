/*
 * Course
 * Represents Course object
 * Author: Daniel Hubmann
 * Last Change: 27.05.2023
 */

import java.awt.event.MouseListener;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Course {

	private String courseID;
	private String title;
	private String roomID;
	private LocalTime startTime;
	private LocalTime endTime;
	private String instructor;
	private boolean isAttendable = true;
	private JButton btnDeleteCourse;
	private JLabel lblCourseBlock;
	private MouseListener mouseListener;
	private JPanel panelCourseBlock;
	private ArrayList<Student> participants;
	private static int numCourses;

	// Getters & Setters
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoom(String roomID) {
		this.roomID = roomID;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public boolean isAttendable() {
		return isAttendable;
	}

	public void setAttendable(boolean isAttendable) {
		this.isAttendable = isAttendable;
	}

	public JButton getBtnDeleteCourse() {
		return btnDeleteCourse;
	}

	public void setBtnDeleteCourse(JButton btnDeleteCourse) {
		this.btnDeleteCourse = btnDeleteCourse;
	}

	public JLabel getLblCourseBlock() {
		return lblCourseBlock;
	}

	public void setLblCourseBlock(JLabel lblCourseBlock) {
		this.lblCourseBlock = lblCourseBlock;
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public JPanel getPanelCourseBlock() {
		return panelCourseBlock;
	}

	public void setPanelCourseBlock(JPanel panelCourseBlock) {
		this.panelCourseBlock = panelCourseBlock;
	}

	public ArrayList<Student> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Student> participants) {
		this.participants = participants;
	}

	public static int getNumCourses() {
		return numCourses;
	}

	public static void setNumCourses(int numCourses) {
		Course.numCourses = numCourses;
	}

	// Constructors
	public Course() {
		numCourses++;
		this.courseID = "C" + numCourses;
		this.participants = new ArrayList<Student>();
	}

	public Course(String title, String roomID, LocalTime startTime, LocalTime endTime, String instructor) {
		numCourses++;
		this.courseID = "C" + numCourses;
		this.title = title;
		this.roomID = roomID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.instructor = instructor;
		this.participants = new ArrayList<Student>();
	}

	// Checks for overlapping times
	public boolean overlap(Course course) {
		if (this.getStartTime().equals(course.getEndTime()) || this.getEndTime().equals(course.getStartTime())) {
			return false;
		}

		if (this.getStartTime().isAfter(course.getEndTime()) || this.getEndTime().isBefore(course.getStartTime())) {
			return false;
		}

		return true;
	}

}
