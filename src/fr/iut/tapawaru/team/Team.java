package fr.iut.tapawaru.team;

import fr.iut.tapawaru.map.Map;


/**
 * Team of Characters. 
 * Two Teams compete in a game. 
 * A Team contains a default number of characters at the beginning of the game. (3)
 * Every Team has its representing color.
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Team
{
	/* ****************************************ATTRIBUTS******************************************** */
				/* *****************************Default Values******************* */
	public final static int	DEFAULT_NB_CHARACTER = 3;
	public final static int	DEFAULT_ACTION_POINT = 3;
	
				/* *****************************Characters******************* */
	/** Team's number of characters   (set to static if same in every Team). */
	private final int nbCharacter;
	
	/** Team's list of characters. */
	private Character[]	listCharacter;
	
				/* *****************************Utility******************* */
	/** Team's color. */
	private TeamColor colorTeam;
	
	/** Team's Action Point left */
	private int actionPointLeft; 
	
	/* ****************************************CONSTRUCTORS******************************************** */
	/**
	 * Constructor for the Team.
	 * @param nb : number of characters.
	 */
	public Team(int nb, TeamColor color,Map map)
	{
		this.nbCharacter = nb;
		this.listCharacter = new Character[this.nbCharacter];
		this.colorTeam = color;
		for(int nbCharacterCreated =0; nbCharacterCreated<this.nbCharacter;nbCharacterCreated++)
		{
			this.listCharacter[nbCharacterCreated] = new Character(this, map,nbCharacterCreated+1);
		}
		this.actionPointLeft = DEFAULT_ACTION_POINT;
	}
	
	/**
	 * Constructor for a default Team.
	 * @param nb : number of characters.
	 */
	public Team( TeamColor color,Map map)
	{
		this.nbCharacter = DEFAULT_NB_CHARACTER;
		this.listCharacter = new Character[this.nbCharacter];
		this.colorTeam = color;	
		for(int nbCharacterCreated =0; nbCharacterCreated<this.nbCharacter;nbCharacterCreated++)
		{
			this.listCharacter[nbCharacterCreated] = new Character(this,map,nbCharacterCreated+1);
		}
		this.actionPointLeft = DEFAULT_ACTION_POINT;
	}
	
	
	/* ****************************************GETTERS / SETTERS *************************************** */
				/* *****************************Characters******************* */
	/** 
	 * @return this.nbCharacter
	 */
	public int getNbCharacter()
	{
		return this.nbCharacter;
	}

	/**
	 * @return this.listCharacter
	 */
	public Character[] getCharacter()
	{
		return this.listCharacter;
	}
	
				/* *****************************Characters******************* */	
	/**
	 * @return this.actionPointLeft
	 */
	public int getActionPointsLeft()
	{
		return this.actionPointLeft;
	}
	
	/**
	 * Replace the actionPointLeft by a given integer.
	 * @param newActionPointLeft new amount of action points left.
	 */
	public void setActionPointsLeft(int newActionPointLeft)
	{
		this.actionPointLeft=newActionPointLeft;
	}
	
				/* *****************************Utility******************* */	
	/**
	 * @return this.colorTeam
	 */
	public TeamColor getColorTeam()
	{
		return this.colorTeam;
	}



}
