package com.mensch_aergere_dich_nicht.models;


public class MoveOption {
	
	private Figure figure;
	private eType type;
	private int newFieldNumber;
	private int thrownCubeNumber;
	
	public enum eType{
		CanBeat, Set;
	}
	
	public MoveOption(Figure f,
			eType type,
			int newFieldNumber,
			int thrownCubeNumber)
	{
		this.figure = f;
		this.type = type;
		this.newFieldNumber = newFieldNumber;
		this.thrownCubeNumber = thrownCubeNumber;
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
	
	public int getThrownCubeNumber()
	{
		return thrownCubeNumber;
	}

	
}