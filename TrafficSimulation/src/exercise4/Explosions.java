package exercise4;

import greenfoot.Actor;

public class Explosions extends Actor {
	private int explosionCounter = 0;

	public Explosions() {
		this.setImage("images\\explosion1.png");
	}

	public void act() {
		explosionCounter++;

		if (explosionCounter == 50) {
			this.setImage("images\\explosion2.png");
		}
		if (explosionCounter == 100) {
			this.setImage("images\\explosion3.png");
		}
		if (explosionCounter == 150) {
			getWorld().removeObject(this);
		}
	}

}
