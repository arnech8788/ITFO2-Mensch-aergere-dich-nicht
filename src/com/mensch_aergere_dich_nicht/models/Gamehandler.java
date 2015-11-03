package com.mensch_aergere_dich_nicht.models;

import java.util.*;
import java.util.List;
import java.awt.*;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.Field.*;
import com.mensch_aergere_dich_nicht.view.*;

public class Gamehandler  {
	public static final int fieldCount = 40;
	
	// extends Observable
	
	Map<Integer,Field> fields;
	Map<String,Player> players;
	
	
	private Options options;
	//private MoveResult lastMoveResult;
	private Board board;
	
	public Gamehandler(Options options,
					   String[] playerNames)
	{
		//this.addObserver(gameboard);
		this.options = options;
		this.createFields();
		this.createPlayers(playerNames);
		this.board = new Board(fields, players);
		this.board.drawBoard();
		
	}
	
	public Map<Integer, Field> getFields() {
		return fields;
	}

	/**public void setFields(Map<String, Field> fields) {
		Fields = fields;
	}**/

	public Map<String, Player> getPlayers() {
		return players;
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
		
		// Player getStartingPlayer (durch Würfeln ermitteln)
		Player player = gh.getStartingPlayer(gh.getPlayers());
		
		
		// TODO: bei 6 darf der Spieler nochmal würfeln
		
		do {
			gh.board.displayMessage(player.getPlayerName() + " ist am Spielzug.");
			
			if (player.canDriveThreeTimes())
			{
				for(int i = 0; i < 3; i++)
				{
					int number = player.throwCube();
					gh.board.displayMessage(player.getPlayerName() + " würfelt eine " + String.valueOf(number));
					
					if(number == 6)
					{
						gh.board.displayMessage("Er darf eine Figur raussetzen.");
					
						// hier muss noch vorher geprüft werden ob schon eine Figur auf dem Feld steht
						int fieldNumber = gh.getFieldNumber(player.getOffset(), Figure.startField);
						if(gh.isFieldFree(fieldNumber))
						{
							// dann setze die Figur
							Figure f = player.setFigureOut();
							gh.setFigure2Field(f, fieldNumber);
	
						}
						else
						{
							// prüfen ob man die figur schlagen kann
							if(gh.canBeatFigure(fieldNumber, player))
							{
								// Figur schlagen mit
								// erster Figur aus Startposition
								gh.beatFigure(fieldNumber, player.getAnyFigureFromStartPosition());
							}
							else
							{
								// es ist die eigene Figur!
								
								
							}
								
						}

						
						
						// bei einer sechs darf er nochmal
						
						break;
					}
				}
				
			}
			
			else{
				int number = player.throwCube();
				gh.board.displayMessage(player.getPlayerName() + " würfelt eine " + String.valueOf(number));
			
				// TODO: ermitteln welche Spielzugmöglichkeiten der Spieler hat
				List<MoveOption> moveOptions = new  ArrayList<MoveOption>();
				
				// alle Figuren die draußen sind ermitteln
				for(Figure f : player.getFigures().values())
				{
					if(f.isOnGameboard(true))
					{
						//-> Figur ist auf dem Spielfeld (auch Haus)
						
						// Prüfen wo die Figur bei setzen der geworfenen Würfel zahl stehen würde
						int fieldNumber = gh.getFieldNumber(player.getOffset(), f.getSteps() + number);
						Field field = gh.getFields().get(fieldNumber);
						
						if(!field.isFree())
						{
							if(gh.canBeatFigure(fieldNumber, f))
							{
								moveOptions.add(new MoveOption(f, MoveOption.Type.CanBeat));
							}
							break;
						}
						
						//-> Feld ist noch frei
						moveOptions.add(new MoveOption(f, MoveOption.Type.Set));					}
+				}
				
				
				// Möglichkeiten der einzelnen Figuren prüfen
				// - MoveOptions
				
				
				// möglichkeiten an gui?
				
				
					
				// hier muss noch vorher geprüft werden ob schon eine Figur auf dem Feld steht
				int fieldNumber = gh.getFieldNumber(player.getOffset(), Figure.startField);
				if(gh.isFieldFree(fieldNumber))
				{
					// figur kann gesetzt werden
					
				}
				
				//Figure f = player.setFigureOut();
				int steps = 0;
				//Figure f = null;
				player.setPlayerFigure(f, steps);
				
				
				gh.setFigure2Field(f, fieldNumber);
			}
			
			// nächsten Spieler holen
			player = gh.getNextPlayer(player);

			
		}while(player != null);
		
		
		// 	3x würfeln bis er eine sechs hat
		
		
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
		
		// TODO: testen
		
		// setze Figur des anderen Spielers wieder zurück
		// TODO: implementieren
		
		Figure fieldFigure = this.getFields().get(fieldNumber).getFigure();
		this.setFigure2Field(fieldFigure, fieldNumber);
		figure.set2StartPosition();
		
		// setze Figur auf das Feld
		this.setFigure2Field(figure, fieldNumber);
		figure.setSteps(this.getSteps(this.getPlayer(figure).getOffset(),fieldNumber));
		
	}
	
	
	
	private int getSteps(int playerOffset,
						 int fieldNumber)
	{
		int temp = fieldNumber - playerOffset;
		if (temp < 0)
		{
			temp+= Gamehandler.fieldCount;
		}
		return temp;
	}
	
	
	
	private Player getPlayer(Figure figure)
	{
		for(Player player : this.getPlayers().values())
		{
			if(player.getPlayerColor() == figure.getFigureColor())
			{
				return player;
			}
		}
		
		throw new RuntimeException("Es wurde kein Spieler mit der Figur gefunden!");
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
		
		
		// solange Würfeln bis einer eine sechs hat
		
		// bis zu drei mal würfeln 
		
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
		// analyse und möglichkeiten ausgeben
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
		// analysiere ob mind eine Figur draußen ist
		if(player.canDriveThreeTimes())
		{
			// 3x würfen 
			// -> bei sechs kann man die nächste Figur ziehen
			
		}
		else
		{
			// 1x würfeln
			// analyse was man machen kann
			// ergebnis an Gui zurück
			
		}
		
		
		// was ist, wenn der Spieler nochmal dran ist?
		// also immer wieder eine sechs würfelt
	}
	**/
	

	
	public Player getNextPlayer(Player lastPlayer)
	{
		// anhand des letzten Spielzugs den nächsten Spieler ermitteln
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
		
		throw new RuntimeException("Es wurde kein Spieler für den nächsten Spielzug gefunden!");
	}
	
	private Player getStartingPlayer(Map<String, Player> players)
	{
		// jeder einmal würfeln
		// mit höchster zahl darf anfangen
		// bei gleicher Augenzahl dürfen die beiden nochmal würfeln
		
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
			// es gab mehrere mit selber größter augenzahl
			// -> also dürfen die Spieler nochmal würfeln
			return getStartingPlayer(startingPlayer);
		}
	}
	
	
	
	/**
	 * setting player figures back in house
	 */
	public void setAllFiguresBack()
	{
		for(Player p : this.players.values())
		{
			p.setFiguresBack();
		}
	}
	
	private void createPlayers(String[] playerNames)
	{
		players = new HashMap<String,Player>();
		
		int iOffset = 0;// Offset of Fieldposition
		boolean bIsComputer = false; // Is Computer?
		//String[] sPlayerName = {"Gernhart Reinholzen","Lassmiranda den si Villia","Timo Beil","Anne Theke"};//Playernames
		Color[] colorArray = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW };//Color of Players
				
		for (int i = 0; i < playerNames.length; i++)
		{
			Player player = new Player(colorArray[i],bIsComputer,iOffset,playerNames[i]);
			players.put(player.getPlayerColor().toString(), player);  // = new Player();
			iOffset += 10;
		}
	}
	

	
	private void createFields(){
		fields = new HashMap<Integer,Field>();
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
			fields.put(i, new Field(type,i));
		}
	}
	
	private void setFigure2Field(Figure figure,
			 					 int fieldNumber)
	{
		// TODO: wenn Flednummer ein Haus ist,
		// anders reagieren...
		
		
		Field field = this.getFields().get(fieldNumber);
		if(! field.isFree())
		{
			throw new RuntimeException("Die Figur " + String.valueOf(figure.getNumber()+ " mit der Farbe " + figure.getFigureColor().toString() + " kann nicht auf das Feld " + field.getNumber() + " setzen, da es von der Figur " + String.valueOf(figure.getNumber()+ " mit der Farbe " + figure.getFigureColor().toString() +  "  besetzt ist.")));
		}
		
		//TODO: Figur von aktuellen Feld entfernen
		
		
		field.setFigure(figure);
		
		if(field.getType() == Field.Type.START)
		{
			board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Startfeld gesetzt. (Feld '"+ String.valueOf(field.getNumber())+ "') von Spieler " + figure.getFigureColor().toString());
		}
		else
		{
			board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Feld " + String.valueOf(field.getNumber())+ " von Spieler " + figure.getFigureColor().toString() +" gesetzt");
		}
		
	}
	
	/**
	private void setFigure2Field(Figure figure,
								 int playerOffSet)
	{
		this.setFigure2Field(figure, getFieldNumber(playerOffSet, figure.getSteps()););
	}**/

	 

}
