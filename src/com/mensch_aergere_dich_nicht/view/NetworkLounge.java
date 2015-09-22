package com.mensch_aergere_dich_nicht.view;
import javax.swing.JButton;
import javax.swing.JFrame;

public class NetworkLounge {
	/**
	 * Wozu die extra Variable?
	 * schaue mal in die anderen Gui-Klassen
	 */
	JFrame NetworkLounge;
	JButton btnShutdown;
	JButton btnHostGame;
	JButton btnJoinGame;
	
	public static void main(String args[])
	{
		NetworkLounge a = new NetworkLounge();
		
	}
	
	public NetworkLounge()
	{
		/*
		 *	Build up GUI 
		 */
		NetworkLounge = new JFrame("Netzwerk"); 
		NetworkLounge.setSize(700,300);
		NetworkLounge.setLocation(300, 300);;
		NetworkLounge.setVisible(true);
		
		btnShutdown = new JButton("Beenden");
		btnHostGame = new JButton("Spiel erstellen");
		btnJoinGame = new JButton("Spiel beitreten");
		
		NetworkLounge.add(btnShutdown);
		NetworkLounge.add(btnHostGame);
		NetworkLounge.add(btnJoinGame);
		
	    //meinFrame.add(new JLabel("Beispiel JLabel"));
	}
	
	
	
    
    
	

}
