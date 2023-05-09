/*
 * Model
 * Represents application data
 * Author: Daniel Hubmann
 * Last Change: 08.05.2023
 */

import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Model {

	LoginView loginView;
	LoginController LoginController;
	ScheduleView scheduleView;
	ScheduleController scheduleController;

	private static ArrayList<Room> rooms = new ArrayList<Room>();
	private static ArrayList<JPanel> roomColumns = new ArrayList<JPanel>(); // lets try this

	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static ArrayList<User> teachingStaff = new ArrayList<User>();
	private static ArrayList<User> students = new ArrayList<User>();
	private static final int MAX_ROOMS = 6;
	private static final LocalTime[] TIMES = generateTimes(8, 20);
	private static final RoomNumber[] ROOM_NUMBERS = RoomNumber.values();
	private static final Building[] BUILDINGS = Building.values();
	private static final RoomEquipment[] ROOM_EQUIPMENT = RoomEquipment.values();

	static {
		Administrator admin1 = new Administrator("emayer", "1234", "emayer@mail.com", true, false);
		Assistent assistent1 = new Assistent("slubos", "1234", "slubos@mail.com", false, true);
		Assistent assistent2 = new Assistent("mgukesh", "1234", "mgukesh@mail.com", false, true);
		Student student1 = new Student("dhubmann", "1234", "dhubmann@mail.com", false, false);
		Student student2 = new Student("rhofer", "1234", "rhofer@mail.com", false, false);

		Room room1 = new Room(Building.A, RoomNumber.R1, RoomEquipment.STANDARD);
		Room room2 = new Room(Building.C, RoomNumber.R3, RoomEquipment.COMPUTERS);
		Room room3 = new Room(Building.B, RoomNumber.R4, RoomEquipment.SPACE);

		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);

		// MAT1 in A-R1 from 08:00 - 10:00 with emayer
		Course course1 = new Course("MAT1", "A-R1", Model.getTimes()[0], Model.getTimes()[4],
				Model.getTeachingStaff().get(0).getUsername());

		// Webtechnology in A-R1 from 10:00 - 13:30 with slubos
		Course course2 = new Course("Webtechnology", "A-R1", Model.getTimes()[4], Model.getTimes()[11],
				Model.getTeachingStaff().get(1).getUsername());

		// Social Work in C-R3 from 09:00 - 13:30 with mgukesh
		Course course3 = new Course("Social Medicine", "C-R3", Model.getTimes()[2], Model.getTimes()[10],
				Model.getTeachingStaff().get(2).getUsername());

		// Social Work in C-R3 from 14:30 - 16:00 with mgukesh
		Course course4 = new Course("Social Work Issues 2", "C-R3", Model.getTimes()[12], Model.getTimes()[16],
				Model.getTeachingStaff().get(2).getUsername());

		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);

	}

	// Getters & Setters
	public static ArrayList<Room> getRooms() {
		return rooms;
	}

	public static void setRooms(ArrayList<Room> rooms) {
		Model.rooms = rooms;
	}

	public static ArrayList<JPanel> getRoomColumns() {
		return roomColumns;
	}

	public static void setRoomColumns(ArrayList<JPanel> roomColumns) {
		Model.roomColumns = roomColumns;
	}

	public static ArrayList<Course> getCourses() {
		return courses;
	}

	public static void setCourses(ArrayList<Course> courses) {
		Model.courses = courses;
	}

	public static ArrayList<User> getTeachingStaff() {
		return teachingStaff;
	}

	public static void setTeachingStaff(ArrayList<User> teachingStaff) {
		Model.teachingStaff = teachingStaff;
	}

	public static ArrayList<User> getStudents() {
		return students;
	}

	public static void setStudents(ArrayList<User> students) {
		Model.students = students;
	}

	public static int getMaxRooms() {
		return MAX_ROOMS;
	}

	public static LocalTime[] getTimes() {
		return TIMES;
	}

	public static RoomNumber[] getRoomNumbers() {
		return ROOM_NUMBERS;
	}

	public static Building[] getBuildings() {
		return BUILDINGS;
	}

	public static RoomEquipment[] getRoomEquipment() {
		return ROOM_EQUIPMENT;
	}

	// Times for Add-Course-View
	public static LocalTime[] generateTimes(int startHour, int endHour) {
		LocalTime[] times = new LocalTime[27];
		int hour = startHour;
		for (int i = 0; i < (endHour - startHour + 1) * 2; i += 2) {
			times[i] = LocalTime.of(hour++, 0);
		}
		hour = startHour;
		for (int i = 1; i < (endHour - startHour + 1) * 2; i += 2) {
			times[i] = LocalTime.of(hour++, 30);
		}
		times[26] = LocalTime.of(21, 0);
		return times;
	}

	// Array of current rooms for Add-Course-View
	public static String[] roomIDs() {
		ArrayList<String> roomIDs = new ArrayList<String>();
		for (Room r : rooms) {
			roomIDs.add(r.getRoomID());
		}
		return roomIDs.toArray(new String[roomIDs.size()]);
	}

	public static String[] instructors() {
		ArrayList<String> instructors = new ArrayList<String>();
		for (User u : teachingStaff) {
			instructors.add(u.getUsername());
		}
		return instructors.toArray(new String[instructors.size()]);
	}

	// returns index of startTime from TIMES
	public static int timeIndex(LocalTime time) {
		for (int i = 0; i < Model.getTimes().length; i++) {
			if (Model.getTimes()[i] == time) {
				return i;
			}
		}
		return 0;
	}

}
