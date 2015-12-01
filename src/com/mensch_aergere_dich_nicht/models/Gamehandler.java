package com.mensch_aergere_dich_nicht.models;

import java.util.*;
import java.util.List;
import java.awt.*;

import com.mensch_aergere_dich_nicht.models.MoveOption.ePriority;
import com.mensch_aergere_dich_nicht.view.*;

public class Gamehandler implements Listener  {
	public static final int fieldCount = 40;
	public static final int errorField = -1;
	
	// extends Observable
	
	Map<Integer,Field> fields;
	Map<String,Player> players;
	
	// TODO: Regeln beachten!
	// TODO: KI implementieren
	// TODO: Prüfung ob ein Spieler gewonnen hat
	// TODO: zwei aktive Spieler
	
	// Tobias:
	// TODO: evtl. mögliche Spielzüge markieren? (Feature)
	// TODO: Figuren im Haus werden nicht angezeigt, obwohl die Objekte passen (Player & Co.)
	
	/**
	 * 
	 * 
wer ist gerade dran? -> markieren

wenn feld -1 dann ignorieren geklicked -> done
Ausgabe der Feldnummern deaktivieren -> Tobias

bei gelb (zwei) sind die housenummern falsch rum -> passen bei allen Spielern nicht so richtig...
Fehler kann eigentlich nur bei Gui liegen

werte der Prioritäten passen nicht (Enum als Flag...)
	 * 
	 * 
	 */
	
	
	private Options options;
	//private MoveResult lastMoveResult;
	private Board board;
	private MainGui mainGui;
	
	private boolean waitForUserInput;
	private List<MoveOption> moveOptions;
	
	public Gamehandler(Options options,
					   String[] playerNames,
					   MainGui mainGui)
	{
		//this.addObserver(gameboard);
		this.options = options;
		this.createFields();
		this.createPlayers(playerNames);
		this.moveOptions = new  ArrayList<MoveOption>();
		this.board = new Board(fields, players, this);
		this.board.drawBoard();
		this.mainGui = mainGui;
		
		this.nextMoveOption(this.getStartingPlayer(this.getPlayers()));
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
				new String[] {"eins",
				  "zwei",
				  "drei",
				  "vier"}, null);
		// AddPlayer()
		
		
		//gh.startGame();
		
		// Player getStartingPlayer (durch Würfeln ermitteln)
		//Player player = gh.getStartingPlayer(gh.getPlayers());
		
		//gh.nextMoveOption(player);

		
		
		// 	3x würfeln bis er eine sechs hat
		
		
		// moveOption getMoveOptions(Player) (welche Spieloptionen)
		
		// boolean setMove(moveOption) (setze Spieler)
		
		// getNextPlayer
		
		
		
		
		//MoveResult result = gh.nextMove();
		
	}
	
	
	private void nextMoveOption(Player player)
	{
		this.board.displayMessage(player.getPlayerName() + " ist am Spielzug.");
		int thrownCubeNumber = 0;
		
		if (player.canDriveThreeTimes())
		{
			for(int i = 0; i < 3; i++)
			{
				//thrownCubeNumber = player.throwCube();
				thrownCubeNumber = this.board.getRoll();
				this.board.displayMessage(player.getPlayerName() + " würfelt eine " + String.valueOf(thrownCubeNumber));
			
				if(thrownCubeNumber == 6)
				{
					this.handleCubeNumberSix(player);
					break;
				}
			}
			
			if(thrownCubeNumber != 6)
			{
				this.nextMoveOption(this.getNextPlayer(player));
			}
		}
	
		else
		{
			thrownCubeNumber = this.board.getRoll(); //player.throwCube();
			
			this.board.displayMessage(player.getPlayerName() + " würfelt eine " + String.valueOf(thrownCubeNumber));
			//this.board.displayMessage("(Farbe: " + player.getPlayerColor().toString());
			this.setMoveOptions(player, thrownCubeNumber);
		
					
			// Möglichkeiten der einzelnen Figuren prüfen
			// - MoveOptions
			// wenn es nur eine Spielzugmöglichkeit gibt,
			// führe diese aus
			if(this.moveOptions.size() == 1)
			{
				// Führe den Spielzug aus
				this.handleMoveOption(this.moveOptions.get(0));
			}
			else if(this.moveOptions.size() > 1)
			{
				this.printDebugMoveOPtions();
				// TODO: nach Prioritäten sortieren
				// wenn es von der höchsten nur eine gibt
				// und die Regel gesetzt ist
				// führe den Spielzug aus...

				Collections.sort(this.moveOptions);
				boolean handledMoveOption = false;
				for(int i = this.moveOptions.size() - 1; i > 0; i--)
				{
					MoveOption curOption = this.moveOptions.get(i);
					MoveOption nextOption = this.moveOptions.get(i - 1);
					
					// Prüfe ob das letzte Element die höchste Priorität hat
					if(curOption.compareTo(nextOption) > 0)
					{
						// TODO: prüfe ob die Regel aktiviert ist...
						//...
						this.handleMoveOption(curOption);
						handledMoveOption = true;
						break;
					}
				}
				
				if(!handledMoveOption)
				{
					//gh.board.displayMessage(player.getPlayerName() + " würfelt eine " + String.valueOf(thrownCubeNumber));
					//gh.board.displayMessage("(Farbe: " + player.getPlayerColor().toString());
					//möglichkeiten an gui?
					this.board.displayMessage("Bitte wählen Sie einen Spielzug aus.");
					this.waitForUserInput = true;
					//break;
				}
			}
		}
	}
	
	

	
	private void setMoveOptions(Player player,
			int thrownCubeNumber)
	{
		// TODO: ForeignStartField && OwnStartField einbauen
		
		this.moveOptions.clear();
		
		// alle Figuren die draußen sind ermitteln
		for(Figure f : player.getFigures().values())
		{
			MoveOption.eType moveType = MoveOption.eType.Unknown;
			int newFieldNumber = 0;
			EnumSet<ePriority> movePriority = null;
			
			if(f.isOnGameboard(true))
			{
				//-> Figur ist auf dem Spielfeld (auch Haus)
				
				// Prüfen wo die Figur bei setzen der geworfenen Würfel zahl stehen würde
				// bei einem ungültigen Feld (zu hohe HausNr) wird
				// -1 zurückgegeben
				newFieldNumber = this.getFieldNumber(player.getOffset(), f.getSteps() + thrownCubeNumber, true);
				
				// Prüfen ob das neue Feld ein Haus wäre
				if(newFieldNumber >= Gamehandler.fieldCount)
				{
					if(this.canSetFigure2House(player, newFieldNumber))
					{
						//this.moveOptions.add(new MoveOption(f, MoveOption.eType.Set, newFieldNumber, thrownCubeNumber));
						moveType = MoveOption.eType.Set;
						// TODO: setzen der Priorität
						movePriority = EnumSet.of(MoveOption.ePriority.InHouse);
					}
				}
				
				else if(newFieldNumber >= 0 && !this.isFieldFree(newFieldNumber))
				{
					if(this.canBeatFigure(newFieldNumber, f))
					{
						//this.moveOptions.add(new MoveOption(f, MoveOption.eType.CanBeat, newFieldNumber, thrownCubeNumber));
						moveType = MoveOption.eType.CanBeat;
						// TODO: setzen der Priorität
						movePriority = EnumSet.of(MoveOption.ePriority.StrikeForPull);
					}
					//break;
				}
				
				else if(newFieldNumber >= 0)
				{
					//-> Feld ist noch frei
					//this.moveOptions.add(new MoveOption(f, MoveOption.eType.Set, newFieldNumber, thrownCubeNumber));
					moveType = MoveOption.eType.Set;
					// TODO: setzen der Priorität
					movePriority = EnumSet.of(MoveOption.ePriority.Normal);
				}
			}
			else if(thrownCubeNumber == 6 && !this.containsMoveOptionsPriority(MoveOption.ePriority.SixFigureOut))
			{
				// Prüfen ob das Startfeld frei ist
				// bzw. keine eigene Figur darauf steht
				newFieldNumber = this.getFieldNumber(player.getOffset(), Figure.startField);
				//Field field = this.fields.get(fieldNumber);
				
				if(this.isFieldFree(newFieldNumber))
				{
					//this.moveOptions.add(new MoveOption(f, MoveOption.eType.SetOut, newFieldNumber, thrownCubeNumber, MoveOption.ePriority.SixFigureOut));
					moveType = MoveOption.eType.SetOut;
					movePriority = EnumSet.of(MoveOption.ePriority.SixFigureOut);
				}
				
				else if(this.canBeatFigure(newFieldNumber, f))
				{
					//this.moveOptions.add(new MoveOption(f, MoveOption.eType.SetOutAndBeat, newFieldNumber, thrownCubeNumber, MoveOption.ePriority.SixFigureOut));
					moveType = MoveOption.eType.SetOutAndBeat;
					movePriority = EnumSet.of(MoveOption.ePriority.SixFigureOut,  MoveOption.ePriority.StrikeForPull);
				}
			}
			
			if(moveType != MoveOption.eType.Unknown)
			{
				this.moveOptions.add(new MoveOption(f, moveType, newFieldNumber, thrownCubeNumber, movePriority));
			}
		}
		
		if(this.moveOptions.size() == 0)
		{
			this.board.displayMessage("Es gibt keine Spielzugmöglichkeit!");
			
			
		}
	}
	
	private boolean containsMoveOptionsPriority(MoveOption.ePriority priority)
	{
		for(MoveOption mo : this.moveOptions)
		{
			if(mo.getPriorityType().contains(priority))
			{
				return true;
			}
		}
		return false;
	}
	
	private void printDebugMoveOPtions()
	{
		System.out.println("Mögliche Spielzüge:");
		for(MoveOption mo : this.moveOptions)
		{
			String output = "";
			output += "FigurNr.: " + String.valueOf(mo.getFigure().getNumber());
			output += "		";
			output += "FeldNr.: " + String.valueOf(mo.getNewFieldNumber());
			output += "		";
			output += "Priorität: " + String.valueOf(mo.getPrioritySize());
			output += "		";
			output += "Prioräten: ";
			for(MoveOption.ePriority p : mo.getPriorityType())
			{
				output += p.toString() + ", ";
			}
			
			System.out.println(output);
		}
	}
	
	
	private void handleCubeNumberSix(Player player)
	{
		//this.board.displayMessage("Er darf eine Figur raussetzen.");
		
		// hier muss noch vorher geprüft werden ob schon eine Figur auf dem Feld steht
		int fieldNumber = this.getFieldNumber(player.getOffset(), Figure.startField);
		if(this.isFieldFree(fieldNumber))
		{
			// dann setze die Figur
			Figure f = player.setFigureOut();
			this.setFigure2Field(f, fieldNumber);
		}
		else
		{
			// prüfen ob man die figur schlagen kann
			if(this.canBeatFigure(fieldNumber, player))
			{
				// Figur schlagen mit
				// erste Figur aus Startposition
				this.beatFigure(fieldNumber, player.getAnyFigureFromStartPosition());
			}
			else
			{
				// es ist die eigene Figur!
				// TODO: testen!
				// Warum irgendeine Figur von Startposition?
				this.setFigure2Field(player.getAnyFigureFromStartPosition(), fieldNumber);
			}
				
		}
		this.board.drawBoard();

		
		
		// bei einer sechs darf er nochmal
		// teil von unten hier auch durchführen...
		this.nextMoveOption(player);
	}
	
	private void handleMoveOption(MoveOption mo)
	{
		switch(mo.getType())
		{
			case CanBeat:
				this.beatFigure(mo.getNewFieldNumber(), mo.getFigure());
				break;
				
			case Set:
				this.setFigure2Field(mo.getFigure(), mo.getNewFieldNumber());
				mo.getFigure().setSteps(this.getSteps(this.getPlayer(mo.getFigure()).getOffset(),mo.getNewFieldNumber()));
				break;
				
			case SetOut:
				Figure f = this.getPlayer(mo.getFigure()).setFigureOut();
				this.setFigure2Field(f, mo.getNewFieldNumber());
				break;
				
			case SetOutAndBeat:
				this.beatFigure(mo.getNewFieldNumber(), mo.getFigure());
				break;
				
			default:
				throw new RuntimeException("Unbekannte Spielzugoption: " + mo.getType().toString());
		}
		this.board.drawBoard();

		Player nextPlayer = this.getPlayer(mo.getFigure());
		// bei einer Sechs darf er nochmal würfeln
		// als bei keiner sehcs nächsten spieler ermitteln
		if(mo.getThrownCubeNumber() != 6)
		{
			nextPlayer = this.getNextPlayer(nextPlayer);
		}
		
		this.nextMoveOption(nextPlayer);
		
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
		Field field = this.getFields().get(fieldNumber);
		Figure fieldFigure = field.getFigure();
		field.clear();
		Player player = this.getPlayer(fieldFigure);
		player.setFigureBack(fieldFigure);
		fieldFigure.set2StartPosition();
		//this.setFigure2Field(fieldFigure, fieldNumber);
		
		
		// setze Figur auf das Feld
		this.setFigure2Field(figure, fieldNumber);
		figure.setSteps(this.getSteps(this.getPlayer(figure).getOffset(),fieldNumber));
		
	}
	
	
	
	private int getSteps(int playerOffset,
						 int fieldNumber)
	{
		if(fieldNumber >= Gamehandler.fieldCount)
		{
			return fieldNumber;
		}
		
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
		return this.getFieldNumber(playerOffSet, figureSteps, false);
	}

	
	private int getFieldNumber(int playerOffSet,
							   int figureSteps,
							   boolean checkSteps)
	{
		// Prüfen ob man ins Haus läuft
		if(figureSteps >= Gamehandler.fieldCount)
		{
			if(figureSteps < 100)
			{
				int houseNumber = figureSteps - Gamehandler.fieldCount + 1;
				houseNumber += House.getHouseAdditionValue(playerOffSet);
				return houseNumber;		
			}
			
			if(!House.isValidHouseNumber(figureSteps))
			{
				if(checkSteps)
				{
					return errorField;
				}
				else
				{
					throw new RuntimeException("Ungültige Schrittanzahl!");
				}
			}
			
			return figureSteps;
		}
		
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
		throw new RuntimeException("function is not implemented!");
		//return false;
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
					Player p = (Player) this.getPlayers().values().toArray()[0];
					this.setCurrentPlayer(p);
					return p;
				}
				else
				{
					Player p = (Player) this.getPlayers().values().toArray()[i + 1]; 
					this.setCurrentPlayer(p);
					return p;
				}
			}
		}
		
		// option 'isCloseGameWhenPlayerWins' beachten
		
		throw new RuntimeException("Es wurde kein Spieler für den nächsten Spielzug gefunden!");
	}
	
	
	private void setCurrentPlayer(Player player)
	{
		for(Player p : this.getPlayers().values())
		{
			if(p.getPlayerColor() == player.getPlayerColor())
			{
				p.setActive(false);
			}
			else
			{
				p.setActive(true);
			}
		}
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
			int threwCube = this.board.getRoll(); //player.getValue().throwCube();
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
		Color[] colorArray = {Color.BLACK,Color.YELLOW,Color.GREEN,Color.RED };//Color of Players
				
		for (int i = 0; i < playerNames.length; i++)
		{
			Player player = new Player(colorArray[i],bIsComputer,iOffset,playerNames[i]);
			players.put(player.getPlayerColor().toString(), player);  // = new Player();
			iOffset += 10;
		}
	}
	

	
	private void createFields(){
		fields = new HashMap<Integer,Field>();
		Field.Type type;
		
		for (Integer i = 0; i < fieldCount; i++)
		{
			if (i % 10 == 0) 
			{ 
				type = Field.Type.START; 
			}
			else
			{
				type = Field.Type.NORMAL;
			}
			fields.put(i, new Field(type,i));
		}
	}
	
	private boolean canSetFigure2House(Player player,
			                           int fieldNumber)
	{
		// prüfen ob gültiges Feld
		if(fieldNumber >= Gamehandler.fieldCount && !House.isValidHouseNumber(fieldNumber))
		{
			return false;
		}
		
		
		// TODO: optimieren (returns)
		
		House house = player.getHouses().get(fieldNumber); 
		// prüfen ob das Feld im Haus frei ist
		boolean isValid = house.isFree();
		
		// prüfen ob in den vorherigen Feldern im Haus
		// eine Figur steht und ggf.
		// die Regel beachten...
		if(isValid && !this.options.isJumpInHouse())
		{
			for(House h : player.getHouses().values())
			{
				if(h.getNumber() > house.getNumber() && !h.isFree())
				{
					isValid = false;
					break;
				}
			}
		}
		
		// wenn alles ok
		// füge Spielzug hinzu
		return isValid;
	}
	
	private void setFigure2House(Player player,
								 Figure figure,
								 int houseNumber)
	{
		if(!this.canSetFigure2House(player, houseNumber))
		{
			throw new RuntimeException("Figur kann nicht in das Haus gestellt werden!");
		}
		
		
		
		// Figur von aktuellen Feld entfernen
		// wenn das aktuelle Feld ein Haus ist, anders vorgehen...
		if(figure.getSteps() >= Gamehandler.fieldCount)
		{
			player.clearHouse(figure);
		}
		else
		{
			this.clearField(figure);
		}

		House house = player.getHouses().get(houseNumber);
		figure.setSteps(houseNumber);
		house.setFigure(figure);
		board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird in das Haus gesetzt. (Nr.: '"+ String.valueOf(house.getNumber())+ "')");
		//board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird in das Haus gesetzt. (Nr.: '"+ String.valueOf(house.getNumber())+ "') von Spieler " + figure.getFigureColor().toString());

	}
	private void setFigure2Field(Figure figure,
			 					 int fieldNumber)
	{
		// wenn Flednummer ein Haus ist,
		// anders reagieren...
		Player player = this.getPlayer(figure);
		if(fieldNumber >= Gamehandler.fieldCount)
		{
			this.setFigure2House(player, figure, fieldNumber);
		}
		else
		{
			Field field = this.getFields().get(fieldNumber);
			if(! field.isFree())
			{
				throw new RuntimeException("Die Figur " + String.valueOf(figure.getNumber()+ " mit der Farbe " + figure.getFigureColor().toString() + " kann nicht auf das Feld " + field.getNumber() + " setzen, da es von der Figur " + String.valueOf(figure.getNumber()+ " mit der Farbe " + figure.getFigureColor().toString() +  "  besetzt ist.")));
			}
		
			// Figur von aktuellen Feld entfernen
			this.clearField(figure);
			
			// Figur auf neues Feld setzen
			field.setFigure(figure);
		
			if(field.getType() == Field.Type.START)
			{
				//board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Startfeld gesetzt. (Feld '"+ String.valueOf(field.getNumber())+ "') von Spieler " + figure.getFigureColor().toString());
				board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Startfeld gesetzt. (Feld '"+ String.valueOf(field.getNumber())+ "')");
			}
			else
			{
				//board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Feld " + String.valueOf(field.getNumber())+ " von Spieler " + figure.getFigureColor().toString() +" gesetzt");
				board.displayMessage("Figur " + String.valueOf(figure.getNumber()) + " wird auf das Feld " + String.valueOf(field.getNumber())+ " gesetzt.");
			}
		
		}
		

	}
	
	private void clearField(Figure figure)
	{
		this.getFields().get(this.getFieldNumber(this.getPlayer(figure).getOffset(), figure.getSteps())).clear();
	}
	

	@Override
	public void fieldClicked(int fieldNumber) 
	{
		try
		{
			// TODO: ? Priorität beachtet?
			if(this.waitForUserInput && fieldNumber != errorField)
			{
				if(this.isValidMoveOption(fieldNumber))
				{
					MoveOption mo = this.getMoveOption4FieldNumber(fieldNumber);
					
					// Führe den Zug aus
					this.handleMoveOption(mo);
							
					
					
					// prüfe ob Spieler nochmal würfeln darf
							
							
							
					// wenn nicht nächsten Spieler ermitteln und würfeln...
							
					
				}
				else
				{
					this.board.displayMessage("Ungültiger Spielzug!");
					this.board.displayMessage("Bite wählen Sie ein anderes Feld aus.");
				}
			}
			
		}
		catch (java.util.ConcurrentModificationException e)
		{
			System.out.println("Unbehandelte Ausnahme!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	private boolean isValidMoveOption(int fieldNumber)
	{
		for(MoveOption mo : this.moveOptions)
		{
			if(mo.getNewFieldNumber() == fieldNumber)
			{
				return true;
			}
		}
		return false;
	}
	
	private MoveOption getMoveOption4FieldNumber(int fieldNumber)
	{
		if (!this.isValidMoveOption(fieldNumber))
		{
			throw new RuntimeException("Es konnte kein Spielzug mit der Feldnummer gefunden werden!");
		}
		
		for(MoveOption mo : this.moveOptions)
		{
			// TODO: implementieren
			if(mo.getNewFieldNumber() == fieldNumber)
			{
				return mo;
			}
		}
		
		throw new RuntimeException("Unknown Error: Es konnte kein Spielzug mit der Feldnummer gefunden werden!");
	}

	@Override
	public void closeGame() {
		// TODO Auto-generated method stub
		this.board.dispose();
		this.board = null;
		this.options = null;
		this.players.clear();
		this.fields.clear();
		this.mainGui.show();
	}

	@Override
	public boolean cubeClicked(int thrownNumber) {
		// TODO: Prüfen ob man würfeln darf
		
		// wenn man nicht würfeln darf
		// reutrn false
		return false;
	}
	
	/**
	private void setFigure2Field(Figure figure,
								 int playerOffSet)
	{
		this.setFigure2Field(figure, getFieldNumber(playerOffSet, figure.getSteps()););
	}**/

	 

}
