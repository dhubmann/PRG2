/*
 * ForgotPassword
 * Represents ForgotPassword View and Logic
 * Author: Daniel Hubmann
 * Author: 1.5.2023
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ForgotPassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmailAddress;

	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		setTitle("Forgot Password?");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 139);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Please enter your email address to recieve a new password:");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTitle.setBounds(10, 11, 334, 14);
		contentPane.add(lblTitle);

		JLabel lblEmail = new JLabel("Email address:");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmail.setBounds(10, 36, 90, 14);
		contentPane.add(lblEmail);

		tfEmailAddress = new JTextField();
		tfEmailAddress.setBounds(110, 33, 234, 20);
		contentPane.add(tfEmailAddress);
		tfEmailAddress.setColumns(10);

		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: implement email check
				if (userEmail(tfEmailAddress.getText())) {
					checkEmailInboxMessage();
				} else {
					createAccountMessage();
				}
				dispose();
			}
		});
		btnContinue.setBounds(10, 61, 334, 28);
		contentPane.add(btnContinue);
	}

	// Checks if user has an account
	public boolean userEmail(String email) {
		for (User u : Model.getTeachingStaff()) {
			if (u.getEmail().equals(email)) {
				return true;
			}
		}
		for (User u : Model.getStudents()) {
			if (u.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public void checkEmailInboxMessage() {
		JOptionPane.showMessageDialog(null,
				"Please check your email inbox for further instructions on how to reset your password.");
	}

	public void createAccountMessage() {
		JOptionPane.showMessageDialog(null,
				"We are sorry - we couldn't find your email address in our system.\nPlease create an account to access the application. Thank you!");
	}

}
