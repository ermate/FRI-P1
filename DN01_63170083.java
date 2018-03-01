import java.util.*;

class DN01_63170083 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vrsta = sc.nextInt();
		int sedez = sc.nextInt();
		int pomoc = sc.nextInt();

		int jakaV = 0, jakaS = 0, jakaR = 0;
		int darkoV = vrsta-1, darkoS = sedez-1, darkoR = 0;
		Boolean odziv = true;

		for(int i=1; i<=pomoc; i++) { 
			int pomocV = sc.nextInt();
			int pomocS = sc.nextInt();

			int razdaljaJ = Math.abs(pomocV-jakaV)+Math.abs(pomocS-jakaS);
			int razdaljaD = Math.abs(pomocV-darkoV)+Math.abs(pomocS-darkoS);
		
			if(razdaljaJ == razdaljaD) {
				if (odziv == true){
					jakaV = pomocV;
					jakaS = pomocS;
					jakaR += razdaljaJ;
					odziv = false;		
				}
				else {
					darkoV = pomocV;
					darkoS = pomocS;
					darkoR += razdaljaD;
					odziv = true;	
				}
			}
			else {
				if(razdaljaJ < razdaljaD) {
					jakaV = pomocV;
					jakaS = pomocS;
					jakaR += razdaljaJ;
				}
				else {
					darkoV = pomocV;
					darkoS = pomocS;
					darkoR += razdaljaD;				
				}
			}
		}
		System.out.println(jakaR);
		System.out.println(darkoR);	
	}
}