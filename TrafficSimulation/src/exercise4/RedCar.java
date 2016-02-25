package exercise4;

public class RedCar extends CanWrap {

	public RedCar(Direction d) {
		super(d);
		this.setImage("images\\topCarRed.png");
		
	}
	public void act(){
		canWrap(this, this.d);
		super.act();
		
	}

}
