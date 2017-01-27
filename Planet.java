/*
Brendan DeMilt
1/19/17
Period: 8
Planet class containing information about each celestial body simulated
 */
public class Planet {

	private double mass;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double G = 6.67e-11;
	private double ax, ay;

	public Planet(double m, double xe, double ye, double vex, double vey) {
		mass = m;
		x = xe;
		y = ye;
		vx = vex;
		vy = vey;
		ax = 0;
		ay = 0;
	}

	// returns planet mass
	public double getMass() {
		return mass;
	}

	// returns position of a planet in a coordinate array
	public double[] getPos() {
		double[] pos = { x, y };
		return pos;
	}

	// distance between two planets
	public double dist(Planet b) {
		return Math.sqrt(Math.pow(x + b.getPos()[0], 2) + Math.pow(y + b.getPos()[1], 2));
	}

	// adds another force to the net acceleration of the body
	public void addforce(Planet b) {
		double F = (G * b.getMass() * mass) / dist(b);
		ax += (F * (x - b.getPos()[0] / dist(b))) / mass;
		ay += (F * (y - b.getPos()[1] / dist(b))) / mass;
	}

	// resets net force acted on planet, usually in preperation for the next
	// iteration
	public void resetForce() {
		ax = 0;
		ay = 0;
	}

	// uses net force to update planet velocity, also changes coordinates based
	//on new velocity and the time passed
	public void updatebody(double timeChange) {
		vx += ax;
		vy += ay;
		x += vx * timeChange;
		y += vy * timeChange;
	}

	
	//returns true if the planet is contained within a square quadrant
	public boolean isIn(Square s) {

		return s.contains(x, y);
	}

}
