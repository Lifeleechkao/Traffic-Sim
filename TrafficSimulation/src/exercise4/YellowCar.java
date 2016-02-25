package exercise4;

public class YellowCar extends CanWrap {

	public YellowCar(Direction d) {
		super(d);
		this.setImage("images\\topCarYellow.png");
		
	}
	public void act(){
		canWrap(this , this.d);
		int turnRight = r.nextInt(4);
		if(turnRight == 3){
			turnRight();
		}
		super.act();
	}

}
