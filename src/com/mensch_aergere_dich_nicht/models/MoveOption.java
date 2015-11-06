package com.mensch_aergere_dich_nicht.models;
import com.mensch_aergere_dich_nicht.models.*;


public class MoveOption {
	
	private Figure figure;
	private Type type;
	
	
	
	public enum Type{
		CanBeat, Set;
	}
	
	public MoveOption(Figure f,
					  Type type)
	{
		this.figure = f;
		this.type = type;
	}

	
	public Figure getFigure() {
		return figure;
	}

	public Type getType() {
		return type;
	}


	
}