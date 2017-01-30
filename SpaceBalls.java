import javax.swing.JOptionPane;

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
		int count = 0;
		//initializes universe
		
		
		for(int i =0; i<amount; i++){
			list[i] = new Planet(StdIn.readDouble(),StdIn.readDouble(),StdIn.readDouble(),StdIn.readDouble(),StdIn.readDouble());
			
			tree.addBody(list[i]);
			list[i].draw();
			
		}
		
		
		
		
		double time = new Double(JOptionPane.showInputDialog("Enter the time change you'd like: "));
		
		
		//amount of time that has passed between force updates
		double timeChange = time;
		
		
		while(true){
			count = 0;
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
		
		
		//draws each node only if they are within the screen
		for(int honor =0; honor<amount;honor++){
			
			if(def.contains(list[honor])){
			list[honor].draw();
			count++;
			
			
			
			}
		}
		StdDraw.text(-scale+(scale/2), -scale+ (scale/8), "Current bodies on screen: "+count);
		
		if(StdDraw.isKeyPressed(32)){
			timeChange+=(timeChange/10);
		}
		
		
		
		}
		
		

	}

}
