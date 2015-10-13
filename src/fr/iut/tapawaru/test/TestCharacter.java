package fr.iut.tapawaru.test;
import junit.framework.*;
import fr.iut.tapawaru.map.*;
import fr.iut.tapawaru.team.*;
import fr.iut.tapawaru.team.Character;


public class TestCharacter extends TestCase
{
	/**
	 * Constructor of the test
	 * @param string name of the test
	 */
	public TestCharacter(String name)
	{
		super(name);
	}
	
	/**
	 * test on the method isAlive()
	 */
	public void isAlive()
	{
		Map mapTest = new Map();
		try
		{
			Character character1 = new Character(new Team(TeamColor.RED,mapTest),mapTest,1);
			Character character2 = new Character(new Team(TeamColor.RED,mapTest),mapTest,2);
			Character character3 = new Character(new Team(TeamColor.RED,mapTest),mapTest,3);
			character2.inflict(5);
			character3.inflict(6);
			
			assertEquals(character1.isAlive(), true);
			assertEquals(character2.isAlive(), false);
			assertEquals(character3.isAlive(), false);
		}
		catch (Exception exception){};
	}
	
	/**
	 * test on the method deduct()
	 */
	public void deduct()
	{
		Map mapTest = new Map();
		try
		{
			TeamController teamControllerTest = new TeamController(mapTest);
			assertEquals(teamControllerTest.isDeductable(0), true);
			assertEquals(teamControllerTest.isDeductable(5), false);
			assertEquals(teamControllerTest.isDeductable(7), false);
		}
		catch (Exception exception){};
	}
	
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new TestCharacter("isAlive"));
		suite.addTest(new TestCharacter("deduct"));
		
		return (Test) suite;
	}


}
