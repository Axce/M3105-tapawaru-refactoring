package fr.iut.tapawaru.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fr.iut.tapawaru.Main;
import fr.iut.tapawaru.map.Map;
import fr.iut.tapawaru.team.Team;

/**
 * Create the Victory Frame.
 * @author jpelloux
 *
 */
public class VictoryFrame extends JFrame implements MouseListener
{
	/**
	 * Constructor for victory of one team.
	 * If winner team is null is an equality.
	 * @param map Map of the game which is win.
	 * @param team The winner team. 
	 */
	public VictoryFrame(Map map, Team team)
	{
		this.addMouseListener(this);
		this.setTitle("Victory");
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JLabel imageVictory;
		if(team == null)
		{
			imageVictory = new JLabel( new ImageIcon( "img/win/winNull.png"));
		}else{
			imageVictory = new JLabel( new ImageIcon( "img/win/"+ team.getColorTeam()+".png"));
		}
		
		panel.add(imageVictory);
		this.add(panel);
		this.pack();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Main.mainFrame.dispose();
		this.dispose();
		System.exit(0);
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
}
