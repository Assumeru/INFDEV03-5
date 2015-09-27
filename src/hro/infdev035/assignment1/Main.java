package hro.infdev035.assignment1;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.gui.LoginScreen;

import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		Connection connection = new Connection(Persistence.createEntityManagerFactory("assignment1").createEntityManager());
		new LoginScreen(connection).setVisible(true);
	}
}
