import java.util.*;
class DN03_63170083 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		long prSt = sc.nextLong();
		long kol = sc.nextLong();

		long ponovitve = kol;
		
		long i = prSt;
		for (; ponovitve > 0; i++) {
				long iTemp = i;	
				while (iTemp%prSt == 0) {
					iTemp/=prSt;
					ponovitve--;
				} 
		}
		System.out.println(i-1);
	}
}