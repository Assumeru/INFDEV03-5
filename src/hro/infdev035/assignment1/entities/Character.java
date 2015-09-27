package hro.infdev035.assignment1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "characters")
public class Character {
	@Id
	@Column
	private String name;
	@Column
	private String profession;//class
	@Column
	private String race;
	@Column
	private int level;
	@ManyToOne
	private User owner;
}
