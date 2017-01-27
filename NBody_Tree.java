import java.util.ArrayList;

/*
Brendan DeMilt
1/27/17
Period: 8
Symbol table quad tree for n body simulation
 */
public class NBody_Tree {

	private class Node {
		// four node children with different quadrants, a square the node
		// contains, and a planet class for storing info
		
		
		//The key used is a square object where the node value is a planet object
		private Node ul, ur, dl, dr;
		private Square space;
		private Planet info;

		public Node(Square s) {

			space = s;
			ul = null;
			ur = null;
			dl = null;
			dr = null;
			info = null;
		}

		public void drawQuad() {
			
			
			StdDraw.setPenColor(StdDraw.WHITE);
			
			StdDraw.square((space.getX()[0] + space.getX()[1]) / 2, (space.getY()[0] + space.getY()[1]) / 2,
					space.getLen() / 2);
			if(info != null){
				StdDraw.setPenRadius(.002);
			StdDraw.point(info.getPos()[0], info.getPos()[1]);
		//	System.out.println("planet mass: " +info.getMass());
			StdDraw.setPenRadius(.0001);
			}
			
		}

		// splits the node into its many many children
		public void initChildren() {
			ul = new Node(new Square(space.getX()[0], (space.getY()[0] + space.getY()[1]) / 2, space.getLen() / 2));
			ur = new Node(new Square((space.getX()[0] + space.getX()[1]) / 2, (space.getY()[0] + space.getY()[1]) / 2,space.getLen() / 2));
			dl = new Node(new Square(space.getX()[0], space.getY()[0], space.getLen() / 2));
			dr = new Node(new Square((space.getX()[0] + space.getX()[1]) / 2, space.getY()[0], space.getLen() / 2));
		}


		public void setPlanet(Planet p) {
			info = p;
		}

	}

	// root quadrant
	private Node root;

	// this ratio is used to see if a body's force should be used to determine
	// if a sole planet's force should be
	// taken or if the center of mass of the general area.
	// if theta ==1, it will be most accurate but will be brute force
	// if theta ==0, it will be the least accurate but be quickest
	private double theta = .5;

	// initializes universe with first quadrant being having side lengths equal
	// to the scale
	
	
	private double nodeCount;
	
	public NBody_Tree(double scale) {
		nodeCount = 0;
		StdDraw.setScale(scale *-1,scale);
		root = new Node(new Square(scale*-1, scale*-1, 2*scale));
	}

	
	//traverses the tree starting at the root to place a planet in it's quadrant
	public void addPlanet(Planet p){
		add(root,p);
	
	//	System.out.println(nodeCount);
	}
	
	
	//recursively goes through the tree to ensure the planet is stored within its own quadrant
	private void add(Node n, Planet b) {
        if(isInternalNode(n))
        {
        	
        	n.info.updateCenterOfMass(b);
        	if(b.isIn(n.ur.space)) add(n.ur , b);
        	if(b.isIn(n.ul.space)) add(n.ul , b);
        	if(b.isIn(n.dr.space)) add(n.dr , b);
        	if(b.isIn(n.dl.space)) add(n.dl , b);
        }
          
        else if(isPlanet(n))
        {
            Planet p = n.info;
            n.initChildren();
           
            
            n.info.updateCenterOfMass(b);
            
            
            if(p.isIn(n.ur.space)) n.ur.setPlanet(p);
        	if(p.isIn(n.ul.space)) n.ul.setPlanet(p);
        	if(p.isIn(n.dr.space)) n.dr.setPlanet(p);
        	if(p.isIn(n.dl.space)) n.dl.setPlanet(p);
        	 add(n , b);
        	 
        }
        else
        {
            n.setPlanet(b);
        	nodeCount++;
            n.drawQuad();
        }
    }
	// determines if a node is internal, where it is the parent to many
	// different planets
	public boolean isInternalNode(Node x) {

		if (isPlanet(x) )
			return false;
		else
			return x.ur != null && x.ul != null && x.dr != null && x.dl != null;

	}

	// recursive function that traverses the quadtree
	private boolean internal(Node x) {
		if (isPlanet(x))
			return true;
		
		else if(x == null)return false;
		else
			return internal(x.ur) || internal(x.dr) || internal(x.dl) || internal(x.ul);
	}

	// If a node has no children AND has a non-null planet class, it has a
	private boolean isPlanet(Node n) {
		if (n == null)
			return false;
		else
			return n.ul == null && n.ur == null && n.dl == null && n.dr == null && n.info != null;
	}

	public static void main(String[] args) {

		
		
		
		
	}

}
