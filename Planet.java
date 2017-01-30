import java.awt.Color;

/*
Brendan DeMilt, Chris Pan
Period: 8
Stores celestial body information
 */
	public class Planet{
		
		
		private double x,y,vx,vy,fx,fy,m;
		private double G = 6.67e-11;
		
		//initializes planet
		public Planet(double xe, double ye, double vex, double vey, double mass){
			x = xe;
			y = ye;
			vx = vex;
			vy =  vey;
			m = mass;
			
		}
		
		public double getMass(){
			return this.m;
		}
		
		//calculates euclidean distance between bodies
		public double distance(Planet b){
			double dx = b.x-this.x;
			double dy = b.y-this.y;
			
			return Math.sqrt(dx*dx+dy*dy);
		}
		
		//returns body position coordinates
		public double[] getPos(){
			double[] pos = {this.x,this.y};
			return pos;
		}
		
		
		//adds to the net force if the body
		public void addForce(Planet p){
			//this double ensures the planets don't get close enough to go haywire
			double setDistance = 9e3;
			
			
			if(this!=p){
				
				
			double f = (G*this.m*p.m)/((distance(p)*distance(p) + setDistance*setDistance));
			fx += f*(p.x-this.x)/distance(p);
			fy += f*(p.y-this.y)/distance(p);}
			
			
		}
		
		
		//used if this object is inside an internal node, updates its center of mass
		public Planet newCOM(Planet p){
			double newMass = this.m + p.m;
			double newX = ((this.x*this.m)+ (p.x*p.m))/newMass;			
			double newY = ((this.y*this.m)+ (p.y*p.m))/newMass;		
			return new Planet(newX,newY, 0,0,newMass);
		}
		
		
		
		//determines if a planet is inside of a square
		public boolean isIn(Square s){
			return s.contains(this);
		}
		
		//applies net force to accelerate the planet in a given direction
		public void updatebody(double t){
			
			this.vx+= fx*t/this.m;
			this.vy+= fy*t/this.m;
			
			this.x += vx*t;
			this.y += vy*t;
			
			
			
		}
		
		//resets net force
		public void resetForce(){
			this.fx = 0;
			this.fy = 0;
		}
		
		//draws the planet
		public void draw(){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.point(this.x,this.y);
		}
		
		
	}
	