/*
 * LoginController
 * Represents Logic behind Login View
 * Author: Daniel Hubmann
 * Last Change: 27.05.2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoginController {

	private LoginView loginView;
	private boolean isStudent;
	private boolean isAdmin;
	private boolean isAssistant;
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

	public boolean isAssistant() {
		return isAssistant;
	}

	public void setAssistant(boolean isAssistant) {
		this.isAssistant = isAssistant;
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
				isAssistant = loginView.getChckbxAssistant().isSelected();

				// Input Validation
				if (InputValidator.checkBlankInput(username)) {
					InputValidator.blankUsername();
					return;
				}

				if (InputValidator.checkBlankInput(password)) {
					InputValidator.blankPassword();
					return;
				}

				if (InputValidator.isAdminAndAssistant(isAdmin, isAssistant)) {
					InputValidator.isAdminAndAssistant();
					return;
				}

				if (getUser(username, password, isAdmin, isAssistant) == null) {
					createAccountWarning();
					return;
				} else {
					user = getUser(username, password, isAdmin, isAssistant);
				}

				// TODO: implement Privilegies Class
				if (isAdmin == false && isAssistant == false) {
					isStudent = true; // show student schedule view
				}

				if (isAdmin == true && isAssistant == false) {
					isAdmin = true; // show admin schedule view
				}

				if (isAdmin == false && isAssistant == true) {
					isAssistant = true; // show Assistant schedule view
				}

				try {
					ScheduleView scheduleView = new ScheduleView();
					ScheduleController scheduleController = new ScheduleController(scheduleView, LoginController.this);

					// adds test rooms to scheduleview
					for (int i = 0; i < Model.getRooms().size(); i++) {
						scheduleController.newRoomColumn(Model.getRooms().get(i), i);
					}

					// adds test courses to rooms
					for (int i = 0; i < Model.getCourses().size(); i++) {
						scheduleController.newCourseBlock(Model.getCourses().get(i));
					}

					loginView.dispose();
					scheduleView.setVisible(true);
					scheduleController.preferencesMismatch();
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

	public User getUser(String username, String password, boolean isAdmin, boolean isAssistant) {
		for (User u : Model.getTeachingStaff()) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.isAdmin() == isAdmin
					&& u.isAssistant() == isAssistant) {
				return u;
			}
		}
		for (User u : Model.getStudents()) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.isAdmin() == isAdmin
					&& u.isAssistant() == isAssistant) {
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
