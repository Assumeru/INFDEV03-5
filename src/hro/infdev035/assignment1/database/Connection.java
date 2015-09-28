package hro.infdev035.assignment1.database;

import hro.infdev035.assignment1.entities.Character;
import hro.infdev035.assignment1.entities.Server;
import hro.infdev035.assignment1.entities.Subscription;
import hro.infdev035.assignment1.entities.User;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Connection {
	private EntityManager manager;

	public Connection(EntityManager manager) {
		this.manager = manager;
	}

	public User getUser(String username) {
		return manager.find(User.class, username);
	}

	public User newUser(String username, String password, String firstName, String lastName, String iban) {
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setBalance(0);
		user.setBanned(false);
		user.setCharacterSlots(0);
		user.setFirstName(firstName);
		user.setIban(iban);
		user.setLastName(lastName);
		user.setMonthsPaid(0);
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		return user;
	}

	public void changeBalance(User user, double balance) {
		manager.getTransaction().begin();
		user.setBalance(user.getBalance() + balance);
		manager.getTransaction().commit();
	}

	public void addSubscription(User user, Subscription sub) {
		manager.getTransaction().begin();
		user.setMonthsPaid(sub.getMonths());
		user.setLastPayment(new Date());
		user.setCharacterSlots(user.getCharacterSlots() + 5);
		user.setBalance(user.getBalance() - sub.getPrice());
		manager.getTransaction().commit();
	}

	public void addSlot(User user) {
		manager.getTransaction().begin();
		user.setCharacterSlots(user.getCharacterSlots() + 1);
		user.setBalance(user.getBalance() - 1);
		manager.getTransaction().commit();
	}

	public void createServers() {
		Server s1 = new Server();
		s1.setAddress("1");
		s1.setConnectedUsers(0);
		s1.setLocation("serverland");
		s1.setMaxUsers(1);
		s1.setName("s1");
		Server s2 = new Server();
		s2.setAddress("2");
		s2.setConnectedUsers(0);
		s2.setLocation("serverland");
		s2.setMaxUsers(0);
		s2.setName("s2");
		manager.getTransaction().begin();
		manager.persist(s1);
		manager.persist(s2);
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Server> getServers() {
		return manager.createQuery("SELECT e FROM Server e").getResultList();
	}

	public boolean characterTaken(String name) {
		return manager.find(Character.class, name) != null;
	}

	public void addCharacter(Character character) {
		manager.getTransaction().begin();
		manager.persist(character);
		User owner = character.getOwner();
		owner.setCharacterSlots(owner.getCharacterSlots() - 1);
		owner.getCharacters().add(character);
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Character> getCharactersByLevel(User user) {
		Query query = manager.createQuery("SELECT e FROM Character e WHERE e.owner = :user ORDER BY e.level");
		query.setParameter("user", user);
		return query.getResultList();
	}

	public void connect(Server server) {
		manager.getTransaction().begin();
		server.setConnectedUsers(server.getConnectedUsers() + 1);
		manager.getTransaction().commit();
	}
}
