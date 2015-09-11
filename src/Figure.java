/*
 * Autor: OK
 * Datum: 11.09.2015
 * Beschreibung: Klasse Figur
 */
public class Figure {
	
	int iSteps;
	/*
	 * -1 = Figur ist im Start
	 * 0 - X = Anzahl der Felder die die FIgur zurückgelegt hat
	 */
	
	public Figure()
	{
		iSteps = -1; // Figur ist zu Beginn im Start
	}
	
	public int getSteps()
	{
		return iSteps;
	}
	
	public void setSteps(int iSteps)
	{
		this.iSteps = iSteps;
	}

}
