package fr.iut.tapawaru.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import fr.iut.tapawaru.song.Audio;
import fr.iut.tapawaru.team.Character;
import fr.iut.tapawaru.team.Team;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.iut.tapawaru.action.Attack;
import fr.iut.tapawaru.action.Move;
import fr.iut.tapawaru.action.Terra;
import fr.iut.tapawaru.map.CellPosition;
import fr.iut.tapawaru.map.Map;

/**
 * Gui manager of the bottom part of the mainFrame.
 * Here we can see spells, action points, terra spells ...
 * @author jpelloux
 *
 */
public class BottomPanel extends JPanel implements KeyListener
{
/* ****************************************ATTRIBUTS******************************************** */
			/* *****************************Images******************* */
	/** Image of action point left = 3. */
	private Image apLeft3;
	
	/** Image of action point left = 2. */
	private Image apLeft2;
	
	/** Image of action point left = 1. */
	private Image apLeft1;

	/** the right side image */
	private Image right;
	
	/** The life point bar image */
	private Image hpBar;
	
			/* *****************************Map******************* */
	/** the game's Map.  */
	private Map map;
	
	/** the game's MapGUI.  */
	private MapGUI mapGui;
	
			/* *****************************Utility******************* */
	/** Say if any case is selected. */
	private boolean caseSelected;
	
	/** The character which is actually selected. */
	private Character characterSelected;

	/** Audio display */
	private Audio audio;
	

	/* ****************************************CONSTRUCTORS******************************************** */
	/**
	 * Constructor of BottomPanel.
	 * @param map Game's map.
	 */
	public BottomPanel(Map map)
	{

		this.map = map;
		this.caseSelected = false;
		this.mapGui = this.map.getMapGui();

		try
		{
			this.apLeft3 = ImageIO.read(new File("img/players/pa3.png"));
			this.apLeft2 = ImageIO.read(new File("img/players/pa2.png"));
			this.apLeft1 = ImageIO.read(new File("img/players/pa1.png"));
			
			this.right = ImageIO.read(new File("img/botScreen/"+this.map.getTeamController().getPlayingTeam().getColorTeam()+"/right.png"));
			this.hpBar = ImageIO.read(new File("img/botScreen/hp.png"));

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		this.audio = new Audio(Audio.BACKGROUND_MUSIQUE);
		this.audio.run();
		

	}

	/* ****************************************GETTERS / SETTERS *************************************** */
	/**
	 * @return this.characterSelected
	 */
	public Character getSelectedCharacter()
	{
		return this.characterSelected;
	}
	
	/* **********************************PANNEL DISPLAY TOOLS************************* */
	/**
	 * Display the left side when a Cell is selected.
	 * @param g this.Graphics()
	 */
	public void paintTerraStateSelected(Graphics g)
	{
		Image tmp = null;
		try
		{
			tmp = ImageIO.read(new File("img/botScreen/"+ this.map.getTeamController().getPlayingTeam().getColorTeam()
					+ "/leftSel.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(tmp, 0, 0, this);
		paintPA(g);
		this.caseSelected = true;

	}

	/**
	 * Display the left side when a Cell is NOT selected.
	 * @param g this.Graphics()
	 */
	public void paintTerraStateUnselected(Graphics g)
	{
		Image tmp = null;
		try
		{
			tmp = ImageIO.read(new File("img/botScreen/" + this.map.getTeamController().getPlayingTeam().getColorTeam() + "/left.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(tmp, 0, 0, this);
		paintPA(g);
		this.caseSelected = false;
	}

	/**
	 * Paint the number of action point left.
	 * @param g this.Graphics()
	 */
	private void paintPA(Graphics g)
	{
		Image tmp = null;
		switch (this.map.getTeamController().getPlayingTeam().getActionPointsLeft())
		{
		case 1:
			tmp = this.apLeft1;
			break;
		case 2:
			tmp = this.apLeft2;
			break;
		case 3:
			tmp = this.apLeft3;
			break;
		}

		g.drawImage(tmp, 311,15 , this);
	}
	
	/**
	 * Print life point left for each Character in the right side.
	 * @param g this.Graphics()
	 */
	public void printHp(Graphics g)
	{
		for (int index = 0; index < this.map.getTeamController().getPlayingTeam().getCharacter()[0].getHealthPoint(); index++)
		{
			g.drawImage(this.hpBar, 485 + 12*index, 77, this);
		}
		for (int index = 0; index < this.map.getTeamController().getPlayingTeam().getCharacter()[1].getHealthPoint(); index++)
		{
			g.drawImage(this.hpBar, 585+12*index, 77, this);
		}
		for (int index = 0; index < this.map.getTeamController().getPlayingTeam().getCharacter()[2].getHealthPoint(); index++)
		{
			g.drawImage(this.hpBar, 685+ 12*index ,77, this);
		}
	}
	
	/**
	 * Display method.
	 * Paint the different component of the BottomPanel.
	 */
	@Override
	public void paintComponent(Graphics g)
	{	
		this.paintTerraStateUnselected(g);

		g.drawImage(this.right, 375, 0, this);

		paintPA(g);

		this.printHp(g);

		if(this.audio.getP().isEndOfMediaReached())
		{
			System.out.println("yolo");
			this.audio = new Audio(Audio.BACKGROUND_MUSIQUE);
			this.audio.run();
		}
	}
	
	/* ****************************************KEY LISTENER******************************************** */
	/**
	 * Key Listener for when a key is pressed d.
	 * Manage : 
	 * 			-Terra spells
	 * 			-Character's selection
	 * 			-Character's cast.
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		/* ****************************************END OF TURN******************************************** */		
		
		if (e.getKeyChar() == 'h')
		{
			TutoFrame tuto = new TutoFrame();
		}
		
		Team playingTeam = this.map.getTeamController().getPlayingTeam();
		
		CellPosition currentCellPosition = this.map.getSelectedCell().getPosition();
		if (e.getKeyChar() == 'u')
		{
			this.map.getTeamController().skipTurn();
			try
			{
				this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
			} catch (IOException k)
			{
				// TODO Auto-generated catch block
				k.printStackTrace();
			}
			this.characterSelected = null;
			this.mapGui.setSelectedCharacterPosition(null);
			if(!(this.caseSelected == false))
			{
				this.mapGui.changeCellState(currentCellPosition.getPositionX(), currentCellPosition
						.getPositionY());
			}

			this.map.setSelectedCell(null);
			this.caseSelected = false;
			this.repaint();
		}
		
		/* ****************************************TERRA******************************************** */
		if (this.caseSelected)
		{
			switch (e.getKeyChar())
			{
						/* *****************************Counter Clock Spin******************* */
			case 'w':
				this.characterSelected = null;
				this.mapGui.setSelectedCharacterPosition(null);
				Terra.glyphCCWspin(this.map, currentCellPosition);
				this.mapGui.printGlyph(this.mapGui.getGraphics());
				this.mapGui.changeCellState(currentCellPosition.getPositionX(), currentCellPosition
						.getPositionY());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
				} catch (IOException k)
				{
					// TODO Auto-generated catch block
					k.printStackTrace();
				}
				this.paint(this.getGraphics());
				break;
						/* *****************************Clock Spin******************* */
			case 'x':
				this.characterSelected = null;
				this.mapGui.setSelectedCharacterPosition(null);
				Terra.glyphCWspin(this.map, currentCellPosition);
				this.mapGui.printGlyph(this.mapGui.getGraphics());
				this.mapGui.changeCellState(currentCellPosition.getPositionX(), currentCellPosition
						.getPositionY());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
				} catch (IOException k)
				{
					// TODO Auto-generated catch block
					k.printStackTrace();
				}
				this.paint(this.getGraphics());
				break;
						/* *****************************Random******************* */
			case 'c':
				this.characterSelected = null;
				this.mapGui.setSelectedCharacterPosition(null);
				Terra.glyphRandom(this.map, currentCellPosition);
				this.mapGui.printGlyph(this.mapGui.getGraphics());
				this.mapGui.changeCellState(currentCellPosition.getPositionX(), currentCellPosition
						.getPositionY());
				this.paint(this.getGraphics());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
				} catch (IOException k)
				{
					// TODO Auto-generated catch block
					k.printStackTrace();
				}
				break;
						/* *****************************Invalid Input******************* */
			default:
			}
		}
		
		/* ****************************************Character Selection******************************************** */		
		switch (e.getKeyChar())
		{
						/* *****************************Character 1******************* */
		case 'a':
			if(!(this.map.getSelectedCell()==null))
				this.mapGui.changeCellState(currentCellPosition.getPositionX(),currentCellPosition.getPositionY());
			if (!playingTeam.getCharacter()[0].isAlive())
				break;
			if (this.characterSelected == null)
			{
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[0].getCellTraveled().getPosition(),
						playingTeam.getColorTeam() + "Selected");
				this.characterSelected = playingTeam.getCharacter()[0];
				this.mapGui.setSelectedCharacterPosition(playingTeam.getCharacter()[0].getCellTraveled()
						.getPosition());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/rightA.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (this.characterSelected == playingTeam.getCharacter()[0])
			{
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[0].getCellTraveled().getPosition(),
						playingTeam.getColorTeam().toString());
				this.characterSelected = null;
				this.mapGui.setSelectedCharacterPosition(null);
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
			{
				this.mapGui.paintGivenCell(this.characterSelected.getCellTraveled().getPosition(), playingTeam.getColorTeam().toString());
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[0].getCellTraveled().getPosition(),
						playingTeam.getColorTeam() + "Selected");
				this.characterSelected = playingTeam.getCharacter()[0];
				this.mapGui.setSelectedCharacterPosition(playingTeam.getCharacter()[0].getCellTraveled()
						.getPosition());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/rightA.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.repaint();
			break;
							/* *****************************Character 2******************* */
		case 'z':
			if(!(this.map.getSelectedCell()==null))
				this.mapGui.changeCellState(currentCellPosition.getPositionX(),currentCellPosition.getPositionY());
			if (!playingTeam.getCharacter()[1].isAlive())
				break;
			if (this.characterSelected == null)
			{
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[1].getCellTraveled().getPosition(),
						playingTeam.getColorTeam() + "Selected");
				this.characterSelected = playingTeam.getCharacter()[1];
				this.mapGui.setSelectedCharacterPosition(playingTeam.getCharacter()[1].getCellTraveled()
						.getPosition());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/rightZ.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (this.characterSelected == playingTeam.getCharacter()[1])
			{
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[1].getCellTraveled().getPosition(),
						playingTeam.getColorTeam().toString());
				this.characterSelected = null;
				this.mapGui.setSelectedCharacterPosition(null);
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else
			{
				this.mapGui.paintGivenCell(this.characterSelected.getCellTraveled().getPosition(), playingTeam.getColorTeam().toString());
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[1].getCellTraveled().getPosition(),
						playingTeam.getColorTeam() + "Selected");
				this.characterSelected = playingTeam.getCharacter()[1];
				this.mapGui.setSelectedCharacterPosition(playingTeam.getCharacter()[1].getCellTraveled()
						.getPosition());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/rightZ.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.repaint();
			break;
						/* *****************************Character 3******************* */
		case 'e':
			if(!(this.map.getSelectedCell()==null))
				this.mapGui.changeCellState(currentCellPosition.getPositionX(),currentCellPosition.getPositionY());
			if (!playingTeam.getCharacter()[2].isAlive())
				break;
			if (this.characterSelected == null)
			{
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[2].getCellTraveled().getPosition(),
						playingTeam.getColorTeam() + "Selected");
				this.characterSelected = playingTeam.getCharacter()[2];
				this.mapGui.setSelectedCharacterPosition(playingTeam.getCharacter()[2].getCellTraveled()
						.getPosition());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/rightE.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (this.characterSelected == playingTeam.getCharacter()[2])
			{
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[2].getCellTraveled().getPosition(),
						playingTeam.getColorTeam().toString());
				this.characterSelected = null;
				this.mapGui.setSelectedCharacterPosition(null);
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else
			{
				this.mapGui.paintGivenCell(this.characterSelected.getCellTraveled().getPosition(), playingTeam.getColorTeam().toString());
				this.mapGui.paintGivenCell(playingTeam.getCharacter()[2].getCellTraveled().getPosition(),
						playingTeam.getColorTeam() + "Selected");
				this.characterSelected = playingTeam.getCharacter()[2];
				this.mapGui.setSelectedCharacterPosition(playingTeam.getCharacter()[2].getCellTraveled()
						.getPosition());
				try
				{
					this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/rightE.png"));
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.repaint();
			break;
		default:
		}
		
		/* ****************************************SPELLS******************************************** */
		if (!(this.characterSelected == null))
		{
						/* *****************************Laser Beam******************* */
			switch (e.getKeyChar())
			{
			case 'q':
				if (this.map.getSelectedCell() == null)
				{
					// TODO You must select before cast a spell
				} else
				{
					Attack.laserBeam(this.map, this.characterSelected, currentCellPosition, true);
					this.repaint();
					this.mapGui.printCell(this.mapGui.getGraphics());
					this.characterSelected = null;
					this.mapGui.setSelectedCharacterPosition(null);
					this.map.setSelectedCell(null);
					this.caseSelected = false;
					try
					{
						this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
						/* *****************************Flower Bomb******************* */
			case 'd':
					Attack.aroundCaster(this.map, this.characterSelected, true);
					this.repaint();
					this.mapGui.printCell(this.mapGui.getGraphics());
					this.characterSelected = null;
					this.mapGui.setSelectedCharacterPosition(null);
					this.map.setSelectedCell(null);
					this.caseSelected = false;
					try
					{
						this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();		
				}
				break;
						/* *****************************Around Caster******************* */
			case 's':
				if (this.map.getSelectedCell() == null)
				{
					// TODO You must select before cast a spell
				} else
				{
					Attack.flowerBomb(this.map, this.characterSelected, currentCellPosition, true);
					this.repaint();
					this.mapGui.printCell(this.mapGui.getGraphics());
					this.characterSelected = null;
					this.mapGui.setSelectedCharacterPosition(null);
					this.map.setSelectedCell(null);
					this.caseSelected = false;
					try
					{
						this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
					/* *****************************Move******************* */
			case ' ':
				if (this.map.getSelectedCell() == null)
				{
					Image tmp = null;
					try
					{
						tmp = ImageIO.read(new File("img/octoSelectable.png"));
					} catch (IOException k)
					{
						// TODO Auto-generated catch block
						k.printStackTrace();
					}
					for (CellPosition cellpos : Move.getAccessiblePos(this.map, this.characterSelected))
					{

						this.mapGui.paintGivenCell(cellpos, tmp);
					}

				} else
				{
					Move.simpleMove(this.map, this.characterSelected, currentCellPosition);
					this.repaint();
					this.mapGui.printCell(this.mapGui.getGraphics());
					this.characterSelected = null;
					try
					{
						this.right = ImageIO.read(new File("img/botScreen/"+playingTeam.getColorTeam()+"/right.png"));
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					this.mapGui.setSelectedCharacterPosition(null);
					this.map.setSelectedCell(null);
					this.caseSelected = false;
				}
				break;
			default:
			}
		}
	}

	/**
	 * Empty.
	 * Key Listener for when a key is released.
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Empty.
	 * Key Listener for when a key is pressed then released.
	 */
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	
}
