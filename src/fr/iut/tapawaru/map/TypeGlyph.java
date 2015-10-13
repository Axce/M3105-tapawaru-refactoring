package fr.iut.tapawaru.map;


/**
 * All Types of Glyphs.
 * Enumeration of all the different types of glyphs.
 * TODO complete javadoc?
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public enum TypeGlyph {
		
	FIRE ("F"),
	EARTH ("E"),
	WATER ("W"),
	AIR ("A"),
	THUNDER ("T"),
	ICE ("I"),
	HOLY ("H"),
	DARK ("D"); 
	
	/* ****************************************ATTRIBUTS******************************************** */
				/* *****************************Number of type used in the map******************* */
	/** The number of type available. */
    public final static int NUMBER_OF_TYPE = 4; 
	
				/* *****************************Utility******************* */
	/** Displays Glyph's type. */
    private final String display;
    
    
	/* ****************************************CONSTRUCTORS******************************************** */	
    /** 
     * Constructor of the Glyph's type. 
     * @param disp: display of the Glyph's type.
     */
    TypeGlyph(final String disp) 
    {
        display = disp;
    }

	/* ****************************************UTILITY******************************************** */	
    /**
     * Return one TypeGlyph at random.
     * @return TypeGlyph
     */
    public final static TypeGlyph getRandomType()
    {
    	int type = (int) (Math.random() * NUMBER_OF_TYPE );
    	
    	switch (type)
		{
		case 0:
			return TypeGlyph.FIRE;
		case 1:
			return TypeGlyph.AIR;
		case 2:
			return TypeGlyph.ICE;
		case 3:
			return TypeGlyph.HOLY;
		case 4:
			return TypeGlyph.THUNDER;
		case 5:
			return TypeGlyph.WATER;
		case 6:
			return TypeGlyph.EARTH;
		case 7:
			return TypeGlyph.DARK;
		}
		return null;
    }

    /**
     * @return Text version type of glyph.
     */
    @Override
    public String toString() 
    {
    	return display;
    }
   
}
