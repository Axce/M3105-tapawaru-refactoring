package fr.iut.tapawaru.team;

import fr.iut.tapawaru.map.Cell;
import fr.iut.tapawaru.map.Map;

public class Character
{
	/* ****************************************ATTRIBUTS******************************************** */
				/* *****************************Default HP******************* */
	public final static int DEFAULT_MAX_HP = 5;

				/* *****************************HP******************* */
	/** Character's life points. */
	private int		healthPoint;
	
	/** Character's maximum life points. */
	private int maxHealthPoint;
	
				/* *****************************Map and Cell******************* */
	/** Cell this Character is actually on.*/
	private Cell	cellTraveled;
	
	/** Map on which the Character is*/
	private Map map;
	
				/* *****************************Utility******************* */
	/** Character's team of appurtenance. */
	private Team	team;
	
	/** Character picture */
	private String	picture;
	
	/** Buff the character have*/
	private Buff buff;

	/** Index of the Charater. Used in picture selection */
	private int index;
	
	/* ****************************************CONSTRUCTORS******************************************** */	
	/**
	 * Constructor of the Character.
	 * 
	 * @param team
	 *            : Character's team.
	 * @param healthpoint
	 *            : Character's life points.
	 */
	public Character(Team team,Map map, int index)
	{
		this.team = team;
		this.maxHealthPoint = DEFAULT_MAX_HP;
		this.healthPoint = this.maxHealthPoint;

		this.cellTraveled = null;
		
		this.index = index;
		this.picture = "img/perso/sorcerer"+index + this.team.getColorTeam().toString();
		
		this.map = map;
		this.buff = Buff.NORMAL;
	}
	
	/* ****************************************GETTERS / SETTERS *************************************** */
				/* *****************************Buffs******************* */
	/**
	 * Change the buff for the current buff.
	 * @param buff new buff the Character have.
	 */
	public void setBuff(Buff buff)
	{
		this.buff = buff;
	}
	
	/**
	 * @return this.buff
	 */
	public Buff getBuff()
	{
		return this.buff;
	}
	
				/* *****************************Cells******************* */
	/**
	 * @return this.cellTraveled
	 */
	public Cell getCellTraveled()
	{
		return this.cellTraveled;
	}
	
	/**
	 * Set the cellTraveled with a given cell.
	 * @param cell new cellTraveled by the Character.
	 */
	public void setCell(Cell cell)
	{
		this.cellTraveled = cell;
	}
	
				/* *****************************Health******************* */
	/**
	 * @return this.maxHealthPoint
	 */
	public int getDefaultHealthPoint()
	{
		return this.maxHealthPoint;
	}
	
	/**
	 * @return  this.healthPoint
	 */
	public int getHealthPoint()
	{
		return this.healthPoint;
	}
	
	/**
	 * Heal given life points to this Character.
	 * @param damages HP the character must win.
	 */
	public void heal(int hp)
	{
		this.healthPoint += hp;
		if (this.healthPoint > this.maxHealthPoint)
			this.healthPoint = this.maxHealthPoint;
	}
	
	/**
	 * Deal given damages to this Character.
	 * @param damages HP the character must loose.
	 */
	public void inflict(int damages)
	{
		this.healthPoint -= damages;
		if (!this.isAlive())
		{
			this.kill();
		}
	}
	
				/* *****************************Utility******************* */
	/**
	 * Modify the path of the picture representing this Character.
	 * @param s "img/perso/" + s
	 */
	public void setPicture(String s)
	{
		this.picture = "img/perso/" + s;
	}
	
	/**
	 * @return this.map
	 */
	public Map getMap()
	{
		return this.map;
	}

	/* ****************************************UTILITY*************************************** */
	/**
	 * Method to check that character's still alive.
	 */
	public boolean isAlive()
	{
		if (this.healthPoint <= 0)
			return false;
		return true;
	}
	
	/**
	 * Method to delete the current Character object.
	 */
	public void kill()
	{
		this.map.putCharacter(null, this.cellTraveled.getPosition());
		this.cellTraveled = null;
		this.setBuff(Buff.DEAD);
		this.map.getMapGui().repaint();
	}
	
	/**
	 * @return the path of the picture representing this Character when he is selected.
	 */
	public String toStringSelected()
	{
		return this.picture + this.healthPoint + "selected" + ".png";
	}
	
	/**
	 * @return the path of the picture representing this Character.
	 */
	@Override
	public String toString()
	{
		return this.picture+this.healthPoint+ ".png";
	}
	

}
