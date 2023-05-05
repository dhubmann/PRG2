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

		// ADD ROOM
		scheduleView.getBtnAddRoom().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * If user is assistent, permission denied
				 */

				// TODO: grey out button
				if (loginController.isAssistent()) {
					permissionDeniedWarning();
					return;
				}

				/*
				 * If numRooms is less than 6, add a new Room object
				 */

				// TODO: implement AddRoomView & AddRoomController
				if (Model.getRooms().size() < 6) {
					try {
						AddRoomView addRoomView = new AddRoomView();
						AddRoomController addRoomController = new AddRoomController(addRoomView);
						addRoomView.setVisible(true);

						addRoomView.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								// adds a new room panel to the schedule after closing addRoomView
//								addRoomController.getRoom()
//										.setPanelRoomColumn(newRoomColumn(addRoomController.getRoom()));
								try {
									newRoomColumn(addRoomController.getRoom());
								} catch (NullPointerException ex) {
									return;
								}
							}
						});

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				else {
					roomLimitWarning();
					return;
				}

			}

		});

		// ADD COURSE
		scheduleView.getBtnAddCourse().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO: grey out button
				if (loginController.isAssistent()) {
					permissionDeniedWarning();
					return;
				}

				/*
				 * Open Add Course View to create new course object
				 */

				if (Room.getNumRooms() == 0) {
					noRoomCreatedWarning();
					return;
				}

				if (roomsUnavailable()) {
					roomsUnavailableWarning();
					return;
				}

				try {
					AddCourseView addCourseView = new AddCourseView();
					AddCourseController addCourseController = new AddCourseController(addCourseView);
					addCourseView.setVisible(true);

					addCourseView.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							// adds new course to schedule and closes view
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
					// 2.5. 23:47
					// TODO: finish implementation
					PreferencesView preferencesView = new PreferencesView();
					PreferencesController preferencesController = new PreferencesController(preferencesView,
							loginController.getUser());
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
	public void newRoomColumn(Room room) {

		JPanel panelRoomColumn = new JPanel();
		panelRoomColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelRoomColumn.setBackground(new Color(255, 191, 191));
		// TODO: implement compression when column in between gets removed
		panelRoomColumn.setBounds((Model.getRooms().size() - 1) * 120, 0, 120, 702);
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

					// TODO:
					// warning: Are you sure you want to remove this room?

					// if yes, then remove room column and data
					/*
					 * TODO: make it possible that if a room between rooms gets deleted, the open
					 * space is available for new room objects - OR: the rest of the rooms get
					 * ordered together so that there are no white spaces between rooms
					 */

					int result = JOptionPane.showConfirmDialog(scheduleView.getPanelMain(),
							"WARNING: Are you sure you want to delete this room?\nAll course information will be lost.",
							"Delete room", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (result == JOptionPane.YES_OPTION) {
						int roomIndex = Model.getRooms().indexOf(room);
						if (roomIndex != -1) {
							Model.getRooms().remove(roomIndex);
							scheduleView.getPanelMain().remove(panelRoomColumn);
							scheduleView.getPanelMain().revalidate();
							scheduleView.getPanelMain().repaint();
						}
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

		// contains time slots for courses
		JPanel panelCourseColumn = new JPanel();
		panelCourseColumn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCourseColumn.setBackground(new Color(255, 191, 191));
		panelCourseColumn.setBounds(10, 41, 100, 650);
		panelRoomColumn.add(panelCourseColumn);

		// CHANGED 3.5. 8:52
		room.setPanelCoursColumn(panelCourseColumn);
//		return panelCourseColumn;
		room.setPanelRoomColumn(panelRoomColumn);
	}

	// Creates new course block
	public JLabel newCourseBlock(Course course) {

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
		lblCourseBlock.setBounds(0, Model.timeIndex(course.getStartTime()) * 25, 100,
				(Model.timeIndex(course.getEndTime()) - Model.timeIndex(course.getStartTime())) * 25);
		lblCourseBlock.setVisible(true);
		course.setLblCourseBlock(lblCourseBlock);

		for (Room r : Model.getRooms()) {
			if (r.getRoomID().equals(course.getRoomID())) {
				JPanel panelCourseColumn = r.getPanelCoursColumn();
				panelCourseColumn.setLayout(null);
				panelCourseColumn.add(lblCourseBlock);
				panelCourseColumn.revalidate();
				panelCourseColumn.repaint();
			}
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
						// TODO: add course to courseList
						// course.getParticipants().add(null);

						lblCourseBlock.setBackground(new Color(255, 0, 0));

						for (Course c : Model.getCourses()) {
							if (course.getCourseID() != c.getCourseID() && course.overlap(c)) {
								// TODO: fix course not attendable
								// course still attendable
								c.setAttendable(false); // fix this
								c.getLblCourseBlock().setBackground(new Color(211, 211, 211));
							}
						}
					}
				}
			});
		}

		return lblCourseBlock;

	}

	public void permissionDeniedWarning() {
		JOptionPane.showMessageDialog(null,
				"We are sorry but you don't have permission to change the room plan.\nPlease contact the office of the University Dean for more information.");
	}

	public void roomLimitWarning() {
		JOptionPane.showMessageDialog(null,
				"We are sorry - the room limit has exceeded!\nPlease contact the office of the University Dean if you need more rooms.");
	}

	public boolean roomsUnavailable() {
		for (Room r : Model.getRooms()) {
			if (r.isAvailable() == true) {
				return false;
			}
		}
		return true;
	}

	public void roomsUnavailableWarning() {
		JOptionPane.showMessageDialog(null,
				"We are sorry - it seems that all rooms are fully occupied.\nPlease contact the office of the University Dean for more information.");
	}

	public void noRoomCreatedWarning() {
		JOptionPane.showMessageDialog(null, "Please add a new room first");
	}

}
