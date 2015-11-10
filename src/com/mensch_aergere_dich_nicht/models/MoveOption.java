package com.mensch_aergere_dich_nicht.models;


public class MoveOption {
	
	private Figure figure;
	private eType type;
	
	
	
	public enum eType{
		CanBeat, Set;
	}
	
	public MoveOption(Figure f,
			eType type)
	{
		this.figure = f;
		this.type = type;
	}

	
	public Figure getFigure() {
		return figure;
	}

	public eType getType() {
		return type;
	}


	
}