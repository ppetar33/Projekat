package osobe;

import enumi.Obrisan;
import enumi.Pol;

public class Dispecar extends Osoba {

	private double plata;
	private String brojTelefonskeLinije;
	private Odeljenje odeljenje;
	private Obrisan obrisan;

	public Dispecar() {}

	public Dispecar(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
                    Pol pol, String brojTelefona, double plata, String brojTelefonskeLinije, Odeljenje odeljenje, Obrisan obrisan) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojTelefonskeLinije = brojTelefonskeLinije;
		this.odeljenje = odeljenje;
		this.obrisan = obrisan;
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

	public Obrisan getObrisan() {
		return obrisan;
	}

	public void setObrisan(Obrisan obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Dispecar{" +
				"plata=" + plata +
				", brojTelefonskeLinije='" + brojTelefonskeLinije + '\'' +
				", odeljenje=" + odeljenje +
				", obrisan=" + obrisan +
				"} " + super.toString();
	}
}