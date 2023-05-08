/*
 * Application
 * Main entry point of application
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

import java.awt.EventQueue;

public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView loginView = new LoginView();
					@SuppressWarnings("unused")
					LoginController loginController = new LoginController(loginView);
					loginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
