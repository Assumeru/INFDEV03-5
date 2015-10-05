package hro.infdev035.assignment1.gui;

import hro.infdev035.assignment1.entities.Character;
import hro.infdev035.assignment1.entities.Server;
import hro.infdev035.assignment1.entities.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CharacterManagement extends JPanel {
	private static final long serialVersionUID = 8051845527118111286L;
	private static final String NEW_TITLE = "Character creation";
	private ManagementScreen parent;
	private CharacterList chars;

	public CharacterManagement(ManagementScreen managementScreen) {
		parent = managementScreen;
		setLayout(new BorderLayout());
		JPanel menu = new JPanel();
		menu.add(getCharacterButton());
		menu.add(getCharacterDropDown());
		menu.add(getConnect());
		add(menu, BorderLayout.NORTH);
	}

	ManagementScreen getParentScreen() {
		return parent;
	}

	/**
	 * @return The Connect button with its ActionListener
	 */
	private JButton getConnect() {
		JButton button = new JButton("Connect");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Server[] servers = parent.getConnection().getServers().toArray(new Server[0]);
				Server server = (Server) JOptionPane.showInputDialog(parent, "Select a server", "Connect", JOptionPane.QUESTION_MESSAGE, null, servers, null);
				if(server == null) {
					//User clicked Cancel
					return;
				} else if(server.getConnectedUsers() >= server.getMaxUsers()) {
					JOptionPane.showConfirmDialog(parent, "Server is full", "Connect error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				parent.getConnection().connect(server, getParentScreen().getUser());
			}
		});
		return button;
	}

	/**
	 * @return The CharacterList dropdown, also sets it
	 */
	private CharacterList getCharacterDropDown() {
		chars = new CharacterList(this);
		return chars;
	}

	/**
	 * @return The Create character button with its ActionListener
	 */
	private JButton getCharacterButton() {
		JButton button = new JButton("Create character");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				User user = parent.getUser();
				if(user.getCharacterSlots() <= 0) {
					JOptionPane.showConfirmDialog(parent, "You are at your character limit", "Too few character slots", JOptionPane.WARNING_MESSAGE);
				} else {
					//Let the User fill out the Character creation form in a series of dialogs
					Server[] servers = parent.getConnection().getServers().toArray(new Server[0]);
					Server server = (Server) JOptionPane.showInputDialog(parent, "Select a server", NEW_TITLE, JOptionPane.QUESTION_MESSAGE, null, servers, null);
					if(server == null) {
						//Cancel
						return;
					}
					String name = JOptionPane.showInputDialog(parent, "Enter a name",  NEW_TITLE, JOptionPane.QUESTION_MESSAGE);
					if(name == null || name.isEmpty()) {
						//Cancel
						return;
					} else if(parent.getConnection().characterTaken(name)) {
						JOptionPane.showConfirmDialog(parent, "This name is unavailable", NEW_TITLE, JOptionPane.WARNING_MESSAGE);
						return;
					}
					String race = JOptionPane.showInputDialog(parent, "Enter a race",  NEW_TITLE, JOptionPane.QUESTION_MESSAGE);
					if(race == null || race.isEmpty()) {
						//Cancel
						return;
					}
					String prof = JOptionPane.showInputDialog(parent, "Enter a class",  NEW_TITLE, JOptionPane.QUESTION_MESSAGE);
					if(prof == null || prof.isEmpty()) {
						//Cancel
						return;
					}
					Character character = new Character();
					character.setName(name);
					character.setOwner(user);
					character.setProfession(prof);
					character.setRace(race);
					character.setServer(server);
					character.setLevel(new Random().nextInt(100) + 1);
					parent.getConnection().addCharacter(character);
					parent.getUserManagement().getSlots().setText(String.valueOf(user.getCharacterSlots()));
					chars.updateCharacters();
				}
			}
		});
		return button;
	}
}
