package exercise4;

public class CanWrap extends Car {

	public CanWrap(Direction d) {
		super(d);
		
	}
	public void canWrap(Car c, Direction d){
		if(isAtEdge() && isAtEnd()){
			if(d == Direction.EAST){
				c.setLocation(0, this.getY());
			}else if(d == Direction.WEST){
				c.setLocation(TrafficWorld.WIDTH, this.getY());
			}else if(d == Direction.NORTH){
				c.setLocation(this.getX(), TrafficWorld.HEIGHT);
			}else if(d == Direction.SOUTH){
				c.setLocation(this.getX(), 0);
			}
		}
	}

}
