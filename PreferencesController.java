/*
 * PreferencesController
 * Represents preference logic
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

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
				LocalTime preferredStartTime = (LocalTime) preferencesView.getCbStartTime().getSelectedItem();
				RoomEquipment preferredRoomEquipment = (RoomEquipment) preferencesView.getCbRoomEquipement()
						.getSelectedItem();

				if (user instanceof Administrator) {
					((Administrator) user).setPreferredRoomEquipment(preferredRoomEquipment);
					((Administrator) user).setPreferredStartTime(preferredStartTime);
				}

				if (user instanceof Assistent) {
					((Assistent) user).setPreferredRoomEquipment(preferredRoomEquipment);
					((Assistent) user).setPreferredStartTime(preferredStartTime);
				}

				preferencesView.dispose();

			}

		});

	}

}
