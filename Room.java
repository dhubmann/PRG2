/*
 * Room
 * Represents Room object
 * Author: Daniel Hubmann
 * Last Change: 2.5.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Room {

	private UUID roomUUID;
	private String roomID;
	private int capacity;
	private boolean isAvailable;
	private Building building;
	private RoomEquipment roomEquipment;
	private JPanel panelRoomColumn;
	private JPanel panelCoursColumn;
	private JButton btnDelete;
	private ArrayList<Course> courseList;
	private static int numRooms;

	// Getters & Setters
	public UUID getRoomUUID() {
		return roomUUID;
	}

	public void setRoomUUID(UUID roomUUID) {
		this.roomUUID = roomUUID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public RoomEquipment getRoomEquipment() {
		return roomEquipment;
	}

	public void setRoomEquipment(RoomEquipment roomEquipment) {
		this.roomEquipment = roomEquipment;
	}

	public void setPanelRoomColumn(JPanel panelRoomColumn) {
		this.panelRoomColumn = panelRoomColumn;
	}

	public JPanel getPanelRoomColumn() {
		return panelRoomColumn;
	}

	public JPanel getPanelCoursColumn() {
		return panelCoursColumn;
	}

	public void setPanelCoursColumn(JPanel panelCoursColumn) {
		this.panelCoursColumn = panelCoursColumn;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	public static int getNumRooms() {
		return numRooms;
	}

	public static void setNumRooms(int numRooms) {
		Room.numRooms = numRooms;
	}

	// Constructors
	public Room() {
		numRooms++;
		this.setRoomUUID(UUID.randomUUID());
		this.roomID = "R" + numRooms; // "R1", "R2"...
		this.capacity = 5;
		this.isAvailable = true;
		this.courseList = new ArrayList<Course>();
	}

	public Room(RoomNumber roomNumber, Building building, RoomEquipment roomEq) {
		numRooms++;
		this.setRoomUUID(UUID.randomUUID());
		this.roomID = building.name() + "-" + roomNumber.name();
		this.building = building;
		this.roomEquipment = roomEq;
		this.capacity = 5;
		this.isAvailable = true;
		this.courseList = new ArrayList<Course>();
	}

	public void setupButton(ScheduleView scheduleView) {

		this.btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
						"WARNING: Are you sure you want to delete this room?\nAll course information will be lost.",
						"Delete room", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					int roomIndex = Model.getRooms().indexOf(Room.this);
					if (roomIndex != -1) {
						Model.getRooms().remove(roomIndex);
						scheduleView.getPanelMain().remove(panelRoomColumn);
						scheduleView.getPanelMain().revalidate();
						scheduleView.getPanelMain().repaint();
					}
				}
			}
		});

	}

	// TODO:
	// printRoomInfo() ?

}
