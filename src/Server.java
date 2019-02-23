import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Server implements Runnable{
	int port;
	public Server(int port) {
		this.port=port;
	}
	


	public void run() {
		try {
			ServerSocket ss = new ServerSocket(port);
			Socket s = ss.accept();
			
			DataInputStream din=new DataInputStream(s.getInputStream());  
			 
			
			
			while(true)
			{
				String str = din.readUTF();
				
				String[] string = new String[3];
				
				int i=0;
				
				for(String val: str.split("#")) {
					string[i++]=val;
				}
				
				int request = Integer.parseInt(string[0]);
				
				if(request==1) {
					
					for(i=0;i<MainClass.clients.size();i++) {
						if(MainClass.clients.get(i).name.equals(string[1])) {
							Client client = MainClass.clients.get(i);
							
							if(client.group==null) {
								Group group = MainClass.getGroup(string[2]);
								if(group!=null) {
									client.group=group;
									MainClass.updateClient(client.name, string[2]);
									JOptionPane.showMessageDialog(null,"You are added in "+string[2]);
								}
								else JOptionPane.showMessageDialog(null,"group not found");
							}
							else JOptionPane.showMessageDialog(null,"You are already in "+client.group.groupId);
							break;
						}
					}
				}
				
				else if(request==2) {
					for(i=0;i<MainClass.clients.size();i++) {
						if(MainClass.clients.get(i).name.equals(string[1])) {
							Client client = MainClass.clients.get(i);
							client.group=null;
							MainClass.updateClient(client.name,"nogroup");
							JOptionPane.showMessageDialog(null,"You are removed form "+string[2]);
							break;
						}
					}
				}
				
				else if(request==3) {
					for(i=0;i<MainClass.clients.size();i++) {
						if(MainClass.clients.get(i).name.equals(string[1])) {
							Client client = MainClass.clients.get(i);
							Group group = client.group;
							group.msg=group.msg+"\n\n"+client.name+": "+string[2];
							GroupHoame.textArea.setText(group.msg);
							MainClass.updateGroup(group.groupId,client.name+": "+string[2]);
							break;
							
						}
					}
				}
			}
			
		} catch (Exception e) {
			
		}
		
	}
}
