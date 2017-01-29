/*
Brendan DeMilt Chris Pan
Period: 8
Driver class for the quad tree barnes hut algorithm
 */
public class SpaceBalls {

	public static void main(String[] args) {
		
		int amount = StdIn.readInt()-1;
		double scale = StdIn.readDouble();
		
		NbodyTree tree = new NbodyTree(scale);
		
		Planet[] list = new Planet[amount];
		StdDraw.setScale(-scale, scale);
		StdDraw.clear(StdDraw.BLACK);
		
		Square def = new Square(-scale,-scale, scale*2);
		
		
		double mass = 0;
		//initializes universe
		for(int i =0; i<amount; i++){
			list[i] = new Planet(StdIn.readDouble(),StdIn.readDouble(),StdIn.readDouble(),StdIn.readDouble(),StdIn.readDouble());
			
			tree.addBody(list[i]);
			list[i].draw();
		}
		
		//amount of time that has passed between force updates
		double timeChange = .1;
		
		
		while(true){
			StdDraw.show(30);
			StdDraw.clear(StdDraw.BLACK);
			tree = new NbodyTree(scale);
		for(int k =0; k<amount;k++){
			
			tree.addBody(list[k]);
		}
		for(int j =0; j<amount;j++){
			tree.calcForce(list[j]);
			list[j].updatebody(timeChange);
			list[j].resetForce();
			
		}
		
		for(int honor =0; honor<amount;honor++){
			
			if(def.contains(list[honor]))
			list[honor].draw();
		}
		
		
		
		
		}
		
		

	}

}
