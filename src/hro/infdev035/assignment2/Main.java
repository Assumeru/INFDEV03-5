package hro.infdev035.assignment2;

import hro.infdev035.assignment1.database.Connection;

import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		Connection connection = new Connection(Persistence.createEntityManagerFactory("assignment1").createEntityManager());
		connection.createServers();
		new Thread(new Inserter(connection, 1000)).start();
	}
}
