package fr.iut.tapawaru.map;

/**
 * Glyph constituting the Map.
 * Exists [unknown number] different types of Glyphs.
 * One Map's Cell is surrounded by 4 (random) Glyphs.
 * The type of the thrown Attack' Spell depends on adjacent Glyphs to the thrower.
 * Choice of a spell's type: the type from a random Glyph amongst the 4, adjacent to the thrower.
 * Choice of a spell's power: power multiplied by the number of Glyphs of the same type, adjacent to the thrower.
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Glyph extends MapElement
{
	/* ****************************************ATTRIBUTS******************************************** */
	/** Glyph's position on Map. */
	private GlyphPosition glyphPosition;
	
	/** Glyph's type. */
	private TypeGlyph typeGlyph;

	/* ****************************************CONSTRUCTORS******************************************** */
	/**
	 * Constructor for the Glyph.
	 * The type of the glyph is random.
	 * @param lineNumber
	 * @param columnNumber
	 */
	public Glyph(int lineNumber, int columnNumber) 
	{
		this.glyphPosition=new GlyphPosition(lineNumber,columnNumber);
		this.typeGlyph= TypeGlyph.getRandomType();
	}
	
	/* ****************************************GETTERS / SETTERS *************************************** */
				/* *****************************Position******************* */
	/**
	 * Gets X position from current Glyph.
	 * @return position x : current X position.
	 */
	public GlyphPosition getPositionGlyph() {
		return this.glyphPosition;
	}
	
	/**
	 * Set the glyph Position
	 * @param glyphPosition new glyph position.
	 */
	public void setGlyphPosition(GlyphPosition glyphPosition)
	{
		this.glyphPosition = glyphPosition;
	}

	/**
	 * Sets Glyph's X position.
	 * @param int positionGlyphX: future Glyph's X position.
	 */
	public void setPositionGlyphX(int positionGlyphX) {
		this.glyphPosition.setPositionX(positionGlyphX);
	}
	
	/**
	 * Sets Glyph's Y position.
	 * @param int positionGlyphY: future Glyph's Y position.
	 */
	public void setPositionGlyphY(int positionGlyphY) {
		this.glyphPosition.setPositionX(positionGlyphY);
	}
			
				/* *****************************Glyph******************* */
	/**
	 * Gets type from current Glyph.
	 * @return typeGlyph : current type.
	 */
	public TypeGlyph getTypeGlyph() {
		return this.typeGlyph;
	}

	/**
	 * Sets Glyph's type position.
	 * @param TypeGlyph typeGlyph: future Glyph's type.
	 */
	public void setTypeGlyph(TypeGlyph typeGlyph) {
		this.typeGlyph = typeGlyph;
	}

	/* ****************************************UTILITY*************************************** */
	/**
	 * Used in consol display.
	 * @return The string type of the glyph.
	 */
	@Override
	public String toString()
	{
		return typeGlyph.toString();
	}	
}
