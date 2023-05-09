/*
 * ScheduleController
 * Represents schedule logic
 * Author: Daniel Hubmann
 * Last Change: 27.04.2023
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class ScheduleController {

	private ScheduleView scheduleView;
	private LoginController loginController;

	// Getters & Setters
	public ScheduleView getScheduleView() {
		return scheduleView;
	}

	public void setScheduleView(ScheduleView scheduleView) {
		this.scheduleView = scheduleView;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	// Constructor
	public ScheduleController(ScheduleView scheduleView, LoginController loginController) {
		this.scheduleView = scheduleView;
		this.loginController = loginController;

		if (loginController.isStudent()) {
			scheduleView.getBtnAddRoom().setVisible(false);
			scheduleView.getBtnAddCourse().setVisible(false);
			scheduleView.getBtnPreferences().setVisible(false);
		}

		if (loginController.isAssistent()) {
			scheduleView.getBtnAddRoom().setEnabled(false);
			scheduleView.getBtnAddCourse().setEnabled(false);
		}

		// ADD ROOM
		scheduleView.getBtnAddRoom().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (Model.getRooms().size() < 6) {
					try {
						AddRoomView addRoomView = new AddRoomView();
						AddRoomController addRoomController = new AddRoomController(addRoomView,
								ScheduleController.this);
						addRoomView.setVisible(true);

						addRoomView.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								// adds a new room panel to the schedule
								try {
									newRoomColumn(addRoomController.getRoom(), Model.getRooms().size() - 1);
								} catch (NullPointerException ex) {
									return;
								}
							}
						});
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					roomLimitWarning();
					return;
				}

			}

		});

		// ADD COURSE
		scheduleView.getBtnAddCourse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Open Add Course View to create new course object
				 */

				if (Room.getNumRooms() == 0) {
					noRoomCreatedWarning();
					return;
				}

				try {
					AddCourseView addCourseView = new AddCourseView();
					AddCourseController addCourseController = new AddCourseController(addCourseView,
							ScheduleController.this);
					addCourseView.setVisible(true);

					addCourseView.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							// adds new course to the room column accordingly
							try {
								newCourseBlock(addCourseController.getCourse());
							} catch (NullPointerException ex) {
								return;
							}
						}
					});

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		// PREFERENCES
		scheduleView.getBtnPreferences().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Open Preferences View to set preferences of adminstrator or assistant
				 */

				try {
					PreferencesView preferencesView = new PreferencesView();
					@SuppressWarnings("unused")
					PreferencesController preferencesController = new PreferencesController(preferencesView,
							ScheduleController.this);
					preferencesView.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		// SIGN OUT
		scheduleView.getBtnLogout().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Come back to login view
				 */

				try {
					scheduleView.dispose();
					LoginView loginView = new LoginView();
					@SuppressWarnings("unused")
					LoginController loginController = new LoginController(loginView);
					loginView.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

	}

	public void updateView() {
		scheduleView.getPanelMain().revalidate();
		scheduleView.getPanelMain().repaint();
	}

	// Creates new room column
	public void newRoomColumn(Room room, int index) {

		JPanel panelRoomColumn = new JPanel();
		panelRoomColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRoomColumn.setBackground(new Color(255, 191, 191));
		// x coordinate determined by number of rooms
		panelRoomColumn.setBounds((index * 120), 0, 120, 702); // keep an eye on this
		panelRoomColumn.setLayout(null);
		panelRoomColumn.setVisible(true);
		scheduleView.getPanelMain().add(panelRoomColumn);
		scheduleView.getPanelMain().revalidate();
		scheduleView.getPanelMain().repaint();

		// delete button
		if (loginController.isAdmin()) {
			JButton btnDelete = new JButton("X");
			btnDelete.setLocation(100, 0);
			btnDelete.setSize(20, 20);
			btnDelete.setForeground(new Color(0, 0, 0));
			btnDelete.setFont(new Font("SansSerif", Font.BOLD, 10));
			btnDelete.setMargin(new Insets(0, 0, 0, 0));
			btnDelete.setPreferredSize(new Dimension(10, 10));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					/*
					 * TODO: implement compression of room columns if a column gets deleted between
					 * two columns or on the far left - so far, only deleting the furthest column to
					 * the right works fine
					 */

					int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
							"WARNING: Are you sure you want to delete this room?\nAll course information will be lost.",
							"Delete room", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (result == JOptionPane.YES_OPTION) {
						int roomIndex = Model.getRooms().indexOf(room);
						Model.getRooms().remove(roomIndex);
						scheduleView.getPanelMain().remove(panelRoomColumn);
						scheduleView.getPanelMain().revalidate();
						scheduleView.getPanelMain().repaint();

					}
				}
			});
			panelRoomColumn.add(btnDelete);
		}

		// name of room
		JLabel lblRoomID = new JLabel(room.getRoomID());
		lblRoomID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblRoomID.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomID.setEnabled(false);
		lblRoomID.setBounds(10, 11, 100, 24);
		panelRoomColumn.add(lblRoomID);

		// contains course blocks from 08:00 to 21:00
		JPanel panelCourseColumn = new JPanel();
		panelCourseColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCourseColumn.setBackground(new Color(255, 191, 191));
		panelCourseColumn.setBounds(10, 41, 100, 650);
		panelRoomColumn.add(panelCourseColumn);

		room.setPanelCoursColumn(panelCourseColumn);
		room.setPanelRoomColumn(panelRoomColumn);
	}

	// Creates new course block
	public JLabel newCourseBlock(Course course) {

		final int Y_COORD = Model.timeIndex(course.getStartTime()) * 25;
		final int WIDTH = 100;
		final int HEIGHT = (Model.timeIndex(course.getEndTime()) - Model.timeIndex(course.getStartTime())) * 25;

		JPanel panelCourseBlock = new JPanel();
		panelCourseBlock.setLayout(null);
		panelCourseBlock.setBounds(0, Y_COORD, WIDTH, HEIGHT);

		// Delete course button
		JButton btnDeleteCourse = new JButton("X");
		btnDeleteCourse.setFont(new Font("SansSerif", Font.BOLD, 9));
		btnDeleteCourse.setBounds(88, 0, 12, 12);
		btnDeleteCourse.setMargin(new Insets(0, 0, 0, 0));
		btnDeleteCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
						"WARNING: Are you sure you want to delete this course?\nAll course information will be lost.",
						"Delete course", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {

					for (Room r : Model.getRooms()) {
						if (course.getRoomID().equals(r.getRoomID())) {
							int courseIndex = Model.getCourses().indexOf(course);
							Model.getCourses().remove(courseIndex);

							r.getPanelCoursColumn().remove(panelCourseBlock);
							r.getPanelCoursColumn().revalidate();
							r.getPanelCoursColumn().repaint();

						}
					}
				}
			}

		});

		panelCourseBlock.add(btnDeleteCourse);

		// Course label
		JLabel lblCourseBlock = new JLabel();
		lblCourseBlock
				.setText("<html><div style='text-align: center;'>" + course.getCourseID() + " - " + course.getTitle()
						+ "</div><div>" + course.getStartTime() + " - " + course.getEndTime() + "</div></html>");
		lblCourseBlock.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCourseBlock.setForeground(new Color(255, 255, 255));
		lblCourseBlock.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCourseBlock.setOpaque(true);
		lblCourseBlock.setBackground(new Color(255, 128, 128));
		lblCourseBlock.setHorizontalAlignment(SwingConstants.CENTER);
		/*
		 * positions course block accordingly to start and end time - height of one
		 * 30-minutes block equals 25px
		 */
		lblCourseBlock.setBounds(0, 0, WIDTH, HEIGHT);
		panelCourseBlock.add(lblCourseBlock);

		panelCourseBlock.setVisible(true);
//		course.setBtnDeleteCourse(btnDeleteCourse);
		course.setLblCourseBlock(lblCourseBlock);
		course.setPanelCourseBlock(panelCourseBlock);

		for (Room r : Model.getRooms()) {
			if (r.getRoomID().equals(course.getRoomID())) {
				JPanel panelCourseColumn = r.getPanelCoursColumn();
				panelCourseColumn.setLayout(null);
				panelCourseColumn.add(panelCourseBlock);
				panelCourseColumn.revalidate();
				panelCourseColumn.repaint();
			}
		}

		// TODO: implemente Priviligies Class
		if (!loginController.isAdmin()) {
			btnDeleteCourse.setVisible(false);
		}

		/*
		 * Assistents only see courses they are the instructor of
		 */
		if (loginController.isAssistent() && !courseInstructor(course, loginController.getUser())) {
			lblCourseBlock.setVisible(false);
		}

		/*
		 * Students click on course blocks to sign in for courses.
		 */
		if (loginController.isStudent() && course.isAttendable()) {

			lblCourseBlock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblCourseBlock.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
							"Do you want to sign in for this course?\n" + "Title: " + course.getTitle() + "\n"
									+ "with: " + course.getInstructor(),
							"Course Sign In", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (result == JOptionPane.YES_OPTION) {
						// TODO: change "JA" and "NEIN" to "YES" and "NO"
						// TODO: add student to course participants
						// TODO: add course to the student's courseList

						// student signs in --> turns label bright red
						lblCourseBlock.setBackground(new Color(255, 0, 0));

						for (Course c : Model.getCourses()) {
							if (course.getCourseID() != c.getCourseID() && course.overlap(c)) {
								c.setAttendable(false);
								c.getLblCourseBlock().setEnabled(false);

								// TODO: maybe change font & background color
								c.getLblCourseBlock().setBackground(new Color(250, 250, 250));
							}
						}
					}
				}
			});
		}

		return lblCourseBlock;

	}

	// Checks if the user is the course's instructor
	public boolean courseInstructor(Course course, User user) {
		if (course.getInstructor().equals(user.getUsername())) {
			return true;
		}
		return false;
	}

	public void roomLimitWarning() {
		JOptionPane.showMessageDialog(null,
				"We are sorry - the room limit has exceeded!\nPlease contact the office of the University Dean if you need more rooms.");
	}

	public void noRoomCreatedWarning() {
		JOptionPane.showMessageDialog(null, "Please add a new room first");
	}

}
