package exercise4;

public class RemoveFromWorld extends Car {

	public RemoveFromWorld(Direction d) {
		super(d);
	}
	public void remove(Car c){
		if(isAtEdge() && isAtEnd()){
			getWorld().removeObject(c);
		}
		
	}

}
