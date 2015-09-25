package hro.infdev035.assignment1.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Server {
	@Id
	@Column
	private String address;
	@Column
	private String name;
	@Column
	private String location;
	@Column
	private long maxUsers;
	@Column
	private long connectedUsers;
	@OneToMany
	private List<User> users;
}
