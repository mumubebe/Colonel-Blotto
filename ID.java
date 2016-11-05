import java.util.Arrays;

public class ID {


	int[] b = new int [RPSTrainer.BATTLEFIELDS];
	
	public ID(int[] a){
		
		//this.b = a;
		for(int i = 0; i<a.length; i++){
			b[i] = a[i];
		}
		
	//System.out.println(toString());
		
	}
	
	public String toString(){
		return Arrays.toString(b);
	}
}
