package exercise3;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TrafficLights extends Actor {
	private static final int GREEN_COUNT = 200;
	private static final int YELLOW_COUNT = 25;
	private static final int RED_COUNT = GREEN_COUNT + YELLOW_COUNT;
	private Color color;
	private int lightCounter = 0;
	private Direction lightDirection;

	public static enum Color {
		RED, GREEN, YELLOW
	}

	private String[] colorImages = { "images/trafficLightRed.png", "images/trafficLightGreen.png",
			"images/trafficLightYellow.png" };

	public TrafficLights(Direction d) {
		lightDirection = d;
		if (d == Direction.EAST || d == Direction.WEST) {
			color = Color.RED;
		} else
			color = Color.GREEN;

		GreenfootImage image = new GreenfootImage(colorImages[color.ordinal()]);
		setImage(image);

	}

	public void setLightRotation() {
		int rotation = 0;
		switch (this.lightDirection) {
		case NORTH:
			rotation = 0;
			break;
		case EAST:
			rotation = 90;
			break;
		case SOUTH:
			rotation = 180;
			break;
		case WEST:
			rotation = 270;
			break;
		}
		this.setRotation(rotation);
	}

	public void setColor(Color newColor) {
		setImage(colorImages[newColor.ordinal()]);
	}

	public void changeColors() {
		lightCounter++;
		switch (this.color) {
		case GREEN:
			if (lightCounter == GREEN_COUNT) {
				color = TrafficLights.Color.YELLOW;
				this.setColor(color);
				lightCounter = 0;
			}
			break;
		case YELLOW:
			if (lightCounter == YELLOW_COUNT) {
				color = TrafficLights.Color.RED;
				this.setColor(color);
				lightCounter = 0;
			}
			break;
		case RED:
			if (lightCounter == RED_COUNT) {
				color = TrafficLights.Color.GREEN;
				this.setColor(color);
				lightCounter = 0;
			}
			break;
		}
	}

	public Direction getLightDirection() {
		return lightDirection;
	}

	public Color getColor() {
		return color;
	}

}
