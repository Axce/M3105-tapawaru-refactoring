package fr.iut.tapawaru.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.iut.tapawaru.map.Cell;
import fr.iut.tapawaru.map.CellPosition;
import fr.iut.tapawaru.map.GlyphPosition;
import fr.iut.tapawaru.map.Map;
import fr.iut.tapawaru.song.Audio;
import fr.iut.tapawaru.team.Buff;
import fr.iut.tapawaru.map.TypeGlyph;

/**
 * Gui manager of the top part of the mainFrame. Here we can see the map with
 * glyph and cell , players , spell's animation...
 * 
 * @author jpelloux
 *
 */
public class MapGUI extends JPanel implements MouseListener
{
	/*
	 * ****************************************ATTRIBUTS*************************
	 * *******************
	 */
	/* *****************************Images size******************* */
	/** Default size of a cell picture. */
	private static final int DEFAULT_OCTO_SIZE = 50;

	/* ***************************** Cell Images******************* */
	/** The default cell image */
	private Image octo;

	/** The cell image for when the cell is Selected */
	private Image octoSelected;

	/* ***************************** Glyph Images******************* */
	/** Fire Glyph image. */
	private Image glyphFire;

	/** Air Glyph image. */
	private Image glyphAir;

	/** Earth Glyph image. */
	private Image glyphEarth;

	/** Ice Glyph image. */
	private Image glyphIce;

	/** Thunder Glyph image. */
	private Image glyphThunder;

	/** Water Glyph image. */
	private Image glyphWater;

	/** Holy Glyph image. */
	private Image glyphHoly;

	/** Dark Glyph image. */
	private Image glyphDark;

	/** Border of each glyph */
	private Image glyphPattern;

	/* ***************************** Buff Images******************* */
	/** Fire buff Image. */
	private Image buffBurning;

	/** Ice buff Image. */
	private Image buffFreezing;

	/* *****************************Utility******************* */
	/** The map we are displaying. */
	private Map map;

	/** The cell which is actually selected. */
	private Cell selectedCell;

	/** The BottomPanel of the MainFrame. */
	private BottomPanel botPanel;

	/** Selected Charater's position. */
	private CellPosition selectedCharacterPosition;

	/*
	 * ****************************************CONSTRUCTORS**********************
	 * **********************
	 */
	/**
	 * Constructor of MapGui. Mainly init the glyph's ,buff's pictures and
	 * cell's pictures.
	 * 
	 * @param map
	 *            the Map we want to display.
	 */
	public MapGUI(Map map)
	{
		try
		{
			this.octo = ImageIO.read(new File("img/octo.png"));
			this.octoSelected = ImageIO.read(new File("img/octoSelected.png"));

			this.glyphFire = ImageIO.read(new File("img/glyph/glyphFire.png"));
			this.glyphAir = ImageIO.read(new File("img/glyph/glyphAir.png"));
			this.glyphEarth = ImageIO.read(new File("img/glyph/glyphEarth.png"));
			this.glyphIce = ImageIO.read(new File("img/glyph/glyphIce.png"));
			this.glyphThunder = ImageIO.read(new File("img/glyph/glyphThunder.png"));
			this.glyphWater = ImageIO.read(new File("img/glyph/glyphWater.png"));
			this.glyphHoly = ImageIO.read(new File("img/glyph/glyphHoly.png"));
			this.glyphDark = ImageIO.read(new File("img/glyph/glyphDark.png"));
			this.glyphPattern = ImageIO.read(new File("img/glyph/glyphPattern.png"));

			this.buffBurning = ImageIO.read(new File("img/perso/buffBurning.png"));
			this.buffFreezing = ImageIO.read(new File("img/perso/buffFreezing.png"));

			this.addMouseListener(this);

			this.map = map;
			this.botPanel = null;
			this.selectedCell = this.map.getSelectedCell();
			this.selectedCharacterPosition = null;
			this.map.setMapGui(this);
		} catch (IOException e)
		{
			e.printStackTrace();

		}

	}

	/*
	 *  *****************************************GETTERS /
	 * SETTERS***************************************
	 */
	/* *****************************BottomPanel******************* */
	/**
	 * @return this.botPanel
	 */
	public BottomPanel getBottomPanel()
	{
		return this.botPanel;
	}

	/**
	 * Set the BotttomPanel linked with this MapGUI.
	 * 
	 * @param botPanel
	 *            the MainFrame BottomPanel.
	 */
	public void addBotPanel(BottomPanel botPanel)
	{
		this.botPanel = botPanel;
		this.addKeyListener(this.botPanel);
	}

	/* *****************************Utility******************* */
	/**
	 * Set the selected character position.
	 * 
	 * @param position
	 *            new selected character position.
	 */
	public void setSelectedCharacterPosition(CellPosition position)
	{
		if (!(this.selectedCharacterPosition == null))
			printCharacter(this.getGraphics(), this.selectedCharacterPosition.getPositionX(), this.selectedCharacterPosition.getPositionY());
		this.selectedCharacterPosition = position;
	}

	/**
	 * @return The map size.
	 */
	public int[] guiMapSize()
	{
		int[] mapSize = new int[2];
		mapSize[0] = this.map.getXSize() * DEFAULT_OCTO_SIZE;
		mapSize[1] = this.map.getYSize() * DEFAULT_OCTO_SIZE;
		return mapSize;
	}

	/*
	 * ****************************************MAP DISPLAY
	 * TOOLS********************************************
	 */
	/* *****************************Cells******************* */
	/**
	 * Paint given position with a default cell but a "string" path character
	 * picture.
	 * 
	 * @param position
	 *            Position's of the cell to paint.
	 * @param picture
	 *            Picture to pain at this position.
	 */
	public void paintGivenCell(CellPosition position, String picture)
	{
		this.getGraphics().drawImage(this.octo, position.getPositionX() * DEFAULT_OCTO_SIZE, position.getPositionY() * DEFAULT_OCTO_SIZE,
				this);
		printCharacter(this.getGraphics(), position.getPositionX(), position.getPositionY(), picture);
	}

	/**
	 * Paint the given picture at the given position.
	 * 
	 * @param position
	 *            Position's of the cell to paint.
	 * @param picture
	 *            Picture to pain at this position.
	 */
	public void paintGivenCell(CellPosition position, Image img)
	{
		this.getGraphics().drawImage(img, position.getPositionX() * DEFAULT_OCTO_SIZE, position.getPositionY() * DEFAULT_OCTO_SIZE, this);
		printCharacter(this.getGraphics(), position.getPositionX(), position.getPositionY());
	}

	/**
	 * Change the state of the cell at the given position.
	 * 
	 * @param xSize
	 *            xPosition of the cell.
	 * @param ySize
	 *            yPosition of the cell.
	 */
	public void changeCellState(int xSize, int ySize)
	{
		boolean goSelected = true;

		if (!(this.selectedCell == null))
		{
			this.getGraphics().drawImage(this.octo, this.selectedCell.getPosition().getPositionX() * DEFAULT_OCTO_SIZE,
					this.selectedCell.getPosition().getPositionY() * DEFAULT_OCTO_SIZE, this);
			this.map.getCell(new CellPosition(xSize, ySize)).setIsSelected(false);

			this.printCharacter(this.getGraphics(), this.selectedCell.getPosition().getPositionX(), this.selectedCell.getPosition()
					.getPositionY());
			if ((this.selectedCell.getPosition().getPositionX() == xSize) && (this.selectedCell.getPosition().getPositionY() == ySize))
			{
				goSelected = false;
			}
			this.selectedCell = null;
			this.map.setSelectedCell(null);

			this.botPanel.paintTerraStateUnselected(this.botPanel.getGraphics());
		}

		if (goSelected)
		{
			this.getGraphics().drawImage(this.octoSelected, xSize * DEFAULT_OCTO_SIZE, ySize * DEFAULT_OCTO_SIZE, this);
			this.map.getCell(new CellPosition(xSize, ySize)).setIsSelected(true);
			this.selectedCell = this.map.getCell(new CellPosition(xSize, ySize));
			this.map.setSelectedCell(this.map.getCell(new CellPosition(xSize, ySize)));

			this.botPanel.paintTerraStateSelected(this.botPanel.getGraphics());
		}

		if (!(this.map.getCharacter(xSize, ySize) == null))
		{
			Image imageBuffer = null;
			if (this.map.getCharacter(xSize, ySize) == this.botPanel.getSelectedCharacter())
			{
				try
				{
					imageBuffer = ImageIO.read(new File(this.map.getCharacter(xSize, ySize).toStringSelected()));
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
			{
				try
				{
					imageBuffer = ImageIO.read(new File(this.map.getCharacter(xSize, ySize).toString()));
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			this.getGraphics().drawImage(imageBuffer, xSize * DEFAULT_OCTO_SIZE, ySize * DEFAULT_OCTO_SIZE, this);
			this.paintBuff(this.getGraphics(), xSize, ySize);
		}

	}

	/**
	 * Default cell printing. Print cell and character.
	 * 
	 * @param g
	 *            this.getGraphics()
	 */
	public void printCell(Graphics g)
	{
		for (int xSize = 0; xSize < this.map.getXSize(); xSize++)
		{
			for (int ySize = 0; ySize < this.map.getYSize(); ySize++)
			{
				g.drawImage(this.octo, xSize * DEFAULT_OCTO_SIZE, ySize * DEFAULT_OCTO_SIZE, this);

				printCharacter(g, xSize, ySize);
			}
		}
	}

	/* *****************************Glyphs******************* */
	/**
	 * Default glyph displaying.
	 * 
	 * @param g
	 *            this.getGraphics()
	 */
	public void printGlyph(Graphics g)
	{
		for (int xSize = 0; xSize < this.map.getXSize() + 1; xSize++)
		{
			for (int ySize = 0; ySize < this.map.getYSize() + 1; ySize++)
			{

				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.FIRE)
				{
					g.drawImage(this.glyphFire, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.AIR)
				{
					g.drawImage(this.glyphAir, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.EARTH)
				{
					g.drawImage(this.glyphEarth, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.ICE)
				{
					g.drawImage(this.glyphIce, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.THUNDER)
				{
					g.drawImage(this.glyphThunder, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.WATER)
				{
					g.drawImage(this.glyphWater, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.HOLY)
				{
					g.drawImage(this.glyphHoly, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}
				if (this.map.getTypeOfGlyph(new GlyphPosition(xSize, ySize)) == TypeGlyph.DARK)
				{
					g.drawImage(this.glyphDark, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);
				}

				g.drawImage(this.glyphPattern, xSize * DEFAULT_OCTO_SIZE - 15, ySize * DEFAULT_OCTO_SIZE - 15, this);

			}

		}
	}

	/* *****************************Characters******************* */
	/**
	 * Method to print Character with a particular picture. "img/perso/" +
	 * picture + this.map.getCharacter(xSize, ySize).getHealthPoint() + ".png".
	 * 
	 * @param g
	 *            this.getGraphics()
	 * @param xSize
	 *            xPosition of the Character.
	 * @param ySize
	 *            yPosition of the Character.
	 * @param picture
	 *            "img/perso/" + picture + this.map.getCharacter(xSize,
	 *            ySize).getHealthPoint() + ".png"
	 */
	public void printCharacter(Graphics g, int xSize, int ySize, String picture)
	{
		if (!(this.map.getCharacter(xSize, ySize) == null))
		{
			Image imageBuffer = null;
			try
			{
				imageBuffer = ImageIO
						.read(new File(this.map.getCharacter(xSize, ySize).toStringSelected()));
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.drawImage(imageBuffer, xSize * DEFAULT_OCTO_SIZE, ySize * DEFAULT_OCTO_SIZE, this);
			paintBuff(g, xSize, ySize);
		}
	}

	/**
	 * Default method to print Character. Print the Character at the given
	 * position.
	 * 
	 * @param g
	 *            this.getGraphics()
	 * @param xSize
	 *            xPosition of the Character.
	 * @param ySize
	 *            yPosition of the Character.
	 */
	private void printCharacter(Graphics g, int xSize, int ySize)
	{
		if (!(this.map.getCharacter(xSize, ySize) == null))
		{
			Image imageBuffer = null;

			try
			{
				System.out.println();
				imageBuffer = ImageIO.read(new File(this.map.getCharacter(xSize, ySize).toString()));
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.drawImage(imageBuffer, xSize * DEFAULT_OCTO_SIZE, ySize * DEFAULT_OCTO_SIZE, this);

			paintBuff(g, xSize, ySize);
		}
	}

	/**
	 * Print the buff affected to a Character at the given position.
	 * 
	 * @param g
	 *            this.getGraphics()
	 * @param xSize
	 *            xPosition of the Character.
	 * @param ySize
	 *            yPosition of the Character.
	 */
	private void paintBuff(Graphics g, int xSize, int ySize)
	{

		Image imageBuffer = null;
		if (!(this.map.getCharacter(xSize, ySize).getBuff() == null))
		{
			switch (this.map.getCharacter(xSize, ySize).getBuff())
			{
			case BURNING:
				imageBuffer = this.buffBurning;
				break;
			case FREEZING:
				imageBuffer = this.buffFreezing;
				break;
			default:
			}
			g.drawImage(imageBuffer, xSize * DEFAULT_OCTO_SIZE, ySize * DEFAULT_OCTO_SIZE, this);
		}
	}

	/* *****************************Components******************* */
	/**
	 * Default method to print map. Call printCell and printGlyph.
	 * 
	 * @param g
	 *            this.getGraphics()
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		this.printCell(g);
		this.printGlyph(g);
	}

	/**
	 * Default method to print map. Call printCell and printGlyph.
	 */
	public void paintComponent()
	{
		this.printCell(this.getGraphics());
		this.printGlyph(this.getGraphics());
	}

	/*
	 * ****************************************ANIMATION
	 * DISPLAY********************************************
	 */
	/**
	 * Display the animation of a spell.
	 * 
	 * @param posList
	 *            list of position affected by the spell.
	 * @param typeGlyph
	 *            type of the spell.
	 */
	public void spellAnimation(ArrayList<CellPosition> posList, TypeGlyph typeGlyph)
	{
		Audio audioAir = new Audio(Audio.SPELL_MUSIQUE,1);
		Audio audioFire = new Audio(Audio.SPELL_MUSIQUE,4);
		Audio audioHoly = new Audio(Audio.SPELL_MUSIQUE,3);
		Audio audioIce = new Audio(Audio.SPELL_MUSIQUE,2);
		Audio audio = null;
		switch(typeGlyph)
		{
		case AIR:  audio = audioAir;
					break;
		case FIRE:  audio = audioFire;
		break;
		case HOLY: audio = audioHoly;
		break;
		case ICE:  audio = audioIce;
		}
		

		audio.run();
		Image tmp = null;
		for (int indexPict = 1; indexPict < 5; indexPict++)
		{
			try
			{
				tmp = ImageIO.read(new File("img/spells/" + typeGlyph.toString() + indexPict + ".png"));
			} catch (IOException k)
			{
				// TODO Auto-generated catch block
				k.printStackTrace();
			}
			for (CellPosition cellpos : posList)
			{

				this.paintGivenCell(cellpos, tmp);
			}

			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try
		{
			Thread.sleep(300);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (CellPosition cellpos : posList)
		{

			this.paintGivenCell(cellpos, this.octo);
		}

		this.botPanel.repaint();
	}

	/**
	 * Display the animation of a buff.
	 * 
	 * @param cellTraveled
	 *            Cell where the buff is applied.
	 * @param buff
	 *            The type of the buff.
	 */
	public void buffAnimation(Cell cellTraveled, Buff buff)
	{
		Image tmp = null;
		for (int indexPict = 1; indexPict < 5; indexPict++)
		{
			try
			{
				tmp = ImageIO.read(new File("img/spells/" + buff.toString() + indexPict + ".png"));
			} catch (IOException k)
			{
				// TODO Auto-generated catch block
				k.printStackTrace();
			}

			this.paintGivenCell(cellTraveled.getPosition(), tmp);

			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			Thread.sleep(300);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.paintGivenCell(cellTraveled.getPosition(), this.octo);
	}

	/*
	 * ****************************************MOUSE
	 * LISTENER********************************************
	 */
	/**
	 * Empty. Mouse Listener for when mouse button is pressed then released.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Empty. Mouse Listener for when mouse is entered.
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Empty. Mouse Listener for when mouse is exited.
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Change the state of the cell which is under the pointer when a button is
	 * pressed. Mouse Listener for when mouse button is pressed.
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.changeCellState((int) e.getX() / 50, (int) e.getY() / 50);
		
	}

	/**
	 * Empty. Mouse Listener for when mouse button is released.
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

}
