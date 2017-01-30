/*
Brendan DeMilt, Chris Pan
1/28/17
Period: 8
Quad tree for a barnes hut simulation
 */
public class NbodyTree {

	
	
	
	
	private class Node{
		
		private Node ur,ul,dr,dl;
		private Square current;
		private Planet body;
		
		public Node(Square s){
			this.current = s;
			this.body = null;
			this.ur = null;
			this.dr = null;
			this.dl = null;
			this.ul = null;
		}
		
		//used typically if the node goes from external to internal, initializes
		//node children, splitting into 4 equal parts
		public void initChildren(){
			
			ul = new Node(new Square(current.getBottom()[0],current.getBottom()[1] + current.getLen()/2 , current.getLen()/2));
			ur = new Node(new Square(current.getBottom()[0] + current.getLen()/2,current.getBottom()[1] + current.getLen()/2, current.getLen()/2));
			dl = new Node(new Square(current.getBottom()[0],current.getBottom()[1], current.getLen()/2));
			dr = new Node(new Square(current.getBottom()[0]+   current.getLen()/2,current.getBottom()[1], current.getLen()/2));
			
			
			//uncomment this section if you want to see each node
			/*
			ul.current.draw();
			ur.current.draw();
			dl.current.draw();
			dr.current.draw();
		*/
		}
		
		
		
		}
	
	//this ratio determines if a bodys force should be used directly or if it is far away enough that the center
	//of mass of its children should be
	//if theta ==0, most accurate but brute force :(
	//if theta ==1, quickest but least accurate :(
	//the standard is .5 since it's a happy mix in between :)
	private double theta =0.5;
	
	
	//root node
	private Node root;
	
	public NbodyTree(double scale){
		
		root = new Node(new Square(-scale,-scale,scale*2));
	}
	
	
	//determines if a node is external or internal
	//is external when it is a 'base square' (has no children)
	//is internal when it has children
	public boolean isExternal(Node n){
		return n.ur == null && n.ul == null && n.dr == null && n.dl == null;
	}
	
	//adds a body to the quad tree such that each node either has node or no planets within it
	public void addBody(Planet p){
		
		add(p,root);
		
	}
	
	//recursively traverses through the quad tree until a happy home is found for the planet!
	public void add(Planet p, Node n){
		 if(n.body == null){
					n.body = p;
					return;
				}
		if(!isExternal(n)){
			n.body = n.body.newCOM(p);
			
			if(p.isIn(n.ur.current)) add(p, n.ur);
			else if(p.isIn(n.dr.current)) add(p, n.dr);
			else if(p.isIn(n.dl.current)) add(p, n.dl);
			else if(p.isIn(n.ul.current)) add(p, n.ul);
		}
		
		 else{
				n.initChildren();
				add(n.body,n);
				add(p,n);
				n.body = n.body.newCOM(p);
		}	
	}

	//sweet jesus in heaven above please let this work
	//uses the almighty barnes hut algorithm to calculate net force acting on a body
			public void calcForce(Planet p){
				force(p,root);
			}
	
			
	//recursively hunts through the tree and compares the ratio between the quadrant width over the distance
	//between that quadrant and the planet with theta to see if an exact comparison between
	// planets should be made
	//if the ratio is smaller than theta, then the center of mass of the region is compared
	//otherwise, it recursively goes deeper into the tree until either the ratio is smaller
	//or the node is external
	public void force(Planet p, Node n){
		
		if(n.body == null || n.body == p)return;
		else if(isExternal(n)){
				p.addForce(n.body);
			}
		else{
			double ratio = n.current.getLen()/p.distance(n.body);
			if(ratio<=theta){
				p.addForce(n.body);
			}
			if(ratio>theta){
				force(p,n.ur);
				force(p,n.ul);
				force(p,n.dr);
				force(p,n.dl);
			}		
		}				
	}
}
