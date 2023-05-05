/*
 * PreferencesView
 * Represents Prefrences view
 * Author: Daniel Hubmann
 * Last Change: 28.04.2023
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PreferencesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<LocalTime> cbStartTime;
	private JComboBox<RoomEquipment> cbRoomEquipement;
	private JButton btnSetPreferences;

	// Getters
	public JComboBox<LocalTime> getCbStartTime() {
		return cbStartTime;
	}

	public JComboBox<RoomEquipment> getCbRoomEquipement() {
		return cbRoomEquipement;
	}

	public JButton getBtnSetPreferences() {
		return btnSetPreferences;
	}

	/**
	 * Create the frame.
	 */
	public PreferencesView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 160);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Please set your preferences");
		lblTitle.setBounds(10, 11, 284, 14);
		contentPane.add(lblTitle);

		JLabel lblTime = new JLabel("Preferred start time:");
		lblTime.setBounds(10, 36, 160, 14);
		contentPane.add(lblTime);

		JLabel lblRoom = new JLabel("Preferred room equipment:");
		lblRoom.setBounds(10, 61, 160, 14);
		contentPane.add(lblRoom);

		// TODO: change to display the description value of the enum
		cbStartTime = new JComboBox<LocalTime>(new DefaultComboBoxModel<LocalTime>(Model.getTimes()));
		cbStartTime.setBounds(180, 32, 70, 22);
		contentPane.add(cbStartTime);

		cbRoomEquipement = new JComboBox<RoomEquipment>(
				new DefaultComboBoxModel<RoomEquipment>(Model.getRoomEquipment()));
		cbRoomEquipement.setBounds(180, 57, 114, 22);
		contentPane.add(cbRoomEquipement);

		btnSetPreferences = new JButton("Set preferences");
		btnSetPreferences.setBounds(10, 86, 284, 23);
		contentPane.add(btnSetPreferences);
	}
}
