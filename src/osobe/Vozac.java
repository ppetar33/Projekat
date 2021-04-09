package osobe;

public class Vozac extends Osoba {

	private double plata;
	private int brojClanskeKarte;
	private String automobil; // mora biti posebna klasa automobil todo

	public Vozac() {
		super();
		this.plata = 0;
		this.brojClanskeKarte = 0;
		this.automobil = "";
	}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
				 Pol pol, String brojTelefona, int plata, int brojClanskeKarte, String automobil) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobil = automobil;
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


	@Override
	public String toString() {
		return "Vozac{" +
				"plata=" + plata +
				", brojClanskeKarte=" + brojClanskeKarte +
				", automobil='" + automobil + '\'' +
				"} " + super.toString();
	}
}
