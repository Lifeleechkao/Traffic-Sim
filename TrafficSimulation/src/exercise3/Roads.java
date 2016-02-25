package exercise3;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Roads extends Actor {
	public static final int ROAD_WIDTH = 50;

	public Roads() {
		GreenfootImage roadImage = new GreenfootImage(TrafficWorld.WIDTH, ROAD_WIDTH);
		roadImage.setColor(Color.GRAY);
		roadImage.fill();
		this.setImage(roadImage);

	}

}
// add object road x,y half of width and road length
/*
 * Create GFIMAGE setcolor Fill SetImage(__)
 */
