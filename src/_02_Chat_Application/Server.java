package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Server implements ActionListener,Runnable{
	JFrame frame=new JFrame();
	JPanel panel=new JPanel();
	JTextField input=new JTextField();
	JTextField output=new JTextField();
	JButton button=new JButton();
	ServerSocket s;
	Socket socket;
	ObjectInputStream d;
	ObjectOutputStream d2;
	public void run() {
		try {
			s=new ServerSocket(8078);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setVisible(true);
		frame.setTitle("Server");
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
		System.out.println("Waiting for connection...");
		ServerSocket serverSocket=s;
		try {
			socket=serverSocket.accept();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			d=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			d2=new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Client has connected.");
		while(b) {
			try {
				
				
				try {
					output.setText((String) d.readObject());
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
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		try {
			d2.writeObject(input.getText());
			input.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
