import java.util.*;

class DN00_63170083 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long p = sc.nextLong();
		long k = sc.nextLong();

		long pot = (long) Math.pow(p, k); 

		for(int i = 1;; i++) {
			if(fakulteta(i)%pot == 0){
				System.out.println(i);
				break;
			}
		}
	}	

	public static long fakulteta(int f) {
		long fak = 1;
		while(f>0) {
			fak*=f;
			f--;
		}
		return fak;
	}
}