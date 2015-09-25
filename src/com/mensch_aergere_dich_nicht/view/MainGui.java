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
//	JPanel ctrlPanel;
	
	private Options options;
	private Option optionFrame;
	private Gamehandler handler;

	public MainGui(){
		super("Mensch ärgere dich nicht");
		
		this.options = new Options();
		optionFrame = new Option(options);
		optionFrame.hide();
		
		//Setting Control Panel
//		ctrlPanel = new JPanel();
//		ctrlPanel.setLayout(new BoxLayout(ctrlPanel, BoxLayout.Y_AXIS));
	
		
		// Player One
		lblPlayerOneNameInfo = new JLabel("Spieler Eins:");
		lblPlayerOneNameInfo.setLocation(200,200);
		lblPlayerOneNameInfo.setSize(20, 5);
		this.add(lblPlayerOneNameInfo);
		cbPlayerOneIsEnabled = new JCheckBox("aktivieren");
		cbPlayerOneIsEnabled.addActionListener(this);
//		ctrlPanel.add(cbPlayerOneIsEnabled);
		
//		ctrlPanel.add(lblPlayerOneNameInfo);
		txtPlayerOneName = new JTextField(20);
//		ctrlPanel.add(txtPlayerOneName);
		cbPlayerOneIsComputer = new JCheckBox("KI");
//		ctrlPanel.add(cbPlayerOneIsComputer);
		
//		// Player Two
//		cbPlayerTwoIsEnabled = new JCheckBox("aktivieren");
//		cbPlayerTwoIsEnabled.addActionListener(this);
//		ctrlPanel.add(cbPlayerTwoIsEnabled);
//		lblPlayerTwoNameInfo = new JLabel("Spieler Zwei:");
//		ctrlPanel.add(lblPlayerTwoNameInfo);
//		txtPlayerTwoName = new JTextField("");
//		ctrlPanel.add(txtPlayerTwoName);
//		cbPlayerTwoIsComputer = new JCheckBox("KI");
//		ctrlPanel.add(cbPlayerTwoIsComputer);
//
//		// Player Three
//		cbPlayerThreeIsEnabled = new JCheckBox("aktivieren");
//		cbPlayerThreeIsEnabled.addActionListener(this);
//		ctrlPanel.add(cbPlayerThreeIsEnabled);
//		lblPlayerThreeNameInfo = new JLabel("Spieler Drei:");
//		ctrlPanel.add(lblPlayerThreeNameInfo);
//		txtPlayerThreeName = new JTextField("");
//		ctrlPanel.add(txtPlayerThreeName);
//		cbPlayerThreeIsComputer = new JCheckBox("KI");
//		ctrlPanel.add(cbPlayerThreeIsComputer);
//
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
		this.setLayout(null);
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
			handler.startGame();
		}
		else if(arg0.getSource() == btnClose)
		{
			System.exit(0);
		}		
	}
}
