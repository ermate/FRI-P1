import java.util.*;

class DN02_63170083 {
	public static Scanner sc;
	public static int n = 0;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		n = sc.nextInt();
		int u = sc.nextInt();

	switch (u) {
		case 1:	System.out.println(manjse()); break;
		case 2:	System.out.println(enakaZadnja()); break;
		case 3: System.out.println(enakaPrva()); break;
		case 4: System.out.println(permutacije()); break;
		}
	}

	//v koliko parih je prvo število strogo manjše od drugega.
	public static int manjse() {
		int manjse = 0;

		for (int i = 0; i < n; i++) {
			int prvo = sc.nextInt();
			int drugo = sc.nextInt();
			if (prvo < drugo) {
				manjse++;
			}
		}
		return manjse;
	}

	//v koliko parih imata obe števili enako zadnjo števko
	public static int enakaZadnja() {
		int enakaZadnja = 0;

		for (int i = 0; i < n; i++) {
			int prvo = sc.nextInt();
			int drugo = sc.nextInt();
			if (prvo % 10 == drugo % 10) {
				enakaZadnja++;
			}
		}	
		return enakaZadnja;
	}

	//v koliko parih imata obe števili enako prvo števko
	public static int enakaPrva() {
		int enakaPrva = 0;

		for (int i = 0; i < n; i++) {
			int prvo = sc.nextInt();
			int drugo = sc.nextInt();

			while (prvo >= 10 ) {
       		prvo /= 10;
    		}
    		while (drugo >= 10 ) {
       		drugo /= 10;
    		}

			if (prvo == drugo) {
				enakaPrva++;
			}
		}
		return enakaPrva;
	}

	//v koliko parih je eno število permutacija drugega
	public static int permutacije() {
		int permutacije = 0;

		for (int i = 0; i < n; i++) {
			int prvo = sc.nextInt();
			int drugo = sc.nextInt();
			if (preslikava(prvo, drugo)) {
				permutacije++;
			}
		}
		return permutacije;		
	}

	//realizacija metode, ki preverja št preslikav izbrane števke x 
	//v obeh podanih številih
	public static boolean preslikava (int prvo, int drugo) {
		int x1 = 0;
		int x2 = 0;
		int y = 0;
		int stPonovitevX = 0;
		int stPonovitevY = 0;

		if (prvo == drugo) return true;
		if (prvo > drugo){
			x1 = prvo;
			x2 = prvo;
			y = drugo;
		}
		else {
			x1 = drugo;
			x2 = drugo;
			y = prvo;
		}

		while (x1 > 0) {
			int stevkaPrvaX = x1 % 10;

			while (x2 > 0) {
				int stevkaDrugaX = x2 % 10;
				if (stevkaPrvaX == stevkaDrugaX) stPonovitevX++;
				x2/=10;
			}

			while (y > 0) {
				int stevkaY = y % 10;
				if (stevkaPrvaX == stevkaY) stPonovitevY++;
				y/=10;
			} 	

			if(stPonovitevX != stPonovitevY) return false;
			
			x1/=10;
			stPonovitevX = stPonovitevY = 0;
			if (prvo > drugo){
				x2 = prvo;
				y = drugo;
			}
			else {
				x2 = drugo;
				y = prvo;
			}
		}
		return true;
	}
}