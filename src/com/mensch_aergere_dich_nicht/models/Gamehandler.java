package com.mensch_aergere_dich_nicht.models;

import java.util.HashMap;
import java.util.Map; 
import java.awt.Color;

import com.mensch_aergere_dich_nicht.models.Field.Type;

public class Gamehandler {
	
	Map<String,Field> Fields;
	Map<String,Player> Players;
	
	
	public Gamehandler(){
		Fields = new HashMap<String,Field>();
		Players = new HashMap<String,Player>();
	}
	
	public Map<String, Field> getFields() {
		return Fields;
	}

	public void setFields(Map<String, Field> fields) {
		Fields = fields;
	}

	public Map<String, Player> getPlayers() {
		return Players;
	}

	public void setPlayers(Map<String, Player> players) {
		Players = players;
	}

	
	public void createPlayers(){
		int iPlayerCount = 4;//Count of Players
		int iOffset = 0;// Offset of Fieldposition
		boolean bIsComputer = false; // Is Computer?
		String[] sPlayerName = {"Gernhart Reinholzen","Lassmiranda den si Villia","Timo Beil","Anne Theke"};//Playernames
		Color[] colorArray = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW };//Color of Players
				
		for (int i = 0; i < iPlayerCount; i++)
		{
			Player player = new Player(colorArray[i],bIsComputer,iOffset,sPlayerName[i]);
			Players.put(player.getPlayerColor().toString(), player);  // = new Player();
			iOffset += 10;
		}
	}
	
	public void createFields(){
		int iFieldCount = 40; //Number of Fields ( without House )
		Type type = Type.START;
		
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
			Field field = new Field(type,i);
			Fields.put(i.toString(), field);
		}
	}
	
	public static void main(String args[])
	{
		Gamehandler gh = new Gamehandler();
		gh.createFields();
		gh.createPlayers();
	}
	
	 

}
