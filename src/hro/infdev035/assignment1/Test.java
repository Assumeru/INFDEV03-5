package hro.infdev035.assignment1;

import hro.infdev035.assignment1.entities.Server;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Test {
	public static void main(String[] args) {
		EntityManager manager = Persistence.createEntityManagerFactory("assignment1").createEntityManager();
		manager.getTransaction().begin();
		Server ns = createServer();
		manager.persist(ns);
		manager.getTransaction().commit();
		Server test = manager.find(Server.class, "address");
		System.out.println(test == ns);
		manager.close();
	}

	private static Server createServer() {
		Server server = new Server();
		server.setAddress("address");
		server.setConnectedUsers(0);
		server.setLocation("somewhere");
		server.setMaxUsers(5);
		server.setName("server");
		return server;
	}
}
