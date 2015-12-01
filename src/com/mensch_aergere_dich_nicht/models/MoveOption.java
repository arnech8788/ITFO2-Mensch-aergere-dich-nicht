package com.mensch_aergere_dich_nicht.models;

import java.util.*;


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
	
	public enum ePriority{
		ForeignStartField(0), 
		Normal(1), 
		OwnStartField(2), 
		InHouse(4), 
		StrikeForPull(8), 
		SixFigureOut(16);
		
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
			temp += item.getPriorityValue();
		}
		return temp;
	}
	
	@Override
	public int compareTo(MoveOption arg0) {
		return this.getPrioritySize() - arg0.getPrioritySize();
	}
}