package exercise4;

import java.util.Random;

import exercise2.TrafficLights.Color;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Car extends Actor implements IntersectionListener {

	protected static Random randomColours = new Random();
	protected Direction d;
	protected Intersection intersection;
	protected TrafficLights t;
	protected Explosions e = new Explosions(); 
	protected state carState = state.APPROACHING;
	protected boolean dead = false;
	protected boolean canTurn = true;
	protected int turningCounter = 0;
	protected int slowSpeed = 2;
	protected int stopSpeed = 0;
	protected int fullSpeed = 3;
	protected int currentSpeed = fullSpeed;
	
protected Random r = new Random();

protected void turnRight() {
	
	if (this.d.ordinal() == d.EAST.ordinal()) {
		if (Math.abs((intersection.getX() - Roads.ROAD_WIDTH / 4) - this.getX()) <= 2 && canTurn) {
			canTurn =false;
			this.setLocation((intersection.getX() - Roads.ROAD_WIDTH / 4), getY());
			assert (t.getColor() != TrafficLights.Color.RED);
			this.turn(90);
			this.d = d.SOUTH;
		}
	}else if (this.d.ordinal() == d.WEST.ordinal()) {
		if (Math.abs((this.getX() - (intersection.getX() + Roads.ROAD_WIDTH / 4)))  <= 2 && canTurn){
			canTurn = false;
			this.setLocation((intersection.getX() + Roads.ROAD_WIDTH / 4), getY());
			assert (t.getColor() != TrafficLights.Color.RED);
			this.turn(90);
			this.d = d.NORTH;
		}
	}else if (this.d.ordinal() == d.NORTH.ordinal()) {
		if (Math.abs((this.getY() - (intersection.getY() + Roads.ROAD_WIDTH / 4)))  <= 2 && canTurn) {
			canTurn = false;
			this.setLocation(getX(), (intersection.getY() + Roads.ROAD_WIDTH / 4));
			assert (t.getColor() != TrafficLights.Color.RED);
			this.turn(90);
			this.d = d.EAST;
		}
	}else if (this.d.ordinal() == d.SOUTH.ordinal()) {
		if ((Math.abs(intersection.getY() - Roads.ROAD_WIDTH / 4) - this.getY()) <= 2 && canTurn) {
			canTurn = false;
			setLocation(getX(), (intersection.getY() - Roads.ROAD_WIDTH / 4));
			assert (t.getColor() != TrafficLights.Color.RED);
			this.turn(90);
			this.d = d.WEST;
		}
	}
}

protected void turnLeft() {

	if (this.d.ordinal() == d.EAST.ordinal()) {
		if ((Math.abs((intersection.getX() + Roads.ROAD_WIDTH / 4) - this.getX())) <= 2 && canTurn) {
			canTurn = false;
			this.setLocation((intersection.getX() - Roads.ROAD_WIDTH / 4), getY());
			assert (t.getColor() != TrafficLights.Color.RED);
				this.turn(270);
				this.d = d.NORTH;
		}
	}

	if (this.d.ordinal() == d.WEST.ordinal()) {
		if (Math.abs((this.getX() - (intersection.getX() - Roads.ROAD_WIDTH / 4)))  <= 2 && canTurn) {
			canTurn = false;
			this.setLocation((intersection.getX() - Roads.ROAD_WIDTH / 4), getY());
			assert (t.getColor() != TrafficLights.Color.RED);
				this.turn(270);
				this.d = d.SOUTH;
		}
	}

	if (this.d.ordinal() == d.NORTH.ordinal()) {
		if (Math.abs((this.getY() - (intersection.getY() - Roads.ROAD_WIDTH / 4)))  <= 2 && canTurn) {
			canTurn = false;
			setLocation(getX(), (intersection.getY() - Roads.ROAD_WIDTH / 4));
			assert (t.getColor() != TrafficLights.Color.RED);
				this.turn(270);
				this.d = d.WEST;
		}
	}

	if (this.d.ordinal() == d.SOUTH.ordinal()) {
		if ((Math.abs((intersection.getY() + Roads.ROAD_WIDTH / 4) - this.getY())) <= 2 && canTurn) {
			canTurn = false;
			setLocation(getX(), (intersection.getY() + Roads.ROAD_WIDTH / 4));
			assert (t.getColor() != TrafficLights.Color.RED);
				this.turn(270);
				this.d = d.WEST;
		}
	}
}

protected static enum state {
	APPROACHING, AT_INTERSECTION, LEAVING;
}

public Car(Direction d) {
	this.d = d;
	
	 
}

public boolean isAtEnd() {
	return ((this.d == Direction.EAST && this.getX() > 0)
			|| (this.d == Direction.WEST && this.getX() < TrafficWorld.WIDTH)
			|| (this.d == Direction.NORTH && this.getY() < TrafficWorld.HEIGHT)
			|| (this.d == Direction.SOUTH && this.getY() > 0));
}


public void act() {
	if(!canTurn){
		turningCounter++;
	}
	if(turningCounter == 10){
		canTurn = true;
		turningCounter = 0;
	}

	move(currentSpeed);
	if (intersection != null) {
		if (this.getRotation() == 0) {
			t = intersection.getLightArray(2);

		} else if (this.getRotation() == 90) {
			t = intersection.getLightArray(0);

		} else if (this.getRotation() == 180) {
			t = intersection.getLightArray(3);

		} else if (this.getRotation() == 270) {
			t = intersection.getLightArray(1);

		}
	}

	if (t != null) {
		switch (carState) {
		case APPROACHING:
			if (t.getColor() == TrafficLights.Color.GREEN) {
				speedUp();
			} else {
				slowDown();

			}
			break;
		case AT_INTERSECTION:
			if (currentSpeed <= slowSpeed) {
				currentSpeed = stopSpeed;
			} else {
				currentSpeed = fullSpeed;
			}
			if (currentSpeed == 0 && t.getColor() == TrafficLights.Color.GREEN) {
				currentSpeed = fullSpeed;
			}

			break;
		case LEAVING:
			speedUp();

			break;
		}

	
		
	}
	move(currentSpeed);
	if (this.carState == state.AT_INTERSECTION) {
		Random b = new Random();
		int randomTurn = b.nextInt(25);
		if (randomTurn == 1 && d == Direction.WEST) {

		}
	}

	try {
		if (this.isTouching(Car.class)) {

			throw (new Exception());

		}
		// if (this.isAtEdge() && isAtEnd()) {
		// getWorld().removeObject(this);
		// }
	} catch (Exception l) {
		getWorld().addObject(e, this.getX(), this.getY());
		getWorld().removeObject(this.getOneIntersectingObject(Car.class));
		dead = true;
		getWorld().removeObject(this);

	}

}

public void speedUp() {
	if (currentSpeed < fullSpeed) {
		currentSpeed++;
	}
}

public void slowDown() {
	if (currentSpeed > slowSpeed) {
		currentSpeed--;
	}
}
// public void setLightColor(TrafficLights.Color lightColor) {
// this.lightColor = lightColor;
// }

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
