package osobe;

public class Dispecar extends Osoba {

	
	private int plata;
	private int brojTelefonskeLinije;
	private String odeljenjeNaKojemRadi;
	private String tipKorisnika;
	
	public Dispecar() {
		super();
		this.plata = 0;
		this.brojTelefonskeLinije = 0;
		this.odeljenjeNaKojemRadi = "";
		this.tipKorisnika = "";
	}

	public Dispecar(String korisnickoIme, String lozinka, String ime, String prezime, double jmbg, String adresa,
			String pol, int brojTelefona, int plata, int brojTelefonskeLinije, String odeljenjeNaKojemRadi, String tipKorisnika) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojTelefonskeLinije = brojTelefonskeLinije;
		this.odeljenjeNaKojemRadi = odeljenjeNaKojemRadi;
		this.tipKorisnika = tipKorisnika;
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
		return "Dispecar{" +
				"plata=" + plata +
				", brojTelefonskeLinije=" + brojTelefonskeLinije +
				", odeljenjeNaKojemRadi='" + odeljenjeNaKojemRadi + '\'' +
				"} " + super.toString();
	}
}
