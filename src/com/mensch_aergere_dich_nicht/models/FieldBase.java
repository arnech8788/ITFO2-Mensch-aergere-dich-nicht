package com.mensch_aergere_dich_nicht.models;

public abstract class FieldBase {
	
	private Figure figure;
	private int number;

	/**protected FieldBase()
	{
		
	}**/
	
	public Figure getFigure() {
		return figure;
	}
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	public int getNumber() {
		return number;
	}
	protected void setNumber(int number){
		this.number = number;
	}
	
	public void clear()
	{
		this.figure = null;
	}
	
	public boolean isFree()
	{
		return figure == null;
	}

}
