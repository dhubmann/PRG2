/*
 * CreateAccount
 * Represents CreateAccount View and Logic
 * Author: Daniel Hubmann
 * Author: 08.05.2023
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CreateAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername, tfEmailAddress;
	private JCheckBox chckbxAdministrator, chckbxAssistent;
	private JPasswordField tfPassword, tfConfirmPassword;
	private JLabel lblTitleISchedule;

	/**
	 * Create the frame.
	 */
	public CreateAccount() {
		setTitle("Create Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 315);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Please fill out the fields to create your account!");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTitle.setBounds(10, 50, 314, 14);
		contentPane.add(lblTitle);

		JLabel lblUsername = new JLabel("Choose a username:");
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblUsername.setBounds(10, 88, 145, 14);
		contentPane.add(lblUsername);

		JLabel lblEmailAddress = new JLabel("Email address:");
		lblEmailAddress.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEmailAddress.setBounds(10, 150, 145, 14);
		contentPane.add(lblEmailAddress);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPassword.setBounds(10, 179, 105, 14);
		contentPane.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(10, 208, 105, 14);
		contentPane.add(lblConfirmPassword);

		chckbxAssistent = new JCheckBox("Assistent");
		chckbxAssistent.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chckbxAssistent.setBounds(125, 113, 99, 30);
		contentPane.add(chckbxAssistent);

		chckbxAdministrator = new JCheckBox("Administrator");
		chckbxAdministrator.setFont(new Font("SansSerif", Font.PLAIN, 12));
		chckbxAdministrator.setBounds(10, 113, 99, 30);
		contentPane.add(chckbxAdministrator);

		tfUsername = new JTextField();
		tfUsername.setBounds(155, 86, 169, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);

		tfEmailAddress = new JTextField();
		tfEmailAddress.setBounds(155, 148, 169, 20);
		contentPane.add(tfEmailAddress);
		tfEmailAddress.setColumns(10);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(155, 177, 169, 20);
		contentPane.add(tfPassword);

		tfConfirmPassword = new JPasswordField();
		tfConfirmPassword.setBounds(155, 206, 169, 20);
		contentPane.add(tfConfirmPassword);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = tfUsername.getText();
				boolean isAdmin = chckbxAdministrator.isSelected();
				boolean isAssistent = chckbxAssistent.isSelected();
				String email = tfEmailAddress.getText();
				String password = new String(tfPassword.getPassword());
				String confirmPassword = new String(tfConfirmPassword.getPassword());

				// TODO: check valid input

				// Input Validation
				if (InputValidator.checkBlankInput(username)) {
					InputValidator.blankUsername();
					return;
				}

				if (InputValidator.checkBlankInput(email)) {
					InputValidator.blankEmail();
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

				if (!password.equals(confirmPassword)) {
					passwordsDontMatch();
					return;
				}

				if (userDuplicate(username)) {
					userDuplicateWarning();
					return;
				}

				if (emailDuplicate(email)) {
					emailDuplicateWarning();
					return;
				}

				User user = null;

				// Create new user
				if (isAdmin) {
					user = new Administrator();
				} else if (isAssistent) {
					user = new Assistent();
				} else {
					user = new Student();
				}
				user.setUsername(username);
				user.setPassword(password);
				user.setAdmin(isAdmin);
				user.setAssistent(isAssistent);
				user.setEmail(email);

				dispose();

			}

		});
		btnCreateAccount.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCreateAccount.setBounds(10, 237, 314, 28);
		contentPane.add(btnCreateAccount);

		lblTitleISchedule = new JLabel("ISchedule");
		lblTitleISchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleISchedule.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitleISchedule.setBounds(10, 11, 314, 28);
		contentPane.add(lblTitleISchedule);
	}

	private boolean userDuplicate(String username) {
		for (User u : Model.getTeachingStaff()) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		for (User u : Model.getStudents()) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	private boolean emailDuplicate(String email) {
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

	public void userDuplicateWarning() {
		JOptionPane.showMessageDialog(null, "We are sorry - the username you entered already exists.");
	}

	public void emailDuplicateWarning() {
		JOptionPane.showMessageDialog(null, "We are sorry - the email address you entered already exists.");
	}

	public void passwordsDontMatch() {
		JOptionPane.showMessageDialog(null,
				"We are sorry - it seems the passwords do not match.\nPlease enter the same password in both fields.");
	}
}
