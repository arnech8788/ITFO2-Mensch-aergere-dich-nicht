package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mensch_aergere_dich_nicht.models.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainGui extends JFrame implements ActionListener{
	
	JCheckBox cbPlayerOneIsEnabled; 
	JCheckBox cbPlayerTwoIsEnabled; 
	JCheckBox cbPlayerThreeIsEnabled; 
	JCheckBox cbPlayerFourIsEnabled; 
	JCheckBox cbDebug; 
	
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
	JButton btnNetwork;
	
	JLabel lblColorPlayerOne;
	JLabel lblColorPlayerTwo;
	JLabel lblColorPlayerThree;
	JLabel lblColorPlayerFour;
	
	private JCheckBox [] cbBoxes = new JCheckBox[4];
	private JTextField [] txtPlayerNames = new JTextField[4];
	//private HashMap<String, Boolean> players = new HashMap();
	private Options options;
	private Option optionFrame;
	private Gamehandler handler;

	public MainGui(){
		super("Mensch �rgere dich nicht");
		
		this.options = new Options();
		optionFrame = new Option(options);
		optionFrame.setVisible(false);
		this.setLayout(null);

		// Debug
		cbDebug = new JCheckBox("Debug-Modus");
		cbDebug.setSize(110,30);
		cbDebug.setLocation(230,2);
		cbDebug.addActionListener(this);
		this.add(cbDebug);
		

		
//------------------ Player controls -------------------------
		// Player One
		lblPlayerOneNameInfo = new JLabel("Spieler Eins:");
		lblPlayerOneNameInfo.setLocation(10,20);
		lblPlayerOneNameInfo.setSize(80, 30);
		cbPlayerOneIsEnabled = new JCheckBox("aktivieren");
		cbPlayerOneIsEnabled.setLocation(82, 20);
		cbPlayerOneIsEnabled.setSize(90,30);
		cbPlayerOneIsEnabled.addActionListener(this);
		cbPlayerOneIsComputer = new JCheckBox("KI");
		cbPlayerOneIsComputer.setLocation(170, 20);
		cbPlayerOneIsComputer.setSize(60,30);
		txtPlayerOneName = new JTextField();
		txtPlayerOneName.setLocation(85, 50);
		txtPlayerOneName.setSize(220,20);
		lblColorPlayerOne = new JLabel("");
		lblColorPlayerOne.setOpaque(true);
		lblColorPlayerOne.setBackground(Gamehandler.colors[0]);
		lblColorPlayerOne.setSize(15,15);
		lblColorPlayerOne.setLocation(52,52);
		this.add(lblPlayerOneNameInfo);
		this.add(cbPlayerOneIsEnabled);
		this.add(cbPlayerOneIsComputer);
		this.add(txtPlayerOneName);
		this.add(lblColorPlayerOne);
		
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
		cbPlayerTwoIsComputer.setSize(60,30);
		txtPlayerTwoName = new JTextField();
		txtPlayerTwoName.setLocation(85, 110);
		txtPlayerTwoName.setSize(220,20);
		lblColorPlayerTwo = new JLabel("");
		lblColorPlayerTwo.setSize(15,15);
		lblColorPlayerTwo.setLocation(52,110);
		lblColorPlayerTwo.setOpaque(true);
		lblColorPlayerTwo.setBackground(Gamehandler.colors[1]);
		this.add(lblPlayerTwoNameInfo);
		this.add(cbPlayerTwoIsEnabled);
		this.add(cbPlayerTwoIsComputer);
		this.add(txtPlayerTwoName);
		this.add(lblColorPlayerTwo);

		// Player Three
		lblPlayerThreeNameInfo = new JLabel("Spieler Drei:");
		lblPlayerThreeNameInfo.setLocation(10, 140);
		lblPlayerThreeNameInfo.setSize(80,30);
		cbPlayerThreeIsEnabled = new JCheckBox("aktivieren");
		cbPlayerThreeIsEnabled.setSize(90,30);
		cbPlayerThreeIsEnabled.setLocation(82,140);
		cbPlayerThreeIsEnabled.addActionListener(this);
		cbPlayerThreeIsComputer = new JCheckBox("KI");
		cbPlayerThreeIsComputer.setLocation(170, 140);
		cbPlayerThreeIsComputer.setSize(60,30);
		txtPlayerThreeName = new JTextField();
		txtPlayerThreeName.setSize(220,20);
		txtPlayerThreeName.setLocation(85,170);
		lblColorPlayerThree = new JLabel("");
		lblColorPlayerThree.setSize(15,15);
		lblColorPlayerThree.setLocation(52,170);
		lblColorPlayerThree.setOpaque(true);
		lblColorPlayerThree.setBackground(Gamehandler.colors[2]);
		this.add(lblPlayerThreeNameInfo);
		this.add(cbPlayerThreeIsEnabled);
		this.add(cbPlayerThreeIsComputer);
		this.add(txtPlayerThreeName);
		this.add(lblColorPlayerThree);
		
		lblPlayerFourNameInfo = new JLabel("Spieler Vier:");
		lblPlayerFourNameInfo.setLocation(10,200);
		lblPlayerFourNameInfo.setSize(80,30);
		cbPlayerFourIsEnabled = new JCheckBox("aktivieren");
		cbPlayerFourIsEnabled.setSize(90,30);
		cbPlayerFourIsEnabled.setLocation(82,200);
		cbPlayerFourIsEnabled.addActionListener(this);
		cbPlayerFourIsComputer = new JCheckBox("KI");
		cbPlayerFourIsComputer.setLocation(170,200);
		cbPlayerFourIsComputer.setSize(60, 30);
		txtPlayerFourName = new JTextField();
		txtPlayerFourName.setSize(220,20);
		txtPlayerFourName.setLocation(85,230);
		lblColorPlayerFour = new JLabel("");
		lblColorPlayerFour.setSize(15,15);
		lblColorPlayerFour.setLocation(52, 230);
		lblColorPlayerFour.setOpaque(true);
		lblColorPlayerFour.setBackground(Gamehandler.colors[3]);
		this.add(lblPlayerFourNameInfo);
		this.add(cbPlayerFourIsEnabled);
		this.add(cbPlayerFourIsComputer);
		this.add(txtPlayerFourName);
		this.add(lblColorPlayerFour);
		
		//Button
		btnStart = new JButton("Spiel starten");
		btnStart.addActionListener(this);
		btnStart.setSize(110,25);
		btnStart.setLocation(115,280);
		this.add(btnStart);
		
		btnOptions = new JButton("Optionen");
		btnOptions.addActionListener(this);
		btnOptions.setSize(100,25);
		btnOptions.setLocation(10,280);
		this.add(btnOptions);
		
		btnNetwork = new JButton("Netzwerk");
		btnNetwork.addActionListener(this);
		btnNetwork.setSize(100, 25);
		btnNetwork.setLocation(230, 280);
		this.add(btnNetwork);
		
		//Set Textfields and Computer checkbox disabled
		txtPlayerOneName.setEnabled(false);
		txtPlayerTwoName.setEnabled(false);
		txtPlayerThreeName.setEnabled(false);
		txtPlayerFourName.setEnabled(false);
		
		cbPlayerOneIsComputer.setEnabled(false);
		cbPlayerTwoIsComputer.setEnabled(false);
		cbPlayerThreeIsComputer.setEnabled(false);
		cbPlayerFourIsComputer.setEnabled(false);
		
		
		/*
		 * Put player checkbox controls in cbBoxes
		 * for checking if min 2 players are selected for starting game
		 */
		cbBoxes[0] = cbPlayerOneIsEnabled;
		cbBoxes[1] = cbPlayerTwoIsEnabled;
		cbBoxes[2] = cbPlayerThreeIsEnabled;
		cbBoxes[3] = cbPlayerFourIsEnabled;
		
		txtPlayerNames[0] = txtPlayerOneName;
		txtPlayerNames[1] = txtPlayerTwoName;
		txtPlayerNames[2] = txtPlayerThreeName;
		txtPlayerNames[3] = txtPlayerFourName;
//-----------------------------------------------------------			
		//Frame
		this.setSize(350,350);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public static void main(String args[])
	{
			new MainGui();
	}
	
	//Check if min two players are selected
	private boolean isGameRunnable(){
		int countOfSelectedPlayers = 0; 
		
		for(int i = 0; i<4; i++){
			if(cbBoxes[i].isSelected() && !txtPlayerNames[i].getText().equals(""))
			{
				countOfSelectedPlayers++;
			}
		}
		if(countOfSelectedPlayers<2){
			return false;
		}
		return true;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Button events
		if(arg0.getSource() == btnOptions)
		{
			optionFrame.show();
		}
		else if(arg0.getSource() == btnStart)
		{
			if(isGameRunnable()){
				
				if(handler != null){
					handler = null;
				}
				
				handler = new Gamehandler(this, cbDebug.isSelected());
				handler.setAllFiguresBack();
				handler.getPlayers().clear();
				
				// TODO: testen ob die Spieloptionen bei �nderungen auch �bernommen werden...
				
				/*
				HashMap<String, Boolean> players = new HashMap<String, Boolean>();

				players.put(txtPlayerOneName.getText(), cbPlayerOneIsComputer.isSelected());
				players.put(txtPlayerTwoName.getText(), cbPlayerTwoIsComputer.isSelected());
				players.put(txtPlayerThreeName.getText(), cbPlayerThreeIsComputer.isSelected());
				players.put(txtPlayerFourName.getText(), cbPlayerFourIsComputer.isSelected());
				*/
				
				handler.addPlayer(txtPlayerOneName.getText(), cbPlayerOneIsComputer.isSelected());
				handler.addPlayer(txtPlayerTwoName.getText(), cbPlayerTwoIsComputer.isSelected());
				handler.addPlayer(txtPlayerThreeName.getText(), cbPlayerThreeIsComputer.isSelected());
				handler.addPlayer(txtPlayerFourName.getText(), cbPlayerFourIsComputer.isSelected());
				this.hide();
				handler.startGame(this.options);
			}
			else{
				JOptionPane.showMessageDialog(new JFrame(),"Es muss mindestens 2 g�ltige Spieler geben!","Fehler Spielstart",JOptionPane.ERROR_MESSAGE);
			}
		}		
		//CheckBox events
		else if(arg0.getSource() == cbPlayerOneIsEnabled){	
			if(cbPlayerOneIsEnabled.isSelected()== true){
				txtPlayerOneName.setEnabled(true);
				cbPlayerOneIsComputer.setEnabled(true);
			}
			else{
				txtPlayerOneName.setText("");
				txtPlayerOneName.setEnabled(false);
				cbPlayerOneIsComputer.setEnabled(false);
			}
		}	
		else if(arg0.getSource() == cbPlayerTwoIsEnabled){
			if(cbPlayerTwoIsEnabled.isSelected()== true){
				txtPlayerTwoName.setEnabled(true);
				cbPlayerTwoIsComputer.setEnabled(true);
			}
			else{
				txtPlayerTwoName.setText("");
				txtPlayerTwoName.setEnabled(false);
				cbPlayerTwoIsComputer.setEnabled(false);
			}
		}
		else if(arg0.getSource() == cbPlayerThreeIsEnabled){
			if(cbPlayerThreeIsEnabled.isSelected()== true){
				txtPlayerThreeName.setEnabled(true);
				cbPlayerThreeIsComputer.setEnabled(true);
			}
			else{
				txtPlayerThreeName.setText("");
				txtPlayerThreeName.setEnabled(false);
				cbPlayerThreeIsComputer.setEnabled(false);
			}
		}
		else if(arg0.getSource() == cbPlayerFourIsEnabled){
			if(cbPlayerFourIsEnabled.isSelected()== true){
				txtPlayerFourName.setEnabled(true);
				cbPlayerFourIsComputer.setEnabled(true);
			}
			else{
				txtPlayerFourName.setText("");
				txtPlayerFourName.setEnabled(false);
				cbPlayerFourIsComputer.setEnabled(false);
			}
		}
	}
}
