import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import BreezyGUI.GBFrame;

public class ParticleGenerator implements Runnable{

	public ArrayList<Particle> list = new ArrayList<Particle>();
	public ArrayList<Integer> listX = new ArrayList<Integer>();
	public ArrayList<Integer> listY = new ArrayList<Integer>();
	public static int sSize = 800;

	ParticlePanel panel;
	
	public ParticleGenerator()
	{
		super();
		panel = new ParticlePanel();
		panel.pg2 = this;
	}
	
	JFrame frm = new JFrame("Fractal");
	
	
	public void run() 
	{	
		while(true)
		{
			/*try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			for(int i = 0; i < list.size(); i++)
			{
				boolean isCollided = false;
				for(int j = 0; !isCollided && j < list.size(); j++)
				{
					boolean test = isInDistance(list.get(i),list.get(j));
					//System.out.println(test);
					if(i != j) 
					{
						if(test)
						{
							isCollided = true;
							break;
						}
					}
				}
				if (!isCollided) 
				{
					int x = randomMovementX();
					int y = randomMovementY();
					if(list.get(i).myX + x >= sSize)
					{
						if(x > 0) 
						{
							list.get(i).myX -= x;
						}
						else 
						{
							list.get(i).myX += x;
						}
					}
					else if(list.get(i).myX + x < 0) 
					{
						list.get(i).myX -= x;
					}
					else 
					{
						list.get(i).myX += x;
					}
					
					if(list.get(i).myY + y >= sSize)
					{
						if(y > 0) 
						{
							list.get(i).myY -= y;
						}
						else 
						{
							list.get(i).myY += y;
						}
					}
					else if(list.get(i).myY + y < 0) 
					{
						list.get(i).myY -= y;
					}
					else 
					{
						list.get(i).myY += y;
					}
					
					//list.get(i).myY += y;
					panel.invalidate();
					panel.validate();
					panel.repaint();
				}

			}
			panel.invalidate();
			panel.validate();
			panel.repaint();
		}
	}
	
	public static int RandomLocationX()
	{
		int x;
		x = (int)(Math.random() * (sSize));//(list.get(i).myX + 2 == list.get(j).myX) || (list.get(i).myX - 2 == list.get(j).myX) || (list.get(i).myY + 2 == list.get(j).myY) || ((list.get(i).myY - 2 == list.get(j).myY))0);
		return x;
	}
	public static int RandomLocationY()
	{
		int y;
		y = (int)(Math.random() * (sSize));
		return y;
	}
	public static int randomMovementX()
	{
		int x =(int)(Math.random() * 5) - 2;
		return x;
	}
	public static int randomMovementY()
	{
		int y =(int)(Math.random() * 5) - 2;
		return y;
	}
	public static boolean isInDistance(Particle one, Particle two)
	{
		double num = distanceF(one, two);
		return num <= 1;
		
		/*int num = one.myX - two.myX;
		int num2 = one.myY - two.myY;
		//System.out.println(num + " " + num2);
		return Math.abs(num) <= 1 && Math.abs(num2) <= 1;*/
		
	
		//TRIAL
		
	/*	if(((one.myX + 1) == two.myX) || ((one.myX - 1) == two.myX))
		{
			System.out.println("1");
			return true;
		}
		else if(((one.myY + 1) == two.myY) || ((one.myY - 1) == two.myY))
		{
			System.out.println("2");
			return true;
		}
		System.out.println("3");
		return false;*/
	}
	
	public static double distanceF(Particle one, Particle two)
	{
		int cX = one.myX - two.myX;
		int cY = one.myY - two.myY;
		cX = cX * cX;
		cY = cY * cY;
		double total = cX + cY;
		total = Math.sqrt(total);
		return total;
	}
	
}
