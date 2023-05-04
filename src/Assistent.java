/*
 * Assistent
 * Represents Assistent object
 * Author: Daniel Hubmann
 * Last Change: 03.05.2023
 */

import java.time.LocalTime;

public class Assistent extends User {

	private LocalTime preferredStartTime;
	private RoomEquipment preferredRoomEquipment;

	// Getters & Setters
	public LocalTime getPreferredStartTime() {
		return preferredStartTime;
	}

	public void setPreferredStartTime(LocalTime preferredStartTime) {
		this.preferredStartTime = preferredStartTime;
	}

	public RoomEquipment getPreferredRoomEquipment() {
		return preferredRoomEquipment;
	}

	public void setPreferredRoomEquipment(RoomEquipment preferredRoomEquipment) {
		this.preferredRoomEquipment = preferredRoomEquipment;
	}

	// Constructors
	public Assistent() {
		super();
		Model.getTeachingStaff().add(this);

	}

	public Assistent(String username, String password, boolean isAdmin, boolean isAssistent) {
		super(username, password, isAdmin, isAssistent);
		this.preferredStartTime = Model.getTimes()[0];
		this.preferredRoomEquipment = RoomEquipment.STANDARD;
		Model.getTeachingStaff().add(this);
	}

}
