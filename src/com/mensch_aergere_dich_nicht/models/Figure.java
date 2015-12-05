package com.mensch_aergere_dich_nicht.models;

import java.awt.Color;

/*
 * Autor: OK / TS
 * Datum: 11.09.2015
 * Beschreibung: Klasse Figure
 */
public class Figure implements Comparable<Figure>
{

	public static final int startPosition = -1;
	public static final int startField = 0;
	//public static final int firstHousePosition = 40;
	//public static final int lastHousePosition = 43;
	
	private int number;
	

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
	public Figure(Color figureColor,
				  int number)
	{
		this.iSteps = startPosition;
		this.number = number;
		this.figureColor = figureColor; 
	}
	
	
	public int getNumber() {
		return number;
	}

	/**
	public void setFigureColor(Color c){
		this.figureColor = c;
	}**/
	
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
	
	public boolean isOnGameboard(boolean includeHouse)
	{
		if(getSteps() >= Gamehandler.fieldCount && !includeHouse)
		{
			return false;
		}
		
		if(getSteps() == Figure.startPosition)
		{
			return false;
		}
		
		return true;
	}

	public void set2StartPosition()
	{
		this.setSteps(Figure.startPosition);
	}

	@Override
	public int compareTo(Figure arg0) {
		return this.getSteps() - arg0.getSteps();
	}
	
	
	public String toString()
	{
		String temp = "";
		temp += "Nr.: " + String.valueOf(this.getNumber());
		temp += "	";
		temp += "Farbe: " + this.getFigureColor().toString();
		temp += "	";
		temp += "Schritte: " + String.valueOf(this.getSteps());
		return temp;
	}
}
