package com.mensch_aergere_dich_nicht.models;

public class House extends FieldBase {

	public House(int number)
	{
		if(!House.isValidHouseNumber(number))
		{
			throw new RuntimeException("Invalid Housenumber!");
		}
		super.setNumber(number);
	}
	
	
	public static int getHouseAdditionValue(int playerOffset)
	{
		switch(playerOffset)
		{
			case 0:
				return 100;
				
			case 10:
				return 200;
				
			case 20:
				return 300;
				
			case 30:
				return 400;
				
			default:
				throw new RuntimeException("Nicht unterstützter Offset!");
		}
	}
	
	public static boolean isValidHouseNumber(int houseNumber)
	{
		int second = Integer.valueOf(new Integer(houseNumber).toString().substring(1,2));
		if( second != 0)
		{
			return false;
		}
		
		int temp = Integer.valueOf(new Integer(houseNumber).toString().substring(2));
		if(temp < 1 || temp > 4)
		{
			return false;
		}
		return true;
	}
	
}
