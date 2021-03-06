package com.mensch_aergere_dich_nicht.view;


import com.mensch_aergere_dich_nicht.models.Options;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Option extends JFrame implements ActionListener 
{	
	//Option view controls
	JCheckBox cbCloseGameWhenPlayerWins;
	JCheckBox cbJumpInHouse;
	JCheckBox cbStrikeForPull;
	JCheckBox cbSixFigureOut;
	JButton btnOk;
	//JButton btnClose;
	
	JPanel cbPanel;
	Options options;
	
	public Option(Options options) {
		super("Option");
		
		//Initialize view controls
		cbCloseGameWhenPlayerWins = new JCheckBox("Spiel beendet wenn Spieler gewonnen");
		cbCloseGameWhenPlayerWins.addActionListener(this);
		
		cbJumpInHouse = new JCheckBox("Im Haus überspringen");
		cbJumpInHouse.addActionListener(this);
		
		cbStrikeForPull = new JCheckBox("Schlagen geht vor ziehen");
		cbStrikeForPull.addActionListener(this);
		
		cbSixFigureOut = new JCheckBox("Bei 6 immer raus");
		cbSixFigureOut.addActionListener(this);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		
		//btnClose = new JButton("Abbrechen");
		//btnClose.addActionListener(this);
		
		cbPanel = new JPanel();
		cbPanel.setLayout(new BoxLayout(cbPanel, BoxLayout.Y_AXIS));
		
		//Put checkboxes on panel control
		cbPanel.add(cbCloseGameWhenPlayerWins);
		cbPanel.add(cbJumpInHouse);
		cbPanel.add(cbStrikeForPull);
		cbPanel.add(cbSixFigureOut);
		
		cbPanel.add(btnOk);
		
		this.add(cbPanel);
				
		//Frame
		this.setSize(300, 170);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.options = options;
	}
	public Options getOptions()
	{
		return options;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isChecked = false;
		if(e.getSource() == cbCloseGameWhenPlayerWins)
		{
			isChecked = cbCloseGameWhenPlayerWins.isSelected();
			options.setCloseGameWhenPlayerWins(isChecked);
		}
		else if(e.getSource() == cbJumpInHouse)
		{
			this.options.setJumpInHouse(cbJumpInHouse.isSelected());
		}
		else if(e.getSource() == cbStrikeForPull)
		{
			this.options.setStrikeForPull(cbStrikeForPull.isSelected());
		}
		else if(e.getSource() == cbSixFigureOut)
		{
			this.options.setSixFigureOut(cbSixFigureOut.isSelected());
		}
		else if(e.getSource() == btnOk)
		{
			this.dispose();
		}
	}	
}
