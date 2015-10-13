package fr.iut.tapawaru.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


public class TutoFrame extends JFrame implements MouseListener
{
	private int tutoIndex;

	public TutoFrame()
	{
		this.tutoIndex =1;
		this.setVisible(true);
		this.setSize(765,740);
		this.addMouseListener(this);
		TutoPanel panel = new TutoPanel(this);

		this.add(panel);

	}
	
	private void next()
	{
		if(this.tutoIndex ==11)
		{
			System.out.println(tutoIndex);
			this.dispose();
		}
		this.tutoIndex++;
	
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.next();
		this.repaint();
		
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

	public int getTutoIndex()
	{
		return this.tutoIndex;
	}

	

}
