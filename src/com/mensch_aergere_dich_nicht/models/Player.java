package com.mensch_aergere_dich_nicht.models;

import java.awt.*;
import java.util.*;

public class Player {
	
	private Color playerColor;
	private int offset;
	private boolean isComputer;
	private String playerName;
	private Map<Integer, Figure> figures;
	private Map<Integer, House> houses;
	
	/*
	 * Creates a player with a color, offset and name
	 * Creates 4 figures with player color
	 */
	
	public Player(Color playerColor, boolean isComputer, int offset, String playerName){
		
		this.playerColor = playerColor;
		this.isComputer = isComputer;
		this.offset = offset;
		this.playerName = playerName;
		
		this.figures = new HashMap<Integer, Figure>();
		this.houses = new HashMap<Integer, House>();
		//create 4 figures
		for(int i = 1 ; i <= 4 ; i++){
			figures.put(i, new Figure(playerColor, i));
			houses.put(i, new House(i));
		}
	}
	
	public int throwCube(){
		return (int)((Math.random()) * 6 + 1);
	}
	
	public void setPlayerFigure(Figure f, int steps){
		f.setSteps(steps);
	}
	
	public void setFigureOut(Figure f){
		f.setSteps(Figure.startField);
	}
	
	public void setFigureBack(Figure f){
		f.setSteps(Figure.startPosition);
	}
	public void setFiguresBack(){
		for(Figure f : figures.values())
		{
			setFigureBack(f);
		}
	}
	
	public Color getPlayerColor(){
		return playerColor;
	}
	
	/**public void setPlayerName(String name){
		playerName = name;
	}**/
	
	public String getPlayerName(){
		return playerName;
	}
	
	public boolean isComputer(){
		return isComputer;
	}
	
	public int getOffset(){
		return offset;
	}
	
	public Map<Integer, Figure> getFigures(){
		return figures;
	}
	
	public Map<Integer, House> getHouses() {
		return houses;
	}

	
	
	public boolean canDriveThreeTimes()
	{
		// prüfen ob Spieler dreimal würfenl darf
		// darf nur 3x würfeln, wenn
		// - alle Figuren noch im Start sind
		// - keine Figur auf Spielbrett und im Haus ganz am Ende sind
		//   (also nicht erst erstes Feld im Haus...)
		
		
		// Prüfen ob alle Figuren am Start sind
		if(figuresAtStart())
		{
			return true;
		}
		
		if(figuresAtHome())
		{
			
		}
	}

	/**
	 * Gibt true zurück, wenn mindestens eine Figur noch in der Startposition ist
	 * @return
	 */
	private boolean figuresAtStart()
	{
		for(Figure f : figures.values())
		{
			if(f.getSteps() != Figure.startPosition)
			{
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Gibt true zurück, wenn mindestens eine Figur im Haus ist
	 * @return
	 */
	/**
	private boolean figuresAtHome()
	{
		for(House h : houses.values())
		{
			if(!h.isFree())
			{
				return true;
			}
		}
		return false;
	}
	**/
	
	/**
	private Figure[] getHouseFigures()
	{
		/**Figure[] homeFigures = new Figure[];
		for(Figure f : figures)
		{
			if(f.getSteps() >= Figure.firstHousePosition)
			{
				
			}
		}**/
		
	}**/
	
}
