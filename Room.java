/*
 * Room
 * Represents Room object
 * Author: Daniel Hubmann
 * Last Change: 10.05.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Room {

	private UUID roomUUID;
	private String roomID;
	private RoomEquipment roomEquipment;
	private JPanel panelRoomColumn;
	private JPanel panelCoursColumn;
	private JButton btnDelete;
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

	public static int getNumRooms() {
		return numRooms;
	}

	public static void setNumRooms(int numRooms) {
		Room.numRooms = numRooms;
	}

	// Constructors
//	public Room() {
//		numRooms++;
//		this.setRoomUUID(UUID.randomUUID());
//		this.roomID = "R" + numRooms; // "R1", "R2"...
//	}

	public Room(Building building, RoomNumber roomNumber, RoomEquipment roomEq) {
		numRooms++;
		this.setRoomUUID(UUID.randomUUID());
		this.roomID = building.name() + "-" + roomNumber.name();
		this.roomEquipment = roomEq;
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
