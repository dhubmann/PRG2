/*
 * PreferencesView
 * Represents Prefrences view
 * Author: Daniel Hubmann
 * Last Change: 09.05.2023
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.time.LocalTime;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;

public class PreferencesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<LocalTime> cbStartTime, cbEndTime;
	private JComboBox<RoomEquipment> cbRoomEquipement;
	private JButton btnSetPreferences;

	// Getters
	public JComboBox<LocalTime> getCbStartTime() {
		return cbStartTime;
	}

	public JComboBox<LocalTime> getCbEndTime() {
		return cbEndTime;
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
		setBounds(100, 100, 320, 184);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Please set your preferences");
		lblTitle.setBounds(10, 11, 284, 14);
		contentPane.add(lblTitle);

		JLabel lblStartTime = new JLabel("Preferred start time:");
		lblStartTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblStartTime.setBounds(10, 36, 160, 14);
		contentPane.add(lblStartTime);

		JLabel lblEndTime = new JLabel("Preferred end time:");
		lblEndTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblEndTime.setBounds(10, 61, 160, 14);
		contentPane.add(lblEndTime);

		JLabel lblRoom = new JLabel("Preferred room equipment:");
		lblRoom.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblRoom.setBounds(10, 86, 160, 14);
		contentPane.add(lblRoom);

		cbStartTime = new JComboBox<LocalTime>(new DefaultComboBoxModel<LocalTime>(Model.getTimes()));
		cbStartTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbStartTime.setBounds(180, 32, 70, 22);
		contentPane.add(cbStartTime);

		cbEndTime = new JComboBox<LocalTime>(new DefaultComboBoxModel<LocalTime>(Model.getTimes()));
		cbEndTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbEndTime.setBounds(180, 57, 70, 22);
		contentPane.add(cbEndTime);

		// TODO: change to display the description value of the enum
		cbRoomEquipement = new JComboBox<RoomEquipment>(
				new DefaultComboBoxModel<RoomEquipment>(Model.getRoomEquipment()));
		cbRoomEquipement.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbRoomEquipement.setBounds(180, 82, 114, 22);
		contentPane.add(cbRoomEquipement);

		btnSetPreferences = new JButton("Set preferences");
		btnSetPreferences.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnSetPreferences.setBounds(10, 111, 284, 23);
		contentPane.add(btnSetPreferences);

	}
}
