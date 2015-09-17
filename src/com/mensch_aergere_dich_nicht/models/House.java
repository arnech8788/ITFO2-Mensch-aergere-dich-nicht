package com.mensch_aergere_dich_nicht.models;

public class House extends FieldBase {

	public House(int number)
	{
		if(number < 1 || number > 4)
		{
			throw new RuntimeException("Invalid Housenumber!");
		}
		super.setNumber(number);
	}
	
}
