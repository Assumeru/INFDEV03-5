package hro.infdev035.assignment1;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.entities.Server;
import hro.infdev035.assignment1.gui.LoginScreen;

import javax.persistence.Persistence;

/**
 * Program entry point.
 * Creates a new database connection using the "assignment1" settings in persistence.xml.
 * Note that those settings drop and recreate all tables each time the program runs.
 * Creates and persists some {@link Server} objects.
 * Creates and displays the login screen.
 */
public class Main {
	public static void main(String[] args) {
		Connection connection = new Connection(Persistence.createEntityManagerFactory("assignment1").createEntityManager());
		connection.createServers();
		new LoginScreen(connection).setVisible(true);
	}
}
