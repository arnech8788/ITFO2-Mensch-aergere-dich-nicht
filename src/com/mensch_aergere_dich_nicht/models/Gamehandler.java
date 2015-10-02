package com.mensch_aergere_dich_nicht.models;

import java.util.*;
import java.awt.*;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.Field.*;
import com.mensch_aergere_dich_nicht.view.*;

public class Gamehandler  {
	public static final int fieldCount = 40;
	
	// extends Observable
	
	Map<Integer,Field> Fields;
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
	
	public Map<Integer, Field> getFields() {
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
		// AddPlayer()
		
		
		//gh.startGame();
		
		// Player getStartingPlayer (durch W�rfeln ermitteln)
		Player player = gh.getStartingPlayer(gh.getPlayers());
		
		
		// TODO: bei 6 darf der Spieler nochmal w�rfeln
		
		do {
			System.out.println(player.getPlayerName() + " ist am Spielzug.");
			
			if (player.canDriveThreeTimes())
			{
				for(int i = 0; i < 3; i++)
				{
					int number = player.throwCube();
					System.out.println(player.getPlayerName() + " w�rfelt eine " + String.valueOf(number));
				
					if(number == 6)
					{
						System.out.println("Er darf eine Figur raussetzen.");
					
						// hier muss noch vorher gepr�ft werden ob schon eine Figur auf dem Feld steht
						int fieldNumber = gh.getFieldNumber(player.getOffset(), Figure.startField);
						if(gh.isFieldFree(fieldNumber))
						{
							// dann setze die Figur
							
						}
						else
						{
							// pr�fen ob man die figur schlagen kann
							if(gh.canBeatFigure(fieldNumber, player))
							{
								// Figur schlagen
								gh.beatFigure(fieldNumber, figure);
							}
							else
							{
								// es ist die eigene Figur!
								
								
							}
								
						}

						
						Figure f = player.setFigureOut();
						gh.setFigure2Field(f, fieldNumber);
						
						// bei einer sechs darf er nochmal
						
						break;
					}
				}
				
			}
			
			else{
				int number = player.throwCube();
				System.out.println(player.getPlayerName() + " w�rfelt eine " + String.valueOf(number));
			
				// TODO: ermitteln welche Spielzugm�glichkeiten der Spieler hat
				
				
				// m�glichkeiten an gui?
				
					
				// hier muss noch vorher gepr�ft werden ob schon eine Figur auf dem Feld steht
				int fieldNumber = gh.getFieldNumber(player.getOffset(), Figure.startField);
				if(gh.isFieldFree(fieldNumber))
				{
					// figur kann gesetzt werden
					
				}
				
				//Figure f = player.setFigureOut();
				int steps = 0;
				Figure f = null;
				player.setPlayerFigure(f, steps);
				
				
				gh.setFigure2Field(f, fieldNumber);
			}
			
			// n�chsten Spieler holen
			player = gh.getNextPlayer(player);

			
		}while(player != null);
		
		
		// 	3x w�rfeln bis er eine sechs hat
		
		
		// moveOption getMoveOptions(Player) (welche Spieloptionen)
		
		// boolean setMove(moveOption) (setze Spieler)
		
		// getNextPlayer
		
		
		
		
		//MoveResult result = gh.nextMove();
		
	}
	
	private void beatFigure(int fieldNumber,
					   		Figure figure)
	{
		if (!canBeatFigure(fieldNumber, figure))
		{
			throw new RuntimeException("Die Figur kann nicht geschlagen werden!");
		}
		// TODO: implementieren
		
	}
	
	private boolean canBeatFigure(int fieldNumber,
								  Player player)
	{
		return this.getFields().get(fieldNumber).getFigure().getFigureColor() != player.getPlayerColor();
	}
	private boolean canBeatFigure(int fieldNumber,
			  Color playerColor)
	{
		return this.getFields().get(fieldNumber).getFigure().getFigureColor() != playerColor;
	}
	private boolean canBeatFigure(int fieldNumber,
			  Figure figure)
	{
		return this.getFields().get(fieldNumber).getFigure().getFigureColor() != figure.getFigureColor();
	}
	
	private boolean isFieldFree(int fieldNumber)
	{
		return this.getFields().get(fieldNumber).isFree();
		
	}
	
	private int getFieldNumber(int playerOffSet,
							   int figureSteps)
	{
		int temp = playerOffSet + figureSteps;
		if (temp >= fieldCount){
			temp -= fieldCount;
		}
		return temp;
	}
	
	public void addPlayer(String name,
						  boolean isKI)
	{
		throw new RuntimeException("function is not implemented!");
	}
	
	
	/**
	public void startGame()
	{
		// alle Figuren an Anfang setzen
		resetPlayers();
		
		
		
		
		// ersten Spielzug...
		
		
		// solange W�rfeln bis einer eine sechs hat
		
		// bis zu drei mal w�rfeln 
		
		// evtl spieler raussetzen
		
		
		
		
	}**/
	
	
	public boolean setNextMove()
	{
		// als Parameter figur?
		return false;
	}
	
	/**
	public MoveOptions getNextMoveOptions()
	{
		// analyse und m�glichkeiten ausgeben
	}
	**/
	
	/**
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
		if(player.canDriveThreeTimes())
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
		
		
		// was ist, wenn der Spieler nochmal dran ist?
		// also immer wieder eine sechs w�rfelt
	}
	**/
	

	
	public Player getNextPlayer(Player lastPlayer)
	{
		// anhand des letzten Spielzugs den n�chsten Spieler ermitteln
		for(int i = 0; i < this.getPlayers().size(); i++)
		{
			Player temp = (Player) this.getPlayers().values().toArray()[i]; 
			if(temp.getPlayerColor() == lastPlayer.getPlayerColor())
			{
				if(i == getPlayers().size() - 1)
				{
					return (Player) this.getPlayers().values().toArray()[0];
				}
				else
				{
					return (Player) this.getPlayers().values().toArray()[i + 1];
				}
			}
		}
		
		// option 'isCloseGameWhenPlayerWins' beachten
		
		throw new RuntimeException("Es wurde kein Spieler f�r den n�chsten Spielzug gefunden!");
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
				startingPlayer.put(player.getKey(), player.getValue());
			}
			else if(threwCube == highestThrew)
			{
				startingPlayer.put(player.getKey(), player.getValue());
			}
		}
		
		if(startingPlayer.size() == 1)
		{
			return (Player) startingPlayer.values().toArray()[0];
		}
		else
		{
			// es gab mehrere mit selber gr��ter augenzahl
			// -> also d�rfen die Spieler nochmal w�rfeln
			return getStartingPlayer(startingPlayer);
		}
	}
	
	
	
	/**
	 * setting player figures back in house
	 */
	public void setAllFiguresBack()
	{
		for(Player p : this.Players.values())
		{
			p.setFiguresBack();
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
		Fields = new HashMap<Integer,Field>();
		int iFieldCount = fieldCount; //Number of Fields ( without House )
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
			Fields.put(i, new Field(type,i));
		}
	}
	
	private void setFigure2Field(Figure figure,
			 					 int fieldNumber)
	{
		Field field = this.getFields().get(fieldNumber);
		if(! field.isFree())
		{
			throw new RuntimeException("Die Figur " + String.valueOf(figure.getNumber()+ " mit der Farbe " + figure.getFigureColor().toString() + " kann nicht auf das Feld " + field.getNumber() + " setzen, da es von der Figur " + String.valueOf(figure.getNumber()+ " mit der Farbe " + figure.getFigureColor().toString() +  "  besetzt ist.")));
		}
		
		field.setFigure(figure);
		
		if(field.getType() == Field.Type.START)
		{
			System.out.println("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Startfeld gesetzt.");
		}
		else
		{
			System.out.println("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Feld " + String.valueOf(field.getNumber())+ " gesetzt.");
		}
		
	}
	
	/**
	private void setFigure2Field(Figure figure,
								 int playerOffSet)
	{
		this.setFigure2Field(figure, getFieldNumber(playerOffSet, figure.getSteps()););
	}**/

	 

}
