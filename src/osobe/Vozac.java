package osobe;


import enumi.Obrisan;
import enumi.Pol;


public class Vozac extends Osoba {

	private double plata;
	private int brojClanskeKarte;
	private String automobil; // mora biti posebna klasa automobil todo
	private Obrisan obrisan;

	public Vozac() {}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
				 Pol pol, String brojTelefona, double plata, int brojClanskeKarte, String automobil, Obrisan obrisan) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobil = automobil;
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

	public String getAutomobil() {
		return automobil;
	}

	public void setAutomobil(String automobil) {
		this.automobil = automobil;
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
				", automobil='" + automobil + '\'' +
				", obrisan=" + obrisan +
				"} " + super.toString();
	}
}