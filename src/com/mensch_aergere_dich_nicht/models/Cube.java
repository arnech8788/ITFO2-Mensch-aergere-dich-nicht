package com.mensch_aergere_dich_nicht.models;

public class Cube {
	
	private int cubeNumber;
	
	public Cube(){
		
	}
	
	public int throwCube(){
		cubeNumber = (int)((Math.random()) * 6 + 1);
		return cubeNumber;
	}
	
	public int getCubeNumber(){
		return cubeNumber;
	}
}
