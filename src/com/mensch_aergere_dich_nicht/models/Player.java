package com.mensch_aergere_dich_nicht.models;

import java.awt.Color;

public class Player {
	
	private Color playerColor;
	private int offset;
	private boolean isComputer;
	private String playerName;
	private Figure figures[] = new Figure[4];
	
	/*
	 * Creates a player with a color, offset and name
	 * Creates 4 figures with player color
	 */
	
	public Player(Color playerColor, boolean isComputer, int offset, String playerName){
		
		this.playerColor = playerColor;
		this.isComputer = isComputer;
		this.offset = offset;
		this.playerName = playerName;
		
		
		//create 4 figures
		for(int i = 0 ; i < 4 ; i++){
			Figure f = new Figure(playerColor);
			figures[i] = f;
		}
	}
	
	public int throwCube(){
		return (int)((Math.random())*6+1);
	}
	
	public void setPlayerFigure(Figure f, int steps){
		f.setSteps(steps);
	}
	
	public void setFigureOut(Figure f){
		f.setSteps(0);
	}
	
	public void setFigureBack(Figure f){
		f.setSteps(-1);
	}
	
	public Color getPlayerColor(){
		return playerColor;
	}
	
	public void setPlayerName(String name){
		playerName = name;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public boolean isComputer(){
		return isComputer;
	}
	
	public int getOffset(){
		return offset;
	}
	
	public Figure[] getFigures(){
		return figures;
	}
}
