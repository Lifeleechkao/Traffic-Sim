package exercise1;

import java.awt.Color;
import java.util.Random;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TrafficWorld extends World {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	public static final int CELL_SIZE = 1; 
	private static final int HORIZONTALGAPSIZE = 125;
	private static final int VERTICALGAPSIZE = 108;
	private static final int NUMOFHORIZONTALROADS = 5;
	private static final int NUMOFVERTICALROADS = 7;
	private static Roads[] HorizontalRoads = new Roads[NUMOFHORIZONTALROADS];
	private static Roads[] VerticalRoads = new Roads[NUMOFVERTICALROADS];
	private static Random r = new Random();
	public TrafficWorld(){
		super(WIDTH, HEIGHT, CELL_SIZE);
		GreenfootImage background = this.getBackground();
		background.setColor(Color.green);
		background.fill();



		for(int i = 0; i < HorizontalRoads.length; i++){
			Roads road = new Roads();
			addObject(road, WIDTH/2, (((HORIZONTALGAPSIZE +Roads.ROAD_WIDTH)*i)+Roads.ROAD_WIDTH/2));


		}
		for(int i = 0;i < VerticalRoads.length; i++){
			Roads road = new Roads();
			road.setRotation(90);
			addObject(road,(((VERTICALGAPSIZE +Roads.ROAD_WIDTH)*i)+Roads.ROAD_WIDTH/2), HEIGHT/2 );
		}

		for(int i = 0; i< NUMOFHORIZONTALROADS; i++){
			Car car = new Car(Direction.EAST);

			this.addObject(car, r.nextInt(WIDTH)  , ((Roads.ROAD_WIDTH/4)*3) + (( HORIZONTALGAPSIZE + Roads.ROAD_WIDTH) * i));
		}
		for(int i = 0; i < NUMOFHORIZONTALROADS; i++){
			Car car =  new Car(Direction.WEST);
			car.setRotation(180);
			this.addObject(car, r.nextInt(WIDTH)  , ((Roads.ROAD_WIDTH/4 ) + (( HORIZONTALGAPSIZE + Roads.ROAD_WIDTH) * i)));

		}
		for(int i =  0; i < NUMOFVERTICALROADS; i++ ){
			Car car = new Car(Direction.NORTH);
			car.setRotation(270);
			this.addObject(car, ((Roads.ROAD_WIDTH/4)*3) + (( VERTICALGAPSIZE + Roads.ROAD_WIDTH) * i)  , r.nextInt(HEIGHT));
		}
		for(int i =  0; i < NUMOFVERTICALROADS; i++ ){
			Car car = new Car(Direction.SOUTH);
			car.setRotation(90);
			this.addObject(car,  ((Roads.ROAD_WIDTH/4 ) + (( VERTICALGAPSIZE + Roads.ROAD_WIDTH) * i)) , r.nextInt(HEIGHT));
		}
	}



}
