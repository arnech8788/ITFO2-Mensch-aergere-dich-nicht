package com.mensch_aergere_dich_nicht.models;

import java.util.*;

import com.mensch_aergere_dich_nicht.models.MoveOption.ePriority;


public class MoveOption implements Comparable<MoveOption> 
{
	
	private Figure figure;
	private eType type;
	private int newFieldNumber;
	private int thrownCubeNumber;
	//private ePriority priorityType;
	private Set<ePriority> priorityType;
	
	public enum eType{
		Unknown, CanBeat, Set, SetOut, SetOutAndBeat;
	}
	
	/****/
	public enum ePriority{
		ForeignStartField(1<<0), Normal(1<<1), OwnStartField(1<<2), InHouse(1<<4), StrikeForPull(1<<6), SixFigureOut(1<<8);
		
		private final long priorityValue;
		
		ePriority(long priorityValue)
		{
			this.priorityValue = priorityValue;
		}
		
		public long getPriorityValue()
		{
			return priorityValue;
		}
	}
	/****/
	
	/**public enum ePriority 
	{
	    private int id;

	    HAPPY(1), SAD(2), CALM(4), SLEEPY(8), OPTIMISTIC(16), PENSIVE(32), ENERGETIC(64);

	    Mood( int id ) {
	        this.id = id;
	    }
	}
	/**public enum ePriority{
		Normal, StrikeForPull, SixFigureOut;
	}**/

	
	public MoveOption(Figure f,
			eType type,
			int newFieldNumber,
			int thrownCubeNumber,
			Set<ePriority> priorityType)
	{
		this.figure = f;
		this.type = type;
		this.newFieldNumber = newFieldNumber;
		this.thrownCubeNumber = thrownCubeNumber;
		this.priorityType = priorityType;
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

	public Set<ePriority> getPriorityType()
	{
		return priorityType;
	}
	
	
	public int getPrioritySize()
	{
		int temp = 0;
		for(ePriority item : this.getPriorityType())
		{
			temp += item.ordinal();
		}
		return temp;
	}
	
	@Override
	public int compareTo(MoveOption arg0) {
		return this.getPrioritySize() - arg0.getPrioritySize();
	}
}