/*
 * AddCourseController
 * Represents Add Course logic
 * Author: Daniel Hubmann
 * Last Change: 28.04.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class AddCourseController {

	private AddCourseView addCourseView;
	private Course course;

	// Getters & Setters
	public AddCourseView getAddCourseView() {
		return addCourseView;
	}

	public void setAddCourseView(AddCourseView addCourseView) {
		this.addCourseView = addCourseView;
	}

	public Course getCourse() {
		return course;
	}

	// Constructor
	public AddCourseController(AddCourseView addCourseView) {
		this.setAddCourseView(addCourseView);

		addCourseView.getBtnAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String courseTitle = addCourseView.getTfCourseTitle().getText();
				String roomID = (String) addCourseView.getCbRoom().getSelectedItem();
				LocalTime startTime = (LocalTime) addCourseView.getCbStartTime().getSelectedItem();
				LocalTime endTime = (LocalTime) addCourseView.getCbEndTime().getSelectedItem();
				String instructor = (String) addCourseView.getCbInstructor().getSelectedItem(); // username

				/*
				 * if input is valid, create new course object
				 */

				if (InputValidator.checkBlankInput(courseTitle)) {
					blankCourseTitle();
					return;
				}

				if (courseTitle.length() > 20) {
					courseTitleTooLong();
					return;
				}

				if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
					invalidTimes();
					return;
				}

				if (!preferredTime(startTime, instructor)) {
					notPreferredTime();
				}

				// TODO: implement room equipement preferenc of instructor
				if (preferredRoomEquipment(instructor, roomID)) {
					System.out.println("PREF");
				} else {
					System.out.println("NOT PREF");
				}

				course = new Course(courseTitle, roomID, startTime, endTime, instructor);

				// TODO: testing
				if (courseOverlap()) {
					roomNotAvailable();
					removeCourseData();
					return;
				}

				if (instructorOverlap()) {
					instructorNotAvailable();
					removeCourseData();
					return;
				}

				Model.getCourses().add(course);
				addCourseView.dispose();

			}

		});
	}

	/*
	 * Overlap check methods & Warnings
	 */
	public void blankCourseTitle() {
		JOptionPane.showMessageDialog(null, "Please enter a course title.");
	}

	public void courseTitleTooLong() {
		JOptionPane.showMessageDialog(null,
				"Course titles must not be longer than 20 characters.\nPlease use abbrevations if possible.");
	}

	public void invalidTimes() {
		JOptionPane.showMessageDialog(null, "We're sorry - the start and end times you entered are invalid.");
	}

	private void removeCourseData() {
		course = null;
		Course.setNumCourses(Course.getNumCourses() - 1);
	}

	// Checks if the room is available
	private boolean courseOverlap() {
		for (Course c : Model.getCourses()) {
			if (!course.getCourseID().equals(c.getCourseID()) && course.getRoomID().equals(c.getRoomID())
					&& course.overlap(c)) {
				return true;
			}
		}
		return false;
	}

	private void roomNotAvailable() {
		JOptionPane.showMessageDialog(null,
				"We're sorry - " + course.getRoomID() + " is not available at the times you entered.");
	}

	// Checks if the instructor is available
	public boolean instructorOverlap() {
		for (Course c : Model.getCourses()) {
			if (!course.getCourseID().equals(c.getCourseID()) && c.getInstructor().equals(course.getInstructor())
					&& course.overlap(c)) {
				return true;
			}
		}
		return false;
	}

	public void instructorNotAvailable() {
		JOptionPane.showMessageDialog(null,
				"We're sorry - " + course.getInstructor() + " is not available at the times you entered.");
	}

	// Compares course start time with preferred start time of instructor
	public boolean preferredTime(LocalTime startTime, String instructor) {
		LocalTime preferredStartTime = null;
		for (User u : Model.getTeachingStaff()) {
			if (u.getUsername().equals(instructor)) {

				if (u instanceof Administrator) {
					preferredStartTime = ((Administrator) u).getPreferredStartTime();
				}

				if (u instanceof Assistent) {
					preferredStartTime = ((Assistent) u).getPreferredStartTime();
				}

				Duration d = Duration.between(preferredStartTime, startTime);
				long difference = Math.abs(d.toHours());
				if (difference >= 2) {
					return false;
				}
			}
		}
		return true;
	}

	public void notPreferredTime() {
		JOptionPane.showMessageDialog(null,
				"The course start time is off by at least 2 hours compared to the instructor's preference.\nPlease inform the instructor. Thank you!");
	}

	// Compares room equipment preference of instructor with room equipment
	public boolean preferredRoomEquipment(String instructor, String roomID) {

		// TODO:
		for (Room r : Model.getRooms()) {
			if (r.getRoomID().equals(roomID)) {

				for (User u : Model.getTeachingStaff()) {
					if (u instanceof Administrator && u.getUsername().equals(instructor)) {

						if (((Administrator) u).getPreferredRoomEquipment().equals(r.getRoomEquipment())) {
							return true;
						}
					}

					if (u instanceof Assistent && u.getUsername().equals(instructor)) {

						if (((Assistent) u).getPreferredRoomEquipment().equals(r.getRoomEquipment())) {
							return true;
						}
					}

				}

			}
		}
		return false;
	}
}
