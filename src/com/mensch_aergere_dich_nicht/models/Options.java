package com.mensch_aergere_dich_nicht.models;

public class Options {


	private boolean closeGameWhenPlayerWins;
	private boolean jumpInHouse;
	private boolean strikeForPull;
	private boolean sixFigureOut;
	
	public Options()
	{
		
	}
	
	
	public boolean isCloseGameWhenPlayerWins() {
		return closeGameWhenPlayerWins;
	}


	public void setCloseGameWhenPlayerWins(boolean closeGameWhenPlayerWins) {
		this.closeGameWhenPlayerWins = closeGameWhenPlayerWins;
	}


	public boolean isJumpInHouse() {
		return jumpInHouse;
	}


	public void setJumpInHouse(boolean jumpInHouse) {
		this.jumpInHouse = jumpInHouse;
	}


	public boolean isStrikeForPull() {
		return strikeForPull;
	}


	public void setStrikeForPull(boolean strikeForPull) {
		this.strikeForPull = strikeForPull;
	}


	public boolean isSixFigureOut() {
		return sixFigureOut;
	}


	public void setSixFigureOut(boolean sixFigureOut) {
		this.sixFigureOut = sixFigureOut;
	}


	

	
}
