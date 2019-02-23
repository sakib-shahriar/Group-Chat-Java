import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GroupHoame extends JPanel {
	private JTextField textField;
	public static JTextArea textArea;
	public GroupHoame(Group group,Client client) {
		
		setSize(450,600);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Leave  Group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.dout.writeUTF(2+"#"+client.name+"#"+client.group.groupId);
				} catch (IOException e1) {
					
				}
				setVisible(false);
				ClientHome clientHome = new ClientHome(client);
				MainClass.frame.getContentPane().add(clientHome);
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 102, 204));
		btnNewButton.setBounds(82, 51, 280, 47);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 426, 305);
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setText(group.msg);
		textArea.setFont(new Font("Arial", Font.BOLD, 20));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 428, 321, 47);
		add(scrollPane_1);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!textField.getText().equals(""))
						client.dout.writeUTF(3+"#"+client.name+"#"+textField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		scrollPane_1.setViewportView(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!textField.getText().equals(""))
						client.dout.writeUTF(3+"#"+client.name+"#"+textField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnSend.setBackground(new Color(0, 102, 204));
		btnSend.setBounds(335, 427, 115, 47);
		add(btnSend);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 18));
		label.setBounds(172, 9, 266, 30);
		label.setText(group.groupId);
		add(label);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ClientHome clientHome = new ClientHome(client);
				MainClass.frame.getContentPane().add(clientHome);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBack.setBackground(new Color(0, 102, 204));
		btnBack.setBounds(153, 506, 115, 47);
		add(btnBack);
	}
}
