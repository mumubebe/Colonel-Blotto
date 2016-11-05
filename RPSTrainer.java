import java.util.ArrayList;

public class RPSTrainer {


	public ArrayList<ID> id = new ArrayList<>();
	public static final int BATTLEFIELDS = 3;
	public int player;
	Action action;


	int[] e = {6, 15, 29};


	public void train(int iterations) {
		for (int i = 0; i < iterations; i++) {

			player = i % 2;
			int opp = 1-player;

			int p1 = action.getAction(action.getStrategy(player));
			int p2 = action.getAction(action.getStrategy(opp));

			int util = getPayoff(id.get(p1).b, id.get(p2).b);
			
			for (int a = 0; a < Abstractions.NUM_ACTIONS; a++){
				action.regretSum[player][a] += getPayoff(id.get(a).b,
						id.get(p2).b) - util;

				//action[player].regretSum[a] = Math.max(0, action[player].regretSum[a]);
				//action[player].strategySum[a] += 	action[player].regretSum[a];
			}

		}
	}

	public int getPayoff(int p1[], int p2[]){
		int hillary = 268;
		int trump = 268;

		int o = 0;
		int p = 0;
		int b1 = 11;
		int b2 = 15;
		int b3 = 29;

		if(player==0){ p=hillary; o=trump; }else{ p=trump; o=hillary;}

		for(int i= 0; i<p1.length; i++){
			if(p1[i]>p2[i]){
				if(i==0){
					p+=b1;
				}if(i==1){
					p+=b2;
				}if(i==2){
					p+=b3;
				}
				//p +=1;
			}else if (p1[i]<p2[i]){
				//	p -=1;

				if(i==0){
					o+=b1;
				}if(i==1){
					o+=b2;
				}if(i==2){
					o+=b3;
				}
			}
		}




		if(p>o){
			return 1;
		}else if(p==o){
			return 0;}
		else
			return -1;


	}


	public void create(int r, int[] s){
		s[0] = 0;	
		for(int i=0; i<r+1; i++){
			for(int k=0; k<r+1; k++){
				s[0] = k;
				s[1] = i;
				if(i==r){
					int count = 0;
					for(int c:s){
						count+=c;
					}
					if(count==r)
						id.add(new ID(s));
					s[2]+=1;
					for(int j=1; j<s.length; j++){										
						if(s[j]==r+1){
							if((j+1)==s.length){
								return;
							}
							s[j] = 0;
							s[j+1]+=1;
						}
					}
					create(r, s);
					return;
				}
				int count = 0;
				for(int c:s){
					count+=c;
				}
				if(count==r)
					id.add(new ID(s));		
			}
		}

	}

	public void setAbstractions(){
		Abstractions.NUM_ACTIONS = id.size();
	}


	public void printResults(){
		for(int i=0; i<action.NUM_PLAYERS; i++){
			for(int j=0; j<Abstractions.NUM_ACTIONS; j++){
				if(action.getAverageStrategy(i)[j]*100>0.5)
					System.out.println("["+id.get(j)+"] "+action.getAverageStrategy(i)[j]*100);

			}
			System.out.println("---");
		}
	}

	public  void printDecisions(){
		for(int i = 0; i<id.size();i++){
			System.out.println(" "+id.get(i));
		}
	}



	public static void main(String[] args) {
		RPSTrainer trainer = new RPSTrainer();
		

		trainer.create(50, new int[BATTLEFIELDS]);
		
		trainer.printDecisions();
		trainer.setAbstractions();
		trainer.action = new Action();


	
		trainer.train(100000);
		trainer.printResults();
		
	


	}


}