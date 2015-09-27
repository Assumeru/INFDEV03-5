package hro.infdev035.assignment1.database;

import hro.infdev035.assignment1.entities.User;

import javax.persistence.EntityManager;

public class Connection {
	private EntityManager manager;

	public Connection(EntityManager manager) {
		this.manager = manager;
	}

	public User getUser(String username) {
		return manager.find(User.class, username);
	}

	public void newUser(String username, String password) {
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		manager.persist(user);
	}
}
