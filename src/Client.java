import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	
	String name,password;
	Group group;
	Socket s=null;
	DataOutputStream dout=null;
	int port;
	public Client(String name, String password,int port) {
		super();
		this.name = name;
		this.password = password;
		this.group=null;
		this.port=port;
		
		try {
			s=new Socket("localhost",port);  
			dout=new DataOutputStream(s.getOutputStream());  
		} catch (Exception e) {
			
		}
	}
	public Client(String name, String password, Group group, int port) {
		super();
		this.name = name;
		this.password = password;
		this.group = group;
		this.port = port;
		try {
			s=new Socket("localhost",port);  
			dout=new DataOutputStream(s.getOutputStream());  
		} catch (Exception e) {
			
		}
	}
	
	

}
