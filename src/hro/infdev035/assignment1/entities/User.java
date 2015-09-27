package hro.infdev035.assignment1.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column
	private String name;
	@Column
	private double balance;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String iban;
	@Column
	private int characterSlots;
	@Column
	private Date lastPayment;
	@Column
	private int monthsPaid;
	@Column
	private String password;
	@Column
	private boolean banned;
	@OneToMany
	private List<Character> characters;
	@ManyToOne
	private Server server;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getCharacterSlots() {
		return characterSlots;
	}

	public void setCharacterSlots(int characterSlots) {
		this.characterSlots = characterSlots;
	}

	public Date getLastPayment() {
		return lastPayment;
	}

	public void setLastPayment(Date lastPayment) {
		this.lastPayment = lastPayment;
	}

	public int getMonthsPaid() {
		return monthsPaid;
	}

	public void setMonthsPaid(int monthsPaid) {
		this.monthsPaid = monthsPaid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
}
