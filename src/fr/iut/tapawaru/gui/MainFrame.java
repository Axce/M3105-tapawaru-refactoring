package fr.iut.tapawaru.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.iut.tapawaru.map.Map;
import fr.iut.tapawaru.song.Audio;

/**
 * The Main container of the game's GUI.
 * @author jpelloux
 */
public class MainFrame extends JFrame
{
	/**
	 * Constructor of the map.
	 * Create the MapGui and the BottomPannel.
	 * @param map The Map used to create the MapGUI.
	 */
	public MainFrame(Map map){
		this.setTitle("Tapawaru");
		
		GridBagLayout myGrid = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(myGrid);
		
		MapGUI mapGui = new MapGUI( map);
		mapGui.setPreferredSize(new Dimension(mapGui.guiMapSize()[0], mapGui.guiMapSize()[1]));
		c.fill = GridBagConstraints.BOTH;
		c.ipady =0 ;
		c.weightx = 0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(mapGui,c);
		
		BottomPanel botPanel = new BottomPanel(map);
		botPanel.setPreferredSize(new Dimension(mapGui.guiMapSize()[0],200));
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 0;
		c.weightx = 0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		this.add(botPanel,c);

		mapGui.addBotPanel(botPanel);

		this.addKeyListener(botPanel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		map.setMapGui(mapGui);
		
	
	}	
}
