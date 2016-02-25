package exercise1;

import java.util.Random;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor {
	private static Random randomColours = new Random();
	private Direction d;
	private String[] carImages = {"images\\topCarBlue.png","images\\topCarRed.png","images\\topCarPurple.png","images\\topCarYellow.png"};
	public Car(Direction d){
		this.d = d;
		int colourRole = randomColours.nextInt(carImages.length);
		GreenfootImage image = new GreenfootImage(carImages[colourRole]);
		this.setImage(image);
	}

	public void act(){
		move(1);
		
		
		
		
		if(isAtEdge()){
			switch(this.d){
				case EAST: 
					 setLocation(0, getY());
					break;
				case WEST:
					setLocation(TrafficWorld.WIDTH, getY());
					break;
				case NORTH:
					setLocation(getX(), TrafficWorld.HEIGHT);
					break;
				case SOUTH:
					setLocation(getX(), 0);
					break;
			
		
			}
		}
		
	}

}
