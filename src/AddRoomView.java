/*
 * AddRoomView
 * Represents Add Room View
 * Author: Daniel Hubmann
 * Last Change: 1.5.2023
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class AddRoomView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Building> cbBuilding;
	private JComboBox<RoomNumber> cbRoomNumber;
	private JComboBox<RoomEquipment> cbRoomEquipment;
	private JButton btnAdd;

	// Getters
	public JPanel getContentPane() {
		return contentPane;
	}

	public JComboBox<RoomNumber> getCbRoomNumber() {
		return cbRoomNumber;
	}

	public JComboBox<Building> getCbBuilding() {
		return cbBuilding;
	}

	public JComboBox<RoomEquipment> getCbRoomEquipment() {
		return cbRoomEquipment;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * Create the frame.
	 */
	public AddRoomView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnAdd.setBounds(10, 122, 314, 28);
		contentPane.add(btnAdd);

		JLabel lblAddRoomTitle = new JLabel("Please enter information about the room");
		lblAddRoomTitle.setBounds(10, 11, 314, 14);
		contentPane.add(lblAddRoomTitle);

		JLabel lblRoomEquipment = new JLabel("Room Equipment:");
		lblRoomEquipment.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblRoomEquipment.setBounds(10, 86, 100, 14);
		contentPane.add(lblRoomEquipment);

		cbRoomEquipment = new JComboBox<RoomEquipment>(
				new DefaultComboBoxModel<RoomEquipment>(Model.getRoomEquipment()));
		cbRoomEquipment.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbRoomEquipment.setBounds(120, 83, 100, 22);
		contentPane.add(cbRoomEquipment);

		JLabel lblBuilding = new JLabel("Building:");
		lblBuilding.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblBuilding.setBounds(10, 36, 100, 14);
		contentPane.add(lblBuilding);

		cbBuilding = new JComboBox<Building>(new DefaultComboBoxModel<Building>(Model.getBuildings()));
		cbBuilding.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbBuilding.setBounds(120, 33, 100, 22);
		contentPane.add(cbBuilding);

		JLabel lblRoomNumber = new JLabel("Room number:");
		lblRoomNumber.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblRoomNumber.setBounds(10, 61, 105, 14);
		contentPane.add(lblRoomNumber);

		cbRoomNumber = new JComboBox<RoomNumber>(new DefaultComboBoxModel<RoomNumber>(Model.getRoomNumbers()));
		cbRoomNumber.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbRoomNumber.setBounds(120, 58, 100, 22);
		contentPane.add(cbRoomNumber);
	}
}
