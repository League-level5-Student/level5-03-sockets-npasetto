package _02_Chat_Application;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
public static void main(String[] args) {
	new Thread(new Client()).start();
	new Thread(new Server()).start();
}
}
