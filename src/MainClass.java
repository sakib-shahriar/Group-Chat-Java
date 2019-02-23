import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class MainClass {
	public static JFrame frame;
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static ArrayList<Group> groups = new ArrayList<Group>();
	static Home home;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass window = new MainClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainClass() {
		initialize();
	}
	
	private void initialize() {
		
		groupInit();
		clientInit();
		
		frame = new JFrame();
		frame.setBounds(800, 150, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		home = new Home();
		frame.getContentPane().add(home);
		home.setVisible(true);
	
		
	}

	private void clientInit()
	{
		try {
			FileReader reader = new FileReader("client.txt");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			while(str!=null) {
				String[] string = new String[3];
				int j=0;
				for(String s:str.split("#"))
					string[j++]=s;
				
				Random rand = new Random();
				int port=rand.nextInt(2000)+3000;
				Thread thread = new Thread(new Server(port));
				thread.start();
				
				if(string[1]!=null) {
					clients.add(new Client(string[0],string[1], getGroup(string[2]), port));
					
				}
				str = br.readLine();
				
			}
			reader.close();
		} catch (Exception e) {
			
		}
	}

	private void groupInit() {
		try {
			for (int i = 1; i <= 5; i++) {
				FileReader reader = new FileReader("group" + i + ".txt");
				BufferedReader br = new BufferedReader(reader);
				Group group = new Group("group" + i, "");
				String str = br.readLine();
				while (str != null) {
					if (!str.equals(""))
						group.msg = group.msg + "\n\n" + str;
					str = br.readLine();
				}
				reader.close();
				groups.add(group);
			}
		} catch (Exception e) {

		}
	}
	
	
	public static Client getClient(String name, String password) {
		for(int i=0;i<clients.size();i++) {
			if(clients.get(i).name.equals(name) && clients.get(i).password.equals(password)) {
				return clients.get(i);
			}
		}
		return null;
	}


	public static Group getGroup(String groupId) {
		for(int i=0;i<groups.size();i++) {
			if(groups.get(i).groupId.equals(groupId)) {
				return groups.get(i);
			}
		}
		return null;
	}
	
	public static void updateClient(String name,String group) {
		try {
			FileReader reader = new FileReader("client.txt");
			BufferedReader br = new BufferedReader(reader);
			StringBuilder fileContent = new StringBuilder();
			
			String str = br.readLine();
			
			while(str!=null) {
				String[] string = new String[3];
				int j=0;
				for(String s:str.split("#"))
					string[j++]=s;
				
				if(string[0].equals(name)) {
					String updatedClient = string[0]+"#"+string[1]+"#"+group;
					fileContent.append(updatedClient);
					fileContent.append("@");
				}
				else {
                    fileContent.append(str);
                    fileContent.append("@");
                }
				str = br.readLine();
			}
			str=fileContent.toString();
			ArrayList<String> list = new ArrayList<String>();
			for(String s:str.split("@"))
				list.add(s);
			FileWriter writer = new FileWriter("client.txt");
			PrintWriter pw = new PrintWriter(writer);
			for(int i=0;i<list.size();i++) {
				pw.println(list.get(i));
			}
			reader.close();
			writer.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void updateGroup(String groupId,String msg)
	{
		try {
			FileWriter fw = new FileWriter(groupId+".txt",true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(msg);
			fw.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean check(String name)
	{
		for(int i=0;i<clients.size();i++) {
			if(clients.get(i).name.equals(name))
				return false;
		}
		return true;
	}
	
	public static void addClient(Client client)
	{
		clients.add(client);
	}
}
