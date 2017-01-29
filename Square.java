/*
Brendan DeMilt Chris Pan
Period: 8
Object that represents a quadrant of space
 */
public class Square {
	
	private double x,y,len;
	public Square(double bottomX, double bottomY, double length){
		x = bottomX;
		y = bottomY;
		len = length;
	}
	
	
	//returns bottom left corner of the square, plus the length
	public double[] getBottom(){
		double[] pos = {this.x,this.y};
		return pos;
	}
	
	public double getLen(){
		return this.len;
	}
	
	
	//determines if a planet object is inside of a square
	public boolean contains(Planet b){
		return x <= b.getPos()[0] && b.getPos()[0] <= (x+len) && y <= b.getPos()[1] && b.getPos()[1] <= (y+len);
		
	}
	
	
	
	//draws the current square for debugging purposes
	public void draw(){
		StdDraw.setPenRadius(.0001);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.square(this.x+this.len/2,this.y+this.len/2, this.len/2);
		StdDraw.setPenRadius(.002);
	}
	
	
	
	

}
