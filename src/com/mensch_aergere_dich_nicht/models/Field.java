package com.mensch_aergere_dich_nicht.models;

public class Field {
	
	public enum Type {
        HOUSE, START, NORMAL, TARGET
    }
	
	private Figure figure;
	private java.awt.Color playerColor;
	private int number;
	private Type type;
	
	
	public Field(Type type)
	{
		setType(type);
	}
	
	public Field(Type type,
				 int number)
	{
		this.type = type;
		this.number = number;
	}
	
	
	
	
	public Figure getFigure() {
		return figure;
	}
	public void setFigure(Figure figure, 
						  java.awt.Color playerColor) {
		this.figure = figure;
		this.playerColor = playerColor;
	}
	
	public int getNumber() {
		return number;
	}
	
	public java.awt.Color getPlayerColor()
	{
		return this.playerColor;
	}
	
	public Type getType()
	{
		return this.type;
	}
	private void setType(Type type)
	{
		switch (type) {
        case HOUSE:
            this.Field(type, -1);
            break;
                
        case START:
            System.out.println("Fridays are better.");
            break;
                    
        default:
            System.out.println("Midweek days are so-so.");
            break;
		}
	}
	
	public boolean isFree()
	{
		return figure == null;
	}
	
}
