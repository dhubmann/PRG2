/*
 * AddRoomController
 * Represents Logic behind Add Room Button
 * Author: Daniel Hubmann
 * Last Change: 1.5.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomController {

	private AddRoomView addRoomView;
	private Room room;

	// Getters & Setters
	public AddRoomView getAddRoomView() {
		return addRoomView;
	}

	public void setAddRoomView(AddRoomView addRoomView) {
		this.addRoomView = addRoomView;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	// Constructor
	public AddRoomController(AddRoomView addRoomView) {
		this.addRoomView = addRoomView;

		addRoomView.getBtnAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				RoomNumber roomNumber = (RoomNumber) addRoomView.getCbRoomNumber().getSelectedItem();
				Building building = (Building) addRoomView.getCbBuilding().getSelectedItem();
				RoomEquipment roomEq = (RoomEquipment) addRoomView.getCbRoomEquipment().getSelectedItem();

				// TODO: input validation

				room = new Room(roomNumber, building, roomEq);

				// TODO: check if room already exists in schedule

				Model.getRooms().add(room);
				addRoomView.dispose();

			}

		});
	}

}
