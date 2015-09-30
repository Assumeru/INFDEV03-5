package hro.infdev035.assignment1.gui;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.entities.User;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class ManagementScreen extends JFrame {
	private static final long serialVersionUID = -1705661963213836306L;
	private Connection connection;
	private User user;
	private UserManagement userManagement;

	/**
	 * Creates a new ManagementScreen with a UserManagement and CharacterManagement tab.
	 * 
	 * @param connection
	 * @param user
	 */
	public ManagementScreen(Connection connection, User user) {
		this.connection = connection;
		this.user = user;
		setTitle("Management");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		JTabbedPane tabs = new JTabbedPane();
		userManagement = new UserManagement(this);
		tabs.add("User", userManagement);
		tabs.add("Characters", new CharacterManagement(this));
		add(tabs);
	}

	User getUser() {
		return user;
	}

	Connection getConnection() {
		return connection;
	}

	UserManagement getUserManagement() {
		return userManagement;
	}
}
