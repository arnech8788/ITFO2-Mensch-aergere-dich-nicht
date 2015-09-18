package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainGui extends JFrame implements ActionListener, Observer {

	JButton btnOptions;
	JLabel lblWelcome;
	
	JCheckBox cbPlayerOneIsEnabled; 
	JCheckBox cbPlayerTwoIsEnabled; 
	JCheckBox cbPlayerThreeIsEnabled; 
	JCheckBox cbPlayerFourIsEnabled; 
	
	JLabel lblPlayerOneNameInfo;
	JLabel lblPlayerTwoNameInfo;
	JLabel lblPlayerThreeNameInfo;
	JLabel lblPlayerFourNameInfo;
	
	JTextField txtPlayerOneName;
	JTextField txtPlayerTwoName;
	JTextField txtPlayerThreeName;
	JTextField txtPlayerFourName;
	
	JCheckBox cbPlayerOneIsComputer; 
	JCheckBox cbPlayerTwoIsComputer; 
	JCheckBox cbPlayerThreeIsComputer; 
	JCheckBox cbPlayerFourIsComputer; 
	
	
	JButton btnStart;
	JButton btnClose;
	JPanel ctrlPanel;
	
	private Options options;
	private Gamehandler handler;

	public MainGui(){
		super("Mensch ärgere dich nicht");
		ctrlPanel = new JPanel();

		btnOptions = new JButton("Optionen");
		btnOptions.addActionListener(this);
		ctrlPanel.add(btnOptions);
		
		lblWelcome = new JLabel("Herzlich Willkommen!");
		ctrlPanel.add(lblWelcome);
		
		
		// Player One
		cbPlayerOneIsEnabled = new JCheckBox("aktivieren");
		cbPlayerOneIsEnabled.addActionListener(this);
		ctrlPanel.add(cbPlayerOneIsEnabled);
		lblPlayerOneNameInfo = new JLabel("Spieler Eins:");
		ctrlPanel.add(lblPlayerOneNameInfo);
		txtPlayerOneName = new JTextField("");
		ctrlPanel.add(txtPlayerOneName);
		cbPlayerOneIsComputer = new JCheckBox("KI");
		ctrlPanel.add(cbPlayerOneIsComputer);
		
		// Player Two
		cbPlayerTwoIsEnabled = new JCheckBox("aktivieren");
		cbPlayerTwoIsEnabled.addActionListener(this);
		ctrlPanel.add(cbPlayerTwoIsEnabled);
		lblPlayerOneNameInfo = new JLabel("Spieler Zwei:");
		ctrlPanel.add(lblPlayerTwoNameInfo);
		txtPlayerTwoName = new JTextField("");
		ctrlPanel.add(txtPlayerTwoName);
		cbPlayerTwoIsComputer = new JCheckBox("KI");
		ctrlPanel.add(cbPlayerTwoIsComputer);

		// Player Three
		cbPlayerThreeIsEnabled = new JCheckBox("aktivieren");
		cbPlayerThreeIsEnabled.addActionListener(this);
		ctrlPanel.add(cbPlayerThreeIsEnabled);
		lblPlayerThreeNameInfo = new JLabel("Spieler Drei:");
		ctrlPanel.add(lblPlayerThreeNameInfo);
		txtPlayerThreeName = new JTextField("");
		ctrlPanel.add(txtPlayerThreeName);
		cbPlayerThreeIsComputer = new JCheckBox("KI");
		ctrlPanel.add(cbPlayerThreeIsComputer);

		// Player Four
		cbPlayerFourIsEnabled = new JCheckBox("aktivieren");
		cbPlayerFourIsEnabled.addActionListener(this);
		ctrlPanel.add(cbPlayerFourIsEnabled);
		lblPlayerFourNameInfo = new JLabel("Spieler Vier:");
		ctrlPanel.add(lblPlayerFourNameInfo);
		txtPlayerFourName = new JTextField("");
		ctrlPanel.add(txtPlayerFourName);
		cbPlayerFourIsComputer = new JCheckBox("KI");
		ctrlPanel.add(cbPlayerFourIsComputer);

		btnStart = new JButton("Spiel starten");
		btnStart.addActionListener(this);
		ctrlPanel.add(btnStart);
		
		btnClose = new JButton("Beenden");
		btnClose.addActionListener(this);
		this.add(ctrlPanel);

		ctrlPanel.add(btnClose);
		
		
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
			MainGui gb = new MainGui();
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
