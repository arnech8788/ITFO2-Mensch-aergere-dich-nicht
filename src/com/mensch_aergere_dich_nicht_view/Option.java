package com.mensch_aergere_dich_nicht_view;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Option extends JFrame{
	
	//Option view controls
	JCheckBox closeGameWhenPlayerWins;
	JCheckBox jumpInHouse;
	JCheckBox strikeForPull;
	JCheckBox sixFigureOut;
	
	public Option(){
		super("Option");
		
		//Initialize checkboxes
		closeGameWhenPlayerWins = new JCheckBox("Spiel beendet wenn Spieler gewonnen");
		jumpInHouse = new JCheckBox("Im Haus überspringen");
		strikeForPull = new JCheckBox("Schlagen geht vor ziehen");
		sixFigureOut = new JCheckBox("Bei 6 immer raus");
		
		this.setVisible(true);
		this.setResizable(false);
	}
}
