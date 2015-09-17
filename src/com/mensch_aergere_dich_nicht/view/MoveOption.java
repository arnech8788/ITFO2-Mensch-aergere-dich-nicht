package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MoveOption extends JFrame {
	
	JPanel ctrlPanel;

	public MoveOption() {
		
		ctrlPanel = new JPanel();
		this.add(ctrlPanel);

		//Frame
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
}
