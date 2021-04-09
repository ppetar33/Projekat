package osobe;

public class Vozac extends Osoba{

	private int plata;
	private int brojClanskeKarte;
	private String automobil;
	private String tipKorisnika;

	public Vozac() {
		super();
		this.plata = 0;
		this.brojClanskeKarte = 0;
		this.automobil = "";
		this.tipKorisnika = "";
	}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, double jmbg, String adresa,
			String pol, int brojTelefona, int plata, int brojClanskeKarte, String automobil, String tipKorisnika) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobil = automobil;
		this.tipKorisnika = tipKorisnika;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
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

	public String getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	@Override
	public String toString() {
		return "Vozac{" +
				"plata=" + plata +
				", brojClanskeKarte=" + brojClanskeKarte +
				", automobil='" + automobil + '\'' +
				", tipKorisnika='" + tipKorisnika + '\'' +
				"} " + super.toString();
	}
}
