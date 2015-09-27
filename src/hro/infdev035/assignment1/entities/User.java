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
}
