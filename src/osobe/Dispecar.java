package osobe;

public class Dispecar extends Osoba {

	private double plata;
	private String brojTelefonskeLinije;
	private Odeljenje odeljenje;

	public Dispecar() {
		super();
		this.plata = 0;
		this.brojTelefonskeLinije = "";
	}

	public Dispecar(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
					Pol pol, String brojTelefona, double plata, String brojTelefonskeLinije, Odeljenje odeljenje) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojTelefonskeLinije = brojTelefonskeLinije;
		this.odeljenje = odeljenje;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getBrojTelefonskeLinije() {
		return brojTelefonskeLinije;
	}

	public void setBrojTelefonskeLinije(String brojTelefonskeLinije) {
		this.brojTelefonskeLinije = brojTelefonskeLinije;
	}

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	@Override
	public String toString() {
		return "Dispecar{" +
				"plata=" + plata +
				", brojTelefonskeLinije='" + brojTelefonskeLinije + '\'' +
				", odeljenje=" + odeljenje +
				'}';
	}
}
