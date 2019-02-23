import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ClientHome extends JPanel {

	
	public ClientHome(Client client) {
		
		setSize(450,600);
		setLayout(null);
		
		JButton btnNewButton = new JButton("My Group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(client.group!=null) {
					setVisible(false);
					GroupHoame groupHoame = new GroupHoame(client.group, client);
					MainClass.frame.getContentPane().add(groupHoame);
				}
				else JOptionPane.showMessageDialog(null,"You are not in any group");
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 102, 204));
		btnNewButton.setBounds(79, 102, 280, 47);
		add(btnNewButton);
		
		JButton btnJoinGroup = new JButton("Join group");
		btnJoinGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String groupId=JOptionPane.showInputDialog("Enter group id");
				try {
					client.dout.writeUTF(1+"#"+client.name+"#"+groupId);
				} catch (IOException e) {
					
				}
			}
		});
		btnJoinGroup.setForeground(Color.WHITE);
		btnJoinGroup.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnJoinGroup.setBackground(new Color(0, 102, 204));
		btnJoinGroup.setBounds(79, 207, 280, 47);
		add(btnJoinGroup);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Home home = new Home();
				MainClass.frame.getContentPane().add(home);
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnLogout.setBackground(new Color(0, 102, 204));
		btnLogout.setBounds(79, 309, 280, 47);
		add(btnLogout);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 25));
		label.setBounds(160, 30, 290, 30);
		label.setText(client.name);
		add(label);

	}

}
