/*
Brendan DeMilt
1/19/17
Period: 8
Square class representing a square quadrant of the "universe"
 */
public class Square {

	private double xmin, ymin, len;
	

	public Square(double bottomLeftX,double bottomLeftY, double length) {
		xmin = bottomLeftX;
		len = length;
		ymin = bottomLeftY;
	
	}

	//determines if a set of coordinate points are within the square
	public boolean contains(double x, double y){
		return  (xmin < x && x < (xmin+len) && ymin < y && y < (ymin+len));
	}
	
	
	// returns x coordinate range
	public double[] getX() {
		double[] pos = { xmin, xmin+len };
		return pos;
	}

	// returns y coordinate range
	public double[] getY() {
		double pos[] = { ymin, ymin+len };
		return pos;
	}
}
