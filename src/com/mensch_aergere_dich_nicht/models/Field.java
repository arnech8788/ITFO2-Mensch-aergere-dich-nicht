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
		if(type == Type.START)
		{
			super.setNumber(0);
		}
		else{
			super.setNumber(number);
		}
	}
	
	
	public Type getType()
	{
		return this.type;
	}
	
	
}
