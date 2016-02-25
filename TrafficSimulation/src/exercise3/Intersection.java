package exercise3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.helpers.PrintConversionEventImpl;

import exercise2.Car;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Intersection extends Actor {

	private TrafficLights[] lightArray = new TrafficLights[4];
	private TrafficLights.Color verticalColor;
	private TrafficLights.Color horizontalColor;

	public Intersection() {
		GreenfootImage intersection = new GreenfootImage(Roads.ROAD_WIDTH, Roads.ROAD_WIDTH);
		setImage(intersection);

	}

	public void addLights() {
		verticalColor = TrafficLights.Color.GREEN;
		horizontalColor = TrafficLights.Color.RED;
		for (int i = 0; i < lightArray.length; i++) {
			lightArray[i] = new TrafficLights(Direction.values()[i]);
		}

		for (int i = 0; i < lightArray.length; i++) {
			int x = 0;
			int y = 0;
			switch (lightArray[i].getLightDirection()) {
			case NORTH:
				x = this.getX();
				y = getY() + Roads.ROAD_WIDTH / 2 + lightArray[i].getImage().getHeight() / 2;
				break;
			case EAST:
				x = this.getX() - Roads.ROAD_WIDTH / 2 - lightArray[i].getImage().getHeight() / 2;
				;
				y = getY();
				break;
			case SOUTH:
				x = this.getX();
				y = getY() - Roads.ROAD_WIDTH / 2 - lightArray[i].getImage().getHeight() / 2;
				;
				break;
			case WEST:
				x = this.getX() + Roads.ROAD_WIDTH / 2 + lightArray[i].getImage().getWidth();
				y = this.getY();
				break;
			}

			getWorld().addObject(lightArray[i], x, y);

			lightArray[i].setLightRotation();
		}

		//
		// getWorld().addObject(tf2, this.getX() + Roads.ROAD_WIDTH/2 +
		// tf.getImage().getWidth(), getY());
		// tf2.setRotation(270);
		// getWorld().addObject(tf3, this.getX() - Roads.ROAD_WIDTH/2 -
		// tf.getImage().getWidth(), getY());
		// tf3.setRotation(90);
		// getWorld().addObject(tf4, this.getX() , getY() - Roads.ROAD_WIDTH/2 -
		// tf.getImage().getHeight()/2);
		// tf4.setRotation(180);

	}

	// public void stopCars(){
	// if(){
	//
	// }
	// }
	public void act() {
		for (TrafficLights t : lightArray) {
			t.changeColors();
		}
		notifyApproaching();
		notifyAtIntersection();
		notifyIsLeavingIntersection();

		// lightCounter++;
		// switch(this.color){
		// case GREEN :
		// if(lightCounter == GREEN_COUNT){
		// color = TrafficLights.Color.YELLOW;
		// this.setColor(color);
		// }
		// break;
		// case YELLOW :
		// if(lightCounter == YELLOW_COUNT){
		// color = TrafficLights.Color.RED;
		// this.setColor(color);
		// }
		// break;
		// case RED :
		// if(lightCounter == RED_COUNT){
		// color = TrafficLights.Color.GREEN;
		// this.setColor(color);
		// lightCounter = 0;
		// }
		// break;
		//
		//
	}

	private List<IntersectionListener> prevApproaching = new ArrayList<IntersectionListener>();
	private List<IntersectionListener> prevIntersecting = new ArrayList<IntersectionListener>();

	public void notifyApproaching() {
		List<IntersectionListener> isApproachingIntersection = getObjectsInRange(75, IntersectionListener.class);
		for (IntersectionListener l : isApproachingIntersection) {
			if (!prevApproaching.contains(l)) {
				l.isApproaching(this);
			}
		}

	}

	public void notifyAtIntersection() {
		List<IntersectionListener> isAtIntersection = getIntersectingObjects(IntersectionListener.class);
		for (IntersectionListener l : isAtIntersection) {
			if (!prevIntersecting.contains(l)) {
				l.isAtIntersection(this);

			}
			prevIntersecting = isAtIntersection;
		}

	}

	public void notifyIsLeavingIntersection() {
		List<IntersectionListener> isLeaving = getObjectsInRange(75, IntersectionListener.class);
		for (IntersectionListener l : prevApproaching) {
			if (!isLeaving.contains(l)) {
				l.isLeavingIntersection(this);
			}
		}

		prevApproaching = isLeaving;
	}

	public TrafficLights getLightArray(int i) {
		return lightArray[i];
	}
}
