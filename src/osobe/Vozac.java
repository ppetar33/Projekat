package osobe;

import automobili.Automobil;
import enumi.Pol;

public class Vozac extends Osoba {

	private String plata;
	private int brojClanskeKarte;
	private Automobil automobili;
	private double ocena;

	public Vozac() {
		this.plata = "";
		this.brojClanskeKarte = 0;
		this.automobili = null;
		this.ocena = 0;
	}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
				 Pol pol, String brojTelefona, boolean obrisan,String plata, int brojClanskeKarte, Automobil automobili, double ocena) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobili = automobili;
		this.ocena = ocena;
	}

	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
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

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	@Override
	public String toString() {
		return "Vozac{" +
				"plata='" + plata + '\'' +
				", brojClanskeKarte=" + brojClanskeKarte +
				", automobili=" + automobili +
				", ocena=" + ocena +
				"} " + super.toString();
	}
}