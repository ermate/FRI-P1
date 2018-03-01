import java.util.*;
class DN08_63170083{
	static int pozObresti;
	static int negObresti;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		pozObresti = sc.nextInt();
		negObresti = sc.nextInt();

		int stUkazov = sc.nextInt();
		
		ArrayList <BancniRacun> racuni = new ArrayList<BancniRacun>();

		for (int i = 0; i < stUkazov; i++) {
			String tipUkaza = sc.next();
			int dan = sc.nextInt();
			int mesec = sc.nextInt();
			int leto = sc.nextInt();
			String naziv = sc.next();
			int k = 0;
			int index = 0;

			switch(tipUkaza) {
				case ("r"): 
					int N = sc.nextInt();
					int D = sc.nextInt();
					int M = sc.nextInt();
					racuni.add(new BancniRacun(dan, mesec, leto, naziv, N, D, M));
					break;

				case ("+"):
					k = sc.nextInt();
					for(int j = 0; j< racuni.size(); j++) {
						if (naziv.equals(racuni.get(j).getNaziv())) {
							index = j;
							break;
						}	
					}

					racuni.get(index).polog(mesec, leto, k);
					break;

				case ("-"):
					k = sc.nextInt();
					for(int j = 0; j< racuni.size(); j++) {
						if (naziv.equals(racuni.get(j).getNaziv())) {
							index = j;
							break;
						}	
					}

					racuni.get(index).dvig(dan, mesec, leto, k);
					break;
			}
		}
	}
}

class BancniRacun {
	private String naziv;
	private int stanje = 0;
	private int N;
	private int D;
	private int M;

	private int dan;
	private int mesec;
	private int leto;

	private int dnevDvig;
	private int mesDvig;

	private int mesecZadnjeObresti;
	private int letoZadnjeObresti;

	private int danZadnjiDvig;
	private int mesecZadnjiDvig;
	private int letoZadnjiDvig;

	public BancniRacun(int dan, int mesec, int leto, String naziv, int N, int D, int M) {
		this.dan = dan;
		this.mesec = mesec;
		this.leto = leto;

		this.naziv = naziv;
		this.stanje = 0;
		this.N = N;
		this.D = D;
		this.M = M;
		System.out.println("OK");
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void obresti() {
		if (this.stanje >= 0) {
			this.stanje += (int) Math.floor(this.stanje*DN08_63170083.pozObresti/1000); 
		}
		else {
			this.stanje -= (int) Math.floor((-this.stanje)*DN08_63170083.negObresti/1000); 
		}
	}

	public void polog(int mesec, int leto, int k) {
		if (this.mesecZadnjeObresti != mesec || this.letoZadnjeObresti != leto) {
			int oldMeseciObresti = (this.letoZadnjeObresti-2000)*12 + this.mesecZadnjeObresti;
			int meseciObresti = (leto-2000)*12 + mesec - oldMeseciObresti;

			for (int i = 0; i < meseciObresti; i++) {
				obresti();
			}

			this.mesecZadnjeObresti = mesec;
			this.letoZadnjeObresti = leto;
		}

		this.stanje += k;
		System.out.println(this.stanje);
	}

	public void dvig(int dan, int mesec, int leto, int k) {
		if (this.mesecZadnjeObresti != mesec || this.letoZadnjeObresti != leto) {
			int oldMeseciObresti = (this.letoZadnjeObresti-2000)*12 + this.mesecZadnjeObresti;
			int meseciObresti = (leto-2000)*12 + mesec - oldMeseciObresti;

			for (int i = 0; i < meseciObresti; i++) {
				obresti();
			}

			this.mesecZadnjeObresti = mesec;
			this.letoZadnjeObresti = leto;
		}

		if (this.danZadnjiDvig != dan && this.mesecZadnjiDvig == mesec && this.letoZadnjiDvig == leto) {
			this.dnevDvig = 0;
		}		
		if (this.mesecZadnjiDvig != mesec || this.letoZadnjiDvig != leto) {
			this.dnevDvig = 0;
			this.mesDvig = 0;
		}

		if (this.stanje - k < -this.N && this.N != -1) {
			System.out.println("N");
			return;
		}
		if (this.dnevDvig + k > this.D && this.D != -1) {
			System.out.println("D");
			return;
		}
		if (this.mesDvig + k > this.M && this.M != -1) {
			System.out.println("M");
			return;
		}

		if (this.danZadnjiDvig == dan && this.mesecZadnjiDvig == mesec && this.letoZadnjiDvig == leto) {
			this.dnevDvig += k;	
			this.mesDvig += k;
		}

		if (this.danZadnjiDvig != dan && this.mesecZadnjiDvig == mesec && this.letoZadnjiDvig == leto) {
			this.mesDvig += k;

			this.danZadnjiDvig = dan;
			this.dnevDvig = k;
		}
		
		if (this.mesecZadnjiDvig != mesec || this.letoZadnjiDvig != leto) {
			this.dnevDvig = k;
			this.mesDvig = k;
			this.danZadnjiDvig = dan;
			this.mesecZadnjiDvig = mesec;
			this.letoZadnjiDvig = leto;
		}

		this.stanje -= k;

		System.out.println(this.stanje);
	}
}