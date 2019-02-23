import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Home extends JPanel {
	private JTextField nameField;
	private JPasswordField passwordField;

	JPanel contentPane;
	public Home() {
		setForeground(new Color(0, 0, 0));
		
		setSize(450,600);
		setLayout(null);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.PLAIN, 20));
		nameField.setBounds(140, 217, 262, 36);
		add(nameField);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(204, 0, 0));
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(140, 297, 255, 16);
		add(label);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String password = passwordField.getText();
				Client client = MainClass.getClient(name,password);
				
				if(client!=null) {
					setVisible(false);
					label.setText("");
					ClientHome clientHome = new ClientHome(client);
					MainClass.frame.getContentPane().add(clientHome);
					
				}
				else label.setText("Name or Password is incorrect");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 102, 204));
		btnNewButton.setBounds(140, 314, 114, 36);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(20, 220, 97, 26);
		add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblPassword.setBounds(20, 264, 97, 36);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 30));
		passwordField.setBounds(140, 265, 262, 36);
		add(passwordField);
		
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel=new JPanel(new BorderLayout(5,5));
				JPanel panel2=new JPanel(new GridLayout(0, 1,2,2));
				panel2.add(new JLabel("Name",SwingConstants.RIGHT));
				panel2.add(new JLabel("Password",SwingConstants.RIGHT));
				panel.add(panel2, BorderLayout.WEST);
				JPanel panel3=new JPanel(new GridLayout(0, 1,2,2));
				JTextField tf=new JTextField(15);
				JPasswordField pas=new JPasswordField(15);
				panel3.add(tf);
				panel3.add(pas);
				panel.add(panel3,BorderLayout.CENTER);
				JOptionPane.showMessageDialog(contentPane,panel,"Sign Up",JOptionPane.QUESTION_MESSAGE);
				String name=tf.getText();
				String password=pas.getText();
				
				if(!name.equals("") || !password.equals("")) {
					if(MainClass.check(name)) {
						Random rand = new Random();
						int port=rand.nextInt(2000)+3000;
						Thread thread = new Thread(new Server(port));
						thread.start();
						Client client = new Client(name,password,port);
						MainClass.addClient(client);
						
						try {
							FileWriter fw = new FileWriter("client.txt",true);
							
							PrintWriter pw = new PrintWriter(fw);
							pw.println(name+"#"+password+"#"+"nogroup");
							fw.close();
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						
						
						JOptionPane.showMessageDialog(null,"Account Added");
					}
					else JOptionPane.showMessageDialog(null,"This name already exist");
				}
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setFont(new Font("Arial", Font.BOLD, 14));
		btnSignup.setBackground(new Color(102, 204, 0));
		btnSignup.setBounds(106, 506, 244, 36);
		add(btnSignup);
		
		JLabel lblNewLabel_2 = new JLabel("Chat App");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setForeground(new Color(0, 102, 204));
		lblNewLabel_2.setFont(new Font("Harlow Solid Italic", Font.BOLD, 38));
		lblNewLabel_2.setBounds(152, 25, 197, 36);
		add(lblNewLabel_2);

	}
}
