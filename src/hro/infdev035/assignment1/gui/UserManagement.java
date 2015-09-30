package hro.infdev035.assignment1.gui;

import hro.infdev035.assignment1.entities.Subscription;
import hro.infdev035.assignment1.entities.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserManagement extends JPanel {
	private static final long serialVersionUID = 2644973936980420693L;
	private static final Subscription[] subscriptions = new Subscription[] {
		new Subscription(1, 5), new Subscription(2, 8), new Subscription(3, 10), new Subscription(12, 35)
	};
	private ManagementScreen parent;
	private JLabel balance;
	private JLabel slots;

	public UserManagement(ManagementScreen managementScreen) {
		parent = managementScreen;
		add(new JLabel("Welcome "+parent.getUser().getName()));
		add(getBalanceButton());
		JPanel balance = new JPanel();
		balance.add(new JLabel("Balance: €"));
		this.balance = new JLabel(String.valueOf(parent.getUser().getBalance()));
		balance.add(this.balance);
		add(balance);
		add(getSubscriptionButton());
		JPanel slots = new JPanel();
		slots.add(new JLabel("Character slots: "));
		this.slots = new JLabel(String.valueOf(parent.getUser().getCharacterSlots()));
		slots.add(this.slots);
		add(slots);
		add(getSlotsButton());
	}

	/**
	 * Changes the User's balance and updates the balance label.
	 * 
	 * @param amount
	 */
	private void changeBalance(double amount) {
		parent.getConnection().changeBalance(parent.getUser(), amount);
		balance.setText(String.valueOf(parent.getUser().getBalance()));
	}

	/**
	 * @return The Add money button with its ActionListener
	 */
	private JButton getBalanceButton() {
		JButton button = new JButton("Add money");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				double money = -1;
				do {
					String input = JOptionPane.showInputDialog(parent, "How much money do you want to add?", "Get rich quick", JOptionPane.QUESTION_MESSAGE);
					if(input == null) {
						//User clicked Cancel
						return;
					}
					try {
						money = Double.parseDouble(input);
					} catch(NumberFormatException e) {}
					//Loop while number if invalid
				} while(money < 0);
				changeBalance(money);
			}
		});
		return button;
	}

	/**
	 * @return The Add subscription button with its ActionListener
	 */
	private JButton getSubscriptionButton() {
		JButton button = new JButton("Add subscription");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Subscription sub = (Subscription) JOptionPane.showInputDialog(parent, "Subscribe for", "Add subscription", JOptionPane.QUESTION_MESSAGE, null, subscriptions, null);
				if(sub != null) {
					User user = parent.getUser();
					//Check if the User's balance is high enough
					if(user.getBalance() >= sub.getPrice()) {
						parent.getConnection().addSubscription(user, sub);
						//Update labels
						balance.setText(String.valueOf(user.getBalance()));
						slots.setText(String.valueOf(user.getCharacterSlots()));
					} else {
						JOptionPane.showConfirmDialog(parent, "You only have €"+user.getBalance(), "Balance too low", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		return button;
	}

	private JButton getSlotsButton() {
		JButton button = new JButton("Add character slot (€1)");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				User user = parent.getUser();
				//Check if the User's balance is high enough
				if(user.getBalance() >= 1) {
					parent.getConnection().addSlot(user);
					//Update labels
					balance.setText(String.valueOf(user.getBalance()));
					slots.setText(String.valueOf(user.getCharacterSlots()));
				} else {
					JOptionPane.showConfirmDialog(parent, "You only have €"+user.getBalance(), "Balance too low", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		return button;
	}

	JLabel getSlots() {
		return slots;
	}
}
