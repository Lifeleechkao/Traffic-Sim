package exercise4;

public class BlueCar extends RemoveFromWorld {
	private boolean turn = true;

	public BlueCar(Direction d) {
		super(d);
		this.setImage("images\\topCarBlue.png");
	}

	public void act() {

		if (turn) {
			turnLeft();
			turn = false;
			
		} else {
			turnRight();
			turn = true;
		}

		super.act();
		if(!dead) {
			remove(this);
		}
	}
}
