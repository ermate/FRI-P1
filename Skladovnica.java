// 63170083

class Skladovnica{
	private int kapacitetaPrvega;
	private int prirast;
	private int stSkatel = 0;

	public Skladovnica(int kapacitetaPrvega, int prirast) {
		this.kapacitetaPrvega = kapacitetaPrvega;
		this.prirast = prirast;
	}

	public int kapacitetaKupa(int kup) {
		return (kapacitetaPrvega+(kup-1)*prirast);
	}

	public void dodaj(int stSkatel) {
		this.stSkatel += stSkatel;
	}

	public int skupnoSteviloSkatel() {
		return this.stSkatel;
	}

	public int zasedenostKupa(int kup) {
		int stSkatelTemp = this.stSkatel;
		int kapacitetaKupa = kapacitetaPrvega+(kup-1)*prirast;
		for (int i = 1; i < kup; i++) {
			stSkatelTemp-=kapacitetaPrvega+(i-1)*prirast;
		}
		if (stSkatelTemp > kapacitetaKupa) return kapacitetaKupa;
		else 
			if(stSkatelTemp > 0) return stSkatelTemp;
			else return 0;
	}

	public boolean odvzemi(int stSkatel) {
		if (this.stSkatel >= stSkatel) {
			this.stSkatel-=stSkatel;
			return true;
		}
		return false;
	}

	public int poisciKup(int skatla) {
		if (stSkatel < skatla) return -1;
		int stSkatelTemp = 0;
		for (int i = 1;; i++) {
			stSkatelTemp += kapacitetaPrvega+(i-1)*prirast;
			if (skatla <= stSkatelTemp) return i;
		}
	}
	public Skladovnica prestavi(int kapacitetaPrvega, int prirast) {
		Skladovnica sk =  new Skladovnica(kapacitetaPrvega, prirast);
		sk.dodaj(this.stSkatel);
		this.odvzemi(this.stSkatel);
		return sk;
	}
}