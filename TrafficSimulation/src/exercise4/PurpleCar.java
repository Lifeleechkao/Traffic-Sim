
package exercise4;

public class PurpleCar extends RemoveFromWorld {

	public PurpleCar(Direction d) {
		super(d);
		this.setImage("images\\topCarPurple.png");

	}

	public void act() {
		int turnLeft = r.nextInt(4);
		if (turnLeft == 1) {
			turnLeft();
		}

		super.act();
		if(!dead) {
			remove(this);
		}
	}

}
