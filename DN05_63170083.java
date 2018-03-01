import java.util.*;
class DN05_63170083 {
	static Scanner sc = new Scanner(System.in);
	static int stPolj = sc.nextInt();
	static int velSkupine = sc.nextInt();
	static int cena = sc.nextInt();
	static int stIgralcev = sc.nextInt();
	static int denar = sc.nextInt();
	static int stPotez = sc.nextInt();

	static MonopoliPolje[] polje = new MonopoliPolje[stPolj];
	static Igralec[] igr = new Igralec[stIgralcev];

	public static void main(String[] args) {

		for(int i = 0; i < stPolj; i++) {
			int skupina = i/velSkupine;
			polje[i] = new MonopoliPolje(cena, skupina);
		}
		for(int i = 0; i < stIgralcev; i++) {
			igr[i] = new Igralec(denar);
		}

		//met kocke
		int igralecNaPotezi = 0;
		for(int i = 0; i < stPotez; i++) {
			int met = sc.nextInt();
			poteza(met, igralecNaPotezi);

			igralecNaPotezi++;
			igralecNaPotezi%=stIgralcev;
			while(igr[igralecNaPotezi].izlocitev() == true) {
				igralecNaPotezi++;
				igralecNaPotezi%=stIgralcev;
			}

			//preveri koliko igralcev je še v igri, če je le en sam se prekine
			int izloceni = 0;
			for(int j = 0; j < stIgralcev; j++) {
				if (igr[j].izlocitev() == true) {
					izloceni++;
				}	
			}
			if (izloceni == stIgralcev-1) break;
		}

		//izpis
		for(int i = 0; i < stIgralcev; i++) {
			System.out.print(igr[i].pozicija() + " ");
			if (igr[i].izlocitev() == true) System.out.print("bankrot");
			else System.out.print(igr[i].denar());
			System.out.println();
		}
	}

	//poteza
	public static void poteza(int met, int igralec) {
		igr[igralec].premik(met, stPolj);
		int pozicija = igr[igralec].pozicija();
		if (polje[pozicija].lastnik() == null && igr[igralec].denar() >= cena) {
			polje[pozicija].nakup(igr[igralec]);
			igr[igralec].placilo(cena);
		}
		if (polje[pozicija].lastnik() != null && polje[pozicija].lastnik() != igr[igralec]) {
			najemnina(pozicija, igralec);
		}
	}

	//placilo najemnine oz bankrot, ce igralec ni zmozen placati
	public static void najemnina(int pozicija, int igralec) {
		Igralec lastnik = polje[pozicija].lastnik();
		int skupina = polje[pozicija].skupina();

		int vLasti = 0;
		for(int i = 0; i < stPolj; i++) {
			if(polje[i].lastnik() == lastnik && polje[i].skupina() == skupina) {
				vLasti++;
			}
		}

		if (igr[igralec].denar() < vLasti) {
			izbris(igr[igralec]);
			igr[igralec].bankrot();
		}
		else {
			igr[igralec].placilo(vLasti);
			lastnik.zasluzek(vLasti);
		}
	}

	//izbris lastnistva
	public static void izbris(Igralec igralec) {
		for(int i = 0; i < stPolj; i++) {
			if(polje[i].lastnik() == igralec) {
				polje[i].nakup(null);
			}
		}
	}
}

//objekt MonopoliPolje
class MonopoliPolje {
	private Igralec lastnik;
	private int cena;
	private int skupina;

	public MonopoliPolje(int cena, int skupina) {
		this.cena = cena;
		this.skupina = skupina;
	}
	public Igralec lastnik() {
		return lastnik;
	}
	public int cena() {
		return cena;
	}
	public int skupina() {
		return skupina;
	}

	public void nakup(Igralec lastnik) {
		this.lastnik = lastnik;
	}
	public void sprostitev() {
		this.lastnik = null;
	}
}

//objekt Igralec
class Igralec {
	private int pozicija = 0;
	private int denar;
	private boolean izlocitev = false;

	public Igralec(int denar) {
		this.denar = denar;
	}

	public int pozicija() {
		return pozicija;
	}
	public int denar() {
		return denar;
	}
	public boolean izlocitev() {
		return izlocitev;
	}

	public void premik(int premik, int stPolj) {
		this.pozicija+=premik;
		this.pozicija%=stPolj;
	}
	public void placilo(int placilo) {
		this.denar-=placilo;
	}
	public void zasluzek(int zasluzek) {
		this.denar+=zasluzek;
	}
	public void bankrot() {
		this.izlocitev = true;
	}
}