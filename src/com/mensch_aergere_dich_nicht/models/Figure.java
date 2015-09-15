package com.mensch_aergere_dich_nicht.models;

import java.awt.Color;

/*
 * Autor: OK / TS
 * Datum: 11.09.2015
 * Beschreibung: Klasse Figure
 */
public class Figure {

	/*
	 * color of the figure
	 */
	private Color figureColor;
	
	/*
	 * -1 = Figur ist im Start
	 * 0 - X = Anzahl der Felder die die Figure zurückgelegt hat
	 */
	private int iSteps;

	/*
	 * creates a figure with a specific color
	 * sets iSteps to -1 --> home position
	 */
	public Figure(Color figureColor)
	{
		iSteps = -1;
		this.figureColor = figureColor; 
	}
	
	public void setFigureColor(Color c){
		this.figureColor = c;
	}
	
	public Color getFigureColor() {
		return figureColor;
	}
	
	public int getSteps()
	{
		return iSteps;
	}
	
	public void setSteps(int iSteps)
	{
		this.iSteps = iSteps;
	}

}
