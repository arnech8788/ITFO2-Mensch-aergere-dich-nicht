package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainGui extends JFrame implements ActionListener{
	
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
	JButton btnOptions;
	
	private Options options;
	private Option optionFrame;
	private Gamehandler handler;

	public MainGui(){
		super("Mensch ärgere dich nicht");
		
		this.options = new Options();
		optionFrame = new Option(options);
		optionFrame.hide();
		this.setLayout(null);

//------------------ Player controls -------------------------
		// Player One
		lblPlayerOneNameInfo = new JLabel("Spieler Eins:");
		lblPlayerOneNameInfo.setLocation(10,20);
		lblPlayerOneNameInfo.setSize(80, 30);
		cbPlayerOneIsEnabled = new JCheckBox("aktivieren");
		cbPlayerOneIsEnabled.setLocation(80, 20);
		cbPlayerOneIsEnabled.setSize(90,30);
		cbPlayerOneIsEnabled.addActionListener(this);
		cbPlayerOneIsComputer = new JCheckBox("KI");
		cbPlayerOneIsComputer.setLocation(170, 20);
		cbPlayerOneIsComputer.setSize(120,30);
		txtPlayerOneName = new JTextField();
		txtPlayerOneName.setLocation(85, 50);
		txtPlayerOneName.setSize(220,20);
		this.add(lblPlayerOneNameInfo);
		this.add(cbPlayerOneIsEnabled);
		this.add(cbPlayerOneIsComputer);
		this.add(txtPlayerOneName);
		
		// Player Two
		lblPlayerTwoNameInfo = new JLabel("Spieler Zwei:");
		lblPlayerTwoNameInfo.setLocation(10,80);
		lblPlayerTwoNameInfo.setSize(80,30);
		cbPlayerTwoIsEnabled = new JCheckBox("aktivieren");
		cbPlayerTwoIsEnabled.setLocation(82,80);
		cbPlayerTwoIsEnabled.setSize(90,30);
		cbPlayerTwoIsEnabled.addActionListener(this);
		cbPlayerTwoIsComputer = new JCheckBox("KI");
		cbPlayerTwoIsComputer.setLocation(170, 80);
		cbPlayerTwoIsComputer.setSize(120,30);
		txtPlayerTwoName = new JTextField();
		txtPlayerTwoName.setLocation(85, 110);
		txtPlayerTwoName.setSize(220,20);
		this.add(lblPlayerTwoNameInfo);
		this.add(cbPlayerTwoIsEnabled);
		this.add(cbPlayerTwoIsComputer);
		this.add(txtPlayerTwoName);

		// Player Three
		lblPlayerThreeNameInfo = new JLabel("Spieler Drei:");
		lblPlayerThreeNameInfo.setLocation(10, 140);
		lblPlayerThreeNameInfo.setSize(80,30);
		cbPlayerThreeIsEnabled = new JCheckBox("aktivieren");
		cbPlayerThreeIsEnabled.setSize(90,30);
		cbPlayerThreeIsEnabled.setLocation(82,140);
		cbPlayerThreeIsComputer = new JCheckBox("KI");
		cbPlayerThreeIsComputer.setLocation(170, 140);
		cbPlayerThreeIsComputer.setSize(120,30);
		txtPlayerThreeName = new JTextField();
		txtPlayerThreeName.setSize(220,20);
		txtPlayerThreeName.setLocation(85,170);
		this.add(lblPlayerThreeNameInfo);
		this.add(cbPlayerThreeIsEnabled);
		this.add(cbPlayerThreeIsComputer);
		this.add(txtPlayerThreeName);
		
//		// Player Four
//		cbPlayerFourIsEnabled = new JCheckBox("aktivieren");
//		cbPlayerFourIsEnabled.addActionListener(this);
//		ctrlPanel.add(cbPlayerFourIsEnabled);
//		lblPlayerFourNameInfo = new JLabel("Spieler Vier:");
//		ctrlPanel.add(lblPlayerFourNameInfo);
//		txtPlayerFourName = new JTextField("");
//		ctrlPanel.add(txtPlayerFourName);
//		cbPlayerFourIsComputer = new JCheckBox("KI");
//		ctrlPanel.add(cbPlayerFourIsComputer);

//-----------------------------------------------------------
		
		//Buttons
		btnStart = new JButton("Spiel starten");
		btnStart.addActionListener(this);
//		ctrlPanel.add(btnStart);
		
		btnOptions = new JButton("Optionen");
		btnOptions.addActionListener(this);
//		ctrlPanel.add(btnOptions);
		
		btnClose = new JButton("Beenden");
		btnClose.addActionListener(this);
//		ctrlPanel.add(btnClose);
		
		//Frame
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		this.add(ctrlPanel);		
	}
	
	public static void main(String args[])
	{
			MainGui gb = new MainGui();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnOptions)
		{
			optionFrame.show();
		}
		else if(arg0.getSource() == btnStart)
		{
			handler = new Gamehandler(options,new String[] {txtPlayerOneName.getText(),
					txtPlayerTwoName.getText(),
					txtPlayerThreeName.getText(),
					txtPlayerOneName.getText()});
//			handler.startGame();
		}
		else if(arg0.getSource() == btnClose)
		{
			System.exit(0);
		}		
	}
}
