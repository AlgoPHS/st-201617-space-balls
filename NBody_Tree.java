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
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square((space.getX()[0] + space.getX()[1]) / 2, (space.getY()[0] + space.getY()[1]) / 2,
					(space.getX()[1] - space.getX()[0]) / 2);
			StdDraw.point(info.getPos()[0], info.getPos()[1]);

		}

		// splits the node into its many many children
		public void initChildren() {
			ul = new Node(new Square(space.getX()[0], (space.getY()[0] + space.getY()[1]) / 2, space.getLen() / 2));
			ur = new Node(new Square((space.getX()[0] + space.getX()[1]) / 2, (space.getY()[0] + space.getY()[1]) / 2,space.getLen() / 2));
			dl = new Node(new Square(space.getX()[0], space.getY()[0], space.getLen() / 2));
			dr = new Node(new Square((space.getX()[0] + space.getX()[1]) / 2, space.getY()[0], space.getLen() / 2));
		}

		public double getMass() {
			if (info != null)
				return info.getMass();
			else
				return ul.getMass() + ur.getMass() + dl.getMass() + dr.getMass();
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
<<<<<<< HEAD

	// initializes universe with first quadrant being having side lengths equal
	// to the scale
=======
	
	
	
	//initializes universe with first quadrant being having side lengths equal to the scale
>>>>>>> 70b408c8ed111588150d98c76d3d7686bee859bb
	public NBody_Tree(double scale) {
		root = new Node(new Square(0, 0, scale));
	}

	public void addBody(Planet p) {
		add(root, p);
	}

<<<<<<< HEAD
	private void add(Node n, Planet b) {

	}

	// determines if a node is internal, where it is the parent to many
	// different planets
	private boolean isInternalNode(Node x) {

		if (isPlanet(x))
			return false;
		else
			return internal(x.ur) || internal(x.dr) || internal(x.dl) || internal(x.ul);

=======
	
	//determines if a node is internal, where it is the parent to many different planets
	private boolean isInternalNode(Node x){
		
		if(isPlanet(x))return false;
		else return internal(x.ur) || internal(x.dr) || internal(x.dl) || internal(x.ul);
		
>>>>>>> 70b408c8ed111588150d98c76d3d7686bee859bb
	}

	// recursive function that traverses the quadtree
	private boolean internal(Node x) {
		if (isPlanet(x))
			return true;
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
<<<<<<< HEAD

	public static void main(String[] args) {

=======
	
	
	private void add(Node n, Planet b) {
        if(isInternalNode(n))
        {
            double nodex , nodey, squarex, squarey;
            nodex = b.getPos()[0];
            nodey = b.getPos()[1];
            squarex = (n.space.getX()[1] + n.space.getX()[0]) / 2;
            squarey = (n.space.getY()[1] + n.space.getY()[0]) / 2;
            if(nodex > squarex)
            {
                if(nodey > squarey)
                {
                    add(n.ur , b);
                }
                else
                {
                    add(n.dr , b);
                }
            }
            else
            {
                if(nodey < squarey)
                {
                    add(n.ul , b);
                }
                else
                {
                    add(n.dl , b);
                }
            }
        }
        else if(isPlanet(n))
        {
            Planet p = n.info;
            n.setPlanet(null);
            add(n , p);
            add(n , b);
        }
        else
        {
            n.setPlanet(b);
        }
    }
	
	public static void main(String[] args){
		
		
		
		
>>>>>>> 70b408c8ed111588150d98c76d3d7686bee859bb
		
		
		
		
	}

}
