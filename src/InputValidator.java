import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 * InputValidator
 * Represents an input validator object
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

public class InputValidator {

	private static final String PASSWORD_PATTERN = "\\w{4,}";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+@[a-zA-Z]+\\\\.[a-zA-Z][a-zA-Z]+";

	// Checks input for blankness
	public static boolean checkBlankInput(String input) {
		if (input.isBlank()) {
			return true;
		}
		return false;
	}

	// Checks password pattern
	public static boolean checkPassword(String password) {
		if (!password.matches(PASSWORD_PATTERN)) {
			return false;
		}
		return true;
	}

	// Checks email adress pattern
	public static boolean checkEmailAddress(String emailAddress) {
		if (!emailAddress.matches(EMAIL_PATTERN)) {
			return false;
		}
		return true;
	}

	// Checks for duplicates in a list
	public static boolean checkForDuplicateName(User user, ArrayList<User> list) {
		for (User u : list) {
			if (u.getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

	// Checks for duplicates in a list
	public static boolean checkForDuplicateEmail(User user, ArrayList<User> list) {
		for (User u : list) {
			if (u.getEmail().equals(user.getEmail())) {
				return true;
			}
		}
		return false;
	}

	// Checks if user is administrator and assistent (not allowed)
	public static boolean isAdminAndAssistent(boolean isAdmin, boolean isAssistent) {
		if (isAdmin && isAssistent) {
			return true;
		}
		return false;
	}

	// Warning messages
	public static void blankUsername() {
		JOptionPane.showMessageDialog(null, "Please enter your user name.");
	}

	public static void blankEmail() {
		JOptionPane.showMessageDialog(null, "Please enter your email address.");
	}

	public static void blankPassword() {
		JOptionPane.showMessageDialog(null, "Please enter your password.");
	}

	public static void isAdminAndAssistent() {
		JOptionPane.showMessageDialog(null,
				"We are sorry - Users are not allowed to be Administrators and Assistents.");
	}

}
