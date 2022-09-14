package Utilites;

import java.util.Random;

public class RendomNun {
	public int get(int start,int end)
	{
		Random rnd =  new Random();
		int rndint = rnd.nextInt(end+1-start) + start;
		return rndint;
	}
}
