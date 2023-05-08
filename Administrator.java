import java.time.LocalTime;

/*
 * Administrator
 * Represents Administrator object
 * Author: Daniel Hubmann
 * Last Change: 08.05.2023
 */

public class Administrator extends User {

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

	// Constructor
	public Administrator() {
		this.setPreferredStartTime(Model.getTimes()[0]);
		this.setPreferredRoomEquipment(RoomEquipment.STANDARD);
		Model.getTeachingStaff().add(this);
	}

	public Administrator(String username, String password, boolean isAdmin, boolean isAssistent) {
		super(username, password, isAdmin, isAssistent);
		this.setPreferredStartTime(Model.getTimes()[0]);
		this.setPreferredRoomEquipment(RoomEquipment.STANDARD);
		Model.getTeachingStaff().add(this);
	}

	public Administrator(String username, String password, String email, boolean isAdmin, boolean isAssistent) {
		super(username, password, email, isAdmin, isAssistent);
		this.setPreferredStartTime(Model.getTimes()[0]);
		this.setPreferredRoomEquipment(RoomEquipment.STANDARD);
		Model.getTeachingStaff().add(this);
	}

}
