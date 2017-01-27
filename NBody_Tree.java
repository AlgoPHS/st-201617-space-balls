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
		
		
		public void drawQuad(){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square((space.getX()[0]+space.getX()[1])/2, (space.getY()[0]+space.getY()[1])/2, (space.getX()[1]-space.getX()[0])/2);
			
			
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
	
	
	
	
//initializes universe with first quadrant being having side lengths equal to the scale
	public NBody_Tree(double scale) {
		root = new Node(new Square(0, 0, scale));
	}

	public void addBody(Planet p) {
		add(root, p);
	}

	private void add(Node n, Planet b) {

	}

	// If a node has no children AND has a non-null planet class, it has a
	private boolean isPlanet(Node n) {
		if (n == null)
			return false;
		else
			return n.ul == null && n.ur == null && n.dl == null && n.dr == null;
	}
	
	
	
	
	public static void main(String[] args){
		
		
		
		
		
	}
	
	
	
	

}
