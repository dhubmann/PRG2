/*
 * AddCourseController
 * Represents Add Course logic
 * Author: Daniel Hubmann
 * Last Change: 28.04.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class AddCourseController {

	private AddCourseView addCourseView;
	private ScheduleController scheduleController;
	private Course course;

	// Getters & Setters
	public AddCourseView getAddCourseView() {
		return addCourseView;
	}

	public void setAddCourseView(AddCourseView addCourseView) {
		this.addCourseView = addCourseView;
	}

	public ScheduleController getScheduleController() {
		return scheduleController;
	}

	public void setScheduleController(ScheduleController scheduleController) {
		this.scheduleController = scheduleController;
	}

	public Course getCourse() {
		return course;
	}

	// Constructor
	public AddCourseController(AddCourseView addCourseView, ScheduleController scheduleController) {
		this.addCourseView = addCourseView;
		this.scheduleController = scheduleController;

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

				// TODO: implement new time preference settings
				// if course.starttime is before instructor.starttime
				// or if course.endttime is after instructor.endtime

				if (!preferredTime(startTime, endTime, instructor)) {
					notPreferredTime(instructor);
				}

				if (!preferredRoomEquipment(instructor, roomID)) {
					notPreferredRoomEquipment(instructor);
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

	// Compares course times with instructor's preferred start and end
	public boolean preferredTime(LocalTime startTime, LocalTime endTime, String instructor) {
		LocalTime start = null;
		LocalTime end = null;
		for (User u : Model.getTeachingStaff()) {
			if (u.getUsername().equals(instructor)) {

				if (u instanceof Administrator) {
					start = ((Administrator) u).getPreferredStartTime();
					end = ((Administrator) u).getPreferredEndTime();

				}

				if (u instanceof Assistent) {
					start = ((Assistent) u).getPreferredStartTime();
					end = ((Assistent) u).getPreferredEndTime();

				}

				if (startTime.isBefore(start) || endTime.isAfter(end)) {
					return false;
				}
			}
		}
		return true;
	}

	// Warning message
	public void notPreferredTime(String instructor) {
		LocalTime start = null;
		LocalTime end = null;
		for (User u : Model.getTeachingStaff()) {
			if (u.getUsername().equals(instructor)) {

				if (u instanceof Administrator) {
					start = ((Administrator) u).getPreferredStartTime();
					end = ((Administrator) u).getPreferredEndTime();

				}

				if (u instanceof Assistent) {
					start = ((Assistent) u).getPreferredStartTime();
					end = ((Assistent) u).getPreferredEndTime();

				}
			}
		}
		JOptionPane.showMessageDialog(null,
				"The course times do not match with " + instructor + "'s preferred course times " + "(" + start + " - "
						+ end + ")" + ".\nPlease inform " + instructor + ". Thank you!");
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

	private void notPreferredRoomEquipment(String instructor) {
		JOptionPane.showMessageDialog(null, "The room equipment does not match " + instructor
				+ "'s preference.\nPlease inform " + instructor + ". Thank you!");

	}

}
