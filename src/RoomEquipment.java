/*
 * RoomEquipment
 * Represents room equipment
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

public enum RoomEquipment {
	NONE("none"), STANDARD("Standard"), COMPUTERS("Computer Stations"), SPACE("spacious");

	private String description;

	RoomEquipment(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
