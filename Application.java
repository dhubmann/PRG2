/*
 * Application
 * Main entry point of application
 * Author: Daniel Hubmann
 * Last Change: 29.04.2023
 */

import java.awt.EventQueue;
import java.time.LocalTime;
import java.util.ArrayList;

public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView loginView = new LoginView();
					LoginController loginController = new LoginController(loginView);
					loginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
