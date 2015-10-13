package fr.iut.tapawaru.map;

public class GlyphPosition
{
	/* ****************************************ATTRIBUTS******************************************** */
				/* *****************************Position******************* */
	/** Glyph's X position on Map. */
	private int positionX;
	
	/** Glyph's Y position on Map. */
	private int positionY;

	/* ****************************************CONSTRUCTORS******************************************** */
	/**
	 * Constructor of the Glyph position
	 * @param positionX
	 * @param positionY
	 */
	public GlyphPosition(int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
	}

	/* ****************************************GETTERS / SETTERS *************************************** */
				/* *****************************Position******************* */
	/**
	 * @return this.positionX
	 */
	public int getPositionX()
	{
		return this.positionX;
	}

	/**
	 * Set the X Position. 
	 * @param positionX new X position.
	 */
	public void setPositionX(int positionX)
	{
		this.positionX = positionX;
	}

	/**
	 * @return this.positionY
	 */
	public int getPositionY()
	{
		return this.positionY;
	}

	/**
	 * Set the Y Position. 
	 * @param positionY new Y position.
	 */
	public void setPositionY(int positionY)
	{
		this.positionY = positionY;
	}
}
