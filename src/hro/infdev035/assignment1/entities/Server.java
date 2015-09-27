package hro.infdev035.assignment1.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servers")
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		this.maxUsers = maxUsers;
	}

	public long getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(long connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
