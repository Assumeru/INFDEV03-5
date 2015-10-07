package hro.infdev035.assignment1.gui;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.entities.User;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginScreen extends JFrame {
	private static final long serialVersionUID = -8647423325301607823L;
	private boolean register = false;
	private JTextField username;
	private JTextField password;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField iban;
	private JPanel registration;
	private JButton submit;
	private Connection connection;

	public LoginScreen(Connection connection) {
		this.connection = connection;
		setTitle("Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 300);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		addForm();
	}

	/**
	 * Adds a text field and label to a container.
	 * 
	 * @param container
	 * @param label
	 * @return The created text field
	 */
	private JTextField addTextField(Container container, String label) {
		JTextField textField = new JTextField();
		container.add(new JLabel(label));
		container.add(textField);
		return textField;
	}

	/**
	 * Adds the login form and registers ActionListeners.
	 */
	private void addForm() {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 2));
		username = addTextField(container, "Username ");
		container.add(new JLabel("Password "));
		password = new JPasswordField();
		container.add(password);
		submit = new JButton("Login");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(!register) {
					performLogin(username.getText(), password.getText());
				} else {
					performRegistration(username.getText(), password.getText(), firstname.getText(), lastname.getText(), iban.getText());
				}
			}
		});
		final JLabel swap = new JLabel("Register");
		swap.setForeground(Color.BLUE);
		swap.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent event) {}
			@Override
			public void mousePressed(MouseEvent event) {}
			@Override
			public void mouseExited(MouseEvent event) {}
			@Override
			public void mouseEntered(MouseEvent event) {}
			@Override
			public void mouseClicked(MouseEvent event) {
				if(!register) {
					swap.setText("Login");
					submit.setText("Register");
				} else {
					swap.setText("Register");
					submit.setText("Login");
				}
				register = !register;
				registration.setVisible(register);
			}
		});
		container.add(submit);
		container.add(swap);
		add(container);
		registration = new JPanel();
		registration.setLayout(new GridLayout(0, 2));
		lastname = addTextField(registration, "Last name: ");
		firstname = addTextField(registration, "First name: ");
		iban = addTextField(registration, "IBAN: ");
		add(registration);
		registration.setVisible(false);
	}

	/**
	 * Displays a warning dialog if username or password is empty.
	 * 
	 * @param username
	 * @param password
	 * @return False if username or password is empty
	 */
	private boolean checkEntered(String username, String password) {
		if(username.isEmpty()) {
			showWarning("Please enter a username", "Error");
			return false;
		} else if(username.isEmpty()) {
			showWarning("Please enter a password", "Error");
			return false;
		}
		return true;
	}

	/**
	 * Logs a User in.
	 * 
	 * @param username
	 * @param password
	 */
	private void performLogin(String username, String password) {
		if(!checkEntered(username, password)) {
			return;
		}
		User user = connection.getUser(username);
		if(user == null) {
			showWarning("User does not exist", "Login error");
		} else if(!user.getPassword().equals(password)) {
			showWarning("Wrong password", "Login error");
		} else {
			login(user);
		}
	}

	/**
	 * Opens the management screen, hiding the login screen.
	 * 
	 * @param user
	 */
	private void login(User user) {
		new ManagementScreen(connection, user).setVisible(true);;
		setVisible(false);
	}

	/**
	 * Creates a new User and logs them in.
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param iban
	 */
	private void performRegistration(String username, String password, String firstName, String lastName, String iban) {
		if(!checkEntered(username, password)) {
			return;
		} else if(firstName.isEmpty()) {
			showWarning("Please enter a first name", "Registration error");
			return;
		} else if(lastName.isEmpty()) {
			showWarning("Please enter a last name", "Registration error");
			return;
		} else if(iban.isEmpty()) {
			showWarning("Please enter your IBAN", "Registration error");
			return;
		} else if(connection.nameTaken(username)) {
			showWarning("This name is taken, please select another", "Registration error");
			return;
		}
		login(connection.newUser(username, password, firstName, lastName, iban));
	}

	/**
	 * Shows a warning dialog.
	 * 
	 * @param message
	 * @param title
	 */
	private void showWarning(String message, String title) {
		JOptionPane.showConfirmDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
	}
}
