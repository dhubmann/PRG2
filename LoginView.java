/*
 * LoginView
 * Represents Login View
 * Author: Daniel Hubmann
 * Last Change: 01.05.2023
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JCheckBox chckbxAdmin, chckbxAssistant;
	private JButton btnLogin, btnForgotPassword, btnCreateAccount;
	private JTextField tfUsername;
	private JPasswordField tfPassword;

	// Getters
	public JFrame getFrame() {
		return frame;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JCheckBox getChckbxAdmin() {
		return chckbxAdmin;
	}

	public JCheckBox getChckbxAssistant() {
		return chckbxAssistant;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnForgotPassword() {
		return btnForgotPassword;
	}

	public JButton getBtnCreateAccount() {
		return btnCreateAccount;
	}

	public JTextField getTfUsername() {
		return tfUsername;
	}

	public JPasswordField getTfPassword() {
		return tfPassword;
	}

	/**
	 * Create the application.
	 */
	public LoginView() {

		setTitle("iSchedule Login");
		setSize(350, 315);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		// components
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 12));
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tfUsername = new JTextField();
		tfUsername.setColumns(20);
		tfPassword = new JPasswordField();
		tfPassword.setColumns(20);
		chckbxAdmin = new JCheckBox("Administrator");
		chckbxAdmin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chckbxAssistant = new JCheckBox("Assistant");
		chckbxAssistant.setFont(new Font("SansSerif", Font.PLAIN, 12));

		btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("SansSerif", Font.PLAIN, 12));

		JLabel titleLabel = new JLabel("iSchedule");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

		// set layout and add components to layout
		GroupLayout layout = new GroupLayout(contentPane);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE).addGroup(layout
								.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup().addComponent(lblUsername)
												.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(chckbxAdmin).addGap(18)
												.addComponent(chckbxAssistant))
										.addComponent(tfPassword, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
										.addComponent(tfUsername, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
						.addComponent(btnCreateAccount, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(btnForgotPassword, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(titleLabel).addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(chckbxAdmin)
								.addComponent(chckbxAssistant))
						.addGap(18).addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnForgotPassword)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCreateAccount)
						.addContainerGap(45, Short.MAX_VALUE)));
		contentPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

	}
}
