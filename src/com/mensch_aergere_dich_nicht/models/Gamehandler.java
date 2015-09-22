package com.mensch_aergere_dich_nicht.models;

import java.util.*;
import java.awt.*;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.Field.*;
import com.mensch_aergere_dich_nicht.view.*;

public class Gamehandler  {
	
	// extends Observable
	
	Map<String,Field> Fields;
	Map<String,Player> Players;
	
	
	private Options options;
	private MoveResult lastMoveResult;
	
	public Gamehandler(Options options,
					   String[] playerNames)
	{
		//this.addObserver(gameboard);
		this.options = options;
		this.createFields();
		this.createPlayers(playerNames);
	}
	
	public Map<String, Field> getFields() {
		return Fields;
	}

	/**public void setFields(Map<String, Field> fields) {
		Fields = fields;
	}**/

	public Map<String, Player> getPlayers() {
		return Players;
	}

	/**public void setPlayers(Map<String, Player> players) {
		Players = players;
	}**/

	
	public static void main(String[] args)
	{
		// only testing methods
		Options op = new Options();
		
		Gamehandler gh = new Gamehandler(op, 
				new String[] {"Gernhart Reinholzen",
				  "Lassmiranda den si Villia",
				  "Timo Beil",
				  "Anne Theke"});
		
		gh.startGame();
		
		MoveResult result = gh.nextMove();
				
	}
	
	
	public void startGame()
	{
		// alle Figuren an Anfang setzen
		resetPlayers();
		
		
		
		
		// ersten Spielzug...
		
		
		// solange W�rfeln bis einer eine sechs hat
		
		// bis zu drei mal w�rfeln 
		
		// evtl spieler raussetzen
		
		
		
		
	}
	
	public MoveResult nextMove()
	{
		// checking last move
		Player player = null;
		if (lastMoveResult == null)
		{
			player = getStartingPlayer(this.Players);
		}
		else
		{
			player = getNextPlayer(lastMoveResult);
		}
		
		
		// ACHTUNG: bei 6 gleicher Spieler nochmal
		// analysiere ob mind eine Figur drau�en ist
		if(canDriveThreeTimes(player))
		{
			// 3x w�rfen 
			// -> bei sechs kann man die n�chste Figur ziehen
			
		}
		else
		{
			// 1x w�rfeln
			// analyse was man machen kann
			// ergebnis an Gui zur�ck
			
		}
		
		
		
	}
	
	
	private boolean canDriveThreeTimes(Player player)
	{
		// pr�fen ob Spieler dreimal w�rfenl darf
		// darf nur 3x w�rfeln, wenn
		// - alle Figuren noch im Start sind
		// - keine Figur auf Spielbrett und im Haus ganz am Ende sind
		//   (also nicht erst erstes Feld im Haus...)
		
	}
	
	
	private Player getNextPlayer(MoveResult lastMoveResult)
	{
		// anhand des letzten Spielzugs den n�chsten Spieler ermitteln
		
		// option 'isCloseGameWhenPlayerWins' beachten
		
		
	}
	
	private Player getStartingPlayer(Map<String, Player> players)
	{
		// jeder einmal w�rfeln
		// mit h�chster zahl darf anfangen
		// bei gleicher Augenzahl d�rfen die beiden nochmal w�rfeln
		
		Map<String, Player> startingPlayer = new HashMap<String, Player>();
		int highestThrew = 0;
		
		for(Map.Entry<String, Player> player : players.entrySet())
		{
			int threwCube = player.getValue().throwCube();
			if(threwCube > highestThrew) {
				highestThrew = threwCube;
				startingPlayer.clear();
				startingPlayer.put(player.getKey(), player.getValue())
			}
			else if(threwCube == highestThrew)
			{
				startingPlayer.put(player.getKey(), player.getValue())
			}
		}
		
		if(startingPlayer.size() == 1)
		{
			// return first
			//return startingPlayer.entrySet().
		}
		
		sonst methode nochmal...
	}
	
	
	
	/**
	 * setting player figures back in house
	 */
	private void resetPlayers()
	{
		for(Map.Entry<String, Player> p : this.Players.entrySet())
		{
			p.getValue().resetFigures();
		}
	}
	
	private void createPlayers(String[] playerNames)
	{
		Players = new HashMap<String,Player>();
		
		int iOffset = 0;// Offset of Fieldposition
		boolean bIsComputer = false; // Is Computer?
		//String[] sPlayerName = {"Gernhart Reinholzen","Lassmiranda den si Villia","Timo Beil","Anne Theke"};//Playernames
		Color[] colorArray = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW };//Color of Players
				
		for (int i = 0; i < playerNames.length; i++)
		{
			Player player = new Player(colorArray[i],bIsComputer,iOffset,playerNames[i]);
			Players.put(player.getPlayerColor().toString(), player);  // = new Player();
			iOffset += 10;
		}
	}
	
	private void createFields(){
		Fields = new HashMap<String,Field>();
		int iFieldCount = 40; //Number of Fields ( without House )
		Type type;
		
		for (Integer i = 0; i < iFieldCount; i++)
		{
			if (i % 10 == 0) 
			{ 
				type = Type.START; 
			}
			else
			{
				type = Type.NORMAL;
			}
			Fields.put(i.toString(), new Field(type,i));
		}
	}
	

	 

}
