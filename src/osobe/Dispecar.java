package osobe;

public class Dispecar extends Musterija {

	
	private int plata;
	private int brojTelefonskeLinije;
	private String odeljenjeNaKojemRadi;
	
	public Dispecar() {
		super();
		this.plata = 0;
		this.brojTelefonskeLinije = 0;
		this.odeljenjeNaKojemRadi = "";
	}

	public Dispecar(String korisnickoIme, String lozinka, String ime, String prezime, double jmbg, String adresa,
			String pol, int brojTelefona, int plata, int brojTelefonskeLinije, String odeljenjeNaKojemRadi) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojTelefonskeLinije = brojTelefonskeLinije;
		this.odeljenjeNaKojemRadi = odeljenjeNaKojemRadi; 
	}


	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public int getBrojTelefonskeLinije() {
		return brojTelefonskeLinije;
	}

	public void setBrojTelefonskeLinije(int brojTelefonskeLinije) {
		this.brojTelefonskeLinije = brojTelefonskeLinije;
	}

	public String getOdeljenjeNaKojemRadi() {
		return odeljenjeNaKojemRadi;
	}

	public void setOdeljenjeNaKojemRadi(String odeljenjeNaKojemRadi) {
		this.odeljenjeNaKojemRadi = odeljenjeNaKojemRadi;
	}

	@Override
	public String toString() {
		return "Dispecar [plata=" + plata + ", brojTelefonskeLinije=" + brojTelefonskeLinije + ", odeljenjeNaKojemRadi="
				+ odeljenjeNaKojemRadi + "]";
	}
	
}
