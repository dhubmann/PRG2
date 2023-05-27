/*
 * PreferencesController
 * Represents preference logic
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JOptionPane;

public class PreferencesController {

	private PreferencesView preferencesView;
	private ScheduleController scheduleController;

	// Getters & Setters
	public PreferencesView getPreferencesView() {
		return preferencesView;
	}

	public void setPreferencesView(PreferencesView preferencesView) {
		this.preferencesView = preferencesView;
	}

	public ScheduleController getScheduleController() {
		return scheduleController;
	}

	public void setScheduleController(ScheduleController scheduleController) {
		this.scheduleController = scheduleController;
	}

	// Constructor
	public PreferencesController(PreferencesView preferencesView, ScheduleController scheduleController) {
		this.preferencesView = preferencesView;
		this.scheduleController = scheduleController;

		preferencesView.getBtnSetPreferences().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				User user = scheduleController.getLoginController().getUser();
				LocalTime startTime = (LocalTime) preferencesView.getCbStartTime().getSelectedItem();
				LocalTime endTime = (LocalTime) preferencesView.getCbEndTime().getSelectedItem();
				RoomEquipment preferredRoomEquipment = (RoomEquipment) preferencesView.getCbRoomEquipement()
						.getSelectedItem();

				// check if endtTime is equal or less than startTime --> warning
				if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
					invalidTimes();
					return;
				}

				if (user instanceof Administrator) {
					((Administrator) user).setPreferredRoomEquipment(preferredRoomEquipment);
					((Administrator) user).setPreferredStartTime(startTime);
					((Administrator) user).setPreferredEndTime(endTime);
				}

				if (user instanceof Assistant) {
					((Assistant) user).setPreferredRoomEquipment(preferredRoomEquipment);
					((Assistant) user).setPreferredStartTime(startTime);
					((Assistant) user).setPreferredEndTime(endTime);
				}

				preferencesView.dispose();

			}

		});

	}

	public void invalidTimes() {
		JOptionPane.showMessageDialog(null, "We're sorry - the start and end times you entered are invalid.");
	}

}
