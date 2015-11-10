package com.mensch_aergere_dich_nicht.models;


public class MoveOption {
	
	private Figure figure;
	private eType type;
	private int newFieldNumber;
	
	
	public enum eType{
		CanBeat, Set;
	}
	
	public MoveOption(Figure f,
			eType type,
			int newFieldNumber)
	{
		this.figure = f;
		this.type = type;
		this.newFieldNumber = newFieldNumber;
	}

	
	public int getNewFieldNumber() {
		return newFieldNumber;
	}

	public Figure getFigure() {
		return figure;
	}

	public eType getType() {
		return type;
	}


	
}