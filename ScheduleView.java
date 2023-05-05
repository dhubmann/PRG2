/*
 * ScheduleView
 * Represents schedule view
 * Author: Daniel Hubmann
 * Last Change: 27.04.2023
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class ScheduleView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelMain, panelCourseColumn;
	private JButton btnPreviousDay, btnNextDay, btnAddRoom, btnAddCourse, btnPreferences, btnDelete, btnLogout;
	private JLabel lblCourseBlock;

	// Getters
	public JPanel getContentPane() {
		return contentPane;
	}

	public JPanel getPanelMain() {
		return panelMain;
	}

	public JPanel getPanelCourseColumn() {
		return panelCourseColumn;
	}

	public JButton getBtnPreviousDay() {
		return btnPreviousDay;
	}

	public JButton getBtnNextDay() {
		return btnNextDay;
	}

	public JButton getBtnAddRoom() {
		return btnAddRoom;
	}

	public JButton getBtnAddCourse() {
		return btnAddCourse;
	}

	public JButton getBtnPreferences() {
		return btnPreferences;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	/**
	 * Create the frame.
	 */
	public ScheduleView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		setResizable(false);
		setLocationRelativeTo(null);

		// TODO: grey out btnPreviousDay & btnNextDay
		// TODO: update date to current date
		// components
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd.MM. '(KW 'ww')'");
		String formattedDate = date.format(formatter);
		
		JLabel lblDate = new JLabel(formattedDate); // TODO: change to current date or sth
		lblDate.setBackground(new Color(255, 255, 255));
		lblDate.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		btnPreviousDay = new JButton("<<");
		btnPreviousDay.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNextDay = new JButton(">>");
		btnNextDay.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		btnPreviousDay.setEnabled(false);
		btnNextDay.setEnabled(false);

		// Main Panel Component
		panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setLayout(null);

		// ROOM COLUMN
//		JPanel panelRoomColumn = new JPanel();
//		panelRoomColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//		panelRoomColumn.setBackground(new Color(255, 191, 191));
//		panelRoomColumn.setBounds(0, 0, 120, 702);
//		panelRoomColumn.setLayout(null);
//		panelRoomColumn.setVisible(true);
//		panelMain.add(panelRoomColumn);
//
//		// contains time slots for courses
//		JPanel panelCourseColumn = new JPanel();
//		panelCourseColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//		panelCourseColumn.setBackground(new Color(255, 191, 191));
//		panelCourseColumn.setBounds(10, 41, 100, 650);
//		panelRoomColumn.add(panelCourseColumn);
//		panelCourseColumn.setLayout(null);
//
//		btnDelete = new JButton("X");
//		btnDelete.setLocation(100, 0);
//		btnDelete.setSize(20, 20);
//		btnDelete.setForeground(new Color(0, 0, 0));
//		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 10));
//		btnDelete.setMargin(new Insets(0, 0, 0, 0));
//		btnDelete.setPreferredSize(new Dimension(10, 10));
//		panelRoomColumn.add(btnDelete);
//
//		lblCourseBlock = new JLabel();
//		lblCourseBlock.setEnabled(false);
//		lblCourseBlock.setText("<html><div style='text-align: center;'>C1</div><div>08:00 - 10:30</div></html>");
//		lblCourseBlock.setForeground(new Color(255, 255, 255));
//		lblCourseBlock.setFont(new Font("Tahoma", Font.BOLD, 12));
//		lblCourseBlock.setOpaque(true);
//		lblCourseBlock.setBackground(new Color(223, 223, 223));
//		lblCourseBlock.setHorizontalAlignment(SwingConstants.CENTER);
//		lblCourseBlock.setBounds(0, 0, 100, 600);
//		panelCourseColumn.add(lblCourseBlock);
//
//		// name of room
//		JLabel lblRoomID = new JLabel("test");
//		lblRoomID.setBounds(10, 11, 100, 24);
//		panelRoomColumn.add(lblRoomID);
//		lblRoomID.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblRoomID.setHorizontalAlignment(SwingConstants.CENTER);
//		lblRoomID.setEnabled(false);

		// Sidebar Component
		JPanel panelSidebar = new JPanel();
		panelSidebar.setBounds(720, 0, 154, 702);
		panelSidebar.setLayout(null);
		panelMain.add(panelSidebar);

		btnAddRoom = new JButton("Add Room");
		btnAddRoom.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAddRoom.setBounds(10, 11, 134, 23);
		panelSidebar.add(btnAddRoom);

		btnAddCourse = new JButton("Add Course");
		btnAddCourse.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAddCourse.setBounds(10, 45, 134, 23);
		panelSidebar.add(btnAddCourse);

		btnPreferences = new JButton("Preferences");
		btnPreferences.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnPreferences.setBounds(10, 79, 134, 23);
		panelSidebar.add(btnPreferences);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 668, 134, 23);
		panelSidebar.add(btnLogout);

		// Info for Students without courses
//		JLabel lblNotSignedIn = new JLabel("<html>INFO<br>You are <b>not</b> signed up for any courses yet.<br> Click on a course to sign up.</html>");
//		lblNotSignedIn.setVerticalAlignment(SwingConstants.TOP);
//		lblNotSignedIn.setHorizontalAlignment(SwingConstants.LEFT);
//		lblNotSignedIn.setBounds(10, 560, 134, 90);
//		panelSidebar.add(lblNotSignedIn);

		// set layout and add components to layout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnPreviousDay, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE).addGap(18)
						.addComponent(btnNextDay, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNextDay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPreviousDay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

	}
}
