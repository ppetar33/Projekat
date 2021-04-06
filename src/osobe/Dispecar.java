package osobe;

public class Dispecar extends Musterija {

	
	private String plata;
	private String brojTelefonskeLinije;
	private String odeljenjeNaKojemRadi;
	
	public Dispecar() {
		super();
		this.plata = "";
		this.brojTelefonskeLinije = "";
		this.odeljenjeNaKojemRadi = "";
	}

	public Dispecar(String korisnickoIme, String lozinka, String ime, String prezime, double jmbg, String adresa,
			String pol, int brojTelefona, String plata, String brojTelefonskeLinije, String odeljenjeNaKojemRadi) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojTelefonskeLinije = brojTelefonskeLinije;
		this.odeljenjeNaKojemRadi = odeljenjeNaKojemRadi; 
	}


	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	public String getBrojTelefonskeLinije() {
		return brojTelefonskeLinije;
	}

	public void setBrojTelefonskeLinije(String brojTelefonskeLinije) {
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
