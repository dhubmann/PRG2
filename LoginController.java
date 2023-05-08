/*
 * LoginController
 * Represents Logic behind Login View
 * Author: Daniel Hubmann
 * Last Change: 08.05.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginController {

	private LoginView loginView;
	private boolean isStudent;
	private boolean isAdmin;
	private boolean isAssistent;
	private User user;

	// Getters & Setters
	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAssistent() {
		return isAssistent;
	}

	public void setAssistent(boolean isAssistent) {
		this.isAssistent = isAssistent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Constructor
	public LoginController(LoginView loginView) {
		this.loginView = loginView;

		// LOGIN
		loginView.getBtnLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String username = loginView.getTfUsername().getText();
				String password = new String(loginView.getTfPassword().getPassword());
				isAdmin = loginView.getChckbxAdmin().isSelected();
				isAssistent = loginView.getChckbxAssistent().isSelected();

				// Input Validation
				if (InputValidator.checkBlankInput(username)) {
					InputValidator.blankUsername();
					return;
				}

				if (InputValidator.checkBlankInput(password)) {
					InputValidator.blankPassword();
					return;
				}

				if (InputValidator.isAdminAndAssistent(isAdmin, isAssistent)) {
					InputValidator.isAdminAndAssistent();
					return;
				}

				if (getUser(username, password, isAdmin, isAssistent) == null) {
					createAccountWarning();
					loginView.getTfUsername().setText("");
					loginView.getTfPassword().setText("");
					return;
				} else {
					user = getUser(username, password, isAdmin, isAssistent);
				}

				// TODO: implement Privilegies Class
				if (isAdmin == false && isAssistent == false) {
					isStudent = true; // show student schedule view
				}

				if (isAdmin == true && isAssistent == false) {
					isAdmin = true; // show admin schedule view
				}

				if (isAdmin == false && isAssistent == true) {
					isAssistent = true; // show assistent schedule view
				}

				try {
					ScheduleView scheduleView = new ScheduleView();
					ScheduleController scheduleController = new ScheduleController(scheduleView, LoginController.this);

					// adds test rooms to scheduleview
					for (int i = 0; i < Model.getRooms().size(); i++) {
						scheduleController.newRoomColumn(Model.getRooms().get(i), i); // keep an eye on this
					}

					// add test courses to rooms
					for (int i = 0; i < Model.getCourses().size(); i++) {
						scheduleController.newCourseBlock(Model.getCourses().get(i));
					}

					loginView.dispose();
					scheduleView.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		// FORGOT PASSWORD
		loginView.getBtnForgotPassword().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ForgotPassword forgotPassword = new ForgotPassword();
					forgotPassword.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		// CREATE ACCOUNT
		loginView.getBtnCreateAccount().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					CreateAccount createAccount = new CreateAccount();
					createAccount.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

	}

	public User getUser(String username, String password, boolean isAdmin, boolean isAssistent) {
		for (User u : Model.getTeachingStaff()) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.isAdmin() == isAdmin
					&& u.isAssistent() == isAssistent) {
				return u;
			}
		}
		for (User u : Model.getStudents()) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.isAdmin() == isAdmin
					&& u.isAssistent() == isAssistent) {
				return u;
			}
		}
		return null;
	}

	public void createAccountWarning() {
		JOptionPane.showMessageDialog(null,
				"We're sorry - it seems that you don't have an account with us yet.\nPlease click on 'Create Account' to create an account. Thank you!");
	}

}
