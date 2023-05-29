/*
 * Assistant
 * Represents Assistant object
 * Author: Daniel Hubmann
 * Last Change: 27.05.2023
 */

import java.time.LocalTime;

public class Assistant extends User {

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

	// Constructors
	public Assistant() {
		super();
		this.preferredStartTime = Model.getTimes()[0];
		this.setPreferredEndTime(Model.getTimes()[16]);
		this.preferredRoomEquipment = RoomEquipment.STANDARD;
		Model.getTeachingStaff().add(this);
	}

	public Assistant(String username, String password) {
		super(username, password);
		this.preferredStartTime = Model.getTimes()[0];
		this.setPreferredEndTime(Model.getTimes()[16]);
		this.preferredRoomEquipment = RoomEquipment.STANDARD;
		Model.getTeachingStaff().add(this);
	}

	public Assistant(String username, String password, String email) {
		super(username, password, email);
		this.preferredStartTime = Model.getTimes()[0];
		this.setPreferredEndTime(Model.getTimes()[16]);
		this.preferredRoomEquipment = RoomEquipment.STANDARD;
		Model.getTeachingStaff().add(this);
	}

}
