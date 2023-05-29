/*
 * Administrator
 * Represents Administrator object
 * Author: Daniel Hubmann
 * Last Change: 27.05.2023
 */

import java.time.LocalTime;

public class Administrator extends User {

	private LocalTime preferredStartTime;
	private LocalTime preferredEndTime;
	private RoomEquipment preferredRoomEquipment;
	private boolean preferred = true;

	// Getters & Setters
	public LocalTime getPreferredStartTime() {
		return preferredStartTime;
	}

	public void setPreferredStartTime(LocalTime preferredStartTime) {
		this.preferredStartTime = preferredStartTime;
	}

	public LocalTime getPreferredEndTime() {
		return preferredEndTime;
	}

	public void setPreferredEndTime(LocalTime preferredEndTime) {
		this.preferredEndTime = preferredEndTime;
	}

	public RoomEquipment getPreferredRoomEquipment() {
		return preferredRoomEquipment;
	}

	public void setPreferredRoomEquipment(RoomEquipment preferredRoomEquipment) {
		this.preferredRoomEquipment = preferredRoomEquipment;
	}

	public boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

	// Constructor
	public Administrator() {
		this.setPreferredStartTime(Model.getTimes()[0]);
		this.setPreferredEndTime(Model.getTimes()[16]);
		this.setPreferredRoomEquipment(RoomEquipment.STANDARD);
		Model.getTeachingStaff().add(this);
	}

	public Administrator(String username, String password) {
		super(username, password);
		this.setPreferredStartTime(Model.getTimes()[0]);
		this.setPreferredEndTime(Model.getTimes()[16]);
		this.setPreferredRoomEquipment(RoomEquipment.STANDARD);
		Model.getTeachingStaff().add(this);
	}

	public Administrator(String username, String password, String email) {
		super(username, password, email);
		this.setPreferredStartTime(Model.getTimes()[0]);
		this.setPreferredEndTime(Model.getTimes()[16]);
		this.setPreferredRoomEquipment(RoomEquipment.STANDARD);
		Model.getTeachingStaff().add(this);
	}

}
