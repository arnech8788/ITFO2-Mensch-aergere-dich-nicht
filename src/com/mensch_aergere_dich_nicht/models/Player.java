package com.mensch_aergere_dich_nicht.models;

import java.awt.*;
import java.awt.List;
import java.util.*;

public class Player {
	
	private Color playerColor;
	private int offset;
	private boolean isComputer;
	private String playerName;
	private Map<Integer, Figure> figures;
	private Map<Integer, House> houses;
	
	/**
	 * Creates a player with a color, offset and name
	 * Creates 4 figures with player color
	 **/
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
	
	public Figure setFigureOut(){
		// Pr�fen ob es noch Figuren am Start gibt, falls nicht Fehler
		if (!this.allFiguresAtStartPosition())
		{
			throw new RuntimeException("In der Startposition gibt es keine Figuren mehr!");
		}
		
		// ansonsten erste Fogur aus start holen
		Figure f = getFirstFigureFromStartPosition();
		f.setSteps(Figure.startField);
		return f;
	}
	
	private Figure getFirstFigureFromStartPosition()
	{
		for(Figure f : this.figures.values())
		{
			if(f.getSteps() == Figure.startPosition)
			{
				return f;
			}
		}
		
		throw new RuntimeException("Es konnte keine Figure an der Startposition gefunden werden!");
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
		// pr�fen ob Spieler dreimal w�rfenl darf
		// darf nur 3x w�rfeln, wenn
		// - alle Figuren noch im Start sind
		// - keine Figur auf Spielbrett und im Haus ganz am Ende sind
		//   (also nicht erst erstes Feld im Haus...)
		
		// Sobald eine Figur auf dem Spielbrett ist,
		// darf man nur noch 1x w�rfeln
		if(anyFigureAtBoard())
		{
			return false;
		}
		
		
		// Pr�fen ob alle Figuren am Start sind
		if(allFiguresAtStartPosition())
		{
			return true;
		}
		
		// Wenn keine Figur auf dem Spielbrett ist
		// und nicht alle Figuren an der Startposition sind,
		// muss mindestens eine Figur im Haus sein
		
		if(!anyFigureAtStartPosition())
		{
			throw new RuntimeException("Fehler bei der Pr�fung der Anzahl der m�glichen W�rfe: Es wurde keine Figur an der Startposition gefunden!");
		}
		
		// Pr�fe ob Figuren im Haus hintereinander sind
		Figure[] homeFigures = this.getHouseFigures();
		Arrays.sort(homeFigures);
		
		
		for (int i = 0; i < homeFigures.length - 1; i ++)
		{
			if(homeFigures[i].getSteps() -1 != homeFigures[i + 1].getSteps())
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Gibt true zur�ck, wenn mindestens eine Figur auf dem Spielbrett ist
	 * (Figuren im Haus geh�ren nicht dazu)
	 * @return
	 */
	private boolean anyFigureAtBoard()
	{
		for(Figure f : this.getFigures().values())
		{
			if(f.isOnGameboard())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Figure getAnyFigureFromStartPosition()
	{
		if(!anyFigureAtStartPosition())
		{
			throw new RuntimeException("Es wurde keine Figur an der Startposition gefunden!");
		}
		
		for(Figure figure : figures.values())
		{
			if(figure.getSteps() == Figure.startPosition)
			{
				return figure;
			}
		}
		
		throw new RuntimeException("Es wurde keine Figur an der Startposition gefunden! (method 'anyFigureAtStartPosition' has any error)");
	}
	
	
	/**
	 * Gibt true zur�ck, wenn mindestens eine Figur im Haus ist
	 * @return
	 */
	/**
	private boolean anyFiguresAtHome()
	{
		for(House h : houses.values())
		{
			if(!h.isFree())
			{
				return true;
			}
		}
		return false;
	}**/

	
	private Figure[] getHouseFigures()
	{
		ArrayList<Figure> homeFigures = new ArrayList<Figure>();
		for(Figure f : this.getFigures().values())
		{
			if(f.getSteps() >= Figure.firstHousePosition)
			{
				homeFigures.add(f);
			}
		}
		
		return (Figure[]) homeFigures.toArray();
	}	
	
	
	
	/**
	 * Gibt true zur�ck, wenn alle Figuren in der Startposition sind
	 * @return
	 */
	private boolean allFiguresAtStartPosition()
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
	 * Gibt true zur�ck, wenn mindestens eine Figur noch in der Startposition ist
	 * @return
	 */
	private boolean anyFigureAtStartPosition()
	{
		for(Figure f : figures.values())
		{
			if(f.getSteps() == Figure.startPosition)
			{
				return true;
			}
		}
		return false;
	}
	
}
