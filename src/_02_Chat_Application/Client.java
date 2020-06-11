package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Client implements ActionListener,Runnable {
JFrame frame=new JFrame();
JPanel panel=new JPanel();
JTextField input=new JTextField();
JTextField output=new JTextField();
JButton button=new JButton();
public void run() {
	frame.setVisible(true);
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
}
@Override
public void actionPerformed(ActionEvent arg0) {
	input.setText("");
	
}
}
