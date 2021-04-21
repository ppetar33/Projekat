package osobe;

import automobili.Automobil;
import enumi.Obrisan;
import enumi.Pol;

public class Vozac extends Osoba {

	private double plata;
	private int brojClanskeKarte;
	private Automobil automobili;
	private Obrisan obrisan;

	public Vozac() {}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
				 Pol pol, String brojTelefona, double plata, int brojClanskeKarte, Automobil automobili, Obrisan obrisan) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobili = automobili;
		this.obrisan = obrisan;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public int getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(int brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public Automobil getAutomobili() {
		return automobili;
	}

	public void setAutomobili(Automobil automobili) {
		this.automobili = automobili;
	}

	public Obrisan getObrisan() {
		return obrisan;
	}

	public void setObrisan(Obrisan obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Vozac{" +
				"plata=" + plata +
				", brojClanskeKarte=" + brojClanskeKarte +
				", automobil='" + automobili + '\'' +
				", obrisan=" + obrisan +
				"} " + super.toString();
	}
}