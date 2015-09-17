package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gameboard extends JFrame implements ActionListener, Observer {

	JButton btnOptions;
	JButton btnStart;
	JButton btnClose;
	JPanel ctrlPanel;
	
	private Options options;
	private Gamehandler handler;

	public Gameboard(){
		super("Mensch ärgere dich nicht");

		btnOptions = new JButton("Optionen");
		btnOptions.addActionListener(this);

		btnStart = new JButton("Spiel starten");
		btnStart.addActionListener(this);

		btnClose = new JButton("Beenden");
		btnClose.addActionListener(this);

		ctrlPanel = new JPanel();

		ctrlPanel.add(btnOptions);
		ctrlPanel.add(btnStart);
		ctrlPanel.add(btnClose);
		
		this.add(ctrlPanel);

		//Frame
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		this.options = new Options();
		//String[] sPlayerName = {"Gernhart Reinholzen","Lassmiranda den si Villia","Timo Beil","Anne Theke"};//Playernames

		this.handler = new Gamehandler(this, 
				new String[] {"Gernhart Reinholzen",
							  "Lassmiranda den si Villia",
							  "Timo Beil",
							  "Anne Theke"});
	}
	
	
	public Options getOptions()
	{
		return this.options;
	}
	
	public static void main(String args[])
	{
			Gameboard gb = new Gameboard();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnOptions)
		{
			Option options = new Option(this.options);

		}
		else if(arg0.getSource() == btnStart)
		{
			handler.startGame();

		}
		else if(arg0.getSource() == btnClose)
		{
			this.dispose();

		}
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		// hier die Gui updaten
		
	}
	
	
}
