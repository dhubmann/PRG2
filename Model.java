/*
 * Model
 * Represents application data
 * Author: Daniel Hubmann
 * Last Change: 03.05.2023
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class Model {

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
		Administrator admin1 = new Administrator("emayer", "1234", true, false);
		Assistent assistent1 = new Assistent("slubos", "1234", false, true);
		Assistent assistent2 = new Assistent("mgukesh", "1234", false, true);
		Student student1 = new Student("dhubmann", "1234", false, false);
		Student student2 = new Student("rhofer", "1234", false, false);

//		admin1.setPreferredStartTime(Model.getTimes()[0]); // emayer: 08:00
//		assistent1.setPreferredStartTime(Model.getTimes()[8]); // slubos: 12:00

		assistent1.setPreferredRoomEquipment(RoomEquipment.COMPUTERS);

		Room room1 = new Room(RoomNumber.R1, Building.A, RoomEquipment.STANDARD);
		Room room2 = new Room(RoomNumber.R3, Building.C, RoomEquipment.COMPUTERS);
		Room room3 = new Room(RoomNumber.R4, Building.B, RoomEquipment.SPACE);

		rooms.add(room1);
		newRoomColumn(room1);

		rooms.add(room2);
		newRoomColumn(room2);

		rooms.add(room3);
		newRoomColumn(room3);

		// MAT1 in A-R1 from 08:00 - 10:00 with emayer
		Course course1 = new Course("MAT1", "A-R1", Model.getTimes()[0], Model.getTimes()[4],
				Model.getTeachingStaff().get(0).getUsername());

		// Programming 2 in A-R1 from 10:00 - 13:30 with slubos
		Course course2 = new Course("Programming 2", "A-R1", Model.getTimes()[4], Model.getTimes()[11],
				Model.getTeachingStaff().get(1).getUsername());

		// Social Work in C-R3 from 09:00 - 13:30 with mgukesh
		Course course3 = new Course("Social Medicine", "C-R3", Model.getTimes()[2], Model.getTimes()[10],
				Model.getTeachingStaff().get(2).getUsername());

		// Social Work in C-R3 from 14:30 - 16:00 with mgukesh
		Course course4 = new Course("Social Work Issues 2", "C-R3", Model.getTimes()[12], Model.getTimes()[16],
				Model.getTeachingStaff().get(2).getUsername());

		newCourseBlock(course1);
		newCourseBlock(course2);
		newCourseBlock(course3);
		newCourseBlock(course4);

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

	// Creates a new room column
	public static void newRoomColumn(Room room) {

		JPanel panelRoomColumn = new JPanel();
		panelRoomColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRoomColumn.setBackground(new Color(255, 191, 191));
		// TODO: implement compression when column in between gets removed
		panelRoomColumn.setBounds((Model.getRooms().size() - 1) * 120, 0, 120, 702);
		panelRoomColumn.setLayout(null);
		panelRoomColumn.setVisible(true);
//		scheduleView.getPanelMain().add(panelRoomColumn);
//		scheduleView.getPanelMain().revalidate();
//		scheduleView.getPanelMain().repaint();

		// delete button

		JButton btnDelete = new JButton("X");
		btnDelete.setLocation(100, 0);
		btnDelete.setSize(20, 20);
		btnDelete.setForeground(new Color(0, 0, 0));
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnDelete.setMargin(new Insets(0, 0, 0, 0));
		btnDelete.setPreferredSize(new Dimension(10, 10));
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
//						"WARNING: Are you sure you want to delete this room?\nAll course information will be lost.",
//						"Delete room", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//
//				if (result == JOptionPane.YES_OPTION) {
//					int roomIndex = Model.getRooms().indexOf(room);
//					if (roomIndex != -1) {
//						Model.getRooms().remove(roomIndex);
//						scheduleView.getPanelMain().remove(panelRoomColumn);
//						scheduleView.getPanelMain().revalidate();
//						scheduleView.getPanelMain().repaint();
//					}
//				}
//			}
//		});
		panelRoomColumn.add(btnDelete);

		// name of room
		JLabel lblRoomID = new JLabel(room.getRoomID());
		lblRoomID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblRoomID.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomID.setEnabled(false);
		lblRoomID.setBounds(10, 11, 100, 24);
		panelRoomColumn.add(lblRoomID);

		// contains time slots for courses
		JPanel panelCourseColumn = new JPanel();
		panelCourseColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCourseColumn.setBackground(new Color(255, 191, 191));
		panelCourseColumn.setBounds(10, 41, 100, 650);
		panelRoomColumn.add(panelCourseColumn);

		room.setPanelCoursColumn(panelCourseColumn);
		room.setPanelRoomColumn(panelRoomColumn);
	}

	// Creates new course block
	public static void newCourseBlock(Course course) {

		JLabel lblCourseBlock = new JLabel();
		lblCourseBlock
				.setText("<html><div style='text-align: center;'>" + course.getCourseID() + " - " + course.getTitle()
						+ "</div><div>" + course.getStartTime() + " - " + course.getEndTime() + "</div></html>");
		lblCourseBlock.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCourseBlock.setForeground(new Color(255, 255, 255));
		lblCourseBlock.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCourseBlock.setOpaque(true);
		lblCourseBlock.setBackground(new Color(255, 128, 128));
		lblCourseBlock.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseBlock.setBounds(0, timeIndex(course.getStartTime()) * 25, 100,
				(timeIndex(course.getEndTime()) - timeIndex(course.getStartTime())) * 25);
		lblCourseBlock.setVisible(true);
		course.setLblCourseBlock(lblCourseBlock);

		for (Room r : Model.getRooms()) {
			if (r.getRoomID().equals(course.getRoomID())) {
				JPanel panelCourseColumn = r.getPanelCoursColumn();
				panelCourseColumn.setLayout(null);
				panelCourseColumn.add(lblCourseBlock);
				panelCourseColumn.revalidate();
				panelCourseColumn.repaint();
			}
		}

		/*
		 * Students click on course blocks to sign in for courses.
		 */

//		if (loginController.isStudent() && course.isAttendable()) {
//
//			lblCourseBlock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			lblCourseBlock.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
//							"Do you want to sign in for this course?\n" + "Title: " + course.getTitle() + "\n"
//									+ "with: " + course.getInstructor(),
//							"Course Sign In", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//
//					if (result == JOptionPane.YES_OPTION) {
//						// TODO: change "JA" and "NEIN" to "YES" and "NO"
//						// TODO: add student to course participants
//						// TODO: add course to courseList
//						// course.getParticipants().add(null);
//
//						lblCourseBlock.setBackground(new Color(255, 0, 0));
//
//						for (Course c : Model.getCourses()) {
//							if (course.getCourseID() != c.getCourseID() && course.overlap(c)) {
//								// TODO: fix course not attendable
//								// course still attendable
//								c.setAttendable(false); // fix this
//								c.getLblCourseBlock().setBackground(new Color(211, 211, 211));
//							}
//						}
//					}
//				}
//			});
//		}

//		return lblCourseBlock;

	}

}
