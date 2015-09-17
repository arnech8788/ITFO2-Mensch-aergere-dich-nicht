package com.mensch_aergere_dich_nicht.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Option extends JFrame{
	
	//Option view controls
	JCheckBox cbCloseGameWhenPlayerWins;
	JCheckBox cbJumpInHouse;
	JCheckBox cbStrikeForPull;
	JCheckBox cbSixFigureOut;
	JButton btnOk;
	JButton btnClose;
	JPanel cbPanel;
	
	public Option(){
		super("Option");
		
		//Initialize view controls
		cbCloseGameWhenPlayerWins = new JCheckBox("Spiel beendet wenn Spieler gewonnen");
		cbJumpInHouse = new JCheckBox("Im Haus überspringen");
		cbStrikeForPull = new JCheckBox("Schlagen geht vor ziehen");
		cbSixFigureOut = new JCheckBox("Bei 6 immer raus");
		btnOk = new JButton("OK");
		btnClose = new JButton("Abbrechen");
		cbPanel = new JPanel();
		
		//Put checkboxes on panel control
		cbPanel.add(cbCloseGameWhenPlayerWins);
		cbPanel.add(cbJumpInHouse);
		cbPanel.add(cbStrikeForPull);
		cbPanel.add(cbSixFigureOut);
		
		this.add(cbPanel);
		
		
		//Frame
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}
}
