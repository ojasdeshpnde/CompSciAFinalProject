import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ParticlePanel extends JPanel 
{
	public ParticleGenerator pg2;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawRect(0, 0, 400, 400);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		ArrayList<Particle> particleList = pg2.list;
		for(Particle p : particleList)
		{
			g2d.drawRect(p.myX, p.myY, 1, 1); 
		}
	}
	
	public static void main(String[] args)
	{
		ParticleGenerator pg = new ParticleGenerator();
		
		pg.frm.setSize(300, 600);
		pg.frm.add(pg.panel);
		pg.frm.setVisible(true);
		
		int counter = 0;
		int numCounter = 0;
		while(counter < 1000)
		{
			int x = ParticleGenerator.RandomLocationX();
			int y = ParticleGenerator.RandomLocationY();
			if(!(pg.listX.contains(x) && pg.listY.contains(y)))
			{
				Particle q = new Particle(x,y);
				pg.list.add(q);
				pg.listX.add(x);
				pg.listY.add(y);
				System.out.println("It did not get Intercepted" + counter);
				counter++;
			}
			else 
			{
				System.out.println("It got Intercepted " + numCounter++);
			}
		}
		pg.panel.repaint();
		Thread t = new Thread(pg);
		t.start();
	}
	
}
