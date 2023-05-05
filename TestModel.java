import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

/*
 * TestModel
 * Represents example data
 * Author: Daniel Hubmann
 * Last Change: 28.04.2023
 */

public class TestModel {

	private ScheduleView scheduleView;
	private ScheduleController scheduleController;

	public static void loadTestUsers() {

		// Users
		User admin1 = new Administrator("emayer", "1234", true, false); // Test Admin
		User admin2 = new Administrator("hbower", "1234", true, false);
		User assistent1 = new Assistent("slubos", "1234", false, true); // Test Assistent
		User assistent2 = new Assistent("rhohensinner", "1234", false, true);
		User student1 = new Student("dhubmann", "1234", false, false); // Test Student
		User student2 = new Student("kmueller", "1234", false, false);
		User student3 = new Student("ekulmer", "1234", false, false);
		User student4 = new Student("rhofer", "1234", false, false);

		// Start Time Preferences
//		admin1.setPreferredStartTime(Model.getTimes()[2]); // emayer: 09:00
//		admin2.setPreferredStartTime(Model.getTimes()[5]); // hbower: 10:30
//		assistent1.setPreferredStartTime(Model.getTimes()[8]); // slubos: 12:00
//		assistent2.setPreferredStartTime(Model.getTimes()[13]); // rhohensinner: 14:30

		// TODO: Room Equipment Preferences

	}

	public static void loadTestRooms(ScheduleView scheduleView, ScheduleController scheduleController) {

		// Rooms
//		Room room1 = new Room();
//		room1.setPanelRoomColumn(scheduleController.newRoomColumn(room1));
//		Room room2 = new Room();
//		room2.setPanelRoomColumn(scheduleController.newRoomColumn(room2));
//		Room room3 = new Room();
//		room3.setPanelRoomColumn(scheduleController.newRoomColumn(room3));

	}

	public static void loadTestCourses(ScheduleController scheduleController) {

		// Courses
		// MAT2, R1, 08:00 - 10:00, emayer
		Course course1 = new Course("MAT2", "R1", Model.getTimes()[0], Model.getTimes()[4],
				Model.getTeachingStaff().get(0).getUsername());
		scheduleController.newCourseBlock(course1);

		// Professional English, R1, 10:00 - 11:00, hbower
		Course course2 = new Course("Professional English", "R1", Model.getTimes()[4], Model.getTimes()[6],
				Model.getTeachingStaff().get(1).getUsername());
		scheduleController.newCourseBlock(course2);

		// WTU, R2, 13:00 - 15:30, slubos
		Course course3 = new Course("WTU", "R2", Model.getTimes()[10], Model.getTimes()[15],
				Model.getTeachingStaff().get(2).getUsername());
		scheduleController.newCourseBlock(course3);

		// DAI, R3, 18:00 - 20:00, rhohensinner
		Course course4 = new Course("Yoga for Beginners", "R3", Model.getTimes()[20], Model.getTimes()[24],
				Model.getTeachingStaff().get(3).getUsername());
		scheduleController.newCourseBlock(course4);

		// Courses
		Course course5 = new Course("Cooking with Sabine", "R1", Model.getTimes()[6], Model.getTimes()[11],
				Model.getTeachingStaff().get(2).getUsername());
		scheduleController.newCourseBlock(course5);
		Course course6 = new Course("Art History", "R2", Model.getTimes()[7], Model.getTimes()[10],
				Model.getTeachingStaff().get(1).getUsername());
		scheduleController.newCourseBlock(course6);

	}

	public static void clearTestModel(ScheduleController scheduleController, ScheduleView scheduleView) {
		Model.getRooms().clear();
		Model.getTeachingStaff().clear();
		Room.setNumRooms(0);
		Course.setNumCourses(0);
	}

	// Optional?!
	// TODO: implement read/write from/to CSV file
	// Write Model data to file
	public static void saveData() {
		try {
			FileOutputStream fos = new FileOutputStream("data.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Model.getRooms());
			oos.writeObject(Model.getCourses());
			oos.writeObject(Model.getTeachingStaff());
			oos.writeObject(Model.getStudents());
			oos.close();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
