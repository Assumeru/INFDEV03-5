package hro.infdev035.assignment1.gui;

import hro.infdev035.assignment1.entities.Character;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CharacterList extends JPanel {
	private static final long serialVersionUID = 5653889040988731588L;
	private ManagementScreen parent;
	private JComboBox<Character> characters;
	private JLabel level;
	private JLabel name;
	private JLabel prof;
	private JLabel race;
	private JLabel server;
	private JPanel stats;

	/**
	 * Creates a new CharacterList dropdown.
	 * Appends a Character stats screen to the parent.
	 * 
	 * @param parent
	 */
	public CharacterList(CharacterManagement parent) {
		parent.add(new JScrollPane(getStats()), BorderLayout.CENTER);
		characters = new JComboBox<Character>();
		this.parent = parent.getParentScreen();
		add(characters);
		add(getSelectButton());
		updateCharacters();
	}

	/**
	 * Adds two labels to a container.
	 * 
	 * @param container
	 * @param label
	 * @return The empty label
	 */
	private JLabel addField(Container container, String label) {
		JLabel field = new JLabel();
		container.add(new JLabel(label));
		container.add(field);
		return field;
	}

	/**
	 * Creates the stats panel.
	 * 
	 * @return The stats panel
	 */
	private JPanel getStats() {
		stats = new JPanel();
		stats.setLayout(new GridLayout(5, 0));
		level = addField(stats, "Level: ");
		name = addField(stats, "Name: ");
		prof = addField(stats, "Class: ");
		race = addField(stats, "Race: ");
		server = addField(stats, "Server: ");
		//Hide until a Character has been selected
		stats.setVisible(false);
		return stats;
	}

	/**
	 * @return The View (Character) button with its ActionListener
	 */
	private JButton getSelectButton() {
		JButton button = new JButton("View");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Character selected = (Character) characters.getSelectedItem();
				if(selected != null) {
					showStatsFor(selected);
					stats.setVisible(true);
				}
			}
		});
		return button;
	}

	/**
	 * Updates the dropdown from the database.
	 */
	public void updateCharacters() {
		List<Character> chars = parent.getConnection().getCharactersByLevel(parent.getUser());
		if(chars != null && chars.size() > 0) {
			characters.removeAllItems();
			for(Character c : chars) {
				characters.addItem(c);
			}
		}
		setVisible(chars != null && chars.size() > 0);
	}

	/**
	 * Displays a Character's stats.
	 * 
	 * @param selected
	 */
	private void showStatsFor(Character selected) {
		level.setText(String.valueOf(selected.getLevel()));
		name.setText(selected.getName());
		prof.setText(selected.getProfession());
		race.setText(selected.getRace());
		server.setText(String.valueOf(selected.getServer()));
	}
}
