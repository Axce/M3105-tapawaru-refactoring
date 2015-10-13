package fr.iut.tapawaru.team;

/**
 * All team's color.
 * Enumeration of all the different team's color.
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public enum TeamColor {
	
	RED ("red"),
	BLUE ("blue");

	/* ****************************************ATTRIBUTS******************************************** */	
	/** Displays Glyph's type. */
    private final String display;
    
	/* ****************************************CONSTRUCTORS******************************************** */
    /** 
     * Constructor of the Glyph's type. 
     * @param disp: display of the Glyph's type.
     */
    TeamColor(final String disp) 
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
