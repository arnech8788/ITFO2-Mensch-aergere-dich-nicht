package com.mensch_aergere_dich_nicht.models;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Collections.*;

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
	public Player(Color playerColor, 
			      boolean isComputer, 
			      int offset, 
			      String playerName){
		
		this.playerColor = playerColor;
		this.isComputer = isComputer;
		this.offset = offset;
		this.playerName = playerName;
		
		this.figures = new HashMap<Integer, Figure>();
		this.houses = new HashMap<Integer, House>();
		//create 4 figures
		for(int i = 1 ; i <= 4 ; i++){
			figures.put(i, new Figure(playerColor, i));

			int houseNumber = i + House.getHouseAdditionValue(offset);
			houses.put(houseNumber, new House(houseNumber));
		}
	}
	
	public int throwCube(){
		return (int)((Math.random()) * 6 + 1);
	}
	
	public void setPlayerFigure(Figure f, int steps){
		f.setSteps(steps);
	}
	
	public Figure setFigureOut(){
		// Prüfen ob es noch Figuren am Start gibt, falls nicht Fehler
		//if (!this.allFiguresAtStartPosition())
		if(!this.anyFigureAtStartPosition())
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
		//f.setSteps(Figure.startPosition);
		// TODO: Figur wieder ins Haus stellen?!
		f.set2StartPosition();
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
		
		// Sobald eine Figur auf dem Spielbrett ist,
		// darf man nur noch 1x würfeln
		// ignoriere dabei Spieler im Haus
		if(anyFigureAtBoard(false))
		{
			return false;
		}
		
		
		// Prüfen ob alle Figuren am Start sind
		if(allFiguresAtStartPosition())
		{
			return true;
		}
		
		// Wenn keine Figur auf dem Spielbrett ist
		// und nicht alle Figuren an der Startposition sind,
		// muss mindestens eine Figur im Haus sein
		
		if(!anyFigureAtStartPosition())
		{
			throw new RuntimeException("Fehler bei der Prüfung der Anzahl der möglichen Würfe: Es wurde keine Figur an der Startposition gefunden!");
		}
		
		// Prüfe ob Figuren im Haus hintereinander sind
		Map<Integer, Figure> xy = this.getHouseFigures();
		Collection<Figure> col = xy.values();
		//Object[] arr = col.toArray();
		Figure[] homeFigures = col.toArray(new Figure[0]);
		Arrays.sort(homeFigures);
		//java.util.Collections.sort((List<Figure>) col.toArray(a));
		//Figure[] homeFigures = col.toArray( new Figure[0]);
		
		for (int i = 0; i < homeFigures.length - 1; i ++)
		{
			
			if(homeFigures[i].getSteps() -1 != homeFigures[i + 1].getSteps())
			{
				return false;
			}
		}

		/**
		for (int i = 0; i < homeFigures.length - 1; i ++)
		{
			if(homeFigures[i].getSteps() -1 != homeFigures[i + 1].getSteps())
			{
				return false;
			}
		}
		**/
		
		return true;
	}

	/**
	 * Gibt true zurück, wenn mindestens eine Figur auf dem Spielbrett ist
	 * (Figuren im Haus gehören nicht dazu)
	 * @return
	 */
	private boolean anyFigureAtBoard(boolean includeHouse)
	{
		for(Figure f : this.getFigures().values())
		{
			if(f.isOnGameboard(includeHouse))
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
	
	public int getCountOfFiguresAtStartPiosition()
	{
		int result = 0;
		for(Figure figure : figures.values())
		{
			if(figure.getSteps() == Figure.startPosition)
			{
				result ++;;
			}
		}
		return result;
	}
	
	/**
	 * Gibt true zurück, wenn mindestens eine Figur im Haus ist
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

	
	public Map<Integer, Figure> getHouseFigures()
	{
		Map<Integer, Figure> homeFigures = new HashMap<Integer, Figure>();
		for(Figure f : this.getFigures().values())
		{
			if(f.getSteps() >= Gamehandler.fieldCount)
			{
				//homeFigures.add(f);
				homeFigures.put(f.getNumber(), f);
			}
		}
		
		//return (Figure[]) homeFigures.toArray();
		return homeFigures;
	}	
	
	
	
	
	/**
	 * Gibt true zurück, wenn alle Figuren in der Startposition sind
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
	 * Gibt true zurück, wenn mindestens eine Figur noch in der Startposition ist
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
