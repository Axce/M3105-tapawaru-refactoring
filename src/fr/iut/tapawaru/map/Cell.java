package fr.iut.tapawaru.map;

/**
 * Practicable Cell forming the Map.
 * One character can walk on Cell.
 * One cell surrounded by 4 Glyphs.
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Cell extends MapElement
{
	/* ****************************************ATTRIBUTS******************************************** */
				/* *****************************Utility******************* */
	/** Cell's position on Map. */
	private CellPosition position;
	
	/** Cell's Character. */
	private fr.iut.tapawaru.team.Character character;
	
	/** Is the cell Selected. */
	private boolean isSelected;
	
	/* ****************************************CONSTRUCTORS******************************************** */
	/**
	 * Constructor for the Cell.
	 * @param positionX: Cell's X position.
	 * @param positionY: Cell's Y position.
	 */
	public Cell(int positionX, int positionY)
	{
		this.position = new CellPosition(positionX, positionY);
		this.character = null;
		this.isSelected = false;
	}

	
	/* ****************************************GETTERS / SETTERS *************************************** */
				/* *****************************Positions******************* */
	/**
	 * Get position from current Cell.
	 * @return position  : current position.
	 */
	public CellPosition getPosition() 
	{
		return this.position;
	}
	
	/**
	 * Sets Cell's position.
	 * @param int position: future Cell's position.
	 */
	public void setPosition(CellPosition position) 
	{
		this.position = position;
	}
	
	/**
	 * Sets Cell's X position.
	 * @param int positionX: future Cell's X position.
	 */
	public void setPositionX(int positionX) 
	{
		this.position.setPositionX(positionX);
	}
	
	/**
	 * Sets Cell's Y position.
	 * @param int positionY : future Cell's Y position.
	 */
	public void setPositionY(int positionY) 
	{
		this.position.setPositionY(positionY);
	}

				/* *****************************Character******************* */
	/**
	 * @return this.character
	 */
	public fr.iut.tapawaru.team.Character getCharacter()
	{
		return this.character;
	}

	/**
	 * Set the cell's Character.
	 * @param character new cell's Character.
	 */
	public void setCharacter(fr.iut.tapawaru.team.Character character)
	{
		this.character = character;	
	}
				/* *****************************Selected******************* */
	/**
	 * @return this.isSelected
	 */
	public boolean isSelected()
	{
		return this.isSelected;
	}
	
	/**
	 * Set if this cell is Selected.
	 * @param state new state of the cell.
	 */
	public void setIsSelected(boolean state)
	{
		this.isSelected = state;
	}

	/* ****************************************UTILITY******************************************** */
	/**
	 * Return "C".
	 * Used in the consol display.
	 */
	@Override
	public String toString()
	{
		return " C ";
	}
		
}
