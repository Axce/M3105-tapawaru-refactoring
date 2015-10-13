package fr.iut.tapawaru.action;

import java.util.ArrayList;
import fr.iut.tapawaru.map.Cell;
import fr.iut.tapawaru.map.CellPosition;
import fr.iut.tapawaru.map.Map;
import fr.iut.tapawaru.map.TypeGlyph;
import fr.iut.tapawaru.team.Buff;
import fr.iut.tapawaru.team.Character;

/**
 * Attack spells.
 * Library gathering static methods of the different attack spells.
 * Inherited class from Spell, likewise Terra spells.
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Attack extends Spell
{
	/**
	 * chooses a random type among the 4 adjacent glyphs
	 */
	private static TypeGlyph getAttackType(Map map, Cell cellCastingFrom)
	{
		return map.getGlyph(cellCastingFrom.getPosition().generateAdjacentGlyphPosition()[(int)(Math.random() * 4)]).getTypeGlyph();
	}

	
	private static void executeAttack(Map map, ArrayList<CellPosition> posList, TypeGlyph type)
	{
		map.getMapGui().spellAnimation(posList,type);
		switch (type)
		{
			case HOLY:
				for (CellPosition cellPos : posList)
				{
					if (map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()) != null)
					{
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).heal(1);
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).setBuff(Buff.NORMAL);
					}
				}
				break;
				
			case FIRE:
				for (CellPosition cellPos : posList)
				{
					if (map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()) != null)
					{
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).setBuff(Buff.BURNING);
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).inflict(2);
					}
				}
				break;
				
			case ICE:
				for (CellPosition cellPos : posList)
				{
					if (map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()) != null)
					{
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).setBuff(Buff.FREEZING);
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).inflict(2);
					}

				}
				break;
				
			case AIR:
				for (CellPosition cellPos : posList)
				{
					if (map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()) != null)
					{
						Character pers = map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY());
						
						if (pers.getBuff() == Buff.BURNING)
							pers.setBuff(Buff.NORMAL);
						
						pers.inflict(1);
					}

						
				}
				break;
				
			default:
				for (CellPosition cellPos : posList)
				{
					if (map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()) != null)
					{
						map.getCharacter(cellPos.getPositionX(), cellPos.getPositionY()).inflict(1);
					}

				}

		}
		
		
		map.getTeamController().checkWin();

	}
	
	/**
	 * laserBeam
	 * o: caster
	 * x: target
	 * *: impact
	 * 
	 * o****x****
	 * 
	 */
	public static ArrayList<CellPosition> laserBeam(Map map, Character caster, CellPosition target, boolean execute)
	{
		ArrayList<CellPosition> cellList = new ArrayList<CellPosition>();

		if (map.getTeamController().isDeductable(1))
		{
		
			int deltaX = target.getPositionX() - caster.getCellTraveled().getPosition().getPositionX();
			int deltaY = target.getPositionY() - caster.getCellTraveled().getPosition().getPositionY();
				
			if (Math.abs(deltaX) > Math.abs(deltaY))
			{
				if (deltaX > 0) //right
				{
					for (int x = caster.getCellTraveled().getPosition().getPositionX()+1,
							 y = caster.getCellTraveled().getPosition().getPositionY();
						 x < map.getXSize();
						 x++)
					{
						cellList.add(new CellPosition(x, y));
					}
				}
				else	//left
				{
					for (int x = caster.getCellTraveled().getPosition().getPositionX()-1,
						 	 y = caster.getCellTraveled().getPosition().getPositionY();
						 x >= 0;
						 x--)
					{
						cellList.add(new CellPosition(x, y));
					}
				}
	
			}
			else
			{
				if (deltaY > 0)	//down
				{
					for (int x = caster.getCellTraveled().getPosition().getPositionX(),
						 	 y = caster.getCellTraveled().getPosition().getPositionY()+1;
						 y < map.getYSize();
						 y++)
					{
						cellList.add(new CellPosition(x, y));
					}
				}
				else	//up
				{
					for (int x = caster.getCellTraveled().getPosition().getPositionX(),
						 	 y = caster.getCellTraveled().getPosition().getPositionY()-1;
						 y >= 0;
						 y--)
					{
						cellList.add(new CellPosition(x, y));
					}
				}
			}
			
			executeAttack(map, cellList, getAttackType(map, caster.getCellTraveled()));
			map.getTeamController().deduct(1);
		}
		
		return cellList;
	}
	
	/**
	 * aroundCaster
	 * o: caster
	 * *: impact
	 *   ***
	 *   *o*
	 *   ***
	 */
	public static ArrayList<CellPosition> aroundCaster(Map map, Character caster, boolean execute)
	{

		ArrayList<CellPosition> cellList = new ArrayList<CellPosition>();
		
		if (map.getTeamController().isDeductable(1))
		{
			int xCaster = caster.getCellTraveled().getPosition().getPositionX();
			int yCaster = caster.getCellTraveled().getPosition().getPositionY();
			
			for (int y = yCaster - 1 ; y <= yCaster + 1 ; y++)
			{
				for (int x = xCaster - 1 ; x <= xCaster + 1 ; x++)
				{
					if ((x != xCaster || y != yCaster) &&
						x >= 0 && y >= 0 &&
						x < map.getXSize() && y < map.getYSize())
						cellList.add(new CellPosition(x, y));
				}
			}
			
			executeAttack(map, cellList, getAttackType(map, caster.getCellTraveled()));
			map.getTeamController().deduct(1);

		}


		return cellList;
	}
	
	/**
	 * flowerBomb
	 * o: caster
	 * X: target/impact
	 * *: impact
	 *      *
	 * o   *X*
	 *      *
	 */
	public static ArrayList<CellPosition> flowerBomb(Map map, Character caster, CellPosition target, boolean execute)
	{
		
		ArrayList<CellPosition> cellList = new ArrayList<CellPosition>();
		
		if (map.getTeamController().isDeductable(1))
		{
			int x, y,
				xTarget = target.getPositionX(),
				yTarget = target.getPositionY();
			
			x = xTarget; y = yTarget+1;
			if (x >= 0 && y >= 0 &&
				x < map.getXSize() && y < map.getYSize())
				cellList.add(new CellPosition(x, y));
			
			x = xTarget; y = yTarget-1;
			if (x >= 0 && y >= 0 &&
				x < map.getXSize() && y < map.getYSize())
				cellList.add(new CellPosition(x, y));
			
			x = xTarget+1; y = yTarget;
			if (x >= 0 && y >= 0 &&
				x < map.getXSize() && y < map.getYSize())
				cellList.add(new CellPosition(x, y));
			
			x = xTarget-1; y = yTarget;
			if (x >= 0 && y >= 0 &&
					x < map.getXSize() && y < map.getYSize())
				cellList.add(new CellPosition(x, y));
			
			x = xTarget; y = yTarget;
			if (x >= 0 && y >= 0 &&
				x < map.getXSize() && y < map.getYSize())
				cellList.add(new CellPosition(x, y));
			
			executeAttack(map, cellList, getAttackType(map, caster.getCellTraveled()));
			map.getTeamController().deduct(1);

		}


		return cellList;
	}
}
