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
	private String[] carImages = { "images\\topCarBlue.png", "images\\topCarRed.png", "images\\topCarPurple.png",
			"images\\topCarYellow.png" };
	private Random r = new Random();

	private static enum state {
		APPROACHING, AT_INTERSECTION, LEAVING;
	}

	public Car(Direction d) {
		this.d = d;
		int colourRole = randomColours.nextInt(carImages.length);
		GreenfootImage image = new GreenfootImage(carImages[colourRole]);
		this.setImage(image);
	}

	public boolean isAtEnd() {
		return ((this.d == Direction.EAST && this.getX() > 0)
				|| (this.d == Direction.WEST && this.getX() < TrafficWorld.WIDTH)
				|| (this.d == Direction.NORTH && this.getX() > 0)
				|| (this.d == Direction.SOUTH && this.getX() < TrafficWorld.HEIGHT));
	}

	public void act() {
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

			if (isAtEdge()) {
				switch (this.d) {
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
		if (this.carState == state.AT_INTERSECTION) {
			Random b = new Random();
			int randomTurn = b.nextInt(25);
			if (randomTurn == 1 && d == Direction.WEST) {

			}
		}
		if (this.d.ordinal() == d.EAST.ordinal()) {
			if (this.getX() == intersection.getX() - Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 25) {
					this.turn(90);
					this.d = d.SOUTH;
				}
			}
		}
		if (this.d.ordinal() == d.EAST.ordinal()) {
			if (this.getX() == intersection.getX() + Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 20) {
					this.turn(270);
					this.d = d.NORTH;
				}
			}
		}
		if (this.d.ordinal() == d.WEST.ordinal()) {
			if (this.getX() == intersection.getX() + Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 15) {
					this.turn(90);
					this.d = d.NORTH;
				}
			}
		}
		if (this.d.ordinal() == d.WEST.ordinal()) {
			if (this.getX() == intersection.getX() - Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 10) {
					this.turn(270);
					this.d = d.SOUTH;
				}
			}
		}
		if (this.d.ordinal() == d.NORTH.ordinal()) {
			if (this.getY() == intersection.getY() - Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 5) {
					this.turn(90);
					this.d = d.EAST;
				}
			}
		}
		if (this.d.ordinal() == d.NORTH.ordinal()) {
			if (this.getY() == intersection.getY() + Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 1) {
					this.turn(270);
					this.d = d.WEST;
				}
			}
		}
		if (this.d.ordinal() == d.SOUTH.ordinal()) {
			if (this.getY() == intersection.getY() + Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 19) {
					this.turn(90);
					this.d = d.EAST;
				}
			}
		}
		if (this.d.ordinal() == d.SOUTH.ordinal()) {
			if (this.getY() == intersection.getY() - Roads.ROAD_WIDTH / 4) {
				int random = r.nextInt(30);
				if (random == 2) {
					this.turn(270);
					this.d = d.WEST;
				}
			}
		}
		if(this.isTouching(Car.class)){
			try{
			throw(new Exception());
			}catch(Exception l){
				GreenfootImage explosion = new GreenfootImage("images\\explosion2.png");
				this.removeTouching(Car.class);
				getWorld().getBackground().drawImage(explosion, this.getX(), this.getY());
				
			}
		}
		
		if (this.isAtEdge() && isAtEnd()) {
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
