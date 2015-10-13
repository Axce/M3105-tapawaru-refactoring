package fr.iut.tapawaru.action;

import fr.iut.tapawaru.map.Cell;
import fr.iut.tapawaru.map.CellPosition;
import fr.iut.tapawaru.map.Glyph;
import fr.iut.tapawaru.map.GlyphPosition;
import fr.iut.tapawaru.map.Map;
import fr.iut.tapawaru.map.TypeGlyph;
import fr.iut.tapawaru.team.Team;

/**
 * Terraforming spells. Library gathering static methods of the different Terra
 * spells. Inherited class from Spell, likewise Attack spells.
 * 
 * @authors CEARD, MATHEY, MOUNIER,
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Terra extends Spell
{
	
	/**
	 * Clockwise glyph spin 01 -> 30 32 21
	 */
	public static void glyphCWspin(Map map, CellPosition cellPosition)
	{
		
		if (map.getTeamController().isDeductable(1))
		{
			GlyphPosition[] glyphPosList = cellPosition
					.generateAdjacentGlyphPosition();
			
			Glyph glyphBuffer = map.getGlyph(glyphPosList[3]);
			
			map.getGlyph(glyphPosList[2]).setGlyphPosition(glyphPosList[3]);
			;
			map.setGlyph(glyphPosList[3], map.getGlyph(glyphPosList[2]));
			map.getGlyph(glyphPosList[1]).setGlyphPosition(glyphPosList[2]);
			;
			map.setGlyph(glyphPosList[2], map.getGlyph(glyphPosList[1]));
			map.getGlyph(glyphPosList[0]).setGlyphPosition(glyphPosList[1]);
			;
			map.setGlyph(glyphPosList[1], map.getGlyph(glyphPosList[0]));
			glyphBuffer.setGlyphPosition(glyphPosList[0]);
			map.setGlyph(glyphPosList[0], glyphBuffer);
			
			map.getTeamController().deduct(1);
		}
		
	}
	
	/**
	 * Counter-clockwise glyph spin
	 * 01 -> 12
	 * 32    03
	 */
	public static void glyphCCWspin(Map map, CellPosition cellPosition)
	{
		
		if (map.getTeamController().isDeductable(1))
		{
			GlyphPosition[] glyphPosList = cellPosition
					.generateAdjacentGlyphPosition();
			
			Glyph glyphBuffer = map.getGlyph(glyphPosList[0]);
			
			map.getGlyph(glyphPosList[1]).setGlyphPosition(glyphPosList[0]);
			;
			map.setGlyph(glyphPosList[0], map.getGlyph(glyphPosList[1]));
			map.getGlyph(glyphPosList[2]).setGlyphPosition(glyphPosList[1]);
			;
			map.setGlyph(glyphPosList[1], map.getGlyph(glyphPosList[2]));
			map.getGlyph(glyphPosList[3]).setGlyphPosition(glyphPosList[2]);
			;
			map.setGlyph(glyphPosList[2], map.getGlyph(glyphPosList[3]));
			glyphBuffer.setGlyphPosition(glyphPosList[3]);
			map.setGlyph(glyphPosList[3], glyphBuffer);
			
			map.getTeamController().deduct(1);
		}
	}
	
	/**
	 * 4 new random glyphs 01 -> ?? 32 ??
	 */
	public static void glyphRandom(Map map, CellPosition cellPosition)
	{
		if (map.getTeamController().isDeductable(1))
		{
			GlyphPosition[] glyphPosList = cellPosition
					.generateAdjacentGlyphPosition();
			
			map.getGlyph(glyphPosList[0]).setTypeGlyph(
					TypeGlyph.getRandomType());
			map.getGlyph(glyphPosList[1]).setTypeGlyph(
					TypeGlyph.getRandomType());
			map.getGlyph(glyphPosList[2]).setTypeGlyph(
					TypeGlyph.getRandomType());
			map.getGlyph(glyphPosList[3]).setTypeGlyph(
					TypeGlyph.getRandomType());
			
			map.getTeamController().deduct(1);
		}
		
	}
	
}
