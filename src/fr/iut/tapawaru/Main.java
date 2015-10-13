package fr.iut.tapawaru;

import fr.iut.tapawaru.gui.MainFrame;
import fr.iut.tapawaru.map.Map;

/**
 * Class containing main
 * 
 * @authors CEARD, MATHEY, MOUNIER, 
 * @authors PELLOUX-PRAYER, PRADELLE
 */
public class Main
{
	/**
	 * MainFrame of the program.
	 */
	public static MainFrame mainFrame;
	
	/**
	 * Entry point of the program.
	 * @param args
	 */
	public static void main(String[] args)
	{
		Map map = new Map();
		mainFrame = new MainFrame(map);

	}
	
	
}
