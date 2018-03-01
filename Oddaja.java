// 63170083

public class Oddaja {
	public static void main(String[] args) {}

	public abstract static class Lik {
		public abstract boolean pripadajocaTocka(int x, int y);
	}

	public static class Pravokotnik extends Lik {
		private int xLevo;
		private int yZgoraj;
		private int sirina;
		private int visina;

		public Pravokotnik(int xLevo, int yZgoraj, int sirina, int visina) {
			this.xLevo = xLevo;
			this.yZgoraj = yZgoraj;
			this.sirina = sirina;
			this.visina = visina;
		}

		public boolean pripadajocaTocka(int x, int y) {
			if (x >= this.xLevo && x < this.xLevo + this.sirina && y >= this.yZgoraj && y < this.yZgoraj + this.visina) return true;
			else return false;
		}
	}

	public static class Mnogokotnik extends Lik {
		private int[][] omejitve;

		public Mnogokotnik(int[][] omejitve) {
			this.omejitve = omejitve;
		}

		public boolean pripadajocaTocka(int x, int y) {
			boolean pripada = true;

			for (int i = 0; i < omejitve.length; i++) {
				int pogoj1 = this.omejitve[i][0] * x;
				int pogoj2 = this.omejitve[i][1] * y;
				int pogoj3 = this.omejitve[i][2];

				if (pogoj1 + pogoj2 + pogoj3 > 0) pripada = false;	
			}

			return pripada;
		}
	}

	public static class Elipsa extends Lik {
		private int xSredisce;
		private int ySredisce;
		private int a;
		private int b;

		public Elipsa(int xSredisce, int ySredisce, int vodoravnaPolos, int navpicnaPolos) {
			this.xSredisce = xSredisce;
			this.ySredisce = ySredisce;
			this.a = vodoravnaPolos;
			this.b = navpicnaPolos;
		}

		public boolean pripadajocaTocka(int x, int y) {
			int pogoj1 = this.b * this.b * (x - this.xSredisce) * (x - this.xSredisce);
			int pogoj2 = this.a * this.a * (y - this.ySredisce) * (y - this.ySredisce);
			int pogoj3 = this.a * this.a * this.b * this.b;	

			if (pogoj1 + pogoj2 <= pogoj3) return true;
			else return false;
		}
	}

	public static class Presek extends Lik {
		private Lik prvi;
		private Lik drugi;

		public Presek(Lik prvi, Lik drugi) {
			this.prvi = prvi;
			this.drugi = drugi;
		}

		public boolean pripadajocaTocka(int x, int y) {
			if (this.prvi.pripadajocaTocka(x, y) && this.drugi.pripadajocaTocka(x, y)) return true;
			else return false;
		}
	}

	public static class Razlika extends Lik {
		private Lik prvi;
		private Lik drugi;

		public Razlika(Lik prvi, Lik drugi) {
			this.prvi = prvi;
			this.drugi = drugi;
		}

		public boolean pripadajocaTocka(int x, int y) {
			if (this.prvi.pripadajocaTocka(x, y) == true && this.drugi.pripadajocaTocka(x, y) == false) return true;
			return false;
		}
	}
	
	public static class Risar {
		private boolean[][] slika =  new boolean[100][100];

		public Risar() {
		}

		public void narisiLik(Lik lik) {
			for (int x = 0; x < 100; x++) {
				for (int y = 0; y < 100; y++) {
					boolean pripada = false;
					if (lik.pripadajocaTocka(x, y)) pripada = true;

					if (pripada || slika[y][x])
						slika[y][x] = true;
					else 
						slika[y][x] = false;
				}
			}
		}

		public void narisiRob(Lik lik, int debelina) {
			for (int x = 0; x < 100; x++) {
				for (int y = 0; y < 100; y++) {
					boolean pripadaLiku = false;
					boolean imaSoseda = true;
					boolean sosedRoba = false;

					if (lik.pripadajocaTocka(x, y)) pripadaLiku = true;

					if (x == 0 || y == 0 || x == 99 || y == 99) imaSoseda = false;
					else {
						boolean pogoj1 = !lik.pripadajocaTocka(x+1, y) || !lik.pripadajocaTocka(x, y+1) || !lik.pripadajocaTocka(x-1, y) || !lik.pripadajocaTocka(x, y-1);
						if (pogoj1) imaSoseda = false;
						
						for (int i = 1; i <= debelina; i++) {
							if (x - i >= 0 && x + i <= 99 && y - i >= 0 && y + i <= 99) {
								boolean pogoj2 = !lik.pripadajocaTocka(x+i, y) || !lik.pripadajocaTocka(x, y+i) || !lik.pripadajocaTocka(x-i, y) || !lik.pripadajocaTocka(x, y-i);	
								if (pogoj2) sosedRoba = true;								
							}						
						}


					}

					if ((pripadaLiku && !imaSoseda) || (pripadaLiku && sosedRoba) || slika[y][x]) {
						slika[y][x] = true;
					}
					else 
						slika[y][x] = false;
				}
			}
			
		}

		public boolean[][] slika() {
			return this.slika;
		}
	}
}