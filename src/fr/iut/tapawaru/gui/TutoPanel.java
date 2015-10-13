package fr.iut.tapawaru.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TutoPanel extends JPanel
{
	private TutoFrame tutoFrame;

	public TutoPanel(TutoFrame tutoFrame)
	{
		this.tutoFrame =tutoFrame;
		

		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{

		Image imageBuffer = null;
		try
		{
			imageBuffer = ImageIO
					.read(new File("tuto/tuto"+this.tutoFrame.getTutoIndex()+".png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g.drawImage(imageBuffer,0,0, this);
	}
	
	
}
