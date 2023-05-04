import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/*
 * PreferencesController
 * Represents preference logic
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

public class PreferencesController {

	private PreferencesView preferencesView;
	private User user;

	// Getters & Setters
	public PreferencesView getPreferencesView() {
		return preferencesView;
	}

	public void setPreferencesView(PreferencesView preferencesView) {
		this.preferencesView = preferencesView;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Constructor
	public PreferencesController(PreferencesView preferencesView, User user) {
		this.preferencesView = preferencesView;
		this.user = user;

		preferencesView.getBtnSetPreferences().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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
