package exercise2;

import java.util.Random;

import exercise2.TrafficLights.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {
	private static Random randomColours = new Random();
	private Direction d;
	private Intersection intersection;
	private TrafficLights t;
	private state carState = state.APPROACHING;
	private int slowSpeed = 2;
	private int stopSpeed = 0;
	private int fullSpeed = 5;
	private int currentSpeed = fullSpeed;
	private String[] carImages = {"images\\topCarBlue.png","images\\topCarRed.png","images\\topCarPurple.png","images\\topCarYellow.png"};
private static enum state{
	APPROACHING,
	AT_INTERSECTION,
	LEAVING;
}
public Car(Direction d){
	this.d = d;
	int colourRole = randomColours.nextInt(carImages.length);
	GreenfootImage image = new GreenfootImage(carImages[colourRole]);
	this.setImage(image);
}

public void act(){
	move(currentSpeed);
	if(intersection != null){
	if(this.getRotation() == 0){
		t = intersection.getLightArray(2);
		
	}else if(this.getRotation() == 90){
		t = intersection.getLightArray(0);
		
	}else if(this.getRotation() == 180){
		t = intersection.getLightArray(3);
		
	}else if(this.getRotation() == 270){
		t = intersection.getLightArray(1);
		
	}
	}
	if(t != null){
	switch(carState){
	case APPROACHING : if(t.getColor() == TrafficLights.Color.GREEN){
		speedUp();
	}else{
		slowDown();
		
	}
	break;
	case AT_INTERSECTION :
		if(currentSpeed <= slowSpeed){
		currentSpeed = stopSpeed;
	}else{
		currentSpeed = fullSpeed;
	}
		if(currentSpeed == 0 && t.getColor() == TrafficLights.Color.GREEN) {
			currentSpeed = fullSpeed;
		}
		
	break;
	case LEAVING : 
		speedUp();
	
	break;
	}
	
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
	move(currentSpeed);
}
public void speedUp(){
	if(currentSpeed < fullSpeed){
		currentSpeed++;
	}
}
public void slowDown(){
	if(currentSpeed > slowSpeed){
		currentSpeed--;
	}
}
//	public void setLightColor(TrafficLights.Color lightColor) {
//		this.lightColor = lightColor;
//	}

@Override
public void isApproaching(Intersection i) {
	// TODO Auto-generated method stub
	
	intersection = i;
	carState = state.APPROACHING;


}

@Override
public void isAtIntersection(Intersection i) {
	// TODO Auto-generated method stub
	
	intersection = i;
	carState = state.AT_INTERSECTION;
}

@Override
public void isLeavingIntersection(Intersection i) {
	// TODO Auto-generated method stub

		intersection = i;
		carState = state.LEAVING;
	}

}
