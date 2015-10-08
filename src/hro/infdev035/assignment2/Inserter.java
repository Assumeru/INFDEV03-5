package hro.infdev035.assignment2;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.entities.Character;
import hro.infdev035.assignment1.entities.Server;
import hro.infdev035.assignment1.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that inserts random data into the database for testing.
 */
public class Inserter implements Runnable {
	private Connection connection;
	private int times;
	private Random random;
	private List<User> users;
	private List<Server> servers;

	public Inserter(Connection connection, int times) {
		this.connection = connection;
		this.times = times;
		random = new Random();
		users = new ArrayList<>(times);
		servers = new ArrayList<>(times);
	}

	@Override
	public void run() {
		System.out.println("Servers in: " + insertServers(times) + " ms");
		System.out.println("Users in: " + insertUsers(times) + " ms");
		System.out.println("Characters in: " + insertCharacters(times) + " ms");
	}

	/**
	 * @return A random string of length [14, 63]
	 */
	private String getRandomString() {
		StringBuilder builder = new StringBuilder();
		int length = random.nextInt(50) + 14;
		for(int i = 0; i < length; i++) {
			builder.append((char)(random.nextInt(94)+32));
		}
		return builder.toString();
	}

	/**
	 * Inserts random Servers into the database.
	 * 
	 * @param amount The number of Servers to create
	 * @return The insertion time in milliseconds
	 */
	private long insertServers(int amount) {
		long time = System.currentTimeMillis();
		for(int n = 0; n < amount; n++) {
			//Use n as address to prevent primary key conflicts
			servers.add(connection.createServer(String.valueOf(n), getRandomString(), getRandomString(), random.nextLong()));
		}
		return System.currentTimeMillis() - time;
	}

	/**
	 * Inserts random Users into the database.
	 * 
	 * @param amount The number of Users to create
	 * @return The insertion time in milliseconds
	 */
	private long insertUsers(int amount) {
		long time = System.currentTimeMillis();
		for(int n = 0; n < amount; n++) {
			//Use n as username to prevent primary key conflicts
			users.add(connection.newUser(String.valueOf(n), getRandomString(), getRandomString(), getRandomString(), getRandomString()));
		}
		return System.currentTimeMillis() - time;
	}

	/**
	 * Inserts random Characters into the database.
	 * 
	 * @param amount The number of Characters to create
	 * @return The insertion time in milliseconds
	 */
	private long insertCharacters(int amount) {
		long time = System.currentTimeMillis();
		for(int n = 0; n < amount; n++) {
			Character newChar = new Character();
			newChar.setLevel(random.nextInt());
			//Use n as name to prevent primary key conflicts
			newChar.setName(String.valueOf(n));
			newChar.setOwner(users.get(random.nextInt(times)));
			newChar.setProfession(getRandomString());
			newChar.setRace(getRandomString());
			newChar.setServer(servers.get(random.nextInt(times)));
			connection.addCharacter(newChar);
		}
		return System.currentTimeMillis() - time;
	}
}
