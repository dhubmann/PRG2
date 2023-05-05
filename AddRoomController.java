/*
 * AddRoomController
 * Represents Logic behind Add Room Button
 * Author: Daniel Hubmann
 * Last Change: 1.5.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AddRoomController {

	private AddRoomView addRoomView;
	private ScheduleController scheduleController;
	private Room room;

	// Getters & Setters
	public AddRoomView getAddRoomView() {
		return addRoomView;
	}

	public void setAddRoomView(AddRoomView addRoomView) {
		this.addRoomView = addRoomView;
	}

	public ScheduleController getScheduleController() {
		return scheduleController;
	}

	public void setScheduleController(ScheduleController scheduleController) {
		this.scheduleController = scheduleController;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	// Constructor
	public AddRoomController(AddRoomView addRoomView, ScheduleController scheduleController) {
		this.addRoomView = addRoomView;
		this.scheduleController = scheduleController;

		addRoomView.getBtnAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Building building = (Building) addRoomView.getCbBuilding().getSelectedItem();
				RoomNumber roomNumber = (RoomNumber) addRoomView.getCbRoomNumber().getSelectedItem();
				RoomEquipment roomEq = (RoomEquipment) addRoomView.getCbRoomEquipment().getSelectedItem();

				if (roomAlreadyExists(building, roomNumber)) {
					roomAlreadyExistsWarning();
					return;
				}

				room = new Room(building, roomNumber, roomEq);

				Model.getRooms().add(room);
				addRoomView.dispose();

			}

		});
	}

	public boolean roomAlreadyExists(Building building, RoomNumber roomNumber) {
		for (Room r : Model.getRooms()) {
			if (r.getRoomID().equals(building + "-" + roomNumber)) {
				return true;
			}
		}
		return false;
	}

	public void roomAlreadyExistsWarning() {
		JOptionPane.showMessageDialog(null, "We're sorry - the room you are trying to add already exists.");
	}

}
