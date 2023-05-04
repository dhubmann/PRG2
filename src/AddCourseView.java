/*
 * AddCourseView
 * Represents add course view
 * Author: Daniel Hubmann
 * Last Change: 28.04.2023
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;

public class AddCourseView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCourseTitle;
	private JComboBox<String> cbRoom, cbInstructor;
	private JComboBox<LocalTime> cbStartTime, cbEndTime;
	private JButton btnAdd;

	// Getters
	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTfCourseTitle() {
		return tfCourseTitle;
	}

	public JComboBox<String> getCbRoom() {
		return cbRoom;
	}

	public JComboBox<LocalTime> getCbStartTime() {
		return cbStartTime;
	}

	public JComboBox<LocalTime> getCbEndTime() {
		return cbEndTime;
	}

	public JComboBox<String> getCbInstructor() {
		return cbInstructor;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * Create the frame.
	 */
	public AddCourseView() {
		setTitle("New Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddCourseTitle = new JLabel("Please enter the course information");
		lblAddCourseTitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblAddCourseTitle.setBounds(10, 11, 314, 14);
		contentPane.add(lblAddCourseTitle);

		JLabel lblCourseTitle = new JLabel("Title of course:");
		lblCourseTitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblCourseTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseTitle.setBounds(10, 36, 87, 14);
		contentPane.add(lblCourseTitle);

		tfCourseTitle = new JTextField();
		tfCourseTitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
		tfCourseTitle.setBounds(107, 33, 217, 20);
		contentPane.add(tfCourseTitle);
		tfCourseTitle.setColumns(10);

		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblRoom.setHorizontalAlignment(SwingConstants.LEFT);
		lblRoom.setBounds(10, 61, 46, 14);
		contentPane.add(lblRoom);

		cbRoom = new JComboBox<String>(new DefaultComboBoxModel<String>(Model.roomIDs()));
		cbRoom.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbRoom.setBounds(107, 57, 70, 22);
		contentPane.add(cbRoom);

		JLabel lblStartTime = new JLabel("From:");
		lblStartTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblStartTime.setBounds(10, 90, 46, 14);
		contentPane.add(lblStartTime);

		cbStartTime = new JComboBox<LocalTime>(new DefaultComboBoxModel<LocalTime>(Model.getTimes()));
		cbStartTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbStartTime.setBounds(107, 86, 70, 22);
		contentPane.add(cbStartTime);

		JLabel lblEndTime = new JLabel("To:");
		lblEndTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblEndTime.setBounds(10, 119, 46, 14);
		contentPane.add(lblEndTime);

		cbEndTime = new JComboBox<LocalTime>(new DefaultComboBoxModel<LocalTime>(Model.getTimes()));
		cbEndTime.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbEndTime.setBounds(107, 115, 70, 22);
		contentPane.add(cbEndTime);

		JLabel lblnstructor = new JLabel("Instructor:");
		lblnstructor.setFont(new Font("SansSerif", Font.PLAIN, 11));
		lblnstructor.setBounds(10, 148, 87, 14);
		contentPane.add(lblnstructor);

		cbInstructor = new JComboBox<String>(new DefaultComboBoxModel<String>(Model.instructors()));
		cbInstructor.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cbInstructor.setBounds(107, 144, 110, 22);
		contentPane.add(cbInstructor);

		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnAdd.setBounds(10, 173, 314, 28);
		contentPane.add(btnAdd);

	}
}
