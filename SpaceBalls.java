/*
Brendan DeMilt, Chris Pan
1/27/17
Period: 8
Driver class for the space balls algorithm

input format is as shows:

number of bodies
scale of universe
planet_x_coordinate planet_y_coorinate x_velocity y_velocity mass
.
.
.



 */
public class SpaceBalls {

	
	
	
	//fades text in/out for dramatic effect
	public static void fadeText(String text){
		
	}
	
	
	public static void main(String[] args) {

		Planet[] system = new Planet[StdIn.readInt()];

		NBody_Tree tree = new NBody_Tree(StdIn.readDouble());

		for (int i = 0; i < system.length; i++) {
			double x = StdIn.readDouble();
			double y = StdIn.readDouble();
			double vx = StdIn.readDouble();
			double vy = StdIn.readDouble();
			double m = StdIn.readDouble();
			system[i] = new Planet(m, x, y, vx, vy);
			
		}
		
		StdDraw.clear(StdDraw.BLACK);
		
		for(int j =0; j<system.length; j++)tree.addPlanet(system[j]);
		
		
		

	}

}
