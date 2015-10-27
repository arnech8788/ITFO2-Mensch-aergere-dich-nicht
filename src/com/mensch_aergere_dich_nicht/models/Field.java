package com.mensch_aergere_dich_nicht.models;

public class Field extends FieldBase {
	
	public enum Type {
        START, NORMAL
    }
	
	private Type type;
	
	

	public Field(Type type,
				 int number)
	{
		this.type = type;
		super.setNumber(number);
	}
	
	
	public Type getType()
	{
		return this.type;
	}
	
	
}
