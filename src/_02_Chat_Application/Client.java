package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.swing.*;

public class Client implements ActionListener,Runnable {
JFrame frame=new JFrame();
JPanel panel=new JPanel();
JTextField input=new JTextField();
JTextField output=new JTextField();
JButton button=new JButton();
Socket s;
ObjectOutputStream d;
ObjectInputStream d2;
public void run() {
	try {
		String ip=InetAddress.getLocalHost().getHostAddress();
		int port=8078;
		s=new Socket(ip,port);
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	frame.setVisible(true);
	frame.setTitle("Client");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel.add(input);
	panel.add(button);
	panel.add(output);
	frame.add(panel);
	input.setEditable(true);
	input.setPreferredSize(new Dimension(100,20));
	output.setEditable(false);
	output.setPreferredSize(new Dimension(100,20));
	button.setPreferredSize(new Dimension(100,20));
	button.setText("Send");
	button.addActionListener(this);
	frame.pack();
	boolean b=true;
	try {
		d=new ObjectOutputStream(s.getOutputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		d2=new ObjectInputStream(s.getInputStream());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	while(b) {
		try {
			
			try {
				output.setText((String) d2.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(SocketTimeoutException e) {
			System.err.println("Connection timed out.");
			b=false;
		}catch(IOException e) {
			System.err.println("ERROR.");
			b=false;
			e.printStackTrace();
		}
	}
}
@Override
public void actionPerformed(ActionEvent arg0) {
	
	try {
		
		d.writeObject(input.getText());
		d.flush();
		input.setText("");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.err.println("ERROR");
		e.printStackTrace();
	}
	
}
}
