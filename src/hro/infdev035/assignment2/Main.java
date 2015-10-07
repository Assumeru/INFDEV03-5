package hro.infdev035.assignment2;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.entities.User;

import java.util.Random;

import javax.persistence.Persistence;

public class Main {
	private static final int TEST_TIMES = 10;
	private static final int REGISTRATION_TIMES = 10000;
	private static final int DROPDOWN_TIMES = 1000;
	private static final int CHARACTER_TIMES = 1000000;

	public static void main(String[] args) {
		Connection connection = new Connection(Persistence.createEntityManagerFactory("assignment1").createEntityManager());
		new Inserter(connection, 1000).run();
		testRegistration(connection);
		testCharacterManagement(connection);
	}

	private static void testRegistration(final Connection connection) {
		final Random random = new Random();
		Test test = new Test(REGISTRATION_TIMES) {
			@Override
			protected void runTest() {
				connection.getUser(String.valueOf(random.nextInt(1000)));
			}
			
			@Override
			protected void createIndex() {
				connection.getManager().getTransaction().begin();
				connection.getManager().createNativeQuery("CREATE INDEX index1 ON users USING hash(name)").executeUpdate();
				connection.getManager().getTransaction().commit();
			}

			@Override
			protected void deleteIndex() {
				connection.getManager().getTransaction().begin();
				connection.getManager().createNativeQuery("DROP INDEX IF EXISTS index1").executeUpdate();
				connection.getManager().getTransaction().commit();
			}
		};
		System.out.println("Index on users.name");
		new Tester(test, TEST_TIMES).run();
	}

	private static void testCharacterManagement(final Connection connection) {
		testCharacterNameTaken(connection);
		testCharacterDropdown(connection);
	}

	private static void testCharacterNameTaken(final Connection connection) {
		final Random random = new Random();
		Test test = new Test(CHARACTER_TIMES) {
			@Override
			protected void runTest() {
				connection.characterTaken(String.valueOf(random.nextInt(1000)));
			}
			
			@Override
			protected void createIndex() {
				connection.getManager().getTransaction().begin();
				connection.getManager().createNativeQuery("CREATE INDEX index2 ON characters USING hash(name)").executeUpdate();
				connection.getManager().getTransaction().commit();
			}

			@Override
			protected void deleteIndex() {
				connection.getManager().getTransaction().begin();
				connection.getManager().createNativeQuery("DROP INDEX IF EXISTS index2").executeUpdate();
				connection.getManager().getTransaction().commit();
			}
		};
		System.out.println("Index on characters.name");
		new Tester(test, TEST_TIMES).run();
	}

	private static void testCharacterDropdown(final Connection connection) {
		final User user = connection.getUser("0");
		Test test = new Test(DROPDOWN_TIMES) {
			@Override
			protected void runTest() {
				connection.getCharactersByLevel(user);
			}
			
			@Override
			protected void createIndex() {
				connection.getManager().getTransaction().begin();
				connection.getManager().createNativeQuery("CREATE INDEX index3 ON characters USING btree(level)").executeUpdate();
				connection.getManager().getTransaction().commit();
			}

			@Override
			protected void deleteIndex() {
				connection.getManager().getTransaction().begin();
				connection.getManager().createNativeQuery("DROP INDEX IF EXISTS index3").executeUpdate();
				connection.getManager().getTransaction().commit();
			}
		};
		System.out.println("Index on characters.level");
		new Tester(test, TEST_TIMES).run();
	}
}
