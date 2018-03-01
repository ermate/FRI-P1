import java.util.*;
class DN09_63170083 {
	public static Objava[] objave;
	public static int stVnosov;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String avtor = sc.next();
		stVnosov = sc.nextInt();
		objave = new Objava[stVnosov];

		for (int i = 0; i < stVnosov; i++) {
			String nextVnos = sc.next();
			int stAvtorjev = sc.nextInt();
		
			switch(nextVnos) {
				case("monografija"):
					objave[i] = new Monografija();
					objave[i].dodajSeznamAvtorjev(stAvtorjev);
					for(int j = 0; j < stAvtorjev; j++) {
						String nextAvtor = sc.next();
						if(nextAvtor.equals("#"))
							objave[i].dodajAvtorja(j, avtor);
						else
							objave[i].dodajAvtorja(j, nextAvtor);
					}
					objave[i].dodajNaslov(sc.next());
					((Monografija)objave[i]).dodajZalozbo(sc.next());
					((Monografija)objave[i]).dodajLetoIzdaje(sc.nextInt());
					((Monografija)objave[i]).dodajISBN(sc.next());
					break;

				case("referat"):
					objave[i] = new Referat();
					objave[i].dodajSeznamAvtorjev(stAvtorjev);
					for(int j = 0; j < stAvtorjev; j++) {
						String nextAvtor = sc.next();
						if(nextAvtor.equals("#")) 
							objave[i].dodajAvtorja(j, avtor);
						else
							objave[i].dodajAvtorja(j, nextAvtor);
					}
					objave[i].dodajNaslov(sc.next());
					((Referat)objave[i]).dodajKonferenco(sc.next());
					((Referat)objave[i]).dodajTip(sc.nextBoolean());
					((Referat)objave[i]).dodajZacetnaStr(sc.nextInt());
					((Referat)objave[i]).dodajKoncnaStr(sc.nextInt());
					break;

				case("clanek"):
					objave[i] = new Clanek();
					objave[i].dodajSeznamAvtorjev(stAvtorjev);
					for(int j = 0; j < stAvtorjev; j++) {
						String nextAvtor = sc.next();
						if(nextAvtor.equals("#")) 
							objave[i].dodajAvtorja(j, avtor);
						else
							objave[i].dodajAvtorja(j, nextAvtor);
					}
					objave[i].dodajNaslov(sc.next());
					((Clanek)objave[i]).dodajNaslovRevije(sc.next());
					((Clanek)objave[i]).dodajLetnik(sc.nextInt());
					((Clanek)objave[i]).dodajStevilko(sc.nextInt());
					((Clanek)objave[i]).dodajLetoIzdaje(sc.nextInt());
					((Clanek)objave[i]).dodajRang(sc.nextInt());
					((Clanek)objave[i]).dodajVseRangirane(sc.nextInt());
					((Clanek)objave[i]).dodajZacetnaStr(sc.nextInt());
					((Clanek)objave[i]).dodajKoncnaStr(sc.nextInt());
					break;
			}
			objave[i].izracunTock();
		}
		
		Arrays.sort(objave);

		for (int i = 0; i < stVnosov; i++) {
			objave[i].izpis();
		}
	}
}

abstract class Objava implements Comparable<Objava>{
	private String[] avtorji;
	private String naslov;
	private double tocke;

	public void dodajSeznamAvtorjev(int stAvtorjev) {
		this.avtorji = new String[stAvtorjev];
	}

	public void dodajAvtorja(int index, String avtor) {
		this.avtorji[index] = avtor;
	} 

	public void dodajNaslov(String naslov) {
		this.naslov = naslov;
	}

	abstract public int tockovnaOsnova();
	
	public double izracunTock() {
		double tocke = this.tockovnaOsnova() /(double)this.avtorji.length;
		this.tocke = tocke;
		return tocke;
	}

	public void izpis() {
		for (int i = 0; i < this.avtorji.length; i++) {
			System.out.print(this.avtorji[i]);
			if (i + 1 < this.avtorji.length) System.out.print(", ");
		}
		System.out.printf(": %s. ", this.naslov);
	}

	public String izpisTock() {
		if(this.tocke % 1 == 0) {
			return Integer.toString((int)this.tocke);
		}
		else {
			int osnova = (int)this.tocke; 
			int ostanek = this.tockovnaOsnova() - osnova * this.avtorji.length;

			int i = ostanek; 
			for (; i > 0; i--) {
				if (ostanek / (double)i % 1 == 0 && this.avtorji.length / (double)i % 1 == 0) break;
			}
			return String.format("%d+%d/%d", osnova, ostanek / i, this.avtorji.length / i);
		}
	}

	public int compareTo(Objava o) {
	    if(this.tocke<o.tocke){
	      return 1;
	    }
	    else if(o.tocke<this.tocke){
	      return -1;
	    }
	    return 0;
  }

}

class Monografija extends Objava {
	private String zalozba;
	private int letoIzdaje;
	private String ISBN;

	public void dodajZalozbo(String zalozba) {
		this.zalozba = zalozba;
	}

	public void dodajLetoIzdaje(int letoIzdaje) {
		this.letoIzdaje = letoIzdaje;
	}

	public void dodajISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public int tockovnaOsnova() {
		return 10;
	}

	public void izpis() {
		super.izpis();
		System.out.printf("%s %d, ISBN %s | %s%n", this.zalozba, this.letoIzdaje, this.ISBN, this.izpisTock());
	}
}

class Referat extends Objava {
	private String konferenca;
	private boolean tip;
	private int zacetnaStr;
	private int koncnaStr;

	public void dodajKonferenco(String konferenca) {
		this.konferenca = konferenca;
	}

	public void dodajTip(boolean tip) {
		this.tip = tip;
	}

	public void dodajZacetnaStr(int zacetnaStr) {
		this.zacetnaStr = zacetnaStr;
	}

	public void dodajKoncnaStr(int koncnaStr) {
		this.koncnaStr = koncnaStr;
	}

	public int tockovnaOsnova() {
		if (this.tip == true)
			return 3;
		else
			return 1;
	}

	public void izpis() {
		super.izpis();
		System.out.printf("%s: %d-%d | %s%n", this.konferenca, this.zacetnaStr, this.koncnaStr, this.izpisTock());
	}
}

class Clanek extends Objava {
	private String naslovRevije;
	private int letnik;
	private int stevilka;
	private int letoIzdaje;
	private int rang;
	private int vsiRangirani;
	private int zacetnaStr;
	private int koncnaStr;

	public void dodajNaslovRevije(String naslovRevije) {
		this.naslovRevije = naslovRevije;
	}

	public void dodajLetnik(int letnik) {
		this.letnik = letnik;
	}

	public void dodajStevilko(int stevilka) {
		this.stevilka = stevilka;
	}

	public void dodajLetoIzdaje(int letoIzdaje) {
		this.letoIzdaje = letoIzdaje;
	}

	public void dodajRang(int rang) {
		this.rang = rang;
	}

	public void dodajVseRangirane(int vsiRangirani) {
		this.vsiRangirani = vsiRangirani;
	}

	public void dodajZacetnaStr(int zacetnaStr) {
		this.zacetnaStr = zacetnaStr;
	}

	public void dodajKoncnaStr(int koncnaStr) {
		this.koncnaStr = koncnaStr;
	}

	public int tockovnaOsnova() {
		double p = (double) this.rang / this.vsiRangirani;
		if (p <= 0.25) return 10;
		if (p <= 0.5) return 8;
		if (p <= 0.75) return 6;
		if (p <= 1) return 4;
		return 2;
	}

	public void izpis() {
		super.izpis();
		System.out.printf("%s %d(%d): %d-%d (%d) | %s%n", this.naslovRevije, this.letnik, this.stevilka, this.zacetnaStr, this.koncnaStr, this.letoIzdaje, this.izpisTock());
	}
}