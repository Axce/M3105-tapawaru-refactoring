package fr.iut.tapawaru.map;

import fr.iut.tapawaru.gui.BottomPanel;
import fr.iut.tapawaru.gui.MapGUI;
import fr.iut.tapawaru.team.TeamController;

/**
 * Map hosting the game.
 * It's editable with the Terra' Spells.
 * X-axis is xSize
 * Y-axis is ySize
 * [xSize][ySize] 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Map
{
	/* ****************************************ATTRIBUTS******************************************** */
				/* *****************************Default Map size******************* */	
	/** Map's default xSize. */
	public static final int  DEFAULT_X_SIZE = 15;
	
	/** Map's default ySize. */
	public static final int DEFAULT_Y_SIZE = 10;
	
				/* *****************************Map size******************* */
	/** Map's xSize. */
	private final int xSize;
	
	/** Map's ySize. */
	private final int ySize;
	
				/* *****************************Maps******************* */
	/** Glyphs'grid. */
	private final Glyph[][] glyph;
	
	/** Cells'grid. */
	private final Cell[][] map;

				/* *****************************Utility******************* */
	/** Map's TeamController. */
	private TeamController teamController;
	
	/** Map's selectedCell. */
	private Cell selectedCell;

	/** Map's MapGUI. */
	private MapGUI mapGui;

	
	
	/* ****************************************CONSTRUCTORS******************************************** */
	/**
	 * Constructor for the Map.
	 */
	public Map()
	{ 
		this.selectedCell=null;
		this.xSize=DEFAULT_X_SIZE;
		this.ySize=DEFAULT_Y_SIZE;
		this.map = new Cell[this.xSize][this.ySize];
		
		for(int lineNumber =0; lineNumber<this.xSize;lineNumber++)
		{
			for(int columnNumber = 0; columnNumber < this.ySize; columnNumber++)
			{
				this.map[lineNumber][columnNumber]= new Cell(lineNumber,columnNumber);
			}
		}	
		this.glyph = new Glyph[this.xSize+1][this.ySize+1];
		for(int lineNumber =0; lineNumber< this.xSize+1; lineNumber++)
		{
			for(int columnNumber = 0; columnNumber < this.ySize+1; columnNumber++)
			{
				this.glyph[lineNumber][columnNumber] = new Glyph(lineNumber,columnNumber);
			}
		}
		this.teamController = new TeamController(this);
		
		this.selectedCell=null;
		
	}

	/* ****************************************GETTERS / SETTERS *************************************** */
				/* *****************************Sizes******************* */
	/**
	 * @return this.xSize
	 */
	public int getXSize()
	{
		return this.xSize;
	}
	
	/**
	 * @return this.ySize
	 */
	public int getYSize()
	{
		return this.ySize;
	}
	
				/* *****************************Cells******************* */
		/**
	 * @param pos Requested cell's position.
	 * @return Cell at given position.
	 */
	public Cell getCell(CellPosition pos)
	{
		return this.map[pos.getPositionX()][pos.getPositionY()];
	}
	
	/**
	 * Set the given Cell at the given CellPosition.
	 * @param pos Destination glyph's position.
	 * @param cell Cell to set at the position.
	 */
	public void setCell(CellPosition pos, Cell cell)
	{
		this.map[pos.getPositionX()][pos.getPositionY()] = cell;		
	}
				
				/* *****************************Glyphs******************* */
	/**
	 * @param pos Requested glyph's position.
	 * @return Glyph at given position.
	 */
	public Glyph getGlyph(GlyphPosition pos)
	{
		return this.glyph[pos.getPositionX()][pos.getPositionY()];
	}

	/**
	 * Set the given Glyph at the given GlyphPosition.
	 * @param pos Destination glyph's position.
	 * @param glyph Glyph to set at the position.
	 */
	public void setGlyph(GlyphPosition pos, Glyph glyph)
	{
		this.glyph[pos.getPositionX()][pos.getPositionY()] = glyph;		
	}
				
	/**
	 * @param pos Glyph's position.
	 * @return type of the glyph at the given GlyphPosition.
	 */
	public TypeGlyph getTypeOfGlyph(GlyphPosition pos)
	{
		return this.glyph[pos.getPositionX()][pos.getPositionY()].getTypeGlyph();
	}
	
				/* *****************************Selected Cell******************* */
	/**
	 * @return this.selectedCell
	 */
	public Cell getSelectedCell()
	{
		return this.selectedCell;
	}
	
	/**
	 * Set the Cell which is currently selected.
	 * @param cell Cell which is selected.
	 */
	public void setSelectedCell(Cell cell)
	{
		this.selectedCell = cell;
	}
				
				/* *****************************Map GUI******************* */
	/**
	 * Set the current MapGUI.
	 * @param mapGui mapGUI to use.
	 */
	public void setMapGui(MapGUI mapGui)
	{
		this.mapGui = mapGui;
	}
	
	/**
	 * @return this.mapGui
	 */
	public MapGUI getMapGui( )
	{
		return this.mapGui ;
	}
				
				/* *****************************Character******************* */
	/**
	 * @param posX X position of the character
	 * @param posY Y position of the character
	 * @return this.this.map[posX][posY].getCharacter();
	 */
	public fr.iut.tapawaru.team.Character getCharacter(int posX, int posY)
	{
		return this.map[posX][posY].getCharacter();
	}
	
	/**
	 * Set the give Character at the given CellPosition
	 * @param character Character we want to put.
	 * @param position Position where we want it to be.
	 */
	public void putCharacter(fr.iut.tapawaru.team.Character character,CellPosition position)
	{
		// TODO EXCEPTIONS
		this.map[position.getPositionX()][position.getPositionY()].setCharacter(character);
		if(!(character ==null))
			character.setCell(this.getCell(position));
	}
	
				/* *****************************Utility******************* */
	/**
	 * @return this.mapGui.getBottomPanel()
	 */
	public BottomPanel getBottomPanel()
	{
		return this.mapGui.getBottomPanel();
	}

	/**
	 * @return this.teamController
	 */
	public TeamController getTeamController()
	{
		return this.teamController;
	}
	
	/* ****************************************UTILITY *************************************** */
	/**
	 * Move the give Character to the given CellPosition.
	 * @param character Character we want to move.
	 * @param position Destination of the character.
	 */
	public void moveCharacter(fr.iut.tapawaru.team.Character character,CellPosition position)
	{
		this.map[character.getCellTraveled().getPosition().getPositionX()][character.getCellTraveled().getPosition().getPositionY()].setCharacter(null);
		this.map[position.getPositionX()][position.getPositionY()].setCharacter(character);
		character.setCell(this.getCell(position));		
	}
	
	/**
	 * ToString override
	 * Print the map with cells and glyphs
	 */
	@Override
	public String toString()
	{
		StringBuilder strMap = new StringBuilder();
		
		for (int ySize = 0; ySize < this.ySize+1; ySize++)
		{
			strMap.append(this.glyph[0][ySize]+"   " );
		}
		strMap.append("\n");
		for (int xSize = 0; xSize < this.xSize; xSize++)
		{
			for (int ySize = 0; ySize < this.ySize; ySize++)
			{
				strMap.append(" " + this.map[xSize][ySize]);
			}
			strMap.append( "\n");
			for (int ySize = 0; ySize < this.ySize+1; ySize++)
			{
				strMap.append(this.glyph[xSize+1][ySize]+"   " );
			}
			strMap.append( "\n");

		}
		return strMap.toString();
	}

}


