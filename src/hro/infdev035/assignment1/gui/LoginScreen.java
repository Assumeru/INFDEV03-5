package hro.infdev035.assignment1.gui;

import hro.infdev035.assignment1.database.Connection;
import hro.infdev035.assignment1.entities.User;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginScreen extends JFrame {
	private static final long serialVersionUID = -8647423325301607823L;
	private boolean register = false;
	private JTextField username;
	private JTextField password;
	private JButton submit;
	private Connection connection;

	public LoginScreen(Connection connection) {
		this.connection = connection;
		setTitle("Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 300);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		addForm();
	}

	private void addForm() {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 2));
		container.add(new JLabel("Username "));
		username = new JTextField();
		container.add(username);
		container.add(new JLabel("Password "));
		password = new JPasswordField();
		container.add(password);
		add(container);
		submit = new JButton("Login");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println(register);
				if(!register) {
					performLogin(username.getText(), password.getText());
				} else {
					performRegister(username.getText(), password.getText());
				}
			}
		});
		final JLabel swap = new JLabel("Register");
		swap.setForeground(Color.BLUE);
		swap.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent event) {}
			@Override
			public void mousePressed(MouseEvent event) {}
			@Override
			public void mouseExited(MouseEvent event) {}
			@Override
			public void mouseEntered(MouseEvent event) {}
			@Override
			public void mouseClicked(MouseEvent event) {
				if(!register) {
					swap.setText("Login");
					submit.setText("Register");
				} else {
					swap.setText("Register");
					submit.setText("Login");
				}
				register = !register;
			}
		});
		container.add(submit);
		container.add(swap);
	}

	private boolean checkEntered(String username, String password) {
		if(username.isEmpty()) {
			showWarning("Please enter a username", "Error");
			return false;
		} else if(username.isEmpty()) {
			showWarning("Please enter a password", "Error");
			return false;
		}
		return true;
	}

	private void performLogin(String username, String password) {
		if(!checkEntered(username, password)) {
			return;
		}
		User user = connection.getUser(username);
		if(user == null) {
			showWarning("User does not exist", "Login error");
		} else if(!user.getPassword().equals(password)) {
			showWarning("Wrong password", "Login error");
		} else {
			//TODO 
		}
	}

	private void performRegister(String username, String password) {
		if(!checkEntered(username, password)) {
			return;
		}
	}

	private void showWarning(String message, String title) {
		JOptionPane.showConfirmDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
	}
}
