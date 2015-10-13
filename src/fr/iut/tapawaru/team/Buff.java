package fr.iut.tapawaru.team;

/**
 * All buff's type.
 * Enumeration of all the different type ofbuff available.
 * 
 * @authors jpelloux and cearda.
 */
public enum Buff
{
	NORMAL("N"), 
	BURNING("F"), // -1 hp per turn
	FREEZING("I"), // can't move
	DEAD("D");

	/* ****************************************ATTRIBUTS******************************************** */
	/** Displays Glyph's type. */
	private final String display;

	/* ****************************************CONSTRUCTORS******************************************** */	
    /** 
     * Constructor of the Glyph's type. 
     * @param disp: display of the Glyph's type.
     */
	Buff(final String disp)
	{
		display = disp;
	}

	/* ****************************************UTILITY******************************************** */	
	/**
	 * @return this.display
	 */
	@Override
	public String toString()
	{
		return display;
	}

}
